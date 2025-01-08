package org.example.repo;
import org.example.model.Exceptions.DatabaseException;

import org.example.model.ExamResult;
import org.example.model.PastMistakes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PastMistakesDBRepository extends DBRepository<PastMistakes> {
    public PastMistakesDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(PastMistakes obj) {
        String sql = "INSERT INTO pastmistakes(id, studentId, " +
                " readingId, grammarId) VALUES(?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getStudent());
            statement.setInt(3, obj.getReadingQuestion());
            statement.setInt(4, obj.getGrammarQuestion());
            statement.execute();
        } catch (SQLException ex) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public PastMistakes read(int id) {
        String sql = "SELECT * FROM pastmistakes WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return extractFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public void update(PastMistakes obj) {
        String sql = "UPDATE pastmistakes SET studentId= ?, "
                + " readingId = ?, grammarId= ? WHERE ID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getStudent());
            statement.setInt(2, obj.getReadingQuestion());
            statement.setInt(3, obj.getGrammarQuestion());
            statement.setInt(4, obj.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }

    }

    @Override
    public void delete(int id){
        String sql = "DELETE FROM pastmistakes WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public List<PastMistakes> getAll(){
        String sql = "SELECT * FROM pastmistakes";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<PastMistakes> mistakes = new ArrayList<>();
            while(resultSet.next()){
                mistakes.add(extractFromResultSet(resultSet));
            }
            return mistakes;
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    private PastMistakes extractFromResultSet(ResultSet resultSet) throws SQLException {
        PastMistakes mistakes=new PastMistakes(resultSet.getInt("id"),resultSet.getInt("studentId"),resultSet.getInt("readingId"),resultSet.getInt("grammarId"));
        return mistakes;
    }
}


