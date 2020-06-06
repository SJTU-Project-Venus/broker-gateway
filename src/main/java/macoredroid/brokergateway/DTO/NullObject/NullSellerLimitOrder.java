package macoredroid.brokergateway.DTO.NullObject;

import macoredroid.brokergateway.DTO.LimitOrder;

public class NullSellerLimitOrder extends LimitOrder {
    @Override
    public int getUnitPrice(){
        return Integer.MAX_VALUE;
    }
}
