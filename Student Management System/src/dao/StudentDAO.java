package dao;
import java.sql.*;
import model.Student;
import util.DBConnection;

public class StudentDAO {

    public void addStudent(Student s) {
        String query =
                "INSERT INTO students(name,age,course) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getCourse());

            ps.executeUpdate();
            System.out.println("Student Added Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayStudents() {
        String query = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("course"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void searchStudent(int id) {
        String query = "SELECT * FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("course"));
            } else {
                System.out.println("Student Not Found");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateStudent(Student s) {
        String query =
                "UPDATE students SET name=?, age=?, course=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getCourse());
            ps.setInt(4, s.getId());

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student Updated");
            else
                System.out.println("Student Not Found");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student Deleted");
            else
                System.out.println("Student Not Found");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}