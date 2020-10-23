package com.jf.wj.demo.rabbitmqpluginsdelayed.consumer;

import com.jf.wj.demo.rabbitmqdelayed.util.JsonUtil;
import com.jf.wj.demo.rabbitmqpluginsdelayed.MsgEnum.MessageTypeEnum;
import com.jf.wj.demo.rabbitmqpluginsdelayed.common.MessageQueueConstants;
import com.jf.wj.demo.rabbitmqpluginsdelayed.entity.QueueMessage;
import com.jf.wj.demo.rabbitmqpluginsdelayed.service.MessageDelayPluginQueueService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 15:46
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Component
@RabbitListener(queues = MessageQueueConstants.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
public class TradeConsumer {
    @Autowired
    private MessageDelayPluginQueueService messageDelayPluginQueueService;

    @RabbitHandler
    public void process(String content) {
        QueueMessage message = JsonUtil.convertJsonObjectByFastjson(content, QueueMessage.class);
        message.setType(MessageTypeEnum.DIRECT.getIndex());
        messageDelayPluginQueueService.send(message);
    }

}
