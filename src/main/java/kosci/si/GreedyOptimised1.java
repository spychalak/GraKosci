package kosci.si;

public class GreedyOptimised1 extends GreedyBaseDiceSI {
	private boolean[] reroll = new boolean[5];
	private double p[] = new double[32];
	
	private static final double[] averagePoints = new double[] {
		5, 10, 15, 20, 25, 30,
		30, 30, 25, 30, 40, 50, 30
	};
	
	public GreedyOptimised1() {}
	
	@Override
	public void Restart() {
	}
	
	@Override
	public boolean[] Reroll(int[] dices, Integer[] categories) {
		for(int i=0; i<32; ++i)
			p[i] = 0.0;
		
		for(int c = 0; c<12; ++c) {
			Category cat = Category.getByRowIndex(c);
			int[][] sets = HandsetsGenerator.allSets[c];
			if(categories[c] == null || c==Category.GENERAL.getRowIndex()) {
				for(int i=0; i<sets.length; ++i) {
					int[] set = sets[i];
					Probabilities.RerollDicesStruct rerollStruct =
						Probabilities.ProbabilityOfHandFrom(dices, cat, set);
					double probability = rerollStruct.probability;
					int reroll = rerollStruct.RerollToInt();
					double points = PointsCalculator.AveragePointsOfDstCategory(
							cat, dices, categories);
					p[reroll] += probability * points * averagePoints[c];
				}
			}
		}
		
		int max = 0;
		for(int i=1; i<32; ++i)
			if(p[i] > p[max])
				max = i;
		
		for(int i=0; i<5; ++i)
			reroll[i] = (max&(1<<i)) == (1<<i);
		
		return reroll;
	}

}

