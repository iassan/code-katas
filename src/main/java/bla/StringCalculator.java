package bla;

public class StringCalculator {

	public int sum(String s) {
		if (s.isEmpty()) {
			return 0;
		} else {
			Input input = parseInput(s);
			return sum(input.numbers.split(input.delimiters));
		}
	}

	private Input parseInput(String s) {
		Input input = splitInput(s);
		rejectNegativeNumbers(input);
		return input;
	}

	private Input splitInput(String s) {
		Input input;
		if (s.startsWith("//")) {
			int splitIndex = s.indexOf("\n");
			input = new Input(s.substring(2, splitIndex), s.substring(splitIndex + 1));
		} else {
			input = new Input("[,\n]", s);
		}
		return input;
	}

	private void rejectNegativeNumbers(Input input) {
		StringBuilder negativeNumbers = new StringBuilder();
		for (String s : input.numbers.split(input.delimiters)) {
			if (s.startsWith("-"))
				negativeNumbers.append(",").append(s);
		}
		if (negativeNumbers.length() > 0)
			throw new RuntimeException("Rejecting negative numbers: " + negativeNumbers.toString());
	}

	private int sum(String[] numbers) {
		int acc = 0;
		for (String n : numbers) {
			acc += Integer.parseInt(n);
		}
		return acc;
	}

	private static class Input {

		String delimiters;

		String numbers;

		public Input(String delimiters, String numbers) {
			this.delimiters = delimiters;
			this.numbers = numbers;
		}
	}
}
