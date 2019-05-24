package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCFAMTA;
import com.hisun.BP.BPCFX;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCOVAWR;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPCSGSEQ;
import com.hisun.BP.BPCUABOX;
import com.hisun.BP.BPCUSBOX;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICGAGA_AGENT_AREA_AGENT_AREA;
import com.hisun.CI.CICSAGEN;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZSCSHD {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DDP10";
    String CPN_UNIT_DEP_PROC = "DD-UNIT-DEP-PROC ";
    String CPN_U_ADD_CBOX = "BP-U-ADD-CBOX      ";
    String CPN_U_SUB_CBOX = "BP-U-SUB-CBOX      ";
    String CPN_AMT_TBL_AUTH = "BP-F-AMT-TBL-AUTH  ";
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_BP_EX = "BP-EX           ";
    String CPN_UNI_CIZACCU = "CI-INQ-ACCU";
    String K_CTL_PRM_TYP = "PDD25";
    String WS_ERR_MSG = " ";
    String WS_BUY_CCY = " ";
    char WS_BUY_CCY_TYPE = ' ';
    double WS_BUY_AMT = 0;
    String WS_SELL_CCY = " ";
    char WS_SELL_CCY_TYPE = ' ';
    double WS_SELL_AMT = 0;
    String WS_PARM_CODE = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCOCSHD DDCOCSHD = new DDCOCSHD();
    BPCOVAWR BPCOVAWR = new BPCOVAWR();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCFAMTA BPCFAMTA = new BPCFAMTA();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    BPCFX BPCFX = new BPCFX();
    CICACCU CICACCU = new CICACCU();
    DDCPAMTL DDCPAMTL = new DDCPAMTL();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DDRMST DDRMST = new DDRMST();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCGWA SCCGWA;
    DDCSCSHD DDCSCSHD;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public void MP(SCCGWA SCCGWA, DDCSCSHD DDCSCSHD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCSHD = DDCSCSHD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCSHD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSCSHD);
        CEP.TRC(SCCGWA, DDCSCSHD.CARD_NO);
        CEP.TRC(SCCGWA, DDCSCSHD.CARD_NO);
        CEP.TRC(SCCGWA, DDCSCSHD.AC);
        CEP.TRC(SCCGWA, DDCSCSHD.CCY);
        CEP.TRC(SCCGWA, DDCSCSHD.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCSHD.PSBK_NO);
        CEP.TRC(SCCGWA, DDCSCSHD.CASH_AMT);
        CEP.TRC(SCCGWA, DDCSCSHD.CASH_CCY);
        CEP.TRC(SCCGWA, DDCSCSHD.CASH_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCSHD.TICK_NO);
        CEP.TRC(SCCGWA, DDCSCSHD.EX_RATE);
        CEP.TRC(SCCGWA, DDCSCSHD.EX_TIME);
        CEP.TRC(SCCGWA, DDCSCSHD.VAL_DATE);
        CEP.TRC(SCCGWA, DDCSCSHD.CASH_NO);
        CEP.TRC(SCCGWA, DDCSCSHD.TX_RMK);
        CEP.TRC(SCCGWA, DDCSCSHD.REMARKS);
        CEP.TRC(SCCGWA, DDCSCSHD.PAY_PERSON);
        B005_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CASH_PROC();
        if (pgmRtn) return;
        R000_GET_CI_TYPE();
        if (pgmRtn) return;
        if (DDCSCSHD.BV_TYP == '6') {
            B015_DEPOSIT_TO_AI_PROC();
            if (pgmRtn) return;
        } else {
            B015_DEPOSIT_TO_AC_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AGENT_FLG);
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B060_AGENT_INF_PORC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B050_OUTPUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B005_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSCSHD.AC.trim().length() == 0 
            && DDCSCSHD.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCSHD.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = DDCSCSHD.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
            if (BPCQCCY.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCSHD.CASH_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = DDCSCSHD.CASH_CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
            if (BPCQCCY.RC.RTNCODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            DDCSCSHD.CASH_CCY = DDCSCSHD.CCY;
        }
        if (DDCSCSHD.CASH_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DDCSCSHD.CASH_AMT < 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSCSHD.VAL_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDCSCSHD.VAL_DATE;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            DDCSCSHD.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (DDCSCSHD.CCY_TYPE == ' ' 
            && DDCSCSHD.CCY.equalsIgnoreCase("156")) {
            DDCSCSHD.CCY_TYPE = '1';
        }
        if (DDCSCSHD.CCY_TYPE == '2') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_ERROR);
        }
    }
    public void B010_EXG_AMT_PROC() throws IOException,SQLException,Exception {
        if (!DDCSCSHD.CCY.equalsIgnoreCase(DDCSCSHD.CASH_CCY)) {
            WS_BUY_CCY = DDCSCSHD.CASH_CCY;
            WS_BUY_AMT = DDCSCSHD.CASH_AMT;
            WS_SELL_CCY = DDCSCSHD.CCY;
            R000_AMT_EX_PROC();
            if (pgmRtn) return;
        } else {
            WS_SELL_AMT = DDCSCSHD.CASH_AMT;
        }
    }
    public void B012_CARD_LIMT_BAL_CHK() throws IOException,SQLException,Exception {
        if (DDCSCSHD.CARD_NO.trim().length() > 0 
            && DDCSCSHD.BV_TYP == '1') {
            IBS.init(SCCGWA, DCCPFTCK);
            DCCPFTCK.VAL.CARD_NO = DDCSCSHD.CARD_NO;
            DCCPFTCK.VAL.REGN_TYP = '0';
            DCCPFTCK.VAL.TXN_TYPE = "02";
            DCCPFTCK.VAL.TXN_CCY = DDCSCSHD.CCY;
            DCCPFTCK.VAL.TXN_AMT = DDCSCSHD.CASH_AMT;
            S000_CALL_DCZPFTCK();
            if (pgmRtn) return;
        }
    }
    public void B015_DEPOSIT_TO_AI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = DDCSCSHD.AC;
        AICUUPIA.DATA.RVS_SEQ = 0;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.AMT = DDCSCSHD.CASH_AMT;
        AICUUPIA.DATA.CCY = DDCSCSHD.CCY;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.POST_NARR = " ";
        AICUUPIA.DATA.RVS_NO = DDCSCSHD.CREV_NO;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.PAY_MAN = DDCSCSHD.PAY_PERSON;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.PAY_MAN);
        AICUUPIA.DATA.DESC = DDCSCSHD.REMARKS;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.DESC);
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        CEP.TRC(SCCGWA, AICUUPIA.RC.RC_CODE);
        if (AICUUPIA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B015_DEPOSIT_TO_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'C';
        DDCUCRAC.AC = DDCSCSHD.AC;
        DDCUCRAC.CARD_NO = DDCSCSHD.CARD_NO;
        DDCUCRAC.TX_AMT = DDCSCSHD.CASH_AMT;
        DDCUCRAC.CCY = DDCSCSHD.CCY;
        DDCUCRAC.CCY_TYPE = DDCSCSHD.CCY_TYPE;
        DDCUCRAC.VAL_DATE = DDCSCSHD.VAL_DATE;
        DDCUCRAC.TX_REF = DDCSCSHD.TX_RMK;
        if (CICACCU.DATA.CI_TYP == '1') {
            DDCUCRAC.PSBK_NO = DDCSCSHD.BV_VNO;
            DDCUCRAC.REMARKS = DDCSCSHD.REMARKS;
        } else {
            DDCUCRAC.NARRATIVE = DDCSCSHD.TX_RMK;
            DDCUCRAC.REMARKS = DDCSCSHD.PAY_PERSON;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            DDCUCRAC.TX_MMO = "G004";
        } else {
            DDCUCRAC.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
        }
        DDCUCRAC.BV_TYP = DDCSCSHD.BV_TYP;
        DDCUCRAC.BV_VTYP = DDCSCSHD.BV_VTYP;
        DDCUCRAC.BV_VNO = DDCSCSHD.BV_VNO;
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCUCRAC.TX_MMO);
    }
    public void B020_CASH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = DDCSCSHD.CASH_CCY;
        BPCUABOX.AMT = DDCSCSHD.CASH_AMT;
        CEP.TRC(SCCGWA, BPCUABOX.CCY);
        CEP.TRC(SCCGWA, BPCUABOX.AMT);
        BPCUABOX.OPP_AC = DDCSCSHD.AC;
        BPCUABOX.CASH_NO = DDCSCSHD.CASH_NO;
        BPCUABOX.APP_NO = DDCSCSHD.REG_CD;
        S000_CALL_BPZUABOX();
        if (pgmRtn) return;
    }
    public void R000_AMT_EX_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BUY_CCY);
        CEP.TRC(SCCGWA, WS_BUY_AMT);
        CEP.TRC(SCCGWA, WS_SELL_CCY);
        IBS.init(SCCGWA, BPCFX);
        BPCFX.FUNC = '3';
        BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
        BPCFX.EXR_TYPE = BPCRBANK.EX_RA;
        BPCFX.BUY_CCY = WS_BUY_CCY;
        BPCFX.BUY_AMT = WS_BUY_AMT;
        BPCFX.SELL_CCY = WS_SELL_CCY;
        if (DDCSCSHD.EX_RATE != 0) {
            BPCFX.TRN_RATE = DDCSCSHD.EX_RATE;
        }
        S000_LINK_BPZSFX();
        if (pgmRtn) return;
        WS_SELL_AMT = BPCFX.SELL_AMT;
        CEP.TRC(SCCGWA, WS_SELL_AMT);
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOCSHD);
        DDCOCSHD.TX_TYP = 'C';
        DDCOCSHD.TX_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCOCSHD.AC = DDCSCSHD.AC;
        if (CICACCU.DATA.AC_ENM.trim().length() == 0) {
            DDCOCSHD.ENG_NM = CICACCU.DATA.CI_ENM;
        } else {
            DDCOCSHD.ENG_NM = CICACCU.DATA.AC_ENM;
        }
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            DDCOCSHD.CHI_NM = CICACCU.DATA.CI_CNM;
        } else {
            DDCOCSHD.CHI_NM = CICACCU.DATA.AC_CNM;
        }
        DDCOCSHD.CR_CCY = DDCSCSHD.CCY;
        DDCOCSHD.CR_CAMT = DDCSCSHD.CASH_AMT;
        DDCOCSHD.BACK_DATE = DDCSCSHD.VAL_DATE;
        DDCOCSHD.TX_RMK = DDCSCSHD.TX_RMK;
        DDCOCSHD.REMARKS = DDCSCSHD.REMARKS;
        DDCOCSHD.PAY_PERSON = DDCSCSHD.PAY_PERSON;
        DDCOCSHD.CARD_NO = DDCSCSHD.CARD_NO;
        DDCOCSHD.NARRATIVE = DDCSCSHD.NARRATIVE;
        DDCOCSHD.TX_MMO = DDCUCRAC.TX_MMO;
        DDCOCSHD.CREV_NO = AICUUPIA.DATA.RVS_NO;
        if (DDCSCSHD.BV_TYP == '2' 
            || DDCSCSHD.BV_TYP == '4' 
            || DDCSCSHD.BV_TYP == '5') {
            DDCOCSHD.BV_NO = DDCSCSHD.BV_VNO;
        }
        CEP.TRC(SCCGWA, DDCOCSHD.BV_NO);
        CEP.TRC(SCCGWA, DDCOCSHD.BAL);
        CEP.TRC(SCCGWA, DDCOCSHD.TX_MMO);
        CEP.TRC(SCCGWA, "TEST001");
        CEP.TRC(SCCGWA, DDCOCSHD.NARRATIVE);
        CEP.TRC(SCCGWA, DDCOCSHD.TX_RMK);
        CEP.TRC(SCCGWA, DDCOCSHD.REMARKS);
        CEP.TRC(SCCGWA, DDCOCSHD.ENG_NM);
        CEP.TRC(SCCGWA, DDCOCSHD.CHI_NM);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOCSHD;
        SCCFMT.DATA_LEN = 1297;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_AGENT_INF_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        if (DDCSCSHD.AC.trim().length() > 0) {
            CICSAGEN.OUT_AC = DDCSCSHD.AC;
        } else {
            CICSAGEN.OUT_AC = DDCSCSHD.CARD_NO;
        }
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void R000_CHECK_TX_AMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFAMTA);
        BPCFAMTA.FUNC = ' ';
        BPCFAMTA.AP_MMO = "DD";
        BPCFAMTA.TBL_NO = "0002";
        BPCFAMTA.CHNL = "CHNL";
        BPCFAMTA.CCY = DDCSCSHD.CCY;
        BPCFAMTA.AMT = DDCSCSHD.CASH_AMT;
        S000_CALL_BPZFAMTA();
        if (pgmRtn) return;
    }
    public void R000_QUREY_AMT_LIMIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DDCPAMTL);
        BPRPRMT.KEY.TYP = K_CTL_PRM_TYP;
        BPRPRMT.KEY.CD = WS_PARM_CODE;
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCPAMTL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MPRD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
