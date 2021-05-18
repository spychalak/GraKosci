package kosci.si;

// Assumes sorted arguments

public class Probabilities {

	public static float ProbabilityOfHandFrom(int[] dices, int[] dst) {
		int dicesToRoll = 0;
		for(boolean b : WhichDicesToReroll(dices, dst)) {
			if(b)
				dicesToRoll++;
		}

	}

	public static boolean[] WhichDicesToReroll(int[] dices, int[] dst) {

	}

	public static float ProbabilityOfRethrowingDices(int[] dices,
		boolean[] reroll, int[] dst) {

	}



}
