package kosci.si;

// Assumes arguments are sorted
// dst can have variable length (ex.: three ones of a kind passes {1,1,1})

public class Probabilities {

	public static class RerollDicesStruct {
		public RerollDicesStruct(int[] dst) {
			missing = new boolean[dst.length];
		}
		public int RerollToInt() {
			int r = 0;
			for(int i=0; i<5; ++i)
				if(reroll[i])
					r |= 1<<i;
			return r;
		}
		public boolean[] reroll = new boolean[5];
		public boolean[] missing;
		public int missingCount = 0;
		public int rerollCount = 0;
		public int missingToThree = 0;
		public double probability = 0.0;
	}

	public static RerollDicesStruct	ProbabilityOfHandFrom(int[] dices, Category cat,
			int[] dst) {
		RerollDicesStruct reroll = WhichDicesToReroll(dices, cat, dst);
		reroll.probability = ProbabilityOfRethrowingDices(dices, reroll, cat, dst);
		return reroll;
	}

	public static RerollDicesStruct WhichDicesToReroll(int[] dices,
			Category cat, int[] dst) {
		RerollDicesStruct ret = new RerollDicesStruct(dst);
		
		for(int i=0; i<5; ++i)
			ret.reroll[i] = true;
		ret.missingCount = dst.length;
		ret.rerollCount = 5;
		for(int i=0; i<dst.length; ++i) {
			ret.missing[i] = true;
			for(int j=0; j<dices.length; ++j) {
				if(ret.reroll[j] == true) {
					if(dices[j] == dst[i]) {
						ret.missingCount--;
						ret.rerollCount--;
						ret.reroll[j] = false;
						ret.missing[i] = false;
						break;
					}
				}
			}
		}
		if(cat == Category.FULL_HOUSE) {
			int threeDices = dst[2];
			int threes = 0;
			for(int d : dices) {
				if(d == threeDices)
					++threes;
			}
			if(threes >= 3)
				ret.missingToThree = 0;
			else
				ret.missingToThree = 3 - threes;
		}
		return ret;
	}

	public static double ProbabilityOfRethrowingDices(int[] dices, 
		RerollDicesStruct reroll, Category cat, int[] dst) {
		return probabilities[cat.getRowIndex()][reroll.missingCount]
			[reroll.rerollCount][reroll.missingToThree];
	}
	
	final private static double[][][][] probabilities = GenerateProbabilitiesArray();
	
	public static double[][][][] GenerateProbabilitiesArray() {
		double p[][][][] = new double[13][6][6][4];
		for(double[][][] a : p) {
			for(double[][] b : a) {
				for(double[] c : b) {
					c[0] = 0;
					c[1] = 0;
					c[2] = 0;
					c[3] = 0;
				}
			}
		}
		for(int m=0; m<=5; ++m) {
			for(int r=0; r<=5; ++r) {
				for(int n=0; n<=3; ++n) {
					p[Category.THREE_OF_A_KIND.getRowIndex()][m][r][n] =
						SetProbabilities.threeOfAKind(m, r);
					p[Category.FOUR_OF_A_KIND.getRowIndex()][m][r][n] =
						SetProbabilities.fourOfAKind(m, r);
					p[Category.FULL_HOUSE.getRowIndex()][m][r][n] =
						SetProbabilities.fullHouse(r, n);
					p[Category.SMALL_STRAIGHT.getRowIndex()][m][r][n] =
						SetProbabilities.smallStraight(m, r);
					p[Category.BIG_STRAIGHT.getRowIndex()][m][r][n] =
						SetProbabilities.bigStraight(r);
					p[Category.GENERAL.getRowIndex()][m][r][n] =
						SetProbabilities.general(r);
					for(int i=0; i<6; ++i){
						p[i][m][r][n] =
								SetProbabilities.upperTable(m,r);
					}
				}
			}
		}
		return p;
	}
}

