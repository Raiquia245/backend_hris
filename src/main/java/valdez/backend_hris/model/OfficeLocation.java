package valdez.backend_hris.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OfficeLocation {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private Double latitude;
    private Double longitude;
    private Double radiusMeter; // misalnya 100 meter
}

