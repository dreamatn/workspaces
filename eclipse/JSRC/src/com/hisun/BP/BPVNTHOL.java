package com.hisun.BP;

import com.hisun.PN.NTHOL_HOL_DATA;
import com.hisun.PN.NTHOL_KEY;

public class BPVNTHOL {
    NTHOL_KEY KEY = new NTHOL_KEY();
    NTHOL_HOL_DATA[] HOL_DATA = new NTHOL_HOL_DATA[50];
    public BPVNTHOL() {
        for (int i=0;i<50;i++) HOL_DATA[i] = new NTHOL_HOL_DATA();
    }
}
