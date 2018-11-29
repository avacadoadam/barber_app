package Backend.ResponseFactory;

import Callback.GetAppointmentsCallback;
import Dataset.Appointment;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;

public class AppointmentResponse implements Response {


    private GetAppointmentsCallback callback;

    public AppointmentResponse(GetAppointmentsCallback callback) {
        this.callback = callback;
    }

    public void process(JsonNode jsonNode) throws Exception {
        JSONObject success = null;
        JSONArray arr = null;
        try {
            success = (JSONObject) jsonNode.getArray().get(0);
        } catch (Exception e) {
            success = jsonNode.getObject();
            e.printStackTrace();
        }
        if (success.getBoolean("success")) {
            arr = jsonNode.getArray();
            Appointment[] appointments = new Appointment[arr.length() - 1];
            for (int i = 1; i < arr.length(); i++) {
                JSONObject a = arr.getJSONObject(i);
                try {
                    appointments[i - 1] = new Appointment(a.getInt("id"), a.getString("Barbershop")
                            , a.getString("Time"), a.getString("CustomerName"), a.getString("BarberName"), a.getString("Date"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            callback.Success(appointments);
        } else {
            callback.Fail(success.getString("error"));
        }
    }

    public void Fail(String error) {
        callback.Fail(error);
    }
}
