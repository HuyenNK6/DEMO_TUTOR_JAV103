package tutor.demoAjax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GiangVien {
    private String ma;
    private String ten;
    private Integer tuoi;
    private Boolean gioiTinh;
    private String boMon;

}
