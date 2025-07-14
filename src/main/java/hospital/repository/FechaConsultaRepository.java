package hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hospital.entity.FechaConsulta;

@Repository
public interface FechaConsultaRepository extends JpaRepository<FechaConsulta, Integer>{

	Optional<List<FechaConsulta>> findByPaciente_DatoGeneral_Dni(String dni);
	Optional<List<FechaConsulta>> findByPaciente_IdPaciente(int idPaciente);
}
