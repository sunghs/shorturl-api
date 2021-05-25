package sunghs.shorturl.api.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sunghs.shorturl.api.model.entity.ShortUrlInfo;

public interface ShortUrlInfoRepository extends JpaRepository<ShortUrlInfo, Long> {

    Optional<ShortUrlInfo> findByOriginalUrlAndExpireDtGreaterThan(final String originalUrl, final LocalDateTime expireDt);
}
