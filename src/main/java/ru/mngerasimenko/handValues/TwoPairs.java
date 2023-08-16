package ru.mngerasimenko.handValues;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.mngerasimenko.Card;

public class TwoPairs extends HandValue {

	public TwoPairs(List<Card> pokerHand) {
		Map<Integer, List<Card>> listMap = pokerHand.stream()
				.collect(Collectors.groupingBy(Card::getPower));
		combination = new ArrayList<>();
		for (Map.Entry<Integer, List<Card>> pair : listMap.entrySet()) {
			if (pair.getValue().size() == 2) {
				combination.addAll(pair.getValue());
			}
			if (pair.getValue().size() == 1) {
				kicker = pair.getValue();
			}
		}
	}

	@Override
	public Integer getPower() {
		return 6;
	}

	@Override
	public int compareTo(HandValue handValue) {
		int compare = this.combination.get(0).compareTo(handValue.getCombination().get(0));
		if (compare != 0) {
			return compare;
		}
		compare = this.combination.get(2).compareTo(handValue.getCombination().get(2));
		if (compare != 0) {
			return compare;
		}
		return this.kicker.get(0).compareTo(handValue.getKicker().get(0));
	}

}
