package com.ziyou.tourGuide.model;

/**
 * Created by Edward on 16/1/18.
 */
public class Wallet {

    /**
     * amount : 0
     * total_amount : 0
     */

    private int amount;
    private int total_amount;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotal_amount() {
        return total_amount;
    }
}
