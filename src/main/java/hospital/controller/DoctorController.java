package hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospital.dto.Mensaje;
import hospital.entity.Doctor;
import hospital.service.DoctorService;


@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	@GetMapping("/detail/{dni}")
	public ResponseEntity<?> getByDni(@PathVariable String dni) {
		if(!doctorService.existsDoctorByDni(dni))
			return new ResponseEntity<Mensaje>(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);
		Doctor doctor = doctorService.findDoctorByDni(dni);
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}
}
