package ru.mngerasimenko.handValues;

import java.util.ArrayList;
import java.util.List;

import ru.mngerasimenko.Card;

public class HighCard extends HandValue {

	public HighCard(List<Card> pokerHand) {
		combination = new ArrayList<>();
		combination.add(pokerHand.get(0));
		kicker = pokerHand.subList(1, 4);
	}

	@Override
	public Integer getPower() {
		return 8;
	}

	@Override
	public int compareTo(HandValue handValue) {
		int compare = this.combination.get(0).compareTo(handValue.getCombination().get(0));
		if (compare != 0) {
			return compare;
		}
		for (int i = 0; i < this.kicker.size() - 1; i++) {
			compare = this.kicker.get(i).compareTo(handValue.getCombination().get(i));
			if (compare != 0) {
				return compare;
			}
		}
		return this.kicker.get(3).compareTo(handValue.getCombination().get(3));
	}

}
