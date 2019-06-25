package OrderManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeOrderInfoServlet")
public class ChangeOrderInfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        int orderNumber = Integer.parseInt(req.getParameter("orderNumber"));
        String staffAccount = req.getParameter("staffAccount");
        String address = req.getParameter("address");
        String request = req.getParameter("request");
        String isCompletion = req.getParameter("isCompletion");

        OrderTools orderTools = new OrderTools(orderNumber);
        Orders orders = orderTools.getOrder();

        if(staffAccount != null)
            orders.setStaffAccount(staffAccount);
        if(address != null)
            orders.setAddress(address);
        if(request != null)
            orders.setRequest(address);
        if(isCompletion != null)
            orders.setIsCompletion(isCompletion);

        int flag = orderTools.ChangeOrderInfo(orders);

        String json = "{\"flag\":\"" + flag + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
