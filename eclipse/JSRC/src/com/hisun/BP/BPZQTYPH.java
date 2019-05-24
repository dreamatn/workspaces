package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQTYPH {
    String CPN_P_INQ_CAL_CD = "BP-P-INQ-CAL-CODE   ";
    String CPN_P_CHK_WORK_DAY = "BP-P-CHK-WORK-DAY   ";
    String CPN_P_CAL_WORK_DAY = "BP-P-CAL-WORK-DAY   ";
    String CPN_R_MAINT_DAYE = "BP-R-MAINT-DAYE     ";
    String WS_ERR_MSG = " ";
    int WS_CNT_A = 0;
    int WS_CNT_B = 0;
    short WS_RC_CODE = 0;
    int WS_DATE = 0;
    char WS_PREV_DATE_FLG = 'N';
    char WS_NEXT_DATE_FLG = 'N';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCTDAYE BPCTDAYE = new BPCTDAYE();
    BPRDAYE BPRDAYE = new BPRDAYE();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    SCCGWA SCCGWA;
    BPCQTYPH BPCQTYPH;
    public void MP(SCCGWA SCCGWA, BPCQTYPH BPCQTYPH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQTYPH = BPCQTYPH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZQTYPH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQTYPH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_INQUIRE_TYPH_STS();
        if (BPCQTYPH.FUNC == '2') {
            B030_CAL_PREV_AND_NEXT_DATE();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCQTYPH.FUNC != '1' 
            && BPCQTYPH.FUNC != '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCQTYPH.DATE == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_DATE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_INQUIRE_TYPH_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQCAL);
        BPCOQCAL.FUNC = '7';
        BPCOQCAL.BK = SCCGWA.COMM_AREA.TR_BANK;
        CEP.TRC(SCCGWA, BPCOQCAL.BK);
        S000_CALL_BPZPQCAL();
        CEP.TRC(SCCGWA, BPCOQCAL);
        if (BPCOQCAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQCAL.RC);
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCOQCAL.CAL_CODE);
        IBS.init(SCCGWA, BPCOCKWD);
        BPCOCKWD.DAYE_FLG = 'Y';
        BPCOCKWD.CAL_CODE = BPCOQCAL.CAL_CODE;
        BPCOCKWD.DATE = BPCQTYPH.DATE;
        S000_CALL_BPZPCKWD();
        CEP.TRC(SCCGWA, BPCOCKWD);
        if (BPCOCKWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCKWD.RC);
            S000_ERR_MSG_PROC();
        }
        if (BPCOCKWD.SPD_DAY[1-1] != 1) {
            BPCQTYPH.DATE_CHA = "H";
        }
        if (BPCOCKWD.SPD_DAY[1-1] == 1) {
            if (BPCOCKWD.DAY_CHAR.trim().length() == 0) {
                BPCQTYPH.DATE_CHA = "W";
            } else {
                BPCQTYPH.DATE_CHA = BPCOCKWD.DAY_CHAR;
            }
        }
        B020_01_GET_OTH_FLAG();
        BPCQTYPH.DATE_TYPE = BPRDAYE.DATE_TYPE;
        BPCQTYPH.EXCH_FLG = BPRDAYE.EXCH_FLG;
    }
    public void B030_CAL_PREV_AND_NEXT_DATE() throws IOException,SQLException,Exception {
        WS_DATE = BPCQTYPH.DATE;
        do {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
            BPCOCLWD.DATE1 = WS_DATE;
            BPCOCLWD.DAYE_FLG = 'Y';
            BPCOCLWD.WDAYS = -2;
            S000_CALL_BPZPCLWD();
            B020_01_GET_OTH_FLAG();
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2_FLG);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2_CHAR);
            if ((BPCOCLWD.DATE2_FLG == 'W' 
                || BPCOCLWD.DATE2_FLG == 'S') 
                && !BPCOCLWD.DATE2_CHAR.equalsIgnoreCase("1")) {
                WS_PREV_DATE_FLG = 'Y';
                BPCQTYPH.PREV_DATE = BPCOCLWD.DATE2;
            }
            CEP.TRC(SCCGWA, BPCQTYPH.PREV_DATE);
            CEP.TRC(SCCGWA, BPCOCLWD);
            WS_DATE = BPCOCLWD.DATE2;
        } while (WS_PREV_DATE_FLG != 'Y');
        WS_DATE = BPCQTYPH.DATE;
        do {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = BPCOQCAL.CAL_CODE;
            BPCOCLWD.DATE1 = WS_DATE;
            BPCOCLWD.DAYE_FLG = 'Y';
            BPCOCLWD.WDAYS = 2;
            S000_CALL_BPZPCLWD();
            B020_01_GET_OTH_FLAG();
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2_FLG);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2_CHAR);
            if ((BPCOCLWD.DATE2_FLG == 'W' 
                || BPCOCLWD.DATE2_FLG == 'S') 
                && !BPCOCLWD.DATE2_CHAR.equalsIgnoreCase("1")) {
                WS_NEXT_DATE_FLG = 'Y';
                BPCQTYPH.NEXT_DATE = BPCOCLWD.DATE2;
            }
            WS_DATE = BPCOCLWD.DATE2;
            CEP.TRC(SCCGWA, BPCQTYPH.NEXT_DATE);
            CEP.TRC(SCCGWA, BPCOCLWD);
        } while (WS_NEXT_DATE_FLG != 'Y');
    }
    public void B020_01_GET_OTH_FLAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTDAYE);
        IBS.init(SCCGWA, BPRDAYE);
        BPCTDAYE.INFO.POINTER = BPRDAYE;
        BPCTDAYE.INFO.FUNC = 'Q';
        BPRDAYE.KEY.TYPE = 'I';
        BPRDAYE.KEY.DATE = BPCQTYPH.DATE;
        IBS.init(SCCGWA, BPCPQBCH);
        BPCPQBCH.ACCT = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQBCH();
        BPRDAYE.KEY.BCH = BPCPQBCH.BCH;
        S000_CALL_BPZTDAYE();
    }
    public void S000_CALL_BPZTDAYE() throws IOException,SQLException,Exception {
    }
    IBS.CALLCPN(SCCGWA, CPN_R_MAINT_DAYE, BPCTDAYE);
    public void S000_CALL_BPZPQCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_CAL_CD, BPCOQCAL);
    }
    public void S000_CALL_BPZPCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CHK_WORK_DAY, BPCOCKWD);
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CAL_WORK_DAY, BPCOCLWD);
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCQTYPH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCQTYPH = ");
            CEP.TRC(SCCGWA, BPCQTYPH);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
