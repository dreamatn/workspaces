package com.hisun.BP;

public class BPB3808_AWA_3808 {
    public char PAY_TYPE = ' ';
    public short PAY_TYPE_NO = 0;
    public String BV_CODE = " ";
    public short BV_CODE_NO = 0;
    public String BV_NAME = " ";
    public short BV_NAME_NO = 0;
    public BPB3808_BV_INFO[] BV_INFO = new BPB3808_BV_INFO[5];
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String TR_TLR = " ";
    public short TR_TLR_NO = 0;
    public int MOV_DT = 0;
    public short MOV_DT_NO = 0;
    public long CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public String DRW_NM = " ";
    public short DRW_NM_NO = 0;
    public String DRW_IDT = " ";
    public short DRW_IDT_NO = 0;
    public String DRW_IDN = " ";
    public short DRW_IDN_NO = 0;
    public BPB3808_AWA_3808() {
        for (int i=0;i<5;i++) BV_INFO[i] = new BPB3808_BV_INFO();
    }
}
