package com.jf.wj.demo.rabbitmqdelayed.service.impl;

import com.jf.wj.demo.rabbitmqdelayed.common.MQConstant;
import com.jf.wj.demo.rabbitmqdelayed.entity.DLXMessage;
import com.jf.wj.demo.rabbitmqdelayed.service.MessageQueueService;
import com.jf.wj.demo.rabbitmqdelayed.util.JsonUtil;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 13:50
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Service
public class MessageQueueServiceImpl implements MessageQueueService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendDirectMsg(String queueName, String msg) {
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,queueName, msg);
    }

    @Override
    public void sendDelayMsg(String queueName, String msg, long times) {
        DLXMessage dlxMessage = new DLXMessage(queueName,msg,times);
        MessagePostProcessor processor = message -> {
            message.getMessageProperties().setExpiration(times + "");
            return message;
        };
        dlxMessage.setExchange(MQConstant.DEFAULT_EXCHANGE);
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, JsonUtil.convertObjectJson(dlxMessage), processor);
    }

}
