package com.interview.preparation.low_level_design.interview.otp_generator_validator.repository.impl;

import com.interview.preparation.low_level_design.interview.otp_generator_validator.exception.InvalidUserException;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.model.OtpData;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.repository.OtpRepository;
import com.interview.preparation.low_level_design.interview.otp_generator_validator.util.PostgreSQLClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OtpRepositoryImpl implements OtpRepository {
    private final PostgreSQLClient postgreSQLClient;
//    private final Map<String, OtpData> otpData = new HashMap<>();

    public OtpRepositoryImpl(PostgreSQLClient postgreSQLClient) {
        this.postgreSQLClient = postgreSQLClient;
    }

    public void saveOTP(String userId, OtpData data) {
//        otpData.put(userId, data);
        Connection connection = postgreSQLClient.getConnection();
        String insertSQL = "INSERT INTO otp_data (user_id, otp_code, creation_time) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, data.getCode());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(data.getCreationTime()));
            preparedStatement.addBatch();
            // Execute the SQL statement to insert the data
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User data inserted successfully.");
            } else {
                System.out.println("User data not inserted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgreSQLClient.close();
        }
    }

    public OtpData getOTP(String userId) throws InvalidUserException {
        Connection connection = postgreSQLClient.getConnection();
        String selectSQL = "SELECT otp_code, creation_time FROM otp_data WHERE user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String code = resultSet.getString("otp_code");
                    long creationTime = resultSet.getTimestamp("creation_time").getTime();
                    return new OtpData(code, creationTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgreSQLClient.close();
        }
        throw new InvalidUserException("provided user does not exist");
    }
}
