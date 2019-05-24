package com.hisun.BP;

public class BPB6220_AWA_6220 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String CNTY_CD = " ";
    public short CNTY_CD_NO = 0;
    public String CITY_CD = " ";
    public short CITY_CD_NO = 0;
    public int DATE = 0;
    public short DATE_NO = 0;
    public int EFF_DT = 0;
    public short EFF_DT_NO = 0;
    public BPB6220_HOL_DATA[] HOL_DATA = new BPB6220_HOL_DATA[50];
    public String CAL_CD = " ";
    public short CAL_CD_NO = 0;
    public BPB6220_AWA_6220() {
        for (int i=0;i<50;i++) HOL_DATA[i] = new BPB6220_HOL_DATA();
    }
}
