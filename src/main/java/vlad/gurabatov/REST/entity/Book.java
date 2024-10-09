package vlad.gurabatov.REST.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Name can't be null")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
    private String description;
    @NotNull
    @Size(min = 1, max = 50, message = "List of genres must be between 1 and 50")
    private List<Genre> genres;
    @NotNull(message = "Author can't be null")
    @ManyToOne
    private User author;


}
