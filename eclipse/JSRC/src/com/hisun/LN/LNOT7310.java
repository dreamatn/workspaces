package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7310 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    LNOT7310_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT7310_WS_TEMP_VARIABLE();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSDRW LNCSDRW = new LNCSDRW();
    SCCGWA SCCGWA;
    LNB7310_AWA_7310 LNB7310_AWA_7310;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT7310 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7310_AWA_7310>");
        LNB7310_AWA_7310 = (LNB7310_AWA_7310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, LNB7310_AWA_7310.LOAN_AC);
        CEP.TRC(SCCGWA, LNB7310_AWA_7310.VAL_DTE);
        CEP.TRC(SCCGWA, LNB7310_AWA_7310.ACCOUNT[1-1].CCY);
        CEP.TRC(SCCGWA, LNB7310_AWA_7310.DRAW_AMT);
        CEP.TRC(SCCGWA, LNB7310_AWA_7310.TRANSFER[1-1].TRAN_AC);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_OUTPUT_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        R00_CHECK_ERROR();
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSDRW);
        LNCSDRW.COMM_DATA.LN_AC = LNB7310_AWA_7310.LOAN_AC;
        LNCSDRW.COMM_DATA.SUF_NO = "" + LNB7310_AWA_7310.SUFFIX;
        JIBS_tmp_int = LNCSDRW.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCSDRW.COMM_DATA.SUF_NO = "0" + LNCSDRW.COMM_DATA.SUF_NO;
        LNCSDRW.COMM_DATA.CCY = LNB7310_AWA_7310.ACCOUNT[1-1].CCY;
        LNCSDRW.COMM_DATA.DRAW_AMT = LNB7310_AWA_7310.DRAW_AMT;
        LNCSDRW.COMM_DATA.VAL_DT = LNB7310_AWA_7310.VAL_DTE;
        LNCSDRW.COMM_DATA.TRAN_AC = LNB7310_AWA_7310.TRANSFER[1-1].TRAN_AC;
        S000_CALL_LNZSDRW();
        if (LNCSDRW.COMM_DATA.SUF_NO.trim().length() == 0) LNB7310_AWA_7310.SUFFIX = 0;
        else LNB7310_AWA_7310.SUFFIX = Integer.parseInt(LNCSDRW.COMM_DATA.SUF_NO);
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZSDRW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-DRAWDOWN", LNCSDRW);
        if (LNCSDRW.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = LNCSDRW.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = LNCSDRW.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
