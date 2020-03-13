package model;

import model.Drink;
import model.DrinkAmount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkAmountTest
{

	@Test
	void getDrink()
	{
		Drink drink = new Drink("Corona", 10);
		DrinkAmount drinkAmount = new DrinkAmount(drink, 2);
		assertEquals(drink, drinkAmount.getDrink());
	}

	@Test
	void getCount()
	{
		DrinkAmount drinkAmount = new DrinkAmount(new Drink("Corona", 10), 2);
		assertEquals(2, drinkAmount.getCount());
	}

	@Test
	void setCount()
	{
		DrinkAmount drinkAmount = new DrinkAmount(new Drink("Corona", 10), 2);
		drinkAmount.setCount(3);
		assertEquals(3, drinkAmount.getCount());
	}

	@Test
	void getPrice()
	{
		DrinkAmount drinkAmount = new DrinkAmount(new Drink("Corona", 10), 2);
		assertEquals(20, drinkAmount.getPrice());
	}

	@Test
	void decreaseAmount1()
	{
		DrinkAmount drinkAmount = new DrinkAmount(new Drink("Corona", 10), 2);
		drinkAmount.decreaseAmount(2);
		assertEquals(0, drinkAmount.getCount());
	}

	@Test
	void decreaseAmount2()
	{
		DrinkAmount drinkAmount = new DrinkAmount(new Drink("Corona", 10), 2);

		assertThrows(IllegalArgumentException.class, () ->
		{
			drinkAmount.decreaseAmount(3);
		});
	}
}