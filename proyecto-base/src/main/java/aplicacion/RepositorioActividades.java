package aplicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioActividades extends JpaRepository<Actividad,String> {
	//Actividad findByNombre(String nombre);
	//List<Actividad> findByNombreOrderByPlazas(String nombre);
}
