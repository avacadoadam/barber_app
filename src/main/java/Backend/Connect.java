package Backend;

import UI.ControllerCallback;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.concurrent.Task;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.Future;

public class Connect {

    private static Connect single_instance = null;
    private static String StringUrl = "http://api/";

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
                        .queryString(Fields)
                        .asJsonAsync(new Callback<JsonNode>() {
                            public void completed(HttpResponse<JsonNode> httpResponse) {
                                try {
                                    JSONObject json = httpResponse.getBody().getObject();
                                    if (json.getBoolean("success")) {
                                        callback.Succes(action);
                                    } else {
                                        callback.Fail(json.getString("error"));
                                    }
                                }catch (Exception e){
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


}
