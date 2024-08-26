package com.book.BookstoreAPI.service;

import com.book.BookstoreAPI.model.Book;
import com.book.BookstoreAPI.repository.BookRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    private final MeterRegistry meterRegistry;
    private final Counter bookCreationCounter;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, MeterRegistry meterRegistry) {
        this.bookRepository = bookRepository;
        this.meterRegistry = meterRegistry;

        this.bookCreationCounter = Counter.builder("custom.metric.book.count")
                .description("Counts the number of books created")
                .register(meterRegistry);

        // Register the gauge metric
        Gauge.builder("custom.metric.book.gauge", this, service -> getBookCount())
                .register(meterRegistry);


    }

    @Override
    public Book createBook(Book book) {
        Book createdBook = bookRepository.save(book);
        bookCreationCounter.increment();
        return createdBook;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooks(String title, String author) {
        return bookRepository.findAll().stream()
                .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                        (author == null || book.getAuthor().equalsIgnoreCase(author)))
                .collect(Collectors.toList());
    }

    private double getBookCount() {
        return bookRepository.count();  // This returns the total number of books
    }
}
