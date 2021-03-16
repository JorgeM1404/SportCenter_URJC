package aplicacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioActividades extends JpaRepository<Actividad,Long> {
	Optional <Actividad> findByNombre(String nombre);
	//List<Actividad> findByNombre(String nombre);
}
