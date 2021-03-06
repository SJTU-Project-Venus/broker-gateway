package macoredroid.brokergateway.Entity;

import macoredroid.brokergateway.DateUtil;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;
@Data
@Document
public class OrderBlotterEntity {
    @Id
    private String id;
    private int count;
    private int price;
    private String creationTime;
    private String buyerTraderName;
    private String sellerTraderName;

    private String buyerOrderId;
    private String sellerOrderId;
    private String marketDepthId;
    private String buyerTraderDetailName;
    private String sellerTraderDetailName;
    private String buyerOtherId;
    private String sellerOtherId;
    private String futureName;

    public static OrderBlotterEntity createOrderBlotter(int delta, int price, OrderEntity buyer, OrderEntity seller, String marketDepthId){
        OrderBlotterEntity orderBlotterEntity = new OrderBlotterEntity();
        orderBlotterEntity.setId(UUID.randomUUID().toString());
        orderBlotterEntity.setCount(delta);
        orderBlotterEntity.setPrice(price);
        orderBlotterEntity.setFutureName(buyer.getFutureName());
        orderBlotterEntity.setCreationTime(DateUtil.getDate(new Date()));
        orderBlotterEntity.setBuyerTraderName(buyer.getTraderName());
        orderBlotterEntity.setBuyerTraderDetailName(buyer.getTraderDetailName());
        orderBlotterEntity.setSellerTraderDetailName(seller.getTraderDetailName());
        orderBlotterEntity.setSellerTraderName(seller.getTraderName());
        orderBlotterEntity.setBuyerOtherId(buyer.getOtherId());
        orderBlotterEntity.setSellerOtherId(seller.getOtherId());
        orderBlotterEntity.setBuyerOrderId(buyer.getId()); orderBlotterEntity.setSellerOrderId(seller.getId());
        orderBlotterEntity.setMarketDepthId(marketDepthId);
        return orderBlotterEntity;
    }
}
