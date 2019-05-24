package com.hisun.BP;

public class BPCOAPOL {
    public int APP_NO = 0;
    public int BR = 0;
    public String SUPR_NM = " ";
    public char FILLER4 = 0X02;
    public char APP_TYPE = ' ';
    public int APP_BR = 0;
    public String APP_NM = " ";
    public char FILLER8 = 0X02;
    public BPCOAPOL_BV_INFO[] BV_INFO = new BPCOAPOL_BV_INFO[10];
    public char APP_STS = ' ';
    public long CONF_NO = 0;
    public int APP_DT = 0;
    public String APP_TLR = " ";
    public int ACP_DT = 0;
    public String ACP_TLR = " ";
    public int CONF_DT = 0;
    public String CONF_TLR = " ";
    public int UNDO_DT = 0;
    public String UNDO_TLR = " ";
    public String BACK_TLR = " ";
    public int BACK_DT = 0;
    public int REJ_DT = 0;
    public String REJ_TLR = " ";
    public int ADT_DT = 0;
    public String ADT_TLR = " ";
    public int OUT_DT = 0;
    public String OUT_TLR = " ";
    public int IN_DT = 0;
    public String IN_TLR = " ";
    public int UPD_DT = 0;
    public String UPD_TLR = " ";
    public BPCOAPOL() {
        for (int i=0;i<10;i++) BV_INFO[i] = new BPCOAPOL_BV_INFO();
    }
}
