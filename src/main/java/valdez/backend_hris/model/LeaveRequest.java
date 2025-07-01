package valdez.backend_hris.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class LeaveRequest {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Employee employee;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    private String status; // PENDING, APPROVED, REJECTED

    private LocalDateTime submittedAt = LocalDateTime.now();
}

