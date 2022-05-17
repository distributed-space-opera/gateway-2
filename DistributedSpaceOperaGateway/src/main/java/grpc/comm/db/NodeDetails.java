package grpc.comm.db;

import java.sql.*;

public class NodeDetails {

    public static Connector.QueryStatus addNewNode(String ip, String password) throws SQLException {
        String query = "INSERT INTO node_details (ip, password) VALUES (?, ?)";
        Connection conn = Connector.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, ip);
            statement.setString(2, password);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) return Connector.QueryStatus.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Connector.QueryStatus.FAILURE;
    }

    public static boolean checkIfNew(String ip) throws SQLException {
        String query = "Select ip FROM node_details WHERE ip = ?";
        Connection conn = Connector.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, ip);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) return true;
            resultSet.last();
            if (resultSet.getRow() == 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
