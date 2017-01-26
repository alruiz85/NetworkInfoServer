package es.alruiz.networkinfoserver.domain.interactor.radio;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

import es.alruiz.networkinfoserver.domain.interactor.listener.OnItemRetrievedListener;
import es.alruiz.networkinfoserver.utils.TextUtils;

/**
 * Created by AlfonsoRuiz on 26/01/2017.
 */

public class RadioInfoInteractorImpl implements RadioInfoInteractor {

    private Context context;

    public RadioInfoInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void execute(OnItemRetrievedListener listener) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String operatorName = telephonyManager.getNetworkOperatorName();
        GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
        if (cellLocation != null) {
            int cellCid = cellLocation.getCid();
            int cellLac = cellLocation.getLac();
        }

        String netWorkCountryISO = telephonyManager.getNetworkCountryIso();
        String networkOperator = telephonyManager.getNetworkOperator();
        int phoneType = telephonyManager.getPhoneType();
        int networkType = telephonyManager.getNetworkType();
        TextUtils.Radio.getPhoneType(networkType);

        String simOperator = telephonyManager.getSimOperator();
        String simOperatorName = telephonyManager.getSimOperatorName();
//        listener.onError("Error");
    }
}
