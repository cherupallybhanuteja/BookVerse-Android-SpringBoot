package org.androidspringbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // JPA annotation to make this class a managed entity
@Table(name = "manga") // Optional: Specify the table name if different from the class name
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // This should be the primary key

    private String title;
    private String author;
    private int year;
    private boolean status;
    private String url;  // The image URL field

    // Getter for URL (if needed, but Lombok should generate this automatically)
    public String getUrl() {
        return url;
    }
}
