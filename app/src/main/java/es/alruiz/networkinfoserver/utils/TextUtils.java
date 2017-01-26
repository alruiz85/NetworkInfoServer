package es.alruiz.networkinfoserver.utils;

/**
 * Created by AlfonsoRuiz on 26/01/2017.
 */

public class TextUtils {

    public static class Radio {

        static final String PHONE_TYPE_NONE = "PHONE_TYPE_NONE";
        static final String PHONE_TYPE_GSM = "PHONE_TYPE_GSM";
        static final String PHONE_TYPE_CDMA = "PHONE_TYPE_CDMA";
        static final String PHONE_TYPE_SIP = "PHONE_TYPE_SIP";

        public static String getPhoneType(int networkType) {
            switch (networkType) {
                case 0:
                    return PHONE_TYPE_NONE;
                case 1:
                    return PHONE_TYPE_GSM;
                case 2:
                    return PHONE_TYPE_CDMA;
                case 14:
                    return PHONE_TYPE_SIP;
            }
            return null;
        }

    }

}
