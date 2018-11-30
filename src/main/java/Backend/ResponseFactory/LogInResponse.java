package Backend.ResponseFactory;

import Backend.API;
import Callback.FormCallback;
import Dataset.User;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;

public class LogInResponse implements Response {

    private FormCallback callback;

    public LogInResponse(FormCallback callback) {
        this.callback = callback;
    }

    public void process(JsonNode httpResponse) throws Exception {
        JSONObject obj = httpResponse.getObject();
        System.out.println("asdfasdf");
        if (obj.getBoolean("success")) {
            if (!obj.getString("type").equals("Admin")) {
                User.CreateInstance(obj.getString("lname"), obj.getString("fname"),
                        obj.getString("email"), obj.getInt("id"), obj.getString("type"),
                        obj.getInt("rating"));
                callback.Succes(API.LogIn);
            } else {
                User.CreateInstance(obj.getString("lname"), obj.getString("fname"),
                        obj.getString("email"), obj.getInt("id"), obj.getString("type"),
                        0);
                callback.Succes(API.LogIn);
            }
        } else {
            callback.Fail(obj.getString("error"));
        }
    }

    public void Fail(String error) {
        callback.Fail(error);
    }
}
