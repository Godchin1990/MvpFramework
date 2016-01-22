package com.ziyou.tourGuide.model;

/**
 * Created by Edward on 16/1/18.
 */
public class Wallet {

    /**
     * amount : 0
     * total_amount : 0
     */

    private float amount;
    private float total_amount;

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
    }

    public float getAmount() {
        return amount;
    }

    public float getTotal_amount() {
        return total_amount;
    }
}
