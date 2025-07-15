package hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "recomendacion_de_ia")
@Data
public class RecomendacionDeIa {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ia")
    private int idIa;

    @Column(name = "recomendacion", nullable = false, length = 300)
    private String recomendacion;
}
