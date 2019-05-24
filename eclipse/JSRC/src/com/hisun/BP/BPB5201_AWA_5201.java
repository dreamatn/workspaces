package com.hisun.BP;

public class BPB5201_AWA_5201 {
    public int STARTD = 0;
    public short STARTD_NO = 0;
    public int ENDD = 0;
    public short ENDD_NO = 0;
    public int DT = 0;
    public short DT_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public BPB5201_CCY_A[] CCY_A = new BPB5201_CCY_A[10];
    public BPB5201_BASE_A[] BASE_A = new BPB5201_BASE_A[10];
    public BPB5201_TENOR_A[] TENOR_A = new BPB5201_TENOR_A[10];
    public BPB5201_R_BR_A[] R_BR_A = new BPB5201_R_BR_A[10];
    public BPB5201_R_CCY_A[] R_CCY_A = new BPB5201_R_CCY_A[10];
    public BPB5201_R_BASE_A[] R_BASE_A = new BPB5201_R_BASE_A[10];
    public BPB5201_R_TERM_A[] R_TERM_A = new BPB5201_R_TERM_A[10];
    public BPB5201_FORMAT_A[] FORMAT_A = new BPB5201_FORMAT_A[10];
    public BPB5201_RATE_A[] RATE_A = new BPB5201_RATE_A[10];
    public BPB5201_FILL_TBL[] FILL_TBL = new BPB5201_FILL_TBL[10];
    public BPB5201_AWA_5201() {
        for (int i=0;i<10;i++) CCY_A[i] = new BPB5201_CCY_A();
        for (int i=0;i<10;i++) BASE_A[i] = new BPB5201_BASE_A();
        for (int i=0;i<10;i++) TENOR_A[i] = new BPB5201_TENOR_A();
        for (int i=0;i<10;i++) R_BR_A[i] = new BPB5201_R_BR_A();
        for (int i=0;i<10;i++) R_CCY_A[i] = new BPB5201_R_CCY_A();
        for (int i=0;i<10;i++) R_BASE_A[i] = new BPB5201_R_BASE_A();
        for (int i=0;i<10;i++) R_TERM_A[i] = new BPB5201_R_TERM_A();
        for (int i=0;i<10;i++) FORMAT_A[i] = new BPB5201_FORMAT_A();
        for (int i=0;i<10;i++) RATE_A[i] = new BPB5201_RATE_A();
        for (int i=0;i<10;i++) FILL_TBL[i] = new BPB5201_FILL_TBL();
    }
}
