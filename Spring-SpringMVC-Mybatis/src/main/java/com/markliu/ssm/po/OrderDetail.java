package com.markliu.ssm.po;

public class OrderDetail {
    private Integer id;

    private Integer ordersId;

    private Integer itemsId;

    private String itemsnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getItemsId() {
        return itemsId;
    }

    public void setItemsId(Integer itemsId) {
        this.itemsId = itemsId;
    }

    public String getItemsnum() {
        return itemsnum;
    }

    public void setItemsnum(String itemsnum) {
        this.itemsnum = itemsnum == null ? null : itemsnum.trim();
    }
}