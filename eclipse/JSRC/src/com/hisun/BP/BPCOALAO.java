package com.hisun.BP;

public class BPCOALAO {
    public int APP_NO = 0;
    public int APP_BR = 0;
    public String APP_NM = " ";
    public char FILLER4 = 0X02;
    public int UP_BR = 0;
    public String UP_TLR = " ";
    public char ALLOT_TYPE = ' ';
    public char APP_TYPE = ' ';
    public char APP_STS = ' ';
    public int CONF_NO = 0;
    public int MOV_DT = 0;
    public String APP_CCY = " ";
    public double APP_AMT = 0;
    public char FILLER14 = 0X01;
    public double OUT_AMT = 0;
    public char FILLER16 = 0X01;
    public String APP_TLR = " ";
    public int APP_DT = 0;
    public String APP_NOTE = " ";
    public char FILLER20 = 0X02;
    public String ACP_TLR = " ";
    public int ACP_DT = 0;
    public String CONF_TLR = " ";
    public int CONF_DT = 0;
    public String ADT_TLR = " ";
    public int ADT_DT = 0;
    public String REJ_TLR = " ";
    public int REJ_DT = 0;
    public String UNDO_TLR = " ";
    public int UNDO_DT = 0;
    public String BACK_TLR = " ";
    public int BACK_DT = 0;
    public String IN_TLR = " ";
    public int IN_DT = 0;
    public String OUT_TLR = " ";
    public int OUT_DT = 0;
    public String UPD_TLR = " ";
    public int UPD_DT = 0;
    public BPCOALAO_PVAL_INFO[] PVAL_INFO = new BPCOALAO_PVAL_INFO[20];
    public BPCOALAO() {
        for (int i=0;i<20;i++) PVAL_INFO[i] = new BPCOALAO_PVAL_INFO();
    }
}
