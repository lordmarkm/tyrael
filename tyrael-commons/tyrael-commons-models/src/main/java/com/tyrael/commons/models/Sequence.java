package com.tyrael.commons.models;

import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public abstract class Sequence {

    private static final String DEFAULT_DELIMITER = "-";
    private static final String PADCHAR = "0";

    protected abstract void checkSequenceReset();

    public String next() {
        checkSequenceReset();
        return new StringBuilder()
            .append(getTemporalPart())
            .append(getDelimiter())
            .append(getSequencePart())
            .toString();
    }

    protected String getDelimiter() {
        return DEFAULT_DELIMITER;
    }

    protected String getTemporalPart() {
        return new StringBuilder()
            .append(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2))
            .append(StringUtils.leftPad(String.valueOf(Calendar.getInstance().get(Calendar.MONTH + 1)), 2, PADCHAR))
            .toString();
    }

    protected String getSequencePart() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    static class SequenceTest extends Sequence { protected void checkSequenceReset() {}}
    public static void main(String[] args) {
        System.out.println(new SequenceTest().next());
    }
}
