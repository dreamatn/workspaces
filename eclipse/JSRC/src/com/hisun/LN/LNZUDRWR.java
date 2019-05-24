package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZUDRWR {
    int JIBS_tmp_int;
    DBParm LNTAGRE_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char LNZUDRWR_FILLER1 = ' ';
    int WS_I = 0;
    int WS_SUF_NO = 0;
    double WS_DRAW_AMT = 0;
    double WS_DRAW_TOT_AMT = 0;
    double WS_LN_TOT_AMT = 0;
    int WS_LAST_TX_SEQ = 0;
    char WS_UPDATE_FLG = ' ';
    char WS_FOUND_FLG = ' ';
    LNRLOAN LNRLOAN = new LNRLOAN();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCLOANM LNCLOANM = new LNCLOANM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCBALHM LNCBALHM = new LNCBALHM();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCCNEV LNCCNEV = new LNCCNEV();
    LNCCNEX LNCCNEX = new LNCCNEX();
    LNCRCAL LNCRCAL = new LNCRCAL();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    LNRAGRE LNRAGRE = new LNRAGRE();
    SCCGWA SCCGWA;
    LNCUDRWR LNCUDRWR;
    public void MP(SCCGWA SCCGWA, LNCUDRWR LNCUDRWR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUDRWR = LNCUDRWR;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUDRWR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        LNCUDRWR.RC.RC_APP = "LN";
        LNCUDRWR.RC.RC_RTNCODE = 0;
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
        B500_MOD_OLDTRAN();
        if (pgmRtn) return;
        B600_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B000_CHECK() throws IOException,SQLException,Exception {
        B010_READ_LNTTRAN();
        if (pgmRtn) return;
        if (LNCTRANM.REC_DATA.TRAN_STS == 'R') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TRAN_CANCEL_REV, LNCUDRWR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B020_READ_LNTCONT();
        if (pgmRtn) return;
        if (LNCCONTM.REC_DATA.LAST_TX_SEQ != LNCTRANM.REC_DATA.TR_SEQ) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_TR_SEQ_NOTMATCH, LNCUDRWR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(31 - 1, 31 + 1 - 1).equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_UN_DRAWDOWN, LNCUDRWR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, LNCCNEX);
        LNCCNEX.COMM_DATA.INQ_CODE = "INQDRAW";
        LNCCNEX.COMM_DATA.LN_AC = LNCUDRWR.COMM_DATA.LN_AC;
        LNCCNEX.COMM_DATA.SUF_NO = LNCUDRWR.COMM_DATA.SUF_NO;
        S000_CALL_LNZCNEX();
        if (pgmRtn) return;
        WS_DRAW_TOT_AMT = LNCCNEX.COMM_DATA.INQ_AMT;
    }
    public void B010_READ_LNTTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '3';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCUDRWR.COMM_DATA.LN_AC;
        CEP.TRC(SCCGWA, LNCUDRWR.COMM_DATA.LN_AC);
        CEP.TRC(SCCGWA, LNCUDRWR.COMM_DATA.SUF_NO);
        if (LNCUDRWR.COMM_DATA.SUF_NO.trim().length() == 0) LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUDRWR.COMM_DATA.SUF_NO);
        LNCTRANM.REC_DATA.KEY.TR_DATE = LNCUDRWR.COMM_DATA.TXN_DT;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = LNCUDRWR.COMM_DATA.JRNNO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
        LNCTRANM.REC_DATA.TR_SEQ = 1;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        LNCUDRWR.COMM_DATA.DRAW_AMT = LNCTRANM.REC_DATA.P_AMT;
        WS_DRAW_AMT = LNCTRANM.REC_DATA.P_AMT;
        LNCUDRWR.COMM_DATA.PREP_INT = LNCTRANM.REC_DATA.I_AMT;
        LNCUDRWR.COMM_DATA.VAL_DT = LNCTRANM.REC_DATA.TR_VAL_DTE;
        CEP.TRC(SCCGWA, WS_DRAW_AMT);
        CEP.TRC(SCCGWA, LNCUDRWR.COMM_DATA.PREP_INT);
    }
    public void B020_READ_LNTCONT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUDRWR.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUDRWR.COMM_DATA.LN_AC;
        if (LNCUDRWR.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUDRWR.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B100_LOAN_READUPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '4';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUDRWR.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDL")) {
            IBS.init(SCCGWA, LNCLOANM);
            LNCLOANM.FUNC = '3';
            LNCLOANM.REC_DATA.KEY.CONTRACT_NO = LNCUDRWR.COMM_DATA.LN_AC;
            S000_CALL_LNZLOANM();
            if (pgmRtn) return;
        }
        WS_LN_TOT_AMT = LNCCONTM.REC_DATA.LN_TOT_AMT;
        WS_DRAW_TOT_AMT = WS_DRAW_TOT_AMT - WS_DRAW_AMT;
        if (LNCUDRWR.COMM_DATA.ACM_EVENT.equalsIgnoreCase("RPC")) {
            WS_LN_TOT_AMT = LNCCONTM.REC_DATA.LN_TOT_AMT - WS_DRAW_AMT;
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        B210_GET_PPMQ_INF();
        if (pgmRtn) return;
        B220_REV_CAL_PROC();
        if (pgmRtn) return;
        B230_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B210_GET_PPMQ_INF() throws IOException,SQLException,Exception {
    }
    public void B220_REV_CAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRCAL);
        LNCRCAL.COMM_DATA.FUNC_CODE = 'U';
        LNCRCAL.COMM_DATA.LN_AC = LNCUDRWR.COMM_DATA.LN_AC;
        LNCRCAL.COMM_DATA.SUF_NO = LNCUDRWR.COMM_DATA.SUF_NO;
        LNCRCAL.COMM_DATA.VAL_DATE = LNCUDRWR.COMM_DATA.VAL_DT;
        S000_CALL_LNZRCAL();
        if (pgmRtn) return;
    }
    public void B230_MAIN_PROC() throws IOException,SQLException,Exception {
        B230_ICTL_UPD_PROCESS();
        if (pgmRtn) return;
    }
    public void B230_ICTL_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUDRWR.COMM_DATA.LN_AC;
        if (LNCUDRWR.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUDRWR.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DRAW_TOT_AMT);
        LNCICTLM.FUNC = '2';
        if (WS_DRAW_TOT_AMT == 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 31 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(31 + 1 - 1);
            }
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 47 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(47 + 1 - 1);
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 48 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(48 + 1 - 1);
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW.substring(56 - 1, 56 + 1 - 1).equalsIgnoreCase("1")) {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 56 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(56 + 1 - 1);
                IBS.init(SCCGWA, BPCFTLPM);
                BPCFTLPM.OP_CODE = 'D';
                BPCFTLPM.TLR = SCCGWA.COMM_AREA.TL_ID;
                if ("1".trim().length() == 0) BPCFTLPM.TX_COUNT = 0;
                else BPCFTLPM.TX_COUNT = Short.parseShort("1");
                BPCFTLPM.BUSS_TYP = "L1";
                S000_CALL_BPZFTLPM();
                if (pgmRtn) return;
            }
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B300_LOAN_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '4';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCUDRWR.COMM_DATA.LN_AC;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        LNCCONTM.FUNC = '2';
        LNCCONTM.REC_DATA.LN_TOT_AMT = WS_LN_TOT_AMT;
        WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
        WS_LAST_TX_SEQ -= 1;
        LNCCONTM.REC_DATA.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNCCONTM.REC_DATA.LAST_F_VAL_DATE = LNCTRANM.REC_DATA.LAST_F_VAL_DATE;
        LNCCONTM.REC_DATA.LAST_TX_SEQ = WS_LAST_TX_SEQ;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B400_CTNR_UPD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCNEV);
        LNCCNEV.COMM_DATA.EVENT_CODE = "DRAWDWN";
        LNCCNEV.COMM_DATA.ACM_EVENT = LNCUDRWR.COMM_DATA.ACM_EVENT;
        LNCCNEV.COMM_DATA.LN_AC = LNCUDRWR.COMM_DATA.LN_AC;
        LNCCNEV.COMM_DATA.SUF_NO = "" + WS_SUF_NO;
        JIBS_tmp_int = LNCCNEV.COMM_DATA.SUF_NO.length();
        for (int i=0;i<8-JIBS_tmp_int;i++) LNCCNEV.COMM_DATA.SUF_NO = "0" + LNCCNEV.COMM_DATA.SUF_NO;
        LNCCNEV.COMM_DATA.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCCNEV.COMM_DATA.P_AMT = WS_DRAW_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].PRI_AMT = WS_DRAW_AMT;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].PRI_AMT = WS_DRAW_AMT;
        LNCCNEV.COMM_DATA.I_AMT = LNCUDRWR.COMM_DATA.PREP_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].INT_AMT = LNCUDRWR.COMM_DATA.PREP_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[2-1].INT_AMT = LNCUDRWR.COMM_DATA.PREP_INT;
        LNCCNEV.COMM_DATA.IETM_AMTS[1-1].FEE_AMT = LNCUDRWR.COMM_DATA.YHS_AMT;
        LNCCNEV.COMM_DATA.RVS_IND = 'Y';
        S000_CALL_LNZCNEV();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCUDRWR.COMM_DATA.LN_AC;
        if (LNCUDRWR.COMM_DATA.SUF_NO.trim().length() == 0) LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUDRWR.COMM_DATA.SUF_NO);
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DRAW_TOT_AMT);
        LNCICTLM.FUNC = '2';
        if (WS_DRAW_TOT_AMT == 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(1);
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 4 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(4 + 1 - 1);
            }
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B500_MOD_OLDTRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '4';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCUDRWR.COMM_DATA.LN_AC;
        if (LNCUDRWR.COMM_DATA.SUF_NO.trim().length() == 0) LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        else LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = Integer.parseInt(LNCUDRWR.COMM_DATA.SUF_NO);
        LNCTRANM.REC_DATA.KEY.TR_DATE = LNCUDRWR.COMM_DATA.TXN_DT;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = LNCUDRWR.COMM_DATA.JRNNO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'D';
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        LNCTRANM.FUNC = '2';
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            LNCTRANM.REC_DATA.TRAN_STS = 'R';
        } else {
            LNCTRANM.REC_DATA.TRAN_STS = 'Y';
        }
        LNCTRANM.REC_DATA.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.TR_REV_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.LOAN_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        CEP.TRC(SCCGWA, LNCTRANM.REC_DATA.LOAN_STSW);
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        LNCUDRWR.COMM_DATA.YHS_AC = LNCTRANM.REC_DATA.PAY_AC2;
        LNCUDRWR.COMM_DATA.YHS_AMT = LNCTRANM.REC_DATA.PAY_AMT2;
        LNCUDRWR.COMM_DATA.REF_NO = LNCTRANM.REC_DATA.REF_NO;
    }
    public void B600_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
    }
    public void T000_READUPD_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.upd = true;
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_AGRE_NOTFND, LNCUDRWR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_LNTAGRE() throws IOException,SQLException,Exception {
        LNRAGRE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRAGRE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.REWRITE(SCCGWA, LNRAGRE, LNTAGRE_RD);
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRWR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZLOANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-LOAN-MAINT", LNCLOANM);
        if (LNCLOANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCLOANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRWR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            LNCUDRWR.RC.RC_APP = LNCICTLM.RC.RC_APP;
            LNCUDRWR.RC.RC_RTNCODE = LNCICTLM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            LNCUDRWR.RC.RC_APP = LNCTRANM.RC.RC_APP;
            LNCUDRWR.RC.RC_RTNCODE = LNCTRANM.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EVENT", LNCCNEV);
        if (LNCCNEV.RC.RC_RTNCODE != 0) {
            LNCUDRWR.RC.RC_APP = LNCCNEV.RC.RC_APP;
            LNCUDRWR.RC.RC_RTNCODE = LNCCNEV.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRCAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REV-CAL-BASE", LNCRCAL);
        if (LNCRCAL.RC.RC_RTNCODE != 0) {
            LNCUDRWR.RC.RC_APP = LNCRCAL.RC.RC_APP;
            LNCUDRWR.RC.RC_RTNCODE = LNCRCAL.RC.RC_RTNCODE;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCNEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-CONT-EXHIBIT", LNCCNEX);
        if (LNCCNEX.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCNEX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRWR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-PEND-MGM", BPCFTLPM);
        if (BPCFTLPM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUDRWR.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCUDRWR.RC.RC_RTNCODE != 0) {
            CEP.TRC(SCCGWA, "LNCUDRWR=");
            CEP.TRC(SCCGWA, LNCUDRWR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
