package com.hisun.CI;

public class CIB5560_AWA_5560 {
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
    public CIB5560_CRS_AREA[] CRS_AREA = new CIB5560_CRS_AREA[25];
    public CIB5560_AWA_5560() {
        for (int i=0;i<25;i++) CRS_AREA[i] = new CIB5560_CRS_AREA();
    }
}
