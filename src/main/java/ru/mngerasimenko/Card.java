package ru.mngerasimenko;

import ru.mngerasimenko.exceptions.CreateCardException;

public class Card implements Comparable<Card> {
	public static final String VALUE_TYPES = "AKQJT98765432";
	public static final String SUIT_TYPES = "SHDC";

	private final String value;
	private final String suit;
	private final Integer power;

	public Card(String card) throws CreateCardException {
		validateCountCharacters(card);

		String value = card.substring(0, 1).toUpperCase();
		String suit = card.substring(1, 2).toUpperCase();

		validateValue(value);
		validateSuit(suit);

		this.value = value;
		this.suit = suit;
		this.power = Card.VALUE_TYPES.indexOf(value);
	}

	private void validateCountCharacters(String card) throws CreateCardException {
		if (card.length() != 2) {
			throw new CreateCardException("Invalid count characters: " + card);
		}
	}

	private void validateValue(String value) throws CreateCardException {
		if (!VALUE_TYPES.contains(value)) {
			throw new CreateCardException("Invalid card value: " + value);
		}
	}

	private void validateSuit(String suit) throws CreateCardException {
		if (!SUIT_TYPES.contains(suit)) {
			throw new CreateCardException("Invalid card suit: " + suit);
		}
	}

	public String getValue() {
		return value;
	}

	public String getSuit() {
		return suit;
	}

	public Integer getPower() {
		return power;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Card card = (Card) o;

		if (!value.equals(card.value)) return false;
		if (!suit.equals(card.suit)) return false;
		return power.equals(card.power);
	}

	@Override
	public int hashCode() {
		int result = value.hashCode();
		result = 31 * result + suit.hashCode();
		result = 31 * result + power.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return value + suit;
	}

	@Override
	public int compareTo(Card card) {
		return power.compareTo(card.power);
	}

}
