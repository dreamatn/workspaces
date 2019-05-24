package com.hisun.BP;

public class BPB2904_AWA_2904 {
    public int PLAN_DT = 0;
    public short PLAN_DT_NO = 0;
    public int BR = 0;
    public short BR_NO = 0;
    public BPB2904_PLAN_INF[] PLAN_INF = new BPB2904_PLAN_INF[60];
    public BPB2904_AWA_2904() {
        for (int i=0;i<60;i++) PLAN_INF[i] = new BPB2904_PLAN_INF();
    }
}
