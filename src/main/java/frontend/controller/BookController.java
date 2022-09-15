package frontend.controller;
import frontend.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired BookService bookService;

    @GetMapping("/{search}")
    public ResponseEntity<?> searchBook(@PathVariable String search){
     List<BookEntity> books = bookService.searchBook(search);
      return new ResponseEntity<>(books, HttpStatus.CREATED);
    }

}
