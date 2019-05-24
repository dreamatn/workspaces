package com.hisun.CI;

public class CICI3010 {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String NAME = " ";
    public String OBJ_TYP = " ";
    public char SPE_FLG = ' ';
    public char CT_FLG = ' ';
    public String TX_TYPE = " ";
    public String CON_SPT = " ";
    public String CON_MTH = " ";
    public String CON_LWTP = " ";
    public CICI3010_VAL[] VAL = new CICI3010_VAL[7];
    public char HIS_FLG = ' ';
    public char ONE_FLG = ' ';
    public String MTX_TYP1 = " ";
    public String MTX_TYP2 = " ";
    public String MTX_TYP3 = " ";
    public int STA_TM = 0;
    public int END_TM = 0;
    public CICI3010() {
        for (int i=0;i<7;i++) VAL[i] = new CICI3010_VAL();
    }
}
