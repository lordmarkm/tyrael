package com.tyrael.commons.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark
 */
public class PageInfo<T> {

    private long total;
    private List<T> data;

    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
    public static <T> PageInfo<T> blank() {
        PageInfo<T> blank = new PageInfo<>();
        blank.setData(new ArrayList<T>());
        blank.setTotal(0);
        return blank;
    }

}