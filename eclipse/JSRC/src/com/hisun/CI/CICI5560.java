package com.hisun.CI;

public class CICI5560 {
    public String REL_NAME = " ";
    public String REL_IDTP = " ";
    public String REL_IDNO = " ";
    public String CRS_TYPE = " ";
    public String CRS_DESC = " ";
    public int PROOF_DT = 0;
    public char PROOF_CH = ' ';
    public CICI5560_CRS_AREA[] CRS_AREA = new CICI5560_CRS_AREA[25];
    public CICI5560() {
        for (int i=0;i<25;i++) CRS_AREA[i] = new CICI5560_CRS_AREA();
    }
}
