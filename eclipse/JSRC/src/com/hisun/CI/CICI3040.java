package com.hisun.CI;

public class CICI3040 {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String OBJ_TYP = " ";
    public String LC_OBJ = " ";
    public String CUS_AC = " ";
    public String TX_TYPE = " ";
    public CICI3040_VAL_INFO[] VAL_INFO = new CICI3040_VAL_INFO[3];
    public int LS_LC_NO = 0;
    public int LS_SEQ = 0;
    public int LC_NO2 = 0;
    public int LC_SEQ2 = 0;
    public String LS_CNTYP = " ";
    public int LS_SBSEQ = 0;
    public CICI3040() {
        for (int i=0;i<3;i++) VAL_INFO[i] = new CICI3040_VAL_INFO();
    }
}
