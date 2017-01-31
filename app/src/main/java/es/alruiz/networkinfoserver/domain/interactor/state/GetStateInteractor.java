package es.alruiz.networkinfoserver.domain.interactor.state;

import es.alruiz.networkinfoserver.domain.interactor.state.listener.StateListener;

/**
 * Created by AlfonsoRuiz on 31/01/2017.
 */

public interface GetStateInteractor {

    void execute(StateListener listener);

}
