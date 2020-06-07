package macoredroid.brokergateway.Domain;

public class NoBuyerLimitOrder extends LimitOrder {
    public int getUnitPrice(){
        return Integer.MIN_VALUE;
    }
}
