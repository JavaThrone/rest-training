package it.discovery.controller;

import it.discovery.exception.BookNotFoundException;
import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("{id}")
    public Book findById(@PathVariable int id) {
        return Optional.ofNullable(bookRepository.findById(id))
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PostMapping
    public Book save(@Valid @RequestBody Book book) {
        bookRepository.save(book);
        return book;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        bookRepository.delete(id);
    }
}
