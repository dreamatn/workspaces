package com.hisun.BP;

public class BPB5090_AWA_5090 {
    public String EXRA_CD = " ";
    public short EXRA_CD_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String TENOR = " ";
    public short TENOR_NO = 0;
    public char CMP_FLG = ' ';
    public short CMP_FLG_NO = 0;
    public BPB5090_AUTH_SET[] AUTH_SET = new BPB5090_AUTH_SET[10];
    public BPB5090_AWA_5090() {
        for (int i=0;i<10;i++) AUTH_SET[i] = new BPB5090_AUTH_SET();
    }
}
