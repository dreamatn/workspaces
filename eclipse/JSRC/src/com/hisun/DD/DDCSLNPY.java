package com.hisun.DD;

public class DDCSLNPY {
    public char FUNC = ' ';
    public String LNCNTR_NO = " ";
    public short LNTX_SEQ = 0;
    public short NUM = 0;
    public char INNER_FLG = ' ';
    public char LNCNTR_FLG = ' ';
    public DDCSLNPY_ITEM_DATA[] ITEM_DATA = new DDCSLNPY_ITEM_DATA[10];
    public int PAGE_ROW = 0;
    public int PAGE_NUM = 0;
    public DDCSLNPY() {
        for (int i=0;i<10;i++) ITEM_DATA[i] = new DDCSLNPY_ITEM_DATA();
    }
}
