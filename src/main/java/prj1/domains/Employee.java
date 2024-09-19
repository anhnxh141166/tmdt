package prj1.domains;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("employee")
public class Employee {
    @Id

    private Long id;

    private String name;

    private String password;

    private int sdt;

    private String address;

    private String email;

    private String gender;

    private String creatd_by;

    private LocalDateTime creatd_at;

    private String updated_by;

    private LocalDateTime Update_at;

    public Employee() {

    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCreatd_by(String creatd_by) {
        this.creatd_by = creatd_by;
    }

    public void setCreatd_at(LocalDateTime creatd_at) {
        this.creatd_at = creatd_at;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public void setUpdate_at(LocalDateTime update_at) {
        Update_at = update_at;
    }
}


