package MaterialManager;

import LoginRegister.Tools;
import StaffManager.StaffTools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeMaterialNum")
public class ChangeMaterialNum extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        String password = req.getParameter("password");
        String materialName = req.getParameter("materialName");
        String change_type = req.getParameter("change_type");
        int num = Integer.parseInt(req.getParameter("num"));

        MaterialTools materialTools = new MaterialTools(materialName);
        int exit = materialTools.FindMaterial();

        if(exit == 1){

            Tools tools = new Tools();
            StaffTools staffTools = new StaffTools(tools.getSession(req), password);
            exit = staffTools.FindPassword();

            if(exit == 1){

                Material mat = materialTools.getMaterial();
                int oldNum = mat.getNum();
                int sum;
                if(change_type.equals("1")) {
                    sum = oldNum + num;
                }
                else{
                    sum = oldNum - num;
                }
                if(sum < 0){
                    exit = 3;
                }
                else {
                    mat.setNum(sum);
                    materialTools.ChangeMaterialInfo(mat);
                }
            }
        }

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
