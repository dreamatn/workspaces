package com.hisun.BP;

public class BPB2120_AWA_2120 {
    public String IN_TLR = " ";
    public short IN_TLR_NO = 0;
    public int IN_BR = 0;
    public short IN_BR_NO = 0;
    public int OUT_BR = 0;
    public short OUT_BR_NO = 0;
    public String OUT_TLR = " ";
    public short OUT_TLR_NO = 0;
    public int BV_DATE = 0;
    public short BV_DATE_NO = 0;
    public String BV_NO = " ";
    public short BV_NO_NO = 0;
    public String CONF_NOG = " ";
    public short CONF_NOG_NO = 0;
    public int CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public int MOVE_DT = 0;
    public short MOVE_DT_NO = 0;
    public char CS_KIND = ' ';
    public short CS_KIND_NO = 0;
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String TR_TLR = " ";
    public short TR_TLR_NO = 0;
    public BPB2120_CCY_INFO[] CCY_INFO = new BPB2120_CCY_INFO[5];
    public BPB2120_P_INFO[] P_INFO = new BPB2120_P_INFO[60];
    public String APP_NO_G = " ";
    public short APP_NO_G_NO = 0;
    public int APP_NO = 0;
    public short APP_NO_NO = 0;
    public char ALLOT_TP = ' ';
    public short ALLOT_TP_NO = 0;
    public BPB2120_AWA_2120() {
        for (int i=0;i<5;i++) CCY_INFO[i] = new BPB2120_CCY_INFO();
        for (int i=0;i<60;i++) P_INFO[i] = new BPB2120_P_INFO();
    }
}
