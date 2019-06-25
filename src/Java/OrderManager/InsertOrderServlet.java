package OrderManager;

import LoginRegister.Tools;
import Utils.C3P0Utils;
import Utils.DBUtil_BO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/InsertOrderServlet")
public class InsertOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userAccount = req.getParameter("userAccount");
        String staffAccount = "未接单";
        String paid = req.getParameter("paid");
        String address = req.getParameter("address");
        String request = req.getParameter("request");
        String isAccept = "0";
        String isDistribute = "0";
        String isCompletion = "0";
        Timestamp startDate = new Timestamp(new Date().getTime());

        OrderTools orderTools = new OrderTools();

        Tools tools = new Tools();
        tools.setAccount(userAccount);
        int exit = tools.FindAccount();
        if(exit == 1) {
            try {
                int orderNumber = orderTools.FindMaxOrder();
                DBUtil_BO dbUtil_bo = new DBUtil_BO();
                dbUtil_bo.conn = C3P0Utils.getConnection();

                String sql = "insert into Orders"
                        + "(orderNumber, userAccount, staffAccount, paid, address, request, isAccept, isDistribute, isCompletion, startDate, realDate)"
                        + "values(" + "'" + orderNumber + "'" + "," + "'" + userAccount + "'" + ","
                        + "'" + staffAccount + "'" + "," + "'" + paid + "'" + ","
                        + "'" + address + "'" + "," + "'" + request + "'" + ","
                        + "'" + isAccept + "'" + "," + "'" + isDistribute + "'" + ","
                        + "'" + isCompletion + "'" + "," + "'" + startDate + "'" + ","
                        + "'" + startDate + "'"
                        + ")";

                dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
                C3P0Utils.executeUpdate(dbUtil_bo);

                C3P0Utils.realseSource(dbUtil_bo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
