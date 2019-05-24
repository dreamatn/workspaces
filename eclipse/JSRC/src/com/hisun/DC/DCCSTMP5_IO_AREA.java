package com.hisun.DC;

public class DCCSTMP5_IO_AREA {
    public char FUNC = ' ';
    public String PROD_COD = " ";
    public String PROD_DSC = " ";
    public String EFFDAT = " ";
    public String EXPDAT = " ";
    public char CI_TYP = ' ';
    public String CCY = " ";
    public char CCY_TYPE = ' ';
    public DCCSTMP5_PERM_INF[] PERM_INF = new DCCSTMP5_PERM_INF[5];
    public char TRIG_TMS = ' ';
    public DCCSTMP5_TRIG_INF[] TRIG_INF = new DCCSTMP5_TRIG_INF[5];
    public DCCSTMP5_INT_INFO[] INT_INFO = new DCCSTMP5_INT_INFO[5];
    public short OUT_LVL = 0;
    public short IN_LVL = 0;
    public char INOUT_FG = ' ';
    public char OVR_BANK = ' ';
    public char OVR_CARD = ' ';
    public String USAGE = " ";
    public char DRAW_ORD = ' ';
    public char DRAW_MTH = ' ';
    public DCCSTMP5_DD_INFO[] DD_INFO = new DCCSTMP5_DD_INFO[10];
    public DCCSTMP5_TD_INFO[] TD_INFO = new DCCSTMP5_TD_INFO[10];
    public DCCSTMP5_IO_AREA() {
        for (int i=0;i<5;i++) PERM_INF[i] = new DCCSTMP5_PERM_INF();
        for (int i=0;i<5;i++) TRIG_INF[i] = new DCCSTMP5_TRIG_INF();
        for (int i=0;i<5;i++) INT_INFO[i] = new DCCSTMP5_INT_INFO();
        for (int i=0;i<10;i++) DD_INFO[i] = new DCCSTMP5_DD_INFO();
        for (int i=0;i<10;i++) TD_INFO[i] = new DCCSTMP5_TD_INFO();
    }
}
