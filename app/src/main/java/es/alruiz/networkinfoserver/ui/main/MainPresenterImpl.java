package es.alruiz.networkinfoserver.ui.main;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONObject;

import es.alruiz.networkinfoserver.R;
import es.alruiz.networkinfoserver.domain.interactor.listener.OnItemRetrievedListener;
import es.alruiz.networkinfoserver.domain.interactor.radio.RadioInfoInteractor;
import es.alruiz.networkinfoserver.domain.interactor.radio.RadioInfoInteractorImpl;
import es.alruiz.networkinfoserver.model.TelephonyData;
import es.alruiz.networkinfoserver.network.Server;
import es.alruiz.networkinfoserver.utils.ip.IPUtilities;
import timber.log.Timber;

/**
 * Created by AlfonsoRuiz on 26/01/2017.
 */

class MainPresenterImpl implements MainPresenter {

    private RadioInfoInteractor interactor;
    private MainView view;
    private TelephonyData telephonyData;
    private Context context;
    private MainActivity activity;
    private Server server;

    MainPresenterImpl(MainView view, Context context, MainActivity activity) {
        this.view = view;
        this.context = context;
        this.activity = activity;
        interactor = new RadioInfoInteractorImpl(context);
    }

    @Override
    public void getPhoneInfo() {
        interactor.execute(new OnItemRetrievedListener() {
            @Override
            public void onSuccess(Object item) {
                telephonyData = (TelephonyData) item;
                if (telephonyData != null) {
                    view.showMessage(context.getResources().getString(R.string.phone_data_retrieved_ok));
                    Gson gson = new Gson();
                    String json = gson.toJson(telephonyData);
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
        server = new Server(activity);
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
