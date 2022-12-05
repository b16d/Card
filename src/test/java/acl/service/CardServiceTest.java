package acl.service;

import acl.db.CSVReader;
import acl.db.IDataReader;
import acl.domain.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CardServiceTest {
   static CardService service;

    @BeforeEach
    public void init() {
        service = new CardService(new FakeReader());
    }

    @Test
    public void test_getAll() {
       Assertions.assertEquals(2, service.getAllCards().size());
    }

    @Test
    public void test_getById() {
        Assertions.assertEquals(1, service.getCardById(1).id());
    }

    @Test
    public void test_updateCard() {
        String name = "UpdateCard";
        service.updateCard(new Card(0, name, name ));

        Assertions.assertEquals(name, service.getCardById(0).information());
    }

    @Test
    public void test_removeCard() {
        String name = "UpdateCard";
        service.removeCard(new Card(0, name, name ));

        Assertions.assertEquals(1, service.getAllCards().size());
    }

}
