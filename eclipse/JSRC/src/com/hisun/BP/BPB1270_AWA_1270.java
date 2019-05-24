package com.hisun.BP;

public class BPB1270_AWA_1270 {
    public char CLT_TYPE = ' ';
    public short CLT_TYPE_NO = 0;
    public char SGN_TYPE = ' ';
    public short SGN_TYPE_NO = 0;
    public String CI_AC = " ";
    public short CI_AC_NO = 0;
    public String FEE_CD = " ";
    public short FEE_CD_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public int ST_DT = 0;
    public short ST_DT_NO = 0;
    public int ED_DT = 0;
    public short ED_DT_NO = 0;
    public String SGN_CINO = " ";
    public short SGN_CINO_NO = 0;
    public String SGN_AC = " ";
    public short SGN_AC_NO = 0;
    public String CHG_AC = " ";
    public short CHG_AC_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public char SGN_RNG = ' ';
    public short SGN_RNG_NO = 0;
    public char CAL_CYC = ' ';
    public short CAL_CYC_NO = 0;
    public short PERD_CNT = 0;
    public short PERD_CNT_NO = 0;
    public char HOLI_MTH = ' ';
    public short HOLI_MTH_NO = 0;
    public String HLD_NO = " ";
    public short HLD_NO_NO = 0;
    public String REMARK = " ";
    public short REMARK_NO = 0;
    public BPB1270_FEE_CDS[] FEE_CDS = new BPB1270_FEE_CDS[20];
    public int PAGE_NUM = 0;
    public short PAGE_NUM_NO = 0;
    public int PAGE_ROW = 0;
    public short PAGE_ROW_NO = 0;
    public BPB1270_AWA_1270() {
        for (int i=0;i<20;i++) FEE_CDS[i] = new BPB1270_FEE_CDS();
    }
}
