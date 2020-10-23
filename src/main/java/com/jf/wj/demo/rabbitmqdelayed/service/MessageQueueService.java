package com.jf.wj.demo.rabbitmqdelayed.service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 13:50
 * @Description: 消息队列服务接口
 * To change this template use File | Settings | File and Templates.
 */

public interface MessageQueueService {
    /**
     * 发送直接消息到队列
     * @param queueName 队列名称
     * @param message 消息内容
     */
     void sendDirectMsg(String queueName, String message);

    /**
     * 延迟发送消息到队列
     * @param queueName 队列名称
     * @param message 消息内容
     * @param times 延迟时间 单位毫秒
     */
    void sendDelayMsg(String queueName,String message,long times);

}
