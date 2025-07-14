package hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hospital.entity.DatoGeneral;
import hospital.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	Optional<Doctor> findByDatoGeneral(DatoGeneral datoGeneral);
    Doctor findByDatoGeneral_Dni(String dni);
    boolean existsByDatoGeneral_Dni(String dni);
}
