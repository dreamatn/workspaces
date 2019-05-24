package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7810 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_ERROR = 'E';
    LNOT7810_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT7810_WS_TEMP_VARIABLE();
    int WS_I = 0;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCPSHM LNCPSHM = new LNCPSHM();
    SCCGWA SCCGWA;
    LNB7810_AWA_7810 LNB7810_AWA_7810;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT7810 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7810_AWA_7810>");
        LNB7810_AWA_7810 = (LNB7810_AWA_7810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.AC);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.SUFFIX);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.TX_TYP);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.PAY_TYP);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.START_TE);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.STRIN_TE);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.TOTAL_TE);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.TERMINFO[1-1].TERMNO);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.TERMINFO[1-1].TERM_DTE);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.TERMINFO[1-1].TERM_AMT);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.TERMINFO[2-1].TERMNO);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.TERMINFO[2-1].TERM_DTE);
        CEP.TRC(SCCGWA, LNB7810_AWA_7810.TERMINFO[2-1].TERM_AMT);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PSHM_PROCESS();
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
    public void B200_MAIN_PSHM_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPSHM);
        LNCPSHM.COMM_DATA.LN_AC = LNB7810_AWA_7810.AC;
        LNCPSHM.COMM_DATA.SUF_NO = "" + LNB7810_AWA_7810.SUFFIX;
        JIBS_tmp_int = LNCPSHM.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCPSHM.COMM_DATA.SUF_NO = "0" + LNCPSHM.COMM_DATA.SUF_NO;
        LNCPSHM.COMM_DATA.TX_TYP = LNB7810_AWA_7810.TX_TYP;
        LNCPSHM.COMM_DATA.PAY_TYP = LNB7810_AWA_7810.PAY_TYP;
        LNCPSHM.COMM_DATA.START_TE = LNB7810_AWA_7810.START_TE;
        LNCPSHM.COMM_DATA.STRIN_TE = LNB7810_AWA_7810.STRIN_TE;
        LNCPSHM.COMM_DATA.TOTAL_TE = LNB7810_AWA_7810.TOTAL_TE;
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERMNO = LNB7810_AWA_7810.TERMINFO[WS_I-1].TERMNO;
            LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_DTE = LNB7810_AWA_7810.TERMINFO[WS_I-1].TERM_DTE;
            LNCPSHM.COMM_DATA.TERMINFO[WS_I-1].TERM_AMT = LNB7810_AWA_7810.TERMINFO[WS_I-1].TERM_AMT;
        }
        S000_CALL_LNZPSHM();
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZPSHM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PSCH-MAINT", LNCPSHM);
        if (LNCPSHM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = LNCPSHM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = LNCPSHM.RC.RC_RTNCODE;
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
