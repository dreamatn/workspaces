package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIMCCY {
    DBParm DDTCCY_RD;
    brParm DDTCCY_BR = new brParm();
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    char WS_CCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    DDCIMCCY DDCIMCCY;
    public void MP(SCCGWA SCCGWA, DDCIMCCY DDCIMCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIMCCY = DDCIMCCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIMCCY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].AC);
        CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY);
        CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY_TYPE);
        B005_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B010_GET_ACAC_INFO();
        if (pgmRtn) return;
        B010_QUERY_RECORD_PROC();
        if (pgmRtn) return;
    }
    public void B005_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCIMCCY.DATA[1-1].AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCIMCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIMCCY.DATA[1-1].CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCIMCCY.DATA[1-1].CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_GET_ACAC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "YYC");
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = DDCIMCCY.DATA[1-1].AC;
        CICQACAC.DATA.CCY_ACAC = DDCIMCCY.DATA[1-1].CCY;
        CICQACAC.DATA.CR_FLG = DDCIMCCY.DATA[1-1].CCY_TYPE;
        CICQACAC.FUNC = 'C';
        CEP.TRC(SCCGWA, "YYC");
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B010_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCCY.CCY = DDCIMCCY.DATA[1-1].CCY;
        DDRCCY.CCY_TYPE = DDCIMCCY.DATA[1-1].CCY_TYPE;
        CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY);
        CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY_TYPE);
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        if (DDRCCY.KEY.AC.trim().length() > 0 
            && DDRCCY.CCY.trim().length() > 0 
            && DDRCCY.CCY_TYPE != ' ') {
            CEP.TRC(SCCGWA, "TEST");
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.init(SCCGWA, DDCIMCCY);
                DDCIMCCY.DATA[1-1].AC = DDRCCY.KEY.AC;
                DDCIMCCY.DATA[1-1].CCY = DDRCCY.CCY;
                DDCIMCCY.DATA[1-1].CCY_TYPE = DDRCCY.CCY_TYPE;
                DDCIMCCY.DATA[1-1].STS = DDRCCY.STS;
                DDCIMCCY.DATA[1-1].OPEN_DATE = DDRCCY.OPEN_DATE;
                DDCIMCCY.DATA[1-1].LAST_DATE = DDRCCY.LAST_DATE;
                DDCIMCCY.DATA[1-1].OPEN_DP = (short) DDRCCY.OPEN_DP;
                DDCIMCCY.DATA[1-1].OPEN_TLR = DDRCCY.OPEN_TLR;
                DDCIMCCY.DATA[1-1].CURR_BAL = DDRCCY.CURR_BAL;
                DDCIMCCY.DATA[1-1].HOLD_BAL = DDRCCY.HOLD_BAL;
                DDCIMCCY.DATA[1-1].NOT_INT_BAL = DDRCCY.NOT_INT_BAL;
                DDCIMCCY.DATA[1-1].CCAL_TOT_BAL = DDRCCY.CCAL_TOT_BAL;
                DDCIMCCY.DATA[1-1].MARGIN_BAL = DDRCCY.MARGIN_BAL;
                DDCIMCCY.DATA[1-1].CLOSE_BAL = DDRCCY.CLOSE_BAL;
                DDCIMCCY.DATA[1-1].LAST_BAL = DDRCCY.LAST_BAL;
                DDCIMCCY.DATA[1-1].CLOSE_BAL = (double)DDRCCY.LAST_BAL_DATE;
                DDCIMCCY.DATA[1-1].LAST_YEAR_BAL = DDRCCY.LAST_YEAR_BAL;
                DDCIMCCY.DATA[1-1].LAST_POST_DATE = DDRCCY.LAST_POST_DATE;
                DDCIMCCY.DATA[1-1].DEP_CAMT = DDRCCY.DEP_CAMT;
                DDCIMCCY.DATA[1-1].DRW_CAMT = DDRCCY.DRW_CAMT;
                DDCIMCCY.DATA[1-1].DEP_TAMT = DDRCCY.DEP_TAMT;
                DDCIMCCY.DATA[1-1].DRW_TAMT = DDRCCY.DRW_TAMT;
                DDCIMCCY.DATA[1-1].LAST_DEP_CAMT = DDRCCY.LAST_DEP_CAMT;
                DDCIMCCY.DATA[1-1].LAST_DRW_CAMT = DDRCCY.LAST_DRW_CAMT;
                DDCIMCCY.DATA[1-1].LAST_DEP_TAMT = DDRCCY.LAST_DEP_TAMT;
                DDCIMCCY.DATA[1-1].LAST_DRW_TAMT = DDRCCY.LAST_DRW_TAMT;
                DDCIMCCY.DATA[1-1].LAST_DAYEND_BAL = DDRCCY.LAST_DAYEND_BAL;
                DDCIMCCY.DATA[1-1].LST_DAY_YEAR_BAL = DDRCCY.LST_DAY_YEAR_BAL;
                CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].AC);
                CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY);
                CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY_TYPE);
            }
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND, DDCIMCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_BR.rp = new DBParm();
        DDTCCY_BR.rp.TableName = "DDTCCY";
        IBS.STARTBR(SCCGWA, DDRCCY, DDTCCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND, DDCIMCCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DDTCCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCY, this, DDTCCY_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_FLG = 'Y';
        } else {
            WS_CCY_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCY_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIMCCY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DDCIMCCY=");
            CEP.TRC(SCCGWA, DDCIMCCY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
