package ru.mngerasimenko;

import org.junit.Assert;
import org.junit.Test;


import ru.mngerasimenko.Card;
import ru.mngerasimenko.exceptions.CreateCardException;

public class CardTest {

	@Test
	public void normalCreateCard() throws CreateCardException {
		Card card = new Card("2S");
		Assert.assertEquals("2S", card.toString());
	}

	@Test(expected = CreateCardException.class)
	public void invalidValue() throws CreateCardException {
		Card card = new Card("DS");
		Assert.assertEquals("DS", card.toString());
	}

	@Test(expected = CreateCardException.class)
	public void invalidSuit() throws CreateCardException {
		Card card = new Card("2F");
		Assert.assertEquals("2F", card.toString());
	}

	@Test(expected = CreateCardException.class)
	public void threeCharacters() throws CreateCardException {
		Card card = new Card("2SF");
		Assert.assertEquals("2SF", card.toString());
	}

	@Test(expected = CreateCardException.class)
	public void oneCharacter() throws CreateCardException {
		Card card = new Card("2");
		Assert.assertEquals("2", card.toString());
	}

	@Test(expected = CreateCardException.class)
	public void emptyCharacter() throws CreateCardException {
		Card card = new Card("");
		Assert.assertEquals("", card.toString());
	}

	@Test
	public void equals() throws CreateCardException {
		Card actual = new Card("QS");
		Card expected = new Card("QS");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void notEquals() throws CreateCardException {
		Card actual = new Card("QS");
		Card expected = new Card("QH");
		Assert.assertNotEquals(expected, actual);
	}

}