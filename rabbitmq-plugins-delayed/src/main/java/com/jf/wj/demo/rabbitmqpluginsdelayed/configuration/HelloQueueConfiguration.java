package com.jf.wj.demo.rabbitmqpluginsdelayed.configuration;

import com.jf.wj.demo.rabbitmqpluginsdelayed.common.MessageQueueConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 15:28
 * @Description:  hello队列配置
 * To change this template use File | Settings | File and Templates.
 */
@Configuration
public class HelloQueueConfiguration {

    @Autowired
    @Qualifier("defaultDirectExchange")
    private DirectExchange exchange;


    @Bean
    public Queue helloQueue() {
        Queue queue = new Queue(MessageQueueConstants.QUEUE_HELLO_NAME,true,false,false);
        return queue;
    }

    @Bean
    public Binding helloBinding() {
        return BindingBuilder.bind(helloQueue()).to(exchange).with(MessageQueueConstants.QUEUE_HELLO_NAME);
    }

}
