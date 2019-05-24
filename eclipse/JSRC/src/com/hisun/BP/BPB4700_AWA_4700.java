package com.hisun.BP;

public class BPB4700_AWA_4700 {
    public char FUNC_CD = ' ';
    public short FUNC_CD_NO = 0;
    public String BANK_CD = " ";
    public short BANK_CD_NO = 0;
    public String APPCD = " ";
    public short APPCD_NO = 0;
    public String CALCD = " ";
    public short CALCD_NO = 0;
    public String T_CALCD = " ";
    public short T_CALCD_NO = 0;
    public int BRNO = 0;
    public short BRNO_NO = 0;
    public String CAL_NAME = " ";
    public short CAL_NAME_NO = 0;
    public short YEAR = 0;
    public short YEAR_NO = 0;
    public short MONTH = 0;
    public short MONTH_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public int STR_DATE = 0;
    public short STR_DATE_NO = 0;
    public int END_DATE = 0;
    public short END_DATE_NO = 0;
    public BPB4700_MONTH_1[] MONTH_1 = new BPB4700_MONTH_1[42];
    public String REG_CD = " ";
    public short REG_CD_NO = 0;
    public char TYPE = ' ';
    public short TYPE_NO = 0;
    public String T_CAL_NE = " ";
    public short T_CAL_NE_NO = 0;
    public BPB4700_AWA_4700() {
        for (int i=0;i<42;i++) MONTH_1[i] = new BPB4700_MONTH_1();
    }
}
