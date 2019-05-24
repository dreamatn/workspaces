package com.hisun.LN;

import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.DC.*;
import com.hisun.AI.*;
import com.hisun.IB.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSPLTN {
    int JIBS_tmp_int;
    DBParm LNTPACK_RD;
    String JIBS_tmp_str[] = new String[10];
    DBParm LNTCTPY_RD;
    DBParm LNTTRAN_RD;
    DBParm LNTRCVD_RD;
    DBParm LNTBALZ_RD;
    boolean pgmRtn = false;
    String K_MMO_P = "12010020";
    String K_MMO_I = "12010021";
    String K_MMO_A = "12010028";
    int K_TF_BR = "043400";
    String K_TF_ITM = "1181651000";
    int K_TF_SEQ = "000002";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_CTL_STSW = " ";
    int WS_LAST_F_VAL_DT = 0;
    int WS_LAST_TX_SEQ = 0;
    LNZSPLTN_WS_TRAN_DATA WS_TRAN_DATA = new LNZSPLTN_WS_TRAN_DATA();
    String WS_TF_AC = " ";
    double WS_AO_INT = 0;
    double WS_INT_N = 0;
    double WS_INT_O = 0;
    double WS_INT_L = 0;
    char WS_TRANS_FLG = ' ';
    char WS_REV_FLG = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    LNCCONTM LNCCONTM = new LNCCONTM();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNRCTPY LNRCTPY = new LNRCTPY();
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNCICUT LNCICUT = new LNCICUT();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    LNRPACK LNRPACK = new LNRPACK();
    DCCPACTY DCCPACTY = new DCCPACTY();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    LNCTRANM LNCTRANM = new LNCTRANM();
    AICUUPIA AICUUPIA = new AICUUPIA();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNCIGVCY LNCIGVCY = new LNCIGVCY();
    LNRRCVD LNRRCVD = new LNRRCVD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNCRAGRE LNCRAGRE = new LNCRAGRE();
    CICQACAC CICQACAC = new CICQACAC();
    int WS_MIN_DATE = 0;
    int WS_MAX_DATE = 0;
    double WS_TRN_N_INT = 0;
    double WS_TRN_O_INT = 0;
    double WS_TRN_L_INT = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    LNCSPLTN LNCSPLTN;
    public void MP(SCCGWA SCCGWA, LNCSPLTN LNCSPLTN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSPLTN = LNCSPLTN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSPLTN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B100_CHECK_PROC();
            if (pgmRtn) return;
            B200_GET_INT_INFO();
            if (pgmRtn) return;
            B300_TRANSFER_AMT_PROC();
            if (pgmRtn) return;
            B600_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else {
            B100_CHECK_PROC();
            if (pgmRtn) return;
            B300_TRANSFER_AMT_PROC();
            if (pgmRtn) return;
            B600_WRITE_HIS_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_PROC() throws IOException,SQLException,Exception {
        if (LNCSPLTN.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPLTN.PRIN_MOD != '0' 
            && LNCSPLTN.PRIN_MOD != '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0300;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSPLTN.INT_MOD != '0' 
            && LNCSPLTN.INT_MOD != '1' 
            && LNCSPLTN.INT_MOD != '2') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0300;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B010_GET_CONTRACT_INFO();
        if (pgmRtn) return;
        if (!LNCCONTM.REC_DATA.CONTRACT_TYPE.equalsIgnoreCase("CLDD")) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_CTA_TYP;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            if (LNCSPLTN.INT_MOD != '0' 
                && (LNCICTLM.REC_DATA.CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
                || LNCICTLM.REC_DATA.CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1"))) {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0357;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNCSPLTN.PRIN_MOD == '0') {
                if (LNCSPLTN.INT_MOD == '0') {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0304;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    WS_TRANS_FLG = 'I';
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    if (LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0305;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            } else {
                WS_TRANS_FLG = 'P';
                if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                if (LNCICTLM.REC_DATA.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1")) {
                    WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0306;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (LNCSPLTN.INT_MOD != '0') {
                    WS_TRANS_FLG = 'A';
                    if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
                    JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
                    for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
                    if (LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
                        WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0305;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void B010_GET_CONTRACT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '3';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCPCFTA);
        LNCPCFTA.BR_GP[1-1].BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        LNCPCFTA.BR_GP[2-1].BR = LNCCONTM.REC_DATA.DOMI_BR;
        S000_CALL_LNZPCFTA();
        if (pgmRtn) return;
        if (LNCPCFTA.FTA_FLG == 'Y') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_NO_TRAN_FTA_NOT_SAME;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B210_GET_TRAN_INFO();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_INT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSPDQ);
        IBS.init(SCCGWA, LNRRCVD);
        LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNCSPLTN.CTA_NO;
        LNCSPDQ.COMM_DATA.TR_VAL_DATE = LNCSPLTN.TRAN_DT;
        S000_CALL_LNZSPDQ();
        if (pgmRtn) return;
        T000_READ_BALZ();
        if (pgmRtn) return;
        if (LNCSPLTN.INT_MOD != '0') {
            IBS.init(SCCGWA, LNCSPDQ);
            LNCSPDQ.COMM_DATA.CTA_INFO.CTA_NO = LNCSPLTN.CTA_NO;
            LNCSPDQ.COMM_DATA.TR_VAL_DATE = LNCSPLTN.TRAN_DT;
            S000_CALL_LNZSPDQ();
            if (pgmRtn) return;
            T000_READ_BALZ();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.CTA_INFO.D_I_AMT);
            CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_I_AMT);
            CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL20);
            WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT = LNCSPDQ.COMM_DATA.CTA_INFO.D_I_AMT + LNRBALZ.LOAN_BALL20;
            WS_TRAN_DATA.WS_INT_BAL.WS_OI_INT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_I_AMT - LNRBALZ.LOAN_BALL20;
            WS_TRAN_DATA.WS_INT_BAL.WS_O_INT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_O_AMT;
            WS_TRAN_DATA.WS_INT_BAL.WS_L_INT = LNCSPDQ.COMM_DATA.TOT_AMTS.TOT_L_AMT;
        }
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.CTA_INFO.P_P_BAL);
        CEP.TRC(SCCGWA, LNCSPDQ.COMM_DATA.CTA_INFO.O_P_BAL);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_INT_BAL.WS_OI_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_INT_BAL.WS_O_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_INT_BAL.WS_L_INT);
        if (LNCSPLTN.INT_MOD != '0') {
            B210_GET_PAY_INT();
            if (pgmRtn) return;
        }
    }
    public void B210_GET_PAY_INT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        WS_MAX_DATE = LNCSPLTN.TRAN_DT;
        WS_MIN_DATE = LNCSPLTN.PACK_DT;
        LNRTRAN.KEY.REC_FLAG = 'B';
        LNRTRAN.KEY.TXN_TYP = 'T';
        LNRTRAN.TRAN_STS = 'N';
        T000_CAL_PAY_INT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_PAY_INT.WS_PAY_N_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_PAY_INT.WS_PAY_O_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_PAY_INT.WS_PAY_L_INT);
    }
    public void B210_GET_TRAN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        LNCTRANM.REC_DATA.KEY.TR_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.FUNC = '4';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        if (LNCCONTM.REC_DATA.LAST_TX_SEQ != LNCTRANM.REC_DATA.TR_SEQ) {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_TR_SEQ_NOTMATCH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_LAST_F_VAL_DT = LNCTRANM.REC_DATA.LAST_F_VAL_DATE;
        IBS.CPY2CLS(SCCGWA, LNCTRANM.REC_DATA.TR_DATA, WS_TRAN_DATA);
        WS_CTL_STSW = LNCTRANM.REC_DATA.LOAN_STSW;
    }
    public void B300_TRANSFER_AMT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (LNCSPLTN.INT_MOD != '0') {
                B330_TRANS_INT_PROC();
                if (pgmRtn) return;
            }
            if (LNCSPLTN.PRIN_MOD == '1') {
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_NP_AMT = LNCSPDQ.COMM_DATA.CTA_INFO.P_P_BAL;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_OP_AMT = LNCSPDQ.COMM_DATA.CTA_INFO.O_P_BAL;
            }
            B310_WRITE_PACK_INFO();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_TOT_INT);
        if (WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_TOT_INT > 0) {
            B340_TRANS_OUT_INT();
            if (pgmRtn) return;
            B340_UPDATE_PACK();
            if (pgmRtn) return;
        }
        B350_WRITE_CTPY_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (LNCTRANM.REC_DATA.KEY.TR_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_REV_FLG = 'Y';
                B360_REV_INT_PROC();
                if (pgmRtn) return;
            }
            B370_UPDATE_STATUS_PROC_R();
            if (pgmRtn) return;
        } else {
            B370_UPDATE_STATUS_PROC();
            if (pgmRtn) return;
        }
        B390_DR_ARAMT_PROC();
        if (pgmRtn) return;
        B395_OAMT_PROC();
        if (pgmRtn) return;
        B390_EVENT_PROC();
        if (pgmRtn) return;
    }
    public void B310_WRITE_PACK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPACK);
        LNRPACK.KEY.BTH_PK = LNCSPLTN.BTH_PK;
        LNRPACK.PK_NM = LNCSPLTN.PK_NM;
        LNRPACK.P_AC_TYP = LNCSPLTN.P_AC_TYP;
        LNRPACK.P_AC = LNCSPLTN.P_AC;
        LNRPACK.I_AC_TYP = LNCSPLTN.I_AC_TYP;
        LNRPACK.I_AC = LNCSPLTN.I_AC;
        LNRPACK.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPACK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRPACK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPACK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPACK_RD = new DBParm();
        LNTPACK_RD.TableName = "LNTPACK";
        IBS.READ(SCCGWA, LNRPACK, LNTPACK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            LNTPACK_RD = new DBParm();
            LNTPACK_RD.TableName = "LNTPACK";
            LNTPACK_RD.errhdl = true;
            IBS.WRITE(SCCGWA, LNRPACK, LNTPACK_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DUPKEY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B330_TRANS_INT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_INT_BAL.WS_OI_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_INT_BAL.WS_O_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_INT_BAL.WS_L_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_N_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_O_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_L_INT);
        CEP.TRC(SCCGWA, LNCSPLTN.N_INT);
        CEP.TRC(SCCGWA, LNCSPLTN.O_INT);
        CEP.TRC(SCCGWA, LNCSPLTN.L_INT);
        if (LNCSPLTN.PACK_DT == SCCGWA.COMM_AREA.AC_DATE) {
            if (LNCSPLTN.INT_MOD == '1') {
                WS_TRAN_DATA.WS_OWN_INT.WS_OWN_N_INT = WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT + WS_TRAN_DATA.WS_INT_BAL.WS_OI_INT;
                WS_TRAN_DATA.WS_OWN_INT.WS_OWN_O_INT = WS_TRAN_DATA.WS_INT_BAL.WS_O_INT;
                WS_TRAN_DATA.WS_OWN_INT.WS_OWN_L_INT = WS_TRAN_DATA.WS_INT_BAL.WS_L_INT;
            } else {
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT = WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_ON_INT = WS_TRAN_DATA.WS_INT_BAL.WS_OI_INT - WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_N_INT;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT = WS_TRAN_DATA.WS_INT_BAL.WS_O_INT - WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_O_INT;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT = WS_TRAN_DATA.WS_INT_BAL.WS_L_INT - WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_L_INT;
            }
        } else {
            if (LNCSPLTN.INT_MOD == '1') {
                if (LNCSPLTN.N_INT > WS_TRAN_DATA.WS_PAY_INT.WS_PAY_N_INT) {
                    WS_TRAN_DATA.WS_OWN_INT.WS_OWN_N_INT = LNCSPLTN.N_INT - WS_TRAN_DATA.WS_PAY_INT.WS_PAY_N_INT - WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_N_INT;
                    WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT = WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT + WS_TRAN_DATA.WS_INT_BAL.WS_OI_INT - WS_TRAN_DATA.WS_OWN_INT.WS_OWN_N_INT - WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_N_INT;
                    if (WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT > WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT) {
                        WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_ON_INT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT - WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT;
                        WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT = WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT;
                    }
                } else {
                    WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_N_INT = WS_TRAN_DATA.WS_PAY_INT.WS_PAY_N_INT - LNCSPLTN.N_INT;
                    WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT = WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT;
                    WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_ON_INT = WS_TRAN_DATA.WS_INT_BAL.WS_OI_INT;
                }
                if (LNCSPLTN.O_INT > WS_TRAN_DATA.WS_PAY_INT.WS_PAY_O_INT) {
                    WS_TRAN_DATA.WS_OWN_INT.WS_OWN_O_INT = LNCSPLTN.O_INT - WS_TRAN_DATA.WS_PAY_INT.WS_PAY_O_INT - WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_O_INT;
                    WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT = WS_TRAN_DATA.WS_INT_BAL.WS_O_INT - WS_TRAN_DATA.WS_OWN_INT.WS_OWN_O_INT - WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_O_INT;
                } else {
                    WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_O_INT = WS_TRAN_DATA.WS_PAY_INT.WS_PAY_O_INT - LNCSPLTN.O_INT;
                    WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT = WS_TRAN_DATA.WS_INT_BAL.WS_O_INT;
                }
                if (LNCSPLTN.L_INT > WS_TRAN_DATA.WS_PAY_INT.WS_PAY_L_INT) {
                    WS_TRAN_DATA.WS_OWN_INT.WS_OWN_L_INT = LNCSPLTN.L_INT - WS_TRAN_DATA.WS_PAY_INT.WS_PAY_L_INT - WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_L_INT;
                    WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT = WS_TRAN_DATA.WS_INT_BAL.WS_L_INT - WS_TRAN_DATA.WS_OWN_INT.WS_OWN_L_INT - WS_TRAN_DATA.WS_CTPY_INT.WS_CTPY_L_INT;
                } else {
                    WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_L_INT = WS_TRAN_DATA.WS_PAY_INT.WS_PAY_L_INT - LNCSPLTN.L_INT;
                    WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT = WS_TRAN_DATA.WS_INT_BAL.WS_L_INT;
                }
            } else {
                WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_N_INT = WS_TRAN_DATA.WS_PAY_INT.WS_PAY_N_INT;
                WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_O_INT = WS_TRAN_DATA.WS_PAY_INT.WS_PAY_O_INT;
                WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_L_INT = WS_TRAN_DATA.WS_PAY_INT.WS_PAY_L_INT;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT = WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_ON_INT = WS_TRAN_DATA.WS_INT_BAL.WS_OI_INT;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT = WS_TRAN_DATA.WS_INT_BAL.WS_O_INT;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT = WS_TRAN_DATA.WS_INT_BAL.WS_L_INT;
            }
        }
        WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_TOT_INT = WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_N_INT + WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_O_INT + WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_L_INT;
        WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_ON_INT + WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT;
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_N_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_O_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_L_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_ON_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT);
    }
    public void B340_TRANS_OUT_INT() throws IOException,SQLException,Exception {
        if (LNCSPLTN.I_AC_TYP.equalsIgnoreCase("02")) {
            B341_TX_IA_CR_PROC();
            if (pgmRtn) return;
        } else if (LNCSPLTN.I_AC_TYP.equalsIgnoreCase("03")) {
            B342_TX_IB_CR_PROC();
            if (pgmRtn) return;
        } else {
            B343_TX_DD_CR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B340_UPDATE_PACK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPACK);
        LNRPACK.KEY.BTH_PK = LNCSPLTN.BTH_PK;
        LNTPACK_RD = new DBParm();
        LNTPACK_RD.TableName = "LNTPACK";
        LNTPACK_RD.upd = true;
        IBS.READ(SCCGWA, LNRPACK, LNTPACK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0298;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            LNRPACK.T_INT_AMT += WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_N_INT;
            LNRPACK.T_O_INT += WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_O_INT;
            LNRPACK.T_L_INT += WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_L_INT;
            LNRPACK.TOT_AMT += WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_TOT_INT;
        } else {
            LNRPACK.T_INT_AMT -= WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_N_INT;
            LNRPACK.T_O_INT -= WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_O_INT;
            LNRPACK.T_L_INT -= WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_L_INT;
            LNRPACK.TOT_AMT -= WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_TOT_INT;
        }
        LNRPACK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRPACK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNTPACK_RD = new DBParm();
        LNTPACK_RD.TableName = "LNTPACK";
        IBS.REWRITE(SCCGWA, LNRPACK, LNTPACK_RD);
    }
    public void B341_TX_IA_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = LNCSPLTN.CTA_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        AICUUPIA.DATA.PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        AICUUPIA.DATA.AC_NO = LNCSPLTN.I_AC;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSPLTN.CCY;
        AICUUPIA.DATA.AMT = WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_TOT_INT;
        AICUUPIA.DATA.VALUE_DATE = LNCSPLTN.TRAN_DT;
        AICUUPIA.DATA.RVS_NO = " ";
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B342_TX_IB_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = LNCSPLTN.I_AC;
        IBCPOSTA.CCY = LNCSPLTN.CCY;
        IBCPOSTA.AMT = WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_TOT_INT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSPLTN.TRAN_DT;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.OUR_REF = LNCSPLTN.CTA_NO;
        IBCPOSTA.THR_REF = LNCSPLTN.CTA_NO;
        if (WS_TRANS_FLG == 'P') {
            IBCPOSTA.TX_MMO = K_MMO_P;
        } else if (WS_TRANS_FLG == 'I') {
            IBCPOSTA.TX_MMO = K_MMO_I;
        } else if (WS_TRANS_FLG == 'A') {
            IBCPOSTA.TX_MMO = K_MMO_A;
        } else {
        }
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZCRAC();
            if (pgmRtn) return;
        }
    }
    public void B343_TX_DD_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.BANK_CR_FLG = 'Y';
        DDCUCRAC.TX_TYPE = 'T';
        if (LNCSPLTN.I_AC_TYP.equalsIgnoreCase("05") 
            || LNCSPLTN.I_AC_TYP.equalsIgnoreCase("06")) {
            DDCUCRAC.CARD_NO = LNCSPLTN.I_AC;
        } else {
            DDCUCRAC.AC = LNCSPLTN.I_AC;
        }
        DDCUCRAC.CCY = LNCSPLTN.CCY;
        DDCUCRAC.TX_AMT = WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_TOT_INT;
        DDCUCRAC.VAL_DATE = LNCSPLTN.TRAN_DT;
        if (WS_TRANS_FLG == 'P') {
            DDCUCRAC.TX_MMO = K_MMO_P;
        } else if (WS_TRANS_FLG == 'I') {
            DDCUCRAC.TX_MMO = K_MMO_I;
        } else if (WS_TRANS_FLG == 'A') {
            DDCUCRAC.TX_MMO = K_MMO_A;
        } else {
        }
        DDCUCRAC.OTHER_AC = LNCSPLTN.CTA_NO;
        DDCUCRAC.OTHER_CCY = LNCSPLTN.CCY;
        DDCUCRAC.OTHER_AMT = WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_TOT_INT;
        if (DDCUCRAC.TX_AMT != 0) {
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B360_REV_INT_PROC() throws IOException,SQLException,Exception {
        T000_READUPD_BALZ();
        if (pgmRtn) return;
        if (WS_CTL_STSW == null) WS_CTL_STSW = "";
        JIBS_tmp_int = WS_CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_CTL_STSW += " ";
        if (WS_CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("0")) {
            LNRBALZ.LOAN_BALL91 = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 - WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT - WS_TRAN_DATA.WS_INT_BAL.WS_OI_INT;
            LNRBALZ.LOAN_BALL92 = LNRBALZ.LOAN_BALL22 + LNRBALZ.LOAN_BALL42 - WS_TRAN_DATA.WS_INT_BAL.WS_O_INT;
            LNRBALZ.LOAN_BALL93 = LNRBALZ.LOAN_BALL52 - WS_TRAN_DATA.WS_INT_BAL.WS_L_INT;
        } else {
            LNRBALZ.LOAN_BALL93 = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22 + LNRBALZ.LOAN_BALL42 + LNRBALZ.LOAN_BALL52 - WS_TRAN_DATA.WS_INT_BAL.WS_NI_INT - WS_TRAN_DATA.WS_INT_BAL.WS_OI_INT - WS_TRAN_DATA.WS_INT_BAL.WS_O_INT - WS_TRAN_DATA.WS_INT_BAL.WS_L_INT;
        }
        T000_REWRITE_BALZ();
        if (pgmRtn) return;
    }
    public void B370_UPDATE_STATUS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        LNCICTLM.FUNC = '4';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        WS_CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        if (LNCSPLTN.PRIN_MOD == '1') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 37 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(37 + 1 - 1);
        }
        if (LNCSPLTN.INT_MOD != '0') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 38 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(38 + 1 - 1);
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1") 
            && LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 39 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(39 + 1 - 1);
        }
        if (WS_TRAN_DATA.WS_OWN_INT.WS_OWN_N_INT > 0 
            || WS_TRAN_DATA.WS_OWN_INT.WS_OWN_O_INT > 0 
            || WS_TRAN_DATA.WS_OWN_INT.WS_OWN_L_INT > 0) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 44 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(44 + 1 - 1);
        }
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.REC_DATA.TR_SEQ += 1 + LNCCONTM.REC_DATA.LAST_TX_SEQ;
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.REC_DATA.DUE_DT = 0;
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_VAL_DTE = LNCSPLTN.TRAN_DT;
        LNCTRANM.REC_DATA.TR_REV_DATE = 0;
        LNCTRANM.REC_DATA.TR_REV_JRN_NO = 0;
        LNCTRANM.REC_DATA.OS_BAL = LNCSPLTN.LOAN_BAL;
        LNCTRANM.REC_DATA.P_REC_AMT += WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_NP_AMT + WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_OP_AMT;
        LNCTRANM.REC_DATA.I_REC_AMT += WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT + WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_ON_INT;
        LNCTRANM.REC_DATA.O_REC_AMT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT;
        LNCTRANM.REC_DATA.L_REC_AMT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT;
        LNCTRANM.REC_DATA.I_AMT = WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_N_INT;
        LNCTRANM.REC_DATA.O_AMT = WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_O_INT;
        LNCTRANM.REC_DATA.L_AMT = WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_L_INT;
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.LAST_F_VAL_DATE = LNCCONTM.REC_DATA.LAST_F_VAL_DATE;
        LNCTRANM.REC_DATA.ACTL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.TXN_CCY = LNCSPLTN.CCY;
        LNCTRANM.REC_DATA.LOAN_STSW = WS_CTL_STSW;
        LNCTRANM.REC_DATA.TR_DATA = IBS.CLS2CPY(SCCGWA, WS_TRAN_DATA);
        if (WS_TRANS_FLG == 'P') {
            LNCTRANM.REC_DATA.TR_MMO = K_MMO_P;
        } else if (WS_TRANS_FLG == 'I') {
            LNCTRANM.REC_DATA.TR_MMO = K_MMO_I;
        } else if (WS_TRANS_FLG == 'A') {
            LNCTRANM.REC_DATA.TR_MMO = K_MMO_A;
        } else {
        }
        LNCTRANM.REC_DATA.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        LNCTRANM.REC_DATA.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCTRANM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        LNCCONTM.FUNC = '4';
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        LNCCONTM.REC_DATA.LAST_F_VAL_DATE = LNCSPLTN.TRAN_DT;
        LNCCONTM.REC_DATA.LAST_TX_DT = LNCSPLTN.TRAN_DT;
        LNCCONTM.REC_DATA.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCCONTM.REC_DATA.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNCCONTM.REC_DATA.LAST_TX_SEQ += 1;
        LNCCONTM.FUNC = '2';
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B370_UPDATE_STATUS_PROC_R() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        LNCICTLM.FUNC = '4';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCSPLTN.PRIN_MOD == '1') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 37 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(37 + 1 - 1);
        }
        if (LNCSPLTN.INT_MOD != '0') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 38 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(38 + 1 - 1);
        }
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("0") 
            || LNCICTLM.REC_DATA.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("0")) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 39 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(39 + 1 - 1);
        }
        if (WS_TRAN_DATA.WS_OWN_INT.WS_OWN_N_INT > 0 
            || WS_TRAN_DATA.WS_OWN_INT.WS_OWN_O_INT > 0 
            || WS_TRAN_DATA.WS_OWN_INT.WS_OWN_L_INT > 0) {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 44 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(44 + 1 - 1);
        }
        if (WS_REV_FLG == 'Y') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 50 - 1) + "1" + LNCICTLM.REC_DATA.CTL_STSW.substring(50 + 1 - 1);
        }
        LNCICTLM.FUNC = '2';
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCTRANM.FUNC = '2';
        LNCTRANM.REC_DATA.TRAN_STS = 'R';
        LNCTRANM.REC_DATA.TR_REV_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.TR_REV_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCCONTM);
        LNCCONTM.FUNC = '4';
        LNCCONTM.REC_DATA.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
        WS_LAST_TX_SEQ = LNCCONTM.REC_DATA.LAST_TX_SEQ;
        WS_LAST_TX_SEQ -= 1;
        LNCCONTM.FUNC = '2';
        LNCCONTM.REC_DATA.LAST_TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        LNCCONTM.REC_DATA.LAST_F_VAL_DATE = WS_LAST_F_VAL_DT;
        LNCCONTM.REC_DATA.LAST_TX_SEQ = WS_LAST_TX_SEQ;
        S000_CALL_LNZCONTM();
        if (pgmRtn) return;
    }
    public void B390_DR_ARAMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        if (LNCSPLTN.P_AC == null) LNCSPLTN.P_AC = "";
        JIBS_tmp_int = LNCSPLTN.P_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) LNCSPLTN.P_AC += " ";
        if (AICUUPIA.DATA.AC_NO == null) AICUUPIA.DATA.AC_NO = "";
        JIBS_tmp_int = AICUUPIA.DATA.AC_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICUUPIA.DATA.AC_NO += " ";
        AICUUPIA.DATA.AC_NO = LNCSPLTN.P_AC.substring(0, 9) + AICUUPIA.DATA.AC_NO.substring(9);
        if (AICUUPIA.DATA.AC_NO == null) AICUUPIA.DATA.AC_NO = "";
        JIBS_tmp_int = AICUUPIA.DATA.AC_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICUUPIA.DATA.AC_NO += " ";
        if (K_TF_ITM == null) K_TF_ITM = "";
        JIBS_tmp_int = K_TF_ITM.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) K_TF_ITM += " ";
        AICUUPIA.DATA.AC_NO = AICUUPIA.DATA.AC_NO.substring(0, 10 - 1) + K_TF_ITM + AICUUPIA.DATA.AC_NO.substring(10 + 10 - 1);
        if (AICUUPIA.DATA.AC_NO == null) AICUUPIA.DATA.AC_NO = "";
        JIBS_tmp_int = AICUUPIA.DATA.AC_NO.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) AICUUPIA.DATA.AC_NO += " ";
        JIBS_tmp_str[0] = "" + K_TF_SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<9-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        AICUUPIA.DATA.AC_NO = AICUUPIA.DATA.AC_NO.substring(0, 20 - 1) + JIBS_tmp_str[0] + AICUUPIA.DATA.AC_NO.substring(20 + 6 - 1);
        CEP.TRC(SCCGWA, "B390-DR-ARAMT-PROC");
        CEP.TRC(SCCGWA, WS_TF_AC);
        AICUUPIA.DATA.CCY = LNCCONTM.REC_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_NP_AMT + WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_OP_AMT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCSPLTN.INT_MOD == '2' 
            && LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("0")) {
            AICUUPIA.DATA.AMT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_NP_AMT + WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_OP_AMT + LNCSPLTN.N_INT + LNCSPLTN.O_INT + LNCSPLTN.L_INT;
        }
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_NP_AMT);
        CEP.TRC(SCCGWA, WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_OP_AMT);
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.PAY_MAN = LNCSPLTN.PK_NM;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B395_OAMT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL24);
        CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL48);
        CEP.TRC(SCCGWA, LNRBALZ.LOAN_BALL58);
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        T000_READUPD_BALZ();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_INT_N = LNRBALZ.LOAN_BALL24 - WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT;
            if (WS_INT_N < 0) {
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT = WS_INT_N * -1;
                WS_AO_INT += LNRBALZ.LOAN_BALL24;
            } else {
                WS_AO_INT += WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT = 0;
                LNRBALZ.LOAN_BALL24 -= WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT;
                LNRBALZ.LOAN_BALL39 = LNRBALZ.LOAN_BALL24;
            }
            WS_INT_O = LNRBALZ.LOAN_BALL48 - WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT;
            if (WS_INT_O < 0) {
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT = WS_INT_O * -1;
                WS_AO_INT += LNRBALZ.LOAN_BALL48;
            } else {
                WS_AO_INT += WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT = 0;
                LNRBALZ.LOAN_BALL48 -= WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_OA_INT;
                LNRBALZ.LOAN_BALL49 = LNRBALZ.LOAN_BALL48;
            }
            WS_INT_L = LNRBALZ.LOAN_BALL58 - WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT;
            if (WS_INT_L < 0) {
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT = WS_INT_L * -1;
                WS_AO_INT += LNRBALZ.LOAN_BALL58;
            } else {
                WS_AO_INT += WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT;
                WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT = 0;
                LNRBALZ.LOAN_BALL58 -= WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT;
                LNRBALZ.LOAN_BALL59 = LNRBALZ.LOAN_BALL58;
            }
        }
        T000_REWRITE_BALZ();
        if (pgmRtn) return;
    }
    public void B390_EVENT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIGVCY);
        LNCIGVCY.DATA.CNTR_TYPE = "CLDD";
        LNCIGVCY.DATA.CTA_NO = LNCSPLTN.CTA_NO;
        LNCIGVCY.DATA.SUB_CTA_NO = 0;
        LNCIGVCY.DATA.PROD_CODE_OLD = LNCSPLTN.PROD_CD;
        LNCIGVCY.DATA.BR_OLD = LNCSPLTN.BR;
        LNCIGVCY.DATA.CCY_INFO[1-1].CCY = LNCSPLTN.CCY;
        LNCIGVCY.DATA.VALUE_DATE = LNCSPLTN.TRAN_DT;
        LNCIGVCY.DATA.CI_NO = LNCSPLTN.CI_NO;
        LNCIGVCY.DATA.STATUS = LNCICTLM.REC_DATA.CTL_STSW;
        LNCIGVCY.DATA.EVENT_CODE = "TF";
        LNCIGVCY.DATA.EFF_DAYS = 0;
        LNCIGVCY.DATA.BAL_NORMAL = LNCSPLTN.LOAN_BAL;
        LNCIGVCY.DATA.AMT_INFO[1-1].AMT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_NP_AMT;
        LNCIGVCY.DATA.AMT_INFO[5-1].AMT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_OP_AMT;
        LNCIGVCY.DATA.AMT_INFO[13-1].AMT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_N_INT;
        LNCIGVCY.DATA.AMT_INFO[15-1].AMT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_O_INT;
        LNCIGVCY.DATA.AMT_INFO[17-1].AMT = WS_AO_INT;
        LNCIGVCY.DATA.AMT_INFO[21-1].AMT = WS_TRAN_DATA.WS_TRANSF_AMT.WS_TF_L_INT;
        LNCIGVCY.DATA.AMT_INFO[63-1].AMT = WS_TRAN_DATA.WS_TF_OUT_INT.WS_TO_TOT_INT;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCSPLTN.INT_MOD == '2' 
            && LNCICTLM.REC_DATA.CTL_STSW.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("0")) {
            LNCIGVCY.DATA.AMT_INFO[69-1].AMT = LNCSPLTN.N_INT + LNCSPLTN.O_INT + LNCSPLTN.L_INT;
            LNCIGVCY.DATA.AMT_INFO[13-1].AMT = LNCIGVCY.DATA.AMT_INFO[13-1].AMT - LNCSPLTN.N_INT;
            LNCIGVCY.DATA.AMT_INFO[15-1].AMT = LNCIGVCY.DATA.AMT_INFO[15-1].AMT - LNCSPLTN.O_INT;
            LNCIGVCY.DATA.AMT_INFO[21-1].AMT = LNCIGVCY.DATA.AMT_INFO[21-1].AMT - LNCSPLTN.L_INT;
        }
        S000_CALL_LNZIGVCY();
        if (pgmRtn) return;
    }
    public void B350_WRITE_CTPY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        LNRCTPY.KEY.TR_TYP = '1';
        LNRCTPY.KEY.BTH_PK = LNCSPLTN.BTH_PK;
        LNRCTPY.KEY.TRAN_DATE = LNCSPLTN.TRAN_DT;
        LNRCTPY.PRIN_MODE = LNCSPLTN.PRIN_MOD;
        LNRCTPY.INT_MODE = LNCSPLTN.INT_MOD;
        LNRCTPY.PACK_DT = LNCSPLTN.PACK_DT;
        LNRCTPY.PRIN_BAL = LNCSPLTN.P_BAL;
        LNRCTPY.TRAN_AMT = LNCSPLTN.LOAN_BAL;
        LNRCTPY.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW;
        LNRCTPY.I_REC_AMT = WS_TRAN_DATA.WS_OWN_INT.WS_OWN_N_INT;
        LNRCTPY.L_REC_AMT = WS_TRAN_DATA.WS_OWN_INT.WS_OWN_L_INT;
        LNRCTPY.O_REC_AMT = WS_TRAN_DATA.WS_OWN_INT.WS_OWN_O_INT;
        LNRCTPY.I_PAY_AMT = 0;
        LNRCTPY.L_PAY_AMT = 0;
        LNRCTPY.O_PAY_AMT = 0;
        LNRCTPY.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCTPY.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRCTPY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCTPY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            LNTCTPY_RD = new DBParm();
            LNTCTPY_RD.TableName = "LNTCTPY";
            LNTCTPY_RD.upd = true;
            IBS.READ(SCCGWA, LNRCTPY, LNTCTPY_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0356;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            LNTCTPY_RD = new DBParm();
            LNTCTPY_RD.TableName = "LNTCTPY";
            IBS.DELETE(SCCGWA, LNRCTPY, LNTCTPY_RD);
        } else {
            LNTCTPY_RD = new DBParm();
            LNTCTPY_RD.TableName = "LNTCTPY";
            IBS.WRITE(SCCGWA, LNRCTPY, LNTCTPY_RD);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
                WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_DUPKEY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B600_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRAGRE);
        IBS.init(SCCGWA, LNRAGRE);
        LNCRAGRE.FUNC = 'I';
        LNRAGRE.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        S000_CALL_LNZRAGRE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC = LNRAGRE.PAPER_NO;
        BPCPFHIS.DATA.ACO_AC = LNCSPLTN.CTA_NO;
        BPCPFHIS.DATA.CI_NO = LNCSPLTN.CI_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.OTH_AC = LNCSPLTN.I_AC;
        BPCPFHIS.DATA.TX_CCY = LNCSPLTN.CCY;
        BPCPFHIS.DATA.TX_VAL_DT = LNCSPLTN.TRAN_DT;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.TX_AMT = LNCSPLTN.LOAN_BAL;
        if (WS_TRANS_FLG == 'P') {
            BPCPFHIS.DATA.TX_MMO = K_MMO_P;
        } else if (WS_TRANS_FLG == 'I') {
            BPCPFHIS.DATA.TX_MMO = K_MMO_I;
        } else if (WS_TRANS_FLG == 'A') {
            BPCPFHIS.DATA.TX_MMO = K_MMO_A;
        } else {
        }
        BPCPFHIS.DATA.TX_MAKER = SCCGWA.COMM_AREA.TL_ID;
        BPCPFHIS.DATA.PRDMO_CD = "CLDD";
        BPCPFHIS.DATA.FMT_CODE = "LNY02";
        BPCPFHIS.DATA.FMT_LEN = 673;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCSPLTN);
        BPCPFHIS.DATA.PROD_CD = LNCSPLTN.PROD_CD;
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void T000_CAL_PAY_INT() throws IOException,SQLException,Exception {
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.set = "WS-TRN-N-INT=NVL(SUM(I_AMT),0),WS-TRN-O-INT=NVL(SUM(O_AMT),0),WS-TRN-L-INT=NVL(SUM(L_AMT),0)";
        LNTTRAN_RD.eqWhere = "CONTRACT_NO";
        LNTTRAN_RD.where = "SUB_CTA_NO = :LNRTRAN.KEY.SUB_CTA_NO "
            + "AND REC_FLAG = :LNRTRAN.KEY.REC_FLAG "
            + "AND TXN_TYP = :LNRTRAN.KEY.TXN_TYP "
            + "AND TRAN_STS = :LNRTRAN.TRAN_STS "
            + "AND ( TR_CODE = 4000 "
            + "OR TR_CODE = 4100 "
            + "OR TR_CODE = 4500 "
            + "OR TR_CODE = 4600 ) "
            + "AND TR_DATE > :WS_MIN_DATE "
            + "AND TR_DATE <= :WS_MAX_DATE";
        IBS.GROUP(SCCGWA, LNRTRAN, this, LNTTRAN_RD);
        WS_TRAN_DATA.WS_PAY_INT.WS_PAY_N_INT = WS_TRN_N_INT;
        WS_TRAN_DATA.WS_PAY_INT.WS_PAY_O_INT = WS_TRN_O_INT;
        WS_TRAN_DATA.WS_PAY_INT.WS_PAY_L_INT = WS_TRN_L_INT;
    }
    public void T000_READ_LNTCTPY() throws IOException,SQLException,Exception {
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        LNTCTPY_RD.where = "CONTRACT_NO = :LNRCTPY.KEY.CONTRACT_NO "
            + "AND TR_TYP = :LNRCTPY.KEY.TR_TYP "
            + "AND INT_MODE = :LNRCTPY.INT_MODE";
        LNTCTPY_RD.fst = true;
        LNTCTPY_RD.order = "TRAN_DATE DESC";
        IBS.READ(SCCGWA, LNRCTPY, this, LNTCTPY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_LN0356;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTRCVD() throws IOException,SQLException,Exception {
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "TERM DESC";
        IBS.READ(SCCGWA, LNRRCVD, LNTRCVD_RD);
    }
    public void T000_READ_BALZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_BALL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_BALZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCSPLTN.CTA_NO;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        LNTBALZ_RD.upd = true;
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_BALL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BALZ() throws IOException,SQLException,Exception {
        LNRBALZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRBALZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.REWRITE(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CREDIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC, true);
    }
    public void S000_CALL_LNZIGVCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-GEN-VCY", LNCIGVCY);
        if (LNCIGVCY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCIGVCY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ, true);
        if (LNCSPDQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCSPDQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCONTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CONT-MAINT", LNCCONTM);
        if (LNCCONTM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCCONTM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_LNZRAGRE() throws IOException,SQLException,Exception {
        LNCRAGRE.REC_PTR = LNRAGRE;
        LNCRAGRE.REC_LEN = 203;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTAGRE", LNCRAGRE);
        if (LNCRAGRE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRAGRE.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
