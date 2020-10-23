package com.jf.wj.demo.rabbitmqpluginsdelayed.consumer;

import com.jf.wj.demo.rabbitmqdelayed.util.DateUtil;
import com.jf.wj.demo.rabbitmqpluginsdelayed.common.MessageQueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 15:49
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Component
@RabbitListener(queues = MessageQueueConstants.QUEUE_HELLO_NAME)
@Slf4j
public class HelloConsumer {

    @RabbitHandler
    public void process(String content) {
        log.info("{}receive hello direct msg---->{}", DateUtil.convert(DateUtil.DATE_FORMAT,new Date()),content);
    }

}
