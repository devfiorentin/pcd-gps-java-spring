package fiorentin.dev.pcd_gps.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import fiorentin.dev.pcd_gps.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClassRepository extends JpaRepository<User, Long> {
    List<User> findByPcdTrue(); // busca apenas os que têm pcd = true
}
