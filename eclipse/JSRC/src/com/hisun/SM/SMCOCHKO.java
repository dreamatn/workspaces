package com.hisun.SM;

import java.util.ArrayList;
import java.util.List;


public class SMCOCHKO {
    public char FUNC = ' ';
    public String NAME = " ";
    public String FMTNO = " ";
    public char OPT = ' ';
    public String REMARK = " ";
    public char FILLER6 = 0X02;
    public String UPD_TLR = " ";
    public int UPD_DATE = 0;
    public int ITM_CNT = 0;
    public List<SMCOCHKO_ITM_TEXT> ITM_TEXT = new ArrayList<SMCOCHKO_ITM_TEXT>();
}
