package com.jf.wj.demo.rabbitmqpluginsdelayed.service.impl;

import com.jf.wj.demo.rabbitmqdelayed.util.JsonUtil;
import com.jf.wj.demo.rabbitmqpluginsdelayed.msgEnum.MessageTypeEnum;
import com.jf.wj.demo.rabbitmqpluginsdelayed.common.MessageQueueConstants;
import com.jf.wj.demo.rabbitmqpluginsdelayed.entity.QueueMessage;
import com.jf.wj.demo.rabbitmqpluginsdelayed.exception.MessageException;
import com.jf.wj.demo.rabbitmqpluginsdelayed.service.MessageDelayPluginQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 15:30
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Service
@Slf4j
public class MessageDelayPluginQueueServiceImpl implements MessageDelayPluginQueueService {
    @Autowired
    private RabbitTemplate rabbitTemplate;




    @Override
    public void send(QueueMessage message) {
//        this.checkMessage(message);
        //即时消息
        if(message.getType() == MessageTypeEnum.DIRECT.getIndex()){
            this.sendMessage(MessageQueueConstants.DEFAULT_DIRECT_EXCHANGE_NAME,message.getQueueName(),message.getMessage());
        }
        //延时消息
        if(message.getType() == MessageTypeEnum.DELAYED.getIndex()){
            sendTimeMessage(message);
        }
    }

    private void sendMessage(String exchange,String queueName,String msg){
        rabbitTemplate.convertAndSend(exchange,queueName, msg);
    }

    /**
     * 延时消息的消息传递流程
     * 发送消息--->延时封装--->设置延时对象头--->根据指定的routingkey发送到对应的交换机上
     * --->进入到延迟类信息的特定延时队列中--->延时队列监听消息，解析出发送过来的信息后，再发送到直接型消息队列中
     * --->监听直接消息型的消费者，消费由延时队列传过来的信息
     *
     *
     * @param message
     */
    public void sendTimeMessage(QueueMessage message) {
        int seconds = message.getSeconds();
        // 直接发送，无需进入死信队列
        if(seconds <= 0){
            sendMessage(MessageQueueConstants.DEFAULT_DIRECT_EXCHANGE_NAME,message.getQueueName(), message.getMessage());
        }else{
            //rabbit默认为毫秒级
            long times = seconds * 1000;
            MessagePostProcessor processor = s -> {
                s.getMessageProperties().setHeader("x-delay", times);
                return s;
            };
            //设置 x-delay 之后  将消息投递到专属交换机 ， 转发队列
            rabbitTemplate.convertAndSend(MessageQueueConstants.DEFAULT_DELAYED_EXCHANGE,MessageQueueConstants.DEFAULT_DELAYED_ROUTING, JsonUtil.convertObjectJsonByFastjson(message), processor);
        }
    }




    private void checkMessage(QueueMessage message){
        if (StringUtils.isEmpty(message.getExchange())) {
            throw new MessageException(10, "发送消息格式错误: 消息交换机(exchange)不能为空!");
        }
        if(message.getGroup() == null){
            throw new MessageException(10, "发送消息格式错误: 消息组(group)不能为空!");
        }
        if(message.getType() == null){
            throw new MessageException(10, "发送消息格式错误: 消息类型(type)不能为空!");
        }
        if(message.getStatus() == null){
            throw new MessageException(10, "发送消息格式错误: 消息状态(status)不能为空!");
        }
        if(StringUtils.isEmpty(message.getQueueName())){
            throw new MessageException(10, "发送消息格式错误: 消息目标名称(queueName)不能为空!");
        }
        if (StringUtils.isEmpty(message.getMessage())) {
            throw new MessageException(10, "发送消息格式错误: 消息内容(message)不能为空!");
        }
    }

}
