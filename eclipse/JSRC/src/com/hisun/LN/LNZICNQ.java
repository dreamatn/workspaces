package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZICNQ {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char LNZICNQ_FILLER1 = ' ';
    int WS_I = 0;
    int WS_IDX = 0;
    double WS_AMT = 0;
    short WS_NO = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCCNTLM LNCCNTLM = new LNCCNTLM();
    LNCCNTIM LNCCNTIM = new LNCCNTIM();
    LNCBALLM LNCBALLM = new LNCBALLM();
    SCCGWA SCCGWA;
    LNCICNQ LNCICNQ;
    public void MP(SCCGWA SCCGWA, LNCICNQ LNCICNQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCICNQ = LNCICNQ;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZICNQ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCICNQ.RC.RC_APP = "LN";
        LNCICNQ.RC.RC_RTNCODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_GET_CNTI_INF();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCICNQ.COMM_DATA.CONTRACT_NO.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCICNQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_GET_CNTI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNTIM);
        LNCCNTIM.FUNC = 'I';
        LNCCNTIM.KEY.TYP = "CNTI";
        LNCCNTIM.KEY.CD = LNCICNQ.COMM_DATA.INQ_CODE;
        LNCCNTLM.KEY.BK = SCCGWA.COMM_AREA.TR_BANK;
        S000_CALL_LNZCNTIM();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_GET_BALL_INF();
        if (pgmRtn) return;
        for (WS_IDX = 1; WS_IDX <= 10; WS_IDX += 1) {
            if (LNCCNTIM.DATA_TXT.CTNR_DATA[WS_IDX-1].CTNR_ID.CTNR_TYP != ' ') {
                B100_GET_CNTL_INF();
                if (pgmRtn) return;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNTIM.DATA_TXT.CTNR_DATA[WS_IDX-1].CTNR_ID);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICNQ.COMM_DATA.CNTR[WS_IDX-1].CNTL_ID);
                B200_COMPUTE_OUTAMT();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_GET_BALL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCICNQ.COMM_DATA.CONTRACT_NO;
        LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = LNCICNQ.COMM_DATA.SUB_CTA_NO;
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
    }
    public void B100_GET_CNTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNTLM);
        LNCCNTLM.FUNC = 'I';
        LNCCNTLM.KEY.TYP = "CNTL";
        LNCCNTLM.KEY.CD = IBS.CLS2CPY(SCCGWA, LNCCNTIM.DATA_TXT.CTNR_DATA[WS_IDX-1].CTNR_ID);
        S000_CALL_LNZCNTLM();
        if (pgmRtn) return;
        WS_NO = LNCCNTLM.DATA_TXT.NO;
    }
    public void B200_COMPUTE_OUTAMT() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNTIM.DATA_TXT.CTNR_DATA[WS_IDX-1].CTNR_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICNQ.COMM_DATA.CNTR[WS_IDX-1].CNTL_ID);
        LNCICNQ.COMM_DATA.CNTR[WS_IDX-1].CNTL_AMT = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[WS_NO-1].LOAN_BAL;
    }
    public void S000_CALL_LNZCNTIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CNTI-MAINT", LNCCNTIM);
        if (LNCCNTIM.RC.RC_RTNCODE != 0) {
            LNCICNQ.RC.RC_APP = LNCCNTIM.RC.RC_APP;
            LNCICNQ.RC.RC_RTNCODE = LNCCNTIM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CNTL-MAINT", LNCCNTLM);
        if (LNCCNTLM.RC.RC_RTNCODE != 0) {
            LNCICNQ.RC.RC_APP = LNCCNTLM.RC.RC_APP;
            LNCICNQ.RC.RC_RTNCODE = LNCCNTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            LNCICNQ.RC.RC_APP = LNCBALLM.RC.RC_APP;
            LNCICNQ.RC.RC_RTNCODE = LNCBALLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCICNQ.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCICNQ=");
            CEP.TRC(SCCGWA, LNCICNQ);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
