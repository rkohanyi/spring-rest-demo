package com.example.demo;

import com.example.demo.view.SimpleBookView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void canFindBookById() throws Exception {
        var result = mockMvc.perform(get("/books/{id}", 3L))
                .andExpect(status().isOk())
                .andReturn();

        String resJson = result.getResponse().getContentAsString();
        SimpleBookView resObj = objectMapper.readValue(resJson, SimpleBookView.class);

        assertNotNull(resObj);
        assertNotNull(resObj.getId());
        assertEquals(resObj.getId(), 3L);
        assertEquals(resObj.getName(), "Book 3");
        assertEquals(resObj.getAuthorName(), "Author 2");
    }
}
