package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZUDRW {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    char LNZUDRW_FILLER2 = ' ';
    int WS_I = 0;
    int WS_SUF_NO = 0;
    double WS_BAL_AMT = 0;
    double WS_DRAW_TOT_AMT = 0;
    int WS_LAST_TX_SEQ = 0;
    char WS_EOM_CAL_FLG = ' ';
    int WS_FST_CAL_DT = 0;
    int WS_FST_FLT_DT = 0;
    int WS_FRST_DATE = 0;
    int WS_NEXT_DATE = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCMSG SCCMSG = new SCCMSG();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCBALHM LNCBALHM = new LNCBALHM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCRATG LNCRATG = new LNCRATG();
    LNCPLAJ LNCPLAJ = new LNCPLAJ();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    SCCGWA SCCGWA;
    LNCUDRW LNCUDRW;
    BPCGCFEE BPCGCFEE;
    public void MP(SCCGWA SCCGWA, LNCUDRW LNCUDRW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUDRW = LNCUDRW;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUDRW return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCUDRW.RC.RC_APP = "LN";
        LNCUDRW.RC.RC_RTNCODE = 0;
        CEP.TRC(SCCGWA, LNCUDRW.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCUDRW.COMM_DATA.DRAW_AMT);
        CEP.TRC(SCCGWA, LNCUDRW.COMM_DATA.VAL_DT);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK();
        if (pgmRtn) return;
        B100_LOAN_READUPD();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
        B300_LOAN_UPD_PROCESS();
        if (pgmRtn) return;
        B400_CTNR_UPD_PROCESS();
        if (pgmRtn) return;
        B500_TRAN_ADD_PROCESS();
        if (pgmRtn) return;
        B700_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        if (LNCUDRW.COMM_DATA.LN_AC.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, LNCUDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDRAW";
        LNCCNEX.COMM_DATA.LN_AC = LNCUDRW.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCUDRW.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_DRAW_TOT_AMT = LNCCNEX.COMM_DATA.INQ_AMT;
    }
    public void B100_LOAN_READUPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        if (LNCUDRW.COMM_DATA.SUF_NO.trim().length() == 0) {
            LNCCONTM.FUNC = '4';
            LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUDRW.COMM_DATA.LN_AC;
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
            WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
            WS_LAST_TX_SEQ += 1;
        } else {
            LNCCONTM.FUNC = '3';
            LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUDRW.COMM_DATA.LN_AC;
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
            WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
        }
        IBS.init(SCCGWA, LNCLOANM);
        LNCLOANM.FUNC = '3';
        LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCUDRW.COMM_DATA.LN_AC;
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCUDRW.COMM_DATA.LN_AC;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        if (WS_DRAW_TOT_AMT != 0 
            && LNCAPRDM.REC_DATA.PAY_MTH == '4') {
            B210_RECSTR_PAIPSCH();
            if (pgmRtn) return;
        }
        B210_ICTL_UPD_PROCESS();
        if (pgmRtn) return;
    }
    public void B210_RECSTR_PAIPSCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPLAJ);
        LNCPLAJ.COMM_DATA.ADJ_IND = 'D';
        LNCPLAJ.COMM_DATA.LN_AC = LNCUDRW.COMM_DATA.LN_AC;
        LNCPLAJ.COMM_DATA.SUF_NO = LNCUDRW.COMM_DATA.SUF_NO;
        LNCPLAJ.COMM_DATA.TR_VAL_DATE = LNCUDRW.COMM_DATA.VAL_DT;
        LNCPLAJ.COMM_DATA.PAY_P_AMT = LNCUDRW.COMM_DATA.DRAW_AMT;
        S000_CALL_LNZPLAJ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.CONT_PAY_MTH);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.CONT_INST_MTH);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.PRPY_INT_MTH);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.INST_RBD_MTH);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.INST_AMT);
        CEP.TRC(SCCGWA, LNCPLAJ.COMM_DATA.INST_TERM);
    }
    public void B210_ICTL_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUDRW.COMM_DATA.LN_AC;
        if (LNCUDRW.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUDRW.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_TERM);
        CEP.TRC(SCCGWA, LNCUDRW.COMM_DATA.VAL_DT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_VAL_DT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.IC_CAL_DUE_DT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.INT_CUT_DT);
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CMP_DUE_DT);
        if (LNCUDRW.COMM_DATA.VAL_DT < LNCICTLM.REC_DATA.INT_CUT_DT) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DRAW_VAL_DATE, LNCUDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 31 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(31 + 1 - 1);
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1));
        CEP.TRC(SCCGWA, LNCICTLM.REC_DATA.P_CMP_DUE_DT);
    }
    public void B300_LOAN_UPD_PROCESS() throws IOException,SQLException,Exception {
        if (LNCUDRW.COMM_DATA.SUF_NO.trim().length() == 0) {
            LNCCONTM.FUNC = '2';
            WS_DRAW_TOT_AMT += LNCUDRW.COMM_DATA.DRAW_AMT;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            if (JIBS_tmp_str[0].equalsIgnoreCase("0132113")) {
                LNCCONTM.REC_DATA.LN_TOT_AMT += LNCUDRW.COMM_DATA.DRAW_AMT;
            }
            if (LNCUDRW.COMM_DATA.ACM_EVENT.equalsIgnoreCase("RPC")) {
                LNCCONTM.REC_DATA.ORIG_TOT_AMT += LNCUDRW.COMM_DATA.DRAW_AMT;
            }
            CEP.TRC(SCCGWA, WS_DRAW_TOT_AMT);
            CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.LN_TOT_AMT);
            CEP.TRC(SCCGWA, LNCCONTM.REC_DATA.ORIG_TOT_AMT);
            if (WS_DRAW_TOT_AMT > LNCCONTM.REC_DATA.ORIG_TOT_AMT) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DRAW_AMT, LNCUDRW.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            LNCCONTM.REC_DATA.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
            LNCCONTM.REC_DATA.LAST_F_VAL_DATE = LNCUDRW.COMM_DATA.VAL_DT;
            LNCCONTM.REC_DATA.LAST_TX_SEQ = WS_LAST_TX_SEQ;
            S000_CALL_LNZCONTM();
            if (pgmRtn) return;
        }
    }
    public void B400_CTNR_UPD_PROCESS() throws IOException,SQLException,Exception {
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL") 
            && LNCAPRDM.REC_DATA.PAY_MTH != '0') {
            LNCUDRW.COMM_DATA.PREP_INT = 0;
        }
        B410_CTNR_UPD_PROCESS();
        if (pgmRtn) return;
    }
    public void B410_CTNR_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "DRAWDWN";
        LNCCNEV.COMM_DATA.ACM_EVENT = LNCUDRW.COMM_DATA.ACM_EVENT;
        LNCCNEV.COMM_DATA.LN_AC = LNCUDRW.COMM_DATA.LN_AC;
        LNCCNEV.COMM_DATA.SUF_NO = LNCUDRW.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = LNCUDRW.COMM_DATA.VAL_DT;
        LNCCNEV.COMM_DATA.P_AMT = LNCUDRW.COMM_DATA.DRAW_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = LNCUDRW.COMM_DATA.DRAW_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = LNCUDRW.COMM_DATA.DRAW_AMT;
        CEP.TRC(SCCGWA, "11111111111");
        CEP.TRC(SCCGWA, LNCUDRW.COMM_DATA.PREP_INT);
        LNCCNEV.COMM_DATA.I_AMT = LNCUDRW.COMM_DATA.PREP_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = LNCUDRW.COMM_DATA.PREP_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = LNCUDRW.COMM_DATA.PREP_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].FEE_AMT = LNCUDRW.COMM_DATA.YHS_AMT;
        LNCCNEV.COMM_DATA.RVS_IND = 'N';
        S000_CALL_LNZCNEV();
        if (pgmRtn) return;
    }
    public void B500_TRAN_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQPBAL";
        LNCCNEX.COMM_DATA.LN_AC = LNCUDRW.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCUDRW.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCUDRW.COMM_DATA.LN_AC;
        if (LNCUDRW.COMM_DATA.SUF_NO.trim().length() == 0) LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUDRW.COMM_DATA.SUF_NO);
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.P_AMT = LNCUDRW.COMM_DATA.DRAW_AMT;
        LNCTRANM.REC_DATA.I_AMT = LNCUDRW.COMM_DATA.PREP_INT;
        CEP.TRC(SCCGWA, LNCUDRW.COMM_DATA.PREP_INT);
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            if ("0132200".trim().length() == 0) LNCTRANM.REC_DATA.TR_CODE = 0;
            else LNCTRANM.REC_DATA.TR_CODE = Short.parseShort("0132200");
        }
        LNCTRANM.REC_DATA.TR_VAL_DTE = LNCUDRW.COMM_DATA.VAL_DT;
        LNCTRANM.REC_DATA.ALL_IN_RATE = LNCICTLM.REC_DATA.CUR_RAT;
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = LNCCONTM.REC_DATA.LAST_F_VAL_DATE;
        LNCTRANM.REC_DATA.TR_SEQ = WS_LAST_TX_SEQ;
        LNCTRANM.REC_DATA.TXN_CCY = LNCUDRW.COMM_DATA.CCY;
        LNCTRANM.REC_DATA.OS_BAL = LNCCNEX.COMM_DATA.INQ_AMT;
        LNCTRANM.REC_DATA.F_AMT = LNCUDRW.COMM_DATA.TRU_FEE;
        LNCTRANM.REC_DATA.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNCTRANM.REC_DATA.TR_MMO = "D106";
        LNCTRANM.REC_DATA.LOAN_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        LNCTRANM.REC_DATA.PAY_AC1 = LNCUDRW.COMM_DATA.TRAN_AC;
        LNCTRANM.REC_DATA.AC_FLG1 = LNCUDRW.COMM_DATA.DRAW_BK_TP;
        LNCTRANM.REC_DATA.AC_TYP1 = LNCUDRW.COMM_DATA.DRAW_AC_TYP;
        LNCTRANM.REC_DATA.PAY_AMT1 = LNCUDRW.COMM_DATA.PREP_INT;
        LNCTRANM.REC_DATA.PAY_AC2 = LNCUDRW.COMM_DATA.YHS_AC;
        LNCTRANM.REC_DATA.PAY_AMT2 = LNCUDRW.COMM_DATA.YHS_AMT;
        LNCTRANM.REC_DATA.REF_NO = LNCUDRW.COMM_DATA.REF_NO;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B600_FEE_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B700_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEV.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRATG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-RATE-GEN", LNCRATG);
        if (LNCRATG.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRATG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-PEND-MGM", BPCFTLPM);
        if (BPCFTLPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLPM.RC);
            S000_ERR_MSG_PROC_W();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            LNCUDRW.RC.RC_APP = BPCTCALF.RC.RC_MMO;
            LNCUDRW.RC.RC_RTNCODE = BPCTCALF.RC.RC_CODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            LNCUDRW.RC.RC_APP = LNCTRANM.RC.RC_APP;
            LNCUDRW.RC.RC_RTNCODE = LNCTRANM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R00_CAL_DATE() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            LNCUDRW.RC.RC_APP = "SC";
            LNCUDRW.RC.RC_RTNCODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPLAJ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PLPI-AUTADJ", LNCPLAJ);
        if (LNCPLAJ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPLAJ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRW.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_W() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCUDRW.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCUDRW=");
            CEP.TRC(SCCGWA, LNCUDRW);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
