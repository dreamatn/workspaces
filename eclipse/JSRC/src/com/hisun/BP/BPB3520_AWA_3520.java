package com.hisun.BP;

public class BPB3520_AWA_3520 {
    public int MOV_DT = 0;
    public short MOV_DT_NO = 0;
    public long CONF_NO = 0;
    public short CONF_NO_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public String TLR = " ";
    public short TLR_NO = 0;
    public char BV_STS = ' ';
    public short BV_STS_NO = 0;
    public BPB3520_BV_DATA[] BV_DATA = new BPB3520_BV_DATA[10];
    public char PSW_TYP = ' ';
    public short PSW_TYP_NO = 0;
    public String TLRC_PSW = " ";
    public short TLRC_PSW_NO = 0;
    public String TLRK_PSW = " ";
    public short TLRK_PSW_NO = 0;
    public char DE_TYP = ' ';
    public short DE_TYP_NO = 0;
    public char PAY_TYP = ' ';
    public short PAY_TYP_NO = 0;
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public int TR_BR = 0;
    public short TR_BR_NO = 0;
    public String TR_TLR = " ";
    public short TR_TLR_NO = 0;
    public String FEE_ACNO = " ";
    public short FEE_ACNO_NO = 0;
    public char BV_FUNC = ' ';
    public short BV_FUNC_NO = 0;
    public String REP_TLR = " ";
    public short REP_TLR_NO = 0;
    public String DRW_NM = " ";
    public short DRW_NM_NO = 0;
    public String DRW_IDT = " ";
    public short DRW_IDT_NO = 0;
    public String DRW_IDN = " ";
    public short DRW_IDN_NO = 0;
    public char OUT_TYP = ' ';
    public short OUT_TYP_NO = 0;
    public BPB3520_AWA_3520() {
        for (int i=0;i<10;i++) BV_DATA[i] = new BPB3520_BV_DATA();
    }
}
