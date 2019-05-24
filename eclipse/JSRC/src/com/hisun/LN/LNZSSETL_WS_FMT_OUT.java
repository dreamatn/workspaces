package com.hisun.LN;

public class LNZSSETL_WS_FMT_OUT {
    String FMT_OUT_CONTRACT_NO = " ";
    String FMT_OUT_PART_BK = " ";
    char FMT_OUT_CI_TYPE = ' ';
    String FMT_OUT_CCY = " ";
    char FMT_OUT_SETTLE_TYPE = ' ';
    char FMT_OUT_MWHD_AC_FLG = ' ';
    String FMT_OUT_AC_TYP = " ";
    String FMT_OUT_AC = " ";
    char FMT_OUT_CCY_TYP = ' ';
    char FMT_OUT_AC_FLG = ' ';
    String FMT_OUT_AC_NM = " ";
    char FILLER52 = 0X02;
    String FMT_OUT_REMARK = " ";
    char FILLER54 = 0X02;
    LNZSSETL_WS_FMT_OUT_AC_DATA[] WS_FMT_OUT_AC_DATA = new LNZSSETL_WS_FMT_OUT_AC_DATA[4];
    public LNZSSETL_WS_FMT_OUT() {
        for (int i=0;i<4;i++) WS_FMT_OUT_AC_DATA[i] = new LNZSSETL_WS_FMT_OUT_AC_DATA();
    }
}
