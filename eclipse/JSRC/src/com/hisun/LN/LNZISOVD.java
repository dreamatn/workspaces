package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZISOVD {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    short WS_IDX = 0;
    int WS_ADJ_BK_SEQ = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_PLPI_FLG = ' ';
    char WS_BALL_FLG = ' ';
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
    LNCIPART LNCIPART = new LNCIPART();
    SCCGWA SCCGWA;
    LNCIOVD LNCIOVD;
    public void MP(SCCGWA SCCGWA, LNCIOVD LNCIOVD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCIOVD = LNCIOVD;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZISOVD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCIOVD.RC.RC_APP = "LN";
        LNCIOVD.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCIOVD.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCIOVD.COMM_DATA.VAL_DATE);
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
        if (LNCIOVD.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCIOVD.RC);
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
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UN_DRAWDOWN, LNCIOVD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B100_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
        if (LNCIOVD.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCIOVD.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        R000_SYNLOAN_CHECK_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCIPART.DATA.CNT);
        S000_CALL_LNZIOVD();
        if (pgmRtn) return;
        if (LNCIPART.DATA.IS_SYN == 'Y' 
            || LNCIPART.DATA.IS_SYN == 'I') {
            for (WS_IDX = 1; WS_IDX <= LNCIPART.DATA.CNT; WS_IDX += 1) {
                LNCIOVD.COMM_DATA.SUF_NO = "" + LNCIPART.GROUP.get(WS_IDX-1).SEQ_NO;
                JIBS_tmp_int = LNCIOVD.COMM_DATA.SUF_NO.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) LNCIOVD.COMM_DATA.SUF_NO = "0" + LNCIOVD.COMM_DATA.SUF_NO;
                S000_CALL_LNZIOVD();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCCONTM.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCCONTM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPPMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PRD-PRM-INQ", LNCPPMQ);
        if (LNCPPMQ.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCPPMQ.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCPPMQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLPIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PLPI-MAINT", LNCPLPIM);
        if (LNCPLPIM.RC.RC_RTNCODE != 0) {
            LNCIOVD.RC.RC_APP = LNCPLPIM.RC.RC_APP;
            LNCIOVD.RC.RC_RTNCODE = LNCPLPIM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIOVD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-DUE-OVD-BASE", LNCIOVD);
        if (LNCIOVD.RC.RC_RTNCODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCIOVD.RC.RC_APP = "SC";
            LNCIOVD.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_SYNLOAN_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIPART.DATA);
        LNCIPART.DATA.FUNC = 'T';
        LNCIPART.DATA.LEVEL = 'R';
        LNCIPART.DATA.CONTRACT_NO = LNCIOVD.COMM_DATA.LN_AC;
        if (LNCCONTM.REC_DATA.FATHER_CONTRACT.trim().length() == 0) {
            LNCIPART.DATA.IS_SYN = 'N';
        }
    }
    public void S000_CALL_LNZIPART() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PARTI-INQ", LNCIPART);
        if (LNCIPART.RC.RC_CODE != 0) {
            LNCIOVD.RC.RC_APP = LNCIPART.RC.RC_MMO;
            LNCIOVD.RC.RC_RTNCODE = LNCIPART.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCIOVD.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCIOVD=");
            CEP.TRC(SCCGWA, LNCIOVD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
