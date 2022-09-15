package frontend.controller;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "book",indexStoreType = "book")
public class BookEntity {
    @Id
    private String id;
    private String name;
    private String title;
    private String description;
}
