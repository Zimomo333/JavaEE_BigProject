package Json;

import javax.json.bind.annotation.JsonbProperty;

@SuppressWarnings("unused")
public class User {
    // 学号
    @JsonbProperty("username")
    private String username;

    // 姓名
    @JsonbProperty("name")
    private String name;

    // 类型
    @JsonbProperty("group")
    private String group;

    // 学院
    @JsonbProperty("faculty_title")
    private String faculty_title;

    // wx_openid
    @JsonbProperty("wx_openid")
    private String wx_openid;

    // openid
    @JsonbProperty("openid")
    private String openid;

    // faculty
    @JsonbProperty("faculty")
    private String faculty;

    // error
    @JsonbProperty("error")
    private String error;

    // message
    @JsonbProperty("message")
    private String message;

    public User(){

    }

    public User(String username, String name, String group, String faculty_title, String wx_openid, String openid, String faculty, String error, String message) {
        this.username = username;
        this.name = name;
        this.group = group;
        this.faculty_title = faculty_title;
        this.wx_openid = wx_openid;
        this.openid = openid;
        this.faculty = faculty;
        this.error = error;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFaculty_title() {
        return faculty_title;
    }

    public void setFaculty_title(String faculty_title) {
        this.faculty_title = faculty_title;
    }

    public String getWx_openid() {
        return wx_openid;
    }

    public void setWx_openid(String wx_openid) {
        this.wx_openid = wx_openid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

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
}