package com.hisun.CM;

public class CMCS4800 {
    public char FUNC = ' ';
    public char BV_TYP = ' ';
    public String BV_CODE = " ";
    public CMCS4800_AC[] AC = new CMCS4800_AC[20];
    public String REQ_ID = " ";
    public int REQ_DATE = 0;
    public String REQ_JRN = " ";
    public CMCS4800() {
        for (int i=0;i<20;i++) AC[i] = new CMCS4800_AC();
    }
}
