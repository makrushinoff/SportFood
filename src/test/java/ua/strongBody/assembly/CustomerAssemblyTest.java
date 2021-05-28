package ua.strongBody.assembly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.strongBody.models.Customer;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerAssemblyTest {

    private static final String FIND_ALL_QUERY = "SELECT * FROM customer";

    private Customer customer;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private RowMapper<Customer> customerRowMapper;

    @InjectMocks
    private CustomerAssembly testInstance;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        List<Customer> customers = Collections.singletonList(customer);

        when(jdbcTemplate.query(FIND_ALL_QUERY, customerRowMapper)).thenReturn(customers);
    }

    @Test
    void shouldFindAllCustomers() {
        List<Customer> actual = testInstance.findAllCustomers();

        assertThat(actual).contains(customer);
    }
}
