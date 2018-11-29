package Backend;

import Backend.ResponseFactory.Response;
import Dataset.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.concurrent.Task;

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
    public void ConstructRequest(final HashMap<String, Object> Fields, final API action, final Response callback) {

        Task task = new Task<Void>() {
            @Override
            public Void call() {
                Future jsonResponse = Unirest.post(Connect.StringUrl + action.getAction())
                        .fields(Fields)
                        .asJsonAsync(new Callback<JsonNode>() {
                            public void completed(HttpResponse<JsonNode> httpResponse) {
                                try {

                                    callback.process(httpResponse.getBody());
                                } catch (Exception e) {
                                    callback.Fail("Error handling response");
                                    e.printStackTrace();
                                }
                            }
                            public void failed(UnirestException e) {
                                e.printStackTrace();callback.Fail("Failed to connect");
                            }

                            public void cancelled() {
                                callback.Fail("Request was canceled");
                            }
                        });
                System.out.println(jsonResponse.isDone());
                return null;
            }

        };

        Thread thread = new Thread(task);
        thread.start();
    }


    public void LogOut() {
        User.getInstance().Logout();
        Future jsonResponse = Unirest.post(Connect.StringUrl + API.Logout.getAction()).asJsonAsync();
    }


}
