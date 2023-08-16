package ru.mngerasimenko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.mngerasimenko.exceptions.CreateCardException;
import ru.mngerasimenko.exceptions.PokerHandException;

public class Starter {

	public static void main(String[] args) throws CreateCardException, PokerHandException {
		System.out.println("PokerHand");

		List<PokerHand> hands = new ArrayList<>();

		hands.add(new PokerHand("8s 9s ts qs js"));
		hands.add(new PokerHand("4s 7C 2h 6d 5C"));
		hands.add(new PokerHand("2h 4h 6h js th"));
		hands.add(new PokerHand("2h 4h 6h 4s 4D"));
		hands.add(new PokerHand("Th 4h 6h Ts 4D"));
		hands.add(new PokerHand("5h 4h 6h 8s 7D"));
		hands.add(new PokerHand("Qh 4h QD 8s 7D"));
		hands.add(new PokerHand("QC 4h 6D Qs 7D"));
		Collections.sort(hands);
		for (PokerHand pokerHand : hands) {
			System.out.println(pokerHand.getPokerHand()
					+ " "
					+ pokerHand.getHandValue().getClass().getSimpleName()
					+ pokerHand.getHandValue().getCombination());
		}
	}

}
