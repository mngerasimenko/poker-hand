package ru.mngerasimenko.handValues;

import java.util.List;

import ru.mngerasimenko.Card;

public abstract class HandValue implements Comparable<HandValue> {

	protected List<Card> combination;
	protected List<Card> kicker;

	public abstract Integer getPower();

	public List<Card> getCombination() {
		return combination;
	}

	public List<Card> getKicker() {
		return kicker;
	}

}

