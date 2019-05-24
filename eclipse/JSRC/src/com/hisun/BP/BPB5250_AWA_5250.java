package com.hisun.BP;

public class BPB5250_AWA_5250 {
    public String RATE_TYP = " ";
    public short RATE_TYP_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String TENOR1 = " ";
    public short TENOR1_NO = 0;
    public String RATEID1 = " ";
    public short RATEID1_NO = 0;
    public int OEFF_DT1 = 0;
    public short OEFF_DT1_NO = 0;
    public double ORATE1 = 0;
    public short ORATE1_NO = 0;
    public int NEFF_DT1 = 0;
    public short NEFF_DT1_NO = 0;
    public double NRATE1 = 0;
    public short NRATE1_NO = 0;
    public BPB5250_BRT_TBL[] BRT_TBL = new BPB5250_BRT_TBL[40];
    public char FUNC = ' ';
    public short FUNC_NO = 0;
    public BPB5250_AWA_5250() {
        for (int i=0;i<40;i++) BRT_TBL[i] = new BPB5250_BRT_TBL();
    }
}
