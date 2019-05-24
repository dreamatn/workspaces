package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7232 {
    String JIBS_tmp_str[] = new String[10];
    String K_CPN_LN_S_DARL_MAIN = "LN-S-DARL-MAIN";
    String CPN_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    LNOT7232_WS_ERR_MSG WS_ERR_MSG = new LNOT7232_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCSDARL LNCSDARL = new LNCSDARL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    LNB7230_AWA_7230 LNB7230_AWA_7230;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT7232 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7230_AWA_7230>");
        LNB7230_AWA_7230 = (LNB7230_AWA_7230) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_SET_NXT_TXN();
        B200_UPD_PTR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB7230_AWA_7230.CONT_NO.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB7230_AWA_7230.CONT_NO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_UPD_PTR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSDARL);
        LNCSDARL.REC_DATA.CONTRACT_NO = LNB7230_AWA_7230.CONT_NO;
        LNCSDARL.FUNC = '3';
        S000_CALL_LNZSDARL();
    }
    public void B200_SET_NXT_TXN() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = LNB7230_AWA_7230.FUNC;
        CEP.TRC(SCCGWA, WS_FUNC_FLG);
        if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 13;
            SCCSUBS.TR_CODE = 7233;
            S020_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 13;
            SCCSUBS.TR_CODE = 7236;
            S020_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 13;
            SCCSUBS.TR_CODE = 7234;
            S020_SET_SUBS_TRN();
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB7230_AWA_7230.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S020_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = LNB7230_AWA_7230.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_LNZSDARL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CPN_LN_S_DARL_MAIN, LNCSDARL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
