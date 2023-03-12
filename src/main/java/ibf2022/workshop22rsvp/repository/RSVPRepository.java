package ibf2022.workshop22rsvp.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.workshop22rsvp.model.RSVP;

@Repository
public class RSVPRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    String countSQL = "select count(*) from rsvp";
    String selectAllSQL = "select * from rsvp";
    String selectByIdSQL = "select * from rsvp where id = ?";
    String selectByNameSQL = "select * from rsvp where full_name like ?";
    String insertSQL = "insert into rsvp (full_name, email, phone, confirmation_date, comments) values (?, ?, ?, ?, ?)";
    String updateSQL = "update rsvp " +
                "set full_name = ?, email = ?, phone = ?, " +
                "confirmation_date = ?, comments = ?" +
                "where id = ?";

    // #1
    public Integer countAll() {
        return jdbcTemplate.queryForObject(countSQL, Integer.class);
    }

    // #2 Get All RSVPs
    public List<RSVP> findAll() {
        return jdbcTemplate.query(selectAllSQL, BeanPropertyRowMapper.newInstance(RSVP.class));
    }

    // #3 Get RSVP by ID
    public RSVP selectById(Integer id) {
        return jdbcTemplate.queryForObject(selectByIdSQL, BeanPropertyRowMapper.newInstance(RSVP.class), id);
    }

    // #4 Get RSVP by Name
    public RSVP findByName(String fullname) {
        return jdbcTemplate.queryForObject(selectByNameSQL, BeanPropertyRowMapper.newInstance(RSVP.class), fullname);
    }

    // #5 Save a new RSVP
    public Boolean save(RSVP rsvp) {
        Integer iResult = jdbcTemplate.update(insertSQL, rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments());
        return iResult > 0 ? true : false;
    }

    // #6 Single update existing RSVP
    public Boolean update(RSVP rsvp) {
        Integer iResult = 0;
        iResult = jdbcTemplate.update(updateSQL, rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments(), rsvp.getId());
        return iResult > 0 ? true : false;
    }

    // #7 Batch update - take note of return which is the size of the update / an integer array
    public int[] batchUpdate(List<RSVP> rsvp) {

        return jdbcTemplate.batchUpdate(insertSQL, new BatchPreparedStatementSetter() {
            
            // Sets / updates values
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, rsvp.get(i).getFullName());
                ps.setString(2, rsvp.get(i).getEmail());
                ps.setString(3, rsvp.get(i).getPhone());
                ps.setDate(4, rsvp.get(i).getConfirmationDate());
                ps.setString(5, rsvp.get(i).getComments());
            }

            // Returns size of update
            public int getBatchSize() {
                return rsvp.size();
            }
        });    
    }
}
