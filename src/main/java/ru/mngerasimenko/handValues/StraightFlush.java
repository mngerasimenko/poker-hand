package ru.mngerasimenko.handValues;

import java.util.List;

import ru.mngerasimenko.Card;

public class StraightFlush extends HandValue {

	public StraightFlush(List<Card> pokerHand) {
		this.combination = pokerHand;
	}

	@Override
	public Integer getPower() {
		return 0;
	}

	@Override
	public int compareTo(HandValue handValue) {
		return this.combination.get(0).compareTo(handValue.getCombination().get(0));
	}

}
