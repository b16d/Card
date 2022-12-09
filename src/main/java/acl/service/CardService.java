package acl.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import acl.db.CSVReader;
import acl.db.IDataReader;
import acl.domain.Card;

public class CardService {

	IDataReader reader;

	private Map<Integer, Card> cardsCache;
	public CardService() {
		reader = new CSVReader();
		reader.read();
		cardsCache = reader.getAll().stream().collect(Collectors.toMap(card -> card.id(), Function.identity()));
	}

	protected CardService(IDataReader reader) {
		this.reader = reader;
		cardsCache = reader.getAll().stream().collect(Collectors.toMap(card -> card.id(), Function.identity()));
	}
	public Collection<Card> getAllCards() {
		return cardsCache.values();
	}
	
	public Card getCardById(int id) {
		return cardsCache.get(id);
	}
	
	public boolean removeCard(int id) {

		cardsCache.remove(id);

		return true;
	}
	
	public boolean updateCard(Card cardToUpdate) {
		//Check card exist
		cardsCache.put(cardToUpdate.id(), cardToUpdate);

		return false;
	}

}
