package com.mql.jsonx.core.impl;

import com.mql.jsonx.core.JsonNumber;
import com.mql.jsonx.core.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Mehdi Maick
 */
public class JsonNumberImpl implements JsonNumber {

    private BigDecimal decimalValue; // lazily instantiated

    public JsonNumberImpl(long value) {
        decimalValue = BigDecimal.valueOf(value);
    }

    public JsonNumberImpl(int value) {
        decimalValue = BigDecimal.valueOf(value);
    }

    public JsonNumberImpl(double value) {
        decimalValue = BigDecimal.valueOf(value);
    }

    @Override
    public int intValue() {
        return decimalValue.intValue();
    }

    @Override
    public long longValue() {
        return decimalValue.longValue();
    }

    @Override
    public double doubleValue() {
        return decimalValue.doubleValue();
    }

    @Override
    public BigDecimal toBigDecimal() {
        return decimalValue;
    }

    @Override
    public BigInteger toBigInteger() {
        return decimalValue.toBigInteger();
    }

    @Override
    public Type getType() {
        return Type.NUMBER;
    }
}
