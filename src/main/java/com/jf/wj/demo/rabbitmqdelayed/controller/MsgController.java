package com.jf.wj.demo.rabbitmqdelayed.controller;

import com.jf.wj.demo.rabbitmqdelayed.common.MQConstant;
import com.jf.wj.demo.rabbitmqdelayed.service.MessageQueueService;
import com.jf.wj.demo.rabbitmqdelayed.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("rabbitmq")
public class MsgController {

    @Autowired
    private MessageQueueService messageQueueService;

    /**
     * 直连型交换机发送消息
     * 成功：
     * 控制台的对应打印信息
     *  在2020-10-23 14:22:30监听到了从队列HELLO中发来的消息---->>>2020-10-23 14:22:30---->>>Direct：Hello world！
     * @return
     */
    @GetMapping("/sendDirect")
    public String sendDirectMessage() {
        messageQueueService.sendDirectMsg(MQConstant.HELLO_QUEUE_NAME, DateUtil.convert(DateUtil.DATE_FORMAT,new Date())+"---->>>Direct：Hello world！");
        return "ok ! direct msg has been sent successfully!";
    }

    /**
     * 死信队列发送消息
     * 成功！
     * 控制台打印信息
     * 2020-10-23 14:44:25发送了一条延时消息
     * 在2020-10-23 14:44:35监听到了从队列HELLO中发来的消息---->>>2020-10-23 14:44:25---->>>Delay：Hello world！Delay~!!
     * 延时10s
     * 过程分析：
     * ①：外部请求被转发到这里后。调用sendDelayMsg方法。在该方法中，
     * 将外部传入的消息和延时时间做包装处理-->使用MessagePostProcessor为消息
     * 设定延迟发送的时间。
     * ②：将包装好的延时消息通过路由键DEFAULT_DEAD_LETTER_QUEUE_NAME(相当于是一个特殊的死信队列)发送到交换机DEFAULT_CHANGE上。
     * ③：在QueueConfiguration中，将deadLetterQueue通过DEFAULT_DEAD_LETTER_QUEUE_NAME路由键绑定在了默认的交换机DEFAULT_CHANGE上。
     * ④：消息的流转过程
     * 延时消息---->找到了DEFAULT_CHANGE---->然后经由DEFAULT_DEAD_LETTER_QUEUE_NAME路由键找到下一个路标----->
     * 然后又到了绑定在默认交换机上的deadLetterQueue中---->“死信”队列中又指定了绑定在默认交换机上的DEFAULT_REPEAT_TRADE_QUEUE_NAME
     * 队列来监听延时消息。于是当有消息过来的时候，DEFAULT_REPEAT_TRADE_QUEUE_NAME的consumer类TradeConsumer借助路由键DEFAULT_REPEAT_TRADE_QUEUE_NAME
     * 获取到了信息，然后执行process方法，消费过来的延时消息。
     * @return
     */
    @GetMapping("/sendDelay")
    public String sendDelayMessage() {
        log.info("{}发送了一条延时消息",DateUtil.convert(DateUtil.DATE_FORMAT,new Date()));
        messageQueueService.sendDelayMsg(MQConstant.HELLO_QUEUE_NAME, DateUtil.convert(DateUtil.DATE_FORMAT,new Date())+"---->>>Delay：Hello world！Delay~!!",10000);
        return "ok ! delay  msg has been sent successfully!";
    }
}
