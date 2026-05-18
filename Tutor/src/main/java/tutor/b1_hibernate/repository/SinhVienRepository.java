package tutor.b1_hibernate.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import tutor.b1_hibernate.entity.SinhVien;
import tutor.b1_hibernate.util.HibernateConfig;

import java.util.List;

public class SinhVienRepository {
    private Session session;

    public SinhVienRepository() {
        session = HibernateConfig.getFACTORY().openSession();//mở phiên làm việc của session
    }

    public List<SinhVien> getAll() {
        return session.createQuery("FROM SinhVien").list();
    }

    public SinhVien getOne(Integer id) {
        return session.find(SinhVien.class, id);
    }

    public void add(SinhVien sv) {
        try {
            session.getTransaction().begin();
            session.save(sv);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    public void update(SinhVien sv){
        try {
            session.getTransaction().begin();
            session.merge(sv);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    public void delete(Integer id){
        try {
            session.getTransaction().begin();
            session.delete(this.getOne(id));
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<SinhVien> search(String ten){
        //org.hibernate.Hibernate;
        //JPQL: Java Persistence Query Language
        // là ngôn ngữ truy vấn của Java Persistence API dùng để truy vấn các Entity trong Java thay vì truy vấn trực tiếp bảng trong database.
        Query query= session.createQuery("FROM SinhVien WHERE ten LIKE :ten");
       //SQL:  Query query= session.createQuery("SELECT * FROM SinhVien sv WHERE sv.ten LIKE :ten");
        query.setParameter("ten", "%"+ten+"%");
        return query.list();
    }
    //phân trang
    //10 phần tử - pageNumber = 1, pageSize =3
    //trang 1: (1-1)*3 = 0 -> 0 1 2
    //trang 2: (2-1)*3 = 3 -> 3 4 5
    //trang 3: (3-1)*3 = 6 -> 6 7 8
    //trang 4: (4-1)*3 = 9 -> 9 10
    public List<SinhVien> paging(Integer pageNumber, Integer pageSize){
        Query query = session.createQuery("FROM SinhVien sv");
        query.setFirstResult((pageNumber-1)* pageSize);
        query.setMaxResults(pageSize);
        return query.list();
    }
    public static void main(String[] args) {
        System.out.println(new SinhVienRepository().getAll());
    }
}
