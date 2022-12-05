package acl.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import acl.domain.Card;

public non-sealed class CSVReader implements IDataReader {

	private List<Card> listOfCards;

	private static String FILE = "C:\\Users\\alban\\IdeaProjects\\Card\\src\\main\\resources\\cards.csv";

	public CSVReader() {
		listOfCards = new ArrayList<>();
	}

	public void read() {

		try {
			List<String> listOfReadDate = readData();
			listOfCards = cardBuilder(listOfReadDate);

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

	@Override
	public List<Card> getAll() {
		return listOfCards;
	}

	@Override
	public synchronized boolean saveCards() {
		try (FileWriter writer = new FileWriter(FILE)) {
			for (Card card : listOfCards) {
				writer.write(card.toCSV());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		;

		return true;
	}

	@Override
	public synchronized boolean updateCards(Card card) { //TODO ACL TO BE REVIEW
		int position = 0;
		for (var cardTempo : listOfCards) {
			if (cardTempo.id() == card.id()) {
				break;
			}
			position++;
		}
		listOfCards.set(position, card);

		return true;
	}

	private List<Card> cardBuilder(List<String> cardFromString) throws NumberFormatException {
		List<Card> cardList = new ArrayList<>();

		cardFromString.forEach(s -> {
			var tempo = s.split(",");
			cardList.add(new Card(Integer.parseInt(tempo[0]), tempo[1], tempo[2]));
		});

		return cardList;
	}

	/**
	 * Read data from CSV and generate a list of list String. Each line of the file
	 * is equal to an entry of the list
	 * 
	 * @return list of string
	 * @throws FileNotFoundException
	 */
	private List<String> readData() throws FileNotFoundException {
		String currentLine;
		List<String> listValues = new CopyOnWriteArrayList<>();

		FileInputStream file = new FileInputStream(FILE);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine != null) {
					listValues.add(currentLine);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listValues;
	}

	@Override
	public synchronized void removeCard(Card card) {
		listOfCards.remove(card);
	}
}
