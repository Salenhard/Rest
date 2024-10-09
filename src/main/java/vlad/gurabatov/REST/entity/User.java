package vlad.gurabatov.REST.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Name can't be null")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;
    @NotNull(message = "Surname can't be null")
    @Size(min = 3, max = 30, message = "Surname must be between 3 and 30 characters")
    private String surname;
    @NotNull(message = "Email can't be null")
    @Size(min = 3, max = 30, message = "Email must be between 3 and 30 characters")
    private String lastName;
    @NotNull(message = "birthday can't be null")
    @Size(min = 3, max = 30, message = "birthday must be between 3 and 30 characters")
    private LocalDate birthday;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Book> books;
    @Email(message = "Email must be valid")
    private String email;

    public User(String name, String surname, String lastName, LocalDate birthday, String email) {
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

    @Override
    public String toString() {
        return "User{" +
                "lastName='" + lastName + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                '}';
    }
}
