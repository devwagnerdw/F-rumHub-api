package forum.hub.api.controller;


import forum.hub.api.domain.comentario.*;
import forum.hub.api.domain.topico.Status;
import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.topico.TopicoRepository;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comentar")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping("/{id}")
    public ResponseEntity<DadosCadastroComentario> cadastrar(
            @PathVariable Long id,
            @RequestBody @Valid DadosCadastroComentario dados,
            UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal Usuario logado
    ){
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        if (topico.getStatus()!= Status.ABERTO) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        Comentario comentario = new Comentario(dados, logado, topico);

        comentarioRepository.save(comentario);

        URI uri = uriBuilder.path("/comentar/{id}").buildAndExpand(comentario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCadastroComentario(comentario));
    }
}