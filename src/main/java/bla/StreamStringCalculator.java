package bla;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStringCalculator extends StringCalculator {

	public int sum(String s) {
		if (s.isEmpty()) {
			return 0;
		} else {
			Input input = parseInput(s);
			return Stream.of(input.numbers.split(input.delimiters))
					.map(Integer::parseInt)
					.filter(i -> i <= 1000)
					.reduce(0, Integer::sum);
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
		String message = Stream.of(input.numbers.split(input.delimiters))
				.filter(s -> s.startsWith("-"))
				.collect(Collectors.joining(","));
		if (!message.isEmpty()) {
			throw new RuntimeException("Rejecting negative numbers: " + message);
		}
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
