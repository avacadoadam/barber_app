package Backend.ResponseFactory;

import com.mashape.unirest.http.JsonNode;

/**
 * A Command Pattern to all Differing Response and callbacks perimeters to use one common interface and Http function
 */
public interface Response {

        void process(JsonNode httpResponse) throws Exception;
        void Fail(String error);


}
