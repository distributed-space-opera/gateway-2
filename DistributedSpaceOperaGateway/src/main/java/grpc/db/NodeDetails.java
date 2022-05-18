package grpc.db;

import java.sql.*;

public class NodeDetails {

    public static Connector.QueryStatus addNewNode(String ip, String password) {
        try {
            String query = "INSERT INTO node_details (ip, password) VALUES (?, ?)";
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

    public static boolean checkIfNew(String ip) {
        try {
            String query = "Select ip FROM node_details WHERE ip = ?";
            Connection conn = Connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, ip);
            ResultSet resultSet = statement.executeQuery();
            statement.close();
            if (resultSet == null) {
                statement.close();
                return true;
            }
            resultSet.last();
            if (resultSet.getRow() == 0) {
                statement.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateNode(String ip, String password) {
        try {
            String query = "Select ip FROM node_details WHERE ip = ? and password = ?";
            Connection conn = Connector.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, ip);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                statement.close();
                return false;
            }
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
