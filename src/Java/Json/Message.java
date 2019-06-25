package Json;
import javax.json.bind.annotation.JsonbProperty;

@SuppressWarnings("unused")
public class Message {

    // 为了使用jsonb,必须有一个空的默认构造方法
    public Message() {

    }

    public Message(String error, String message, String openid, String access_token) {
        this.error = error;
        this.message = message;
        this.openid = openid;
        this.access_token = access_token;
    }


    // error
    @JsonbProperty("error")
    private String error;

    // message
    @JsonbProperty("message")
    private String message;

    // openid
    @JsonbProperty("openid")
    private String openid;

    // access_token
    @JsonbProperty("access_token")
    private String access_token;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}