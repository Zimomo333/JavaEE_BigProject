package UserManager;

import LoginRegister.Tools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeUserInfoServlet")
public class ChangeUserInfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        Tools tools = new Tools();
        String account = tools.getSession(req);
        tools.setAccount(account);
        Customer customer = tools.getCustomer();

        if(name != null)
            customer.setName(name);
        if(sex != null)
            customer.setSex(sex);
        if(phone != null)
            customer.setPhone(phone);
        if(address != null)
            customer.setAddress(address);

        int mark = tools.ChangeAccountInfo(customer);

        String json = "{\"mark\":\"" + mark + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
