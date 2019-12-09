package filter;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import util.AES256Util;

public class AESRequestWrapper extends HttpServletRequestWrapper {

	private AES256Util aes256 = null;
	private HttpServletRequest request;
	private String key = "12345678901234567890/";

	public AESRequestWrapper(HttpServletRequest request) throws UnsupportedEncodingException {
		super(request);
		this.request = request;
		aes256 = new AES256Util(key);
	}

	@Override
	public String getParameter(String str) {
		try {
			String retVal = request.getParameter(str);
//			if (str.equals("email") || str.equals("password"))
			if (str.equals("password"))
				retVal = doEncryption(retVal);
			
			if (str.equals("curPwd"))
				retVal = doEncryption(retVal);
			
			if (str.equals("newPwd"))
				retVal = doEncryption(retVal);
			
			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String doEncryption(String str) throws Exception {
		return str == null ? null : aes256.aesEncode(str);
	}

}