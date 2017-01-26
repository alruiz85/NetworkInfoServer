package es.alruiz.networkinfoserver.domain.interactor.listener;

/**
 * Created by AlfonsoRuiz on 26/01/2017.
 */

public interface OnItemRetrievedListener<T> {

    void onSuccess(T item);

    void onError(String error);

}
