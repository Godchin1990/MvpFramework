package com.example.edward.mvpframework.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 15/11/17.
 */
public class Pagination {

    /**
     * limit : 1
     * offset : 0
     * total : 1
     */

    private int limit;
    private int offset;
    private int total;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

//    public void setTotal(int total) {
//        this.total = total;
//    }

    public void clearTotal(){
        total = 0;
    }

    public void appendTotal(int append) {
        total = total + append;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getTotal() {
        return total;
    }

    public Map<String,String> getMap(){
        Map<String,String> map = new HashMap<>();
        map.put("limit",limit+"");
        map.put("offset",offset+"");
        return map;
    }
}
