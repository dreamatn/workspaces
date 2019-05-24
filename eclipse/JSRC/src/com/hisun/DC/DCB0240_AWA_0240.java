package com.hisun.DC;

public class DCB0240_AWA_0240 {
    public String PROD_CD = " ";
    public short PROD_CD_NO = 0;
    public DCB0240_CARD_DTA[] CARD_DTA = new DCB0240_CARD_DTA[10];
    public DCB0240_AWA_0240() {
        for (int i=0;i<10;i++) CARD_DTA[i] = new DCB0240_CARD_DTA();
    }
}
