package forum.hub.api.service;

import forum.hub.api.domain.comentario.Comentario;
import forum.hub.api.domain.comentario.ComentarioRepository;
import forum.hub.api.domain.comentario.DadosComentario;
import forum.hub.api.domain.topico.*;
import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.infra.exception.TopicoNaoEncontradoException;
import forum.hub.api.infra.exception.UsuarioNaoAutorizadoException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<DadosComentario> comentariosConvertidos = comentarios.stream()
                .map(DadosComentario::new)
                .collect(Collectors.toList());

        return new DadosListagemTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getCurso(),
                topico.getRespostas(),
                topico.getUsuario().getId(),
                comentariosConvertidos);
    }

    public Topico criarTopico(DadosCadastroTopico dados, Usuario usuario) {
        Topico topico = new Topico(dados, usuario);
        return topicoRepository.save(topico);
    }

    @Transactional
    public Topico atualizarTopico(Long id, DadosAtualizacaoTopico dados, Usuario usuario) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoNaoEncontradoException("Tópico não encontrado"));

        if (!topico.getUsuario().getId().equals(usuario.getId())) {
            throw new UsuarioNaoAutorizadoException("Usuário não autorizado");
        }

        topico.atualizarInformacoes(dados);
        return topicoRepository.save(topico);
    }


    public void excluirTopico(Long id, Usuario usuario) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoNaoEncontradoException("Tópico não encontrado"));

        if (!topico.getUsuario().getId().equals(usuario.getId())) {
            throw new UsuarioNaoAutorizadoException("Usuário não autorizado");
        }
        topicoRepository.delete(topico);
    }

    public Page<DadosListagemTopico> listarTopicosPorUsuarioId(Long usuarioId, Pageable paginacao) {
        return topicoRepository.findByUsuarioId(usuarioId, paginacao)
                .map(this::criarListagemTopico);
    }
}
