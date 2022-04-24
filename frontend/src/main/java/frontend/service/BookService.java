package frontend.service;
import frontend.controller.BookEntity;
import java.util.List;

public interface BookService {
    List<BookEntity> searchBook(String search);
}
