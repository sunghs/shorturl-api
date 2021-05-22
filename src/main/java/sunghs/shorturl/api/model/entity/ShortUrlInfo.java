package sunghs.shorturl.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ShortUrlInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long seq;

    @Column(nullable = false, length = 512)
    private String originalUrl;

    @Column(length = 128)
    private String shortUrl;

    @Column(nullable = false)
    private long requestCount;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDt;

    @Column(nullable = false)
    private LocalDateTime expireDt;
}
