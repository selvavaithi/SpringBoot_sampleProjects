package com.googlepubsubapp.GooglePubSubApplication.Controllers;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Service;

@Service
public class PubSubApplication {

    private static final Log LOGGER = LogFactory.getLog(PubSubApplication.class);

    @Bean
    public MessageChannel pubsubInputChannel(){
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("pubsubInputChannel") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate
    ){
            PubSubInboundChannelAdapter adapter =
                    new PubSubInboundChannelAdapter(pubSubTemplate, "mytopic_pubsub_test-sub");

            adapter.setOutputChannel(inputChannel);
            adapter.setAckMode(AckMode.MANUAL);

            return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "pubsubInputChannel")
    public MessageHandler messageReceiver(){
        return message -> {
            LOGGER.info("The Message has ARRIVED!! Payload: "+
                    new String((byte[]) message.getPayload()));
            BasicAcknowledgeablePubsubMessage originalMsg =
                    message.getHeaders().get(
                            GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class
                    );
            originalMsg.ack();
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "pubsubOutputChannel")
    public MessageHandler messageSender(PubSubTemplate pubSubTemplate){
        return new PubSubMessageHandler(pubSubTemplate, "mytopic_pubsub_test");
    }

    @MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
    public interface PubsubOutBoundGateWay{
        void sendToPubsub(String text);
    }
}
