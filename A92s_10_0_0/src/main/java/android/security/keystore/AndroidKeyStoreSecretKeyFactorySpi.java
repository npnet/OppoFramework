package android.security.keystore;

import android.security.Credentials;
import android.security.GateKeeper;
import android.security.KeyStore;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterDefs;
import android.security.keystore.KeyProperties;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.ProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.SecretKeySpec;

public class AndroidKeyStoreSecretKeyFactorySpi extends SecretKeyFactorySpi {
    private final KeyStore mKeyStore = KeyStore.getInstance();

    /* access modifiers changed from: protected */
    @Override // javax.crypto.SecretKeyFactorySpi
    public KeySpec engineGetKeySpec(SecretKey key, Class keySpecClass) throws InvalidKeySpecException {
        String entryAlias;
        if (keySpecClass == null) {
            throw new InvalidKeySpecException("keySpecClass == null");
        } else if (!(key instanceof AndroidKeyStoreSecretKey)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Only Android KeyStore secret keys supported: ");
            sb.append(key != null ? key.getClass().getName() : "null");
            throw new InvalidKeySpecException(sb.toString());
        } else if (SecretKeySpec.class.isAssignableFrom(keySpecClass)) {
            throw new InvalidKeySpecException("Key material export of Android KeyStore keys is not supported");
        } else if (KeyInfo.class.equals(keySpecClass) == 1) {
            AndroidKeyStoreKey keystoreKey = (AndroidKeyStoreKey) key;
            String keyAliasInKeystore = keystoreKey.getAlias();
            if (keyAliasInKeystore.startsWith(Credentials.USER_PRIVATE_KEY) == 1) {
                entryAlias = keyAliasInKeystore.substring(Credentials.USER_PRIVATE_KEY.length());
            } else if (keyAliasInKeystore.startsWith(Credentials.USER_SECRET_KEY) == 1) {
                entryAlias = keyAliasInKeystore.substring(Credentials.USER_SECRET_KEY.length());
            } else {
                throw new InvalidKeySpecException("Invalid key alias: " + keyAliasInKeystore);
            }
            return getKeyInfo(this.mKeyStore, entryAlias, keyAliasInKeystore, keystoreKey.getUid());
        } else {
            throw new InvalidKeySpecException("Unsupported key spec: " + keySpecClass.getName());
        }
    }

    static KeyInfo getKeyInfo(KeyStore keyStore, String entryAlias, String keyAliasInKeystore, int keyUid) {
        boolean insideSecureHardware;
        int origin;
        KeyCharacteristics keyCharacteristics = new KeyCharacteristics();
        int errorCode = keyStore.getKeyCharacteristics(keyAliasInKeystore, null, null, keyUid, keyCharacteristics);
        boolean invalidatedByBiometricEnrollment = true;
        if (errorCode == 1) {
            try {
                if (keyCharacteristics.hwEnforced.containsTag(KeymasterDefs.KM_TAG_ORIGIN) == 1) {
                    origin = KeyProperties.Origin.fromKeymaster(keyCharacteristics.hwEnforced.getEnum(KeymasterDefs.KM_TAG_ORIGIN, -1));
                    insideSecureHardware = true;
                } else if (keyCharacteristics.swEnforced.containsTag(KeymasterDefs.KM_TAG_ORIGIN) == 1) {
                    origin = KeyProperties.Origin.fromKeymaster(keyCharacteristics.swEnforced.getEnum(KeymasterDefs.KM_TAG_ORIGIN, -1));
                    insideSecureHardware = false;
                } else {
                    throw new ProviderException("Key origin not available");
                }
                long keySizeUnsigned = keyCharacteristics.getUnsignedInt(KeymasterDefs.KM_TAG_KEY_SIZE, -1);
                if (keySizeUnsigned == -1) {
                    throw new ProviderException("Key size not available");
                } else if (keySizeUnsigned <= 2147483647L) {
                    int keySize = (int) keySizeUnsigned;
                    int purposes = KeyProperties.Purpose.allFromKeymaster(keyCharacteristics.getEnums(KeymasterDefs.KM_TAG_PURPOSE));
                    List<String> encryptionPaddingsList = new ArrayList<>();
                    List<String> signaturePaddingsList = new ArrayList<>();
                    for (Integer num : keyCharacteristics.getEnums(KeymasterDefs.KM_TAG_PADDING)) {
                        int keymasterPadding = num.intValue();
                        try {
                            encryptionPaddingsList.add(KeyProperties.EncryptionPadding.fromKeymaster(keymasterPadding));
                        } catch (IllegalArgumentException e) {
                            try {
                                signaturePaddingsList.add(KeyProperties.SignaturePadding.fromKeymaster(keymasterPadding));
                            } catch (IllegalArgumentException e2) {
                                throw new ProviderException("Unsupported encryption padding: " + keymasterPadding);
                            }
                        }
                    }
                    String[] encryptionPaddings = (String[]) encryptionPaddingsList.toArray(new String[encryptionPaddingsList.size()]);
                    String[] signaturePaddings = (String[]) signaturePaddingsList.toArray(new String[signaturePaddingsList.size()]);
                    String[] digests = KeyProperties.Digest.allFromKeymaster(keyCharacteristics.getEnums(KeymasterDefs.KM_TAG_DIGEST));
                    String[] blockModes = KeyProperties.BlockMode.allFromKeymaster(keyCharacteristics.getEnums(KeymasterDefs.KM_TAG_BLOCK_MODE));
                    int keymasterSwEnforcedUserAuthenticators = keyCharacteristics.swEnforced.getEnum(KeymasterDefs.KM_TAG_USER_AUTH_TYPE, 0);
                    int keymasterHwEnforcedUserAuthenticators = keyCharacteristics.hwEnforced.getEnum(KeymasterDefs.KM_TAG_USER_AUTH_TYPE, 0);
                    List<BigInteger> keymasterSecureUserIds = keyCharacteristics.getUnsignedLongs(KeymasterDefs.KM_TAG_USER_SECURE_ID);
                    Date keyValidityStart = keyCharacteristics.getDate(KeymasterDefs.KM_TAG_ACTIVE_DATETIME);
                    Date keyValidityForOriginationEnd = keyCharacteristics.getDate(KeymasterDefs.KM_TAG_ORIGINATION_EXPIRE_DATETIME);
                    Date keyValidityForConsumptionEnd = keyCharacteristics.getDate(KeymasterDefs.KM_TAG_USAGE_EXPIRE_DATETIME);
                    boolean userAuthenticationRequired = !keyCharacteristics.getBoolean(KeymasterDefs.KM_TAG_NO_AUTH_REQUIRED);
                    long userAuthenticationValidityDurationSeconds = keyCharacteristics.getUnsignedInt(KeymasterDefs.KM_TAG_AUTH_TIMEOUT, -1);
                    if (userAuthenticationValidityDurationSeconds <= 2147483647L) {
                        boolean userAuthenticationRequirementEnforcedBySecureHardware = (userAuthenticationRequired == 1 && keymasterHwEnforcedUserAuthenticators != 0 && keymasterSwEnforcedUserAuthenticators == 0) ? true : false;
                        boolean userAuthenticationValidWhileOnBody = keyCharacteristics.hwEnforced.getBoolean(KeymasterDefs.KM_TAG_ALLOW_WHILE_ON_BODY);
                        boolean trustedUserPresenceRequred = keyCharacteristics.hwEnforced.getBoolean(KeymasterDefs.KM_TAG_TRUSTED_USER_PRESENCE_REQUIRED);
                        if (keymasterSwEnforcedUserAuthenticators != 2 && keymasterHwEnforcedUserAuthenticators != 2) {
                            invalidatedByBiometricEnrollment = false;
                        } else if (keymasterSecureUserIds == null || keymasterSecureUserIds.isEmpty() == 1 || keymasterSecureUserIds.contains(getGateKeeperSecureUserId()) == 1) {
                            invalidatedByBiometricEnrollment = false;
                        }
                        return new KeyInfo(entryAlias, insideSecureHardware, origin, keySize, keyValidityStart, keyValidityForOriginationEnd, keyValidityForConsumptionEnd, purposes, encryptionPaddings, signaturePaddings, digests, blockModes, userAuthenticationRequired, (int) userAuthenticationValidityDurationSeconds, userAuthenticationRequirementEnforcedBySecureHardware, userAuthenticationValidWhileOnBody, trustedUserPresenceRequred, invalidatedByBiometricEnrollment, keyCharacteristics.hwEnforced.getBoolean(KeymasterDefs.KM_TAG_TRUSTED_CONFIRMATION_REQUIRED));
                    }
                    throw new ProviderException("User authentication timeout validity too long: " + userAuthenticationValidityDurationSeconds + " seconds");
                } else {
                    throw new ProviderException("Key too large: " + keySizeUnsigned + " bits");
                }
            } catch (IllegalArgumentException e3) {
                throw new ProviderException("Unsupported key characteristic", e3);
            }
        } else {
            throw new ProviderException("Failed to obtain information about key. Keystore error: " + errorCode);
        }
    }

    private static BigInteger getGateKeeperSecureUserId() throws ProviderException {
        try {
            return BigInteger.valueOf(GateKeeper.getSecureUserId());
        } catch (IllegalStateException e) {
            throw new ProviderException("Failed to get GateKeeper secure user ID", e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.SecretKeyFactorySpi
    public SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
        throw new InvalidKeySpecException("To generate secret key in Android Keystore, use KeyGenerator initialized with " + KeyGenParameterSpec.class.getName());
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.SecretKeyFactorySpi
    public SecretKey engineTranslateKey(SecretKey key) throws InvalidKeyException {
        if (key == null) {
            throw new InvalidKeyException("key == null");
        } else if ((key instanceof AndroidKeyStoreSecretKey) == 1) {
            return key;
        } else {
            throw new InvalidKeyException("To import a secret key into Android Keystore, use KeyStore.setEntry");
        }
    }
}
