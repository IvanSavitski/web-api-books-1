package test.task.webapi.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import test.task.webapi.entity.Book;
import test.task.webapi.service.BookService;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    // http://localhost:8082/api/books/get/getAll
    @GetMapping("get/getAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    // http://localhost:8082/api/books/get/getById/{id}
    @GetMapping("get/getById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable (value = "id") Long id) {

        //return new ResponseEntity<Book>(bookService.getBookById(id), HttpStatus.OK);

        return bookService.getBookById(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        // DELETING
        // Optional<Book> optionalBook = bookRepository.findById(id);
        //return optionalBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // http://localhost:8082/api/books/get/getByIsbn/{isbn}
    @GetMapping("get/getByIsbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable (value = "isbn") String isbn) {

        //return new ResponseEntity<Book>(bookService.getBookByIsbn(isbn), HttpStatus.OK);

        return bookService.getBookByIsbn(isbn)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        // DELETING
        // Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        // return optionalBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }





    //http://localhost:8082/api/books/create
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        //return this.bookRepository.save(book);
        //or ???          return bookRepository.save(book);
        log.info("Placing Order");
        return new ResponseEntity<Book>(bookService.addBook(book), HttpStatus.CREATED);
    }




    //http://localhost:8082/api/books/update/{id}
    @PutMapping("update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {

        // DELETING
        /*Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            // обновление информации о книге
            existingBook.setIsbn(book.getIsbn());
            existingBook.setTitle(book.getTitle());
            existingBook.setGenre(book.getGenre());
            existingBook.setDescription(book.getDescription());
            existingBook.setAuthor(book.getAuthor());

            return ResponseEntity.ok(bookRepository.save(existingBook));
        } else {
            return ResponseEntity.notFound().build();
        }*/

        return new ResponseEntity<Book>(bookService.updateBook(book, id), HttpStatus.OK);


    }



    //http://localhost:8082/api/books/delete/{id}
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        //bookRepository.deleteById(id);
        //return ResponseEntity.noContent().build();

        bookService.deleteBook(id);
        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }
}
