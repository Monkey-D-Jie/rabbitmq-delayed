package com.jf.wj.demo.rabbitmqpluginsdelayed.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-12 15:55
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class MessageException extends RuntimeException{

    private int exCode;

    public MessageException(int  exCode, String message) {
        super(message);
        this.exCode = exCode;
    }
}
