package com.hisun.BP;

public class BPCOACM {
    public String CNTR_TYPE = " ";
    public String PROD_TYPE = " ";
    public int BR = 0;
    public String MOD_NO = " ";
    public String MOD_NAME = " ";
    public char FILLER6 = 0X02;
    public char MOD_TYP = ' ';
    public BPCOACM_EVENT[] EVENT = new BPCOACM_EVENT[60];
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public int CNT = 0;
    public BPCOACM() {
        for (int i=0;i<60;i++) EVENT[i] = new BPCOACM_EVENT();
    }
}
