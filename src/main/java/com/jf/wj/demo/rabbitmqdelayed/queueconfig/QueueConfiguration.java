package com.jf.wj.demo.rabbitmqdelayed.queueconfig;

import com.jf.wj.demo.rabbitmqdelayed.common.MQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 13:54
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Configuration
public class QueueConfiguration {
    //默认交换机
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(MQConstant.DEFAULT_EXCHANGE, true, false);
    }
    /*********************    hello 队列  测试  start  *****************/
    @Bean
    public Queue queue() {
        Queue queue = new Queue(MQConstant.HELLO_QUEUE_NAME,true);
        return queue;
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(MQConstant.HELLO_QUEUE_NAME);
    }
    /*********************    hello 队列  测试   end *****************/
    /*********************    (过期)延迟队列  测试  start  *****************/
    @Bean
    public Queue repeatTradeQueue() {
        Queue queue = new Queue(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME,true,false,false);
        return queue;
    }

    @Bean
    public Binding  drepeatTradeBinding() {
        return BindingBuilder.bind(repeatTradeQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
    }
    @Bean
    public Queue deadLetterQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", MQConstant.DEFAULT_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
        Queue queue = new Queue(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME,true,false,false,arguments);
        System.out.println("arguments :" + queue.getArguments());
        return queue;
    }

    @Bean
    public Binding  deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME);
    }
    /*********************    (过期)延迟队列  测试  end  *****************/



}
