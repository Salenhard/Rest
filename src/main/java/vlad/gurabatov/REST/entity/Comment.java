package vlad.gurabatov.REST.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "Comment is required")
    @Size(min = 5, max = 500, message = "Comment must be between 5 and 500 characters")
    private String text;
    private LocalDate createDate;
    @ManyToOne
    @NotNull(message = "Book is required")
    private Book book;
    @ManyToOne
    @NotNull(message = "User is required")
    private User author;
}
