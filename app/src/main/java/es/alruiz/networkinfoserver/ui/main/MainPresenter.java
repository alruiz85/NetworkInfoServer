package es.alruiz.networkinfoserver.ui.main;

/**
 * Created by AlfonsoRuiz on 26/01/2017.
 */

public interface MainPresenter {

    void getPhoneInfo();

    void getPhoneInfoEmergencyMode();

    void getState();

    void getIPs();

    void startServer();

    void stopServer();

}
