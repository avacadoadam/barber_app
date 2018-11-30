package Backend.ResponseFactory;

import com.mashape.unirest.http.JsonNode;

/**
 * A Command Pattern to all Differing Response and callbacks perimeters to use one common interface and Http function
 */
public interface Response {
        //Process The response for the server which will be json
        void process(JsonNode httpResponse) throws Exception;
        //Called whenever a error occurs in the request lifecycle
        void Fail(String error);


}
