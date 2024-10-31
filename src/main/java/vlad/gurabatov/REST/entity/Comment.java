package vlad.gurabatov.REST.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Table(name = "comments")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NotBlank(message = "Comment is mandatory")
    @Size(min = 5, max = 500, message = "Comment must be between 5 and 500 characters")
    private String text;
    private LocalDate createDate = LocalDate.now();
    @ManyToOne
    @NotNull(message = "Book is mandatory")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User author;
}
