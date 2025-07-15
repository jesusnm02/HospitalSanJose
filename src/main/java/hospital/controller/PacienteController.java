package hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enums.RolNombre;
import hospital.dto.Mensaje;
import hospital.entity.DatoGeneral;
import hospital.entity.Paciente;
import hospital.service.PacienteService;

@RestController
@RequestMapping("/paciente")
@CrossOrigin(origins = "https://hospitalsanjose-e3a0f.web.app")
public class PacienteController {

	@Autowired 
	PacienteService pacienteService;
	
	@GetMapping("/detail/{dni}")
	public ResponseEntity<?> getByDni(@PathVariable("dni") String dni) {
		if(!pacienteService.existsPacienteByDni(dni)) 
			return new ResponseEntity<Mensaje>(new Mensaje("El paciente no existe"), HttpStatus.NOT_FOUND);
		Paciente paciente = pacienteService.findPacienteByDni(dni);
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}
	
	@PostMapping("/guardarPaciente")
    public ResponseEntity<?> registrarPaciente(@RequestBody DatoGeneral datoGeneral) {
		String dni = datoGeneral.getDni();
		if(pacienteService.existsPacienteByDni(dni))
			return new ResponseEntity<Mensaje>(new Mensaje("El paciente con el DNI: " + dni + " ya existe"), HttpStatus.OK);
        try {
            Paciente nuevoPaciente = pacienteService.savePaciente(datoGeneral);
            return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
        } catch (RuntimeException e) {
            System.err.println("Error processing patient registration: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (Exception e) { 
            System.err.println("Unexpected error during patient registration: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/getIdPaciente/{dni}")
	public ResponseEntity<Integer> getIdPacienteByDni(@PathVariable("dni") String dni) {
		if(!pacienteService.existsPacienteByDni(dni)) 
			return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);
		Paciente paciente = pacienteService.findPacienteByDni(dni);
		return new ResponseEntity<Integer>(paciente.getIdPaciente(), HttpStatus.OK);
	}
}
