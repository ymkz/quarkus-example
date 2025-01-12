package dev.ymkz.domain.value;

public record Isbn13(String value) {

  public Isbn13 {
    if (!isValid(value)) {
      throw new IllegalArgumentException("Invalid ISBN-13 format");
    }
  }

  private static boolean isValid(String value) {
    if (value == null || value.length() != 13) {
      return false;
    }

    int sum = 0;
    for (int i = 0; i < 12; i++) {
      int digit = Character.getNumericValue(value.charAt(i));
      sum += digit * (i % 2 == 0 ? 1 : 3);
    }

    int checkDigit = Character.getNumericValue(value.charAt(12));
    return (10 - (sum % 10)) % 10 == checkDigit;
  }
}
