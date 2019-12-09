package noti.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import noti.dao.MyNotiDAO;
import noti.model.MyNotification;

public class ReadMyNotiService {
	MyNotiDAO myNotiDAO = new MyNotiDAO();
	
	public NotiData readMyNoti(int myId) {
		try (Connection conn = ConnectionProvider.getConnection()){
			List<MyNotification> notiList = myNotiDAO.getMyNotiList(conn, myId);
			return new NotiData(notiList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean isNotiCheck(int myId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int falseCnt = myNotiDAO.selectFalseCount(conn, myId);
			if (falseCnt == 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}