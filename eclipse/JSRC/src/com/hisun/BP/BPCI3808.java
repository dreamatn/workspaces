package com.hisun.BP;

public class BPCI3808 {
    public char PAY_TYPE = ' ';
    public String BV_CODE = " ";
    public String BV_NAME = " ";
    public BPCI3808_BV_INFO[] BV_INFO = new BPCI3808_BV_INFO[5];
    public String REMARK = " ";
    public int TR_BR = 0;
    public String TR_TLR = " ";
    public int MOV_DT = 0;
    public long CONF_NO = 0;
    public String DRW_NM = " ";
    public String DRW_IDT = " ";
    public String DRW_IDN = " ";
    public BPCI3808() {
        for (int i=0;i<5;i++) BV_INFO[i] = new BPCI3808_BV_INFO();
    }
}
