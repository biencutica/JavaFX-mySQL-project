package repository;

import domain.Meal;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Meal> getAll() {
        ArrayList<Meal> cakes = new ArrayList<>();
        try {
            openConnection();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Meal;"); ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Meal d = new Meal(rs.getString("name"), rs.getInt("cooking_time"), rs.getString("ingredients"));
                    cakes.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return cakes;
    }

    public void addItem(Meal report) {
        try {
            openConnection();
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Meal VALUES (?, ?, ?);")) {
                ps.setString(1, report.getName());
                ps.setInt(2, report.getCooking_time());
                ps.setString(3, report.getIngredients());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
