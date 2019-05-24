package com.hisun.DD;

public class DDCOINDE_FMT_DETAIL {
    public String FMT_VS_AC = " ";
    public char FMT_AC_STS = ' ';
    public String FMT_PARENT_AC = " ";
    public String FMT_P_AC_NAME = " ";
    public char FILLER6 = 0X02;
    public String FMT_CCY = " ";
    public char FMT_CCY_TYP = ' ';
    public double FMT_VS_CURR_BAL = 0;
    public char FILLER10 = 0X01;
    public String FMT_VS_AC_NAME = " ";
    public char FILLER12 = 0X02;
    public String FMT_VS_CON_NAME = " ";
    public char FILLER14 = 0X02;
    public String FMT_VS_CON_TEL = " ";
    public String FMT_VS_CON_ADDR = " ";
    public char FILLER17 = 0X02;
    public int FMT_OPEN_DT = 0;
    public int FMT_CLOSE_DT = 0;
    public String FMT_REMARK = " ";
    public char FILLER21 = 0X02;
    public char FMT_CINT_FLG = ' ';
    public String FMT_OPDP_OTH = " ";
    public DDCOINDE_VS_SPE_DATA[] VS_SPE_DATA = new DDCOINDE_VS_SPE_DATA[3];
    public String FMT_VS_IDTYP = " ";
    public String FMT_VS_IDNO = " ";
    public char FILLER30 = 0X02;
    public String FMT_VS_MMO = " ";
    public char FMT_VS_FLG = ' ';
    public String FMT_VS_CHNLNO = " ";
    public String FMT_VS_INTAC = " ";
    public String FMT_VS_SYSNO = " ";
    public DDCOINDE_FMT_DETAIL() {
        for (int i=0;i<3;i++) VS_SPE_DATA[i] = new DDCOINDE_VS_SPE_DATA();
    }
}
