package OrderManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DistributeOrder")
public class DistributeOrder extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        int orderNumber = Integer.parseInt(req.getParameter("orderNumber"));
        String isDistribute = "1";

        OrderTools orderTools = new OrderTools(orderNumber);
        Orders orders = orderTools.getOrder();

        int mark;

        if(orders.getIsAccept().equals("0")){
            mark = 0;
        }
        else{
            orders.setIsDistribute(isDistribute);
            mark = orderTools.ChangeOrderInfo(orders);
        }

        String json = "{\"mark\":\"" + mark + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
