package OrderManager;

import LoginRegister.Tools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddMaterialRequest")
public class AddMaterialRequest extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        int orderNumber = Integer.parseInt(req.getParameter("orderNumber"));
        String materialRequest = req.getParameter("material_require");

        Tools tools = new Tools();
        String account = tools.getSession(req);

        OrderTools orderTools = new OrderTools(orderNumber);
        int exit = orderTools.FindOrders();
        if(exit == 1){
            Orders orders = orderTools.getOrder();
            if(orders.getIsCompletion().equals("0")) {
                if (orders.getStaffAccount().equals(account)) {
                    orders.setRequestMaterial(materialRequest);
                    orders.setIsDistribute("0");
                    exit = orderTools.ChangeOrderInfo(orders);
                } else
                    exit = 2;
            }
            else
                exit = 3;
        }

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
