package it.discovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.discovery.RestApplication;
import it.discovery.model.Book;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(RestApplication.class)
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void saveBook_emptyStorage_bookSaved() throws Exception {
        Book book = new Book();
        book.setAuthor("Gosling");
        book.setName("Spring MVC");
        book.setYear(2020);

        ResultActions actions = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(MAPPER.writeValueAsString(book)));

        actions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.author", Matchers.equalTo(book.getAuthor())));
    }

}

