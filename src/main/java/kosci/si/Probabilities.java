package kosci.si;

// Assumes sorted arguments

public class Probabilities {

	private class RerollDicesStruct {
		public boolean[] reroll;
		public boolean[] missing;
	}

	public static float ProbabilityOfHandFrom(int[] dices, int[] dst) {
		boolean[] reroll = WhichDicesToReroll(dices, dst);
		return ProbabilityOfRethrowingDices(dices, reroll, dst);
	}

	public static boolean[] WhichDicesToReroll(int[] dices, int[] dst) {
		boolean[] reroll = new boolean[] {true, true, true, true, true};
		int it = 0;
		for(int dice : dst) {
			for(; dices[it] < dice; ++it);
			if(dices[it] == dice) {
				reroll[it] = false;
				++it;
			}
		}
		return reroll;
	}

	public static float ProbabilityOfRethrowingDices(int[] dices,
		boolean[] reroll, int[] dst) {
		return 0.0f;
	}



}
