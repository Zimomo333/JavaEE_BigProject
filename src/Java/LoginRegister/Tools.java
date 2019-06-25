package LoginRegister;

import UserManager.Customer;
import Utils.C3P0Utils;
import Utils.DBUtil_BO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tools{

    private String account;
    private String password;

    public Tools(){

    }

    public Tools(String account, String password){
        this.account = account;
        this.password = password;
    }

    public List GetSingleRs() {

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Customer where account =" + "'" + account + "'";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Customer customer = new Customer();
                customer.setAccount(dbUtil_bo.rs.getString("account"));
                customer.setID(dbUtil_bo.rs.getInt("ID"));
                customer.setPhone(dbUtil_bo.rs.getString("phone"));
                customer.setName(dbUtil_bo.rs.getString("name"));
                customer.setSex(dbUtil_bo.rs.getString("sex"));
                customer.setAddress(dbUtil_bo.rs.getString("address"));
                customer.setPassword(dbUtil_bo.rs.getString("password"));
                customer.setPower(dbUtil_bo.rs.getInt("power"));
                customer.setImg(dbUtil_bo.rs.getString("img"));
                list.add(customer);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List GetRs() {

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Customer";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Customer customer = new Customer();
                customer.setAccount(dbUtil_bo.rs.getString("account"));
                customer.setID(dbUtil_bo.rs.getInt("ID"));
                customer.setPhone(dbUtil_bo.rs.getString("phone"));
                customer.setName(dbUtil_bo.rs.getString("name"));
                customer.setSex(dbUtil_bo.rs.getString("sex"));
                customer.setAddress(dbUtil_bo.rs.getString("address"));
                customer.setPassword(dbUtil_bo.rs.getString("password"));
                customer.setPower(dbUtil_bo.rs.getInt("power"));
                customer.setImg(dbUtil_bo.rs.getString("img"));
                list.add(customer);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int FindAccount(){
        //判断该用户是否存在
        int exit = 0;
        try {
            List list = GetSingleRs();
            if(list.size() > 0) {
                for(int i = 0; i < list.size(); i++) {
                    Customer customer = (Customer)list.get(i);
                    if (customer.getAccount().equals(account)) {
                        exit = 1;
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            return exit;
    }

    public int FindPassword(){
        //判断密码是否正确;
        int exit = 2;
        try {
            List list = GetSingleRs();
            if(list.size() > 0) {
                for(int i = 0; i < list.size(); i++) {
                    Customer customer = (Customer)list.get(i);
                    if (password.equals(customer.getPassword())) {
                        exit = 1;
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return exit;
    }

    public int FindMaxID(){

        int num = 1;
        List list = GetRs();
        if(list.size() != 0) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();
                String sql = "select MAX(ID) from Customer";
                dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
                C3P0Utils.executeQuery(dbUtil_bo);
                dbUtil_bo.rs.next();
                int number = Integer.valueOf(dbUtil_bo.rs.getInt(1));
                num = number + 1;
                C3P0Utils.realseSource(dbUtil_bo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return num;
    }

    public int DeleteAccount(){
        int flag = 1;
        //判断用户是否存在
        int exit = FindAccount();
        if(exit == 1) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();
                String sql = "DELETE FROM Customer a where a.account = " + "'" + account + "'";
                dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
                C3P0Utils.executeUpdate(dbUtil_bo);
                C3P0Utils.realseSource(dbUtil_bo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            flag = 0;
        return flag;
    }

    public int ChangeAccountInfo(Customer customer) {

        int flag = 1;
        int exit = FindAccount();

        if (exit == 1) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();

                String sql = "update Customer set name = " + "'" + customer.getName() + "'"
                        + ",sex =" + "'" + customer.getSex() + "'" + ",phone = " + "'" + customer.getPhone() + "'"
                        + ",address =" + "'" + customer.getAddress() + "'" + ",password =" + "'" + customer.getPassword() + "'"
                        + ",img = " + "'" + customer.getImg() + "'where account =" + "'" + customer.getAccount() + "'";

                dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);

                C3P0Utils.executeUpdate(dbUtil_bo);
                C3P0Utils.realseSource(dbUtil_bo);

            }catch (Exception e) {
                e.printStackTrace();
            }
        } else
            flag = 0;
        return flag;
    }

    public Customer getCustomer() {
        int exit = FindAccount();
        if (exit == 1) {
            List list = GetSingleRs();
            if(list.size() > 0){
                Customer customer = (Customer)list.get(0);
                return customer;
            }
            else
                return null;
        } else
            return null;
    }

    public String getSession(HttpServletRequest req) {
        Map<String, String> infoMap = (Map<String, String>) req.getSession().getAttribute("isLogin");
        if (infoMap != null) {
            String account = infoMap.get("account");
            return account;
        } else
            return null;
    }

    public void SetSession(HttpServletRequest req){
        Map<String, String> info = new HashMap<String, String>();
        info.put("account", account);
        HttpSession session = req.getSession(true);
        session.setAttribute("isLogin", info);
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
