package macoredroid.brokergateway.listener;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import macoredroid.brokergateway.event.NewOrderBlotterEvent;
import macoredroid.brokergateway.repository.OrderBlotterRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderBlotterListener {
    Logger logger = LoggerFactory.getLogger(MarketDepthListener.class);
    @Autowired
    OrderBlotterRepository orderBlotterRepository;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @EventHandler
    public void on(NewOrderBlotterEvent newOrderBlotterEvent){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("order", JSON.toJSONString(newOrderBlotterEvent.getOrderBlotterEntity()));

        logger.info("jsonObject: {}", jsonObject.toString());

        rabbitTemplate.convertAndSend("finishedOrder", "finishedOrder", jsonObject.toString());
        orderBlotterRepository.save(newOrderBlotterEvent.getOrderBlotterEntity());
    }
}
