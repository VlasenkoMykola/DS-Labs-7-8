package ua.knu.vlasenko;
import ua.knu.vlasenko.Dealer;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealerSQL implements Dealer {
    private static final String JDBC_URL = "jdbc:sqlite:dealer.lite";

    public DealerSQL() throws RemoteException {
    }

    @Override
    public boolean createMaker(String name) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Maker (name) VALUES (?)")) {

            preparedStatement.setString(1, name);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeMaker(String name) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Maker WHERE name = ?")) {

            preparedStatement.setString(1, name);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addModel(String makerName, String modelName, int year, int price) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Dealer (modelname, makername, year, price) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, modelName);
            preparedStatement.setString(2, makerName);
            preparedStatement.setInt(3, year);
            preparedStatement.setInt(4, price);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeModel(String modelName) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Dealer WHERE modelname = ?")) {

            preparedStatement.setString(1, modelName);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getMakerNames() throws RemoteException {
        List<String> makerNames = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT DISTINCT makername FROM Dealer")) {

            while (resultSet.next()) {
                makerNames.add(resultSet.getString("makername"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return makerNames;
    }

    @Override
    public int getNumberOfMakers() throws RemoteException {
        int numberOfMakers = 0;
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(DISTINCT makername) AS count FROM Dealer")) {

            if (resultSet.next()) {
                numberOfMakers = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfMakers;
    }

    @Override
    public int getNumberOfModels(String makerName) throws RemoteException {
        int numberOfModels = 0;
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS count FROM Dealer WHERE makername = ?");
             ) {

            preparedStatement.setString(1, makerName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                numberOfModels = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfModels;
    }

    @Override
    public List<String> getModelList() throws RemoteException {
        List<String> modelList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT modelname, makername FROM Dealer")) {

            while (resultSet.next()) {
                String modelName = resultSet.getString("modelname");
                String makerName = resultSet.getString("makername");
                modelList.add(modelName + " " + makerName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelList;
    }

    public static Dealer connect() throws RemoteException {
	return new DealerSQL();
    }

    @Override
    public void done() throws RemoteException {
    }
}
