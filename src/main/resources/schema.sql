CREATE DATABASE IF NOT EXISTS shorturl;

CREATE TABLE IF NOT EXISTS shorturl.short_url_info (
    seq int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '테이블 sequence',
    original_url varchar(512) NOT NULL COMMENT '원본 URL',
    short_url varchar(128) COMMENT '단축 URL',
    request_count int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '요청 횟수',
    create_dt datetime NOT NULL COMMENT '생성시간',
    expire_dt datetime NOT NULL COMMENT '만료시간',
    PRIMARY KEY(seq),
    INDEX idx_short_url_info_1 (original_url, short_url)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1000000 COMMENT='단축 URL 변환 정보 테이블';