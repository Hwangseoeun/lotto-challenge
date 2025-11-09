/* member 테이블 생성 쿼리 */
CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(30) NOT NULL UNIQUE
);

/* lotto_statistic 테이블 생성 쿼리 */
CREATE TABLE lotto_statistic (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_price INT NOT NULL,
    return_rate FLOAT NOT NULL,
    member_id BIGINT NOT NULL,
    CONSTRAINT fk_lotto_statistic_member
        FOREIGN KEY (member_id)
        REFERENCES member (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);