package com.hisun.BP;

public class BPB5810_AWA_5810 {
    public char CMZ_FLG = ' ';
    public short CMZ_FLG_NO = 0;
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public String CMZ_AC = " ";
    public short CMZ_AC_NO = 0;
    public String CMZ_BIZ = " ";
    public short CMZ_BIZ_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public char ENTI_FLG = ' ';
    public short ENTI_FLG_NO = 0;
    public String CMZ_DESC = " ";
    public short CMZ_DESC_NO = 0;
    public BPB5810_FEE_INFO[] FEE_INFO = new BPB5810_FEE_INFO[20];
    public BPB5810_AWA_5810() {
        for (int i=0;i<20;i++) FEE_INFO[i] = new BPB5810_FEE_INFO();
    }
}
