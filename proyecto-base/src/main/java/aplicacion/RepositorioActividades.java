package aplicacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioActividades extends JpaRepository<Actividad,Long> {
	Actividad findByNombre(String nombre);
	//List<Actividad> findByNombreOrderByPlazas(String nombre);
}
