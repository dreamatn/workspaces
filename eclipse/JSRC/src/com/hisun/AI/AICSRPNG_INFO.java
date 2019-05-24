package com.hisun.AI;

public class AICSRPNG_INFO {
    public String RPT_ID = " ";
    public String RPT_NAME = " ";
    public String ITM_CNRT = " ";
    public short SEQ = 0;
    public char CALC_MTH = ' ';
    public String DESC = " ";
    public AICSRPNG_CNT[] CNT = new AICSRPNG_CNT[60];
    public int UPDTBL_DATE = 0;
    public AICSRPNG_INFO() {
        for (int i=0;i<60;i++) CNT[i] = new AICSRPNG_CNT();
    }
}
