package noti.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import noti.dao.MyNotiDAO;

public class DeleteMyNotiService {
	private MyNotiDAO mynotiDAO = new MyNotiDAO();
	
	public void delete(int notiNo) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			int noti = mynotiDAO.selectById(conn, notiNo);
			
			if (noti <= 0) {
				throw new NotificationNotFoundException();
			}
			
			mynotiDAO.delete(conn, noti);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}