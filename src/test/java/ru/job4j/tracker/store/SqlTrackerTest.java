package ru.job4j.tracker.store;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }
    @Test
    public void whenReplaceItemThenMustReturnTrue() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        Item newItem = new Item("new item");
        assertThat(tracker.replace(item.getId(), newItem)).isTrue();
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo("new item");
    }

    @Test
    public void whenReplaceItemWithInvalidIdThenMustReturnFalse() {
        SqlTracker tracker = new SqlTracker(connection);
        Item newItem = new Item("new item");
        assertThat(tracker.replace(999, newItem)).isFalse();
    }

    @Test
    public void whenDeleteItemThenMustBeRemoved() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenFindAllThenMustReturnAllItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        tracker.add(item1);
        tracker.add(item2);
        List<Item> items = tracker.findAll();
        assertThat(items).containsExactlyInAnyOrder(item1, item2);
    }

    @Test
    public void whenFindByNameThenMustReturnCorrectItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        tracker.add(item1);
        tracker.add(item2);
        List<Item> foundItems = tracker.findByName("item1");
        assertThat(foundItems).containsExactly(item1);
    }

    @Test
    public void whenFindByNameThatDoesNotExistThenMustReturnEmptyList() {
        SqlTracker tracker = new SqlTracker(connection);
        List<Item> foundItems = tracker.findByName("nonexistent");
        assertThat(foundItems).isEmpty();
    }

    @Test
    public void whenFindByIdThenMustReturnCorrectItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenFindByIdThatDoesNotExistThenMustReturnNull() {
        SqlTracker tracker = new SqlTracker(connection);
        assertThat(tracker.findById(999)).isNull();
    }
}