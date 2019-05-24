package com.hisun.BP;

public class BPCPCMWD {
    public BPCPCMWD_RC RC = new BPCPCMWD_RC();
    public char FUNC_FLAG = ' ';
    public int CHECK_DATE = 0;
    public String[] CALCD = new String[7];
    public BPCPCMWD_CAL_CODE[] CAL_CODE = new BPCPCMWD_CAL_CODE[7];
    public char DATE_TYPE = ' ';
    public char HOLIDAY_FLG_ALL = ' ';
    public int NEXT_WORK_DAY_ALL = 0;
    public int LAST_WORK_DAY_ALL = 0;
    public char[] HOLIDAY_FLG = new char[7];
    public int[] NEXT_WORK_DAY = new int[7];
    public int[] LAST_WORK_DAY = new int[7];
    public BPCPCMWD() {
        for (int i=0;i<7;i++) CAL_CODE[i] = new BPCPCMWD_CAL_CODE();
    }
}
