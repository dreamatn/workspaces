package com.hisun.BP;

public class BPCOTSTS {
    public String TSTS_APP = " ";
    public String TSTS_NO = " ";
    public String DESC = " ";
    public char FILLER4 = 0X02;
    public String PB_TYPE = " ";
    public String PB_DESC = " ";
    public char FILLER7 = 0X02;
    public short FLD_CNT = 0;
    public BPCOTSTS_ITEM_LST_DATA[] ITEM_LST_DATA = new BPCOTSTS_ITEM_LST_DATA[33];
    public String CHNL = " ";
    public BPCOTSTS() {
        for (int i=0;i<33;i++) ITEM_LST_DATA[i] = new BPCOTSTS_ITEM_LST_DATA();
    }
}
