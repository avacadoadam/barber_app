package Callback;


import Backend.API;

/**
 * A interface so that a reponse can be given back to the UI
 */
public interface ControllerCallback {

    void Succes(API action);

    void Fail(String error);

}

