package macoredroid.brokergateway.Domain;

import macoredroid.brokergateway.helper.Util;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;
@Data
@Document
public class OrderBlotterDTO {
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

    public static OrderBlotterDTO createOrderBlotter(int delta, int price, OrderDTO buyer, OrderDTO seller, String marketDepthId){
        OrderBlotterDTO orderBlotterDTO = new OrderBlotterDTO();
        orderBlotterDTO.setId(UUID.randomUUID().toString());
        orderBlotterDTO.setCount(delta);
        orderBlotterDTO.setPrice(price);
        orderBlotterDTO.setCreationTime(Util.getDate(new Date()));
        orderBlotterDTO.setBuyerTraderName(buyer.getTraderName()); orderBlotterDTO.setSellerTraderName(seller.getTraderName());
        orderBlotterDTO.setBuyerOrderId(buyer.getId()); orderBlotterDTO.setSellerOrderId(seller.getId());
        orderBlotterDTO.setMarketDepthId(marketDepthId);
        return orderBlotterDTO;
    }
}
