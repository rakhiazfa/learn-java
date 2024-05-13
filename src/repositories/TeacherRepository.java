package repositories;

import core.Database;
import core.ServiceContainer;
import models.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeacherRepository {
    private final Database database;

    public TeacherRepository() {
        this.database = ServiceContainer.get("database");
    }

    public ArrayList<Teacher> getAll() throws SQLException {
        ArrayList<Teacher> teachers = new ArrayList<>();

        Statement statement = this.database.getConnection().createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM teachers");

        while (result.next()) {
            teachers.add(new Teacher(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            ));
        }

        return teachers;
    }

    public boolean create(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teachers (name, subject, email) VALUES (?, ?, ?)";
        PreparedStatement statement = this.database.getConnection().prepareStatement(sql);

        statement.setString(1, teacher.name());
        statement.setString(2, teacher.subject());
        statement.setString(3, teacher.email());

        return statement.executeUpdate() > 0;
    }

    public Teacher getById(int id) throws SQLException {
        String sql = "SELECT * FROM teachers WHERE id = ?";
        PreparedStatement statement = this.database.getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        if(result.next()) {
            return new Teacher(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }

        return null;
    }

    public boolean update(Teacher teacher) throws SQLException {
        String sql = "UPDATE teachers SET name = ?, subject = ?, email = ? WHERE id = ?";
        PreparedStatement statement = this.database.getConnection().prepareStatement(sql);

        statement.setString(1, teacher.name());
        statement.setString(2, teacher.subject());
        statement.setString(3, teacher.email());
        statement.setInt(4, teacher.id());

        return statement.executeUpdate() > 0;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM teachers WHERE id = ?";
        PreparedStatement statement = this.database.getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        return statement.executeUpdate() > 0;
    }
}
