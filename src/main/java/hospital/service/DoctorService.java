package hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hospital.entity.Doctor;
import hospital.repository.DoctorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	
	public Doctor findDoctorByDni(String dni) {
		return doctorRepository.findByDatoGeneral_Dni(dni);
	}
	
	public boolean existsDoctorByDni(String dni) {
		return doctorRepository.existsByDatoGeneral_Dni(dni);
	}
}
