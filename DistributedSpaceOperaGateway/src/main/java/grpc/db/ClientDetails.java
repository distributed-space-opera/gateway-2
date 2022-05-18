package grpc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDetails {
    public static Connector.QueryStatus addNewClient(String ip, String password) {
        try {
            String query = "INSERT INTO client_details (ip, password) VALUES (?, ?)";
            Connection conn = Connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, ip);
            statement.setString(2, password);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                statement.close();
                return Connector.QueryStatus.SUCCESS;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Connector.QueryStatus.FAILURE;
    }

    public static boolean validateClient(String ip, String password) {
        try {
            String query = "Select ip FROM client_details WHERE ip = ? and password = ?";
            Connection conn = Connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            statement.setString(1, ip);
            statement.setString(2, password);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                statement.close();
                return false;
            }

            resultSet.last();
            System.out.println(resultSet.getRow());
            if (resultSet.getRow() == 1) {
                statement.close();
                return true;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
