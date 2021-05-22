package kosci.si;

import java.util.Random;
import java.util.Arrays;

public class TestProbabilities {
	
	public static String ToString(int[] ar) {
		String str = "";
		int i=0;
		for(i=0; i<ar.length; ++i)
			str += ar[i];
		for(; i<5; ++i)
			str += " ";
		return str;
	}
	
	public static String ToString(boolean[] ar) {
		String str = "";
		int i=0;
		for(i=0; i<ar.length; ++i)
			str += ar[i] ? "1" : "0";
		for(; i<5; ++i)
			str += " ";
		return str;
	}
	
	public static void main(String[] args) {
		int[] dices = new int[5];
		int[] dst;
		Random random = new Random();
		for(int I=0; I<1000; ++I) {
			for(int i=0; i<5; ++i)
				dices[i] = random.nextInt(6)+1;
			Arrays.sort(dices);
			int k = random.nextInt(6)+6;
			int j = random.nextInt(HandsetsGenerator.allSets[k].length);
			dst = HandsetsGenerator.allSets[k][j];
			
			Probabilities.RerollDicesStruct reroll =
				Probabilities.WhichDicesToReroll(dices,
						Category.getByRowIndex(k), dst);
			
			System.out.println(ToString(dices) + " -> " + ToString(dst)
					+ "   r" + ToString(reroll.reroll) + " " +
					reroll.rerollCount + "   m" + 
					ToString(reroll.missing) + " " + reroll.missingCount
					+ "   3:" + reroll.missingToThree);
		}
	}
}

