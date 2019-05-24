package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZISPST {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    short WS_IDX = 0;
    int WS_ADJ_BK_SEQ = 0;
    char WS_ADJ_IS_LOCAL_FLG = ' ';
    LNZISPST_WS_IPST_AMT_INFO WS_IPST_AMT_INFO = new LNZISPST_WS_IPST_AMT_INFO();
    LNZISPST_WS_LOCAL_BK_AMT_INFO WS_LOCAL_BK_AMT_INFO = new LNZISPST_WS_LOCAL_BK_AMT_INFO();
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_PLPI_FLG = ' ';
    char WS_BALL_FLG = ' ';
    char WS_IPST_FLG = ' ';
    LNRICTL LNRICTL = new LNRICTL();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    SCCGWA SCCGWA;
    LNCIPST LNCIPST;
    public void MP(SCCGWA SCCGWA, LNCIPST LNCIPST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIPST = LNCIPST;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZISPST return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCIPST.RC.RC_APP = "LN";
        LNCIPST.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.SUF_NO);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_GET_LOAN_DATA();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCIPST.COMM_DATA.FUNC_CODE != 'I' 
            && LNCIPST.COMM_DATA.FUNC_CODE != 'U') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCIPST.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCIPST.COMM_DATA.BEG_DATE == 0) {
            LNCIPST.COMM_DATA.BEG_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (LNCIPST.COMM_DATA.END_DATE == 0) {
            LNCIPST.COMM_DATA.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.BEG_DATE);
        CEP.TRC(SCCGWA, LNCIPST.COMM_DATA.END_DATE);
        if (LNCIPST.COMM_DATA.BEG_DATE > LNCIPST.COMM_DATA.END_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BEG_GE_END_DATE, LNCIPST.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_DATA() throws IOException,SQLException,Exception {
        B100_GET_LOAN_INF();
        if (pgmRtn) return;
        B100_GET_ICTL_INF();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0")) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCIPST.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B100_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCIPST.COMM_DATA.LN_AC;
        if (LNCIPST.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCIPST.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        S000_CALL_LNZIPST();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCCONTM.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCCONTM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPPMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PRD-PRM-INQ", LNCPPMQ);
        if (LNCPPMQ.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCPPMQ.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCPPMQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLPIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PLPI-MAINT", LNCPLPIM);
        if (LNCPLPIM.RC.RC_RTNCODE != 0) {
            LNCIPST.RC.RC_APP = LNCPLPIM.RC.RC_APP;
            LNCIPST.RC.RC_RTNCODE = LNCPLPIM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIPST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-INT-POST-BASE", LNCIPST);
        if (LNCIPST.RC.RC_RTNCODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCIPST.RC.RC_APP = "SC";
            LNCIPST.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCIPST.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCIPST=");
            CEP.TRC(SCCGWA, LNCIPST);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
