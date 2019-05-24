package com.hisun.LN;

public class LN420_OUT_RECODE {
    public LN420_OUT_HEAD OUT_HEAD = new LN420_OUT_HEAD();
    public LN420_OUT_INFO[] OUT_INFO = new LN420_OUT_INFO[5];
    public LN420_OUT_RECODE() {
        for (int i=0;i<5;i++) OUT_INFO[i] = new LN420_OUT_INFO();
    }
}
