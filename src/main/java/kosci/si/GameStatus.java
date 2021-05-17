package kosci.si;

public class GameStatus {

	private final Integer[] catPoints = new Integer[] {null, null, null, null, null, null, null, null, null, null, null, null, null};
	private final boolean[] usedCategories = new boolean[13];

	public GameStatus () {
		for(int i=0; i<usedCategories.length; ++i)
			usedCategories[i] = false;
	}

	public Integer[] getCatPoints() {
		return catPoints;
	}

	public Integer getCategoryPoints(Category cat)
	{
		return catPoints[cat.getRowIndex()];
	}

	public void setCategoryPoints(int points, Category cat)
	{
		this.catPoints[cat.getRowIndex()] = points;
		usedCategories[cat.getRowIndex()] = true;
	}

	public boolean isCategoryAlreadyUsed(Category cat) {
		return getCategoryPoints(cat) != null;
	}

	public boolean[] getUsedCategories() {
		return usedCategories;
	}
}
