package es.alruiz.networkinfoserver.utils.text;

import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

/**
 * Created by AlfonsoRuiz on 26/01/2017.
 */

public class TextUtils {

    public static class Radio {

        public static String getNetworkType(int networkType) {
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return "2G";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return "4G";
                default:
                    return "UNKNOWN";
            }
        }

        public static String getServiceState(int state) {
            switch (state) {
                case ServiceState.STATE_IN_SERVICE:
                    return "STATE_IN_SERVICE";
                case ServiceState.STATE_EMERGENCY_ONLY:
                    return "STATE_EMERGENCY_ONLY";
                case ServiceState.STATE_OUT_OF_SERVICE:
                    return "STATE_OUT_OF_SERVICE";
                case ServiceState.STATE_POWER_OFF:
                    return "STATE_POWER_OFF";
                default:
                    return "UNKNOWN";
            }
        }

    }

}
