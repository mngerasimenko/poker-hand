package ru.mngerasimenko.handValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.mngerasimenko.Card;

public class Three extends HandValue {

	public Three(List<Card> pokerHand) {
		Map<Integer, List<Card>> listMap = pokerHand.stream()
				.collect(Collectors.groupingBy(Card::getPower));
		kicker = new ArrayList<>();
		for (Map.Entry<Integer, List<Card>> pair : listMap.entrySet()) {
			if (pair.getValue().size() == 3) {
				combination = pair.getValue();
			}
			if (pair.getValue().size() == 1) {
				kicker.addAll(pair.getValue());
			}
		}
	}

	@Override
	public Integer getPower() {
		return 5;
	}

	@Override
	public int compareTo(HandValue handValue) {
		int compare = this.combination.get(0).compareTo(handValue.getCombination().get(0));
		if (compare != 0) {
			return compare;
		}
		compare = this.kicker.get(0).compareTo(handValue.getKicker().get(0));
		if (compare != 0) {
			return compare;
		}
		return this.kicker.get(1).compareTo(handValue.getKicker().get(1));
	}

}
