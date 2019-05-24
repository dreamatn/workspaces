package com.hisun.BP;

public class BPB2902_AWA_2902 {
    public int PLAN_DT = 0;
    public short PLAN_DT_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public BPB2902_PLAN_INF[] PLAN_INF = new BPB2902_PLAN_INF[60];
    public BPB2902_AWA_2902() {
        for (int i=0;i<60;i++) PLAN_INF[i] = new BPB2902_PLAN_INF();
    }
}
