package hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "sala_de_atencion")
@Data
public class SalaDeAtencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_sala_atencion")
    private int idSalaAtencion;

    @Column(name = "nom_sala", nullable = false, length = 45) 
    private String nomSala; 
}
