package com.scottlogic.javatraining;

import javax.validation.constraints.*;


public abstract class Order {

    @NotBlank(message = "Account is Mandatory")
    public String account;

    @NotNull(message = "Price is Mandatory")
    @Min(1)
    public int price;

    @NotNull(message = "Quantity is Mandatory")
    @Min(1)
    public int quantity;


    public Order(String account, int price, int quantity) {
        this.account = account;
        this.price = price;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
