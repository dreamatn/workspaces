package com.hisun.AI;

public class AICPQBAL_OUTPUT_DATA {
    public double LEDG_BAL = 0;
    public double VALUE_BAL = 0;
    public AICPQBAL_SGL_BAL_ARY[] SGL_BAL_ARY = new AICPQBAL_SGL_BAL_ARY[500];
    public AICPQBAL_OUTPUT_DATA() {
        for (int i=0;i<500;i++) SGL_BAL_ARY[i] = new AICPQBAL_SGL_BAL_ARY();
    }
}
