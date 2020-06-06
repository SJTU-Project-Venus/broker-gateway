package macoredroid.brokergateway.Domain;

import macoredroid.brokergateway.helper.Util;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Data
@Document
public class MarketQuotation {
    private float lastClosePrice;
    private float openPrice;
    private float closePrice;
    private float highPrice;
    private float lowPrice;
    private float currentPrice;
    private float changePrice;
    private float changePercent;
    private int totalVolume;
    private int totalCapital;
    private int totalShare = 1000000;
    private float turnoverRate;
    private String date;
    private String marketDepthId;
    private String currentTime;
    @Id
    private String id;

    MarketQuotation(String currentDate, float lastClosePrice, String marketDepthId){
        setDate(currentDate);
        setLastClosePrice(lastClosePrice);
        setOpenPrice(0);
        setHighPrice(0);
        setLowPrice(0);
        setTotalVolume(0);
        setTotalCapital(0);
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

    public void update(OrderBlotterDTO orderBlotter){
        float price = orderBlotter.getPrice();
        int volume = orderBlotter.getCount();
        String time = orderBlotter.getCreationTime();
        setCurrentTime(time);
        setTotalVolume(totalVolume+volume);
        setTotalCapital((int)(totalCapital+volume*price));
        setCurrentPrice(price);
        setChangePrice(currentPrice - lastClosePrice);
        setChangePercent(changePrice / lastClosePrice);
        setTurnoverRate(totalVolume/totalShare);
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

    public MarketQuotation() {
    }

    public MarketQuotation(MarketQuotation other){
        this.openPrice = other.getClosePrice();
        this.id = UUID.randomUUID().toString();
        this.marketDepthId = other.getMarketDepthId();
        this.date = Util.getNowDate();
    }

    public MarketQuotation clone(){
        MarketQuotation marketQuotation = new MarketQuotation();
        BeanUtils.copyProperties(this, marketQuotation);
        return marketQuotation;
    }



}

