package com.interview.preparation.low_level_design.interview.otp_generator_validator.respository;

import com.interview.preparation.low_level_design.interview.otp_generator_validator.exception.InvalidUserException;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.model.OtpData;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.repository.impl.OtpRepositoryImpl;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.util.PostgreSQLClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtpRepositoryImplTest {
    @Mock
    private PostgreSQLClient postgreSQLClient;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private OtpRepositoryImpl otpRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        otpRepository = new OtpRepositoryImpl(postgreSQLClient);
    }

    @Test
    public void testSaveOTP() throws SQLException {
        OtpData data = new OtpData("12345", System.currentTimeMillis());

        Mockito.when(postgreSQLClient.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        otpRepository.saveOTP("user123", data);

        Mockito.verify(preparedStatement, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testGetOTP() throws SQLException, InvalidUserException {
        Long creationTime = System.currentTimeMillis();
        OtpData expectedOtpData = new OtpData("54321", creationTime);

        Mockito.when(postgreSQLClient.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getString("otp_code")).thenReturn("54321");
        Mockito.when(resultSet.getTimestamp("creation_time")).thenReturn(new Timestamp(creationTime));

        OtpData otpData = otpRepository.getOTP("user123");

        assertEquals(expectedOtpData.getCode(), otpData.getCode());
        assertEquals(expectedOtpData.getCreationTime(), otpData.getCreationTime());
    }

    @Test(expected = InvalidUserException.class)
    public void testGetOTP_InvalidUser() throws SQLException, InvalidUserException {
        Mockito.when(postgreSQLClient.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);

        Mockito.when(resultSet.next()).thenReturn(false);

        otpRepository.getOTP("nonexistent_user");
    }
}
