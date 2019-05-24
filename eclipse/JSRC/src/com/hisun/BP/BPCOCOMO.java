package com.hisun.BP;

public class BPCOCOMO {
    public String FEE_CODE = " ";
    public String REG_CODE = " ";
    public String CHN_NO = " ";
    public String FREE_FMT = " ";
    public short FEE_NO = 0;
    public BPCOCOMO_FAV_DATA[] FAV_DATA = new BPCOCOMO_FAV_DATA[9];
    public char FAV_SELECT = ' ';
    public String TXT = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public String FEE_DESC = " ";
    public char FILLER13 = 0X02;
    public String AREA_TYPE = " ";
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public String MSG_CODE = " ";
    public BPCOCOMO() {
        for (int i=0;i<9;i++) FAV_DATA[i] = new BPCOCOMO_FAV_DATA();
    }
}
