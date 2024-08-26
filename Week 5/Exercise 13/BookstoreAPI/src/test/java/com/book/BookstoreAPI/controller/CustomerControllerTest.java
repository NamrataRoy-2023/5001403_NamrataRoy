package com.book.BookstoreAPI.controller;

import com.book.BookstoreAPI.model.Customer;
import com.book.BookstoreAPI.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void createCustomer_ShouldReturnCreatedCustomer() throws Exception {
        Customer customer = new Customer(1L, "Monalisa Saha", "monalisa256@gmail.com", "Purba Bardhaman, West Bengal");

        given(customerService.createCustomer(any(Customer.class))).willReturn(customer);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Monalisa Saha\", \"email\": \"monalisa256@gmail.com\", \"address\": \"Purba Bardhaman, West Bengal\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Monalisa Saha")))
                .andExpect(jsonPath("$.email", is("monalisa256@gmail.com")))
                .andExpect(jsonPath("$.address", is("Purba Bardhaman, West Bengal")));
    }

    @Test
    void getAllCustomers_ShouldReturnListOfCustomers() throws Exception {
        Customer customer1 = new Customer(1L, "Monalisa Saha", "monalisa256@gmail.com", "Purba Bardhaman, West Bengal");
        Customer customer2 = new Customer(2L, "Aritra Hazra", "aritra02@gmail.com", "Kolkata, West Bengal");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        given(customerService.getAllCustomers()).willReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Monalisa Saha")))
                .andExpect(jsonPath("$[0].email", is("monalisa256@gmail.com")))
                .andExpect(jsonPath("$[0].address", is("Purba Bardhaman, West Bengal")))
                .andExpect(jsonPath("$[1].name", is("Aritra Hazra")))
                .andExpect(jsonPath("$[1].email", is("aritra02@gmail.com")))
                .andExpect(jsonPath("$[1].address", is("Kolkata, West Bengal")));
    }

    @Test
    void getCustomerById_ShouldReturnCustomer() throws Exception {
        Customer customer = new Customer(1L, "Monalisa Saha", "monalisa256@gmail.com", "Purba Bardhaman, West Bengal");

        given(customerService.getCustomerById(anyLong())).willReturn(Optional.of(customer));

        mockMvc.perform(get("/customers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Monalisa Saha")))
                .andExpect(jsonPath("$.email", is("monalisa256@gmail.com")))
                .andExpect(jsonPath("$.address", is("Purba Bardhaman, West Bengal")));
    }

    @Test
    void getCustomerById_WhenCustomerNotFound_ShouldReturn404() throws Exception {
        given(customerService.getCustomerById(anyLong())).willReturn(Optional.empty());

        mockMvc.perform(get("/customers/{id}", 3L))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCustomer_ShouldReturnUpdatedCustomer() throws Exception {
        Customer customer = new Customer(1L, "Monalisa Saha", "monalisa256@gmail.com", "Purba Bardhaman, West Bengal");

        given(customerService.updateCustomer(anyLong(), any(Customer.class))).willReturn(customer);

        mockMvc.perform(put("/customers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Monalisa Saha\", \"email\": \"monalisa256@gmail.com\", \"address\": \"Purba Bardhaman, West Bengal\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Monalisa Saha")))
                .andExpect(jsonPath("$.email", is("monalisa256@gmail.com")))
                .andExpect(jsonPath("$.address", is("Purba Bardhaman, West Bengal")));
    }

    @Test
    void deleteCustomer_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/customers/{id}", 1L))
                .andExpect(status().isNoContent());

        Mockito.verify(customerService).deleteCustomer(anyLong());
    }

    @Test
    void getCustomers_WithValidParams_ShouldReturnFilteredCustomers() throws Exception {
        Customer customer = new Customer(1L, "Monalisa Saha", "monalisa256@gmail.com", "Purba Bardhaman, West Bengal");
        List<Customer> customers = Arrays.asList(customer);

        given(customerService.getCustomers(anyString(), anyString())).willReturn(customers);

        mockMvc.perform(get("/customers/search")
                        .param("name", "Monalisa Saha")
                        .param("email", "monalisa256@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Monalisa Saha")))
                .andExpect(jsonPath("$[0].email", is("monalisa256@gmail.com")))
                .andExpect(jsonPath("$[0].address", is("Purba Bardhaman, West Bengal")));
    }

    @Test
    void getCustomers_WithNoMatchingParams_ShouldReturnNoContent() throws Exception {
        given(customerService.getCustomers(anyString(), anyString())).willReturn(Arrays.asList());

        mockMvc.perform(get("/customers/search")
                        .param("name", "Nonexistent Name")
                        .param("email", "nonexistent.email@example.com"))
                .andExpect(status().isNoContent());
    }
}
