package vlad.gurabatov.REST.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Data
@ToString(exclude = "comments")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull(message = "Name is mandatory")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
    private String description;
    @NotNull
    @Size(min = 1, max = 50, message = "List of genres must be between 1 and 50")
    @CollectionTable(name = "books_genres", joinColumns = @JoinColumn(name = "book_id"))
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;
    @NotNull(message = "Author is mandatory")
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
    private Long views = 0L;

    public Book(User author, List<Genre> genres, String description, String name) {
        this.author = author;
        this.genres = genres;
        this.description = description;
        this.name = name;
    }

}

