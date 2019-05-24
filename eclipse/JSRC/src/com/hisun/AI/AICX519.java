package com.hisun.AI;

public class AICX519 {
    public String COA_FLG = " ";
    public long COA_FR = 0;
    public long COA_TO = 0;
    public short CNT = 0;
    public AICX519_BR[] BR = new AICX519_BR[220];
    public AICX519() {
        for (int i=0;i<220;i++) BR[i] = new AICX519_BR();
    }
}
