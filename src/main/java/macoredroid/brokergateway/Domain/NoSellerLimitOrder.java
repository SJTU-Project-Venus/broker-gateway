package macoredroid.brokergateway.Domain;

public class NoSellerLimitOrder extends LimitOrder {
    @Override
    public int getUnitPrice(){
        return Integer.MAX_VALUE;
    }
}
