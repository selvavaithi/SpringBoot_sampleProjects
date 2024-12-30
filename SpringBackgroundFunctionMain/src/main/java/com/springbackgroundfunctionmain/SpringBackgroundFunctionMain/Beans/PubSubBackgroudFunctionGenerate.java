package com.springbackgroundfunctionmain.SpringBackgroundFunctionMain.Beans;

import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import com.google.events.cloud.pubsub.v1.Message;
import com.google.events.cloud.pubsub.v1.MessagePublishedData;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;

import java.util.Base64;
import java.util.function.Supplier;

public class PubSubBackgroudFunctionGenerate {

    @Bean
    public BackgroundFunction<MessagePublishedData> pubSubBackgroundFunction() {
        return (pubSubMessage, context) -> {
            Message message = pubSubMessage.getMessage();
            String data = new String(Base64.getDecoder().decode(message.getData()));
            Gson gson = new Gson();
            PubSubMessage pubSubMessageObject = gson.fromJson(data, PubSubMessage.class);
            System.out.println("Received message: " + pubSubMessageObject.getData());
        };
    }
}
