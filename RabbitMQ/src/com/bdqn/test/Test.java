package com.bdqn.test;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Test {

	@org.junit.Test
	public void send() throws IOException {
		// 1.拿到连接
		Connection conn = ConnectionUtil.getConnection();
		Channel channel = null;

		try {
			// 2.创建通道
			channel = conn.createChannel();
			// 3.确认队列
			channel.queueDeclare("吴科坤", false, false, false, null);
			String str = "hello world";
			// 4.发送信息
			channel.basicPublish("", "吴科坤", null, str.getBytes());
			System.out.println("发送成功!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (channel != null) {
				channel.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	@org.junit.Test
	public void revice() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		// 1.获取连接
		Connection conn = ConnectionUtil.getConnection();
		// 2.获取通道
		Channel channel = conn.createChannel();
		// 3.确认队列
		channel.queueDeclare("吴科坤", false, false, false, null);
		// 4.创建消费者
		QueueingConsumer consumner = new QueueingConsumer(channel);
		channel.basicConsume("吴科坤", true, consumner);
		// 5.获取信息
		while (true) {
			QueueingConsumer.Delivery delivery = consumner.nextDelivery();
			System.out.println(new String(delivery.getBody()));
		}
	}

}
