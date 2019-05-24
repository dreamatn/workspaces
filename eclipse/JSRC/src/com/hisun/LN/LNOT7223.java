package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT7223 {
    String JIBS_tmp_str[] = new String[10];
    char K_MODIFY = 'M';
    char K_ENTER = 'E';
    char K_INST_MTH_SAM = '1';
    char K_INST_MTH_SCP = '2';
    LNOT7223_WS_ERR_MSG WS_ERR_MSG = new LNOT7223_WS_ERR_MSG();
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCSTMPP LNCSTMPP = new LNCSTMPP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    LNCCLOAN LNCCLOAN = new LNCCLOAN();
    SCCGWA SCCGWA;
    LNB7220_AWA_7220 LNB7220_AWA_7220;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "LNOT7223 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB7220_AWA_7220>");
        LNB7220_AWA_7220 = (LNB7220_AWA_7220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, LNB7220_AWA_7220.GNST_AMT);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_UPD_PTR_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB7220_AWA_7220.LN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_ERR_MSG);
            WS_FLD_NO = LNB7220_AWA_7220.LN_AC_NO;
            S000_ERR_MSG_PROC();
        }
        if (LNB7220_AWA_7220.INST_MTH == K_INST_MTH_SAM 
            && LNB7220_AWA_7220.PRIN_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PRINCIPAL_M_INPUT, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        if (LNB7220_AWA_7220.INST_MTH != K_INST_MTH_SAM 
            && LNB7220_AWA_7220.INST_MTH != K_INST_MTH_SCP) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INST_FORM_CODE, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, LNCCLOAN);
        LNCCLOAN.COMM_DATA.LN_AC = LNB7220_AWA_7220.LN_AC;
        LNCCLOAN.COMM_DATA.CHECK_TYP = K_MODIFY;
        S000_CALL_FUN_LNZCLOAN();
    }
    public void B200_UPD_PTR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSTMPP);
        LNCSTMPP.REC_DATA.KEY.TRAN_SEQ = LNB7220_AWA_7220.TRAN_SEQ;
        LNCSTMPP.REC_DATA.KEY.LN_AC = LNB7220_AWA_7220.LN_AC;
        LNCSTMPP.REC_DATA.KEY.PHS_NO = LNB7220_AWA_7220.PHS_NO;
        LNCSTMPP.REC_DATA.INST_MTH = LNB7220_AWA_7220.INST_MTH;
        LNCSTMPP.REC_DATA.PERD = LNB7220_AWA_7220.PERD;
        LNCSTMPP.REC_DATA.PERD_UNIT = LNB7220_AWA_7220.PER_UNIT;
        LNCSTMPP.REC_DATA.PHS_PRIN_AMT = LNB7220_AWA_7220.PRIN_AMT;
        LNCSTMPP.REC_DATA.PHS_TOT_TERM = LNB7220_AWA_7220.TOT_TERM;
        LNCSTMPP.REC_DATA.COGN_TERM = LNB7220_AWA_7220.COG_TERM;
        LNCSTMPP.REC_DATA.COGN_INST_AMT = LNB7220_AWA_7220.GNST_AMT;
        LNCSTMPP.REC_DATA.PHS_REMP_AMT = LNB7220_AWA_7220.REMP_AMT;
        LNCSTMPP.FUNC = '2';
        S000_CALL_LNZSTMPP();
    }
    public void S000_CALL_LNZSTMPP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-TMPP-MAIN", LNCSTMPP);
        CEP.TRC(SCCGWA, LNCSTMPP.RC);
        if (LNCSTMPP.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSTMPP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_FUN_LNZCLOAN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CHECK-LNTLOAN", LNCCLOAN);
        if (LNCCLOAN.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCLOAN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
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