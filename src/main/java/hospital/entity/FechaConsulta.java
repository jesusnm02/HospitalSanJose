package hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "fecha_consulta")
@Data
public class FechaConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fecha_consulta")
    private int idFechaConsulta;

    @Column(name = "sintomas")
    private String sintomas;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_final")
    private LocalDate fechaFinal;

    @Column(name = "resultados")
    private String resultados;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_doctor", nullable = false)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "id_sugerencias", nullable = false)
    private TratamientoEntity tratamiento;

    @ManyToMany
    @JoinTable(
        name = "fecha_recomendacion_ia",
        joinColumns = @JoinColumn(name = "id_fecha_consulta"),
        inverseJoinColumns = @JoinColumn(name = "id_ia")
    )
    private List<RecomendacionDeIa> recomendacionesIa = new ArrayList<>();
}