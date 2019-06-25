package OrderManager;

import LoginRegister.Tools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AcceptOrder")
public class AcceptOrder extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        int orderNumber = Integer.parseInt(req.getParameter("orderNumber"));

        String isAccept = "1";

        OrderTools orderTools = new OrderTools(orderNumber);
        Orders orders = orderTools.getOrder();

        Tools tools = new Tools();
        orders.setStaffAccount(tools.getSession(req));
        orders.setIsAccept(isAccept);

        int mark = orderTools.ChangeOrderInfo(orders);

        String json = "{\"mark\":\"" + mark + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
