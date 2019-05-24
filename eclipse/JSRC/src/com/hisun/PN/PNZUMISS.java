package com.hisun.PN;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class PNZUMISS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm PNTBCC_RD;
    DBParm PNTGBCC_RD;
    boolean pgmRtn = false;
    String CPN_F_BPZFFTXI = "BP-F-F-TX-INFO";
    String CPN_F_BPZFCALF = "BP-F-F-CAL-FEE";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_U_BPZPQORG = "BP-P-INQ-ORG";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    char WS_TABLE_FLG = ' ';
    char WS_TABLE_GBCC_FLG = ' ';
    int WS_ISS_BR = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICACCU CICACCU = new CICACCU();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICQACRI CICQACRI = new CICQACRI();
    PNRBCC PNRBCC = new PNRBCC();
    PNRGBCC PNRGBCC = new PNRGBCC();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PNCUMISS PNCUMISS;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCGCFEE BPCGCFEE;
    BPCGPFEE BPCGPFEE;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, PNCUMISS PNCUMISS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUMISS = PNCUMISS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZUMISS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGCFEE = new BPCGCFEE();
        IBS.init(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, GWA_BP_AREA.FEE_AREA.FEE_DATA_PTR, BPCGCFEE);
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B201_INPUT_CHK_PROC();
        if (pgmRtn) return;
        if (PNCUMISS.DATA.FUNC == 'L') {
            B220_COST_FEE_PROC();
            if (pgmRtn) return;
        }
        B202_BCC_MST_PROC();
        if (pgmRtn) return;
        B203_GBCC_MST_PROC();
        if (pgmRtn) return;
        B204_BP_HISTORY();
        if (pgmRtn) return;
    }
    public void B201_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PNCUMISS.DATA.FUNC);
        if (PNCUMISS.DATA.FUNC == 'L') {
            if (PNCUMISS.DATA.LOS_DATE == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_LOSDT_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-LOS-DATE=" + PNCUMISS.DATA.LOS_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.ISS_DATE == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DT_MUST_IN, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-ISS-DATE=" + PNCUMISS.DATA.ISS_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.LOS_ADDR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_LOSAD_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-LOS-ADDR=" + PNCUMISS.DATA.LOS_ADDR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.APP_NM.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_APNM_MUST_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-APP-NM=" + PNCUMISS.DATA.APP_NM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.APP_ADDR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_APADR_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-APP-ADDR=" + PNCUMISS.DATA.APP_ADDR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.APP_TEL.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_APTEL_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-APP-TEL=" + PNCUMISS.DATA.APP_TEL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, PNCUMISS.DATA.ID_TYPE);
            if (PNCUMISS.DATA.ID_TYPE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_IDTYP_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-ID-TYPE=" + PNCUMISS.DATA.ID_TYPE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.ID_NO.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_IDNO_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-ID-NO=" + PNCUMISS.DATA.ID_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.REASON.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RSN_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-REASON=" + PNCUMISS.DATA.REASON;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, PNCUMISS.DATA.BCC_CINO);
        if (PNCUMISS.DATA.FUNC == 'S') {
            if (PNCUMISS.DATA.STP_DATE == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STPDT_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-STP-DATE=" + PNCUMISS.DATA.STP_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.ISS_DATE == 0) {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DT_MUST_IN);
            }
            if (PNCUMISS.DATA.STP_RENO.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STPRENO_MUST_INPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-STP-RENO=" + PNCUMISS.DATA.STP_RENO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.STP_RSN.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RSN_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-STP-RSN=" + PNCUMISS.DATA.STP_RSN;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNCUMISS.DATA.FUNC == 'R') {
            if (PNCUMISS.DATA.RM_NM.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RMNM_MUST_INPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-RM-NM=" + PNCUMISS.DATA.RM_NM;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.RM_ADDR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RMADDR_MUST_INPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-RM-ADDR=" + PNCUMISS.DATA.RM_ADDR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.RM_TEL.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RMTEL_MUST_INPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-RM-TEL=" + PNCUMISS.DATA.RM_TEL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.RM_IDTY.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_IDTYP_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-RM-IDTY=" + PNCUMISS.DATA.RM_IDTY;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.RM_IDNO.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_IDNO_NOT_IPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-RM-IDNO=" + PNCUMISS.DATA.RM_IDNO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUMISS.DATA.RM_SMR.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_RMSMR_MUST_INPT, PNCUMISS.RC);
                WS_ERR_INFO = "UMISS-RM-SMR=" + PNCUMISS.DATA.RM_SMR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, PNRBCC);
        PNRBCC.KEY.BILL_KND = PNCUMISS.DATA.KND;
        PNRBCC.KEY.BILL_NO = PNCUMISS.DATA.CC_NO;
        CEP.TRC(SCCGWA, PNCUMISS.DATA.KND);
        CEP.TRC(SCCGWA, PNCUMISS.DATA.CC_NO);
        T000_READ_PNTBCC_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TABLE_FLG);
        if (WS_TABLE_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_BCC_NOT_FND, PNCUMISS.RC);
            WS_ERR_INFO = "UMISS-KND =" + PNCUMISS.DATA.KND + ",UMISS-CC-NO =" + PNCUMISS.DATA.CC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRBCC.ISS_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ISS_BR = PNRBCC.ISS_BR;
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSBR_NOT_COMM, PNCUMISS.RC);
            WS_ERR_INFO = "BCC-ISS-BR=" + WS_ISS_BR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PNRBCC.ISS_DT);
        CEP.TRC(SCCGWA, PNRBCC.AMT);
        CEP.TRC(SCCGWA, PNCUMISS.DATA.ISS_DATE);
        CEP.TRC(SCCGWA, PNCUMISS.DATA.ISS_AMT);
        if (PNCUMISS.DATA.ISS_DATE != PNRBCC.ISS_DT) {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISS_DATE_ERR, PNCUMISS.RC);
            WS_ERR_INFO = "UMISS-ISS-DATE=" + PNCUMISS.DATA.ISS_DATE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUMISS.DATA.ISS_AMT != PNRBCC.AMT) {
            CEP.TRC(SCCGWA, "CHECK BCC-AMT=UMISS-ISS-AMT");
            CEP.TRC(SCCGWA, PNRBCC.AMT);
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_ISSAMT_NOT_EQ, PNCUMISS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUMISS.DATA.FUNC == 'L') {
            CEP.TRC(SCCGWA, PNRBCC.STS);
            if (PNRBCC.STS != '1' 
                && PNRBCC.STS != '3') {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_NOT_NML, PNCUMISS.RC);
                WS_ERR_INFO = "BCC-STS=" + PNRBCC.STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNCUMISS.DATA.FUNC == 'S') {
            if (PNRBCC.STS != '3' 
                && PNRBCC.STS != '1') {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_NOT_NML, PNCUMISS.RC);
                WS_ERR_INFO = "BCC-STS=" + PNRBCC.STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNCUMISS.DATA.FUNC == 'R') {
            if (PNRBCC.STS != '3' 
                && PNRBCC.STS != '4') {
                IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_STS_NOT_NML, PNCUMISS.RC);
                WS_ERR_INFO = "BCC-STS=" + PNRBCC.STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B206_CHECK_TRAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPRMT.KEY.TYP = "PPN02";
        BPRPRMT.KEY.CD = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRPRMT.KEY.CD = "0" + BPRPRMT.KEY.CD;
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
            || BPCPRMM.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_TRAN_NOT_LOSE, PNCUMISS.RC);
            WS_ERR_INFO = "BCC-C-T-FLG=" + PNRBCC.C_T_FLG + ",UMISS-CC-NO =" + PNCUMISS.DATA.CC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B220_COST_FEE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CI_NO = PNCUMISS.DATA.BCC_CINO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = PNRBCC.CCY;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TX_CCY = PNRBCC.CCY;
        BPCTCALF.INPUT_AREA.TX_AMT = PNRBCC.AMT;
        BPCTCALF.INPUT_AREA.PROD_CODE = PNRBCC.PRD_CD;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = PNRBCC.PRD_CD;
        BPCTCALF.INPUT_AREA.CPN_ID = "PN-U-MIS-PNT";
        BPCTCALF.INPUT_AREA.TX_CI = PNCUMISS.DATA.BCC_CINO;
        BPCTCALF.INPUT_AREA.FREE_FMT = "01";
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        S000_CALL_BPZFCALF();
        if (pgmRtn) return;
        PNCUMISS.DATA.TXN_FEE = BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT;
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].CHG_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].ADJ_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[1-1].DER_AMT);
        CEP.TRC(SCCGWA, BPCTCALF.OUTPUT_AREA.FEE_INFO[2-1].DER_AMT);
    }
    public void B202_BCC_MST_PROC() throws IOException,SQLException,Exception {
        if (PNCUMISS.DATA.FUNC == 'L') {
            PNRBCC.LAST_STS = PNRBCC.STS;
            PNRBCC.STS = '3';
        }
        if (PNCUMISS.DATA.FUNC == 'S') {
            PNRBCC.LAST_STS = PNRBCC.STS;
            PNRBCC.STS = '4';
        }
        if (PNCUMISS.DATA.FUNC == 'R') {
            PNRBCC.LAST_STS = PNRBCC.STS;
            PNRBCC.STS = '1';
        }
        PNRBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_PNTBCC();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            CEP.TRC(SCCGWA, "111111");
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_BCC_NOT_FND, PNCUMISS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B203_GBCC_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRGBCC);
        PNRGBCC.KEY.BILL_KND = PNCUMISS.DATA.KND;
        PNRGBCC.KEY.BILL_NO = PNCUMISS.DATA.CC_NO;
        T000_READ_PNTGBCC_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_TABLE_GBCC_FLG);
        if (PNCUMISS.DATA.FUNC == 'R' 
            && WS_TABLE_GBCC_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_GBCC_REC_NOT_EXIST, PNCUMISS.RC);
            WS_ERR_INFO = "UMISS-FUNC=" + PNCUMISS.DATA.FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNCUMISS.DATA.FUNC == 'L') {
            PNRGBCC.KEY.BILL_KND = PNCUMISS.DATA.KND;
            PNRGBCC.KEY.BILL_NO = PNCUMISS.DATA.CC_NO;
            PNRGBCC.MISS_DATE = SCCGWA.COMM_AREA.AC_DATE;
            PNRGBCC.APP_NAME = PNCUMISS.DATA.APP_NM;
            PNRGBCC.APP_ID_TYPE = PNCUMISS.DATA.ID_TYPE;
            PNRGBCC.APP_ID_NO = PNCUMISS.DATA.ID_NO;
            PNRGBCC.APP_TEL = PNCUMISS.DATA.APP_TEL;
            PNRGBCC.APP_ADDR = PNCUMISS.DATA.APP_ADDR;
            PNRGBCC.LOSE_DATE = PNCUMISS.DATA.LOS_DATE;
            PNRGBCC.LOSE_ADDR = PNCUMISS.DATA.LOS_ADDR;
            PNRGBCC.LOSE_RSN = PNCUMISS.DATA.REASON;
            PNRGBCC.PAY_DATE = PNCUMISS.DATA.PAY_DATE;
            PNRGBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            PNRGBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].CHG_AC_TY);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].CHG_AC);
            CEP.TRC(SCCGWA, BPCGCFEE.FEE_DATA.FEE_INFO[1-1].ADJ_AMT);
            if (BPCGCFEE.FEE_DATA.FEE_INFO[1-1].ADJ_AMT == 0) {
                PNRGBCC.FEE_FLG = '2';
            } else {
                if (BPCGCFEE.FEE_DATA.FEE_INFO[1-1].CHG_AC_TY == '1') {
                    PNRGBCC.FEE_FLG = '0';
                } else {
                    PNRGBCC.FEE_FLG = '1';
                    PNRGBCC.FEE_AC = BPCGCFEE.FEE_DATA.FEE_INFO[1-1].CHG_AC;
                }
            }
            CEP.TRC(SCCGWA, PNRGBCC.FEE_FLG);
            CEP.TRC(SCCGWA, PNRGBCC.FEE_AC);
            if (WS_TABLE_GBCC_FLG == 'N') {
                CEP.TRC(SCCGWA, "222222");
                PNRGBCC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                PNRGBCC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_WRITE_PNTGBCC();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "333333");
                PNRGBCC.STOP_DATE = 0;
                PNRGBCC.STOP_REP_NO = " ";
                PNRGBCC.STOP_RSN = " ";
                PNRGBCC.REL_NAME = " ";
                PNRGBCC.REL_ADDR = " ";
                PNRGBCC.REL_TEL = " ";
                PNRGBCC.REL_ID_TYPE = " ";
                PNRGBCC.REL_ID_NO = " ";
                PNRGBCC.REL_SMR = " ";
                PNRGBCC.REL_DATE = 0;
                T000_REWRITE_PNTGBCC();
                if (pgmRtn) return;
            }
        }
        if (PNCUMISS.DATA.FUNC == 'S') {
            if (WS_TABLE_GBCC_FLG == 'Y') {
                CEP.TRC(SCCGWA, "444444");
                PNRGBCC.STOP_DATE = PNCUMISS.DATA.STP_DATE;
                PNRGBCC.STOP_REP_NO = PNCUMISS.DATA.STP_RENO;
                PNRGBCC.STOP_RSN = PNCUMISS.DATA.STP_RSN;
                PNRGBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                PNRGBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_PNTGBCC();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "555555");
                PNRGBCC.KEY.BILL_NO = PNCUMISS.DATA.CC_NO;
                PNRGBCC.KEY.BILL_KND = PNCUMISS.DATA.KND;
                PNRGBCC.STOP_DATE = PNCUMISS.DATA.STP_DATE;
                PNRGBCC.STOP_REP_NO = PNCUMISS.DATA.STP_RENO;
                PNRGBCC.STOP_RSN = PNCUMISS.DATA.STP_RSN;
                PNRGBCC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                PNRGBCC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_WRITE_PNTGBCC();
                if (pgmRtn) return;
            }
        }
        if (PNCUMISS.DATA.FUNC == 'R') {
            CEP.TRC(SCCGWA, "666666");
            PNRGBCC.REL_NAME = PNCUMISS.DATA.RM_NM;
            PNRGBCC.REL_ADDR = PNCUMISS.DATA.RM_ADDR;
            PNRGBCC.REL_TEL = PNCUMISS.DATA.RM_TEL;
            PNRGBCC.REL_ID_TYPE = PNCUMISS.DATA.RM_IDTY;
            PNRGBCC.REL_ID_NO = PNCUMISS.DATA.RM_IDNO;
            PNRGBCC.REL_SMR = PNCUMISS.DATA.RM_SMR;
            PNRGBCC.REL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            PNRGBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            PNRGBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_PNTGBCC();
            if (pgmRtn) return;
        }
    }
    public void B204_BP_HISTORY() throws IOException,SQLException,Exception {
        if (PNCUMISS.DATA.FUNC == 'L' 
            && PNCUMISS.DATA.FEE_FLG != '2') {
        } else {
            B204_02_BP_NFHIS();
            if (pgmRtn) return;
        }
    }
    public void B204_02_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = " ";
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = PNCUMISS.DATA.CC_NO;
        if (PNCUMISS.DATA.FUNC == 'L') {
            BPCPNHIS.INFO.TX_RMK = PNCUMISS.DATA.REASON;
        }
        if (PNCUMISS.DATA.FUNC == 'S') {
            BPCPNHIS.INFO.TX_RMK = PNCUMISS.DATA.STP_RSN;
        }
        if (PNCUMISS.DATA.FUNC == 'R') {
            BPCPNHIS.INFO.TX_RMK = PNCUMISS.DATA.RM_SMR;
        }
        BPCPNHIS.INFO.NEW_DAT_PT = PNCUMISS;
        BPCPNHIS.INFO.FMT_ID = "PNCUMISS";
        BPCPNHIS.INFO.FMT_ID_LEN = 1804;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPQORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUMISS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CALL CPN-BPZPRMM");
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUMISS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PNTBCC_UPD() throws IOException,SQLException,Exception {
        PNTBCC_RD = new DBParm();
        PNTBCC_RD.TableName = "PNTBCC";
        PNTBCC_RD.upd = true;
        IBS.READ(SCCGWA, PNRBCC, PNTBCC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_PNTBCC() throws IOException,SQLException,Exception {
        PNTBCC_RD = new DBParm();
        PNTBCC_RD.TableName = "PNTBCC";
        IBS.REWRITE(SCCGWA, PNRBCC, PNTBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_PNTGBCC_UPD() throws IOException,SQLException,Exception {
        PNTGBCC_RD = new DBParm();
        PNTGBCC_RD.TableName = "PNTGBCC";
        PNTGBCC_RD.upd = true;
        IBS.READ(SCCGWA, PNRGBCC, PNTGBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_GBCC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_GBCC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTGBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_PNTGBCC() throws IOException,SQLException,Exception {
        PNTGBCC_RD = new DBParm();
        PNTGBCC_RD.TableName = "PNTGBCC";
        IBS.WRITE(SCCGWA, PNRGBCC, PNTGBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, PNCMSG_ERROR_MSG.PN_BCC_REC_EXIST, PNCUMISS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTGBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_PNTGBCC() throws IOException,SQLException,Exception {
        PNTGBCC_RD = new DBParm();
        PNTGBCC_RD.TableName = "PNTGBCC";
        IBS.REWRITE(SCCGWA, PNRGBCC, PNTGBCC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_GBCC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_GBCC_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PNTGBCC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BPZFFTXI, BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUMISS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BPZFCALF, BPCTCALF);
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        if (BPCTCALF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUMISS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU_FIRST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase("CI3011")) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUMISS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU_SECOND() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], PNCUMISS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, PNCUMISS.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_ERR_INFO);
        Z_RET();
        if (pgmRtn) return;
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (PNCUMISS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, PNCUMISS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
