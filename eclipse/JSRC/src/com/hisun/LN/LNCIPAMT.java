package com.hisun.LN;

public class LNCIPAMT {
    public LNCIPAMT_RC RC = new LNCIPAMT_RC();
    public String CTA_NO = " ";
    public double P_AMT = 0;
    public double I_AMT = 0;
    public double O_AMT = 0;
    public double L_AMT = 0;
    public double F_AMT = 0;
    public char INNER_OUT_FLG = ' ';
    public LNCIPAMT_PART_DATA[] PART_DATA = new LNCIPAMT_PART_DATA[10];
    public LNCIPAMT() {
        for (int i=0;i<10;i++) PART_DATA[i] = new LNCIPAMT_PART_DATA();
    }
}
