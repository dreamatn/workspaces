package com.hisun.DD;

public class DDCSMTDQ {
    public char OPT = ' ';
    public String AC = " ";
    public String CCY = " ";
    public char FLG = ' ';
    public String CI_CNM = " ";
    public char FILLER6 = 0X02;
    public String CI_ENM = " ";
    public char FILLER8 = 0X02;
    public String ADP_NO = " ";
    public int ADP_DATE = 0;
    public int ADP_STRDATE = 0;
    public int ADP_EXPDATE = 0;
    public char ADP_POST_PERIOD = ' ';
    public short ADP_POST_FEQ = 0;
    public char TIR_TYPE = ' ';
    public char AGSP_FLG = ' ';
    public DDCSMTDQ_TIR_DATA TIR_DATA = new DDCSMTDQ_TIR_DATA();
    public char ADP_STS = ' ';
    public DDCSMTDQ_TIR_INF2[] TIR_INF2 = new DDCSMTDQ_TIR_INF2[5];
    public DDCSMTDQ() {
        for (int i=0;i<5;i++) TIR_INF2[i] = new DDCSMTDQ_TIR_INF2();
    }
}
