package lotto_challenge.repository;

import lotto_challenge.database.DBConnectionUtil;
import lotto_challenge.model.PurchasePrice;
import lotto_challenge.model.ReturnRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LottoStatisticRepository {

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
}
