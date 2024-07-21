package forum.hub.api.controller;

import forum.hub.api.domain.topico.*;
import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar(
            @RequestBody @Valid DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal Usuario logado
    ) {
        Topico topico = new Topico(dados, logado);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public Page<DadosListagemTopico> listar(
            @AuthenticationPrincipal Usuario logado,
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        return topicoRepository.findByUsuarioId(logado.getId(), paginacao).map(DadosListagemTopico::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados, @AuthenticationPrincipal Usuario logado) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    if (!topico.getUsuario().getId().equals(logado.getId())) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para atualizar este tópico.");
                    }
                    topico.atualizarInformacoes(dados);
                    return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> {
                    topicoRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> ResponseEntity.ok(new DadosDetalhamentoTopico(topico)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
