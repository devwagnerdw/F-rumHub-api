package forum.hub.api.controller;


import forum.hub.api.topico.DadosCadastroTopico;
import forum.hub.api.topico.Topico;
import forum.hub.api.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/novotopico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroTopico dados){
        topicoRepository.save(new Topico(dados));

    }
}
