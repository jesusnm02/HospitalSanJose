package hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hospital.entity.DatoGeneral;
import hospital.entity.Paciente;
import hospital.repository.PacienteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PacienteService {

	@Autowired
	PacienteRepository pacienteRepository;
	
	public Paciente findPacienteByDni(String dni) {
		return pacienteRepository.findByDatoGeneral_Dni(dni);
	}
	
	public boolean existsPacienteByDni(String dni) {
		return pacienteRepository.existsByDatoGeneral_Dni(dni);
	}
	
	@Transactional
    public Paciente savePaciente(DatoGeneral datoGeneral) {
        Paciente paciente = new Paciente();
        paciente.setDatoGeneral(datoGeneral);
        return pacienteRepository.save(paciente);
    }
}
