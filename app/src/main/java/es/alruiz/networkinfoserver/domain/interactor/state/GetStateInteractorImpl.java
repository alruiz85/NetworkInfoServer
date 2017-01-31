package es.alruiz.networkinfoserver.domain.interactor.state;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

import es.alruiz.networkinfoserver.domain.interactor.state.listener.StateListener;

/**
 * Created by AlfonsoRuiz on 31/01/2017.
 */

public class GetStateInteractorImpl implements GetStateInteractor {

    private Context context;

    public GetStateInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void execute(StateListener listener) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        listener = new StateListener() {
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
        };
        telephonyManager.listen(listener, PhoneStateListener.LISTEN_SERVICE_STATE);
    }
}
