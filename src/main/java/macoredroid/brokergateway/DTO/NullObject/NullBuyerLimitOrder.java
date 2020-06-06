package macoredroid.brokergateway.DTO.NullObject;

import macoredroid.brokergateway.DTO.LimitOrder;

public class NullBuyerLimitOrder extends LimitOrder {
    public int getUnitPrice(){
        return Integer.MIN_VALUE;
    }
}
