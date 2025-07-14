package hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hospital.entity.DatoGeneral;

@Repository
public interface DatoGeneralRepository extends JpaRepository<DatoGeneral, Integer>{

	Optional<DatoGeneral> findByDni(String dni);
}
