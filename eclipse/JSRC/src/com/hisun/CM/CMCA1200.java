package com.hisun.CM;

public class CMCA1200 {
    public int IPT_DATE = 0;
    public String OTH_KEY = " ";
    public char STR_FLG = ' ';
    public char END_FLG = ' ';
    public short REC_CNT = 0;
    public int EFF_DATE = 0;
    public CMCA1200_VCH_ARRAY[] VCH_ARRAY = new CMCA1200_VCH_ARRAY[7];
    public CMCA1200() {
        for (int i=0;i<7;i++) VCH_ARRAY[i] = new CMCA1200_VCH_ARRAY();
    }
}
