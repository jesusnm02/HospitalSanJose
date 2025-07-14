package hospital.dto;

import enums.RolNombre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDto {

	private int idUsuario;
	private String nomUsuario;
	private RolNombre userUsuario;
}
