package frontend.service;
import frontend.api.response.ApiResponse;
import frontend.controller.BookEntity;
import frontend.feign.BookProxy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
     ElasticsearchOperations elasticsearchOperations;
    @Autowired BookProxy bookProxy;

    @Override
    public List<BookEntity> searchBook(String text) {
        ApiResponse apiResponse = bookProxy.searchByQuery(text).getBody();

    List<String> searchIn = new ArrayList<>();
        searchIn.add("title");
        searchIn.add("name");
        searchIn.add("author");
        SearchHits<BookEntity> searchHits = search(text,searchIn, BookEntity.class);
        List<BookEntity> books = searchHits.stream().map(e->e.getContent()).collect(Collectors.toList());
        return books;
    }

    public <D> SearchHits<D> search(String text , List<String> searchIn, Class<D> destination){
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery(text,searchIn.toArray(new String[0]))).build();
        SearchHits<D> searchHits = elasticsearchOperations.search(nativeSearchQuery,destination);
        return searchHits;
    }
}
