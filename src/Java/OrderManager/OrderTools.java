package OrderManager;

import Utils.C3P0Utils;
import Utils.DBUtil_BO;

import java.util.ArrayList;
import java.util.List;

public class OrderTools {

    private int orderNumber;

    public OrderTools(){

    }

    public OrderTools(int orderNumber){
        this.orderNumber = orderNumber;
    }

    public List GetSingleRs() {

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Orders where orderNumber =" + "'" + orderNumber + "'";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Orders orders = new Orders();
                orders.setOrderNumber(dbUtil_bo.rs.getInt("orderNumber"));
                orders.setUserAccount(dbUtil_bo.rs.getString("userAccount"));
                orders.setStaffAccount(dbUtil_bo.rs.getString("staffAccount"));
                orders.setPaid(dbUtil_bo.rs.getDouble("paid"));
                orders.setAddress(dbUtil_bo.rs.getString("address"));
                orders.setRequest(dbUtil_bo.rs.getString("request"));
                orders.setRequestMaterial(dbUtil_bo.rs.getString("requestMaterial"));
                orders.setIsAccept(dbUtil_bo.rs.getString("isAccept"));
                orders.setIsDistribute(dbUtil_bo.rs.getString("isDistribute"));
                orders.setIsCompletion(dbUtil_bo.rs.getString("isCompletion"));
                orders.setStartDate(dbUtil_bo.rs.getTimestamp("startDate"));
                orders.setRealDate(dbUtil_bo.rs.getTimestamp("realDate"));
                list.add(orders);

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
            String sql = "select * from Orders";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Orders orders = new Orders();
                orders.setOrderNumber(dbUtil_bo.rs.getInt("orderNumber"));
                orders.setUserAccount(dbUtil_bo.rs.getString("userAccount"));
                orders.setStaffAccount(dbUtil_bo.rs.getString("staffAccount"));
                orders.setPaid(dbUtil_bo.rs.getFloat("paid"));
                orders.setAddress(dbUtil_bo.rs.getString("address"));
                orders.setRequest(dbUtil_bo.rs.getString("request"));
                orders.setRequestMaterial(dbUtil_bo.rs.getString("requestMaterial"));
                orders.setIsAccept(dbUtil_bo.rs.getString("isAccept"));
                orders.setIsDistribute(dbUtil_bo.rs.getString("isDistribute"));
                orders.setIsCompletion(dbUtil_bo.rs.getString("isCompletion"));
                orders.setStartDate(dbUtil_bo.rs.getTimestamp("startDate"));
                orders.setRealDate(dbUtil_bo.rs.getTimestamp("realDate"));
                list.add(orders);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int FindOrders(){
        //判断该订单是否存在
        int exit = 0;
        try {
            List list = GetSingleRs();
            if(list.size() > 0) {
                for(int i = 0; i < list.size(); i++) {
                    Orders orders = (Orders)list.get(i);
                    if (orders.getOrderNumber() == orderNumber) {
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

    public List FindIsDistributeOrders(String flag){

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Orders where isDistribute =" + "'" + flag + "'";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Orders orders = new Orders();
                orders.setOrderNumber(dbUtil_bo.rs.getInt("orderNumber"));
                orders.setUserAccount(dbUtil_bo.rs.getString("userAccount"));
                orders.setStaffAccount(dbUtil_bo.rs.getString("staffAccount"));
                orders.setPaid(dbUtil_bo.rs.getFloat("paid"));
                orders.setAddress(dbUtil_bo.rs.getString("address"));
                orders.setRequest(dbUtil_bo.rs.getString("request"));
                orders.setRequestMaterial(dbUtil_bo.rs.getString("requestMaterial"));
                orders.setIsAccept(dbUtil_bo.rs.getString("isAccept"));
                orders.setIsDistribute(dbUtil_bo.rs.getString("isDistribute"));
                orders.setIsCompletion(dbUtil_bo.rs.getString("isCompletion"));
                orders.setStartDate(dbUtil_bo.rs.getTimestamp("startDate"));
                orders.setRealDate(dbUtil_bo.rs.getTimestamp("realDate"));
                list.add(orders);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List FindIsAcceptOrders(String flag){

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Orders where isAccept =" + "'" + flag + "'";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Orders orders = new Orders();
                orders.setOrderNumber(dbUtil_bo.rs.getInt("orderNumber"));
                orders.setUserAccount(dbUtil_bo.rs.getString("userAccount"));
                orders.setStaffAccount(dbUtil_bo.rs.getString("staffAccount"));
                orders.setPaid(dbUtil_bo.rs.getFloat("paid"));
                orders.setAddress(dbUtil_bo.rs.getString("address"));
                orders.setRequest(dbUtil_bo.rs.getString("request"));
                orders.setRequestMaterial(dbUtil_bo.rs.getString("requestMaterial"));
                orders.setIsAccept(dbUtil_bo.rs.getString("isAccept"));
                orders.setIsDistribute(dbUtil_bo.rs.getString("isDistribute"));
                orders.setIsCompletion(dbUtil_bo.rs.getString("isCompletion"));
                orders.setStartDate(dbUtil_bo.rs.getTimestamp("startDate"));
                orders.setRealDate(dbUtil_bo.rs.getTimestamp("realDate"));
                list.add(orders);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List FindIsCompleteOrders(String flag){

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Orders where isCompletion =" + "'" + flag + "'";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Orders orders = new Orders();
                orders.setOrderNumber(dbUtil_bo.rs.getInt("orderNumber"));
                orders.setUserAccount(dbUtil_bo.rs.getString("userAccount"));
                orders.setStaffAccount(dbUtil_bo.rs.getString("staffAccount"));
                orders.setPaid(dbUtil_bo.rs.getFloat("paid"));
                orders.setAddress(dbUtil_bo.rs.getString("address"));
                orders.setRequest(dbUtil_bo.rs.getString("request"));
                orders.setRequestMaterial(dbUtil_bo.rs.getString("requestMaterial"));
                orders.setIsAccept(dbUtil_bo.rs.getString("isAccept"));
                orders.setIsDistribute(dbUtil_bo.rs.getString("isDistribute"));
                orders.setIsCompletion(dbUtil_bo.rs.getString("isCompletion"));
                orders.setStartDate(dbUtil_bo.rs.getTimestamp("startDate"));
                orders.setRealDate(dbUtil_bo.rs.getTimestamp("realDate"));
                list.add(orders);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List FindCustomerOrders(String account){

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Orders where userAccount =" + "'" + account + "'";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Orders orders = new Orders();
                orders.setOrderNumber(dbUtil_bo.rs.getInt("orderNumber"));
                orders.setUserAccount(dbUtil_bo.rs.getString("userAccount"));
                orders.setStaffAccount(dbUtil_bo.rs.getString("staffAccount"));
                orders.setPaid(dbUtil_bo.rs.getFloat("paid"));
                orders.setAddress(dbUtil_bo.rs.getString("address"));
                orders.setRequest(dbUtil_bo.rs.getString("request"));
                orders.setRequestMaterial(dbUtil_bo.rs.getString("requestMaterial"));
                orders.setIsAccept(dbUtil_bo.rs.getString("isAccept"));
                orders.setIsDistribute(dbUtil_bo.rs.getString("isDistribute"));
                orders.setIsCompletion(dbUtil_bo.rs.getString("isCompletion"));
                orders.setStartDate(dbUtil_bo.rs.getTimestamp("startDate"));
                orders.setRealDate(dbUtil_bo.rs.getTimestamp("realDate"));
                list.add(orders);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List FindStaffAcceptOrders(String account){

        List list = new ArrayList();

        try {
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();
            String sql = "select * from Orders where staffAccount =" + "'" + account + "'" + "and isAccept =" + "'" + "1" + "'";
            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeQuery(dbUtil_bo);

            while (dbUtil_bo.rs.next()) {

                Orders orders = new Orders();
                orders.setOrderNumber(dbUtil_bo.rs.getInt("orderNumber"));
                orders.setUserAccount(dbUtil_bo.rs.getString("userAccount"));
                orders.setStaffAccount(dbUtil_bo.rs.getString("staffAccount"));
                orders.setPaid(dbUtil_bo.rs.getFloat("paid"));
                orders.setAddress(dbUtil_bo.rs.getString("address"));
                orders.setRequest(dbUtil_bo.rs.getString("request"));
                orders.setRequestMaterial(dbUtil_bo.rs.getString("requestMaterial"));
                orders.setIsAccept(dbUtil_bo.rs.getString("isAccept"));
                orders.setIsDistribute(dbUtil_bo.rs.getString("isDistribute"));
                orders.setIsCompletion(dbUtil_bo.rs.getString("isCompletion"));
                orders.setStartDate(dbUtil_bo.rs.getTimestamp("startDate"));
                orders.setRealDate(dbUtil_bo.rs.getTimestamp("realDate"));
                list.add(orders);

            }
            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int FindMaxOrder(){
        int num = 1;
        List list = GetRs();
        if(list.size() != 0) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();
                String sql = "select MAX(orderNumber) from Orders";
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

    public int DeleteOrder(){
        int flag = 1;
        //判断订单是否存在
        int exit = FindOrders();
        if(exit == 1) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();
                String sql = "DELETE FROM Orders a where a.orderNumber = " + "'" + orderNumber + "'";
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

    public int ChangeOrderInfo(Orders orders) {

        int flag = 1;
        int exit = FindOrders();

        if (exit == 1) {
            try {
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();

                String sql = "update Orders set userAccount = " + "'" + orders.getUserAccount() + "'"
                        + ",staffAccount =" + "'" + orders.getStaffAccount() + "'" + ",address = " + "'" + orders.getAddress() + "'"
                        + ",request =" + "'" + orders.getRequest() + "'" + ",isDistribute =" + "'" + orders.getIsDistribute() + "'"
                        + ",isAccept =" + "'" + orders.getIsAccept() + "'" + ",isCompletion =" + "'" + orders.getIsCompletion() + "'"
                        + ",realDate =" + "'" + orders.getRealDate() + "'" + ",startDate = " + "'" + orders.getStartDate() + "'"
                        + ",requestMaterial =" + "'" + orders.getRequestMaterial()
                        + "'where orderNumber =" + "'" + orders.getOrderNumber() + "'";

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

    public Orders getOrder() {
        int exit = FindOrders();
        if (exit == 1) {
            List list = GetSingleRs();
            if(list.size() > 0){
                Orders orders = (Orders) list.get(0);
                return orders;
            }
            else
                return null;
        } else
            return null;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

}
