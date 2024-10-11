package vlad.gurabatov.REST.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Name is mandatory")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
    private String description;
    @NotNull
    @Size(min = 1, max = 50, message = "List of genres must be between 1 and 50")
    private List<Genre> genres;
    @NotNull(message = "Author is mandatory")
    @ManyToOne
    private User author;
    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Book(User author, List<Genre> genres, String description, String name) {
        this.author = author;
        this.genres = genres;
        this.description = description;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", genres=" + genres +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

