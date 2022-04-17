package nsh.codility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public abstract class AbstractPairACoder2022Test {
	abstract PairACoder2022Interface getTestObject();

	PairACoder2022Interface testObject;

	@BeforeEach
	void setup() {
		testObject = getTestObject();

	}

	@ParameterizedTest
	@CsvSource(textBlock = """
			1,abccac
			2,abcdabcdabcd
			0,axaabyab
			1,tqweqrtyr
				""")
	@DisplayName("Sample")
	void test_sample(int E, String S) {
		assertEquals(E, testObject.solution(S));
	}

	@ParameterizedTest
	@CsvSource(textBlock = """
			1,a
			0,aa
			2,ab
			0,aba
			1,baa
				""")
	@DisplayName("Small")
	void test_small(int E, String S) {
		assertEquals(E, testObject.solution(S));
	}

	@Test
	@DisplayName("A-Z")
	void test_az() {
		String S = "abcdefghijklmnopqrstuvwxyz";
		int E = 26;

		assertEquals(E, testObject.solution(S));
	}

	@Test
	@DisplayName("A-Z*26")
	void test_az_repeat() {
		String S1 = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer SB = new StringBuffer();
		for (int i = 0; i < S1.length(); i++)
			for (int j = 0; j < 26; j++)
				SB.append(S1.charAt(i));
		String S = SB.toString();
		int E = 0;

		assertEquals(E, testObject.solution(S));
	}

	@ParameterizedTest
	@CsvSource(textBlock = """
			1,abacadaeafagahaiajakalamanaoapaqarasatauavawaxayaz
			2,bacadaeafagahaiajakalamanaoapaqarasatauavawaxayaz
				""")
	@DisplayName("a in between")
	void test_a_in_between(int E, String S) {
		assertEquals(E, testObject.solution(S));
	}

	@ParameterizedTest
	@CsvSource(textBlock = """
			0,aaaaabbbabcccacdddad
			0,aaadabbbabcccbcdddcd
			0,aaaeabbbabcccbcdddcd
			0,aaababbbabcccdcdddcd
			0,aaaxyzabbbuvwbcccrstcdddopqd
			""")
	@DisplayName("all_but_1")
	void test_all_but1(int E, String S) {

		assertEquals(E, testObject.solution(S));
	}

	@ParameterizedTest
	@CsvSource(textBlock = """
			0,aaababbbabcaeacdafaddddad
			0,aaababbbabcaeacdafadddadad
			""")
	@DisplayName("not_first_not_last")
	void test_not_first_not_last(int E, String S) {

		assertEquals(E, testObject.solution(S));
	}


	@Test
	@DisplayName("2 segments")
	void test_seg2() {
		String S = "cacacbacacba";
		int E = 0;

		assertEquals(E, testObject.solution(S));
	}

	@Test
	@DisplayName("a-d*")
	void test_long_a_d() {
		String S = "abcd".repeat(25000);
		int E = 0;

		assertEquals(E, testObject.solution(S));
	}

	@Test
	@DisplayName("a-z*")
	void test_long_a_z() {
		String S = "abcdefghijklmnopqrstuvwxyz".repeat(3846);
		int E = 0;

		assertEquals(E, testObject.solution(S));
	}
}
