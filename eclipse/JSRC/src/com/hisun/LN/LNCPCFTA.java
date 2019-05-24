package com.hisun.LN;

public class LNCPCFTA {
    public LNCPCFTA_RC RC = new LNCPCFTA_RC();
    public LNCPCFTA_BR_GP[] BR_GP = new LNCPCFTA_BR_GP[20];
    public char FTA_FLG = ' ';
    public char VILL_BK_FLG = ' ';
    public char BR_RGN_FLG = ' ';
    public LNCPCFTA_FLG_DATA[] FLG_DATA = new LNCPCFTA_FLG_DATA[20];
    public LNCPCFTA() {
        for (int i=0;i<20;i++) BR_GP[i] = new LNCPCFTA_BR_GP();
        for (int i=0;i<20;i++) FLG_DATA[i] = new LNCPCFTA_FLG_DATA();
    }
}
