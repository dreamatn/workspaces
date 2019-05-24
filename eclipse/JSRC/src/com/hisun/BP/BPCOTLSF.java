package com.hisun.BP;

public class BPCOTLSF {
    public int O_T_CNT = 0;
    public String TLR = " ";
    public int TLR_BR = 0;
    public short TLR_TKS = 0;
    public String STAF_NO = " ";
    public String TLR_CN_NM = " ";
    public char FILLER7 = 0X02;
    public String TLR_EN_NM = " ";
    public int AC_DATE = 0;
    public int SIGN_TIMES = 0;
    public int PEND_CNT = 0;
    public BPCOTLSF_ARRAY[] ARRAY = new BPCOTLSF_ARRAY[30];
    public BPCOTLSF() {
        for (int i=0;i<30;i++) ARRAY[i] = new BPCOTLSF_ARRAY();
    }
}
