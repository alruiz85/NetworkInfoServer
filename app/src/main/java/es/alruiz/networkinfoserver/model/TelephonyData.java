package es.alruiz.networkinfoserver.model;

import android.telephony.CellLocation;

/**
 * Created by Alfonso on 28/01/2017.
 */

public class TelephonyData {

    private String operatorName;
    private CellLocation cellLocation;
    private String signalStregth;
    private String netWorkCountryISO;
    private String networkOperator;
    private String networkType;
    private String simOperator;
    private String simOperatorName;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public CellLocation getCellLocation() {
        return cellLocation;
    }

    public void setCellLocation(CellLocation cellLocation) {
        this.cellLocation = cellLocation;
    }

    public String getSignalStregth() {
        return signalStregth;
    }

    public void setSignalStregth(String signalStregth) {
        this.signalStregth = signalStregth;
    }

    public String getNetWorkCountryISO() {
        return netWorkCountryISO;
    }

    public void setNetWorkCountryISO(String netWorkCountryISO) {
        this.netWorkCountryISO = netWorkCountryISO;
    }

    public String getNetworkOperator() {
        return networkOperator;
    }

    public void setNetworkOperator(String networkOperator) {
        this.networkOperator = networkOperator;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getSimOperator() {
        return simOperator;
    }

    public void setSimOperator(String simOperator) {
        this.simOperator = simOperator;
    }

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }
}
