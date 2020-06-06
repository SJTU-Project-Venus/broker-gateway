package cn.pipipan.eisproject.brokergatewayddd.DTO.NullObject;

import cn.pipipan.eisproject.brokergatewayddd.DTO.LimitOrder;

public class NullSellerLimitOrder extends LimitOrder {
    @Override
    public int getUnitPrice(){
        return Integer.MAX_VALUE;
    }
}
