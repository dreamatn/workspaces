package com.hisun.LN;

public class LNB5250_AWA_5250 {
    public char FUN_CODE = ' ';
    public short FUN_CODE_NO = 0;
    public String FAC_NO = " ";
    public short FAC_NO_NO = 0;
    public String FAC_NM = " ";
    public short FAC_NM_NO = 0;
    public int BUF_NO = 0;
    public short BUF_NO_NO = 0;
    public LNB5250_LEV_FLG[] LEV_FLG = new LNB5250_LEV_FLG[9];
    public String CCY = " ";
    public short CCY_NO = 0;
    public LNB5250_CTA_NO[] CTA_NO = new LNB5250_CTA_NO[9];
    public double EXG_BUF = 0;
    public short EXG_BUF_NO = 0;
    public LNB5250_CONT_DES[] CONT_DES = new LNB5250_CONT_DES[9];
    public String TS = " ";
    public short TS_NO = 0;
    public LNB5250_AWA_5250() {
        for (int i=0;i<9;i++) LEV_FLG[i] = new LNB5250_LEV_FLG();
        for (int i=0;i<9;i++) CTA_NO[i] = new LNB5250_CTA_NO();
        for (int i=0;i<9;i++) CONT_DES[i] = new LNB5250_CONT_DES();
    }
}
