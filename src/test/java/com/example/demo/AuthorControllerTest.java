package com.example.demo;

import com.example.demo.view.CreateAuthorView;
import com.example.demo.view.SimpleAuthorView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void canCreateAuthor() throws Exception {
        var reqObj = new CreateAuthorView("Author X");
        var reqJson = objectMapper.writeValueAsString(reqObj);

        var result = mockMvc.perform(post("/authors")
                        .content(reqJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resJson = result.getResponse().getContentAsString();
        SimpleAuthorView resObj = objectMapper.readValue(resJson, SimpleAuthorView.class);

        assertNotNull(resObj);
        assertNotNull(resObj.getId());
        assertEquals(resObj.getName(), "Author X");
    }
}
