package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZMACC {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTBVT_RD;
    brParm TDTBVT_BR = new brParm();
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    brParm TDTSMST_BR = new brParm();
    String K_CLOS_ADV_FMT = "TD503";
    String K_AP_MMO = "TD";
    String K_STS_TBL = "0009";
    String K_STS_TBL_P = "0019";
    String WS_ERR_MSG = " ";
    String WS_BVT_BV_CD = " ";
    char WS_BVT_BV_TYP = ' ';
    String WS_BVT_BV_NO = " ";
    char WS_DRAW_MTH = ' ';
    String WS_REL_AC = " ";
    String WS_DRAW_INF = " ";
    char WS_CMST_REC_FLG = ' ';
    char WS_TDTSMST_FLG = ' ';
    char WS_IAACR_FLG = ' ';
    char WS_TDTBVT_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPLDPD BPCPLDPD = new BPCPLDPD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    TDCPRDP TDCPRDP = new TDCPRDP();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCCGAC BPCCGAC = new BPCCGAC();
    CICSACR CICSACR = new CICSACR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    TDRBVT TDRBVT = new TDRBVT();
    TDRSMST TDRSMST = new TDRSMST();
    TDCOCADV TDCOCADV = new TDCOCADV();
    BPCPQORG BPCPQORG = new BPCPQORG();
    TDCQYBTF TDCQYBTF = new TDCQYBTF();
    TDCQMS TDCQMS = new TDCQMS();
    TDCACM TDCACM = new TDCACM();
    BPCPRGST BPCPRGST = new BPCPRGST();
    CICACCU CICACCU = new CICACCU();
    SCCHMPW SCCHMPW = new SCCHMPW();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    TDRCMST TDRCMST = new TDRCMST();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPROCAC BPROCAC = new BPROCAC();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICSACRL CICSACRL = new CICSACRL();
    CICQACRL CICQACRL = new CICQACRL();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    TDCMACC TDCMACC;
    public void MP(SCCGWA SCCGWA, TDCMACC TDCMACC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCMACC = TDCMACC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZMACC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CEP.TRC(SCCGWA, TDCMACC.DRAW_MTH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCMACC.BV_CD);
        CEP.TRC(SCCGWA, TDCMACC.BV_TYPE);
        CEP.TRC(SCCGWA, TDCMACC.BV_NO);
        CEP.TRC(SCCGWA, TDCMACC.AC_NO);
        CEP.TRC(SCCGWA, TDCMACC.DRAW_MTH);
        CEP.TRC(SCCGWA, TDCMACC.DRAW_INF);
        CEP.TRC(SCCGWA, TDCMACC.ID_TYPE);
        CEP.TRC(SCCGWA, TDCMACC.ID_NO);
        B100_CHK_INPUT_PROC();
        B200_MCA_CLOS_PROC();
        B300_FMT_OUTPUT_PROC();
    }
    public void B100_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (TDCMACC.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT);
        }
        if (TDCMACC.BV_TYPE != '0' 
            && TDCMACC.BV_TYPE != '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_TYP_NOT_MATCH);
        }
    }
    public void B200_MCA_CLOS_PROC() throws IOException,SQLException,Exception {
        B240_GET_AC_NAME_PROC();
        B220_CHK_MAIN_AC_PROC();
        B210_CHK_BV_PROC();
        B230_CHK_SUB_AC_PROC();
        B250_17_CRE_ACRL_PROC();
        B240_UPD_AC_CI_REL_PROC();
        B260_WRI_NFIN_HIS_PROC();
        B270_UPD_TDTCMST_PROC();
        B290_UPD_BPTOCAC_PROC();
    }
    public void B210_CHK_BV_PROC() throws IOException,SQLException,Exception {
        if (TDRCMST.BV_TYP == '1') {
            B212_READ_TDTBVT();
        }
    }
    public void B212_READ_TDTBVT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCMACC.AC_NO;
        T000_READ_TDTBVT1();
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_BV_LOSS);
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_BV_LOSS);
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PSW_LOCK_PLS_RLS);
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_IMPAWNED);
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(6 - 1, 6 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_HAS_B_PSW_LOS);
        }
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        WS_BVT_BV_CD = TDRBVT.BV_CD;
        WS_BVT_BV_NO = TDRBVT.BV_NO;
    }
    if (TDCMACC.BV_TYPE == '1') {
        CEP.TRC(SCCGWA, TDRBVT.BV_CD);
        CEP.TRC(SCCGWA, TDCMACC.BV_CD);
        if (!TDRBVT.BV_CD.equalsIgnoreCase(TDCMACC.BV_CD)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_CD_NOT_MATCH);
        }
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        CEP.TRC(SCCGWA, TDCMACC.BV_NO);
        if (!TDRBVT.BV_NO.equalsIgnoreCase(TDCMACC.BV_NO)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH);
        }
    }
    CEP.TRC(SCCGWA, TDCMACC.DRAW_MTH);
    public void R000_GEN_PSW() throws IOException,SQLException,Exception {
    }
    public void B240_GET_AC_NAME_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCMACC.AC_NO;
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
    }
    public void B220_CHK_MAIN_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCMACC.AC_NO;
        T000_READ_TDTCMST();
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRCMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        if (TDRCMST.DRAW_MTH == '1' 
            || TDRCMST.DRAW_MTH == '4') {
            if (!BPCPQPRD.AC_TYPE.equalsIgnoreCase("043") 
                && !BPCPQPRD.AC_TYPE.equalsIgnoreCase("044")) {
                IBS.init(SCCGWA, TDCACM);
                TDCACM.FUNC = 'C';
                TDCACM.AC_NO = TDCMACC.AC_NO;
                TDCACM.OLD_AC_NO = TDCMACC.AC_NO;
                TDCACM.CARD_PSW_OLD = TDCMACC.DRAW_INF;
                TDCACM.CARD_PSW_NEW = TDCMACC.DRAW_INF;
                TDCACM.ID_TYP = TDCMACC.ID_TYPE;
                TDCACM.ID_NO = TDCMACC.ID_NO;
                TDCACM.CI_NM = TDCMACC.CI_CNM;
                S000_CALL_TDZACM();
            }
        }
        if (TDRCMST.CROS_DR_FLG == '0' 
            && TDRCMST.CHE_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            S000_CALL_BPZPRGST();
        }
        if (TDRCMST.CROS_DR_FLG == '2' 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != TDRCMST.CHE_BR 
            && BPCPORUP.DATA_INFO.BBR != TDRCMST.OWNER_BRDP) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DR_CL_ERR);
        }
        CEP.TRC(SCCGWA, TDRCMST.STS);
        if (TDRCMST.STS == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        CEP.TRC(SCCGWA, TDRCMST.STS);
        if (TDRCMST.STS == '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_OPEN_CANCLE);
        }
        if (TDRCMST.STS == '3') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOEFFECT);
        }
        WS_DRAW_MTH = TDRCMST.DRAW_MTH;
        WS_DRAW_INF = TDRCMST.DRAW_INF;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_STSW);
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_AP_MMO;
        if (CICACCU.DATA.CI_TYP == '1') {
            BPCFCSTS.TBL_NO = K_STS_TBL_P;
        } else {
            BPCFCSTS.TBL_NO = K_STS_TBL;
        }
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 01 - 1) + CICACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(01 + 80 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.VER_STSW == null) CICACCU.DATA.VER_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.VER_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CICACCU.DATA.VER_STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 81 - 1) + CICACCU.DATA.VER_STSW + BPCFCSTS.STATUS_WORD.substring(81 + 87 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + TDRCMST.STSW + BPCFCSTS.STATUS_WORD.substring(101 + 32 - 1);
        if (TDCMACC.BV_TYPE != '0') {
            if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
            JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
            for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
            BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 151 - 1) + "00000000000000000000000000000000" + BPCFCSTS.STATUS_WORD.substring(151 + 32 - 1);
        }
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, TDRCMST.CHE_BR);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = TDRCMST.CHE_BR;
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        CEP.TRC(SCCGWA, BPCPRGST.RC.RC_CODE);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        CEP.TRC(SCCGWA, BPCPRGST.FLAG);
        CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
        if (BPCPRGST.BRANCH_FLG == 'Y') {
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DR_CL_ERR);
        }
    }
    public void B230_CHK_SUB_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCMACC.AC_NO;
        T000_STARTBR_TDTSMST();
        T000_READNEXT_TDTSMST();
        while (WS_TDTSMST_FLG != 'N') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
                && TDRSMST.ACO_STS == '0') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_CLEAR);
            }
            T000_READNEXT_TDTSMST();
        }
        T000_ENDBR_TDTSMST();
    }
    public void B240_UPD_AC_CI_REL_PROC() throws IOException,SQLException,Exception {
        CICSACR.FUNC = 'D';
        if (TDCMACC.DRAW_INF.equalsIgnoreCase("GROPDRAW")) {
            CICSACR.FUNC = 'A';
        }
        CICSACR.DATA.AGR_NO = TDCMACC.AC_NO;
        CICSACR.DATA.ENTY_TYP = '3';
        CEP.TRC(SCCGWA, CICSACR.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICSACR.DATA.ENTY_TYP);
        S000_CALL_CIZSACR();
    }
    public void B250_17_CRE_ACRL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCMACC.AC_NO;
        T000_READ_UPD_TDTCMST();
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRCMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("043") 
            || BPCPQPRD.AC_TYPE.equalsIgnoreCase("044") 
            || BPCPQPRD.AC_TYPE.equalsIgnoreCase("045")) {
            IBS.init(SCCGWA, CICQACRL);
            CICQACRL.FUNC = 'I';
            CICQACRL.DATA.AC_NO = TDCMACC.AC_NO;
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("043")) {
                CICQACRL.DATA.AC_REL = "05";
            }
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("044")) {
                CICQACRL.DATA.AC_REL = "06";
            }
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("045")) {
                CICQACRL.DATA.AC_REL = "11";
            }
            S000_CALL_CIZQACRL();
            WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
            CEP.TRC(SCCGWA, WS_REL_AC);
            IBS.init(SCCGWA, CICSACRL);
            CICSACRL.FUNC = 'D';
            CICSACRL.DATA.AC_NO = TDCMACC.AC_NO;
            CICSACRL.DATA.REL_AC_NO = WS_REL_AC;
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("043")) {
                CICSACRL.DATA.AC_REL = "05";
            }
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("044")) {
                CICSACRL.DATA.AC_REL = "06";
            }
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("045")) {
                CICSACRL.DATA.AC_REL = "11";
            }
            S000_CALL_CIZSACRL();
        }
    }
    public void B260_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P115";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.AC = TDCMACC.AC_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
    }
    public void B270_UPD_TDTCMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCMACC.AC_NO;
        T000_READ_UPD_TDTCMST();
        if (WS_CMST_REC_FLG == 'Y') {
            TDRCMST.STS = '1';
            TDRCMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_TDTCMST();
        }
    }
    public void B290_UPD_BPTOCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = TDCMACC.AC_NO;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.AUT_TLR = " ";
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.PROD_CD = TDRCMST.PROD_CD;
        BPCSOCAC.CLO_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZSOCAC();
    }
    public void B300_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        B310_OUTPUT_CLOS_ADV();
    }
    public void B310_OUTPUT_CLOS_ADV() throws IOException,SQLException,Exception {
        R000_GET_BK_NAME();
        IBS.init(SCCGWA, TDCOCADV);
        TDCOCADV.AC_NO = TDCMACC.AC_NO;
        TDCOCADV.AC_NM = CICACCU.DATA.CI_CNM;
        TDCOCADV.CL_BK_NM = BPCPQORG.CHN_NM;
        TDCOCADV.CLOS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCOCADV.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_CLOS_ADV_FMT;
        SCCFMT.DATA_PTR = TDCOCADV;
        SCCFMT.DATA_LEN = 362;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GET_BK_NAME() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC);
        if (BPCCGAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-PSW-CHECK", TDCACM);
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCHMPW);
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PWD_ERROR);
        }
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZSACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACRL", CICSACRL);
        CEP.TRC(SCCGWA, CICSACRL.RC.RC_CODE);
        if (CICSACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO "
            + "AND BV_NO = :TDRBVT.BV_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTBVT1() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_STARTBR_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_BR.rp = new DBParm();
        TDTBVT_BR.rp.TableName = "TDTBVT";
        TDTBVT_BR.rp.where = "AC = :TDRBVT.KEY.AC_NO";
        IBS.STARTBR(SCCGWA, TDRBVT, this, TDTBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READNEXT_TDTBVT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRBVT, this, TDTBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTBVT_FLG = 'Y';
        } else {
            WS_TDTBVT_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTBVT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTBVT_BR);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMST_REC_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE TDTCMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_UPD_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTSMST_FLG = 'Y';
        } else {
            WS_TDTSMST_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void T000_REWRITE_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
