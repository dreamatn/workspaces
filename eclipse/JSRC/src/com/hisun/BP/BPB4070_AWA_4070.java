package com.hisun.BP;

public class BPB4070_AWA_4070 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String CNTR_TYP = " ";
    public short CNTR_TYP_NO = 0;
    public String PROD_TYP = " ";
    public short PROD_TYP_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public int EFF_DT = 0;
    public short EFF_DT_NO = 0;
    public int EXP_DT = 0;
    public short EXP_DT_NO = 0;
    public String MOD_NO = " ";
    public short MOD_NO_NO = 0;
    public String MOD_NAME = " ";
    public short MOD_NAME_NO = 0;
    public char MOD_TYP = ' ';
    public short MOD_TYP_NO = 0;
    public BPB4070_EVENT[] EVENT = new BPB4070_EVENT[60];
    public BPB4070_AWA_4070() {
        for (int i=0;i<60;i++) EVENT[i] = new BPB4070_EVENT();
    }
}
