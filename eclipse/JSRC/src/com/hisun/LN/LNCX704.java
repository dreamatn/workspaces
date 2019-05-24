package com.hisun.LN;

public class LNCX704 {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String CODE = " ";
    public String DESC = " ";
    public String CDESC = " ";
    public char FILLER6 = 0X02;
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public short VAL_LEN = 0;
    public char FILLER10 = 0X01;
    public LNCX704_CNTIM_CTNR_DATA[] CNTIM_CTNR_DATA = new LNCX704_CNTIM_CTNR_DATA[10];
    public LNCX704() {
        for (int i=0;i<10;i++) CNTIM_CTNR_DATA[i] = new LNCX704_CNTIM_CTNR_DATA();
    }
}
