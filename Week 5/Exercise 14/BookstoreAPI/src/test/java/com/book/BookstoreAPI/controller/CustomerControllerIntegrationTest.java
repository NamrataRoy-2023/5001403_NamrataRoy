package com.book.BookstoreAPI.controller;

import com.book.BookstoreAPI.model.Customer;
import com.book.BookstoreAPI.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        // Reset the mocks before each test
        Mockito.reset(customerService);
    }

    @Test
    void testCreateCustomer() throws Exception {
        Customer customer = new Customer(null, "Name1", "email@example.com", "Address1");
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Name1\", \"email\": \"email@example.com\", \"address\": \"Address1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Name1"))
                .andExpect(jsonPath("$.email").value("email@example.com"))
                .andExpect(jsonPath("$.address").value("Address1"));

        verify(customerService, times(1)).createCustomer(any(Customer.class));
    }

    @Test
    void testGetAllCustomers() throws Exception {
        Customer customer1 = new Customer(1L, "Name1", "email1@example.com", "Address1");
        Customer customer2 = new Customer(2L, "Name2", "email2@example.com", "Address2");
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Name1"))
                .andExpect(jsonPath("$[1].name").value("Name2"));

        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = new Customer(1L, "Name1", "email@example.com", "Address1");
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name1"))
                .andExpect(jsonPath("$.email").value("email@example.com"))
                .andExpect(jsonPath("$.address").value("Address1"));

        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer updatedCustomer = new Customer(1L, "Updated Name", "updatedemail@example.com", "Updated Address");
        when(customerService.updateCustomer(eq(1L), any(Customer.class))).thenReturn(updatedCustomer);

        mockMvc.perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Name\", \"email\": \"updatedemail@example.com\", \"address\": \"Updated Address\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("updatedemail@example.com"))
                .andExpect(jsonPath("$.address").value("Updated Address"));

        verify(customerService, times(1)).updateCustomer(eq(1L), any(Customer.class));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());

        verify(customerService, times(1)).deleteCustomer(1L);
    }

    @Test
    void testGetCustomersByNameAndEmail() throws Exception {
        Customer customer = new Customer(1L, "Name1", "email1@example.com", "Address1");
        when(customerService.getCustomers("Name1", "email1@example.com")).thenReturn(Arrays.asList(customer));

        mockMvc.perform(get("/customers/search?name=Name1&email=email1@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Name1"))
                .andExpect(jsonPath("$[0].email").value("email1@example.com"));

        verify(customerService, times(1)).getCustomers("Name1", "email1@example.com");
    }
}
