package com.hisun.BP;

public class BPB5203_AWA_5203 {
    public int DT = 0;
    public short DT_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public String CCY = " ";
    public short CCY_NO = 0;
    public String BASE_TYP = " ";
    public short BASE_TYP_NO = 0;
    public BPB5203_TAL_A[] TAL_A = new BPB5203_TAL_A[50];
    public String TENOR_B = " ";
    public short TENOR_B_NO = 0;
    public BPB5203_AWA_5203() {
        for (int i=0;i<50;i++) TAL_A[i] = new BPB5203_TAL_A();
    }
}
