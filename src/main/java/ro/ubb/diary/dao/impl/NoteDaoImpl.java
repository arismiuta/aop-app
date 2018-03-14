package ro.ubb.diary.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ro.ubb.diary.dao.CustomerDao;
import ro.ubb.diary.dao.NoteDao;
import ro.ubb.diary.model.Customer;
import ro.ubb.diary.model.Note;
import ro.ubb.diary.observer.Subject;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class NoteDaoImpl extends JdbcDaoSupport implements NoteDao {


	Logger logger = LoggerFactory.getLogger(NoteDaoImpl.class);


	@Autowired
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public void insert(Note note) {
		logger.info("Adding note " + note);
		String sql = "INSERT INTO notes " +
				"(NOTE_ID, CUST_ID, DATE, TIME, TEXT) VALUES (?, ?, ?, ?, ?)" ;

		getJdbcTemplate().update(sql, note.getNoteId(), note.getCustId(), note.getDate(), note.getTime(), note.getText());
	}
//
//	@Override
//	public void inserBatch(List<Note> customers) {
//		String sql = "INSERT INTO customer " + "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
//		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
//			public void setValues(PreparedStatement ps, int i) throws SQLException {
//				Customer customer = customers.get(i);
//				ps.setLong(1, customer.getCustId());
//				ps.setString(2, customer.getName());
//				ps.setInt(3, customer.getAge());
//			}
//
//			public int getBatchSize() {
//				return customers.size();
//			}
//		});
//
//	}

	public List<Note> loadAllNotes(){
		logger.info("Query all notes");
		String sql = "SELECT * FROM notes";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Note> result = new ArrayList<>();
		for(Map<String, Object> row:rows){
			Note note = new Note();
			note.setDate((Date)row.get("date"));
			note.setTime((Time)row.get("time"));
			note.setText((String)row.get("text"));
			note.setCustId((Long)row.get("cust_id"));
			note.setNoteId((Long)row.get("note_id"));
			result.add(note);
		}

		return result;
	}
//
//	@Override
//	public Customer findCustomerById(long cust_id) {
//		String sql = "SELECT * FROM customer WHERE CUST_ID = ?";
//		return (Customer)getJdbcTemplate().queryForObject(sql, new Object[]{cust_id}, new RowMapper<Customer>(){
//			@Override
//			public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {
//				Customer cust = new Customer();
//				cust.setCustId(rs.getLong("cust_id"));
//				cust.setName(rs.getString("name"));
//				cust.setAge(rs.getInt("age"));
//				return cust;
//			}
//		});
//	}
//
//	@Override
//	public String findNameById(long cust_id) {
//		String sql = "SELECT name FROM customer WHERE cust_id = ?";
//		return getJdbcTemplate().queryForObject(sql, new Object[]{cust_id}, String.class);
//	}
//
//	@Override
//	public int getTotalNumberCustomer() {
//		String sql = "SELECT Count(*) FROM customer";
//		int total = getJdbcTemplate().queryForObject(sql, Integer.class);
//		return total;
//	}
//
//
}

