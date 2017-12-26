package com.mql.jsonx.core;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * abstraction over every number representation in a json object
 *
 * @author Mehdi Maick
 */
public interface JsonNumber extends JsonValue {

    int intValue();

    long longValue();

    double doubleValue();

    BigDecimal toBigDecimal();

    BigInteger toBigInteger();

    String toString();

    int hashCode();
}
