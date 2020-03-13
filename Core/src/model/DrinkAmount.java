package model;

public class DrinkAmount
{
	private Drink drink;
	private int count;

	public DrinkAmount(Drink drink, int count)
	{
		this.drink = drink;
		setCount(count);
	}

	public Drink getDrink()
	{
		return drink;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		if(count < 0)
		{
			throw new IllegalArgumentException("Count cannot be negative");
		}

		this.count = count;
	}

	public void decreaseAmount(int count)
	{
		if(this.count - count < 0)
		{
			throw new IllegalArgumentException("Count cannot be negative");
		}

		this.count -= count;
	}

	public int getPrice()
	{
		return drink.getPrice() * count;
	}
}
