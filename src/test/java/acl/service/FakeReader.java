package acl.service;

import acl.db.CSVReader;
import acl.db.IDataReader;
import acl.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class FakeReader extends CSVReader {

    List<Card> cardList = new ArrayList<>();

    public FakeReader() {
        cardList.add(new Card(0,"Card1", "Card1"));
        cardList.add(new Card(1,"Card2", "Card2"));
    }

    @Override
    public void read() {
        //To nothing
    }

    @Override
    public List<Card> getAll() {
        return cardList;
    }

    @Override
    public boolean saveCards() {
        return true;
    }

    @Override
    public boolean updateCards(Card card) {
        return true;
    }

    @Override
    public void removeCard(Card card) {

    }
}
