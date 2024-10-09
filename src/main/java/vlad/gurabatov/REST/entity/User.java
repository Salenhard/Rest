package vlad.gurabatov.REST.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    String name;
    String surname;
    String lastName;
    LocalDate birthday;
    @OneToMany(mappedBy = "user")
    List<Book> books;

    int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

}
