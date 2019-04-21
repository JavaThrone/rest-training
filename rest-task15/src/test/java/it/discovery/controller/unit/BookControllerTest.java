package it.discovery.controller.unit;

import it.discovery.RestApplication;
import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(RestApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<Book> jacksonTester;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void saveBook_emptyStorage_bookSaved() throws Exception {
        Book book = new Book();
        book.setAuthor("Gosling");
        book.setName("Spring MVC");
        book.setYear(2020);

        given(bookRepository.save(book))
                .willReturn(book);

        ResultActions actions = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jacksonTester.write(book).getJson()));

        actions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.author", Matchers.equalTo(book.getAuthor())));
    }
}
