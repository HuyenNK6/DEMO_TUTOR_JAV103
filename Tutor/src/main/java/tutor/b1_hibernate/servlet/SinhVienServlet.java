package tutor.b1_hibernate.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import tutor.b1_hibernate.entity.Lop;
import tutor.b1_hibernate.entity.SinhVien;
import tutor.b1_hibernate.repository.LopRepository;
import tutor.b1_hibernate.repository.SinhVienRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SinhVienServlet", value = {
        "/sinh-vien/hien-thi",
        "/sinh-vien/add",
        "/sinh-vien/detail",
        "/sinh-vien/delete",
        "/sinh-vien/update",
        "/sinh-vien/view-update",
        "/sinh-vien/search",
        "/sinh-vien/paging",
        "/sinh-vien/top3"
        })
public class SinhVienServlet extends HttpServlet {
    SinhVienRepository svRepo= new SinhVienRepository();
    LopRepository lopRepo= new LopRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("hien-thi")){
            this.hienThi(req,resp);
        }else if(uri.contains("detail")){
            this.detail(req,resp);
        }else if(uri.contains("delete")){
            this.delete(req,resp);
        }else if(uri.contains("view-update")){
            this.viewUpdate(req,resp);
        }else if(uri.contains("search")){
            this.search(req,resp);
        }else if(uri.contains("paging")){
            this.paging(req,resp);
        }else if(uri.contains("top3")){
            this.top3(req,resp);
        }
        
        
        
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        if(uri.contains("add")){
            this.add(req,resp);
        }else if(uri.contains("update")){
            this.update(req,resp);
        }
    }
    @SneakyThrows
    private void update(HttpServletRequest req, HttpServletResponse resp) {
        SinhVien sv= new SinhVien();
        BeanUtils.populate(sv, req.getParameterMap());
//        String ma = req.getParameter("ma");
//        String ten = req.getParameter("ten");
//        Integer tuoi = Integer.parseInt( req.getParameter("tuoi"));
//        String diaChi = req.getParameter("diaChi");
//        Boolean gioiTinh = Boolean.valueOf(req.getParameter("gioiTinh"));

        Integer lopId = Integer.valueOf( req.getParameter("lopId"));
        Lop lop = lopRepo.getOne(lopId);
        sv.setLop(lop);
//        SinhVien sv= SinhVien.builder()
//                .ma(ma)
//                .ten(ten)
//                .tuoi(tuoi)
//                .diaChi(diaChi)
//                .gioiTinh(gioiTinh)
//                .lop(lop)
//                .build();

        svRepo.update(sv);
        resp.sendRedirect("/sinh-vien/hien-thi");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String ma = req.getParameter("ma");
        String ten = req.getParameter("ten");
        Integer tuoi = Integer.parseInt( req.getParameter("tuoi"));
        String diaChi = req.getParameter("diaChi");
        Boolean gioiTinh = Boolean.valueOf(req.getParameter("gioiTinh"));

        Integer lopId = Integer.valueOf( req.getParameter("lopId"));
        Lop lop = lopRepo.getOne(lopId);

        SinhVien sv= SinhVien.builder()
                .ma(ma)
                .ten(ten)
                .tuoi(tuoi)
                .diaChi(diaChi)
                .gioiTinh(gioiTinh)
                .lop(lop)
                .build();
        svRepo.add(sv);
        resp.sendRedirect("/sinh-vien/hien-thi");
    }

    private void top3(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String tenTimKiem= req.getParameter("tenTimKiem");
        req.setAttribute("listSV", svRepo.search(tenTimKiem));
        req.setAttribute("listLop",lopRepo.getAll());
        req.getRequestDispatcher("/buoi1/hien-thi.jsp").forward(req,resp);
    }

    private void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id= req.getParameter("id");
        SinhVien sv= svRepo.getOne(Integer.valueOf(id));
        req.setAttribute("sv",sv);
        req.setAttribute("listLop",lopRepo.getAll());
        req.getRequestDispatcher("/buoi1/view-update.jsp").forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id= req.getParameter("id");
        svRepo.delete(Integer.valueOf(id));
        resp.sendRedirect("/sinh-vien/hien-thi");
    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id= req.getParameter("id");
        SinhVien sv= svRepo.getOne(Integer.valueOf(id));
        req.setAttribute("sv",sv);
        req.setAttribute("listSV", svRepo.getAll());
        req.setAttribute("listLop",lopRepo.getAll());
        //resp.sendRedirect("/sinh-vien/hien-thi");
        req.getRequestDispatcher("/buoi1/hien-thi.jsp").forward(req,resp);
    }

    private void hienThi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SinhVien> listSV= svRepo.getAll();
        req.setAttribute("listSV", listSV);
        List<Lop> listLop= lopRepo.getAll();
        req.setAttribute("listLop",listLop);
        req.getRequestDispatcher("/buoi1/hien-thi.jsp").forward(req,resp);
    }
    private void paging(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = 3;
        int pageNumber = 1;
        if(req.getParameter("pageNumber") != null){
            pageNumber= Integer.valueOf(req.getParameter("pageNumber"));
        }
        //---------------------
        // chặn pageNumber nhỏ hơn 1
        if(pageNumber < 1){
            pageNumber = 1;
        }
        // chặn pageNumber vượt quá totalPage
        // tổng số page
        int totalRecord= svRepo.getAll().size();
        int totalPage = (int) Math.ceil((double) totalRecord / pageSize);
        if(pageNumber > totalPage){
            pageNumber = totalPage;
        }
        //---------------------
        req.setAttribute("pageNumber",pageNumber);
        req.setAttribute("totalPage",totalPage);
        req.setAttribute("listSV", svRepo.paging(pageNumber,pageSize));
        req.setAttribute("listLop",lopRepo.getAll());
        req.getRequestDispatcher("/buoi1/hien-thi.jsp").forward(req,resp);
    }
}
