package vlad.gurabatov.REST.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private List<Genre> genres;
    @ManyToOne
    private User author;


}
