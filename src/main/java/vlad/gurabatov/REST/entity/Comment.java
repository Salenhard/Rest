package vlad.gurabatov.REST.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Table(name = "comments")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    @NotBlank(message = "Comment is mandatory")
    @Size(min = 5, max = 500, message = "Comment must be between 5 and 500 characters")
    private String text;
    private LocalDate createDate;
    @ManyToOne
    @NotNull(message = "Book is mandatory")
    private Book book;
    @ManyToOne
    @NotNull(message = "User is mandatory")
    private User author;
}
