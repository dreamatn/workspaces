package com.hisun.AI;

public class AIRGPCY_DATA_INF {
    public AIRGPCY_CY_OCC[] CY_OCC = new AIRGPCY_CY_OCC[40];
    public AIRGPCY_DATA_INF() {
        for (int i=0;i<40;i++) CY_OCC[i] = new AIRGPCY_CY_OCC();
    }
}
