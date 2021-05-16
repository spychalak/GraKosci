package kosci.si;

public class GameStatus {

	private final Integer[] catPoints = new Integer[] {null, null, null, null, null, null, null, null, null, null, null, null, null};

	public GameStatus () {

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
	}

	public boolean isCategoryAlreadyUsed(Category cat) {
		return getCategoryPoints(cat) != null;
	}
}
