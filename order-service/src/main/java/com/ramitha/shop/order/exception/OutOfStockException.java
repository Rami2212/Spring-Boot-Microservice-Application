package com.ramitha.shop.order.exception;

public class OutOfStockException extends RuntimeException {
  public OutOfStockException(String message) {

    super(message);
  }
}
