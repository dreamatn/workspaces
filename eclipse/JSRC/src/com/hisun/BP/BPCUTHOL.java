package com.hisun.BP;

import com.hisun.TD.UTHOL_HOL_DATA;
import com.hisun.TD.UTHOL_KEY;
import com.hisun.TD.UTHOL_RC;

public class BPCUTHOL {
    UTHOL_RC RC = new UTHOL_RC();
    char FUNC = ' ';
    char FUNC_OPT = ' ';
    UTHOL_KEY KEY = new UTHOL_KEY();
    int DATE = 0;
    UTHOL_HOL_DATA[] HOL_DATA = new UTHOL_HOL_DATA[50];
    int LAST_DATE = 0;
    String LAST_TELLER = " ";
    String TS = " ";
    public BPCUTHOL() {
        for (int i=0;i<50;i++) HOL_DATA[i] = new UTHOL_HOL_DATA();
    }
}
