package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZCNEX {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char LNZCNEX_FILLER1 = ' ';
    int WS_I = 0;
    double WS_AMT = 0;
    short WS_NO = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    int WS_CNTI_CNT = 10;
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    LNCCNTIM LNCCNTIM = new LNCCNTIM();
    LNCCNTLM LNCCNTLM = new LNCCNTLM();
    LNCBALLM LNCBALLM = new LNCBALLM();
    SCCGWA SCCGWA;
    LNCCNEX LNCCNEX;
    public void MP(SCCGWA SCCGWA, LNCCNEX LNCCNEX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCCNEX = LNCCNEX;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZCNEX return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCCNEX.RC.RC_APP = "LN";
        LNCCNEX.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_CODE);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.SUF_NO);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCCNEX.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCCNEX.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_CNTI_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNTIM);
        LNCCNTIM.FUNC = 'I';
        LNCCNTIM.KEY.TYP = "CNTI";
        LNCCNTIM.KEY.CD = LNCCNEX.COMM_DATA.INQ_CODE;
        CEP.TRC(SCCGWA, "===INQ-CODE===");
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_CODE);
        CEP.TRC(SCCGWA, "CNTIM-TYP=====");
        CEP.TRC(SCCGWA, LNCCNTIM.KEY.TYP);
        S000_CALL_LNZCNTIM();
        if (pgmRtn) return;
    }
    public void B200_GET_BALL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCBALLM);
        LNCBALLM.FUNC = '3';
        LNCBALLM.REC_DATA.KEY.CONTRACT_NO = LNCCNEX.COMM_DATA.LN_AC;
        if (LNCCNEX.COMM_DATA.SUF_NO.trim().length() == 0) LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCBALLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCCNEX.COMM_DATA.SUF_NO);
        S000_CALL_LNZBALLM();
        if (pgmRtn) return;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_GET_CNTI_INF();
        if (pgmRtn) return;
        B200_GET_BALL_INF();
        if (pgmRtn) return;
        LNCCNEX.COMM_DATA.INQ_AMT = 0;
        for (WS_I = 1; WS_I <= WS_CNTI_CNT; WS_I += 1) {
            if (LNCCNTIM.DATA_TXT.CTNR_DATA[WS_I-1].CTNR_ID.CTNR_TYP != ' ') {
                B300_GET_CNTL_INF();
                if (pgmRtn) return;
                B400_COMPUTE_OUTAMT();
                if (pgmRtn) return;
            }
        }
    }
    public void B300_GET_CNTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNTLM);
        LNCCNTLM.FUNC = 'I';
        LNCCNTLM.KEY.TYP = "CNTL";
        LNCCNTLM.KEY.CD = IBS.CLS2CPY(SCCGWA, LNCCNTIM.DATA_TXT.CTNR_DATA[WS_I-1].CTNR_ID);
        LNCCNTLM.KEY.BK = SCCGWA.COMM_AREA.TR_BANK;
        CEP.TRC(SCCGWA, "=====CNTLM-TYP=====");
        CEP.TRC(SCCGWA, LNCCNTLM.KEY.TYP);
        CEP.TRC(SCCGWA, "=====CNTLM-CD======");
        CEP.TRC(SCCGWA, LNCCNTLM.KEY.CD);
        S000_CALL_LNZCNTLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "=====CNTLM-NO=====");
        CEP.TRC(SCCGWA, LNCCNTLM.DATA_TXT.NO);
        WS_NO = LNCCNTLM.DATA_TXT.NO;
    }
    public void B400_COMPUTE_OUTAMT() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNTIM.DATA_TXT.CTNR_DATA[WS_I-1].CTNR_ID);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCCNEX.COMM_DATA.CTNR_DATA[WS_I-1].CTNR_ID);
        LNCCNEX.COMM_DATA.CTNR_DATA[WS_I-1].LOG_REL = LNCCNTIM.DATA_TXT.CTNR_DATA[WS_I-1].LOG_REL;
        WS_AMT = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[WS_NO-1].LOAN_BAL;
        LNCCNEX.COMM_DATA.CTNR_DATA[WS_I-1].CTNR_AMT = LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[WS_NO-1].LOAN_BAL;
        CEP.TRC(SCCGWA, LNCCNTIM.DATA_TXT.CTNR_DATA[WS_I-1].LOG_REL);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[2-1].CTNR_AMT);
        if (LNCCNTIM.DATA_TXT.CTNR_DATA[WS_I-1].LOG_REL == 'A') {
            LNCCNEX.COMM_DATA.INQ_AMT += WS_AMT;
        }
        if (LNCCNTIM.DATA_TXT.CTNR_DATA[WS_I-1].LOG_REL == 'S') {
            LNCCNEX.COMM_DATA.INQ_AMT -= WS_AMT;
        }
        CEP.TRC(SCCGWA, "=======(WS-I)=======");
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, "==========CNEX-CTNR-ID(WS-I)=========");
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[WS_I-1].CTNR_ID);
        CEP.TRC(SCCGWA, "==========CNEX-LOG-REL(WS-I)==========");
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.CTNR_DATA[WS_I-1].LOG_REL);
        CEP.TRC(SCCGWA, "==========WS-NO=======================");
        CEP.TRC(SCCGWA, WS_NO);
        CEP.TRC(SCCGWA, "==========BALLM-LOAN-BAL(WS-NO)======");
        CEP.TRC(SCCGWA, LNCBALLM.REC_DATA.REDEFINES18.REDEFINES21.LOAN_CONT[WS_NO-1].LOAN_BAL);
        CEP.TRC(SCCGWA, "==========CNEX-INQ-AMT===============");
        CEP.TRC(SCCGWA, LNCCNEX.COMM_DATA.INQ_AMT);
    }
    public void S000_CALL_LNZCNTIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CNTI-MAINT", LNCCNTIM);
        if (LNCCNTIM.RC.RC_RTNCODE != 0) {
            LNCCNEX.RC.RC_APP = LNCCNTIM.RC.RC_APP;
            LNCCNEX.RC.RC_RTNCODE = LNCCNTIM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZBALLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-BALL-MAINT", LNCBALLM);
        if (LNCBALLM.RC.RC_RTNCODE != 0) {
            LNCCNEX.RC.RC_APP = LNCBALLM.RC.RC_APP;
            LNCCNEX.RC.RC_RTNCODE = LNCBALLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CNTL-MAINT", LNCCNTLM);
        if (LNCCNTLM.RC.RC_RTNCODE != 0) {
            LNCCNEX.RC.RC_APP = LNCCNTLM.RC.RC_APP;
            LNCCNEX.RC.RC_RTNCODE = LNCCNTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, LNCCNEX);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, " ");
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
