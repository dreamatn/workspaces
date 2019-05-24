package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT3010 {
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    LNOT3010_WS_MSGID WS_MSGID = new LNOT3010_WS_MSGID();
    LNOT3010_WS_OUT_DATA WS_OUT_DATA = new LNOT3010_WS_OUT_DATA();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSPMHQ LNCSPMHQ = new LNCSPMHQ();
    SCCGWA SCCGWA;
    LNB3010_AWA_3010 LNB3010_AWA_3010;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT3010 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB3010_AWA_3010>");
        LNB3010_AWA_3010 = (LNB3010_AWA_3010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_CALL_LNZSPMHQ();
        B300_OUTPUT_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB3010_AWA_3010.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_CALL_LNZSPMHQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPMHQ);
        LNCSPMHQ.COMM_DATA.CTA_NO = LNB3010_AWA_3010.CTA_NO;
        S000_CALL_LNZSPMHQ();
        if (LNCSPMHQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSPMHQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_OUT_CCY_TYP = LNCSPMHQ.COMM_DATA.CCY_TYP;
        WS_OUT_DATA.WS_OUT_CTA_NO = LNCSPMHQ.COMM_DATA.CTA_NO;
        WS_OUT_DATA.WS_OUT_CI_NO = LNCSPMHQ.COMM_DATA.CI_NO;
        WS_OUT_DATA.WS_OUT_CI_CNM = LNCSPMHQ.COMM_DATA.CI_CNM;
        WS_OUT_DATA.WS_OUT_PRD_TYP = LNCSPMHQ.COMM_DATA.PRD_TYP;
        WS_OUT_DATA.WS_OUT_CCY = LNCSPMHQ.COMM_DATA.CCY;
        WS_OUT_DATA.WS_OUT_CCY_TYP = LNCSPMHQ.COMM_DATA.CCY_TYP;
        WS_OUT_DATA.WS_OUT_PRIN_AMT = LNCSPMHQ.COMM_DATA.PRIN_AMT;
        WS_OUT_DATA.WS_OUT_OS_BAL = LNCSPMHQ.COMM_DATA.OS_BAL;
        WS_OUT_DATA.WS_OUT_START_DT = LNCSPMHQ.COMM_DATA.START_DT;
        WS_OUT_DATA.WS_OUT_MATU_DT = LNCSPMHQ.COMM_DATA.MATU_DT;
        WS_OUT_DATA.WS_OUT_C_RP_M = LNCSPMHQ.COMM_DATA.C_RP_M;
        WS_OUT_DATA.WS_OUT_TOT_TERM = LNCSPMHQ.COMM_DATA.TOT_TERM;
        WS_OUT_DATA.WS_OUT_IN_MTH = LNCSPMHQ.COMM_DATA.IN_MTH;
        WS_OUT_DATA.WS_OUT_PRAL_PERD = LNCSPMHQ.COMM_DATA.PRAL_PERD;
        WS_OUT_DATA.WS_OUT_P_PERD = LNCSPMHQ.COMM_DATA.P_PERD;
        WS_OUT_DATA.WS_OUT_P_UNIT = LNCSPMHQ.COMM_DATA.P_UNIT;
        WS_OUT_DATA.WS_OUT_INTER_PERD = LNCSPMHQ.COMM_DATA.INTER_PERD;
        WS_OUT_DATA.WS_OUT_I_PERD = LNCSPMHQ.COMM_DATA.I_PERD;
        WS_OUT_DATA.WS_OUT_I_UNIT = LNCSPMHQ.COMM_DATA.I_UNIT;
        WS_OUT_DATA.WS_OUT_C_TYP = LNCSPMHQ.COMM_DATA.C_TYP;
        WS_OUT_DATA.WS_OUT_C_DAY = LNCSPMHQ.COMM_DATA.C_DAY;
        WS_OUT_DATA.WS_OUT_C_PERD = LNCSPMHQ.COMM_DATA.C_PERD;
        WS_OUT_DATA.WS_OUT_INT_ED = LNCSPMHQ.COMM_DATA.INT_ED;
        WS_OUT_DATA.WS_OUT_B_PERD = LNCSPMHQ.COMM_DATA.B_PERD;
        WS_OUT_DATA.WS_OUT_CAL_PERD = LNCSPMHQ.COMM_DATA.CAL_PERD;
        WS_OUT_DATA.WS_OUT_CAL_UNIT = LNCSPMHQ.COMM_DATA.CAL_UNIT;
        SCCFMT.FMTID = "LN301";
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 404;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_LNZSPMHQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-PAYMTH-INQ", LNCSPMHQ);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
