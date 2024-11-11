package com.jfoster.finalproject.service;

/**
 * Custom exception that represents when an account has insufficient balance for an update to be legal.
 * Thrown when an account has insufficient balance/overdraft to handle a credit request,
 * or when a new maximum overdraft would be lower than the current amount the account is overdrawn by.
 */
public class InsufficientAccountBalanceException extends IllegalArgumentException {
  public InsufficientAccountBalanceException(String message) {
    super(message);
  }
}
