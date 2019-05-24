package com.hisun.BP;

public class BPCXP15_DATA_TXT {
    public char VCH_IND = ' ';
    public String REMARK = " ";
    public BPCXP15_DEST_TR_TBL[] DEST_TR_TBL = new BPCXP15_DEST_TR_TBL[3];
    public BPCXP15_DATA_TXT() {
        for (int i=0;i<3;i++) DEST_TR_TBL[i] = new BPCXP15_DEST_TR_TBL();
    }
}
