package acl.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import acl.domain.Card;

//@TestInstance(Lifecycle.PER_CLASS)
public class CSVReaderTest {
/*
	CSVReader reader;
	
	public CSVReaderTest() {
		reader = new CSVReader();
	}
	
	@BeforeAll
	public void init() {
		reader.read();
	}
	@Test
	public void test_get_all_cards()  {
		List<Card> carList = reader.getAll();
		
		assertEquals(2, carList.size());
		assertEquals(1, carList.get(0).id());
	}
	
	@Test
	public void test_updadte_one_card() {
		Card card = reader.getAll().get(0);
		Card newCard = new Card(card.id(), card.title(), "Information2");
		
		reader.updateCards(newCard);
		List<Card> carList = reader.getAll();
		
		assertEquals("Information2", carList.get(0).information());
	}
	
	@Test
	public void remove_one_card() {
		Card card = reader.getAll().get(0);
		reader.removeCard(card);
		
		assertEquals(1, reader.getAll().size());
		reader.read();
	}*/
}
