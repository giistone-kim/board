package util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieBox {

	public static Map<String, String> getCookiesValueMap(HttpServletRequest request) {
		System.out.println("cookiebox method");
		Map<String, String> cookiesMap = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length >= 1) {
			for (Cookie c : cookies) {
				String key = c.getName();
				String value = c.getValue();
				cookiesMap.put(key, value);
			}
		}
		return cookiesMap;
	}

}