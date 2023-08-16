package ru.mngerasimenko.handValues;

import java.util.List;

import ru.mngerasimenko.Card;

public class Straight extends HandValue {

	public Straight(List<Card> pokerHand) {
		this.combination = pokerHand;
	}

	@Override
	public Integer getPower() {
		return 4;
	}

	@Override
	public int compareTo(HandValue handValue) {
		return this.combination.get(0).compareTo(handValue.getCombination().get(0));
	}

}
