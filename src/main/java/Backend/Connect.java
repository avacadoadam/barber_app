package Backend;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class Connect {
    public static HttpRequestWithBody jsonResponse;

    public Connect() {
        jsonResponse = Unirest.post("http://httpbin.org/post");

    }


}
