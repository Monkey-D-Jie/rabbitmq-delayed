package com.jf.wj.demo.rabbitmqdelayed.consumer;

import com.jf.wj.demo.rabbitmqdelayed.common.MQConstant;
import com.jf.wj.demo.rabbitmqdelayed.entity.DLXMessage;
import com.jf.wj.demo.rabbitmqdelayed.service.MessageQueueService;
import com.jf.wj.demo.rabbitmqdelayed.util.JsonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 14:32
 * @Description: 死信接收处理消费者——处理自定义的过期消息
 * To change this template use File | Settings | File and Templates.
 */
@Component
@RabbitListener(queues = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
public class TradeConsumer {
    @Autowired
    private MessageQueueService messageQueueService;

    @RabbitHandler
    public void process(String content) {
        DLXMessage message = JsonUtil.convertJsonCollection(content, DLXMessage.class);
        messageQueueService.sendDirectMsg(message.getQueueName(), message.getContent());
    }

}
