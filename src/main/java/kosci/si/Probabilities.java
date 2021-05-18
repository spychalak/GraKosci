package kosci.si;

// Assumes arguments are sorted
// dst can have variable length (ex.: three ones of a kind passes {1,1,1})

public class Probabilities {

	private static class RerollDicesStruct {
		public RerollDicesStruct(int[] dst) {
			missing = new boolean[dst.length];
		}
		public boolean[] reroll = new boolean[5];
		public boolean[] missing;
		public int missingCount = 0;
		public int rerollCount = 0;
	}

	public static float ProbabilityOfHandFrom(int[] dices, int[] dst) {
		RerollDicesStruct reroll = WhichDicesToReroll(dices, dst);
		return ProbabilityOfRethrowingDices(dices, reroll, dst);
	}

	public static RerollDicesStruct WhichDicesToReroll(int[] dices, int[] dst) {
		RerollDicesStruct ret = new RerollDicesStruct(dst);
		int it = 0;
		for(int i=0; i<dst.length; ++i) {
			for(; dices[it] < dst[i] && it<dices.length; ++it, ++ret.rerollCount)
				ret.reroll[it] = true;
			if(it < dices.length) {
				if(dices[it] == dst[i]) {
					ret.reroll[it] = false;
					ret.missing[i] = false;
					++it;
				} else {
					ret.reroll[it] = true;
					ret.missing[i] = true;
					++ret.missingCount;
				}
			} else {
				ret.missing[i] = true;
				++ret.missingCount;
			}
		}
		return ret;
	}

	public static float ProbabilityOfRethrowingDices(int[] dices,
		RerollDicesStruct reroll, int[] dst) {


		return 0.0f;
	}



}
