package com.liquid.tree.classes;

public class Tuple2 <T1, T2> {
    private T1 item1;
    private T2 item2;

    public Tuple2() {
    }

    public Tuple2(T1 item1, T2 item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public T1 left() {
        return item1;
    }

    public T2 right() {
        return item2;
    }

    public void setLeft(T1 item) {
        this.item1 = item;
    }

    public void setRight(T2 item) {
        this.item2 = item;
    }

    public void add(T1 item1, T2 item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public <N extends Number> void leftSub(N number) {
        if (item1 instanceof Number) {
            if (item1 instanceof Integer && number instanceof Integer) {
                item1 = (T1) Integer.valueOf(((Integer) item1) - number.intValue());
            } else if (item1 instanceof Float && number instanceof Float) {
                item1 = (T1) Float.valueOf(((Float) item1) - number.floatValue());
            }
        }
    }

    public <N extends Number> void rightSub(N number) {
        if (item2 instanceof Number) {
            if (item2 instanceof Integer && number instanceof Integer) {
                item2 = (T2) Integer.valueOf(((Integer) item2) - number.intValue());
            } else if (item2 instanceof Float && number instanceof Float) {
                item2 = (T2) Float.valueOf(((Float) item2) - number.floatValue());
            }
        }
    }
}