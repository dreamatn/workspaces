package com.hisun.AI;

public class AIRGPBR_DATA_INF {
    public AIRGPBR_BR_OCC[] BR_OCC = new AIRGPBR_BR_OCC[40];
    public AIRGPBR_DATA_INF() {
        for (int i=0;i<40;i++) BR_OCC[i] = new AIRGPBR_BR_OCC();
    }
}
