package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZISCAL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTAGRE_RD;
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    char K_PRINCIPAL = 'P';
    char K_INTEREST = 'I';
    short WS_IDX = 0;
    int WS_ADJ_BK_SEQ = 0;
    char WS_ICAL_FUNC_TYPE = ' ';
    double WS_P_REC_AMT = 0;
    double WS_I_REC_AMT = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    char WS_PLPI_FLG = ' ';
    char WS_BALL_FLG = ' ';
    LNRICTL LNRICTL = new LNRICTL();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRCONT LNRCONT = new LNRCONT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCPLPIM LNCPLPIM = new LNCPLPIM();
    LNCIPART LNCIPART = new LNCIPART();
    SCCGWA SCCGWA;
    LNCICAL LNCICAL;
    public void MP(SCCGWA SCCGWA, LNCICAL LNCICAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCICAL = LNCICAL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZISCAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCICAL.RC.RC_APP = "LN";
        LNCICAL.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.VAL_DATE);
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
        if (LNCICAL.COMM_DATA.FUNC_CODE != 'I' 
            && LNCICAL.COMM_DATA.FUNC_CODE != 'U') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE, LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICAL.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_DATA() throws IOException,SQLException,Exception {
        B100_GET_LOAN_INF();
        if (pgmRtn) return;
        B100_GET_ICTL_INF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "=========ICTLM-CTL-STSW = 0 IS ERROR====");
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1));
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UN_DRAWDOWN, LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_GET_LOAN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B100_GET_ICTL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        if (LNCICAL.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCICAL.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        if (LNCCONTM.REC_DATA.FATHER_CONTRACT.trim().length() == 0) {
            LNRAGRE.VRTU_FLG = 'Y';
        } else {
            LNRAGRE.KEY.CONTRACT_NO = LNCCONTM.REC_DATA.KEY.CONTRACT_NO;
            T000_READ_LNTAGRE_BY_CONT_NO();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCIPART.DATA);
        if (LNRAGRE.VRTU_FLG != 'Y') {
            LNCIPART.DATA.IS_SYN = 'Y';
        }
        CEP.TRC(SCCGWA, LNCIPART.DATA.CNT);
        CEP.TRC(SCCGWA, LNCIPART.DATA.IS_SYN);
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.FUNC_TYPE);
        LNCICAL.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
        WS_ICAL_FUNC_TYPE = LNCICAL.COMM_DATA.FUNC_TYPE;
        CEP.TRC(SCCGWA, WS_ICAL_FUNC_TYPE);
        CEP.TRC(SCCGWA, "=============CALL-LNZICAL==========");
        S000_CALL_LNZICAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "=============CALL-LNZICAL SUSSFUL==");
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.INT_AMT);
        WS_P_REC_AMT = LNCICAL.COMM_DATA.PRI_AMT;
        WS_I_REC_AMT = LNCICAL.COMM_DATA.INT_AMT;
        if (LNCIPART.DATA.IS_SYN == 'Y' 
            || LNCIPART.DATA.IS_SYN == 'I') {
            if (WS_ICAL_FUNC_TYPE == K_PRINCIPAL 
                || WS_ICAL_FUNC_TYPE == ' ') {
                LNCICAL.COMM_DATA.FUNC_TYPE = K_PRINCIPAL;
                for (WS_IDX = 1; WS_IDX <= LNCIPART.DATA.CNT; WS_IDX += 1) {
                    CEP.TRC(SCCGWA, LNCIPART.GROUP.get(WS_IDX-1).SEQ_NO);
                    LNCICAL.COMM_DATA.SUF_NO = "" + LNCIPART.GROUP.get(WS_IDX-1).SEQ_NO;
                    JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
                    S000_CALL_LNZICAL();
                    if (pgmRtn) return;
                }
            }
            if (WS_ICAL_FUNC_TYPE == K_INTEREST 
                || WS_ICAL_FUNC_TYPE == ' ') {
                LNCICAL.COMM_DATA.FUNC_TYPE = K_INTEREST;
                for (WS_IDX = 1; WS_IDX <= LNCIPART.DATA.CNT; WS_IDX += 1) {
                    CEP.TRC(SCCGWA, LNCIPART.GROUP.get(WS_IDX-1).SEQ_NO);
                    CEP.TRC(SCCGWA, LNCIPART.GROUP.get(WS_IDX-1).ADJ_BK_FLG);
                    CEP.TRC(SCCGWA, LNCIPART.GROUP.get(WS_IDX-1).LC_BK_FLG);
                    if (LNCIPART.GROUP.get(WS_IDX-1).ADJ_BK_FLG == 'N' 
                        || LNCIPART.GROUP.get(WS_IDX-1).ADJ_BK_FLG == 'P') {
                        CEP.TRC(SCCGWA, LNCIPART.GROUP.get(WS_IDX-1).SEQ_NO);
                        LNCICAL.COMM_DATA.SUF_NO = "" + LNCIPART.GROUP.get(WS_IDX-1).SEQ_NO;
                        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
                        for (int i=0;i<8-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
                        S000_CALL_LNZICAL();
                        if (pgmRtn) return;
                        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.INT_AMT);
                    } else {
                        if (LNCIPART.GROUP.get(WS_IDX-1).ADJ_BK_FLG == 'A' 
                            || LNCIPART.GROUP.get(WS_IDX-1).ADJ_BK_FLG == 'I') {
                            WS_ADJ_BK_SEQ = LNCIPART.GROUP.get(WS_IDX-1).SEQ_NO;
                            CEP.TRC(SCCGWA, WS_ADJ_BK_SEQ);
                        }
                    }
                }
                B203_ADJ_BK_INT_PROC();
                if (pgmRtn) return;
            }
        }
        LNCICAL.COMM_DATA.PRI_AMT = WS_P_REC_AMT;
        LNCICAL.COMM_DATA.INT_AMT = WS_I_REC_AMT;
    }
    public void B203_ADJ_BK_INT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNCICAL.COMM_DATA.FUNC_TYPE);
        CEP.TRC(SCCGWA, WS_ICAL_FUNC_TYPE);
        LNCICAL.COMM_DATA.SUF_NO = "" + WS_ADJ_BK_SEQ;
        JIBS_tmp_int = LNCICAL.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCICAL.COMM_DATA.SUF_NO = "0" + LNCICAL.COMM_DATA.SUF_NO;
        LNCICAL.COMM_DATA.ADJ_BK_FLG = 'Y';
        S000_CALL_LNZICAL();
        if (pgmRtn) return;
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCCONTM.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCCONTM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPPMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PRD-PRM-INQ", LNCPPMQ);
        if (LNCPPMQ.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCPPMQ.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCPPMQ.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLPIM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-PLPI-MAINT", LNCPLPIM);
        if (LNCPLPIM.RC.RC_RTNCODE != 0) {
            LNCICAL.RC.RC_APP = LNCPLPIM.RC.RC_APP;
            LNCICAL.RC.RC_RTNCODE = LNCPLPIM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-INT-CAL-BASE", LNCICAL);
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCICAL.RC.RC_APP = "SC";
            LNCICAL.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_SYNLOAN_CHECK_PROC() throws IOException,SQLException,Exception {
        LNCIPART.DATA.FUNC = 'T';
        LNCIPART.DATA.LEVEL = 'R';
        LNCIPART.DATA.CONTRACT_NO = LNCICAL.COMM_DATA.LN_AC;
        S000_CALL_LNZIPART();
        if (pgmRtn) return;
    }
    public void T000_READ_LNTAGRE_BY_CONT_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRAGRE.KEY.CONTRACT_NO);
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AGRE_NOTFND, LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SYS_ERR, LNCICAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIPART() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-PARTI-INQ", LNCIPART);
        if (LNCIPART.RC.RC_CODE != 0) {
            LNCICAL.RC.RC_APP = LNCIPART.RC.RC_MMO;
            LNCICAL.RC.RC_RTNCODE = LNCIPART.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCICAL.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCICAL=");
            CEP.TRC(SCCGWA, LNCICAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
