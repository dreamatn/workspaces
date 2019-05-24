package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2814 {
    String JIBS_tmp_str[] = new String[10];
    LNOT2814_WS_ERR_MSG WS_ERR_MSG = new LNOT2814_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    char WS_FOUND_FLG = ' ';
    char WS_ERR_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNCSPLST LNCSPLST = new LNCSPLST();
    SCCGWA SCCGWA;
    LNB2810_AWA_2810 LNB2810_AWA_2810;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT2814 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB2810_AWA_2810>");
        LNB2810_AWA_2810 = (LNB2810_AWA_2810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, LNCSPLST);
        LNCSPLST.RC.RC_MMO = "LN";
        LNCSPLST.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_MAIN_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        WS_ERR_FLG = 'N';
        if (LNB2810_AWA_2810.CONT_NO.trim().length() == 0) {
            WS_ERR_FLG = 'Y';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB2810_AWA_2810.CONT_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (LNB2810_AWA_2810.TSK_DT == 0) {
            WS_ERR_FLG = 'Y';
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB2810_AWA_2810.TSK_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_ERR_FLG == 'Y') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        B210_INTPUT_PLST();
    }
    public void B210_INTPUT_PLST() throws IOException,SQLException,Exception {
        LNCSPLST.COMM_DATA.CONT_NO = LNB2810_AWA_2810.CONT_NO;
        LNCSPLST.COMM_DATA.TSK_DT = LNB2810_AWA_2810.TSK_DT;
        LNCSPLST.FUNC = 'D';
        S000_CALL_LNCSPLST();
    }
    public void S000_CALL_LNCSPLST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-PLST-MAIN", LNCSPLST);
        if (LNCSPLST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSPLST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
