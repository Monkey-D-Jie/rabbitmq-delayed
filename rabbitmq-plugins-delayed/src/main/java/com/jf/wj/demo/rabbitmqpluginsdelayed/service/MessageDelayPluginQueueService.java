package com.jf.wj.demo.rabbitmqpluginsdelayed.service;

import com.jf.wj.demo.rabbitmqpluginsdelayed.entity.QueueMessage;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 15:29
 * @Description: 消息队列接口
 * To change this template use File | Settings | File and Templates.
 */

public interface MessageDelayPluginQueueService {
    /**
     * 发送消息，返回是否发送成功
     * @param message
     * @return
     */
    void send(QueueMessage message);
}
