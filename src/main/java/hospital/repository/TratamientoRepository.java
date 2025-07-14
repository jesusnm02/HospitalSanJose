package hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hospital.entity.TratamientoEntity;

@Repository
public interface TratamientoRepository extends JpaRepository<TratamientoEntity, Integer>{

}
