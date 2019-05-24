package com.hisun.CI;

public class CICI9124 {
    public String REL_NAME = " ";
    public String REL_IDTP = " ";
    public String REL_IDNO = " ";
    public String CRS_TYPE = " ";
    public String CRS_DESC = " ";
    public int PROOF_DT = 0;
    public char PROOF_CH = ' ';
    public CICI9124_CRS_AREA[] CRS_AREA = new CICI9124_CRS_AREA[25];
    public CICI9124() {
        for (int i=0;i<25;i++) CRS_AREA[i] = new CICI9124_CRS_AREA();
    }
}
