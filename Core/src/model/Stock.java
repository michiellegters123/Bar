package model;

import java.util.Collection;
import java.util.HashMap;

public class Stock
{
	private HashMap<Drink, DrinkAmount> drinks = new HashMap();

	public void addDrink(DrinkAmount drinkAmount)
	{
		drinks.put(drinkAmount.getDrink(), drinkAmount);
	}

	public DrinkAmount getDrink(Drink drink)
	{
		return drinks.get(drink);
	}

	public DrinkAmount[] getDrinks()
	{
		return drinks.values().toArray(new DrinkAmount[0]);
	}

	public Order createOrder(Collection<DrinkAmount> amounts)
	{
		Order order = new Order();

		for(var drink : amounts)
		{
			DrinkAmount myAmounts = getDrink(drink.getDrink());

			myAmounts.decreaseAmount(drink.getCount());

			order.addDrink(new DrinkAmount(drink.getDrink(), drink.getCount()));
		}

		return order;
	}
}
