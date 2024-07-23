package forum.hub.api.service;

import forum.hub.api.domain.comentario.Comentario;
import forum.hub.api.domain.comentario.ComentarioRepository;
import forum.hub.api.domain.comentario.DadosCadastroComentario;
import forum.hub.api.domain.topico.Status;
import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.topico.TopicoRepository;
import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.infra.exception.TopicoFechadoException;
import forum.hub.api.infra.exception.TopicoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public Comentario cadastrar(Long topicoId, DadosCadastroComentario dados, Usuario logado) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new TopicoNaoEncontradoException("Tópico com ID " + topicoId + " não encontrado"));

        if (topico.getStatus() != Status.ABERTO) {
            throw new TopicoFechadoException("Tópico não está mais aberto para comentários");
        }

        Comentario comentario = new Comentario(dados, logado, topico);

        return comentarioRepository.save(comentario);
    }
}