package dev.ymkz.domain.value;

public record RangeInteger(Integer min, Integer max) {

  public RangeInteger {
    if (min != null && max != null && min > max) {
      throw new IllegalArgumentException("The min value is greater than the max value");
    }
  }

  public boolean contains(int value) {
    return value >= min && value <= max;
  }
}
