package Callback;

import Dataset.Appointment;

public interface GetAppointmentsCallback {

    void Success(Appointment[] appointment);

    void Fail(String errorMessage);

}
