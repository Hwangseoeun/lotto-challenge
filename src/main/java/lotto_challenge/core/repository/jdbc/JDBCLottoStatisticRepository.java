package lotto_challenge.core.repository.jdbc;

import lotto_challenge.core.database.DBConnectionUtil;
import lotto_challenge.core.model.PurchasePrice;
import lotto_challenge.core.model.ReturnRate;
import lotto_challenge.core.repository.LottoStatisticRepository;
import lotto_challenge.core.service.dto.LottoStatisticInfoDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCLottoStatisticRepository implements LottoStatisticRepository {

    @Override
    public void save(final Long memberId, final PurchasePrice purchasePrice, final ReturnRate returnRate) {
        final String sql = "INSERT INTO lotto_statistic(member_id, purchase_price, return_rate) VALUES (?, ?, ?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId.toString());
            preparedStatement.setString(2, String.valueOf(purchasePrice.getValue()));
            preparedStatement.setString(3, String.valueOf(returnRate.getValue()));
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

    @Override
    public List<LottoStatisticInfoDto> findByMemberEmail(final String email) {
        final String sql =
            "SELECT ls.purchase_price, ls.return_rate " +
                "FROM lotto_statistic ls " +
                "JOIN member m ON ls.member_id = m.id " +
                "WHERE m.email = ?";

        List<LottoStatisticInfoDto> results = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                final int purchasePrice = resultSet.getInt("purchase_price");
                final float returnRate = resultSet.getFloat("return_rate");
                results.add(new LottoStatisticInfoDto(purchasePrice, returnRate));
            }

            return results;
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
