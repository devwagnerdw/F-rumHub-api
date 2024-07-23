package forum.hub.api.service;

import forum.hub.api.domain.comentario.Comentario;
import forum.hub.api.domain.comentario.ComentarioRepository;
import forum.hub.api.domain.topico.*;
import forum.hub.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Topico criarTopico(DadosCadastroTopico dados, Usuario usuario) {
        Topico topico = new Topico(dados, usuario);
        return topicoRepository.save(topico);
    }

    public void atualizarTopico(Topico topico, DadosAtualizacaoTopico dados) {
        topico.atualizarInformacoes(dados);
        topicoRepository.save(topico);
    }

    public void excluirTopico(Topico topico) {
        topicoRepository.delete(topico);
    }

    public Page<DadosListagemTopico> listarTopicosPorUsuarioId(Long usuarioId, Pageable paginacao) {
        return topicoRepository.findByUsuarioId(usuarioId, paginacao)
                .map(this::criarListagemTopico);
    }
}
