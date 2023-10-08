package security.xauthtoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.xauthtoken.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(long id);

}
