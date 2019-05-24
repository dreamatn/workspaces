package com.hisun.BP;

public class BPB5100_AWA_5100 {
    public int BR = 0;
    public short BR_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String CORR_CCY = " ";
    public short CORR_CCY_NO = 0;
    public char CMP_FLG = ' ';
    public short CMP_FLG_NO = 0;
    public BPB5100_AUTH_SET[] AUTH_SET = new BPB5100_AUTH_SET[10];
    public BPB5100_AWA_5100() {
        for (int i=0;i<10;i++) AUTH_SET[i] = new BPB5100_AUTH_SET();
    }
}
