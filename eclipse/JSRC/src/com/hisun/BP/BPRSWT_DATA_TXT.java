package com.hisun.BP;

public class BPRSWT_DATA_TXT {
    public BPRSWT_DEST_TR_TBL[] DEST_TR_TBL = new BPRSWT_DEST_TR_TBL[3];
    public char VCH_IND = ' ';
    public String FILLER = " ";
    public String REMARK = " ";
    public BPRSWT_DATA_TXT() {
        for (int i=0;i<3;i++) DEST_TR_TBL[i] = new BPRSWT_DEST_TR_TBL();
    }
}
