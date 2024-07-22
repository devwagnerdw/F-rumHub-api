package forum.hub.api.domain.comentario;

import forum.hub.api.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
    List<Comentario> findByTopico(Topico topico);
}
