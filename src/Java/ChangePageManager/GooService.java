package ChangePageManager;

import Utils.C3P0Utils;
import OrderManager.Orders;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class GooService {
    public List<Orders> getPageList(Integer i, Integer j) {
        QueryRunner runner = new QueryRunner();
        List<Orders> list = null;
        Connection connection = C3P0Utils.getConnection();

        try {
//            "select * from ORDERS limit ?,?", new BeanListHandler(Orders.class), i, j
            String sql = "select * from Orders limit" + "'" + i + "'" + "," + "'" + j + "'";
            list = (List)runner.query(connection, sql, new BeanListHandler(Orders.class));
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            C3P0Utils.close(connection, (PreparedStatement)null, (ResultSet)null);
        }

        return list;
    }

    public Integer getCounter() {
        Integer counter = null;
        Connection connection = C3P0Utils.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = connection.prepareStatement("select count(orderNumber) from Orders");

            for(rs = pst.executeQuery(); rs.next(); counter = rs.getInt(1)) {
            }
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            C3P0Utils.close(connection, (PreparedStatement)null, (ResultSet)null);
        }

        return counter;
    }

}
