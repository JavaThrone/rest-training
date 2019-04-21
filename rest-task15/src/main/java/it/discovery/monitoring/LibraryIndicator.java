package it.discovery.monitoring;

import it.discovery.repository.BookRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class LibraryIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    public LibraryIndicator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Health health() {
        if(bookRepository.isEmpty()) {
            return Health.down().withDetail("reason", "No books in the library").build();
        }
        return Health.up().build();
    }
}
