package lotto_challenge.database;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class DBConnectionUtilTest {

    @DisplayName("DB Connection 테스트")
    @Test
    void whenGetConnection_thenSuccess() {
        //When
        Connection connection = DBConnectionUtil.getConnection();

        //Then
        Assertions.assertThat(connection).isNotNull();
    }
}