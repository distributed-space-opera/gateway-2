package grpc.comm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" +
                            "admin:admin1234@distributed-space-opera.csq8e5ojo7vo.us-east-2.rds.amazonaws.com:3306" +
                            "/gateway2"
            );
        }
        return connection;
    }
}
