package ru.mngerasimenko.handValues;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.mngerasimenko.Card;

public class FullHouse extends HandValue {

	public FullHouse(List<Card> pokerHand) {
		Map<Integer, List<Card>> listMap = pokerHand.stream()
				.collect(Collectors.groupingBy(Card::getPower));
		combination = new ArrayList<>();
		for (Map.Entry<Integer, List<Card>> pair : listMap.entrySet()) {
			if (pair.getValue().size() == 3) {
				combination.addAll(0, pair.getValue());
			}
			if (pair.getValue().size() == 2) {
				combination.addAll(pair.getValue());
			}
		}
	}

	@Override
	public Integer getPower() {
		return 2;
	}

	@Override
	public int compareTo(HandValue handValue) {
		int compare = this.combination.get(0).compareTo(handValue.getCombination().get(0));
		if (compare != 0) {
			return compare;
		}
		return this.combination.get(3).compareTo(handValue.getCombination().get(3));
	}

}
