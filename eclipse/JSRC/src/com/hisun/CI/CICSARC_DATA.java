package com.hisun.CI;

public class CICSARC_DATA {
    public String REL_NAME = " ";
    public String REL_IDTP = " ";
    public String REL_IDNO = " ";
    public String CRS_TYPE = " ";
    public String CRS_DESC = " ";
    public int PROOF_DT = 0;
    public char PROOF_CH = ' ';
    public CICSARC_CRS_AREA[] CRS_AREA = new CICSARC_CRS_AREA[25];
    public CICSARC_DATA() {
        for (int i=0;i<25;i++) CRS_AREA[i] = new CICSARC_CRS_AREA();
    }
}
