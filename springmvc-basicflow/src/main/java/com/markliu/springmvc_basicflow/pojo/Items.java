package com.markliu.springmvc_basicflow.pojo;

/**
 * Author: markliu
 * Time  : 16-8-3 下午2:34
 */
public class Items {
    private int id;
    private String name;
    private float price;
    private String detail;

    public Items() {
    }

    public Items(String name, float price, String detail) {
        this.name = name;
        this.price = price;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                '}';
    }
}
