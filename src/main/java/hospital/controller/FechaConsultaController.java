package hospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import hospital.dto.Mensaje;
import hospital.entity.FechaConsulta;
import hospital.service.FechaConsultaService;

@Controller
@RequestMapping("/fechaConsulta")
@CrossOrigin(origins = "https://frontendhospitalsanjose.web.app")
public class FechaConsultaController {

	@Autowired
	FechaConsultaService fechaConsultaService;
	
	@PostMapping("/saveConsulta")
	public ResponseEntity<?> saveConsultaFecha(@RequestBody FechaConsulta request) {
		try {
            FechaConsulta nuevaFechaConsulta = fechaConsultaService.crearFechaConsultaCompleta(
                    request,
                    request.getTratamiento(),
                    request.getRecomendacionesIa(),
                    request.getPaciente().getIdPaciente(),
                    request.getDoctor().getIdDoctor()
            );
            
            return new ResponseEntity<>(new Mensaje("Consulta guardada exitosamente"), HttpStatus.CREATED);

		} catch (RuntimeException e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al guardar la consulta: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@PutMapping("/updateConsulta")
	public ResponseEntity<Mensaje> updateConsultaFecha(@RequestBody FechaConsulta request) {
		try {
			if(!fechaConsultaService.actualizarFechaConsultaYTratamiento(request))
            	return new ResponseEntity<>(new Mensaje("Error, No se pudo editar"), HttpStatus.NOT_FOUND);

			return new ResponseEntity<>(new Mensaje("Consulta editada exitosamente"), HttpStatus.CREATED);

		} catch (RuntimeException e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al guardar la consulta: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/getFechaConsulta/{dni}")
	public ResponseEntity<?> getFechaConsultaByDni(@PathVariable("dni") String dni) {
		Optional<List<FechaConsulta>> listFeOptional = fechaConsultaService.getFechaConsultaByDni(dni);
		if(listFeOptional.get().isEmpty())
			return new ResponseEntity<Mensaje>(new Mensaje("No existe reportes del paciente con el dni: " + dni), HttpStatus.NOT_FOUND);
		List<FechaConsulta> listFe = listFeOptional.get();
		return new ResponseEntity<List<FechaConsulta>>(listFe, HttpStatus.OK);
	}
	
	@GetMapping("/getFechaConsultaByPaciente/{idPaciente}")
	public ResponseEntity<?> getFechaConsultaByIdPaciente(@PathVariable("idPaciente") int idPaciente) {
		Optional<List<FechaConsulta>> listFeOptional = fechaConsultaService.getFechaConsultaByIdPaciente(idPaciente);
		if(listFeOptional.get().isEmpty())
			return new ResponseEntity<Mensaje>(new Mensaje("No existe reportes del paciente con el dni: " + idPaciente), HttpStatus.NOT_FOUND);
		List<FechaConsulta> listFe = listFeOptional.get();
		return new ResponseEntity<List<FechaConsulta>>(listFe, HttpStatus.OK);
	}
}
