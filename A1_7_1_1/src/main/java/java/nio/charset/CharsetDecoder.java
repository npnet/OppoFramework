package java.nio.charset;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/*  JADX ERROR: NullPointerException in pass: ReSugarCode
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ReSugarCode.initClsEnumMap(ReSugarCode.java:159)
    	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:44)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
    	at java.lang.Iterable.forEach(Iterable.java:75)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
    	at jadx.core.ProcessClass.process(ProcessClass.java:37)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ExtractFieldInit.checkStaticFieldsInit(ExtractFieldInit.java:58)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:44)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
    	at java.lang.Iterable.forEach(Iterable.java:75)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
    	at jadx.core.ProcessClass.process(ProcessClass.java:37)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public abstract class CharsetDecoder {
    /* renamed from: -assertionsDisabled */
    static final /* synthetic */ boolean f15-assertionsDisabled = false;
    private static final int ST_CODING = 1;
    private static final int ST_END = 2;
    private static final int ST_FLUSHED = 3;
    private static final int ST_RESET = 0;
    private static String[] stateNames;
    private final float averageCharsPerByte;
    private final Charset charset;
    private CodingErrorAction malformedInputAction;
    private final float maxCharsPerByte;
    private String replacement;
    private int state;
    private CodingErrorAction unmappableCharacterAction;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e9 in method: java.nio.charset.CharsetDecoder.<clinit>():void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Iterable.java:75)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e9
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 9 more
        */
    static {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e9 in method: java.nio.charset.CharsetDecoder.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.charset.CharsetDecoder.<clinit>():void");
    }

    protected abstract CoderResult decodeLoop(ByteBuffer byteBuffer, CharBuffer charBuffer);

    private CharsetDecoder(Charset cs, float averageCharsPerByte, float maxCharsPerByte, String replacement) {
        this.malformedInputAction = CodingErrorAction.REPORT;
        this.unmappableCharacterAction = CodingErrorAction.REPORT;
        this.state = 0;
        this.charset = cs;
        if (averageCharsPerByte <= 0.0f) {
            throw new IllegalArgumentException("Non-positive averageCharsPerByte");
        } else if (maxCharsPerByte <= 0.0f) {
            throw new IllegalArgumentException("Non-positive maxCharsPerByte");
        } else if (Charset.atBugLevel("1.4") || averageCharsPerByte <= maxCharsPerByte) {
            this.replacement = replacement;
            this.averageCharsPerByte = averageCharsPerByte;
            this.maxCharsPerByte = maxCharsPerByte;
        } else {
            throw new IllegalArgumentException("averageCharsPerByte exceeds maxCharsPerByte");
        }
    }

    protected CharsetDecoder(Charset cs, float averageCharsPerByte, float maxCharsPerByte) {
        this(cs, averageCharsPerByte, maxCharsPerByte, "�");
    }

    public final Charset charset() {
        return this.charset;
    }

    public final String replacement() {
        return this.replacement;
    }

    public final CharsetDecoder replaceWith(String newReplacement) {
        if (newReplacement == null) {
            throw new IllegalArgumentException("Null replacement");
        }
        int len = newReplacement.length();
        if (len == 0) {
            throw new IllegalArgumentException("Empty replacement");
        } else if (((float) len) > this.maxCharsPerByte) {
            throw new IllegalArgumentException("Replacement too long");
        } else {
            this.replacement = newReplacement;
            implReplaceWith(newReplacement);
            return this;
        }
    }

    protected void implReplaceWith(String newReplacement) {
    }

    public CodingErrorAction malformedInputAction() {
        return this.malformedInputAction;
    }

    public final CharsetDecoder onMalformedInput(CodingErrorAction newAction) {
        if (newAction == null) {
            throw new IllegalArgumentException("Null action");
        }
        this.malformedInputAction = newAction;
        implOnMalformedInput(newAction);
        return this;
    }

    protected void implOnMalformedInput(CodingErrorAction newAction) {
    }

    public CodingErrorAction unmappableCharacterAction() {
        return this.unmappableCharacterAction;
    }

    public final CharsetDecoder onUnmappableCharacter(CodingErrorAction newAction) {
        if (newAction == null) {
            throw new IllegalArgumentException("Null action");
        }
        this.unmappableCharacterAction = newAction;
        implOnUnmappableCharacter(newAction);
        return this;
    }

    protected void implOnUnmappableCharacter(CodingErrorAction newAction) {
    }

    public final float averageCharsPerByte() {
        return this.averageCharsPerByte;
    }

    public final float maxCharsPerByte() {
        return this.maxCharsPerByte;
    }

    public final CoderResult decode(ByteBuffer in, CharBuffer out, boolean endOfInput) {
        CoderResult cr;
        int newState = endOfInput ? 2 : 1;
        if (!(this.state == 0 || this.state == 1 || (endOfInput && this.state == 2))) {
            throwIllegalStateException(this.state, newState);
        }
        this.state = newState;
        while (true) {
            try {
                cr = decodeLoop(in, out);
                if (cr.isOverflow()) {
                    return cr;
                }
                if (cr.isUnderflow()) {
                    if (!endOfInput || !in.hasRemaining()) {
                        return cr;
                    }
                    cr = CoderResult.malformedForLength(in.remaining());
                }
                CodingErrorAction action = null;
                if (cr.isMalformed()) {
                    action = this.malformedInputAction;
                } else if (cr.isUnmappable()) {
                    action = this.unmappableCharacterAction;
                } else if (!f15-assertionsDisabled) {
                    throw new AssertionError(cr.toString());
                }
                if (action == CodingErrorAction.REPORT) {
                    return cr;
                }
                if (action == CodingErrorAction.REPLACE) {
                    if (out.remaining() < this.replacement.length()) {
                        return CoderResult.OVERFLOW;
                    }
                    out.put(this.replacement);
                }
                if (action == CodingErrorAction.IGNORE || action == CodingErrorAction.REPLACE) {
                    in.position(in.position() + cr.length());
                } else if (!f15-assertionsDisabled) {
                    throw new AssertionError();
                }
            } catch (BufferUnderflowException x) {
                throw new CoderMalfunctionError(x);
            } catch (BufferOverflowException x2) {
                throw new CoderMalfunctionError(x2);
            }
        }
        return cr;
    }

    public final CoderResult flush(CharBuffer out) {
        if (this.state == 2) {
            CoderResult cr = implFlush(out);
            if (cr.isUnderflow()) {
                this.state = 3;
            }
            return cr;
        }
        if (this.state != 3) {
            throwIllegalStateException(this.state, 3);
        }
        return CoderResult.UNDERFLOW;
    }

    protected CoderResult implFlush(CharBuffer out) {
        return CoderResult.UNDERFLOW;
    }

    public final CharsetDecoder reset() {
        implReset();
        this.state = 0;
        return this;
    }

    protected void implReset() {
    }

    public final CharBuffer decode(ByteBuffer in) throws CharacterCodingException {
        int n = (int) (((float) in.remaining()) * averageCharsPerByte());
        CharBuffer out = CharBuffer.allocate(n);
        if (n == 0 && in.remaining() == 0) {
            return out;
        }
        reset();
        while (true) {
            CoderResult cr = in.hasRemaining() ? decode(in, out, true) : CoderResult.UNDERFLOW;
            if (cr.isUnderflow()) {
                cr = flush(out);
            }
            if (cr.isUnderflow()) {
                out.flip();
                return out;
            } else if (cr.isOverflow()) {
                n = (n * 2) + 1;
                CharBuffer o = CharBuffer.allocate(n);
                out.flip();
                o.put(out);
                out = o;
            } else {
                cr.throwException();
            }
        }
    }

    public boolean isAutoDetecting() {
        return f15-assertionsDisabled;
    }

    public boolean isCharsetDetected() {
        throw new UnsupportedOperationException();
    }

    public Charset detectedCharset() {
        throw new UnsupportedOperationException();
    }

    private void throwIllegalStateException(int from, int to) {
        throw new IllegalStateException("Current state = " + stateNames[from] + ", new state = " + stateNames[to]);
    }
}
