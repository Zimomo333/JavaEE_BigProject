package OrderManager;

import ProductionManager.ProductionTools;
import Utils.C3P0Utils;
import Utils.DBUtil_BO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

@WebServlet("/CompleteOrder")
public class CompleteOrder extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        int orderNumber = Integer.parseInt(req.getParameter("orderNumber"));
        Timestamp realDate = new Timestamp(new Date().getTime());
        String isCompletion = "1";

        OrderTools orderTools = new OrderTools(orderNumber);
        Orders orders = orderTools.getOrder();

        int mark;

        if(orders.getIsDistribute().equals("0")){
            mark = 0;
        }
        else {
            orders.setIsCompletion(isCompletion);
            orders.setRealDate(realDate);
            mark = orderTools.ChangeOrderInfo(orders);
        }

        double needPay = orders.getPaid() /0.3 * 0.7;

        ProductionTools productionTools = new ProductionTools();

        try {
            int ID = productionTools.FindMaxProduction();
            DBUtil_BO dbUtil_bo = new DBUtil_BO();
            dbUtil_bo.conn = C3P0Utils.getConnection();

            String sql = "insert into Produce"
                    + "(ID, orderNumber, needPay)"
                    + "values(" + "'" + ID + "'" + "," + "'" + orderNumber + "'" + ","
                    + "'" + needPay + "'"
                    + ")";

            dbUtil_bo.st = dbUtil_bo.conn.prepareStatement(sql);
            C3P0Utils.executeUpdate(dbUtil_bo);

            C3P0Utils.realseSource(dbUtil_bo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String json = "{\"mark\":\"" + mark + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
