package MaterialManager;

import LoginRegister.Tools;
import StaffManager.StaffTools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteMaterialServlet")
public class DeleteMaterialServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        String password = req.getParameter("password");
        String material = req.getParameter("materialName");

        MaterialTools materialTools = new MaterialTools(material);

        int exit = materialTools.FindMaterial();

        if(exit == 1) {
            Tools tools = new Tools();
            StaffTools staffTools = new StaffTools(tools.getSession(req), password);
            exit = staffTools.FindPassword();
            if(exit == 1){
                exit = materialTools.DeleteMaterial();
            }
        }

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
