package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRDAMT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_AMT1 = 0;
    double WS_AMT2 = 0;
    double WS_RESULT_AMT = 0;
    long WS_QUOTIENT = 0;
    double WS_REMAINDER = 0;
    long WS_QUOTIENT1 = 0;
    long WS_QUOTIENT2 = 0;
    long WS_QUOTIENT3 = 0;
    double WS_REMAINDER1 = 0;
    double WS_REMAINDER2 = 0;
    double WS_REMAINDER3 = 0;
    double WS_REMAINDER4 = 0;
    double WS_REMAINDER_TMP = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRCCY BPCRCCY = new BPCRCCY();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    BPCRDAMT BPCRDAMT;
    public void MP(SCCGWA SCCGWA, BPCRDAMT BPCRDAMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRDAMT = BPCRDAMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRDAMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCCY);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRDAMT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INPUT DATA:");
        CEP.TRC(SCCGWA, BPCRDAMT.CCY);
        CEP.TRC(SCCGWA, BPCRDAMT.AMT);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPCRDAMT.CCY;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCQCCY.DATA.RND_MTH);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        if (BPCQCCY.DATA.RND_MTH == 0) {
            B020_CUT_PROCESS();
            if (pgmRtn) return;
        } else if (BPCQCCY.DATA.RND_MTH == 1) {
            B030_CARRY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCQCCY.DATA.RND_MTH == 2) {
            B040_ROUND_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "WRONG");
        }
        CEP.TRC(SCCGWA, "OUTPUT DATA:");
        CEP.TRC(SCCGWA, BPCRDAMT.RESULT_AMT);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCRDAMT.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NEED_CCY_OR_CCYCD, BPCRDAMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRDAMT.AMT == 0) {
            CEP.TRC(SCCGWA, "OUT WITHOUT ANY PROCESSING");
            BPCRDAMT.RESULT_AMT = 0;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CUT_PROCESS() throws IOException,SQLException,Exception {
        if (BPCRDAMT.AMT < 0) {
            WS_AMT1 = BPCRDAMT.AMT * -1;
        } else {
            WS_AMT1 = BPCRDAMT.AMT;
        }
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        CEP.TRC(SCCGWA, WS_AMT1);
        if (BPCQCCY.DATA.DEC_MTH == 0) {
            WS_REMAINDER = WS_AMT1 % 1;
            WS_QUOTIENT = (long) ((WS_AMT1 - WS_REMAINDER) / 1);
            CEP.TRC(SCCGWA, "0000000000");
            CEP.TRC(SCCGWA, WS_QUOTIENT);
            CEP.TRC(SCCGWA, WS_REMAINDER);
            WS_RESULT_AMT = WS_AMT1 - WS_REMAINDER;
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else if (BPCQCCY.DATA.DEC_MTH == 1) {
            WS_REMAINDER4 = WS_AMT1 % 1;
            WS_QUOTIENT1 = (long) ((WS_AMT1 - WS_REMAINDER4) / 1);
            WS_REMAINDER1 = WS_REMAINDER4;
            CEP.TRC(SCCGWA, "1111111111");
            CEP.TRC(SCCGWA, WS_QUOTIENT1);
            CEP.TRC(SCCGWA, WS_REMAINDER4);
            CEP.TRC(SCCGWA, WS_REMAINDER1);
            WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER1;
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else if (BPCQCCY.DATA.DEC_MTH == 2) {
            WS_REMAINDER4 = WS_AMT1 % 1;
            WS_QUOTIENT1 = (long) ((WS_AMT1 - WS_REMAINDER4) / 1);
            WS_REMAINDER2 = WS_REMAINDER4;
            CEP.TRC(SCCGWA, "2222222222");
            CEP.TRC(SCCGWA, WS_QUOTIENT1);
            CEP.TRC(SCCGWA, WS_REMAINDER4);
            CEP.TRC(SCCGWA, WS_REMAINDER2);
            WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER2;
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else if (BPCQCCY.DATA.DEC_MTH == 3) {
            WS_REMAINDER4 = WS_AMT1 % 1;
            WS_QUOTIENT1 = (long) ((WS_AMT1 - WS_REMAINDER4) / 1);
            WS_REMAINDER3 = WS_REMAINDER4;
            CEP.TRC(SCCGWA, "3333333333");
            CEP.TRC(SCCGWA, WS_QUOTIENT1);
            CEP.TRC(SCCGWA, WS_REMAINDER4);
            CEP.TRC(SCCGWA, WS_REMAINDER3);
            WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER3;
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else {
            CEP.TRC(SCCGWA, "WRONG");
        }
        if (BPCRDAMT.AMT < 0) {
            CEP.TRC(SCCGWA, "-");
            BPCRDAMT.RESULT_AMT = WS_RESULT_AMT * -1;
        } else {
            CEP.TRC(SCCGWA, "+");
            BPCRDAMT.RESULT_AMT = WS_RESULT_AMT;
        }
    }
    public void B030_CARRY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCRDAMT.AMT < 0) {
            WS_AMT1 = BPCRDAMT.AMT * -1;
        } else {
            WS_AMT1 = BPCRDAMT.AMT;
        }
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        CEP.TRC(SCCGWA, WS_AMT1);
        if (BPCQCCY.DATA.DEC_MTH == 0) {
            WS_REMAINDER = WS_AMT1 % 1;
            WS_QUOTIENT = (long) ((WS_AMT1 - WS_REMAINDER) / 1);
            CEP.TRC(SCCGWA, "0000000000");
            CEP.TRC(SCCGWA, WS_QUOTIENT);
            CEP.TRC(SCCGWA, WS_REMAINDER);
            if (WS_REMAINDER > 0) {
                WS_RESULT_AMT = WS_AMT1 - WS_REMAINDER + 1;
            } else {
                WS_RESULT_AMT = WS_AMT1;
            }
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else if (BPCQCCY.DATA.DEC_MTH == 1) {
            WS_REMAINDER4 = WS_AMT1 % 1;
            WS_QUOTIENT1 = (long) ((WS_AMT1 - WS_REMAINDER4) / 1);
            WS_REMAINDER1 = WS_REMAINDER4;
            WS_REMAINDER_TMP = WS_REMAINDER4 - WS_REMAINDER1;
            CEP.TRC(SCCGWA, "1111111111");
            CEP.TRC(SCCGWA, WS_QUOTIENT1);
            CEP.TRC(SCCGWA, WS_REMAINDER4);
            CEP.TRC(SCCGWA, WS_REMAINDER1);
            CEP.TRC(SCCGWA, WS_REMAINDER_TMP);
            if (WS_REMAINDER_TMP > 0) {
                WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER1 + 0.1;
            } else {
                WS_RESULT_AMT = WS_AMT1;
            }
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else if (BPCQCCY.DATA.DEC_MTH == 2) {
            WS_REMAINDER4 = WS_AMT1 % 1;
            WS_QUOTIENT1 = (long) ((WS_AMT1 - WS_REMAINDER4) / 1);
            WS_REMAINDER2 = WS_REMAINDER4;
            WS_REMAINDER_TMP = WS_REMAINDER4 - WS_REMAINDER2;
            CEP.TRC(SCCGWA, "2222222222");
            CEP.TRC(SCCGWA, WS_QUOTIENT1);
            CEP.TRC(SCCGWA, WS_REMAINDER4);
            CEP.TRC(SCCGWA, WS_REMAINDER2);
            CEP.TRC(SCCGWA, WS_REMAINDER_TMP);
            if (WS_REMAINDER_TMP > 0) {
                WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER2 + 0.01;
            } else {
                WS_RESULT_AMT = WS_AMT1;
            }
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else if (BPCQCCY.DATA.DEC_MTH == 3) {
            WS_REMAINDER4 = WS_AMT1 % 1;
            WS_QUOTIENT1 = (long) ((WS_AMT1 - WS_REMAINDER4) / 1);
            WS_REMAINDER3 = WS_REMAINDER4;
            WS_REMAINDER_TMP = WS_REMAINDER4 - WS_REMAINDER3;
            CEP.TRC(SCCGWA, "3333333333");
            CEP.TRC(SCCGWA, WS_QUOTIENT1);
            CEP.TRC(SCCGWA, WS_REMAINDER4);
            CEP.TRC(SCCGWA, WS_REMAINDER3);
            CEP.TRC(SCCGWA, WS_REMAINDER_TMP);
            if (WS_REMAINDER_TMP > 0) {
                WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER3 + 0.001;
            } else {
                WS_RESULT_AMT = WS_AMT1;
            }
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else {
            CEP.TRC(SCCGWA, "WRONG");
        }
        if (BPCRDAMT.AMT < 0) {
            CEP.TRC(SCCGWA, "-");
            BPCRDAMT.RESULT_AMT = WS_RESULT_AMT * -1;
        } else {
            CEP.TRC(SCCGWA, "+");
            BPCRDAMT.RESULT_AMT = WS_RESULT_AMT;
        }
    }
    public void B040_ROUND_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        if (BPCRDAMT.AMT < 0) {
            WS_AMT1 = BPCRDAMT.AMT * -1;
        } else {
            WS_AMT1 = BPCRDAMT.AMT;
        }
        CEP.TRC(SCCGWA, WS_AMT1);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.DEC_MTH);
        if (BPCQCCY.DATA.DEC_MTH == 0) {
            WS_REMAINDER = WS_AMT1 % 1;
            WS_QUOTIENT = (long) ((WS_AMT1 - WS_REMAINDER) / 1);
            CEP.TRC(SCCGWA, "0000000000");
            CEP.TRC(SCCGWA, WS_QUOTIENT);
            CEP.TRC(SCCGWA, WS_REMAINDER);
            if (WS_REMAINDER < .5) {
                CEP.TRC(SCCGWA, "CUT");
                WS_RESULT_AMT = WS_AMT1 - WS_REMAINDER;
            } else {
                CEP.TRC(SCCGWA, "CARRY");
                WS_RESULT_AMT = WS_AMT1 - WS_REMAINDER + 1;
            }
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else if (BPCQCCY.DATA.DEC_MTH == 1) {
            WS_REMAINDER4 = WS_AMT1 % 1;
            WS_QUOTIENT1 = (long) ((WS_AMT1 - WS_REMAINDER4) / 1);
            WS_REMAINDER1 = WS_REMAINDER4;
            WS_REMAINDER_TMP = WS_REMAINDER4 - WS_REMAINDER1;
            CEP.TRC(SCCGWA, "1111111111");
            CEP.TRC(SCCGWA, WS_QUOTIENT1);
            CEP.TRC(SCCGWA, WS_REMAINDER4);
            CEP.TRC(SCCGWA, WS_REMAINDER1);
            CEP.TRC(SCCGWA, WS_REMAINDER_TMP);
            if (WS_REMAINDER_TMP < .05) {
                CEP.TRC(SCCGWA, "CUT");
                WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER1;
            } else {
                CEP.TRC(SCCGWA, "CARRY");
                WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER1 + 0.1;
            }
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else if (BPCQCCY.DATA.DEC_MTH == 2) {
            WS_REMAINDER4 = WS_AMT1 % 1;
            WS_QUOTIENT1 = (long) ((WS_AMT1 - WS_REMAINDER4) / 1);
            WS_REMAINDER2 = WS_REMAINDER4;
            WS_REMAINDER_TMP = WS_REMAINDER4 - WS_REMAINDER2;
            CEP.TRC(SCCGWA, "2222222222");
            CEP.TRC(SCCGWA, WS_QUOTIENT1);
            CEP.TRC(SCCGWA, WS_REMAINDER4);
            CEP.TRC(SCCGWA, WS_REMAINDER2);
            CEP.TRC(SCCGWA, WS_REMAINDER_TMP);
            if (WS_REMAINDER_TMP < .005) {
                CEP.TRC(SCCGWA, "CUT");
                WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER2;
            } else {
                CEP.TRC(SCCGWA, "CARRY");
                WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER2 + 0.01;
            }
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else if (BPCQCCY.DATA.DEC_MTH == 3) {
            WS_REMAINDER4 = WS_AMT1 % 1;
            WS_QUOTIENT1 = (long) ((WS_AMT1 - WS_REMAINDER4) / 1);
            WS_REMAINDER3 = WS_REMAINDER4;
            WS_REMAINDER_TMP = WS_REMAINDER4 - WS_REMAINDER3;
            CEP.TRC(SCCGWA, "3333333333");
            CEP.TRC(SCCGWA, WS_QUOTIENT1);
            CEP.TRC(SCCGWA, WS_REMAINDER4);
            CEP.TRC(SCCGWA, WS_REMAINDER3);
            CEP.TRC(SCCGWA, WS_REMAINDER_TMP);
            if (WS_REMAINDER_TMP < .0005) {
                CEP.TRC(SCCGWA, "CUT");
                WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER3;
            } else {
                CEP.TRC(SCCGWA, "CARRY");
                WS_RESULT_AMT = WS_QUOTIENT1 + WS_REMAINDER3 + 0.001;
            }
            CEP.TRC(SCCGWA, WS_AMT1);
            CEP.TRC(SCCGWA, WS_RESULT_AMT);
        } else {
            CEP.TRC(SCCGWA, "WRONG");
        }
        if (BPCRDAMT.AMT < 0) {
            CEP.TRC(SCCGWA, "-");
            BPCRDAMT.RESULT_AMT = WS_RESULT_AMT * -1;
        } else {
            CEP.TRC(SCCGWA, "+");
            BPCRDAMT.RESULT_AMT = WS_RESULT_AMT;
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRCCY.DATA.EFF_DT);
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        CEP.TRC(SCCGWA, BPCQCCY.RC);
        if (BPCQCCY.RC.RTNCODE == 0) {
            BPCRDAMT.RC.RC_CODE = 0;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCRDAMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRDAMT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRDAMT = ");
            CEP.TRC(SCCGWA, BPCRDAMT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
