package Backend.ResponseFactory;

import Callback.FormCallback;
import com.mashape.unirest.http.JsonNode;

public class GenericResponse implements Response {
    private FormCallback callback;

    public GenericResponse(FormCallback callback) {
        this.callback = callback;
    }

    public void process(JsonNode httpResponse) throws Exception {
        if (httpResponse.getObject().getBoolean("success")) {
            callback.Succes(null);
        } else {
            callback.Fail(httpResponse.getObject().getString("error"));
        }
    }

    public void Fail(String error) {
        callback.Fail(error);
    }
}
