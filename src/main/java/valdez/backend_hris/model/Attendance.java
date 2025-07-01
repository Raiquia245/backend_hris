package valdez.backend_hris.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Getter
@Setter
@Entity
public class Attendance {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Employee employee;

    private LocalDate date;

    // Presensi Masuk
    private LocalTime checkInTime;
    private Double checkInLatitude;
    private Double checkInLongitude;

    // Presensi Keluar
    private LocalTime checkOutTime;
    private Double checkOutLatitude;
    private Double checkOutLongitude;

    private String status; // HADIR, TERLAMBAT, BELUM_CHECKOUT, DLL

    private LocalDateTime createdAt = LocalDateTime.now();
}
