package com.tuling.changestream.listener;


import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.data.mongodb.core.messaging.MessageListener;
import org.springframework.stereotype.Component;


/**
 * @author Fox
 * 配置mongo监听器，用于接收数据库的变更信息
 */
@Component
public class DocumentMessageListener<S, T> implements MessageListener<S, T> {

	@Override
	public void onMessage(Message<S, T> message) {

		System.out.println(String.format("Received Message in collection %s.\n\trawsource: %s\n\tconverted: %s",
				message.getProperties().getCollectionName(), message.getRaw(), message.getBody()));


	}

}