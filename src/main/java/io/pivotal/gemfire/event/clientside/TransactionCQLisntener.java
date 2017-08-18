package io.pivotal.gemfire.event.clientside;

import io.pivotal.domain.Transaction;

import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.cache.client.PoolManager;
import com.gemstone.gemfire.cache.query.CqEvent;
import com.gemstone.gemfire.cache.query.CqListener;

public class TransactionCQLisntener implements CqListener {

	private static ClientCache cache;
	private static Region<String, String> orders;

	static {
		cache = ClientCacheFactory.getAnyInstance();
		orders = cache.getRegion("orders");
	}

	public void close() {
		System.out.println("TransactionCQClient:Received Close Event");
	}

	public void onError(CqEvent event) {

		System.out.println("TransactionCQClient:Received onError event");
		System.out.println("TransactionCQClient:Throwable: " + event.getThrowable());
	}

	public void onEvent(CqEvent event) {


		orders.put((String) event.getKey(),
				(String)event.getNewValue());

		System.out.println("\nTransaction Key is:  " + event.getKey());
//		Transaction transaction = (Transaction) event.getNewValue();
//		System.out.println("Transaction Details: " + transaction);
	}

}
