package macoredroid.brokergateway.Domain;

import macoredroid.brokergateway.Entity.OrderBlotterEntity;
import macoredroid.brokergateway.DateUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Data
@Document
public class MarketPrice {
    private float lastClosePrice;
    private float openPrice;
    private float closePrice;
    private float highPrice;
    private float lowPrice;
    private float currentPrice;
    private float changePrice;
    private float changePercent;

    private String date;
    private String marketDepthId;
    private String currentTime;
    @Id
    private String id;

    public MarketPrice(String currentDate, float lastClosePrice, String marketDepthId){
        setDate(currentDate);
        setLastClosePrice(lastClosePrice);
        setOpenPrice(0);
        setHighPrice(0);
        setLowPrice(0);
        setCurrentTime(currentDate+" 01:00:00");
        setId(UUID.randomUUID().toString());
        setMarketDepthId(marketDepthId);
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public void update(OrderBlotterEntity orderBlotter){
        float price = orderBlotter.getPrice();
        String time = orderBlotter.getCreationTime();
        setCurrentTime(time);
        setCurrentPrice(price);
        setChangePrice(currentPrice - lastClosePrice);
        setChangePercent(changePrice / lastClosePrice);
        if(openPrice == 0){
            setOpenPrice(price);
            setLowPrice(price);
            setHighPrice(price);
        }
        if(price > highPrice){
            setHighPrice(price);
        }
        if(price < lowPrice){
            setLowPrice(price);
        }
    }

    public MarketPrice() {
    }

    public MarketPrice(MarketPrice other){
        this.openPrice = other.getClosePrice();
        this.id = UUID.randomUUID().toString();
        this.marketDepthId = other.getMarketDepthId();
        this.date = DateUtil.getNowDate();
    }

    public MarketPrice clone(){
        MarketPrice marketPrice = new MarketPrice();
        BeanUtils.copyProperties(this, marketPrice);
        return marketPrice;
    }



}

