package com.hisun.BP;

public class BPB1188_AWA_1188 {
    public String CHG_AC = " ";
    public short CHG_AC_NO = 0;
    public String FABF_AC = " ";
    public short FABF_AC_NO = 0;
    public String FEE_CODE = " ";
    public short FEE_CODE_NO = 0;
    public String PRD_CODE = " ";
    public short PRD_CODE_NO = 0;
    public double FEE_AMT = 0;
    public short FEE_AMT_NO = 0;
    public char AC_TYP = ' ';
    public short AC_TYP_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String MMO = " ";
    public short MMO_NO = 0;
    public BPB1188_FEE_INFO[] FEE_INFO = new BPB1188_FEE_INFO[40];
    public BPB1188_AWA_1188() {
        for (int i=0;i<40;i++) FEE_INFO[i] = new BPB1188_FEE_INFO();
    }
}
