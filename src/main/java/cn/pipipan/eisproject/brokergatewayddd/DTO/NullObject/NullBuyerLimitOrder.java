package cn.pipipan.eisproject.brokergatewayddd.DTO.NullObject;

import cn.pipipan.eisproject.brokergatewayddd.DTO.LimitOrder;

public class NullBuyerLimitOrder extends LimitOrder {
    public int getUnitPrice(){
        return Integer.MIN_VALUE;
    }
}
