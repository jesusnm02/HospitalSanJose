package hospital.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.OneToOne;   

@Entity
@Table(name = "paciente")
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private int idPaciente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dato_general", referencedColumnName = "id_dato_general", nullable = false)
    private DatoGeneral datoGeneral; 
}