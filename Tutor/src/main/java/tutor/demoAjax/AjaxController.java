package tutor.demoAjax;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AjaxController", value = "/api/ajax/giang-vien")
public class AjaxController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GiangVien gv= new GiangVien("GV01","Nguyen Van A", 20, false, "UDPM");
        //chuyển Object sang String JSON
        Gson gson= new Gson();
        String data= gson.toJson(gv);
        //chuyển đổi kiểu dữ liệu của response
        resp.setContentType("application/json");
        //Hiển thị dữ liệu lên màn hình
        PrintWriter pw= resp.getWriter();
        pw.println(data);
        pw.flush();
    }
}
