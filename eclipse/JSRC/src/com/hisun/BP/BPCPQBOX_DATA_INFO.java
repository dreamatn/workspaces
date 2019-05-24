package com.hisun.BP;

public class BPCPQBOX_DATA_INFO {
    public int VB_BR = 0;
    public String VB_TLR = " ";
    public String PLBOX_NO = " ";
    public String CASH_TYP = " ";
    public char VB_FLG = ' ';
    public String CCY = " ";
    public String MGR_TLR = " ";
    public double LMT_U = 0;
    public double LMT_L = 0;
    public double BAL = 0;
    public double GD_AMT = 0;
    public double BD_AMT = 0;
    public double HBD_AMT = 0;
    public double L_GD_AMT = 0;
    public double L_BD_AMT = 0;
    public double L_HBD_AMT = 0;
    public double L_TLT_AMT = 0;
    public int LAST_DT = 0;
    public char BAL_FLG = ' ';
    public char STS = ' ';
    public String OPP_TLR = " ";
    public int CNT = 0;
    public BPCPQBOX_CLBI_INF[] CLBI_INF = new BPCPQBOX_CLBI_INF[20];
    public BPCPQBOX_DATA_INFO() {
        for (int i=0;i<20;i++) CLBI_INF[i] = new BPCPQBOX_CLBI_INF();
    }
}
