package UserManager;

import LoginRegister.Tools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        String account = req.getParameter("account");
        Tools tools = new Tools();
        tools.setAccount(account);

        int exit = tools.DeleteAccount();

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);

    }
}
