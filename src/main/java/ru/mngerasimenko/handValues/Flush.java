package ru.mngerasimenko.handValues;

import java.util.List;

import ru.mngerasimenko.Card;

public class Flush extends HandValue {

	public Flush(List<Card> pokerHand) {
		combination = pokerHand;
	}

	@Override
	public Integer getPower() {
		return 3;
	}

	@Override
	public int compareTo(HandValue handValue) {
		for (int i = 0; i < this.combination.size() - 1; i++) {
			int compare = this.combination.get(i).compareTo(handValue.getCombination().get(i));
			if (compare != 0) {
				return compare;
			}
		}
		return this.combination.get(4).compareTo(handValue.getCombination().get(4));
	}

}
