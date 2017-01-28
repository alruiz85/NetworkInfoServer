package es.alruiz.networkinfoserver.domain.interactor.radio;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

import java.util.List;

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

        CellLocation location = telephonyManager.getCellLocation();

        String signalStregth = getSignalStrength(telephonyManager);

        String netWorkCountryISO = telephonyManager.getNetworkCountryIso();
        String networkOperator = telephonyManager.getNetworkOperator();
        int networkType = telephonyManager.getNetworkType();
        TextUtils.Radio.getNetworkType(networkType);

        String simOperator = telephonyManager.getSimOperator();
        String simOperatorName = telephonyManager.getSimOperatorName();
//        listener.onError("Error");
    }

    private String getSignalStrength(TelephonyManager telephonyManager) {
        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo();
        if (cellInfos != null) {
            for (int i = 0; i < cellInfos.size(); i++) {
                if (cellInfos.get(i).isRegistered()) {
                    if (cellInfos.get(i) instanceof CellInfoWcdma) {
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) telephonyManager.getAllCellInfo().get(0);
                        CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();
                        return String.valueOf(cellSignalStrengthWcdma.getDbm());
                    } else if (cellInfos.get(i) instanceof CellInfoGsm) {
                        CellInfoGsm cellInfogsm = (CellInfoGsm) telephonyManager.getAllCellInfo().get(0);
                        CellSignalStrengthGsm cellSignalStrengthGsm = cellInfogsm.getCellSignalStrength();
                        return String.valueOf(cellSignalStrengthGsm.getDbm());
                    } else if (cellInfos.get(i) instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) telephonyManager.getAllCellInfo().get(0);
                        CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
                        return String.valueOf(cellSignalStrengthLte.getDbm());
                    } else if (cellInfos.get(i) instanceof CellInfoCdma) {
                        CellInfoCdma cellInfoCdma = (CellInfoCdma) telephonyManager.getAllCellInfo().get(0);
                        CellSignalStrengthCdma cellSignalStrengthCdma = cellInfoCdma.getCellSignalStrength();
                        return String.valueOf(cellSignalStrengthCdma.getDbm());
                    }
                }
            }
        }
        return null;
    }


}
