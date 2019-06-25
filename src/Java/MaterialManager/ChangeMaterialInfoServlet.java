package MaterialManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeMaterialInfoServlet")
public class ChangeMaterialInfoServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        String materialName = req.getParameter("material");
        int num = Integer.parseInt(req.getParameter("num"));

        MaterialTools materialTools = new MaterialTools(materialName);
        Material material = materialTools.getMaterial();

        if(num > 0)
            material.setNum(num);

        int flag = materialTools.ChangeMaterialInfo(material);

        String json = "{\"flag\":\"" + flag + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
    }
}
