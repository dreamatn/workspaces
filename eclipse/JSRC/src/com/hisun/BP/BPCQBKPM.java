package com.hisun.BP;

public class BPCQBKPM {
    public BPCQBKPM_RC RC = new BPCQBKPM_RC();
    public char FUNC = ' ';
    public BPCQBKPM_KEY KEY = new BPCQBKPM_KEY();
    public int CNT = 0;
    public BPCQBKPM_DATA[] DATA = new BPCQBKPM_DATA[10];
    public String COA_TYP = " ";
    public BPCQBKPM() {
        for (int i=0;i<10;i++) DATA[i] = new BPCQBKPM_DATA();
    }
}
