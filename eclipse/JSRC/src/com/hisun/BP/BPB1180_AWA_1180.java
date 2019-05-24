package com.hisun.BP;

public class BPB1180_AWA_1180 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String DER_CODE = " ";
    public short DER_CODE_NO = 0;
    public String DER_RES = " ";
    public short DER_RES_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public BPB1180_DER_INFO[] DER_INFO = new BPB1180_DER_INFO[50];
    public BPB1180_AWA_1180() {
        for (int i=0;i<50;i++) DER_INFO[i] = new BPB1180_DER_INFO();
    }
}
