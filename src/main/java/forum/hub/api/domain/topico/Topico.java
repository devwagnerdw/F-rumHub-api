package forum.hub.api.domain.topico;

import forum.hub.api.domain.comentario.Comentario;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;


@Table(name="topicos")
@Entity(name="Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private Date dataCriacao;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Curso curso;
    private int respostas;

    @ManyToOne
    @JoinColumn(name=" usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    public Topico(DadosCadastroTopico dados, Usuario usuario) {
        this.titulo= dados.titulo();
        this.mensagem=dados.mensagem();
        this.curso=dados.curso();
        this.dataCriacao = new Date();
        this.status = Status.ABERTO;
        this.respostas=0;
        this.usuario = usuario;
    }


    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }
        if (dados.status()!= null){
            this.status=dados.status();
        }

    }
}
