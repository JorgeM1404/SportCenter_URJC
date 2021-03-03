package aplicacion;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCentros extends JpaRepository<CentroDeportivo,Long>
{
	public Optional<CentroDeportivo> findByCampus(String campus);
	public void deleteByCampus(String campus);
}
