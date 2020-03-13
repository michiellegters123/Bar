package model;

import model.Drink;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest
{

	@org.junit.jupiter.api.Test
	void getName()
	{
		Drink drink = new Drink("Corona", 10);

		assertEquals("Corona", drink.getName());
	}

	@org.junit.jupiter.api.Test
	void setName()
	{
		Drink drink = new Drink("Grolsch", 10);
		drink.setName("Corona");

		assertEquals("Corona", drink.getName());
	}

	@org.junit.jupiter.api.Test
	void getPrice()
	{
		Drink drink = new Drink("Grolsch", 10);
		assertEquals(10, drink.getPrice());
	}

	@org.junit.jupiter.api.Test
	void setPrice()
	{
		Drink drink = new Drink("Grolsch", 10);
		drink.setPrice(5);
		assertEquals(5, drink.getPrice());
	}
}