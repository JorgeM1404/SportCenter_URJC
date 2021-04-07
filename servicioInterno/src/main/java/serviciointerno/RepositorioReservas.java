package serviciointerno;

import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioReservas extends JpaRepository<Reserva,Long>
{
	Optional<Reserva> findByFecha(Date date);
	void deleteByFecha(Date date);
}
