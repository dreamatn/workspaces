package com.hisun.BP;

public class BPCSTSTS {
    public char FUNC = ' ';
    public char UPDATE_FUNC = ' ';
    public char SUB_IND = ' ';
    public String TSTS_APP = " ";
    public String TSTS_NO = " ";
    public String DESC = " ";
    public String PB_TYPE = " ";
    public short FLD_CNT = 0;
    public BPCSTSTS_ITEM_LST_DATA[] ITEM_LST_DATA = new BPCSTSTS_ITEM_LST_DATA[33];
    public String CHNL = " ";
    public BPCSTSTS() {
        for (int i=0;i<33;i++) ITEM_LST_DATA[i] = new BPCSTSTS_ITEM_LST_DATA();
    }
}
