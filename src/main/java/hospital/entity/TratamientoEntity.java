package hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tratamiento")
@Data
public class TratamientoEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sugerencias")
    private int idSugerencias;

    @Column(name = "texto", length = 100)
    private String texto;

    @Column(name = "precio")
    private float precio;
}
