package it.discovery.controller;

import io.micrometer.core.annotation.Timed;
import it.discovery.exception.BookNotFoundException;
import it.discovery.model.Book;
import it.discovery.pagination.Page;
import it.discovery.pagination.PageCriteria;
import it.discovery.repository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<List<Book>> search(PageCriteria pageCriteria) {
        Page page = bookRepository.searchBooks(pageCriteria);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-TOTAL-COUNT", String.valueOf(page.getTotalCount()));

        return ResponseEntity.ok().headers
                (headers).body(page.getBooks());
    }

    @GetMapping("{id}")
    @Timed("book.findById")
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
