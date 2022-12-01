package acl.service;

import java.util.Collection;

import acl.db.CSVReader;
import acl.domain.Card;

public class CardService {

	CSVReader reader;

	public CardService() {
		reader = new CSVReader();
	}
	public Collection<Card> getAllCards() {
		return reader.getAll();
	}
	
	public Card getCardById(int id) {
		return reader.getAll().stream().filter(c -> c.id() == id).findFirst().orElse(null);
	}
	
	public boolean removeCard(Card cardToRemove) {
		//Check card exist
		var card = getCardById(cardToRemove.id());

		return false;
	}
	
	public boolean updateCard(Card cardToUpdate) {
		//Check card exist
		var card = getCardById(cardToUpdate.id());

		if (card == null) {
			System.out.println("Card not Found");
			return false;
		}

		return reader.updateCards(card);
	}

}
