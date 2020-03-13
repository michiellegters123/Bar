package model;

import model.Order;
import model.OrderQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderQueueTest
{

	@Test
	void addOrder()
	{
		OrderQueue queue = new OrderQueue();

		queue.addOrder(new Order());
		queue.addOrder(new Order());

		assertEquals(2, queue.getOrderCount());
	}

	@Test
	void getOrderCount()
	{
		OrderQueue queue = new OrderQueue();

		queue.addOrder(new Order());
		queue.addOrder(new Order());

		assertEquals(2, queue.getOrderCount());
	}

	@Test
	void getNextOrder()
	{
		OrderQueue queue = new OrderQueue();

		queue.addOrder(new Order());

		assertNotNull(queue.getNextOrder());
	}

	@Test
	void consumeOrder1()
	{
		OrderQueue queue = new OrderQueue();

		queue.addOrder(new Order());
		queue.addOrder(new Order());

		queue.consumeOrder();

		assertEquals(1, queue.getOrderCount());
	}

	@Test
	void consumeOrder2()
	{
		OrderQueue queue = new OrderQueue();
		Order order = new Order();
		queue.addOrder(order);

		;

		assertEquals(order, queue.consumeOrder());
		assertEquals(0, queue.getOrderCount());
	}
}