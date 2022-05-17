package grpc.comm.db;

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
            if (rowsAffected == 1) return Connector.QueryStatus.SUCCESS;
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
            if (resultSet == null) return true;
            resultSet.last();
            if (resultSet.getRow() == 0) return true;
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
            if (resultSet == null) return false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}