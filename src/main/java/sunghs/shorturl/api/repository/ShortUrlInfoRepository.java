package sunghs.shorturl.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sunghs.shorturl.api.model.entity.ShortUrlInfo;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ShortUrlInfoRepository extends JpaRepository<ShortUrlInfo, Long> {

    Optional<ShortUrlInfo> findByOriginalUrlAndExpireDtGreaterThan(final String originalUrl, final LocalDateTime expireDt);

    Optional<ShortUrlInfo> findByShortUrlAndExpireDtGreaterThan(final String shortUrl, final LocalDateTime expireDt);
}
