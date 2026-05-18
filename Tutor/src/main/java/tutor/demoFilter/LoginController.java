package tutor.demoFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "loginController", value = {
        "/demo-filter/login",
        "/demo-filter/logout"
})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri= req.getRequestURI();
        if(uri.contains("login")){
            req.getRequestDispatcher("/demoFilter/login.jsp").forward(req,resp);
        }else{
            HttpSession session= req.getSession();
            session.invalidate();
            resp.sendRedirect("/demo-filter/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //username: HuyenNK6 và password: 123 và role : Giảng viên
        //username: MSSV và password: [tên lớp] và role: Sinh viên
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (
                (username.equals("HuyenNK6") && password.equals("123"))
                        || (username.equals("PH12345") && password.equals("456"))
        ) {
            HttpSession session = req.getSession();
            session.setAttribute("taiKhoan", username);
            if (username.equals("HuyenNK6")) {
                session.setAttribute("chucVu", "ql");
                resp.sendRedirect("/quan-ly");
            }else{
                session.setAttribute("chucVu", "nv");
                resp.sendRedirect("/nhan-vien");
            }
        } else {
            req.setAttribute("messageLogin", "Sai thông tin đăng nhập!");
            req.getRequestDispatcher("/demoFilter/login.jsp").forward(req, resp);
        }
    }
}
