package hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.OneToOne; 

@Entity
@Table(name = "doctor")
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private int idDoctor;

    @OneToOne
    @JoinColumn(name = "id_dato_general", referencedColumnName = "id_dato_general", nullable = false)
    private DatoGeneral datoGeneral;

    @ManyToMany
    @JoinTable(
        name = "especialidad_doctor",
        joinColumns = @JoinColumn(name = "id_doctor"),
        inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    private Set<Especialidad> especialidades = new HashSet<>(); 

    @ManyToMany
    @JoinTable(
        name = "doctor_sala",
        joinColumns = @JoinColumn(name = "id_doctor"),
        inverseJoinColumns = @JoinColumn(name = "id_sala_atencion")
    )
    private Set<SalaDeAtencion> salasDeAtencion = new HashSet<>();
}