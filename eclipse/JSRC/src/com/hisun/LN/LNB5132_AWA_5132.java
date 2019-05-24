package com.hisun.LN;

public class LNB5132_AWA_5132 {
    public char CONT_TYP = ' ';
    public short CONT_TYP_NO = 0;
    public String CONT_NO = " ";
    public short CONT_NO_NO = 0;
    public String PART_BK = " ";
    public short PART_BK_NO = 0;
    public char CI_TYPE = ' ';
    public short CI_TYPE_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public char STL_TYP = ' ';
    public short STL_TYP_NO = 0;
    public char MWHD_FLG = ' ';
    public short MWHD_FLG_NO = 0;
    public String AC_TYP = " ";
    public short AC_TYP_NO = 0;
    public String AC_NO = " ";
    public short AC_NO_NO = 0;
    public char AC_FLG = ' ';
    public short AC_FLG_NO = 0;
    public String AC_NM = " ";
    public short AC_NM_NO = 0;
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public LNB5132_AC_DATA[] AC_DATA = new LNB5132_AC_DATA[4];
    public LNB5132_AWA_5132() {
        for (int i=0;i<4;i++) AC_DATA[i] = new LNB5132_AC_DATA();
    }
}
