package macoredroid.brokergateway.listener;

import macoredroid.brokergateway.event.MarketDepthFixedEvent;
import macoredroid.brokergateway.repository.FutureRepository;
import macoredroid.brokergateway.repository.MarketDepthRepository;
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MarketDepthListener {
    Logger logger = LoggerFactory.getLogger(MarketDepthListener.class);
    @Autowired
    MarketDepthRepository marketDepthRepository;
    @Autowired
    FutureRepository futureRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @EventHandler
    public void on(MarketDepthFixedEvent marketDepthFixedEvent){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("marketQuotation", JSON.toJSONString(marketDepthFixedEvent.getMarketPrice()));
        jsonObject.addProperty("marketDepth", JSON.toJSONString(marketDepthFixedEvent.getMarketDepthEntity()));
        jsonObject.addProperty("marketDepthId", marketDepthFixedEvent.getId());
        jsonObject.addProperty("timestamp", new Date().getTime());
        jsonObject.addProperty("futureName", getFutureName(marketDepthFixedEvent.getId()) );
        logger.info("jsonObject: {}", jsonObject.toString());

        //rabbitTemplate.convertAndSend("marketDepth", "marketDepth", jsonObject.toString());
        marketDepthRepository.save(marketDepthFixedEvent.getMarketDepthEntity());
    }


    //@Cacheable(key = "#p0")
    public String getFutureName(String marketDepthId){
        return futureRepository.findFutureDTOByMarketDepthIdEquals(marketDepthId).getName();
    }
}
