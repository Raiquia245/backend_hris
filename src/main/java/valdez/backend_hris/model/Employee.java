package valdez.backend_hris.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String fullName;
    private String position;
    private String department;
    private LocalDate joinDate;

    @OneToOne
    private Users user;
}
