package es.alruiz.networkinfoserver.ui.main;

import android.content.Context;

import es.alruiz.networkinfoserver.R;
import es.alruiz.networkinfoserver.domain.interactor.listener.OnItemRetrievedListener;
import es.alruiz.networkinfoserver.domain.interactor.radio.RadioInfoInteractor;
import es.alruiz.networkinfoserver.domain.interactor.radio.RadioInfoInteractorImpl;
import es.alruiz.networkinfoserver.model.TelephonyData;

/**
 * Created by AlfonsoRuiz on 26/01/2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private RadioInfoInteractor interactor;
    private MainView view;
    private TelephonyData telephonyData;
    private Context context;

    MainPresenterImpl(MainView view, Context context) {
        this.view = view;
        this.context = context;
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
                }
            }

            @Override
            public void onError(String error) {
                view.showMessage(error);
            }
        });
    }
}
