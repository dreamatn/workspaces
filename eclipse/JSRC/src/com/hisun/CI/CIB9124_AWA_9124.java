package com.hisun.CI;

public class CIB9124_AWA_9124 {
    public String REL_NAME = " ";
    public short REL_NAME_NO = 0;
    public String REL_IDTP = " ";
    public short REL_IDTP_NO = 0;
    public String REL_IDNO = " ";
    public short REL_IDNO_NO = 0;
    public String CRS_TYPE = " ";
    public short CRS_TYPE_NO = 0;
    public String CRS_DESC = " ";
    public short CRS_DESC_NO = 0;
    public int PROOF_DT = 0;
    public short PROOF_DT_NO = 0;
    public char PROOF_CH = ' ';
    public short PROOF_CH_NO = 0;
    public CIB9124_CRS_AREA[] CRS_AREA = new CIB9124_CRS_AREA[25];
    public CIB9124_AWA_9124() {
        for (int i=0;i<25;i++) CRS_AREA[i] = new CIB9124_CRS_AREA();
    }
}
