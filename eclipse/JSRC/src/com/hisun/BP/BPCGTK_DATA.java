package com.hisun.BP;

public class BPCGTK_DATA {
    public char FUNC = ' ';
    public int EFF_DATE = 0;
    public int EFF_TIME = 0;
    public String EXR_TYPE = " ";
    public String TENOR = " ";
    public String BASE_TYPE = " ";
    public String BASE_CCY = " ";
    public BPCGTK_CCY_INFO[] CCY_INFO = new BPCGTK_CCY_INFO[20];
    public char CONT_FLAG = ' ';
    public short CMPL_CNT = 0;
    public short OUT_REC_CNT = 0;
    public char CMPL_FLAG = ' ';
    public BPCGTK_DATA() {
        for (int i=0;i<20;i++) CCY_INFO[i] = new BPCGTK_CCY_INFO();
    }
}