package security.xauthtoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.xauthtoken.entity.VerificationCodeEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCodeEntity, Long> {
    Optional<VerificationCodeEntity> findByCodeAndUserId(String code, long id);

    void deleteByCreatedOnBefore(LocalDateTime localDateTime);
}
