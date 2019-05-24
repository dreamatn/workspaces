package com.hisun.DC;

public class DCB0220_AWA_0220 {
    public String PROD_CD = " ";
    public short PROD_CD_NO = 0;
    public String PROD_NM = " ";
    public short PROD_NM_NO = 0;
    public DCB0220_E_DATA[] E_DATA = new DCB0220_E_DATA[10];
    public DCB0220_A_DATA[] A_DATA = new DCB0220_A_DATA[10];
    public DCB0220_AWA_0220() {
        for (int i=0;i<10;i++) E_DATA[i] = new DCB0220_E_DATA();
        for (int i=0;i<10;i++) A_DATA[i] = new DCB0220_A_DATA();
    }
}
