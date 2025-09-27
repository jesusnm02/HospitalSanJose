package hospital.service;

import hospital.entity.FechaConsulta;
import hospital.entity.TratamientoEntity;
import hospital.entity.RecomendacionDeIa;
import hospital.entity.Paciente;
import hospital.entity.Doctor;
import hospital.repository.FechaConsultaRepository;
import hospital.repository.RecomendacionDeIaRepository;
import hospital.repository.TratamientoRepository;
import hospital.repository.PacienteRepository;
import hospital.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class FechaConsultaService {

    @Autowired
    private FechaConsultaRepository fechaConsultaRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Autowired
    private RecomendacionDeIaRepository recomendacionDeIaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;


    @Transactional
    public FechaConsulta crearFechaConsultaCompleta(
            FechaConsulta fechaConsulta,
            TratamientoEntity nuevoTratamiento,
            List<RecomendacionDeIa> recomendacionesRecibidas,
            int pacienteId,
            int doctorId
    ) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(pacienteId);
        if (pacienteOptional.isEmpty()) {
            throw new RuntimeException("Paciente no encontrado con ID: " + pacienteId);
        }
        fechaConsulta.setPaciente(pacienteOptional.get());

        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isEmpty()) {
            throw new RuntimeException("Doctor no encontrado con ID: " + doctorId);
        }
        fechaConsulta.setDoctor(doctorOptional.get());

        TratamientoEntity tratamientoGuardado = tratamientoRepository.save(nuevoTratamiento);
        fechaConsulta.setTratamiento(tratamientoGuardado);

        List<RecomendacionDeIa> recomendacionesParaAsociar = new ArrayList<>();

        for (RecomendacionDeIa recRecibida : recomendacionesRecibidas) {
            // Guardamos directamente cada recomendación recibida
            RecomendacionDeIa recomendacionGuardada = recomendacionDeIaRepository.save(recRecibida);
            recomendacionesParaAsociar.add(recomendacionGuardada);
        }

        fechaConsulta.setRecomendacionesIa(recomendacionesParaAsociar);

        // Guardamos la fechaConsulta con todas sus asociaciones
        return fechaConsultaRepository.save(fechaConsulta);

    }
    
    public Optional<List<FechaConsulta>> getFechaConsultaByDni(String dni) {
    	return fechaConsultaRepository.findByPaciente_DatoGeneral_Dni(dni);
    }
    
    public Optional<List<FechaConsulta>> getFechaConsultaByIdPaciente(int idPaciente) {
    	return fechaConsultaRepository.findByPaciente_IdPaciente(idPaciente);
    }
    
    @Transactional
    public boolean actualizarFechaConsultaYTratamiento(FechaConsulta fechaConsultaActualizada) {
        Optional<FechaConsulta> fechaConsultaOptional = fechaConsultaRepository.findById(fechaConsultaActualizada.getIdFechaConsulta());
        if (fechaConsultaOptional.isEmpty()) {
            return false;
        }
        FechaConsulta existente = fechaConsultaOptional.get();
        existente.setSintomas(fechaConsultaActualizada.getSintomas());
        existente.setFechaInicio(fechaConsultaActualizada.getFechaInicio());
        existente.setFechaFinal(fechaConsultaActualizada.getFechaFinal());
        existente.setResultados(fechaConsultaActualizada.getResultados());

        TratamientoEntity tratamiento = fechaConsultaActualizada.getTratamiento();
        if (tratamiento != null) {
            TratamientoEntity nuevoTratamiento = new TratamientoEntity();
            nuevoTratamiento.setTexto(tratamiento.getTexto());
            nuevoTratamiento.setPrecio(tratamiento.getPrecio());

            TratamientoEntity guardado = tratamientoRepository.save(nuevoTratamiento);
            existente.setTratamiento(guardado);
        }
        fechaConsultaRepository.save(existente);
        return true;
    }
}