package hospital.entity;

import enums.RolNombre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "dato_general")
@Data
public class DatoGeneral {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dato_general")
	private int idDatoGeneral;

	@Column(name = "dni", nullable = false, length = 8)
	private String dni;

	@Column(name = "nombres", nullable = false, length = 45)
	private String nombres;

	@Column(name = "ape_pa", nullable = false, length = 45)
	private String apePa;

	@Column(name = "ape_ma", nullable = false, length = 45)
	private String apeMa;

	@Column(name = "direccion", nullable = false, length = 45)
	private String direccion;

	@Column(name = "telefono", length = 15)
	private String telefono;

	@Column(name = "correo_email", length = 45)
	private String correoEmail;

	@Column(name = "edad", nullable = false)
	private int edad;

	@Column(name = "genero", nullable = false, length = 1)
	private char genero;

	@Column(name = "usuario", nullable = false, length = 5)
	@Enumerated(EnumType.STRING)
	private RolNombre usuario;
}