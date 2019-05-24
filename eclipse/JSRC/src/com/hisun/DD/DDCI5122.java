package com.hisun.DD;

public class DDCI5122 {
    public char I_OPT = ' ';
    public String I_DD_AC = " ";
    public String I_CCY = " ";
    public char I_FLG = ' ';
    public String I_CI_CNM = " ";
    public String I_CI_ENM = " ";
    public String I_PROLNO = " ";
    public int I_MTDT = 0;
    public int I_STDT = 0;
    public int I_EDDT = 0;
    public char I_JXOPT = ' ';
    public short I_SEQ2 = 0;
    public char I_FCTYP = ' ';
    public char I_CJTYP = ' ';
    public DDCI5122_TIR_INF[] TIR_INF = new DDCI5122_TIR_INF[5];
    public DDCI5122() {
        for (int i=0;i<5;i++) TIR_INF[i] = new DDCI5122_TIR_INF();
    }
}
