package vlad.gurabatov.REST.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Name can't be null")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    String name;
    @NotNull(message = "Surname can't be null")
    @Size(min = 3, max = 30, message = "Surname must be between 3 and 30 characters")
    String surname;
    @NotNull(message = "Email can't be null")
    @Size(min = 3, max = 30, message = "Email must be between 3 and 30 characters")
    String lastName;
    @NotNull(message = "birthday can't be null")
    @Size(min = 3, max = 30, message = "birthday must be between 3 and 30 characters")
    LocalDate birthday;
    @OneToMany(mappedBy = "user")
    List<Book> books;
    @Email(message = "Email must be valid")
    String email;

    int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

}
