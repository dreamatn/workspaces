package com.hisun.CI;

public class CICFCGRP {
    public CICFCGRP_KEY KEY = new CICFCGRP_KEY();
    public String NAME = " ";
    public String TYPE = " ";
    public int BR = 0;
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public char FLG = ' ';
    public CICFCGRP_MATCH_INFO[] MATCH_INFO = new CICFCGRP_MATCH_INFO[5];
    public CICFCGRP() {
        for (int i=0;i<5;i++) MATCH_INFO[i] = new CICFCGRP_MATCH_INFO();
    }
}
