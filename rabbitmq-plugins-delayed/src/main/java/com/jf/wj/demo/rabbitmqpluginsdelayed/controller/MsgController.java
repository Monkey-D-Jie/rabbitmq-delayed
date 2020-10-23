package com.jf.wj.demo.rabbitmqpluginsdelayed.controller;

import com.jf.wj.demo.rabbitmqdelayed.util.DateUtil;
import com.jf.wj.demo.rabbitmqpluginsdelayed.MsgEnum.MessageTypeEnum;
import com.jf.wj.demo.rabbitmqpluginsdelayed.common.MessageQueueConstants;
import com.jf.wj.demo.rabbitmqpluginsdelayed.entity.QueueMessage;
import com.jf.wj.demo.rabbitmqpluginsdelayed.service.MessageDelayPluginQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 13:58
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 * tip1:RestController的使用
 * 要注意的是，打上这个注解的类，意即表明该类为Controller类的转发处理器，
 * 同时表明对应类下的接口以json的形式返回(同@ResponseBody)
 * 这里是不能直接用@RestController(‘/test’)来拼接路径
 * 如果有需要的，要用@RequestMapping来做
 */
@Slf4j
@RestController
@RequestMapping("mq-delay-plugin")
public class MsgController {

    @Autowired
    private MessageDelayPluginQueueService messageDelayPluginQueueService;

    @RequestMapping("/send")
    public String send(){
        QueueMessage message = new QueueMessage(MessageQueueConstants.QUEUE_HELLO_NAME, "测试及时消息...");
        message.setType(MessageTypeEnum.DIRECT.getIndex());
        messageDelayPluginQueueService.send(message);
        return "plugins direct msg has been sent successfully!";
    }

    @RequestMapping("/send/delayed")
    public String sendDelayed(){
        log.info("{} send delayed msgs has been stated!", DateUtil.convert(DateUtil.DATE_FORMAT,new Date()));
        {
            //消息1    10秒延迟
            QueueMessage message = new QueueMessage(MessageQueueConstants.QUEUE_HELLO_NAME, "测试延时消息 001 --> 10s");
            message.setType(MessageTypeEnum.DELAYED.getIndex());
            message.setSeconds(10);
            messageDelayPluginQueueService.send(message);
        }
        {
            //消息2  30 秒延迟
            QueueMessage message = new QueueMessage(MessageQueueConstants.QUEUE_HELLO_NAME, "测试延时消息 002 --> 30s");
            message.setType(MessageTypeEnum.DELAYED.getIndex());
            message.setSeconds(30);
            messageDelayPluginQueueService.send(message);
        }
        {
            //消息3  5秒延迟
            QueueMessage message = new QueueMessage(MessageQueueConstants.QUEUE_HELLO_NAME, "测试延时消息 003 --> 5s");
            message.setType(MessageTypeEnum.DELAYED.getIndex());
            message.setSeconds(5);
            messageDelayPluginQueueService.send(message);
        }

        {
            //消息4  没延迟
            QueueMessage message = new QueueMessage(MessageQueueConstants.QUEUE_HELLO_NAME, "测试普通消息 004");
            message.setType(MessageTypeEnum.DIRECT.getIndex());
            messageDelayPluginQueueService.send(message);
        }
        return "plugins delayed msgs has been sent successfully!";
    }


}
