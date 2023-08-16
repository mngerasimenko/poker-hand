package ru.mngerasimenko;

import java.util.*;
import java.util.stream.Collectors;

import ru.mngerasimenko.exceptions.CreateCardException;
import ru.mngerasimenko.exceptions.PokerHandException;
import ru.mngerasimenko.handValues.*;

public class PokerHand implements Comparable<PokerHand> {

	List<Card> pokerHand;
	HandValue handValue;

	public PokerHand(String pokerHand) throws CreateCardException, PokerHandException {
		this.pokerHand = parseCards(pokerHand);
		sortCards();
		this.handValue = calculateHandValue();
	}

	private List<Card> parseCards(String pokerHand) throws CreateCardException, PokerHandException {
		String[] receivedCards = pokerHand.trim().split(" ");
		if (receivedCards.length != 5) {
			throw new PokerHandException("Incorrect number of cards");
		}

		List<Card> cards = new ArrayList<>();
		for (String receivedCard : receivedCards) {
			Card card = new Card(receivedCard);
			if (cards.contains(card)) {
				throw new PokerHandException("The card has already been used: " + card);
			}
			cards.add(card);
		}

		return cards;
	}

	private void sortCards() {
		Collections.sort(pokerHand);
	}

	private HandValue calculateHandValue() {
		if (isStraightFlash()) {
			return new StraightFlush(pokerHand);
		}
		if (isFour()) {
			return new Four(pokerHand);
		}
		if (isFullHouse()) {
			return new FullHouse(pokerHand);
		}
		if (isFlush()) {
			return new Flush(pokerHand);
		}
		if (isStraight()) {
			return new Straight(pokerHand);
		}
		if (isThree()) {
			return new Three(pokerHand);
		}
		if (isTwoPair()) {
			return new TwoPairs(pokerHand);
		}
		if (isPair()) {
			return new Pair(pokerHand);
		}
		return new HighCard(pokerHand);
	}

	private boolean isStraightFlash() {
		return isAllOfSameSuit() && isInOrder();
	}

	private boolean isAllOfSameSuit() {
		return pokerHand.stream()
				.allMatch(c -> pokerHand.get(0).getSuit().equals(c.getSuit()));
	}

	private boolean isInOrder() {
		int firstCardPower = pokerHand.get(0).getPower();
		for (int i = firstCardPower, j = 0; j < pokerHand.size(); i++, j++) {
			if (i != pokerHand.get(j).getPower()) {
				return false;
			}
		}
		return true;
	}

	private boolean isContainsAmount(int amount) {
		return pokerHand.stream()
				.collect(Collectors.toMap(Card::getPower, value -> 1, Integer::sum))
				.entrySet()
				.stream()
				.anyMatch(c -> c.getValue() == amount);
	}

	private boolean isFour() {
		return isContainsAmount(4);
	}

	private boolean isFullHouse() {
		return isContainsAmount(3) && isContainsAmount(2);
	}

	private boolean isFlush() {
		return isAllOfSameSuit();
	}

	private boolean isStraight() {
		return isInOrder();
	}

	private boolean isThree() {
		return isContainsAmount(3);
	}

	private boolean isTwoPair() {
		return pokerHand.stream()
				.collect(Collectors.toMap(Card::getPower, value -> 1, Integer::sum))
				.entrySet()
				.stream()
				.filter(i -> i.getValue() == 2)
				.count() == 2;
	}

	private boolean isPair() {
		return isContainsAmount(2);
	}

	public List<Card> getPokerHand() {
		return pokerHand;
	}

	public HandValue getHandValue() {
		return handValue;
	}

	@Override
	public String toString() {
		return pokerHand.get(0) + " "
				+ pokerHand.get(1) + " "
				+ pokerHand.get(2) + " "
				+ pokerHand.get(3) + " "
				+ pokerHand.get(4);
	}

	@Override
	public int compareTo(PokerHand hand) {
		int compare = this.handValue.getPower().compareTo(hand.handValue.getPower());
		if (compare != 0) {
			return compare;
		}
		return handValue.compareTo(hand.getHandValue());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PokerHand hand = (PokerHand) o;

		return this.pokerHand.size() == hand.getPokerHand().size()
				&& new HashSet<>(this.pokerHand).containsAll(hand.getPokerHand());
	}

	@Override
	public int hashCode() {
		return this.pokerHand.hashCode();
	}

}
