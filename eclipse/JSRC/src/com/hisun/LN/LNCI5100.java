package com.hisun.LN;

public class LNCI5100 {
    public char FUN_CD = ' ';
    public String CONT_NO = " ";
    public String CCY = " ";
    public char MWHD_FLG = ' ';
    public String AC_TYP = " ";
    public String AC_NO = " ";
    public char CCY_TYP = ' ';
    public char AC_FLG = ' ';
    public String PART_BK = " ";
    public char CI_TYPE = ' ';
    public char STL_TYP = ' ';
    public String AC_NM = " ";
    public String REMARK = " ";
    public LNCI5100_AC_DATA[] AC_DATA = new LNCI5100_AC_DATA[4];
    public LNCI5100() {
        for (int i=0;i<4;i++) AC_DATA[i] = new LNCI5100_AC_DATA();
    }
}
