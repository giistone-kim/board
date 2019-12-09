package util;

import java.util.Random;

public class RandomBox {
	Random random = new Random();

	public static String getRandomImage() {
		Random random = new Random();
		int num = random.nextInt(3);
		return "/profileimg/user-default0" + num + ".png";
	}

	public static String getRandomRegisterCode() {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int num = Math.abs(random.nextInt());
		return "U" + num;
	}
}