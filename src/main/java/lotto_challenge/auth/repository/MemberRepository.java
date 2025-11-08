package lotto_challenge.auth.repository;

import lotto_challenge.auth.model.Member;
import lotto_challenge.database.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberRepository {

    public void save(final Member member) {
        final String sql = "INSERT INTO member(email) VALUES (?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getEmail());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            }
            catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
