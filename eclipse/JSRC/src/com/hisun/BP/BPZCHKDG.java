package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCHKDG {
    BPZCHKDG_WS_VARS WS_VARS = new BPZCHKDG_WS_VARS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCCHKDG BPCCHKDG;
    BPZCHKDG_WL1 WL1;
    public void MP(SCCGWA SCCGWA, BPCCHKDG BPCCHKDG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCHKDG = BPCCHKDG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZCHKDG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_VARS);
        WS_VARS.PTR = BPCCHKDG.DIGITS;
        WL1 = WS_VARS.PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCCHKDG.TYPE == '1') {
            B100_CHK_AC();
        } else if (BPCCHKDG.TYPE == '2') {
            B200_CHK_SUB_AC_ID();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TYPE(" + BPCCHKDG.TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHK_AC() throws IOException,SQLException,Exception {
        WS_VARS.LUHN_LEN = 19;
        R000_LUHN();
    }
    public void B200_CHK_SUB_AC_ID() throws IOException,SQLException,Exception {
        WS_VARS.LUHN_LEN = 20;
        R000_LUHN();
    }
    public void R000_LUHN() throws IOException,SQLException,Exception {
        WS_VARS.CHK_DIGIT = 0;
        WS_VARS.I = WS_VARS.LUHN_LEN;
        WS_VARS.DOUBLE_FLG = 'Y';
        while (WS_VARS.I != 0) {
            WS_VARS.I -= 1;
            WS_VARS.DIGIT = WL1.DIGIT[WS_VARS.I-1];
            if (WS_VARS.DOUBLE_FLG == 'Y') {
                WS_VARS.DIGIT = (short) (WS_VARS.DIGIT * 2);
                if (WS_VARS.DIGIT > 9) {
                    WS_VARS.DIGIT -= 9;
                }
                WS_VARS.DOUBLE_FLG = ' ';
            } else {
                WS_VARS.DOUBLE_FLG = 'Y';
            }
            WS_VARS.CHK_DIGIT += WS_VARS.DIGIT;
            if (WS_VARS.CHK_DIGIT > 9) {
                WS_VARS.CHK_DIGIT -= 10;
            }
        }
        WL1.DIGIT[WS_VARS.LUHN_LEN-1] = WS_VARS.CHK_DIGIT;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
