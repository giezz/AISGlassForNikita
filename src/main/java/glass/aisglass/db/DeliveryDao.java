package glass.aisglass.db;

import glass.aisglass.models.Delivery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeliveryDao {

    public static void create(Delivery delivery) {
        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(Queries.DELIVERY_INSERT.QUERY)) {
            preparedStatement.setDouble(1, delivery.getPrice_of_delivery());
            preparedStatement.setString(2, delivery.getAddress_of_delivery());
            preparedStatement.setString(3, delivery.getDescription_of_delivery());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Delivery> getAll() {
        try (Statement statement = DataSource.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(Queries.DELIVERY_GET_ALL.QUERY);
            ObservableList<Delivery> deliveryObservableList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                deliveryObservableList.add(
                        new Delivery(
                                resultSet.getInt(1),
                                resultSet.getDouble(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        )
                );
            }
            return deliveryObservableList;
        } catch (SQLException e) {
            e.printStackTrace();
            return FXCollections.emptyObservableList();
        }
    }

    public static ObservableList<Delivery> getByParams(String sql) {
        try (Statement statement = DataSource.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            ObservableList<Delivery> deliveryObservableList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                deliveryObservableList.add(
                        new Delivery(
                                resultSet.getInt(1),
                                resultSet.getDouble(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        )
                );
            }
            return deliveryObservableList;
        } catch (SQLException e) {
            e.printStackTrace();
            return FXCollections.emptyObservableList();
        }
    }

    public static Delivery get(int key) {
        Delivery delivery = new Delivery();
        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(Queries.DELIVERY_GET_BY_ID.QUERY)) {
            preparedStatement.setInt(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                delivery.setNumber_of_delivery(resultSet.getInt(1));
                delivery.setPrice_of_delivery(resultSet.getDouble(2));
                delivery.setAddress_of_delivery(resultSet.getString(3));
                delivery.setDescription_of_delivery(resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return delivery;
    }

    public static void update(Delivery delivery) {
        try (PreparedStatement preparedStatement = DataSource.getConnection().prepareStatement(Queries.DELIVERY_UPDATE.QUERY)) {
            preparedStatement.setDouble(1, delivery.getPrice_of_delivery());
            preparedStatement.setString(2, delivery.getAddress_of_delivery());
            preparedStatement.setString(3, delivery.getDescription_of_delivery());
            preparedStatement.setInt(4, delivery.getNumber_of_delivery());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int key) {
        try (PreparedStatement statement = DataSource.getConnection().prepareStatement(Queries.DELIVERY_DELETE.QUERY)) {
            statement.setInt(1, key);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
