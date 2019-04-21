package it.discovery.client;

import it.discovery.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

public class BookRestClient {
    private final RestTemplate restTemplate;

    public BookRestClient(String baseUrl) {
        restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(
                new DefaultUriBuilderFactory(baseUrl));
    }

    public ResponseEntity<Book> findById(int id) {
        return restTemplate.getForEntity("book/" + id, Book.class);
    }

    public ResponseEntity<Book> save(Book book) {
        return restTemplate.postForEntity("book", book, Book.class);
    }

    public static void main(String[] args) {
        Book book = new Book();
        book.setName("Spring MVC");
        book.setYear(2019);
        book.setAuthor("Gosling");
        BookRestClient client = new BookRestClient("http://localhost:8080/");
        client.save(book);
        ResponseEntity<Book> responseEntity =  client.findById(1);
        System.out.println("Body is " + responseEntity.getBody());
    }
}
