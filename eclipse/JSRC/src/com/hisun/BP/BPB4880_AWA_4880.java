package com.hisun.BP;

public class BPB4880_AWA_4880 {
    public String AP_MMO = " ";
    public short AP_MMO_NO = 0;
    public String TBL_NO = " ";
    public short TBL_NO_NO = 0;
    public String DESC = " ";
    public short DESC_NO = 0;
    public String FLAG = " ";
    public short FLAG_NO = 0;
    public String CHNL = " ";
    public short CHNL_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public String EXTYPE = " ";
    public short EXTYPE_NO = 0;
    public int EFFDTE = 0;
    public short EFFDTE_NO = 0;
    public int EXPDTE = 0;
    public short EXPDTE_NO = 0;
    public BPB4880_TAMT_ARR[] TAMT_ARR = new BPB4880_TAMT_ARR[6];
    public BPB4880_AWA_4880() {
        for (int i=0;i<6;i++) TAMT_ARR[i] = new BPB4880_TAMT_ARR();
    }
}
