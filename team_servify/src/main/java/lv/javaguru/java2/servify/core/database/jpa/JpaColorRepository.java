package lv.javaguru.java2.servify.core.database.jpa;

import lv.javaguru.java2.servify.core.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaColorRepository extends JpaRepository<Color, Long> {
}
