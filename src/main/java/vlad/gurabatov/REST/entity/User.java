package vlad.gurabatov.REST.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@ToString(exclude = { "comments", "books" })
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    @Size(min = 2, max = 30, message = "Surname must be between 2 and 30 characters")
    private String surname;
    @NotBlank(message = "Email is mandatory")
    @Size(min = 2, max = 30, message = "Email must be between 2 and 30 characters")
    private String lastName;
    @NotNull(message = "birthday is mandatory")
    private LocalDate birthday;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> books;
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Comment> comments;

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
}
