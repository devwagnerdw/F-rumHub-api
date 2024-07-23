package forum.hub.api.controller;

import forum.hub.api.domain.topico.*;
import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.service.TopicoService;
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
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar(
            @RequestBody @Valid DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal Usuario logado
    ) {
        Topico topico = topicoService.criarTopico(dados, logado);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados, @AuthenticationPrincipal Usuario logado) {
        Optional<Topico> topicoOptional = topicoService.buscarTopicoPorId(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoOptional.get();
        if (!topico.getUsuario().getId().equals(logado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        topicoService.atualizarTopico(topico, dados);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoService.buscarTopicoPorId(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        topicoService.excluirTopico(topicoOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<DadosListagemTopico> listar(
            @AuthenticationPrincipal Usuario logado,
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        return topicoService.listarTopicosPorUsuarioId(logado.getId(), paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemTopico> obterTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoService.buscarTopicoPorId(id);
        return topicoOptional
                .map(topico -> ResponseEntity.ok(topicoService.criarListagemTopico(topico)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
