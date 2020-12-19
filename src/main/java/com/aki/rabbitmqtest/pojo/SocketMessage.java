package com.aki.rabbitmqtest.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangxiaohui
 * @Date: Create by 2018-12-5 15:38
 * @Description:
 */
@Data
public class SocketMessage implements Serializable {
    private static final long serialVersionUID = -8221467966772683998L;
    private String id;
    private String senderUser;
    private String receiverUser;
    private String content;
    private Date sendTime;
    private Date readTime;
}
