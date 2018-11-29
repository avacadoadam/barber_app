package Backend.ResponseFactory;

import Callback.ListBarbersController;
import Dataset.ListBarber;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetBarbersResponse implements Response {

    private ListBarbersController callback;

    public GetBarbersResponse(ListBarbersController callback) {
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

    public void Fail(String error) {
        callback.Fail(error);
    }
}
