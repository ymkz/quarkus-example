package dev.ymkz.domain.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

public enum BookOrder {
  @JsonProperty("+price")
  PRICE_ASC("+price", "価格_昇順"),

  @JsonProperty("-price")
  PRICE_DESC("-price", "価格_降順");

  private final String _value;

  BookOrder(String value, String label) {
    _value = value;
  }

  public static BookOrder of(String value) {
    return Arrays.stream(values())
        .filter(order -> order._value.equals(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid BookOrder value: " + value));
  }

  public String sort() {
    return _value.substring(1);
  }

  public String order() {
    var symbol = _value.substring(0, 1);
    return symbol.equals("+") ? "ASC" : "DESC";
  }
}
