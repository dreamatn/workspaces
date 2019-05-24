package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZIQHLD {
    int JIBS_tmp_int;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    double WS_CURR_BAL = 0;
    double WS_AVL_BAL = 0;
    double WS_DEP_INT = 0;
    double WS_NOT_INT_BAL = 0;
    double WS_AVA_BAL_TOT = 0;
    short WS_V_CNT = 0;
    double WS_VTD_BAL = 0;
    double WS_OD_ADP_BAL = 0;
    char WS_DDTCCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    DDCIQHLD DDCIQHLD;
    public void MP(SCCGWA SCCGWA, DDCIQHLD DDCIQHLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIQHLD = DDCIQHLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIQHLD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DDCIQHLD.RC.RC_MMO = "DD";
        DDCIQHLD.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_ACO_AC();
        if (pgmRtn) return;
        B020_GET_HOLD_BAL();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCIQHLD.DATA.CUS_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDCIQHLD.DATA.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDCIQHLD.DATA.CCY_TYPE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT);
        }
    }
    public void B020_GET_ACO_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCIQHLD.DATA.CUS_AC;
        CICQACAC.DATA.CCY_ACAC = DDCIQHLD.DATA.CCY;
        CICQACAC.DATA.CR_FLG = DDCIQHLD.DATA.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
    }
    public void B020_GET_HOLD_BAL() throws IOException,SQLException,Exception {
        if (DDRCCY.HOLD_BAL > 0) {
            DDCIQHLD.DATA.HOLD_BAL = DDRCCY.HOLD_BAL;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            if (DDRCCY.HOLD_BAL < DDRCCY.CURR_BAL) {
                DDCIQHLD.DATA.HOLD_BAL = DDRCCY.CURR_BAL;
            } else {
                DDCIQHLD.DATA.HOLD_BAL = DDRCCY.HOLD_BAL;
            }
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DDCIQHLD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DDCIQHLD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
