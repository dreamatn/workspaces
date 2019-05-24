package com.hisun.CI;

public class CICQACR {
    public CICQACR_RC RC = new CICQACR_RC();
    public char FUNC = ' ';
    public char CLS_FLG = ' ';
    public int OPEN_BR = 0;
    public String FILLER1 = " ";
    public String CI_NO = " ";
    public char ENTY_TYP = ' ';
    public String AGR_NO = " ";
    public String I_CNTRCT_TYP = " ";
    public short CNT = 0;
    public String AC_CNM = " ";
    public String AC_ENM = " ";
    public CICQACR_ACS[] ACS = new CICQACR_ACS[3000];
    public String FRM_APP = " ";
    public CICQACR() {
        for (int i=0;i<3000;i++) ACS[i] = new CICQACR_ACS();
    }
}
