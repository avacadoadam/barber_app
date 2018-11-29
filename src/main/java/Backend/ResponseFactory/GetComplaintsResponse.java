package Backend.ResponseFactory;

import Callback.ComplaintsCallback;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;

public class GetComplaintsResponse implements Response {

    private ComplaintsCallback callback;

    public GetComplaintsResponse(ComplaintsCallback callback) {
        this.callback = callback;
    }

    public void process(JsonNode jsonnode) throws Exception {
        JSONArray jsonArray = jsonnode.getArray();
        String[] complaints = new String[jsonArray.length() - 1];

        for (int i = 1; i < jsonArray.length(); i++) {
            complaints[i - 1] = jsonArray.getJSONObject(i).getString("complaint");
        }

        callback.Success(complaints);
    }

    public void Fail(String error) {
        callback.fail(error);
    }
}
