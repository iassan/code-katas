package bla;

import org.junit.Ignore;
import org.junit.Test;

import static bla.ArabicToRomanConverter.convert;
import static org.assertj.core.api.Assertions.assertThat;

public class ArabicToRomanTest {

	@Test
	public void convertToSingleLetters() {
		assertThat(convert(1)).isEqualTo("I");
		assertThat(convert(5)).isEqualTo("V");
		assertThat(convert(10)).isEqualTo("X");
		assertThat(convert(50)).isEqualTo("L");
		assertThat(convert(100)).isEqualTo("C");
		assertThat(convert(500)).isEqualTo("D");
		assertThat(convert(1000)).isEqualTo("M");
	}

	@Test
	public void convertToMultiplesOfSingleLetters() {
		assertThat(convert(2)).isEqualTo("II");
		assertThat(convert(3)).isEqualTo("III");
		assertThat(convert(20)).isEqualTo("XX");
		assertThat(convert(30)).isEqualTo("XXX");
		assertThat(convert(200)).isEqualTo("CC");
		assertThat(convert(300)).isEqualTo("CCC");
		assertThat(convert(2000)).isEqualTo("MM");
		assertThat(convert(3000)).isEqualTo("MMM");
	}

	@Test
	public void convertToDifferentLetters() {
		assertThat(convert(6)).isEqualTo("VI");
		assertThat(convert(7)).isEqualTo("VII");
		assertThat(convert(8)).isEqualTo("VIII");
		assertThat(convert(202)).isEqualTo("CCII");
	}

	@Test
	public void convertWithSubtraction() {
		assertThat(convert(4)).isEqualTo("IV");
		assertThat(convert(999)).isEqualTo("CMXCIX");
		assertThat(convert(2751)).isEqualTo("MMDCCLI");
		assertThat(convert(369)).isEqualTo("CCCLXIX");
		assertThat(convert(448)).isEqualTo("CDXLVIII");
		assertThat(convert(1998)).isEqualTo("MCMXCVIII");
	}
}
