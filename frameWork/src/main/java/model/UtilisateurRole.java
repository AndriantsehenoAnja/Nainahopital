
package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utilisateur_role")
@Getter
@Setter
public class UtilisateurRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "id_user")
    private Integer idUser;
}
