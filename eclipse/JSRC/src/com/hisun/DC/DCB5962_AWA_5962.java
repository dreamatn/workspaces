package com.hisun.DC;

public class DCB5962_AWA_5962 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String PROD_COD = " ";
    public short PROD_COD_NO = 0;
    public String PROD_DSC = " ";
    public short PROD_DSC_NO = 0;
    public int EFFDAT = 0;
    public short EFFDAT_NO = 0;
    public int EXPDAT = 0;
    public short EXPDAT_NO = 0;
    public char CI_TYP = ' ';
    public short CI_TYP_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public char CCY_TYPE = ' ';
    public short CCY_TYPE_NO = 0;
    public DCB5962_PERM_INF[] PERM_INF = new DCB5962_PERM_INF[5];
    public char TRIG_TMS = ' ';
    public short TRIG_TMS_NO = 0;
    public DCB5962_TRIG_INF[] TRIG_INF = new DCB5962_TRIG_INF[5];
    public DCB5962_INT_INFO[] INT_INFO = new DCB5962_INT_INFO[5];
    public short OUT_LVL = 0;
    public short OUT_LVL_NO = 0;
    public short IN_LVL = 0;
    public short IN_LVL_NO = 0;
    public char INOUT_FG = ' ';
    public short INOUT_FG_NO = 0;
    public char OVR_BANK = ' ';
    public short OVR_BANK_NO = 0;
    public char OVR_CARD = ' ';
    public short OVR_CARD_NO = 0;
    public String USAGE = " ";
    public short USAGE_NO = 0;
    public char DRAW_ORD = ' ';
    public short DRAW_ORD_NO = 0;
    public char DRAW_MTH = ' ';
    public short DRAW_MTH_NO = 0;
    public DCB5962_DD_INFO[] DD_INFO = new DCB5962_DD_INFO[10];
    public DCB5962_TD_INFO[] TD_INFO = new DCB5962_TD_INFO[10];
    public DCB5962_AWA_5962() {
        for (int i=0;i<5;i++) PERM_INF[i] = new DCB5962_PERM_INF();
        for (int i=0;i<5;i++) TRIG_INF[i] = new DCB5962_TRIG_INF();
        for (int i=0;i<5;i++) INT_INFO[i] = new DCB5962_INT_INFO();
        for (int i=0;i<10;i++) DD_INFO[i] = new DCB5962_DD_INFO();
        for (int i=0;i<10;i++) TD_INFO[i] = new DCB5962_TD_INFO();
    }
}
