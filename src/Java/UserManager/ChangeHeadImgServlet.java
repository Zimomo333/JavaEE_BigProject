package UserManager;

import LoginRegister.Tools;

import StaffManager.Staff;
import StaffManager.StaffTools;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/ChangeHeadImgServlet")
public class ChangeHeadImgServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Tools tools = new Tools();
        StaffTools staffTools = new StaffTools();
        String account = tools.getSession(req);
        tools.setAccount(account);
        staffTools.setAccount(account);
        Customer customer;
        Staff staff;
        int exit;

        int flag = tools.FindAccount();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload;
        upload = new ServletFileUpload(factory);
        List<FileItem> list = null;
        String head_img = null;
        try {
            list = upload.parseRequest(req);
            for (FileItem fi : list) {
                if (!fi.isFormField()) {
                    if (fi.getSize() != 0) {
                        head_img = fi.getName();
                        String path = "C:\\Users\\HP\\IdeaProjects\\Test\\web\\webapp\\headImg";
                        InputStream is = fi.getInputStream();
                        FileOutputStream os = new FileOutputStream(new File(path, head_img));
                        IOUtils.copy(is, os);
                        os.close();
                        is.close();
                        fi.delete();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        if(flag == 0){
            staff = staffTools.getStaff();
            staff.setImg(head_img);
            exit = staffTools.ChangeStaffInfo(staff);
        }
        else {
            customer = tools.getCustomer();
            customer.setImg(head_img);
            exit = tools.ChangeAccountInfo(customer);
        }

        String json = "{\"exit\":\"" + exit + "\"}";
        resp.setContentType("application/json, charset = utf-8");
        resp.getWriter().println(json);
        resp.sendRedirect("/webapp/index.jsp");
    }
}
