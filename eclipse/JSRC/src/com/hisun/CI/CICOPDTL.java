package com.hisun.CI;

public class CICOPDTL {
    public char FUNC = ' ';
    public String TYPE = " ";
    public String OBJ_TYP = " ";
    public String LC_OBJ = " ";
    public String CUS_AC = " ";
    public CICOPDTL_DTL[] DTL = new CICOPDTL_DTL[4];
    public CICOPDTL_VAL_INFO[] VAL_INFO = new CICOPDTL_VAL_INFO[50];
    public int STA_DT = 0;
    public int END_DT = 0;
    public char END_FLG = ' ';
    public CICOPDTL() {
        for (int i=0;i<4;i++) DTL[i] = new CICOPDTL_DTL();
        for (int i=0;i<50;i++) VAL_INFO[i] = new CICOPDTL_VAL_INFO();
    }
}
