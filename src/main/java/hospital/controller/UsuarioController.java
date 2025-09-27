package hospital.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hospital.dto.Mensaje;
import hospital.dto.UsuarioDto;
import hospital.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
@CrossOrigin(origins = "https://frontendhospitalsanjose.web.app")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/detail/{dni}")
	public ResponseEntity<?> getUserByDni(@PathVariable("dni") String dni) {
		Optional<UsuarioDto> usuOptional = usuarioService.getUsuarioInfoByDni(dni);
		if(usuOptional.isEmpty()) {
			return new ResponseEntity<Mensaje>(new Mensaje("El usuario con el dni: " + dni + " no existe"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UsuarioDto>(usuOptional.get(), HttpStatus.OK);
	}
	
	@GetMapping("exists/{dni}")
	public ResponseEntity<Boolean> existsByDni(@PathVariable("dni") String dni) {
		Optional<UsuarioDto> usuOptional = usuarioService.getUsuarioInfoByDni(dni);
		if(usuOptional.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
