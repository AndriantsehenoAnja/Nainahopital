
package repository;

import model.UtilisateurRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRoleRepository extends JpaRepository<UtilisateurRole, Integer> {

    UtilisateurRole findByIdUser(Integer idUser);

    List<UtilisateurRole> findByIdRole(Integer idRole);

    boolean existsByIdUserAndIdRole(Integer idUser, Integer idRole);
}
