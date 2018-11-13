package Backend;

import UI.Callback;
import com.mashape.unirest.http.Unirest;

public class Form extends Connect {

    private Type type;

    public Form(Type type) {
        this.type = type;
    }
    /*
    Must encrpty password
    Send back to callback
     */
    public void Login(String email, String passwd, Callback callback) {
        String hash = passwd;
        jsonResponse = Unirest.post(this.StringUrl + API.LogIn.getType())
          .header("accept", "application/json")
                .queryString("apiKey", "123")
                .field("email", email)
                .field("passwd", hash)
                .field("type", this.type.getType())
                .asJson();
    }


    /*
HttpResponse<JsonNode> jsonResponse = Unirest.post("http://httpbin.org/post")
  .header("accept", "application/json")
  .queryString("apiKey", "123")
  .field("parameter", "value")
  .field("foo", "bar")
  .asJson();
 */
}
