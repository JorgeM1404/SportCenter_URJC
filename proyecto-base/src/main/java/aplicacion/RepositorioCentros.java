package aplicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCentros extends JpaRepository<CentroDeportivo,String>
{
	//public Optional<CentroDeportivo> findByCampus(String campus);
	//public void deleteByCampus(String campus);
}
