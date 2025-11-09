package lotto_challenge.repository;

import lotto_challenge.database.DBConnectionUtil;
import lotto_challenge.model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberRepository {

    public Long save(final Member member) {
        final String sql = "INSERT INTO member(email) VALUES (?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, member.getEmail());
            preparedStatement.executeUpdate();

            return getMemberId(preparedStatement);
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

    private Long getMemberId(final PreparedStatement preparedStatement) {
        try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return null;
    }

    public boolean existsByEmail(final String email) {
        final String sql ="SELECT COUNT(*) FROM member WHERE email = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

            return false;
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

    public Long findMemberIdByEmail(final String email) {
        final String sql ="SELECT id FROM member WHERE email = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }

            return null;
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
