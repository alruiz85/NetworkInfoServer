package es.alruiz.networkinfoserver.ui.main;

import android.os.AsyncTask;
import android.telephony.ServiceState;

import com.google.gson.Gson;

import es.alruiz.networkinfoserver.R;
import es.alruiz.networkinfoserver.domain.interactor.listener.OnItemRetrievedListener;
import es.alruiz.networkinfoserver.domain.interactor.radio.RadioInfoInteractor;
import es.alruiz.networkinfoserver.domain.interactor.radio.RadioInfoInteractorImpl;
import es.alruiz.networkinfoserver.domain.interactor.state.GetStateInteractor;
import es.alruiz.networkinfoserver.domain.interactor.state.GetStateInteractorImpl;
import es.alruiz.networkinfoserver.domain.interactor.state.listener.StateListener;
import es.alruiz.networkinfoserver.model.TelephonyData;
import es.alruiz.networkinfoserver.network.Server;
import es.alruiz.networkinfoserver.utils.ip.IPUtilities;
import es.alruiz.networkinfoserver.utils.text.TextUtils;

/**
 * Created by AlfonsoRuiz on 26/01/2017.
 */

class MainPresenterImpl implements MainPresenter {

    private RadioInfoInteractor radioInfoInteractor;
    private GetStateInteractor getStateInteractor;
    private MainView view;
    private TelephonyData telephonyData;
    private String telephonyDataJson;
    private MainActivity activity;
    private Server server;

    MainPresenterImpl(MainView view, MainActivity activity) {
        this.view = view;
        this.activity = activity;
        radioInfoInteractor = new RadioInfoInteractorImpl(activity);
        getStateInteractor = new GetStateInteractorImpl(activity);
    }

    @Override
    public void getPhoneInfo() {
        radioInfoInteractor.execute(new OnItemRetrievedListener() {
            @Override
            public void onSuccess(Object item) {
                telephonyData = (TelephonyData) item;
                if (telephonyData != null) {
                    view.showMessage(activity.getApplicationContext().getResources().getString(R.string.phone_data_retrieved_ok));
                    Gson gson = new Gson();
                    telephonyDataJson = gson.toJson(telephonyData);
                    startServer();

                }
            }

            @Override
            public void onError(String error) {
                view.showMessage(error);
            }
        });
    }

    @Override
    public void getPhoneInfoEmergencyMode() {

    }

    @Override
    public void getState() {
        getStateInteractor.execute(new StateListener(), new OnItemRetrievedListener() {
            @Override
            public void onSuccess(Object item) {
                view.showMessage(TextUtils.Radio.getServiceState((Integer) item));
                getPhoneInfo();
                if ((Integer) item == ServiceState.STATE_IN_SERVICE) {
                    getPhoneInfo();
                } else if ((Integer) item == ServiceState.STATE_EMERGENCY_ONLY) {
                    getPhoneInfoEmergencyMode();
                }
            }

            @Override
            public void onError(String error) {
                view.showMessage(error);
            }
        });
    }

    @Override
    public void getIPs() {
        view.showMessage("Internal IP: " + IPUtilities.getInternalIp());
        new IpPublicTask().execute();
    }

    @Override
    public void startServer() {
        server = new Server(activity, telephonyDataJson);
        view.showMessage("Server running...");
    }

    @Override
    public void stopServer() {
        server.stopServer();
    }

    private class IpPublicTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                return IPUtilities.getPublicIp();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                view.showMessage("Public IP: " + result);
            }
        }
    }

}
