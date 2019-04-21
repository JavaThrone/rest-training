package it.discovery.repository;

import it.discovery.model.Book;
import it.discovery.pagination.Page;
import it.discovery.pagination.PageCriteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SimpleBookRepository implements BookRepository {
    private final Map<Integer, Book> books = new ConcurrentHashMap<>();

    private int counter = 0;

    @Override
    public Book findById(int id) {
        return books.get(id);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Page searchBooks(PageCriteria pageCriteria) {
        List<Book> bookList = new ArrayList<>(books.values());
        int startOffset = pageCriteria.getSize() * pageCriteria.getPage();
        int endOffset = pageCriteria.getSize() * (pageCriteria.getPage() + 1);

        return new Page(books.size(), bookList.subList(
                startOffset,
                endOffset));
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            counter++;
            book.setId(counter);
            books.put(counter, book);
            System.out.println("*** Book with id=" + book.getId() + " was created");
        } else {
            books.put(book.getId(), book);
            System.out.println("*** Book with id=" + book.getId() + " was updated");
        }
        return book;
    }

    @Override
    public boolean delete(int id) {
        if (!books.containsKey(id)) {
            return false;
        }

        books.remove(id);
        System.out.println("*** Book with id=" + id + " was deleted");
        return true;
    }

    @Override
    public boolean isEmpty() {
        return books.isEmpty();
    }

}
