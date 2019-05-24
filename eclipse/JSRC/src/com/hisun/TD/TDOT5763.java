package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5763 {
    String JIBS_tmp_str[] = new String[10];
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
    TDCLML TDCLML = new TDCLML();
    SCCGWA SCCGWA;
    TDB5763_AWA_5763 TDB5763_AWA_5763;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5763 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5763_AWA_5763>");
        TDB5763_AWA_5763 = (TDB5763_AWA_5763) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, TDCLML);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        S000_CALL_TDZLML();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5763_AWA_5763.PROD_CD);
        CEP.TRC(SCCGWA, TDB5763_AWA_5763.ACTI_NO);
        CEP.TRC(SCCGWA, TDB5763_AWA_5763.BR);
        CEP.TRC(SCCGWA, TDB5763_AWA_5763.CHNL_NO);
        if (TDB5763_AWA_5763.PROD_CD.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROD_CD_IN_ERR);
        }
        if (TDB5763_AWA_5763.ACTI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ACTI_NO_IN_ERR);
        }
        if (TDB5763_AWA_5763.BR == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_INPUT_ERR);
        }
    }
    public void S000_CALL_TDZLML() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCLML);
        TDCLML.FUNC = TDB5763_AWA_5763.FUNC;
        TDCLML.PROD_CD = TDB5763_AWA_5763.PROD_CD;
        TDCLML.ACTI_NO = TDB5763_AWA_5763.ACTI_NO;
        TDCLML.BR = TDB5763_AWA_5763.BR;
        TDCLML.CHNL_NO = TDB5763_AWA_5763.CHNL_NO;
        IBS.CALLCPN(SCCGWA, "TD-Z-LML", TDCLML);
        if (TDCLML.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCLML.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
