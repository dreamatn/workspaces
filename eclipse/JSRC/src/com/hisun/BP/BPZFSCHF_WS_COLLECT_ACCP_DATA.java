package com.hisun.BP;

public class BPZFSCHF_WS_COLLECT_ACCP_DATA {
    char WS_CLT_RANGE = ' ';
    char WS_FEE_CD_FND_FLG = ' ';
    int WS_CD_CNT = 0;
    BPZFSCHF_WS_FEE_CDS[] WS_FEE_CDS = new BPZFSCHF_WS_FEE_CDS[20];
    public BPZFSCHF_WS_COLLECT_ACCP_DATA() {
        for (int i=0;i<20;i++) WS_FEE_CDS[i] = new BPZFSCHF_WS_FEE_CDS();
    }
}
