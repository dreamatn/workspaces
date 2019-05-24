package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5770 {
    String WS_MSGID = " ";
    char WS_FUN_CI = ' ';
    char WS_FUN_CCY = ' ';
    char WS_FUN_TERM = ' ';
    short WS_ST = 0;
    short WS_EN = 0;
    String K_OUTPUT_FMT1 = "TD579";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCMACE TDCMACE = new TDCMACE();
    TDRCIIN TDRCIIN = new TDRCIIN();
    TDCTLML TDCTLML = new TDCTLML();
    SCCGWA SCCGWA;
    TDB5770_AWA_5770 TDB5770_AWA_5770;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5770 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5770_AWA_5770>");
        TDB5770_AWA_5770 = (TDB5770_AWA_5770) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        S000_CALL_TDZTLML();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB5770_AWA_5770.FUNC == ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DOCU_FUNC_ERR);
        }
        if (TDB5770_AWA_5770.PROD_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR);
        }
        if (TDB5770_AWA_5770.FR == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FR_ERR);
        }
        if (TDB5770_AWA_5770.DT == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DT_SPACE_ERR);
        }
        if (TDB5770_AWA_5770.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_CCY_I_ERR);
        }
        if (TDB5770_AWA_5770.FUNC == 'A' 
            && TDB5770_AWA_5770.TOT_BAL == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_MUST_INPUT);
        }
        if (TDB5770_AWA_5770.FUNC == 'M' 
            && TDB5770_AWA_5770.TOT_BAL == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BAL_MUST_INPUT);
        }
    }
    public void S000_CALL_TDZTLML() throws IOException,SQLException,Exception {
        TDCTLML.FUNC = TDB5770_AWA_5770.FUNC;
        TDCTLML.PROD_TYP = TDB5770_AWA_5770.PROD_TYP;
        TDCTLML.FR = TDB5770_AWA_5770.FR;
        TDCTLML.DT = TDB5770_AWA_5770.DT;
        TDCTLML.CCY = TDB5770_AWA_5770.CCY;
        TDCTLML.TOT_BAL = TDB5770_AWA_5770.TOT_BAL;
        TDCTLML.AGN_BAL = TDB5770_AWA_5770.AGN_BAL;
        CEP.TRC(SCCGWA, TDB5770_AWA_5770.TOT_BAL);
        IBS.CALLCPN(SCCGWA, "TD-PRO-TLML", TDCTLML);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
