package tutor.b1_hibernate.repository;

import org.hibernate.Session;
import tutor.b1_hibernate.entity.Lop;
import tutor.b1_hibernate.util.HibernateConfig;

import java.util.List;

public class LopRepository {
    private Session s;

    public LopRepository() {
        s= HibernateConfig.getFACTORY().openSession();
    }
    public List<Lop> getAll(){
        return s.createQuery("FROM Lop ").list();
    }
    public Lop getOne(Integer id){
        return s.find(Lop.class, id);
    }
}
