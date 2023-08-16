package ru.mngerasimenko.handValues;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.mngerasimenko.Card;

public class Four extends HandValue {

	public Four(List<Card> pokerHand) {
		Map<Integer, List<Card>> listMap = pokerHand.stream()
				.collect(Collectors.groupingBy(Card::getPower));
		for (Map.Entry<Integer, List<Card>> pair : listMap.entrySet()) {
			if (pair.getValue().size() == 4) {
				combination = pair.getValue();
			}
			if (pair.getValue().size() == 1) {
				kicker = pair.getValue();
			}
		}
	}

	@Override
	public Integer getPower() {
		return 1;
	}

	@Override
	public int compareTo(HandValue handValue) {
		int compare = this.combination.get(0).compareTo(handValue.getCombination().get(0));
		if (compare != 0) {
			return compare;
		}
		return this.kicker.get(0).compareTo(handValue.getKicker().get(0));
	}

}
