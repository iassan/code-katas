package bla;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringCalculatorTest {

	private StringCalculator calc = new StreamStringCalculator();

	@Test
	public void sumsUpTo2Numbers() {
		assertThat(calc.sum("")).isEqualTo(0);
		assertThat(calc.sum("1")).isEqualTo(1);
		assertThat(calc.sum("2")).isEqualTo(2);
		assertThat(calc.sum("1,2")).isEqualTo(3);
	}

	@Test
	public void sumsLongSequences() {
		assertThat(calc.sum("1,2,5,8,9,143,463")).isEqualTo(631);
		assertThat(calc.sum("374,457,23,896")).isEqualTo(1750);
		assertThat(calc.sum("1,1,1,1,1,1,1,1,1,1,1,2")).isEqualTo(13);
	}

	@Test
	public void handlesNewlineAsDelimiter() {
		assertThat(calc.sum("1\n2")).isEqualTo(3);
		assertThat(calc.sum("3\n2,1")).isEqualTo(6);
	}

	@Test
	public void handlesDefinedDelimiters() {
		assertThat(calc.sum("//;\n1;2")).isEqualTo(3);
		assertThat(calc.sum("//o\n3o4")).isEqualTo(7);
	}

	@Test
	public void rejectsNegativeNumbers() {
		assertThatThrownBy(() -> calc.sum("1,-2"))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("-2");
		assertThatThrownBy(() -> calc.sum("//]\n-1]-2]3"))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("-1,-2");
	}

	@Test
	public void ignoresBigNumbers() {
		assertThat(calc.sum("2,1000")).isEqualTo(1002);
		assertThat(calc.sum("2,1001")).isEqualTo(2);
	}

}
