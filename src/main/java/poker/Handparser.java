package poker;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class Handparser {

	public static Score parse(String hand) {
		String[] cards = hand.split(" ");
		Map<String, Integer> cardMap = new HashMap<String, Integer>();
		for (int i = 0; i < cards.length; i++) {
			String key = cards[i].substring(0, 1);
			if (cardMap.containsKey(key)) {
				cardMap.put(key, cardMap.get(key) + 1);
			} else {
				cardMap.put(key, 1);
			}
		}
		return findHand(cardMap, areAllTheSameColour(cards));
	}

	private static Score findHand(Map<String, Integer> cardMap, boolean theSameColour) {
		Collection<Integer> valuesCard = cardMap.values();
		if (valuesCard.contains(4))
			return Score.FOUR;

		boolean containThree = valuesCard.contains(3);
		if (containThree) {
			if (valuesCard.contains(2))
				return Score.FULL;
			return Score.THREE;
		}

		int result = 0;
		for (Integer element : valuesCard) {
			if (element == 2) {
				result++;
			}
		}
		if (result == 2) {
			return Score.TWO_PAIRS;
		} else if (result == 1) {
			return Score.PAIR;
		}

		int[] lSNumbers = findLargestAndSmallestNumber(parseCards(cardMap));

		if (lSNumbers[0] - lSNumbers[1] == 4) {
			if (theSameColour) {
				if (lSNumbers[1] == 10)
					return Score.KING_POKER;
				else
					return Score.POKER;
			} else
				return Score.STRIGHT;
		} else if (theSameColour) {
			return Score.FLUSH;
		}

		return Score.HIGH_CARD; // tutaj trzeba dorobic obsluge tego straznego
								// ukladu
	}

	private static boolean areAllTheSameColour(String[] cards) {
		String colour = cards[0].substring(1, 2);
		for (int i = 1; i < cards.length; i++) {
			if (!cards[i].substring(1, 2).equals(colour)) {
				return false;
			}
		}
		return true;
	}

	private static int[] findLargestAndSmallestNumber(int[] numbers) {
		int largest = numbers[0];
		int smallest = numbers[0];

		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > largest)
				largest = numbers[i];
			else if (numbers[i] < smallest)
				smallest = numbers[i];
		}

		int[] tab = new int[2];
		tab[0] = largest;
		tab[1] = smallest;

		return tab;
	}

	private static int[] parseCards(Map<String, Integer> cardMap) {
		Set<String> keySet = cardMap.keySet();

		int[] tab = new int[5];
		int i = 0;

		for (String s : keySet) {
			String number = s.substring(0, 1);
			tab[i] = parseOneCard(number);
			i++;
		}
		return tab;
	}
	
	private static int parseOneCard(String sign) {
		int value;
		if (sign.equals("T")) {
			value = 10;
		} else if (sign.equals("J")) {
			value = 11;
		} else if (sign.equals("Q")) {
			value = 12;
		} else if (sign.equals("K")) {
			value = 13;
		} else if (sign.equals("A")) {
			value = 14;
		} else {
			value = Integer.parseInt(sign);
		}
		return value;
	}

	public static int checkTheBestCard(String hand) {
		String[] cards = hand.split(" ");
		Map<String, Integer> cardMap = new HashMap<String, Integer>();
		for (int i = 0; i < cards.length; i++) {
			String key = cards[i].substring(0, 1);
			if (cardMap.containsKey(key)) {
				cardMap.put(key, cardMap.get(key) + 1);
			} else {
				cardMap.put(key, 1);
			}
		}
		
		Collection<Integer> valuesCard = cardMap.values();
		int bestCard = 2;
		if (valuesCard.contains(4)) {
			bestCard = getKeysJakJaTegoNienawidze(cardMap, 4);
		} else if (valuesCard.contains(3)) {
			bestCard = getKeysJakJaTegoNienawidze(cardMap, 3);
		} else if (valuesCard.contains(2)) {
			bestCard = getKeysJakJaTegoNienawidze(cardMap, 2);
		} else {
			bestCard = getKeysJakJaTegoNienawidze(cardMap, 1);
		}
		
		return bestCard;
	}
	
	private static int getKeysJakJaTegoNienawidze(Map<String, Integer> cardMap, int value) {
	    Set<String> keys = new HashSet<String>();
	    int bestCard = 0;
	    for (Entry<String, Integer> entry : cardMap.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            keys.add(entry.getKey());
	        }
	    }
	    int current = 0;
	    for (String s : keys) {
	    	current = parseOneCard(s.substring(0, 1));
	    	if(current > bestCard) {
	    		bestCard = current;
	    	}
	    }
	    return bestCard;
	}
}
