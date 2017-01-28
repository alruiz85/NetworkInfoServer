package es.alruiz.networkinfoserver.ui.main;

import android.content.Context;
import android.util.Log;

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

    MainPresenterImpl(MainView view, Context context) {
        this.view = view;
        interactor = new RadioInfoInteractorImpl(context);
    }

    @Override
    public void getPhoneInfo() {
        interactor.execute(new OnItemRetrievedListener() {
            @Override
            public void onSuccess(Object item) {
                telephonyData = (TelephonyData)item;
            }

            @Override
            public void onError(String error) {
                view.showMessage(error);
            }
        });
    }
}
