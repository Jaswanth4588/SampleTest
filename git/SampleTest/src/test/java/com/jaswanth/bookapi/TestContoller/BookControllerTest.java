package com.jaswanth.bookapi.TestContoller;

import com.jaswanth.bookapi.controller.BookController;
import com.jaswanth.bookapi.exception.ResourceNotFoundException;
import com.jaswanth.bookapi.model.Book;
import com.jaswanth.bookapi.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testGetBookById_NotFound() throws Exception {
        when(bookService.get(1L)).thenThrow(new ResourceNotFoundException("Book not found with ID: 1"));

        mockMvc.perform(get("/book/edit/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())  // Expect a redirect
                .andExpect(redirectedUrl("/books"))  // Expect to be redirected to /books
                .andExpect(flash().attributeExists("message"));  // Check for flash message
    }

    @Test
    public void testDeleteBook_Success() throws Exception {
        doNothing().when(bookService).delete(1L);

        mockMvc.perform(delete("/book/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("message", "The Book ID 1 has been deleted."));
    }

    @Test
    public void testDeleteBook_NotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Book not found with ID: 1")).when(bookService).delete(1L);

        mockMvc.perform(delete("/book/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("message"));
    }

}

