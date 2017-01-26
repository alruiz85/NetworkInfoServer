package es.alruiz.networkinfoserver.domain.interactor.radio;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

import java.util.List;

import es.alruiz.networkinfoserver.domain.interactor.listener.OnItemRetrievedListener;

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
        GsmCellLocation cellLocation = (GsmCellLocation)telephonyManager.getCellLocation();
        if(cellLocation !=null){
            int cellCid= cellLocation.getCid();
            int cellLac = cellLocation.getLac();
        }

        String netWorkCountryISO = telephonyManager.getNetworkCountryIso();
        String networkOperator = telephonyManager.getNetworkOperator();
        int networkType = telephonyManager.getNetworkType();
        String simOperator = telephonyManager.getSimOperator();
        String simOperatorName = telephonyManager.getSimOperatorName();
//        listener.onError("Error");
    }
}
