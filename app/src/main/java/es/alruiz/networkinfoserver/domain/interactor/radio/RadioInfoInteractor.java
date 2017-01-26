package es.alruiz.networkinfoserver.domain.interactor.radio;

import es.alruiz.networkinfoserver.domain.interactor.listener.OnItemRetrievedListener;

/**
 * Created by AlfonsoRuiz on 26/01/2017.
 */

public interface RadioInfoInteractor {

    void execute(OnItemRetrievedListener listener);

}
