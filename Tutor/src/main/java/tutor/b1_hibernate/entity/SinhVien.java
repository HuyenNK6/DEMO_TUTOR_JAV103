package tutor.b1_hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "sinh_vien")
public class SinhVien {
    //đặt tên theo camelCase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;
    @Column(name = "tuoi")
    private Integer tuoi;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;
    //Cách 1: Chỉ lưu ID đơn thuần, KHÔNG hiểu đây là quan hệ giữa 2 bảng.
//    @Column(name = "lop_id")
//    private Integer lopId;
    //Cách 2: Mapping quan hệ ORM chuẩn
    @ManyToOne
    @JoinColumn(name = "lop_id", referencedColumnName = "id")
    private Lop lop;
}
