package Backend.ResponseFactory;

import Backend.API;
import Callback.FormCallback;
import com.mashape.unirest.http.JsonNode;

public class RegisterResponse implements Response {

    private FormCallback callback;

    public RegisterResponse(FormCallback callback) {
        this.callback = callback;
    }

    public void process(JsonNode httpResponse) throws Exception {
        if (httpResponse.getObject().getBoolean("success")) {
            callback.Succes(API.Register);
        } else {
            callback.Fail("Error Handling Response");
        }
    }

    public void Fail(String error) {
        callback.Fail(error);
    }
}
