package android.provider;

import android.annotation.OppoHook;
import android.annotation.OppoHook.OppoHookType;
import android.annotation.OppoHook.OppoRomType;
import android.net.Uri;

/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ExtractFieldInit.checkStaticFieldsInit(ExtractFieldInit.java:58)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:44)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:42)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public final class OppoSettings {

    @OppoHook(level = OppoHookType.NEW_FIELD, note = "MTK ADD [Originally defined in Settings.Global in Settings.java ]", property = OppoRomType.MTK)
    public interface Mtk_Global {
        public static final String ACM_ENABLED = "acm_enabled";
        public static final String AUTO_TIME_GPS = "auto_time_gps";
        public static final String BLUETOOTH_STATE = "bluetooth_state";
        public static final String CURRENT_NETWORK_CALL = "current_network_call";
        public static final String CURRENT_NETWORK_SMS = "current_network_sms";
        public static final String DATA_ROAMING_2 = "data_roaming_2";
        public static final String DATA_ROAMING_3 = "data_roaming_3";
        public static final String DATA_ROAMING_4 = "data_roaming_4";
        public static final String ENHANCED_4G_MODE_ENABLED_SIM2 = "volte_vt_enabled_sim2";
        public static final String ENHANCED_4G_MODE_ENABLED_SIM3 = "volte_vt_enabled_sim3";
        public static final String ENHANCED_4G_MODE_ENABLED_SIM4 = "volte_vt_enabled_sim4";
        public static final String HIDE_CARRIER_NETWORK_SETTINGS = "hide_carrier_network_settings";
        public static final String IMS_SWITCH = "ims_switch";
        public static final String LTE_ON_CDMA_RAT_MODE = "lte_on_cdma_rat_mode";
        public static final String MSIM_MODE_SETTING = "msim_mode_setting";
        public static final String MULTI_SIM_DEFAULT_DATA_CALL_SUBSCRIPTION = "multi_sim_defaut_data_call";
        public static final String NFC_HCE_ON = "nfc_hce_on";
        public static final String NFC_MULTISE_ACTIVE = "nfc_multise_active";
        public static final String NFC_MULTISE_IN_SWITCHING = "nfc_multise_in_switching";
        public static final String NFC_MULTISE_IN_TRANSACTION = "nfc_multise_in_transation";
        public static final String NFC_MULTISE_LIST = "nfc_multise_list";
        public static final String NFC_MULTISE_ON = "nfc_multise_on";
        public static final String NFC_MULTISE_PREVIOUS = "nfc_multise_previous";
        public static final String NFC_ON = "nfc_on";
        public static final String NFC_RF_FIELD_ACTIVE = "nfc_rf_field_active";
        public static final String NFC_SEAPI_CMCC_SIM = "nfc_seapi_cmcc_sim";
        public static final String NFC_SEAPI_SUPPORT_CMCC = "nfc_seapi_support_cmcc";
        public static final String PREFERRED_NETWORK_MODE_2 = "preferred_network_mode_2";
        public static final String PRIMARY_SIM = "primary_sim";
        public static final String TELEPHONY_MISC_FEATURE_CONFIG = "telephony_misc_feature_config";
        public static final String USER_PREFERRED_NETWORK_MODE = "user_preferred_network_mode";
        public static final String VT_IMS_ENABLED_SIM2 = "vt_ims_enabled_sim2";
        public static final String VT_IMS_ENABLED_SIM3 = "vt_ims_enabled_sim3";
        public static final String VT_IMS_ENABLED_SIM4 = "vt_ims_enabled_sim4";
        public static final String WFC_AID_VALUE = "wfc_aid_value";
        public static final String WFC_IMS_ENABLED_SIM2 = "wfc_ims_enabled_sim2";
        public static final String WFC_IMS_ENABLED_SIM3 = "wfc_ims_enabled_sim3";
        public static final String WFC_IMS_ENABLED_SIM4 = "wfc_ims_enabled_sim4";
        public static final String WFC_IMS_MODE_SIM2 = "wfc_ims_mode_sim2";
        public static final String WFC_IMS_MODE_SIM3 = "wfc_ims_mode_sim3";
        public static final String WFC_IMS_MODE_SIM4 = "wfc_ims_mode_sim4";
        public static final String WFC_IMS_ROAMING_ENABLED_SIM2 = "wfc_ims_roaming_enabled_sim2";
        public static final String WFC_IMS_ROAMING_ENABLED_SIM3 = "wfc_ims_roaming_enabled_sim3";
        public static final String WFC_IMS_ROAMING_ENABLED_SIM4 = "wfc_ims_roaming_enabled_sim4";
        public static final String WIFI_DISPLAY_AUTO_CHANNEL_SELECTION = "wifi_display_auto_channel_selection";
        public static final String WIFI_DISPLAY_CHOSEN_CAPABILITY = "wifi_display_chosen_capability";
        public static final String WIFI_DISPLAY_DISPLAY_NOTIFICATION_TIME = "wifi_display_notification_time";
        public static final String WIFI_DISPLAY_DISPLAY_TOAST_TIME = "wifi_display_display_toast_time";
        public static final String WIFI_DISPLAY_LATENCY_PROFILING = "wifi_display_latency_profiling";
        public static final String WIFI_DISPLAY_PORTRAIT_RESOLUTION = "wifi_display_portrait_resolution";
        public static final String WIFI_DISPLAY_POWER_SAVING_DELAY = "wifi_display_power_saving_delay";
        public static final String WIFI_DISPLAY_POWER_SAVING_OPTION = "wifi_display_power_saving_option";
        public static final String WIFI_DISPLAY_QE_ON = "wifi_display_qe_on";
        public static final String WIFI_DISPLAY_RESOLUTION = "wifi_display_max_resolution";
        public static final String WIFI_DISPLAY_RESOLUTION_DONOT_REMIND = "wifi_display_change_resolution_remind";
        public static final String WIFI_DISPLAY_SECURITY_OPTION = "wifi_display_security_option";
        public static final String WIFI_DISPLAY_SOUND_PATH_DONOT_REMIND = "wifi_display_sound_path_do_not_remind";
        public static final String WIFI_DISPLAY_SQC_INFO_ON = "wifi_display_sqc_info_on";
        public static final String WIFI_DISPLAY_WFD_LATENCY = "wifi_display_wfd_latency";
        public static final String WIFI_DISPLAY_WIFI_INFO = "wifi_display_wifi_info";
        public static final String WORLD_PHONE_AUTO_SELECT_MODE = "world_phone_auto_select_mode";
        public static final String WORLD_PHONE_FDD_MODEM_TIMER = "world_phone_fdd_modem_timer";
    }

    @OppoHook(level = OppoHookType.NEW_FIELD, note = "MTK ADD [Originally defined in Settings.Secure in Settings.java ]", property = OppoRomType.MTK)
    public interface Mtk_Secure {
        public static final String BATTERY_PERCENTAGE = "battery_percentage";
        public static final String DATA_ROAMING_2 = "data_roaming_2";
        public static final String DEFAULT_BROWSER_PACKAGE = "default_browser_package";
        public static final String EPO_AUTO_DOWNLOAD_ON = "epo_auto_download_on";
        public static final String EPO_ENABLED = "epo_enabled";
        public static final String EPO_ROAMING_DOWNLOAD_ON = "epo_roaming_dWownload_on";
        public static final String EPO_SERVER_CODE = "epo_server_code";
        public static final String EPO_SERVER_CODE_DEF = "epo_server_01";
        public static final String EPO_UPDATE_PERIOD = "epo_update_period";
        public static final int EPO_UPDATE_PERIOD_DEF = 4320;
        public static final String INTERFACE_THROTTLE = "interface_throttle_enable";
        public static final String INTERFACE_THROTTLE_RX_VALUE = "interface_throttle_rx_value";
        public static final String INTERFACE_THROTTLE_TX_VALUE = "interface_throttle_tx_value";
        public static final String LAST_PDP_RX_DATA_STATISTIC = "last_pdp_rx_data_statistic";
        public static final String LAST_PDP_TX_DATA_STATISTIC = "last_pdp_tx_data_statistic";
        public static final int MOBILE_DATA_DEFAULT = 0;
        public static final String OVERALL_PROXY_AUTHENED = "overall_proxy_authened";
        public static final String OVERALL_PROXY_ENABLED = "overall_proxy_enabled";
        public static final String OVERALL_PROXY_HOST = "overall_proxy_host";
        public static final String OVERALL_PROXY_PORT = "overall_proxy_port";
        public static final String OVERALL_PROXY_PWD = "overall_proxy_pwd";
        public static final String OVERALL_PROXY_USRNAME = "overall_proxy_usrname";
        public static final String PREFERRED_TTY_MODE_SIM2 = "preferred_tty_mode_sim2";
        public static final String PREFERRED_TTY_MODE_SIM3 = "preferred_tty_mode_sim3";
        public static final String PREFERRED_TTY_MODE_SIM4 = "preferred_tty_mode_sim4";
        public static final String WFD_AUTO_CONNECT_ON = "wfd_auto_connect_on";
        public static final String WIFIP2P_DEV_NAME = "wifip2p_device_name";
        public static final String WIFI_PROXY = "wifi_proxy";
        public static final String WIFI_PROXY_EXCLUDE_LIST = "wifi_proxy_exclude_list";
    }

    /*  JADX ERROR: NullPointerException in pass: ReSugarCode
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.ReSugarCode.initClsEnumMap(ReSugarCode.java:159)
        	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:44)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.util.ArrayList.forEach(ArrayList.java:1251)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    @OppoHook(level = OppoHookType.NEW_FIELD, note = "MTK ADD [Originally defined in Settings.System in Settings.java ]", property = OppoRomType.MTK)
    public interface Mtk_System {
        public static final String ACCELEROMETER_ROTATION_RESTORE = "accelerometer_rotation_restore";
        public static final String ANR_DEBUGGING_MECHANISM = "anr_debugging_mechanism";
        public static final String ANR_DEBUGGING_MECHANISM_STATUS = "anr_debugging_mechanism_status";
        public static final String AUTO_TIME_GPS = "auto_time_gps";
        public static final String BASE_VOICE_WAKEUP_COMMAND_KEY = "voice_wakeup_app";
        public static final String BG_POWER_SAVING_ENABLE = "background_power_saving_enable";
        public static final String BOOT_UP_SELECT_MODE = "boot_up_select_mode";
        public static final int BOOT_UP_SELECT_MODE_DEFAULT = 0;
        public static final String CRO_SETTING = "cro_setting";
        public static final long CRO_SETTING_DISABLE = 0;
        public static final long CRO_SETTING_ENABLE = 1;
        public static final String CT_TIME_DISPLAY_MODE = "ct_time_display_mode";
        public static final String CURRENT_WALLPAPER_NAME = "current_wallpaper_component_name";
        public static final String DATAUSAGE_ON_LOCKSCREEN_SIM1 = "data_usage_on_lockscreen_sim1";
        public static final String DATAUSAGE_ON_LOCKSCREEN_SIM2 = "data_usage_on_lockscreen_sim2";
        public static final long DEFAULT_SIM_NOT_SET = -5;
        public static final int DEFAULT_SIM_NOT_SET_INT = -5;
        public static final long DEFAULT_SIM_SETTING_ALWAYS_ASK = -1;
        public static final Uri DEFAULT_SIP_CALL_URI = null;
        public static final Uri DEFAULT_VIDEO_CALL_URI = null;
        public static final int DIALOG_SEQUENCE_DEFAULT = 0;
        public static final int DIALOG_SEQUENCE_KEYGUARD = 1;
        public static final String DIALOG_SEQUENCE_SETTINGS = "dialog_sequence_settings";
        public static final int DIALOG_SEQUENCE_STK = 2;
        public static final String DM_BOOT_START_ENABLE_KEY = "dm_boot_start_enable_key";
        public static final String DUAL_SIM_MODE_SETTING = "dual_sim_mode_setting";
        public static final int DUAL_SIM_MODE_SETTING_DEFAULT = 3;
        public static final String ENABLE_INTERNET_CALL = "enable_internet_call_value";
        public static final String FONT_SCALE_EXTRALARGE = "settings_fontsize_extralarge";
        public static final String FONT_SCALE_LARGE = "settings_fontsize_large";
        public static final String FONT_SCALE_SMALL = "settings_fontsize_small";
        public static final int GPRS_CONNECTION_MODE_SETTING_DEFAULT = -1;
        public static final String GPRS_CONNECTION_MODE_SIM1 = "gprs_connection_mode_setting_sim1";
        public static final String GPRS_CONNECTION_MODE_SIM2 = "gprs_connection_mode_setting_sim2";
        public static final String GPRS_CONNECTION_SETTING = "gprs_connection_setting";
        public static final int GPRS_CONNECTION_SETTING_DEFAULT = -4;
        public static final String GPRS_CONNECTION_SIM_SETTING = "gprs_connection_sim_setting";
        public static final long GPRS_CONNECTION_SIM_SETTING_NEVER = 0;
        public static final String GPRS_TRANSFER_SETTING = "gprs_transfer_setting";
        public static final int GPRS_TRANSFER_SETTING_DEFAULT = 1;
        public static final String HDMI_AUDIO_OUTPUT_MODE = "hdmi_audio_output_mode";
        public static final String HDMI_CABLE_PLUGGED = "hdmi_cable_plugged";
        public static final String HDMI_COLOR_SPACE = "hdmi_color_space";
        public static final String HDMI_DEEP_COLOR = "hdmi_deep_color";
        public static final String HDMI_ENABLE_STATUS = "hdmi_enable_status";
        public static final String HDMI_VIDEO_RESOLUTION = "hdmi_video_resolution";
        public static final String HDMI_VIDEO_SCALE = "hdmi_video_scale";
        public static final String HOO_SETTING = "hoo_setting";
        public static final long HOO_SETTING_DISABLE = 0;
        public static final long HOO_SETTING_ENABLE = 1;
        public static final String INTER_DIAL_SETTING = "international_dialing_key";
        public static final String IPO_SETTING = "ipo_setting";
        public static final String IVSR_SETTING = "ivsr_setting";
        public static final long IVSR_SETTING_DISABLE = 0;
        public static final long IVSR_SETTING_ENABLE = 1;
        public static final String LANDSCAPE_LAUNCHER = "landscape_launcher";
        public static final String LAST_SIMID_BEFORE_WIFI_DISCONNECTED = "last_simid_before_wifi_disconnected";
        public static final String LOG2SERVER_DIALOG_SHOW = "log2server_dialog_show";
        public static final String MSIM_MODE_SETTING = "msim_mode_setting";
        public static final String MTK_RTSP_MAX_UDP_PORT = "mtk_rtsp_max_udp_port";
        public static final String MTK_RTSP_MIN_UDP_PORT = "mtk_rtsp_min_udp_port";
        public static final String MTK_RTSP_NAME = "mtk_rtsp_name";
        public static final String MTK_RTSP_NETINFO = "mtk_rtsp_netinfo";
        public static final String MTK_RTSP_TO_NAPID = "mtk_rtsp_to_napid";
        public static final String MTK_RTSP_TO_PROXY = "mtk_rtsp_to_proxy";
        public static final String OOBE_DISPLAY = "oobe_display";
        public static final int OOBE_DISPLAY_DEFAULT = 0;
        public static final int OOBE_DISPLAY_ON = 1;
        public static final String PERMISSION_CONTROL_ATTACH = "permission_control_attached";
        public static final String PERMISSION_CONTROL_STATE = "permission_control_state";
        public static final String POWER_OFF_ALARM_PACKAGE_NAME = "power_off_alarm_package_name";
        public static final String ROAMING_INDICATION_NEEDED = "roaming_indication_needed";
        public static final String ROAMING_REMINDER_MODE_SETTING = "roaming_reminder_mode_setting";
        public static final String SCREEN_BRIGHTNESS_ECO_MODE = "screen_brightness_eco_mode";
        public static final int SCREEN_BRIGHTNESS_ECO_MODE_AUTOMATIC = 2;
        public static final String SELECT_WEB_SEARCH_ENGINE = "select_web_search_engine";
        public static final String SHOW_QSG = "show_quick_start_guide";
        public static final String SIM_LOCK_STATE_SETTING = "sim_lock_state_setting";
        public static final int SINGLE_SIM_MODE_SETTING_DEFAULT = 1;
        public static final String SIP_CALL = "sip_call";
        public static final String SMS_SIM_SETTING = "sms_sim_setting";
        public static final long SMS_SIM_SETTING_AUTO = -3;
        public static final String TETHER_IPV6_FEATURE = "tether_ipv6_feature";
        public static final String USB_TETHERING_TYPE = "usb_tethering_type";
        public static final int USB_TETHERING_TYPE_DEFAULT = 0;
        public static final int USB_TETHERING_TYPE_EEM = 1;
        public static final String VIDEO_CALL = "video_call";
        public static final String VIDEO_CALL_SIM_SETTING = "video_call_sim_setting";
        public static final String VOICE_CALL_REJECT_MODE = "voice_call_reject_mode";
        public static final String VOICE_CALL_SIM_SETTING = "voice_call_sim_setting";
        public static final long VOICE_CALL_SIM_SETTING_INTERNET = -2;
        public static final String VOICE_UNLOCK_AND_LAUNCH1 = "voice_unlock_and_launch1";
        public static final String VOICE_UNLOCK_AND_LAUNCH2 = "voice_unlock_and_launch2";
        public static final String VOICE_UNLOCK_AND_LAUNCH3 = "voice_unlock_and_launch3";
        public static final String VOICE_UNLOCK_SCREEN = "voice_unlock_screen";
        public static final String VOICE_WAKEUP_COMMAND_STATUS = "voice_wakeup_command_status";
        public static final String VOICE_WAKEUP_MODE = "voice_wakeup_mode";
        public static final String VOLTE_DMYK_STATE_0 = "volte_dmyk_state_0";
        public static final String VOLTE_DMYK_STATE_1 = "volte_dmyk_state_1";
        @OppoHook(level = OppoHookType.NEW_FIELD, note = "QCOM DEFINED, OMIT ONE", property = OppoRomType.MTK)
        public static final String VOLUME_FM = "volume_fm";
        public static final String VOLUME_MATV = "volume_matv";
        public static final String VT_CALL_REJECT_MODE = "vt_call_reject_mode";
        public static final String WIFI_CONNECT_AP_TYPE = "wifi_ap_connect_type";
        public static final int WIFI_CONNECT_AP_TYPE_AUTO = 0;
        public static final int WIFI_CONNECT_AP_TYPE_MANUL = 1;
        public static final String WIFI_CONNECT_REMINDER = "wifi_connect_reminder";
        public static final String WIFI_CONNECT_TYPE = "wifi_connect_type";
        public static final int WIFI_CONNECT_TYPE_ASK = 2;
        public static final int WIFI_CONNECT_TYPE_AUTO = 0;
        public static final int WIFI_CONNECT_TYPE_MANUL = 1;
        public static final String WIFI_HOTSPOT_AUTO_DISABLE = "wifi_hotspot_auto_disable";
        public static final int WIFI_HOTSPOT_AUTO_DISABLE_FOR_FIVE_MINS = 1;
        public static final int WIFI_HOTSPOT_AUTO_DISABLE_FOR_TEN_MINS = 2;
        public static final int WIFI_HOTSPOT_AUTO_DISABLE_OFF = 0;
        public static final int WIFI_HOTSPOT_DEFAULT_CLIENT_NUM = 8;
        public static final int WIFI_HOTSPOT_DEFAULT_START_TIME = 0;
        public static final String WIFI_HOTSPOT_MAX_CLIENT_NUM = "wifi_hotspot_max_client_num";
        public static final String WIFI_HOTSPOT_START_TIME = "wifi_hotspot_start_time";
        public static final String WIFI_PRIORITY_TYPE = "wifi_priority_type";
        public static final int WIFI_PRIORITY_TYPE_DEFAULT = 0;
        public static final int WIFI_PRIORITY_TYPE_MAMUAL = 1;
        public static final int WIFI_SELECT_SSID_ASK = 2;
        public static final int WIFI_SELECT_SSID_AUTO = 0;
        public static final int WIFI_SELECT_SSID_MANUL = 1;
        public static final String WIFI_SELECT_SSID_TYPE = "wifi_select_ssid_type";

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android.provider.OppoSettings.Mtk_System.<clinit>():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 6 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android.provider.OppoSettings.Mtk_System.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: android.provider.OppoSettings.Mtk_System.<clinit>():void");
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ReSugarCode
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.ReSugarCode.initClsEnumMap(ReSugarCode.java:159)
        	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:44)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.util.ArrayList.forEach(ArrayList.java:1251)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    @OppoHook(level = OppoHookType.NEW_FIELD, note = "OPPO ADD [Originally defined in Settings.System in Settings.java ]", property = OppoRomType.OPPO)
    public interface Oppo_System {
        public static final String AUTO_REDAIL_ENABLED = "auto_redial";
        public static final String AUTO_REDAIL_SETTING = "auto_redail_setting";
        public static final String BOOT_AUTHENTICATION_ENABLE = "boot_authentication_enable";
        public static final String BOOT_AUTHENTICATION_PASSWORD = "boot_authentication_password";
        public static final String BREATHE_LIGHT = "breathe_light";
        public static final String BREATH_LIGHT = "breath_light";
        public static final String BUTTON_LIGHT_MODE = "button_light_mode";
        public static final String BUTTON_LIGHT_TIMEOUT = "button_light_timeout";
        public static final String CALENDAR_REMINDER_SOUND = "calendar_sound";
        public static final Uri CALENDAR_REMINDER_SOUND_URI = null;
        public static final String CALL_VIBRATE_METHOD = "call_vibrate_method";
        public static final String DEFAULT_CALENDAR_REMINDER_SOUND = "calendar_default_sound";
        public static final Uri DEFAULT_NOTIFICATION_SIM2_URI = null;
        public static final Uri DEFAULT_RINGTONE_SIM2_URI = null;
        public static final String DISPLAY_POWER_PERCENT = "display_power_percent";
        public static final String FESTIVAL_WALLPAPER = "festival_wallpaper";
        public static final String FESTIVAL_WALLPAPER_ENABLED = "festival_wallpaper_enabled";
        public static final String FLIP_MUTE = "flip_mute";
        public static final String GPRS_MODE_FIRST = "GPRSmode_fisrt";
        public static final String HOLIDAY_WALLPAPER_ENABLED = "wallpaper_holiday";
        public static final String IME_KEYBOARD_FEEDBACK = "ime_keyboard_feedback";
        public static final String IME_KEYBOARD_SOUND = "ime_keyboard_sound";
        public static final String IS_USING_THEME = "is_using_theme";
        public static final String KEY_NETSTATS_ERROR = "netstats_error";
        public static final String KEY_SYSTEM_APP_WTF = "system_app_wtf";
        public static final String KEY_SYSTEM_SERVER_WTF = "system_server_wtf";
        public static final String KEY_TONE_ENABLE = "oppo_key_tone";
        public static final String KEY_VIBRATE_ENABLE = "oppo_key_vibrate";
        public static final String KE_EVENT_DATA = "event_data";
        public static final String KE_EVENT_LOG = "event_log";
        public static final String LID_CLOSE_SOUND = "lid_close_sound";
        public static final String LID_OPEN_SOUND = "lid_open_sound";
        public static final String LID_SOUNDS_ENABLED = "lid_sounds_enabled";
        public static final String MODE_3G_ON = "3GMode_on";
        public static final String NOTIFICATION_ON_CONNECTED = "notification_on_connected";
        public static final String NOTIFICATION_SOUND_SIM2 = "notification_sim2";
        public static final String OPPO_3G_SWITCH_VISIBLE = "oppo_3g_switch_visible";
        public static final String OPPO_BLANK_SCREEN_CAMERA_ENABLED = "oppo_blank_screen_camera_enabled";
        public static final String OPPO_BLANK_SCREEN_FLASHLIGHT_ENABLED = "oppo_blank_screen_flashlight_enabled";
        public static final String OPPO_BLANK_SCREEN_MULTI_TOUCH_CAMERA_ENABLED = "oppo_blank_screen_multi_touch_camera_enabled";
        public static final String OPPO_BLANK_SCREEN_MUSIC_ENABLED = "oppo_blank_screen_music_enabled";
        public static final String OPPO_BODY_RESPONSE_DAIL = "oppo_body_response_dail";
        public static final String OPPO_BREATH_LED_CHARGE = "oppo_breath_led_charge";
        public static final String OPPO_BREATH_LED_LOW_POWER = "oppo_breath_led_low_power";
        public static final String OPPO_BREATH_LED_NOTIFICATION = "oppo_breath_led_notification";
        public static final String OPPO_DATA_TRAFFIC_USED = "oppo_data_traffic_used";
        public static final String OPPO_DEFAULT_ALARM = "oppo_default_alarm";
        public static final String OPPO_DEFAULT_NOTIFICATION = "oppo_default_notification";
        public static final String OPPO_DEFAULT_NOTIFICATION_SIM2 = "oppo_default_notification_sim2";
        public static final String OPPO_DEFAULT_RINGTONE = "oppo_default_ringtone";
        public static final String OPPO_DEFAULT_RINGTONE_SIM2 = "oppo_default_ringtone_sim2";
        public static final String OPPO_DIRAC_SOUND_EFFECT = "oppo_dirac_sound_effect";
        public static final String OPPO_DISAPLAY_CALCULATE_DATA_TRAFFIC = "oppo_disaplay_calculate_data_traffic";
        public static final String OPPO_DOUBLE_CLICK_CAMERA_ON = "oppo_double_click_camera_on";
        public static final String OPPO_DOUBLE_CLICK_TO_TOP_EFFECT = "oppo_double_click_to_top_effect";
        public static final String OPPO_DOUBLE_FINGER_CONTROL_VOLUME_ENABLED = "oppo_double_finger_control_volume_enabled";
        public static final String OPPO_DOUBLE_PRESS_HOME_LOCK_SCREEN_ENABLED = "oppo_double_press_home_lock_screen";
        public static final String OPPO_EYE_TRACKING_ENABLED = "oppo_eye_tracking_enabled";
        public static final String OPPO_GESTURE_ANSWER = "oppo_smart_apperceive_gesture_answer";
        public static final String OPPO_GESTURE_APP_ENABLE = "oppo_app_gesture_enable";
        public static final String OPPO_GESTURE_PAGE = "oppo_smart_apperceive_gesture_page";
        public static final String OPPO_GESTURE_SCREEN_HOVERING = "oppo_gesture_screen_hovering";
        public static final String OPPO_GESTURE_SLIDING_DESKTOP = "oppo_smart_apperceive_gesture_sliding_desktop";
        public static final String OPPO_GESTURE_TO_TAKE_PHOTO_ENABLED = "oppo_gesture_to_take_photo_enabled";
        public static final String OPPO_GLOVE_MODE_ENABLED = "oppo_glove_mode_enabled";
        public static final String OPPO_LED_COLOR_FOR_FAVORITE_CONTACTS = "oppo_led_color_for_favorite_contacts";
        public static final String OPPO_LED_COLOR_FOR_GENERAL_CONTACTS = "oppo_led_color_for_general_contacts";
        public static final String OPPO_LED_COLOR_FOR_GENERAL_NOTIFICATIONS = "oppo_led_color_for_general_notificaions";
        public static final String OPPO_NFC_LIGHTNING_TRANSFER = "oppo_nfc_lightning_transfer";
        public static final String OPPO_PREVENT_MISOPERATION_ENABLED = "oppo_prevent_misoperation_enabled";
        public static final String OPPO_QUICK_START_ROTATIVE_CAMERA_ENABLED = "oppo_quick_start_rotative_camera_enabled";
        public static final String OPPO_SCREAN_COLOR_DEFINE = "oppo_screan_color_define";
        public static final String OPPO_SMART_APPERCEIVE_ADJUST_SPEAKER = "oppo_smart_apperceive_adjust_speaker";
        public static final String OPPO_SMART_APPERCEIVE_AUTO_ANSWER = "oppo_smart_apperceive_auto_answer";
        public static final String OPPO_SMART_APPERCEIVE_DECREASE_VOLUME = "oppo_smart_apperceive_decrease_volume";
        public static final String OPPO_SMART_APPERCEIVE_DIAL = "oppo_smart_apperceive_dial";
        public static final String OPPO_SMART_APPERCEIVE_ENABLED = "oppo_smart_apperceive_enabled";
        public static final String OPPO_SMART_APPERCEIVE_GESTURE_OP = "oppo_smart_apperceive_gesture_op";
        public static final String OPPO_SMART_APPERCEIVE_IMAGE = "oppo_smart_apperceive_image";
        public static final String OPPO_SMART_APPERCEIVE_NOTIFICATION = "oppo_smart_apperceive_notification";
        public static final String OPPO_SMART_APPERCEIVE_SCREEN_CAPTURE = "oppo_smart_apperceive_screen_capture";
        public static final String OPPO_SMART_APPERCEIVE_SCREEN_LOCK = "oppo_smart_apperceive_screen_lock";
        public static final String OPPO_SMART_APPERCEIVE_SLIENT = "oppo_smart_apperceive_slient";
        public static final String OPPO_SMART_SCREEN_OFF = "oppo_smart_screen_off";
        public static final String OPPO_SMS_NOTIFICATION_SOUND = "oppo_sms_notification_sound";
        public static final String OPPO_STATUS_BAR_ENABLE_WHEN_LOCK = "oppo_status_bar_enable_when_lock";
        public static final String OPPO_THEME_CHANGE = "oppo_theme_change";
        public static final String OPPO_THREE_FINGERS_SWITCH_APP_ENABLED = "oppo_three_fingers_switch_app";
        public static final String OPPO_TOUCHPAD_CTRL_ENABLED = "oppo_touchpad_ctrl_enabled";
        public static final String OPPO_UNLOCK_CHANGE = "oppo_unlock_change";
        public static final String OPPO_UNLOCK_CHANGE_PKG = "oppo_unlock_change_pkg";
        public static final String OPPO_UNLOCK_CHANGE_PROCESS = "oppo_unlock_change_process";
        public static final String OPPO_USE_OPTIMIZED_APP_ICON_ENABLED = "oppo_use_optimized_app_icon_enabled";
        public static final String ORIENTATION_ANIMATION_ENABLED = "orientation_animation";
        public static final String PHONE_IP_PREFIX = "phone_ip_prefix";
        public static final String PHONE_IP_PREFIX_SIM1 = "phone_ip_prefix_sim1";
        public static final String PHONE_IP_PREFIX_SIM2 = "phone_ip_prefix_sim2";
        public static final String POWER_OFF_SOUND = "power_off_sound";
        public static final String POWER_ON_SOUND = "power_on_sound";
        public static final String POWER_ON_TIMES = "power_on_times";
        public static final String POWER_SAVE_MODE = "power_save_method";
        public static final String PRESSKEY_LIGHT_TIMEOUT = "presskey_light_timeout";
        public static final String RINGTONE_SIM2 = "ringtone_sim2";
        public static final String SCREEN_EFFECT = "screen_effect";
        public static final String SHUTDOWN_REQUEST_MISSED = "shutdown_request_missed";
        public static final String STATE_GLOBALEFFECT = "global_effect_state";
        public static final String SYSTEM_APP_STRICTMODE = "system_app_strictmode";
        public static final String TIMEPOWER_CONFIG = "timepower_config";
        public static final String TP_CTRL_CONVENIENT_PAGE_ENABLED = "tp_ctrl_convenient_page_enabled";
        public static final String TP_CTRL_DOUBLE_CLICK_START_APP_ENABLED = "tp_ctrl_double_click_start_app_enabled";
        public static final String TP_CTRL_DOUBLE_CLICK_START_APP_SET = "tp_ctrl_double_click_start_app_set";
        public static final String TP_CTRL_IN_READING_ENABLED = "tp_ctrl_in_reading_enabled";
        public static final String TP_CTRL_IN_RECORDING_ENABLED = "tp_ctrl_in_recording_enabled";
        public static final String TP_CTRL_MUSIC_PAGE_ENABLED = "tp_ctrl_music_page_enabled";
        public static final String TP_CTRL_TOUCH_TO_SLIDE_ENABLED = "tp_ctrl_touch_to_slide_enabled";
        public static final String TURN_SLIENCE_ENABLED = "turn_slience_enabled";
        public static final String USB_NO_ASK_AGAIN = "usb_no_ask_again";
        public static final String USB_REMEBER_SELECTION = "usb_remeber_selection";
        public static final String VISTOR_MODE_PASSWORD = "oppo_vistor_mode_password";
        public static final String VISTOR_MODE_PASSWORD_STATE = "oppo_vistor_mode_password_state";
        public static final String VISTOR_MODE_PATTERN = "oppo_vistor_mode_pattern";
        public static final String VISTOR_MODE_STATE = "oppo_vistor_mode_state";

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android.provider.OppoSettings.Oppo_System.<clinit>():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 6 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android.provider.OppoSettings.Oppo_System.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: android.provider.OppoSettings.Oppo_System.<clinit>():void");
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ReSugarCode
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.ReSugarCode.initClsEnumMap(ReSugarCode.java:159)
        	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:44)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.util.ArrayList.forEach(ArrayList.java:1251)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    @OppoHook(level = OppoHookType.NEW_FIELD, note = "QCOM ADD [Originally defined in Settings.Global in Settings.java ]", property = OppoRomType.QCOM)
    public interface Qcom_Global {
        public static final String ASSISTED_GPS_CONFIGURABLE_LIST = "assisted_gps_configurable_list";
        public static final String ASSISTED_GPS_NETWORK = "assisted_gps_network";
        public static final String ASSISTED_GPS_POSITION_MODE = "assisted_gps_position_mode";
        public static final String ASSISTED_GPS_RESET_TYPE = "assisted_gps_reset_type";
        public static final String ASSISTED_GPS_SUPL_HOST = "assisted_gps_supl_host";
        public static final String ASSISTED_GPS_SUPL_PORT = "assisted_gps_supl_port";
        public static final String MULTI_SIM_DATA_CALL_SUBSCRIPTION = "multi_sim_data_call";
        public static final String MULTI_SIM_PRIORITY_SUBSCRIPTION = "multi_sim_priority";
        public static final String MULTI_SIM_SMS_PROMPT = "multi_sim_sms_prompt";
        public static final String MULTI_SIM_SMS_SUBSCRIPTION = "multi_sim_sms";
        public static final String[] MULTI_SIM_USER_PREFERRED_SUBS = null;
        public static final String MULTI_SIM_VOICE_CALL_SUBSCRIPTION = "multi_sim_voice_call";
        public static final String MULTI_SIM_VOICE_PROMPT = "multi_sim_voice_prompt";
        public static final String TUNE_AWAY_STATUS = "tune_away";

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android.provider.OppoSettings.Qcom_Global.<clinit>():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 6 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android.provider.OppoSettings.Qcom_Global.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: android.provider.OppoSettings.Qcom_Global.<clinit>():void");
        }
    }

    /*  JADX ERROR: NullPointerException in pass: ReSugarCode
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.ReSugarCode.initClsEnumMap(ReSugarCode.java:159)
        	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:44)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.util.ArrayList.forEach(ArrayList.java:1251)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    @OppoHook(level = OppoHookType.NEW_FIELD, note = "QCOM ADD [Originally defined in Settings.System in Settings.java ]", property = OppoRomType.QCOM)
    public interface Qcom_System {
        public static final String ANC = "anc";
        public static final String AUTO_ANSWER_TIMEOUT = "auto_answer";
        public static final String DEFAULT_FILE_MANAGER = "default_file_manager";
        public static final Uri DEFAULT_MMS_NOTIFICATION_URI = null;
        public static final String DEFAULT_SUBSCRIPTION = "default_subscription";
        public static final String DUALMIC = "dualmic";
        public static final String DUMMY_STRING_FOR_PADDING = "";
        public static final String MMS_NOTIFICATION_SOUND = "mms_notification";
        public static final String MULTI_SIM_DATA_CALL_SUBSCRIPTION = "multi_sim_data_call";
        public static final String MULTI_SIM_SMS_SUBSCRIPTION = "multi_sim_sms";
        public static final String MULTI_SIM_VOICE_CALL_SUBSCRIPTION = "multi_sim_voice_call";
        public static final String MULTI_SIM_VOICE_PROMPT = "multi_sim_voice_prompt";
        public static final String SOCKET_DATA_CALL_ENABLE = "socket_data_call_enable";
        public static final String[] USER_PREFERRED_SUBS = null;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android.provider.OppoSettings.Qcom_System.<clinit>():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 6 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android.provider.OppoSettings.Qcom_System.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: android.provider.OppoSettings.Qcom_System.<clinit>():void");
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android.provider.OppoSettings.<init>():void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 5 more
        */
    public OppoSettings() {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android.provider.OppoSettings.<init>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: android.provider.OppoSettings.<init>():void");
    }
}