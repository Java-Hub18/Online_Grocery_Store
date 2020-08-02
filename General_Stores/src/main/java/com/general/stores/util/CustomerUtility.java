package com.general.stores.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

public class CustomerUtility {

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	public static String generateNewToken() {
		byte[] randomBytes = new byte[24];
		secureRandom.nextBytes(randomBytes);
		return base64Encoder.encodeToString(randomBytes);
	}

/*	public static int generateNumber() {
		Random random = new Random();
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int number = 0;
		while (arrayList.size() <= 4) { // how many numbers u need - it will 4
			number = random.nextInt(1000000) + 1; // this will give numbers between 1 and 1000000.
			if (!arrayList.contains(number)) {
				arrayList.add(number);
			}
		}
		return number;
	}*/
}
