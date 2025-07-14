package hospital.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hospital.entity.DatoGeneral;
import hospital.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

	Optional<Paciente> findByDatoGeneral(DatoGeneral datoGeneral);
	Paciente findByDatoGeneral_Dni(String dni);
    boolean existsByDatoGeneral_Dni(String dni);
}
