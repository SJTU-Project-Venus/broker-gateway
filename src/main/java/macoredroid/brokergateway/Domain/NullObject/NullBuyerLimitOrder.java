package macoredroid.brokergateway.Domain.NullObject;

import macoredroid.brokergateway.Domain.LimitOrder;

public class NullBuyerLimitOrder extends LimitOrder {
    public int getUnitPrice(){
        return Integer.MIN_VALUE;
    }
}
