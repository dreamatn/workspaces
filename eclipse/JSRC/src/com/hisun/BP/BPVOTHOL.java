package com.hisun.BP;

import com.hisun.PN.OTHOL_HOL_DATA;
import com.hisun.PN.OTHOL_KEY;

public class BPVOTHOL {
    OTHOL_KEY KEY = new OTHOL_KEY();
    OTHOL_HOL_DATA[] HOL_DATA = new OTHOL_HOL_DATA[50];
    public BPVOTHOL() {
        for (int i=0;i<50;i++) HOL_DATA[i] = new OTHOL_HOL_DATA();
    }
}
