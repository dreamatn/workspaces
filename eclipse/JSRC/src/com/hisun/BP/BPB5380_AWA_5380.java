package com.hisun.BP;

public class BPB5380_AWA_5380 {
    public String FAV_CODE = " ";
    public short FAV_CODE_NO = 0;
    public int EFF_DT = 0;
    public short EFF_DT_NO = 0;
    public int EXP_DT = 0;
    public short EXP_DT_NO = 0;
    public String FAV_DESC = " ";
    public short FAV_DESC_NO = 0;
    public String FAV_CDES = " ";
    public short FAV_CDES_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String FEE_TYPE = " ";
    public short FEE_TYPE_NO = 0;
    public char CAL_MTH = ' ';
    public short CAL_MTH_NO = 0;
    public int STR_TIME = 0;
    public short STR_TIME_NO = 0;
    public int END_TIME = 0;
    public short END_TIME_NO = 0;
    public BPB5380_STR_INFO[] STR_INFO = new BPB5380_STR_INFO[10];
    public int UPD_DT = 0;
    public short UPD_DT_NO = 0;
    public int UPD_TM = 0;
    public short UPD_TM_NO = 0;
    public int UPD_BR = 0;
    public short UPD_BR_NO = 0;
    public String UPD_TLR = " ";
    public short UPD_TLR_NO = 0;
    public String FEE_CODE = " ";
    public short FEE_CODE_NO = 0;
    public char CMP_FLG = ' ';
    public short CMP_FLG_NO = 0;
    public BPB5380_AWA_5380() {
        for (int i=0;i<10;i++) STR_INFO[i] = new BPB5380_STR_INFO();
    }
}
