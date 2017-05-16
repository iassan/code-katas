package bla;

import java.util.HashMap;
import java.util.Map;

public class ArabicToRomanConverter {

	private static Map<Integer, String> romanDigits = new HashMap<>();

	static {
		romanDigits.put(1, "I");
		romanDigits.put(5, "V");
		romanDigits.put(10, "X");
		romanDigits.put(50, "L");
		romanDigits.put(100, "C");
		romanDigits.put(500, "D");
		romanDigits.put(1000, "M");
	}

	public static String convert(int number) {
		if (number == 0) {
			return "";
		}
		if (romanDigits.containsKey(number)) {
			return romanDigits.get(number);
		} else {
			int divisor = 1;
			while (number / divisor > 0) {
				divisor *= 10;
			}
			divisor /= 10;
			int currentDigitToConvert = number / divisor;
			int[] keys;
			switch (currentDigitToConvert) {
				case 1:
					keys = new int[]{1};
					break;
				case 2:
					keys = new int[]{1, 1};
					break;
				case 3:
					keys = new int[]{1, 1, 1};
					break;
				case 4:
					keys = new int[]{1, 5};
					break;
				case 5:
					keys = new int[]{5};
					break;
				case 6:
					keys = new int[]{5, 1};
					break;
				case 7:
					keys = new int[]{5, 1, 1};
					break;
				case 8:
					keys = new int[]{5, 1, 1, 1};
					break;
				case 9:
					keys = new int[]{1, 10};
					break;
				default:
					keys = new int[]{};
					break;
			}
			String result = "";
			for (int key : keys) {
				result += romanDigits.get(key * divisor);
			}
			return result + convert(number % divisor);
//			int biggestCurrent = 1;
//			for (Map.Entry<Integer, String> entry : romanDigits.entrySet()) {
//				if (entry.getKey() < number && biggestCurrent < entry.getKey()) {
//					biggestCurrent = entry.getKey();
//				}
//			}
//			return romanDigits.get(biggestCurrent) + convert(number - biggestCurrent);
		}
	}

}
