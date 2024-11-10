package com.jfoster.finalproject.service;

public class InsufficientAccountBalanceException extends IllegalArgumentException {
  public InsufficientAccountBalanceException(String message) {
    super(message);
  }
}
