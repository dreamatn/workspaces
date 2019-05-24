package com.hisun.DC;

public class DCCSBALR_OUT_DATA {
    public String CARD = " ";
    public String VIA_AC = " ";
    public String ID_TYPE = " ";
    public String ID_NO = " ";
    public char FILLER13 = 0X02;
    public String SVR_LVL = " ";
    public DCCSBALR_BAL_ARRAY[] BAL_ARRAY = new DCCSBALR_BAL_ARRAY[20];
    public DCCSBALR_OUT_DATA() {
        for (int i=0;i<20;i++) BAL_ARRAY[i] = new DCCSBALR_BAL_ARRAY();
    }
}
