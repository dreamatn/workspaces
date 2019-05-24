package com.hisun.PY;

public class PYCPCFTA {
    public PYCPCFTA_RC RC = new PYCPCFTA_RC();
    public PYCPCFTA_BR_GP[] BR_GP = new PYCPCFTA_BR_GP[20];
    public char FTA_FLG = ' ';
    public char VILL_BK_FLG = ' ';
    public char BR_RGN_FLG = ' ';
    public PYCPCFTA_FLG_DATA[] FLG_DATA = new PYCPCFTA_FLG_DATA[20];
    public PYCPCFTA() {
        for (int i=0;i<20;i++) BR_GP[i] = new PYCPCFTA_BR_GP();
        for (int i=0;i<20;i++) FLG_DATA[i] = new PYCPCFTA_FLG_DATA();
    }
}
