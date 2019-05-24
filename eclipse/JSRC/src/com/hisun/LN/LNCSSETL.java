package com.hisun.LN;

public class LNCSSETL {
    public String CONTRACT_NO = " ";
    public char CONTRACT_ATTR = ' ';
    public String PART_BK = " ";
    public char CI_TYPE = ' ';
    public String CCY = " ";
    public char SETTLE_TYPE = ' ';
    public char MWHD_AC_FLG = ' ';
    public String AC_TYP = " ";
    public String AC = " ";
    public char CCY_TYP = ' ';
    public char AC_FLG = ' ';
    public String AC_NM = " ";
    public char FILLER13 = 0X02;
    public String REMARK = " ";
    public char FILLER15 = 0X02;
    public int CRT_DATE = 0;
    public String CRT_TLR = " ";
    public LNCSSETL_AC_DATA[] AC_DATA = new LNCSSETL_AC_DATA[4];
    public int PAGE_ROW = 0;
    public int PAGE_NUM = 0;
    public LNCSSETL() {
        for (int i=0;i<4;i++) AC_DATA[i] = new LNCSSETL_AC_DATA();
    }
}
