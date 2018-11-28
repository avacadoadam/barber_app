package Callback;

import Dataset.Appointment;

public interface GetAppointmentsCallback {

    void Success(Appointment[] appointments);
    void Fail(String errorMessage);

}
