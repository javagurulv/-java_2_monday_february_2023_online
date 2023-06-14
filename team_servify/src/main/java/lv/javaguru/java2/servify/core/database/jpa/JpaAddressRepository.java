package lv.javaguru.java2.servify.core.database.jpa;

import lv.javaguru.java2.servify.core.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAddressRepository extends JpaRepository<Address, Long> {
}
