package Backend;

import Callback.*;
import Dataset.Appointment;
import Dataset.ListBarber;
import Dataset.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.concurrent.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.Future;

public class Connect {

    private static Connect single_instance = null;
    private static String StringUrl = "http://api/api";

    private Connect() {
    }

    public static Connect getInstance() {
        if (single_instance == null)
            single_instance = new Connect();
        return single_instance;
    }

    /**
     * Constructs a http request
     *
     * @param Fields
     * @param action
     * @param callback
     */
    public void ConstructRequest(final HashMap<String, Object> Fields, final API action, final ControllerCallback callback) {

        Task task = new Task<Void>() {
            @Override
            public Void call() {
                Future jsonResponse = Unirest.post(Connect.StringUrl + action.getAction())
                        .fields(Fields)
                        .asJsonAsync(new Callback<JsonNode>() {
                            public void completed(HttpResponse<JsonNode> httpResponse) {
                                try {
                                    JSONObject json = httpResponse.getBody().getObject();
                                    if (json.getBoolean("success")) {

                                        if (action == API.LogIn) {
                                            LogInSuccess(json);
                                        }
                                        callback.Succes(action);
                                    } else {
                                        callback.Fail(json.getString("error"));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    callback.Fail("Failed Response");
                                }
                            }

                            public void failed(UnirestException e) {
                                callback.Fail("Failed to connect");
                            }

                            public void cancelled() {
                                callback.Fail("Request was canceled");
                            }
                        });
                return null;
            }

        };

        new Thread(task).start();
    }

    public void GetAppointments(final HashMap<String, Object> Fields, final GetAppointmentsCallback callback) {

        Task task = new Task<Void>() {
            @Override
            public Void call() {
                Future jsonResponse = Unirest.post(Connect.StringUrl + API.GetAppointments.getAction())
                        .fields(Fields)
                        .asJsonAsync(new Callback<JsonNode>() {
                            public void completed(HttpResponse<JsonNode> httpResponse) {
                                JsonNode jsonNode = httpResponse.getBody();
                                JSONObject success = null;
                                JSONArray arr = null;
                                try {
                                    success = (JSONObject) jsonNode.getArray().get(0);
                                } catch (Exception e) {
                                    success = httpResponse.getBody().getObject();
                                    e.printStackTrace();
                                }
                                if (success.getBoolean("success")) {
                                    arr = httpResponse.getBody().getArray();
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

                            public void failed(UnirestException e) {
                                callback.Fail("Failed to connect");
                            }

                            public void cancelled() {
                                callback.Fail("Request was canceled");
                            }
                        });
                return null;
            }

        };

        new Thread(task).start();
    }

    public void GetBarbers(final HashMap<String, Object> Fields, final ListBarbersController callback) {
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                System.out.println("Thread Called1");
                Future jsonResponse = Unirest.post(Connect.StringUrl + API.GetAppointments.getAction())
                        .fields(Fields)
                        .asJsonAsync(new Callback<JsonNode>() {
                            public void completed(HttpResponse<JsonNode> httpResponse) {
                                JsonNode jsonNode = httpResponse.getBody();
                                JSONObject success = null;
                                JSONArray arr = null;
                                try {
                                    success = (JSONObject) jsonNode.getArray().get(0);
                                } catch (Exception e) {
                                    success = httpResponse.getBody().getObject();
                                    e.printStackTrace();
                                }
                                if (success.getBoolean("success")) {
                                    arr = httpResponse.getBody().getArray();
                                    ListBarber[] barbers = new ListBarber[arr.length() - 1];
                                    for (int i = 1; i < arr.length(); i++) {
                                        JSONObject a = arr.getJSONObject(i);
                                        try {
                                            barbers[i - 1] = new ListBarber(a.getString("BarberName"),
                                                    a.getInt("BarberID"), a.getInt("rating"));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    callback.Success(barbers);
                                } else {
                                    callback.Fail(success.getString("error"));
                                }
                            }

                            public void failed(UnirestException e) {
                                callback.Fail("Failed to connect");
                            }

                            public void cancelled() {
                                callback.Fail("Request was canceled");
                            }
                        });
                return null;
            }

        };

        new Thread(task).start();
    }

    public void GetComplaints(final HashMap<String, Object> Fields, final ComplaintsCallback callback) {
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                Future jsonResponse = Unirest.post(Connect.StringUrl + API.Admin.getAction())
                        .fields(Fields)
                        .asJsonAsync(new Callback<JsonNode>() {
                            public void completed(HttpResponse<JsonNode> httpResponse) {
                                JSONArray jsonArray = httpResponse.getBody().getArray();
                                String[] complaints = new String[jsonArray.length() - 1];

                                for (int i = 1; i < jsonArray.length(); i++) {
                                    complaints[i - 1] = jsonArray.getJSONObject(i).getString("complaint");
                                }

                                callback.Success(complaints);
                            }

                            public void failed(UnirestException e) {
                                callback.fail("Failed to connect");
                            }

                            public void cancelled() {
                                callback.fail("Request was canceled");
                            }
                        });
                return null;
            }

        };

        new Thread(task).start();
    }

    public void GetUnApprovedBarbers( final UnapprovedBarbers callback) {
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                Future jsonResponse = Unirest.post(Connect.StringUrl + API.Admin.getAction())
                        .field("action","BarbersWaitingApproval")
                        .asJsonAsync(new Callback<JsonNode>() {
                            public void completed(HttpResponse<JsonNode> httpResponse) {
                                JSONArray jsonArray = httpResponse.getBody().getArray();
                                String[] BarbersName = new String[jsonArray.length() - 1];
                                for (int i = 1; i < jsonArray.length(); i++) {
                                    BarbersName[i - 1] = jsonArray.getJSONObject(i).getString("fname") + jsonArray.getJSONObject(i).getString("lname");
                                }
                                callback.Success(BarbersName);
                            }

                            public void failed(UnirestException e) {
                                callback.Fail("Failed to connect");
                            }

                            public void cancelled() {
                                callback.Fail("Request was canceled");
                            }
                        });
                return null;
            }

        };

        new Thread(task).start();
    }

    public void AdminGetAllAppointments(final GetAppointmentsCallback callback){
         Task task = new Task<Void>() {
            @Override
            public Void call() {
                Future jsonResponse = Unirest.post(Connect.StringUrl + API.Admin.getAction())
                        .field("action","GetAppointments")
                        .asJsonAsync(new Callback<JsonNode>() {
                            public void completed(HttpResponse<JsonNode> httpResponse) {
                                JsonNode jsonNode = httpResponse.getBody();
                                JSONObject success = null;
                                JSONArray arr = null;
                                try {
                                    success = (JSONObject) jsonNode.getArray().get(0);
                                } catch (Exception e) {
                                    success = httpResponse.getBody().getObject();
                                    e.printStackTrace();
                                }
                                if (success.getBoolean("success")) {
                                    arr = httpResponse.getBody().getArray();
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

                            public void failed(UnirestException e) {
                                callback.Fail("Failed to connect");
                            }

                            public void cancelled() {
                                callback.Fail("Request was canceled");
                            }
                        });
                return null;
            }

        };

        new Thread(task).start();
    }


    public void LogOut() {
        Future jsonResponse = Unirest.post(Connect.StringUrl + API.Logout.getAction()).asJsonAsync();
    }

    //Adds user appointments given from Login
    private void LogInSuccess(JSONObject obj) {
        if (!obj.getString("type").equals("Admin")) {
            User.CreateInstance(obj.getString("lname"), obj.getString("fname"),
                    obj.getString("email"), obj.getInt("id"), obj.getString("type"),
                    obj.getInt("rating"));
        } else {
            User.CreateInstance(obj.getString("lname"), obj.getString("fname"),
                    obj.getString("email"), obj.getInt("id"), obj.getString("type"),
                    0);
        }
    }


}
