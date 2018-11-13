package Backend;

import org.json.JSONObject;

public class ReponseHandler {

    public response HandleResponse(String response) throws Exception {
        JSONObject json = new JSONObject(response);
        if (json.getBoolean("success")) {
            return new response(true);
        } else {
            return new response(false, json.getString("error"));
        }
    }

}

/*
Simple class to represent If a response
 */
class response {

    private boolean success;
    private String errorMessage;


    public response(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public response(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

