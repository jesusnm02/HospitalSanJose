package hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hospital.entity.RecomendacionDeIa;

@Repository
public interface RecomendacionDeIaRepository extends JpaRepository<RecomendacionDeIa, Integer>{

	Optional<RecomendacionDeIa> findByRecomendacion(String recomendacion);
}
