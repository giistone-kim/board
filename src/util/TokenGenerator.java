package util;

import java.util.UUID;

public class TokenGenerator {

	public static String getTokenKey() {
		UUID uid = UUID.randomUUID();
		System.out.println("gen uid=" + uid.toString());
		return uid.toString();
	}
}