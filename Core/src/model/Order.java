package model;

import java.util.ArrayList;

public class Order
{
	private ArrayList<DrinkAmount> drinks;
	private String table;

	public Order(String table)
	{
		this.table = table;
		drinks = new ArrayList<>();
	}

	public Order(ArrayList<DrinkAmount> drinks, String table)
	{
		this.drinks = drinks;
		this.table = table;
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

	public String getTable()
	{
		return table;
	}

	public void setTable(String table)
	{
		this.table = table;
	}
}
