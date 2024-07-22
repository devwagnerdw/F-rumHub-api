package forum.hub.api.service;

import forum.hub.api.domain.comentario.Comentario;
import forum.hub.api.domain.comentario.ComentarioRepository;
import forum.hub.api.domain.topico.DadosListagemTopico;
import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private final TopicoRepository topicoRepository;

    @Autowired
    private final ComentarioRepository comentarioRepository;

    public TopicoService(TopicoRepository topicoRepository, ComentarioRepository comentarioRepository) {
        this.topicoRepository = topicoRepository;
        this.comentarioRepository = comentarioRepository;
    }

    public Optional<Topico> buscarTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public DadosListagemTopico criarListagemTopico(Topico topico) {
        List<Comentario> comentarios = comentarioRepository.findByTopico(topico);
        return new DadosListagemTopico(topico);
    }
}