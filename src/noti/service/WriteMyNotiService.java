package noti.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import noti.dao.MyNotiDAO;
import noti.model.MyNotification;

public class WriteMyNotiService {
	
	MyNotiDAO myNotiDAO = new MyNotiDAO();
	
	public void writeMyNoti(MyNotification myNoti) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			myNotiDAO.insert(conn, myNoti);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			 throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}