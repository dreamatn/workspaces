package com.hisun.CI;

public class CICQPDTL {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String OBJ_TYP = " ";
    public String LC_OBJ = " ";
    public String CUS_AC = " ";
    public String TX_TYPE = " ";
    public CICQPDTL_VAL_INFO[] VAL_INFO = new CICQPDTL_VAL_INFO[3];
    public int LS_LC_NO = 0;
    public int LS_SEQ = 0;
    public int LS_LC_NO2 = 0;
    public int LS_LC_SEQ2 = 0;
    public String LS_CNTYP2 = " ";
    public int LS_SBSEQ2 = 0;
    public CICQPDTL() {
        for (int i=0;i<3;i++) VAL_INFO[i] = new CICQPDTL_VAL_INFO();
    }
}
