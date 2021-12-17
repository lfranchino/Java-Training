package com.scottlogic.javatraining;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaTrainingApplicationTests {

	Matcher matcher;
	BuyOrder buyOrder1;
	BuyOrder buyOrder2;
	BuyOrder buyOrder3;
	BuyOrder buyOrder4;

	SellOrder sellOrder1;
	SellOrder sellOrder2;
	SellOrder sellOrder3;
	SellOrder sellOrder4;

	@BeforeEach
	void init() {
		matcher = new Matcher();
		buyOrder1 = new BuyOrder("1", 1, 30, "buy");
		buyOrder2 = new BuyOrder("2", 3, 40, "buy");
		buyOrder3 = new BuyOrder("3", 2, 30, "buy");
		buyOrder4 = new BuyOrder("4", 2, 30, "buy");

		sellOrder1 = new SellOrder("5", 1, 10, "sell");
		sellOrder2 = new SellOrder("6", 3, 50, "sell");
		sellOrder3 = new SellOrder("7", 2, 80, "sell");
		sellOrder4 = new SellOrder("8", 4, 30, "sell");
	}

	@Test
	@DisplayName("The lists are sorted correctly.")
	void test1() {
		matcher.storeOrder(buyOrder1);
		matcher.storeOrder(buyOrder2);
		matcher.storeOrder(buyOrder3);
		matcher.storeOrder(buyOrder4);

		matcher.storeOrder(sellOrder1);
		matcher.storeOrder(sellOrder2);
		matcher.storeOrder(sellOrder3);
		matcher.storeOrder(sellOrder4);

		assertEquals(matcher.buyList.indexOf(buyOrder1), 0);
		assertEquals(matcher.buyList.indexOf(buyOrder2), 3);
		assertEquals(matcher.buyList.indexOf(buyOrder3), 1);
		assertEquals(matcher.buyList.indexOf(buyOrder4), 2);

		assertEquals(matcher.sellList.indexOf(sellOrder1), 3);
		assertEquals(matcher.sellList.indexOf(sellOrder2), 1);
		assertEquals(matcher.sellList.indexOf(sellOrder3), 2);
		assertEquals(matcher.sellList.indexOf(sellOrder4), 0);
	}

	@Test
	@DisplayName("Matches orders correctly")
	void test2() {
		matcher.matchNewOrder(sellOrder1);
		matcher.matchNewOrder(sellOrder2);
		matcher.matchNewOrder(sellOrder3);
		matcher.matchNewOrder(sellOrder4);

		matcher.matchNewOrder(buyOrder1);
		matcher.matchNewOrder(buyOrder2);
		matcher.matchNewOrder(buyOrder3);
		matcher.matchNewOrder(buyOrder4);

		assertEquals(matcher.sellList.get(0), new SellOrder("8", 4, 30, "sell"));
		assertEquals(matcher.sellList.get(1), new SellOrder("6", 3, 10, "sell"));
		assertEquals(matcher.sellList.get(2), new SellOrder("7", 2, 20, "sell"));

		assertEquals(matcher.buyList.get(0), new BuyOrder("1", 1, 20, "buy"));
	}

	@Test
	@DisplayName("Orders are accumulated correctly")
	void Test3() {
		BuyOrder buyOrder5 = new BuyOrder("9", 1, 40, "buy");

		matcher.matchNewOrder(sellOrder1);
		matcher.matchNewOrder(sellOrder2);
		matcher.matchNewOrder(sellOrder3);
		matcher.matchNewOrder(sellOrder4);

		matcher.matchNewOrder(buyOrder1);
		matcher.matchNewOrder(buyOrder2);
		matcher.matchNewOrder(buyOrder3);
		matcher.matchNewOrder(buyOrder4);
		matcher.matchNewOrder(buyOrder5);

		assertEquals(matcher.accumulativeBuys.get(0).price, 1);
		assertEquals(matcher.accumulativeBuys.get(0).quantity, 60);

		assertEquals(matcher.accumulativeSells.get(0).price, 1);
		assertEquals(matcher.accumulativeSells.get(1).price, 2);
		assertEquals(matcher.accumulativeSells.get(2).price, 3);
		assertEquals(matcher.accumulativeSells.get(3).price, 4);

		assertEquals(matcher.accumulativeSells.get(0).quantity, 170);
		assertEquals(matcher.accumulativeSells.get(1).quantity, 160);
		assertEquals(matcher.accumulativeSells.get(2).quantity, 80);
		assertEquals(matcher.accumulativeSells.get(3).quantity, 30);
	}

	@Test
	@DisplayName("Orders aggregated correctly")
	void test4() {
		BuyOrder buyOrder5 = new BuyOrder("9", 1, 40, "buy");

		matcher.matchNewOrder(sellOrder1);
		matcher.matchNewOrder(sellOrder2);
		matcher.matchNewOrder(sellOrder3);
		matcher.matchNewOrder(sellOrder4);

		matcher.matchNewOrder(buyOrder1);
		matcher.matchNewOrder(buyOrder2);
		matcher.matchNewOrder(buyOrder3);
		matcher.matchNewOrder(buyOrder4);
		matcher.matchNewOrder(buyOrder5);

		assertEquals(matcher.buyAggregates.get(0).price, 1);
		assertEquals(matcher.buyAggregates.get(0).quantity, 60);

		assertEquals(matcher.sellAggregates.get(0).price, 4);
		assertEquals(matcher.sellAggregates.get(1).price, 3);
		assertEquals(matcher.sellAggregates.get(2).price, 2);
		assertEquals(matcher.sellAggregates.get(3).price, 1);

		assertEquals(matcher.sellAggregates.get(0).quantity, 30);
		assertEquals(matcher.sellAggregates.get(1).quantity, 50);
		assertEquals(matcher.sellAggregates.get(2).quantity, 80);
		assertEquals(matcher.sellAggregates.get(3).quantity, 10);
	}

	@Test
	@DisplayName("Orders are paired and stored")
	void test5() {
		matcher.matchNewOrder(sellOrder1);
		matcher.matchNewOrder(buyOrder1);

		assertEquals(matcher.pairedOrders.get(0).buyOrder, new BuyOrder("1", 1, 30, "buy"));
		assertEquals(matcher.pairedOrders.get(0).sellOrder, new SellOrder("5", 1, 10, "sell"));
	}
}
