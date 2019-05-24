package com.hisun.BP;

public class BPB4750_AWA_4750 {
    public char FUNC_CD = ' ';
    public short FUNC_CD_NO = 0;
    public String BANK_CD = " ";
    public short BANK_CD_NO = 0;
    public String CALCD = " ";
    public short CALCD_NO = 0;
    public String T_CALCD = " ";
    public short T_CALCD_NO = 0;
    public String CAL_NAME = " ";
    public short CAL_NAME_NO = 0;
    public String T_CAL_NE = " ";
    public short T_CAL_NE_NO = 0;
    public String CNTYS_CD = " ";
    public short CNTYS_CD_NO = 0;
    public String CITY_CD = " ";
    public short CITY_CD_NO = 0;
    public short YEAR = 0;
    public short YEAR_NO = 0;
    public short END_YEAR = 0;
    public short END_YEAR_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public BPB4750_MONTH[] MONTH = new BPB4750_MONTH[12];
    public BPB4750_AWA_4750() {
        for (int i=0;i<12;i++) MONTH[i] = new BPB4750_MONTH();
    }
}
