package com.hisun.BP;

public class BPCSAPLO {
    public int APP_NO = 0;
    public int BR = 0;
    public char APP_TYPE = ' ';
    public int APP_BR = 0;
    public BPCSAPLO_BV_INFO[] BV_INFO = new BPCSAPLO_BV_INFO[30];
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
    public int BACK_DT = 0;
    public String BACK_TLR = " ";
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
    public BPCSAPLO() {
        for (int i=0;i<30;i++) BV_INFO[i] = new BPCSAPLO_BV_INFO();
    }
}
