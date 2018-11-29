package Backend.ResponseFactory;

import Callback.UnapprovedBarbers;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;

public class UnApprovedBarbers implements Response {


    private UnapprovedBarbers callback;

    public UnApprovedBarbers(UnapprovedBarbers callback) {
        this.callback = callback;
    }

    public void process(JsonNode httpResponse) throws Exception {
        JSONArray jsonArray = httpResponse.getArray();
        String[] BarbersName = new String[jsonArray.length() - 1];
        for (int i = 1; i < jsonArray.length(); i++) {
            BarbersName[i - 1] = jsonArray.getJSONObject(i).getString("fname") + jsonArray.getJSONObject(i).getString("lname");
        }
        callback.Success(BarbersName);
    }

    public void Fail(String error) {
        callback.Fail(error);
    }
}
