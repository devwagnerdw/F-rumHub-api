package forum.hub.api.domain.comentario;

import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;


    public Comentario(DadosCadastroComentario dados, Usuario usuario, Topico topico) {
        this.texto = dados.texto();
        this.usuario = usuario;
        this.topico = topico;
    }
}