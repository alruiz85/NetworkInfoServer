package es.alruiz.networkinfoserver.domain.interactor.state.listener;

import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;

/**
 * Created by AlfonsoRuiz on 31/01/2017.
 */

public class StateListener extends PhoneStateListener {

    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        switch (serviceState.getState()) {
            case ServiceState.STATE_IN_SERVICE:
                serviceState.setState(ServiceState.STATE_IN_SERVICE);
                break;
            case ServiceState.STATE_OUT_OF_SERVICE:
                serviceState.setState(ServiceState.STATE_OUT_OF_SERVICE);
                break;
            case ServiceState.STATE_EMERGENCY_ONLY:
                serviceState.setState(ServiceState.STATE_EMERGENCY_ONLY);
                break;
            case ServiceState.STATE_POWER_OFF:
                serviceState.setState(ServiceState.STATE_POWER_OFF);
                break;
            default:
                break;
        }
    }

}
