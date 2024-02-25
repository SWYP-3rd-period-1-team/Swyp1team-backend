package com.swig.zigzzang.connection;


import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
@SpringBootTest
public class DataConnectionTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException{ // DB 연결 test

        @Cleanup
        Connection conn = dataSource.getConnection();

        log.info(conn);

        Assertions.assertNotNull(conn);
    }
}
