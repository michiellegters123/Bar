package model;

import java.util.ArrayList;

public class Order
{
	private ArrayList<DrinkAmount> drinks;

	public Order()
	{
		drinks = new ArrayList<>();
	}

	public Order(ArrayList<DrinkAmount> drinks)
	{
		this.drinks = drinks;
	}

	public int getSize()
	{
		return drinks.size();
	}

	public int getDrinkCount()
	{
		return drinks.stream().mapToInt(DrinkAmount::getCount).sum();
	}


	public void addDrink(DrinkAmount drinkAmount)
	{
		drinks.add(drinkAmount);
	}

	public int getPrice()
	{
		return drinks.stream().mapToInt(DrinkAmount::getPrice).sum();
	}

	public DrinkAmount[] getDrinks()
	{
		return drinks.toArray(new DrinkAmount[0]);
	}
}
