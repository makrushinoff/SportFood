package ua.strongBody.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.strongBody.assembly.CustomerAssembly;
import ua.strongBody.dao.CustomerDAO;
import ua.strongBody.models.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerAssembly customerAssembly;

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate, CustomerAssembly customerAssembly) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerAssembly = customerAssembly;
    }

    @Override
    public List<Customer> findAll() throws DataAccessException {
        return customerAssembly.findAllCustomers();
    }

    @Override
    public void save(Customer customer) throws DataAccessException {
        jdbcTemplate.update("INSERT INTO customer (id, email, username, password, first_name, last_name, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)",
                customer.getId(),
                customer.getEmail(),
                customer.getUsername(),
                customer.getPassword(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber()
        );
    }

    @Override
    public void saveWithoutId(Customer customer) throws DataAccessException {
        jdbcTemplate.update("INSERT INTO customer (email, username, password, first_name, last_name, phone_number) VALUES (?, ?, ?, ?, ?, ?)",
                customer.getEmail(),
                customer.getUsername(),
                customer.getPassword(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber()
        );
    }

    @Override
    public void updateById(UUID id, Customer customer) {
        jdbcTemplate.update("UPDATE customer SET email = ?, username = ?, password = ?, first_name = ?, last_name = ?, phone_number = ? WHERE id = ?",
                customer.getEmail(),
                customer.getUsername(),
                customer.getPassword(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                id);
    }

    @Override
    public void deleteById(UUID customerId) {
        jdbcTemplate.update("DELETE FROM customer WHERE id = ?", customerId);
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return findAll().stream()
                .filter(customer -> isEqualsById(id, customer))
                .findFirst();
    }

    private boolean isEqualsById(UUID id, Customer customer) {
        return customer.getId().equals(id);
    }

    @Override
    public Optional<Customer> findFirstByUsername(String username) {
        return findAll().stream()
                .filter(customer -> isEqualsByUsername(username, customer))
                .findFirst();
    }

    private boolean isEqualsByUsername(String username, Customer customer) {
        return customer.getUsername().equals(username);
    }
}


