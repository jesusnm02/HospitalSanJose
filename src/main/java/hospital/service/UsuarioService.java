package hospital.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enums.RolNombre;
import hospital.dto.UsuarioDto;
import hospital.entity.DatoGeneral;
import hospital.entity.Doctor;
import hospital.entity.Paciente;
import hospital.repository.DatoGeneralRepository;
import hospital.repository.DoctorRepository;
import hospital.repository.PacienteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	DatoGeneralRepository datoGeneralRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	PacienteRepository pacienteRepository;
	
	public Optional<UsuarioDto> getUsuarioInfoByDni(String dni) {
        Optional<DatoGeneral> datoGeneralOpt = datoGeneralRepository.findByDni(dni);

        if (datoGeneralOpt.isEmpty()) {
            return Optional.empty();
        }

        DatoGeneral datoGeneral = datoGeneralOpt.get();

        RolNombre userType = datoGeneral.getUsuario();
        int idUsuario = 0;

        if (RolNombre.ADMIN.equals(userType)) {
            Optional<Doctor> doctorOpt = doctorRepository.findByDatoGeneral(datoGeneral);
            if (doctorOpt.isPresent()) {
                idUsuario = doctorOpt.get().getIdDoctor();
            } else {
                return Optional.empty();
            }
        } else if (RolNombre.USER.equals(userType)) {
            Optional<Paciente> pacienteOpt = pacienteRepository.findByDatoGeneral(datoGeneral);
            if (pacienteOpt.isPresent()) {
                idUsuario = pacienteOpt.get().getIdPaciente();
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }

        UsuarioDto usuarioDto = new UsuarioDto(
            idUsuario,
            datoGeneral.getNombres(),
            datoGeneral.getUsuario()
        );
        return Optional.of(usuarioDto);
    }
}
