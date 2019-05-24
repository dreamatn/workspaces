package com.hisun.AI;

public class AIB8051_AWA_8051 {
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public String TYP = " ";
    public short TYP_NO = 0;
    public String CD = " ";
    public short CD_NO = 0;
    public String DESC = " ";
    public short DESC_NO = 0;
    public String CDESC = " ";
    public short CDESC_NO = 0;
    public int EFF_DATE = 0;
    public short EFF_DATE_NO = 0;
    public int EXP_DATE = 0;
    public short EXP_DATE_NO = 0;
    public short VAL_LEN = 0;
    public short VAL_LEN_NO = 0;
    public AIB8051_DATA_INF[] DATA_INF = new AIB8051_DATA_INF[40];
    public AIB8051_AWA_8051() {
        for (int i=0;i<40;i++) DATA_INF[i] = new AIB8051_DATA_INF();
    }
}
