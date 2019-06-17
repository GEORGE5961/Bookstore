package com.zhengyu.bookstore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhengyu.bookstore.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import com.zhengyu.bookstore.model.Order;

import java.util.Date;
import java.util.UUID;


@Component
@Slf4j
public class KafkaProducer {
/*
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        //log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        kafkaTemplate.send("test", gson.toJson(message));
    }*/
}
