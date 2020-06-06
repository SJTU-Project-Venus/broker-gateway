package macoredroid.brokergateway.Domain.NullObject;

import macoredroid.brokergateway.Domain.LimitOrder;

public class NullSellerLimitOrder extends LimitOrder {
    @Override
    public int getUnitPrice(){
        return Integer.MAX_VALUE;
    }
}
