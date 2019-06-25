package StaffManager;

import Utils.C3P0Utils;
import Utils.DBUtil_BO;

import java.util.ArrayList;
import java.util.List;

public class StaffTools {

    private String account;
    private String password;

    public StaffTools(){

    }

    public StaffTools(String account, String password){
        this.account = account;
        this.password = password;
    }

    public List GetSingleRs() {

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Staff where account =" + "'" + account + "'";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Staff staff = new Staff();
                staff.setAccount(dbUtil_bo.rs.getString("account"));
                staff.setWorkNum(dbUtil_bo.rs.getInt("workNum"));
                staff.setName(dbUtil_bo.rs.getString("name"));
                staff.setSex(dbUtil_bo.rs.getString("sex"));
                staff.setPhone(dbUtil_bo.rs.getString("phone"));
                staff.setAddress(dbUtil_bo.rs.getString("address"));
                staff.setIDcard(dbUtil_bo.rs.getString("IDcard"));
                staff.setPassword(dbUtil_bo.rs.getString("password"));
                staff.setPower(dbUtil_bo.rs.getInt("power"));
                staff.setImg(dbUtil_bo.rs.getString("img"));
                list.add(staff);

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
            String sql = "select * from Staff";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Staff staff = new Staff();
                staff.setAccount(dbUtil_bo.rs.getString("account"));
                staff.setWorkNum(dbUtil_bo.rs.getInt("workNum"));
                staff.setName(dbUtil_bo.rs.getString("name"));
                staff.setSex(dbUtil_bo.rs.getString("sex"));
                staff.setPhone(dbUtil_bo.rs.getString("phone"));
                staff.setAddress(dbUtil_bo.rs.getString("address"));
                staff.setIDcard(dbUtil_bo.rs.getString("IDcard"));
                staff.setPassword(dbUtil_bo.rs.getString("password"));
                staff.setPower(dbUtil_bo.rs.getInt("power"));
                staff.setImg(dbUtil_bo.rs.getString("img"));
                list.add(staff);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int FindStaff(){
        //判断该员工是否存在
        int exit = 0;
        try {
            List list = GetSingleRs();
            if(list.size() > 0) {
                for(int i = 0; i < list.size(); i++) {
                    Staff staff = (Staff)list.get(i);
                    if (staff.getAccount().equals(account)) {
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
                    Staff staff = (Staff)list.get(i);
                    if (password.equals(staff.getPassword())) {
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

    public int FindMaxWorkNum(){
        int num = 1;
        List list = GetRs();
        if(list.size() != 0) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();
                String sql = "select MAX(workNum) from staff";
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

    public int DeleteStaff(){
        int flag = 1;
        //判断员工是否存在
        int exit = FindStaff();
        if(exit == 1) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();
                String sql = "DELETE FROM staff a where a.account = " + "'" + account + "'";
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

    public int ChangeStaffInfo(Staff staff) {

        int flag = 1;
        int exit = FindStaff();

        if (exit == 1) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();

                String sql = "update Staff set name = " + "'" + staff.getName() + "'"
                        + ",sex =" + "'" + staff.getSex() + "'" + ",phone = " + "'" + staff.getPhone() + "'"
                        + ",address =" + "'" + staff.getAddress() + "'" + ",password =" + "'" + staff.getPassword() + "'"
                        + ",IDcard =" + "'" + staff.getIDcard() + "'" + ",power =" + "'" + staff.getPower() + "'"
                        + ",img = " + "'" + staff.getImg() + "'where account =" + "'" + staff.getAccount() + "'";

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

    public Staff getStaff() {
        int exit = FindStaff();
        if (exit == 1) {
            List list = GetSingleRs();
            if(list.size() > 0){
                Staff staff = (Staff)list.get(0);
                return staff;
            }
            else
                return null;
        } else
            return null;
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
