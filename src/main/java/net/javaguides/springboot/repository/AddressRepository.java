package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Address;
import net.javaguides.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByEmployee(Employee employee);
}
