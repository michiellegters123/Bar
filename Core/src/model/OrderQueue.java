package model;

import java.util.ArrayList;

public class OrderQueue
{
	private ArrayList<Order> orders = new ArrayList<>();

	public void addOrder(Order order)
	{
		orders.add(order);
	}

	public int getOrderCount()
	{
		return orders.size();
	}

	public Order getNextOrder()
	{
		if(orders.size() <= 0)
			throw new IndexOutOfBoundsException("No orders left");

		return orders.get(0);
	}

	public Order consumeOrder()
	{
		return orders.remove(orders.indexOf(getNextOrder()));
	}

	public ArrayList<Order> getOrders()
	{
		return orders;
	}
}
