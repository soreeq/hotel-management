package pl.hotelmanagement.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="guests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Guest {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guest_id;

    @NotBlank(message = "Pole nie mogą pozostać puste")
    private String firstname;
    private String lastname;
    private String middlename;
    private String address;
    private int phonenumber;
    private String email;
    private String notes;

}
