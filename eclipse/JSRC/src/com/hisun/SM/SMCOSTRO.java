package com.hisun.SM;

import java.util.ArrayList;
import java.util.List;


public class SMCOSTRO {
    public char FUNC = ' ';
    public String NAME = " ";
    public String ALIAS = " ";
    public String EN_NAME = " ";
    public String CH_NAME = " ";
    public char FILLER6 = 0X02;
    public String TYPE = " ";
    public String UPD_TLR = " ";
    public int UPD_DATE = 0;
    public int ITM_CNT = 0;
    public List<SMCOSTRO_ITM_TEXT> ITM_TEXT = new ArrayList<SMCOSTRO_ITM_TEXT>();
}
