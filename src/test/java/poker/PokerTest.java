package poker;

import static org.junit.Assert.assertThat;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class PokerTest {

	@Test
	public void shouldParseOnePair() {
		String info = "KH KH 2C 3H 4C";
		Assertions.assertThat(Handparser.parse(info)).isEqualTo(Score.PAIR);
	}

	@Test
	public void shouldParseTwoPairs() {
		String info = "KH KH 2C 3H 2C";
		Assertions.assertThat(Handparser.parse(info)).isEqualTo(Score.TWO_PAIRS);
	}

	@Test
	public void shouldParseThreeOfTheKind() {
		String info = "KH 2C KH 9C KH";
		Assertions.assertThat(Handparser.parse(info)).isEqualTo(Score.THREE);
	}

	@Test
	public void shouldParseFullHouse() {
		String info = "KH 2C 2C KH KH";
		Assertions.assertThat(Handparser.parse(info)).isEqualTo(Score.FULL);
	}

	@Test
	public void shouldParseFourOfTheKind() {
		String info = "KH 2C KH KH KH";
		Assertions.assertThat(Handparser.parse(info)).isEqualTo(Score.FOUR);
	}

	@Test
	public void shouldParseStright() {
		String info = "7H 6C 4C 3H 5H";
		Assertions.assertThat(Handparser.parse(info)).isEqualTo(Score.STRIGHT);
	}
	
	@Test
	public void shouldParsePoker() {
		String info = "7H 6H 4H 3H 5H";
		Assertions.assertThat(Handparser.parse(info)).isEqualTo(Score.POKER);
	}
	
	@Test
	public void shouldParseKingPoker() {
		String info = "TH AH JH KH QH";
		Assertions.assertThat(Handparser.parse(info)).isEqualTo(Score.KING_POKER);
	}
	
	@Test
	public void shouldParseFlush() {
		String info = "2H JH 7H 9H 5H";
		Assertions.assertThat(Handparser.parse(info)).isEqualTo(Score.FLUSH);
	}
	
	@Test 
	public void shouldParseHighCard() {
		String info =  "2H JH 7H 9H 5C";
		Assertions.assertThat(Handparser.parse(info)).isEqualTo(Score.HIGH_CARD);
	}
	
	@Test
	public void shouldW() {
		
		String a = "aaa";
		String b = "bbb";
		String ab = "aaabbb";
		String ab2 = "aaa" + "bbb";
		System.out.println((a+b) == ab ? "true" : "false");
		System.out.println(ab == ab2 ? "true" : "false");
	}
	
	@Test
	public void shouldBeScoreGreaterThanZero() {
		PokerTable pokerTable = new PokerTable();
		Assertions.assertThat(pokerTable.play()).isGreaterThan(0);
		System.out.println(pokerTable.play());
	}
	
	@Test
	public void checkBestCardIsSeven() {
		String info = "7H 2H 2H 2H 5H";
		Assertions.assertThat(Handparser.checkTheBestCard(info)).isEqualTo(2);
	}

}
