
package org.example.repo;
import org.example.model.Enrolled;
import org.example.model.Exceptions.DatabaseException;
import org.example.model.PastVocabMistakes;
import org.example.model.Book;
import org.example.model.Reading;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PastVocabMistakesDBRepository extends DBRepository<PastVocabMistakes> {
    public PastVocabMistakesDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(PastVocabMistakes obj) {
        String sql = "INSERT INTO pastvocabmistakes(id, studentId, wordId) " +
                "  VALUES(?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getStudent());
            statement.setInt(3, obj.getWord());
            statement.execute();
        } catch (SQLException ex) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public PastVocabMistakes read(int id) {
        String sql = "SELECT * FROM pastvocabmistakes WHERE id = ?";

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
    public void update(PastVocabMistakes obj) {
        String sql = "UPDATE pastvocabmistakes SET studentId = ?, "
                + " wordId = ? WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getStudent());
            statement.setInt(2, obj.getWord());
            statement.setInt(3, obj.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }

    }

    @Override
    public void delete(int id){
        String sql = "DELETE FROM pastvocabmistakes WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public List<PastVocabMistakes> getAll(){
        String sql = "SELECT * FROM pastvocabmistakes";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<PastVocabMistakes> idkanymore = new ArrayList<>();
            while(resultSet.next()){
                idkanymore.add(extractFromResultSet(resultSet));
            }
            return idkanymore;
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    private PastVocabMistakes extractFromResultSet(ResultSet resultSet) throws SQLException {
        PastVocabMistakes thing=new PastVocabMistakes(resultSet.getInt("id"),resultSet.getInt("studentId"),resultSet.getInt("wordId"));
        return thing;
    }
}

