package com.hisun.TD;

import java.util.ArrayList;
import java.util.List;


public class TDCQIRPD {
    public String PROD = " ";
    public String CCY = " ";
    public double PRD_RAT = 0;
    public char FILLER4 = 0X01;
    public int BR = 0;
    public String RUL_CD = " ";
    public String CDESC = " ";
    public char FILLER8 = 0X02;
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public char TIER_TYPE = ' ';
    public char TIER_TYPE2 = ' ';
    public char SPRD_TYPE = ' ';
    public int CNT = 0;
    public List<TDCQIRPD_SPRD_DATA3> SPRD_DATA3 = new ArrayList<TDCQIRPD_SPRD_DATA3>();
}
