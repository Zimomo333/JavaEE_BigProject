package StaffManager;

//职工类
public class Staff {
    private String account;     //账  号
    private int workNum;        //工  号
    private String name;        //姓  名
    private String sex;         //性  别
    private String phone;       //手机号
    private String address;     //地  址
    private String IDcard;      //身份证
    private String password;    //密  码
    private int power;          //权  限
    private String img;         //图  像

    public Staff(){

    }

    public Staff(String account, int workNum, String name, String sex, String phone, String address, String IDcard, String password, int power, String img){
        this.account = account;
        this.workNum = workNum;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.IDcard = IDcard;
        this.password = password;
        this.power = power;
        this.img = img;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getWorkNum() {
        return workNum;
    }

    public void setWorkNum(int workNum) {
        this.workNum = workNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
