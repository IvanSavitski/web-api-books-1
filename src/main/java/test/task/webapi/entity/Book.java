package test.task.webapi.entity;
import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data   /* equals
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
*/

@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false, name = "isbn")
    private String isbn;

    @Column(nullable = false, length = 200, name = "title")
    private String title;

    @Column(nullable = false, length = 200, name = "genre")
    private String genre;

    @Column(nullable = false, length = 2000, name = "description")
    private String description;

    @Column(nullable = false, length = 200, name = "author")
    private String author;

}
