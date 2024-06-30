package forum.hub.api.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND FUNCTION('YEAR', t.dataCriacao) = :ano")
    Page<Topico> findByCursoAndAno(@Param("curso") Curso curso, @Param("ano") Integer ano, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.curso = :curso")
    Page<Topico> findByCurso(@Param("curso") Curso curso, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE FUNCTION('YEAR', t.dataCriacao) = :ano")
    Page<Topico> findByAno(@Param("ano") Integer ano, Pageable pageable);

}
