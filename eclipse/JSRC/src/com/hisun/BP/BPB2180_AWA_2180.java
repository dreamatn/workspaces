package com.hisun.BP;

public class BPB2180_AWA_2180 {
    public int BR = 0;
    public short BR_NO = 0;
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public String CI_NM = " ";
    public short CI_NM_NO = 0;
    public String ITM_NO = " ";
    public short ITM_NO_NO = 0;
    public int SEQ = 0;
    public short SEQ_NO = 0;
    public BPB2180_CCYS[] CCYS = new BPB2180_CCYS[10];
    public BPB2180_AWA_2180() {
        for (int i=0;i<10;i++) CCYS[i] = new BPB2180_CCYS();
    }
}
