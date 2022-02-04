package ru.netology.page;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbInteractionDbUtils {

    @BeforeEach
    @SneakyThrows
    public static Connection getConnection() {
        final Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/app", "app", "pass");
        return connection;
    }


    @AfterEach
    @SneakyThrows
    public static String getCodeVerification() {
        String codeVerification;


        var codeSQL = "SELECT code  FROM auth_codes WHERE created = (SELECT max(created) FROM auth_codes)";
        var runner = new QueryRunner();

        try (Connection conn = getConnection();) {
            var code = runner.query(conn, codeSQL, new ScalarHandler<>());
            codeVerification = (String) code;
            return codeVerification;
        }
    }
@AfterAll
    @SneakyThrows
    public static void clean() {
        String cardsSQLClean = "DELETE FROM cards WHERE TRUE";
        String authCodeSQLClean = "DELETE FROM auth_codes WHERE TRUE";
        String usersSQLClean = "DELETE FROM auth_codes WHERE TRUE";
        var runner = new QueryRunner();

        try (Connection conn = getConnection();

        )
        {
            runner.update(conn, cardsSQLClean);
            runner.update(conn, authCodeSQLClean);
            runner.update(conn, usersSQLClean);
        }
    }

}



