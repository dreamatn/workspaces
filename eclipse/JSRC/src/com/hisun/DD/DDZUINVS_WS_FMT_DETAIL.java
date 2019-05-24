package com.hisun.DD;

public class DDZUINVS_WS_FMT_DETAIL {
    String WS_FMT_VS_AC = " ";
    char WS_FMT_AC_STS = ' ';
    String WS_FMT_PARENT_AC = " ";
    String WS_FMT_P_AC_NAME = " ";
    char DDZUINVS_FILLER38 = 0X02;
    String WS_FMT_CCY = " ";
    char WS_FMT_CCY_TYP = ' ';
    double WS_FMT_VS_CURR_BAL = 0;
    char DDZUINVS_FILLER42 = 0X01;
    String WS_FMT_VS_AC_NAME = " ";
    char DDZUINVS_FILLER44 = 0X02;
    String WS_FMT_VS_CON_NAME = " ";
    char DDZUINVS_FILLER46 = 0X02;
    String WS_FMT_VS_CON_TEL = " ";
    String WS_FMT_VS_CON_ADDR = " ";
    char DDZUINVS_FILLER49 = 0X02;
    int WS_FMT_OPEN_DT = 0;
    int WS_FMT_CLOSE_DT = 0;
    String WS_FMT_REMARK = " ";
    char DDZUINVS_FILLER53 = 0X02;
    char WS_FMT_CINT_FLG = ' ';
    String WS_FMT_OPDP_OTH = " ";
    DDZUINVS_WS_VS_SPE_DATA[] WS_VS_SPE_DATA = new DDZUINVS_WS_VS_SPE_DATA[3];
    String WS_FMT_VS_IDTYP = " ";
    String WS_FMT_VS_IDNO = " ";
    char DDZUINVS_FILLER62 = 0X02;
    String WS_FMT_VS_MMO = " ";
    char WS_FMT_VS_FLG = ' ';
    String WS_FMT_VS_CHNLNO = " ";
    String WS_FMT_VS_INTAC = " ";
    String WS_FMT_VS_SYSNO = " ";
    public DDZUINVS_WS_FMT_DETAIL() {
        for (int i=0;i<3;i++) WS_VS_SPE_DATA[i] = new DDZUINVS_WS_VS_SPE_DATA();
    }
}
