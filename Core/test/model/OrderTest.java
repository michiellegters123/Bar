package model;

import model.Drink;
import model.DrinkAmount;
import model.Order;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest
{

	@Test
	void getSize()
	{
		Order order = new Order();
		order.addDrink(new DrinkAmount(new Drink("Corona", 10), 10));

		assertEquals(1, order.getSize());
	}

	@Test
	void addDrink()
	{
		Order order = new Order();
		order.addDrink(new DrinkAmount(new Drink("Corona", 10), 10));

		assertEquals(1, order.getSize());
	}

	@Test
	void getPrice()
	{
		Order order = new Order();
		order.addDrink(new DrinkAmount(new Drink("Corona", 10), 2));
		order.addDrink(new DrinkAmount(new Drink("Grolsch", 5), 10));

		assertEquals(70, order.getPrice());
	}

	@Test
	void getDrinkCount()
	{
		Order order = new Order(new ArrayList<>(Arrays.asList(
				new DrinkAmount(new Drink("Corona", 10), 2),
				new DrinkAmount(new Drink("Grolsch", 5), 10)
		)));

		assertEquals(12, order.getDrinkCount());
	}
}