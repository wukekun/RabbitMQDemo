package com.bdqn.test;

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
	public static Connection getConnection(){
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = null;
		try {
			//factory里面添加四个信息
			factory.setHost("192.168.91.187");
			factory.setPort(5672);
			factory.setUsername("admin");
			factory.setPassword("123456");
			connection = factory.newConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
