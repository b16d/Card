package acl.db;

import java.util.List;

import acl.domain.Card;

public sealed interface IDataReader permits CSVReader {

	public void read();
	
	public List<Card> getAll();
	
	public boolean saveCards();
	
	public boolean updateCards(Card card);
	
	public void removeCard(Card card);
}
