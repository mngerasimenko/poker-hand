package ru.mngerasimenko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ru.mngerasimenko.PokerHand;
import ru.mngerasimenko.exceptions.CreateCardException;
import ru.mngerasimenko.exceptions.PokerHandException;

public class PokerHandTest {

	@Test
	public void createPokerHand() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("KS 2H 5C JD TD");
		Assert.assertEquals("KS JD TD 5C 2H", pokerHand.toString());
	}

	@Test(expected = PokerHandException.class)
	public void insufficientNumberCards() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("KS 2H 5C JD");
		Assert.assertEquals("KS 2H 5C JD", pokerHand.toString());
	}

	@Test(expected = PokerHandException.class)
	public void extraCards() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("KS 2H 5C JD TD 3C");
		Assert.assertEquals("KS 2H 5C JD TD 3C", pokerHand.toString());
	}

	@Test(expected = PokerHandException.class)
	public void emptyString() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("");
		Assert.assertEquals("", pokerHand.toString());
	}

	@Test
	public void equals() throws CreateCardException, PokerHandException {
		PokerHand actual = new PokerHand("KS 2H 5C JD TD");
		PokerHand expected = new PokerHand("JD TD 5C KS 2H");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void notEquals() throws CreateCardException, PokerHandException {
		PokerHand actual = new PokerHand("KS 2H 5C JD TD");
		PokerHand expected = new PokerHand("3H TD 5C KS 2H");
		Assert.assertNotEquals(expected, actual);
	}

	@Test
	public void highCard() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("KS 2H 5C JD TD");
		Assert.assertEquals("HighCard", pokerHand.getHandValue().getClass().getSimpleName());
		Assert.assertEquals("[KS]", pokerHand.getHandValue().getCombination().toString());
	}

	@Test
	public void pair() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("2S 3H 5C 2D TD");
		Assert.assertEquals("Pair", pokerHand.getHandValue().getClass().getSimpleName());
		Assert.assertEquals("[2S, 2D]", pokerHand.getHandValue().getCombination().toString());
	}

	@Test
	public void twoPairs() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("2S 3H 5C 2D 5D");
		Assert.assertEquals("TwoPairs", pokerHand.getHandValue().getClass().getSimpleName());
		Assert.assertEquals("[5C, 5D, 2S, 2D]", pokerHand.getHandValue().getCombination().toString());
	}

	@Test
	public void three() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("2S 2H 4C 2D 5D");
		Assert.assertEquals("Three", pokerHand.getHandValue().getClass().getSimpleName());
		Assert.assertEquals("[2S, 2H, 2D]", pokerHand.getHandValue().getCombination().toString());
	}

	@Test
	public void straight() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("6S 3H 4C 2D 5D");
		Assert.assertEquals("Straight", pokerHand.getHandValue().getClass().getSimpleName());
		Assert.assertEquals("[6S, 5D, 4C, 3H, 2D]", pokerHand.getHandValue().getCombination().toString());
	}

	@Test
	public void flush() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("QS 3S 4S 2S 5S");
		Assert.assertEquals("Flush", pokerHand.getHandValue().getClass().getSimpleName());
		Assert.assertEquals("[QS, 5S, 4S, 3S, 2S]", pokerHand.getHandValue().getCombination().toString());
	}

	@Test
	public void fullHouse() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("QH 3S QD 3H QC");
		Assert.assertEquals("FullHouse", pokerHand.getHandValue().getClass().getSimpleName());
		Assert.assertEquals("[QH, QD, QC, 3S, 3H]", pokerHand.getHandValue().getCombination().toString());
	}

	@Test
	public void four() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("QH QS QD 3H QC");
		Assert.assertEquals("Four", pokerHand.getHandValue().getClass().getSimpleName());
		Assert.assertEquals("[QH, QS, QD, QC]", pokerHand.getHandValue().getCombination().toString());
	}

	@Test
	public void straightFlush() throws CreateCardException, PokerHandException {
		PokerHand pokerHand = new PokerHand("QH AH JH KH TH");
		Assert.assertEquals("StraightFlush", pokerHand.getHandValue().getClass().getSimpleName());
		Assert.assertEquals("[AH, KH, QH, JH, TH]", pokerHand.getHandValue().getCombination().toString());
	}

	@Test
	public void sorting() throws CreateCardException, PokerHandException {
		List<PokerHand> actual = new ArrayList<>(
				Arrays.asList(
						new PokerHand("Qh 4h QD 2s 7D"),
						new PokerHand("QC 4h 6D Qs 7D"),
						new PokerHand("8s 9s ts qs js")
				));
		List<PokerHand> expected = new ArrayList<>(
				Arrays.asList(
						new PokerHand("QS JS TS 9S 8S"),
						new PokerHand("QC QS 7D 6D 4H"),
						new PokerHand("QH QD 7D 4H 2S")
				));
		Collections.sort(actual);
		Assert.assertEquals(actual, expected);
	}

}