package serviciointerno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPistas extends JpaRepository<PistaDeportiva,Long>
{
	Optional<PistaDeportiva> findByPista(String pista);
	void removeByPista(String pista);
}
