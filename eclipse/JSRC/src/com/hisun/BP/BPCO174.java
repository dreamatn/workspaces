package com.hisun.BP;

public class BPCO174 {
    public char PAY_TYP = ' ';
    public String BV_CODE = " ";
    public String BV_NAME = " ";
    public char FILLER4 = 0X02;
    public BPCO174_BV_INFO[] BV_INFO = new BPCO174_BV_INFO[5];
    public String REMARK = " ";
    public char FILLER11 = 0X02;
    public String DRW_NM = " ";
    public char FILLER13 = 0X02;
    public String DRW_IDT = " ";
    public String DRW_IDN = " ";
    public char FILLER16 = 0X02;
    public BPCO174() {
        for (int i=0;i<5;i++) BV_INFO[i] = new BPCO174_BV_INFO();
    }
}
