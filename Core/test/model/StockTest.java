package model;

import model.Drink;
import model.DrinkAmount;
import model.Order;
import model.Stock;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StockTest
{

	@Test
	void addDrink()
	{
		Stock stock = new Stock();
		stock.addDrink(new DrinkAmount(new Drink("Corona", 10), 10));

		assertEquals(1, stock.getDrinks().length);
	}

	@Test
	void getDrinks()
	{
		Stock stock = new Stock();
		stock.addDrink(new DrinkAmount(new Drink("Corona", 10), 10));

		assertEquals(1, stock.getDrinks().length);
	}

	@Test
	void getDrink()
	{
		Drink corona = new Drink("Corona", 10);
		DrinkAmount drinkAmount = new DrinkAmount(corona, 10);
		Stock stock = new Stock();

		stock.addDrink(drinkAmount);

		assertEquals(drinkAmount, stock.getDrink(corona));
	}

	@Test
	void createOrder1()
	{
		Drink corona = new Drink("Corona", 10);
		Drink grolsch = new Drink("Grolsch", 10);
		Stock stock = new Stock();
		stock.addDrink(new DrinkAmount(corona, 10));
		stock.addDrink(new DrinkAmount(grolsch, 5));

		Order order = stock.createOrder(Arrays.asList(new DrinkAmount(corona, 2), new DrinkAmount(grolsch, 3), new DrinkAmount(corona, 1)));

		assertEquals(6, order.getDrinkCount());
		assertEquals(3, order.getSize());
	}

	@Test
	void createOrder2()
	{
		Drink corona = new Drink("Corona", 10);
		Drink grolsch = new Drink("Grolsch", 10);
		Stock stock = new Stock();
		stock.addDrink(new DrinkAmount(corona, 10));
		stock.addDrink(new DrinkAmount(grolsch, 5));

		assertThrows(IllegalArgumentException.class, () ->
		{
			Order order = stock.createOrder(Arrays.asList(new DrinkAmount(corona, 2), new DrinkAmount(grolsch, 6)));
		});
	}
}