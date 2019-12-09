package noti.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import noti.dao.MyNotiDAO;

public class UpdateMyNotiService {
	
	private MyNotiDAO notiDAO = new MyNotiDAO();
	
	public void update(int notiNo) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			int noti = notiDAO.selectById(conn, notiNo);
			
			if(noti <= 0) {
				throw new NotificationNotFoundException();
			}
			
			notiDAO.update(conn, noti);
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}