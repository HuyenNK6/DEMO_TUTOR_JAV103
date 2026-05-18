package tutor.demoFilter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter", value = {
        "/nhan-vien/*",
        "/quan-ly/*"
})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //người dùng truy cập -> request -> filter ->controller -> servlet
        // filterChain.doFilter(servletRequest, servletResponse);
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        String taiKhoan = (String) session.getAttribute("taiKhoan");
        String chucVu = (String) session.getAttribute("chucVu");

        if (taiKhoan != null) {
            //có tài khoản -> đã đăng nhập
            if (chucVu.equals("ql")) {
                filterChain.doFilter(req, resp);
            } else if (chucVu.equals("nv")) {
                String uri = req.getRequestURI();
                System.out.println("URI= "+uri);
                if (uri.contains("nhan-vien")) {
                    filterChain.doFilter(req, resp);
                } else {
                    req.getRequestDispatcher("/demoFilter/error403.jsp").forward(req, resp);
                }
            }
        } else {
            //taiKhoan == null-> ko có tài khoản -> quay lại trang đăng nhập
            resp.sendRedirect("/demo-filter/login");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
