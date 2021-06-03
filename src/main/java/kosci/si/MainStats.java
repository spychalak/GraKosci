package kosci.si;
import java.io.*;

public class MainStats {
	private FileWriter file;
	private int count;
	
	public MainStats(String fileName, int count) throws IOException {
		file = new FileWriter(new File(fileName));
		this.count = count;
	}
	
	public void End() throws IOException {
		file.close();
	}
	
	public static void main(String[] args) throws IOException {
		MainStats m = new MainStats("log.csv", 100000);
		m.Run();
		m.End();
	}
	
	public void Run() throws IOException {
		Test("random", new RandomStrategy());
		Test("greedy category", new GreedyBaseDiceSI());
		Test("greedy si", new GreedySI1());
		Test("grreedy optimised", new GreedyOptimised1());
	}
	
	private void Test(String name, DiceSI si) throws IOException {
		long sum = 0;
		int maxGeneral = 0;
		int maxPoints = 0;
		
		int[] points = new int[2048];
		for(int i=0; i<points.length; ++i)
			points[i] = 0;
		
		for(int i=0; i<count; ++i) {
			si.Restart();

			Game game = new Game();
			for (int roundNo = 0; roundNo < game.getRoundsCount(); roundNo++) {
				int[] dices = game.throwDices();

				boolean[] rethrow = si.Reroll(dices, game.getGameStatus().getCatPoints());
				dices = game.rethrowDices(rethrow);
				rethrow = si.Reroll(dices, game.getGameStatus().getCatPoints());
				dices = game.rethrowDices(rethrow);
				Category category = si.ChooseCategory(dices, game.getGameStatus().getCatPoints());
				game.chooseCategory(category);
			}
			sum += (long)game.getResult();
			int s = game.getGameStatus().getCatPoints()[Category.GENERAL.getRowIndex()];
			if(s > maxGeneral)
				maxGeneral = s;
			if(maxPoints < game.getResult())
				maxPoints = game.getResult();
			points[game.getResult()]++;
		}
		
		int end=points.length;
		file.write(name);
		for(;points[end-1]==0 && end>0; --end);
		for(int i=0; i<end; ++i)
			file.write("," + points[i]);
		file.write("\n");
		
		System.out.println("Average result: " + ((double)sum/(double)count));
		System.out.println("Max general: " + maxGeneral);
		System.out.println("Max points: " + maxPoints);
	}
}

