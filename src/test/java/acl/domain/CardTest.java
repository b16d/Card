package acl.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CardTest {

	@Test
	public void test_to_CSV() {
		Card card = new Card(0,"title", "information");
		
		assertEquals("0,title,information", card.toCSV());
	}
}
