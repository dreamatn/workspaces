package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DC.*;
import com.hisun.IB.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class GDZSPNPR {
    int JIBS_tmp_int;
    brParm TDTSMST_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTSMST_RD;
    DBParm DDTCCY_RD;
    DBParm GDTPLDR_RD;
    DBParm DDTMST_RD;
    DBParm TDTCMST_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "GD310";
    String WS_ERR_MSG = " ";
    double WS_AVA_RELAT_AMT = 0;
    double WS_HLD_AMT = 0;
    String WS_EQ_BKID = " ";
    String WS_IB_AC1 = " ";
    String WS_IB_AC2 = " ";
    String WS_DD_AC1 = " ";
    String WS_DD_AC2 = " ";
    int WS_AC_SEQ = 0;
    double WS_RELAT_AMT = 0;
    double WS_NEED_PLDR_AMT = 0;
    double WS_AVAL_BAL = 0;
    String WS_RSEQ = " ";
    String WS_OTHER_AC = " ";
    double WS_AVL_RAMT = 0;
    char WS_MPRD_TD_FLG = ' ';
    char WS_PLDR_FLG = ' ';
    char WS_SMST_FLG = ' ';
    char WS_READ_TWICE = ' ';
    char WS_FEE_ONLY = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    GDRPLDR GDRPLDR = new GDRPLDR();
    GDRHIS GDRHIS = new GDRHIS();
    CICACCU CICACCU = new CICACCU();
    GDCOMPRL GDCOMPRL = new GDCOMPRL();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCUSBOX BPCUSBOX = new BPCUSBOX();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    DCCSKHLD DCCSKHLD = new DCCSKHLD();
    DCCSQHLD DCCSQHLD = new DCCSQHLD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    GDCRPLDR GDCRPLDR = new GDCRPLDR();
    GDCRHIS GDCRHIS = new GDCRHIS();
    DDCRMST DDCRMST = new DDCRMST();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    IBCQRAC IBCQRAC = new IBCQRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    DDCSCINM DDCSCINM = new DDCSCINM();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    TDCACE TDCACE = new TDCACE();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACAC CICQACAC = new CICQACAC();
    DCCURHLD DCCURHLD = new DCCURHLD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    BPCSFHIS BPCSFHIS = new BPCSFHIS();
    AICPUITM AICPUITM = new AICPUITM();
    AICPQMIB AICPQMIB = new AICPQMIB();
    GDCSRLSR GDCSRLSR = new GDCSRLSR();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    GDCSPNPR GDCSPNPR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, GDCSPNPR GDCSPNPR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCSPNPR = GDCSPNPR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZSPNPR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA_VALIDITY();
        if (pgmRtn) return;
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            B015_CHECK_GDKD_PROC();
            if (pgmRtn) return;
        }
        if (GDCSPNPR.VAL.BUSI_TYP.equalsIgnoreCase("08")) {
            WS_FEE_ONLY = 'Y';
        }
        if (WS_FEE_ONLY != 'Y') {
            B015_01_CHECK_GDKD_PROC();
            if (pgmRtn) return;
            if (WS_MPRD_TD_FLG != '0') {
                B020_CREATE_PLDR_PROC();
                if (pgmRtn) return;
            }
        }
        if (GDCSPNPR.VAL.FEE_AMT > 0) {
            R000_DR_DD_PROC();
            if (pgmRtn) return;
            R000_CR_ITM_PROC();
            if (pgmRtn) return;
            R000_CALL_FEE_REG();
            if (pgmRtn) return;
        }
        if (GDCSPNPR.VAL.ALLO_FLG == '1') {
            B025_ALLO_IB_PROC();
            if (pgmRtn) return;
        }
        B030_OUTPUT_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DATA_VALIDITY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSPNPR.KEY.AC);
        CEP.TRC(SCCGWA, GDCSPNPR.KEY.RSEQ);
        CEP.TRC(SCCGWA, GDCSPNPR.KEY.AC_SEQ);
        if (GDCSPNPR.KEY.AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSPNPR.VAL.AC_TYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSPNPR.VAL.CTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSPNPR.VAL.BUSI_TYP.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (GDCSPNPR.VAL.PNAMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PN_AMT_M_IPT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = GDCSPNPR.KEY.AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDCSPNPR.KEY.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDRMST.YW_TYP);
            GDCSPNPR.VAL.BUSI_TYP = DDRMST.YW_TYP;
        } else {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = GDCSPNPR.KEY.AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, TDRCMST.REF_TYP);
            GDCSPNPR.VAL.BUSI_TYP = TDRCMST.REF_TYP;
        }
    }
    public void B015_CONTINUE_HLD_VALIDITY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCURHLD);
        CEP.TRC(SCCGWA, GDCSPNPR.VAL.HLD_NO);
        CEP.TRC(SCCGWA, GDCSPNPR.VAL.RL_AMT);
        if (GDCSPNPR.VAL.HLD_NO.trim().length() > 0) {
            DCCURHLD.DATA.HLD_NO = GDCSPNPR.VAL.HLD_NO;
            DCCURHLD.DATA.RAMT = GDCSPNPR.VAL.RL_AMT;
            DCCURHLD.DATA.RHLD_TYP = '2';
            S000_CALL_DCZURHLD();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCURHLD.DATA.RAMT);
        CEP.TRC(SCCGWA, DCCURHLD.DATA.AVL_AMT);
    }
    public void B015_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSPNPR.KEY.AC);
        CEP.TRC(SCCGWA, GDCSPNPR.VAL.CCY);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = GDCSPNPR.KEY.AC;
        DDRCCY.CCY = GDCSPNPR.VAL.CCY;
        DDRCCY.CCY_TYPE = GDCSPNPR.VAL.CCY_TYP;
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDRCCY.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = GDCSPNPR.VAL.CCY;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        CEP.TRC(SCCGWA, GDCSPNPR.VAL.CCY);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDVMPRD.VAL.TD_FLG);
        WS_MPRD_TD_FLG = DDVMPRD.VAL.TD_FLG;
    }
    public void B015_01_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        if (WS_MPRD_TD_FLG == '0') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B025_GDKD_PLDR_PROC();
                if (pgmRtn) return;
            } else {
                B025_GDKD_CANC_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_AC_SEQ = GDCSPNPR.KEY.AC_SEQ;
            WS_RELAT_AMT = GDCSPNPR.VAL.RL_AMT;
            WS_RSEQ = GDCSPNPR.KEY.RSEQ;
        }
    }
    public void B020_CREATE_PLDR_PROC() throws IOException,SQLException,Exception {
        B040_CHECK_PLDR_PROC();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (WS_PLDR_FLG == 'N') {
                CEP.TRC(SCCGWA, "ADD PLDR");
                IBS.init(SCCGWA, GDCUMPLD);
                GDCUMPLD.FUNC = 'C';
                GDCUMPLD.AC = GDCSPNPR.KEY.AC;
                GDCUMPLD.AC_TYPE = GDCSPNPR.VAL.AC_TYP;
                GDCUMPLD.AC_SEQ = GDCSPNPR.KEY.AC_SEQ;
                GDCUMPLD.CCY = GDCSPNPR.VAL.CCY;
                if (GDCSPNPR.VAL.CCY.equalsIgnoreCase("156")) {
                    GDCUMPLD.CCY_TYPE = '1';
                } else {
                    GDCUMPLD.CCY_TYPE = '2';
                }
                GDCUMPLD.SYS_NO = GDCSPNPR.VAL.SYS_NO;
                GDCUMPLD.RSEQ = GDCSPNPR.KEY.RSEQ;
                GDCUMPLD.TX_AMT = GDCSPNPR.VAL.RL_AMT;
                GDCUMPLD.PN_AMT = GDCSPNPR.VAL.PNAMT;
                GDCUMPLD.EXP_DATE = GDCSPNPR.VAL.EXP_DT;
                GDCUMPLD.CTA_NO = GDCSPNPR.VAL.CTA_NO;
                GDCUMPLD.REF_NO = GDCSPNPR.VAL.REF_NO;
                GDCUMPLD.BUSI_TYPE = GDCSPNPR.VAL.BUSI_TYP;
                GDCUMPLD.MMO = "A310";
                S000_CALL_GDZUMPLD();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (WS_PLDR_FLG == 'Y' 
                && GDRPLDR.RELAT_STS == 'N') {
                CEP.TRC(SCCGWA, "DELETE PLDR");
                IBS.init(SCCGWA, GDCUMPLD);
                GDCUMPLD.FUNC = 'D';
                GDCUMPLD.AC = GDCSPNPR.KEY.AC;
                GDCUMPLD.AC_TYPE = GDCSPNPR.VAL.AC_TYP;
                GDCUMPLD.AC_SEQ = GDCSPNPR.KEY.AC_SEQ;
                GDCUMPLD.CCY = GDCSPNPR.VAL.CCY;
                if (GDCSPNPR.VAL.CCY.equalsIgnoreCase("156")) {
                    GDCUMPLD.CCY_TYPE = '1';
                } else {
                    GDCUMPLD.CCY_TYPE = '2';
                }
                GDCUMPLD.SYS_NO = GDCSPNPR.VAL.SYS_NO;
                GDCUMPLD.RSEQ = GDRPLDR.KEY.RSEQ;
                GDCUMPLD.TX_AMT = GDCSPNPR.VAL.RL_AMT;
                GDCUMPLD.PN_AMT = GDCSPNPR.VAL.PNAMT;
                GDCUMPLD.EXP_DATE = GDCSPNPR.VAL.EXP_DT;
                GDCUMPLD.CTA_NO = GDCSPNPR.VAL.CTA_NO;
                GDCUMPLD.REF_NO = GDCSPNPR.VAL.REF_NO;
                GDCUMPLD.BUSI_TYPE = GDCSPNPR.VAL.BUSI_TYP;
                GDCUMPLD.MMO = "P620";
                S000_CALL_GDZUMPLD();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, GDCMSG_ERROR_MSG.GD_INACTIVE_RSEQ);
            }
        }
    }
    public void B025_GDKD_PLDR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.CCAL_TOT_BAL);
        CEP.TRC(SCCGWA, DDRCCY.MARGIN_BAL);
        CEP.TRC(SCCGWA, "GDKD PLDR START");
        WS_AVAL_BAL = DDRCCY.CURR_BAL + DDRCCY.CCAL_TOT_BAL - DDRCCY.HOLD_BAL - DDRCCY.MARGIN_BAL;
        CEP.TRC(SCCGWA, WS_AVAL_BAL);
        CEP.TRC(SCCGWA, GDCSPNPR.VAL.RL_AMT);
        if (WS_AVAL_BAL < GDCSPNPR.VAL.RL_AMT 
            && SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RL_AMT_GT_LMT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_NEED_PLDR_AMT = GDCSPNPR.VAL.RL_AMT;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = GDCSPNPR.KEY.AC;
        T000_STARTBR_TDTSMST();
        if (pgmRtn) return;
        T000_READNEXT_TDTSMST();
        if (pgmRtn) return;
        if (WS_SMST_FLG == 'N' 
            || WS_NEED_PLDR_AMT == 0) {
            B020_CREATE_PLDR_PROC();
            if (pgmRtn) return;
        }
        while (WS_SMST_FLG != 'N' 
            && WS_NEED_PLDR_AMT != 0) {
            CEP.TRC(SCCGWA, "PLDR RECORDIG....");
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_AVL_RAMT = TDRSMST.BAL - TDRSMST.HBAL - TDRSMST.GUAR_BAL;
            CEP.TRC(SCCGWA, WS_AVL_RAMT);
            if (WS_AVL_RAMT > WS_NEED_PLDR_AMT) {
                WS_RELAT_AMT = WS_NEED_PLDR_AMT;
            } else {
                WS_RELAT_AMT = WS_AVL_RAMT;
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'A';
            CICQACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ);
            WS_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
            if (WS_READ_TWICE == 'Y') {
                WS_RSEQ = GDCUMPLD.O_RSEQ;
                CEP.TRC(SCCGWA, WS_RSEQ);
            }
            CEP.TRC(SCCGWA, "ADD PLDR");
            IBS.init(SCCGWA, GDCUMPLD);
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                GDCUMPLD.FUNC = 'C';
            } else {
                GDCUMPLD.FUNC = 'D';
            }
            GDCUMPLD.AC = GDCSPNPR.KEY.AC;
            GDCUMPLD.AC_TYPE = GDCSPNPR.VAL.AC_TYP;
            GDCUMPLD.AC_SEQ = WS_AC_SEQ;
            GDCUMPLD.CCY = GDCSPNPR.VAL.CCY;
            if (GDCSPNPR.VAL.CCY.equalsIgnoreCase("156")) {
                GDCUMPLD.CCY_TYPE = '1';
            } else {
                GDCUMPLD.CCY_TYPE = '2';
            }
            GDCUMPLD.SYS_NO = GDCSPNPR.VAL.SYS_NO;
            GDCUMPLD.RSEQ = WS_RSEQ;
            GDCUMPLD.TX_AMT = WS_RELAT_AMT;
            GDCUMPLD.PN_AMT = GDCSPNPR.VAL.PNAMT;
            GDCUMPLD.EXP_DATE = GDCSPNPR.VAL.EXP_DT;
            GDCUMPLD.CTA_NO = GDCSPNPR.VAL.CTA_NO;
            GDCUMPLD.REF_NO = GDCSPNPR.VAL.REF_NO;
            GDCUMPLD.BUSI_TYPE = GDCSPNPR.VAL.BUSI_TYP;
            GDCUMPLD.MMO = "A310";
            S000_CALL_GDZUMPLD();
            if (pgmRtn) return;
            WS_NEED_PLDR_AMT = WS_NEED_PLDR_AMT - WS_RELAT_AMT;
            T000_READNEXT_TDTSMST();
            if (pgmRtn) return;
            WS_READ_TWICE = 'Y';
            CEP.TRC(SCCGWA, WS_NEED_PLDR_AMT);
            CEP.TRC(SCCGWA, WS_RELAT_AMT);
        }
        T000_ENDBR_TDTSMST();
        if (pgmRtn) return;
        if (GDCSPNPR.VAL.RL_AMT > 0) {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL + GDCSPNPR.VAL.RL_AMT;
            } else {
                DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL - GDCSPNPR.VAL.RL_AMT;
            }
            T000_UPDATE_DDTCCY();
            if (pgmRtn) return;
        }
    }
    public void B025_GDKD_CANC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        GDRPLDR.KEY.AC = GDCSPNPR.KEY.AC;
        GDRPLDR.DEAL_CD = GDCSPNPR.VAL.CTA_NO;
        GDRPLDR.BSREF = GDCSPNPR.VAL.REF_NO;
        T000_READ_FIRST_REC_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, GDRPLDR.KEY.RSEQ);
        IBS.init(SCCGWA, GDCSRLSR);
        GDCSRLSR.VAL.AC = GDCSPNPR.KEY.AC;
        GDCSRLSR.VAL.RL_SEQ = GDRPLDR.KEY.RSEQ;
        GDCSRLSR.VAL.CTA_NO = GDCSPNPR.VAL.CTA_NO;
        GDCSRLSR.VAL.REF_NO = GDCSPNPR.VAL.REF_NO;
        S000_CALL_GDZSRLSR();
        if (pgmRtn) return;
    }
    public void B025_ALLO_IB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = GDCSPNPR.VAL.ALLO_B;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_EQ_BKID = BPCPQORG.VIL_TYP;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_EQ_BKID;
        IBCQRAC.AC_TYP = "04";
        S000_CALL_IBZQRAC();
        if (pgmRtn) return;
        WS_IB_AC1 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_EQ_BKID;
        IBCQRAC.AC_TYP = "03";
        S000_CALL_IBZQRAC();
        if (pgmRtn) return;
        WS_IB_AC2 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = WS_IB_AC1;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = "156";
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.AMT = GDCSPNPR.VAL.PNAMT;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.OTH_AC_NO = WS_IB_AC2;
        WS_OTHER_AC = IBCPOSTA.OTH_AC_NO;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        IBCPOSTA.OTH_CNM = CICQACRI.O_DATA.O_AC_CNM;
        IBCPOSTA.OTH_BR = CICQACRI.O_DATA.O_OPN_BR;
        IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        S000_CALL_IBZCRAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.AC = WS_IB_AC2;
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = "156";
        IBCPOSTA.CCY_TYP = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = GDCSPNPR.VAL.PNAMT;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.OTH_AC_NO = WS_IB_AC1;
        WS_OTHER_AC = IBCPOSTA.OTH_AC_NO;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        IBCPOSTA.OTH_CNM = CICQACRI.O_DATA.O_AC_CNM;
        IBCPOSTA.OTH_BR = CICQACRI.O_DATA.O_OPN_BR;
        IBCPOSTA.OTH_BR_CNM = BPCPQORG.CHN_NM;
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_EQ_BKID;
        IBCQRAC.AC_TYP = "01";
        S000_CALL_IBZQRAC();
        if (pgmRtn) return;
        WS_DD_AC1 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, IBCQRAC);
        IBCQRAC.BANK_ID = WS_EQ_BKID;
        IBCQRAC.AC_TYP = "02";
        S000_CALL_IBZQRAC();
        if (pgmRtn) return;
        WS_DD_AC2 = IBCQRAC.AC_NO;
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'C';
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = WS_DD_AC2;
        DDCUCRAC.CCY = "156";
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_AMT = GDCSPNPR.VAL.PNAMT;
        DDCUCRAC.OTHER_AC = WS_DD_AC1;
        WS_OTHER_AC = DDCUCRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        DDCUCRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUCRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUCRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUCRAC.OTHER_BR = "0" + DDCUCRAC.OTHER_BR;
        DDCUCRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUCRAC.TX_MMO = "A001";
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCUDRAC);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.GD_WITHDR_FLG = 'Y';
        DDCUDRAC.AC = WS_DD_AC1;
        DDCUDRAC.CCY = "156";
        DDCUDRAC.CCY_TYPE = '1';
        DDCUDRAC.TX_AMT = GDCSPNPR.VAL.PNAMT;
        DDCUDRAC.OTHER_AC = WS_DD_AC2;
        WS_OTHER_AC = DDCUDRAC.OTHER_AC;
        B170_02_GET_RLT_BR_INFO();
        if (pgmRtn) return;
        DDCUDRAC.OTHER_AC_NM = CICQACRI.O_DATA.O_AC_CNM;
        DDCUDRAC.OTHER_BR = "" + CICQACRI.O_DATA.O_OPN_BR;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.OTHER_BK_NM = BPCPQORG.CHN_NM;
        DDCUDRAC.REMARKS = GDCSPNPR.VAL.RMK;
        DDCUDRAC.TX_MMO = "A019";
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B030_OUTPUT_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSPNPR.KEY.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_ENM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_ENM);
        WS_AVA_RELAT_AMT = GDCUMPLD.AVA_RAMT - GDCSPNPR.VAL.RL_AMT;
        IBS.init(SCCGWA, GDCOMPRL);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        GDCOMPRL.VAL.CI_NO = CICACCU.DATA.CI_NO;
        GDCOMPRL.VAL.CI_NM = CICACCU.DATA.CI_CNM;
        GDCOMPRL.VAL.AC = GDCSPNPR.KEY.AC;
        if (GDCSPNPR.VAL.AC_NM.trim().length() == 0) {
            GDCOMPRL.VAL.AC_NM = CICACCU.DATA.AC_CNM;
        } else {
            GDCOMPRL.VAL.AC_NM = GDCSPNPR.VAL.AC_NM;
        }
        GDCOMPRL.VAL.AC_SEQ = GDCSPNPR.KEY.AC_SEQ;
        GDCOMPRL.VAL.PROD_CD = GDCUMPLD.PROD_CD;
        GDCOMPRL.VAL.CCY = GDCSPNPR.VAL.CCY;
        GDCOMPRL.VAL.CCY_TYP = GDCUMPLD.CCY_TYPE;
        GDCOMPRL.VAL.BAL = GDCUMPLD.BAL;
        CEP.TRC(SCCGWA, GDCOMPRL.VAL.BAL);
        GDCOMPRL.VAL.AVL_AMT = WS_AVA_RELAT_AMT;
        CEP.TRC(SCCGWA, GDCOMPRL.VAL.AVL_AMT);
        GDCOMPRL.VAL.CTA_NO = GDCSPNPR.VAL.CTA_NO;
        GDCOMPRL.VAL.REF_NO = GDCSPNPR.VAL.REF_NO;
        GDCOMPRL.VAL.BUSI_TYP = GDCSPNPR.VAL.BUSI_TYP;
        GDCOMPRL.VAL.RL_AMT = GDCSPNPR.VAL.RL_AMT;
        GDCOMPRL.VAL.EXP_DT = GDCSPNPR.VAL.EXP_DT;
        GDCOMPRL.VAL.RMK = GDCSPNPR.VAL.RMK;
        GDCOMPRL.VAL.RSEQ = GDCUMPLD.RSEQ;
        SCCFMT.DATA_PTR = GDCOMPRL;
        SCCFMT.DATA_LEN = 860;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B040_CHECK_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        IBS.init(SCCGWA, GDCRPLDR);
        GDRPLDR.DEAL_CD = GDCSPNPR.VAL.CTA_NO;
        GDRPLDR.BSREF = GDCSPNPR.VAL.REF_NO;
        GDRPLDR.KEY.AC = GDCSPNPR.KEY.AC;
        GDRPLDR.KEY.AC_SEQ = GDCSPNPR.KEY.AC_SEQ;
        T000_READ_CTA_REC_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void R000_CALL_FEE() throws IOException,SQLException,Exception {
        B015_SET_FEE_INFO_CN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = GDCSPNPR.VAL.FAC_NO;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = GDCSPNPR.VAL.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = GDCSPNPR.VAL.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS = GDCSPNPR.VAL.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_AMT = GDCSPNPR.VAL.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = GDCSPNPR.VAL.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = GDCSPNPR.VAL.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = GDCSPNPR.VAL.FEE_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = GDCSPNPR.VAL.FEE_CD;
        CEP.TRC(SCCGWA, BPCFFCON.RC);
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFFCON.RC);
    }
    public void R000_DR_DD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = GDCSPNPR.VAL.FAC_NO;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = GDCSPNPR.VAL.FAC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        DDCUDRAC.CCY = GDCSPNPR.VAL.CCY;
        DDCUDRAC.CCY_TYPE = GDCSPNPR.VAL.CCY_TYP;
        if (GDCSPNPR.VAL.CCY.equalsIgnoreCase("156")) {
            DDCUDRAC.CCY_TYPE = '1';
        }
        DDCUDRAC.TX_AMT = GDCSPNPR.VAL.FEE_AMT;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            DDCUDRAC.VAL_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        }
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.TX_MMO = "Q007";
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.BANK_DR_FLG = 'N';
        DDCUDRAC.OTHER_AC_NM = "票据�?来户";
        DDCUDRAC.RLT_AC = "30040806";
        DDCUDRAC.RLT_AC_NAME = "票据�?来户";
        DDCUDRAC.OTHER_BR = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        JIBS_tmp_int = DDCUDRAC.OTHER_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUDRAC.OTHER_BR = "0" + DDCUDRAC.OTHER_BR;
        DDCUDRAC.RLT_BK_NM = "票据�?来户";
        DDCUDRAC.REMARKS = " ";
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void R000_CR_ITM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPUITM);
        AICPUITM.DATA.EVENT_CODE = "CR";
        AICPUITM.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICPUITM.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICPUITM.DATA.CCY = GDCSPNPR.VAL.CCY;
        AICPUITM.DATA.ITM_NO = "30040806";
        AICPUITM.DATA.AMT = GDCSPNPR.VAL.FEE_AMT;
        AICPUITM.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICPUITM.DATA.THEIR_AC = GDCSPNPR.VAL.FAC_NO;
        AICPUITM.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICPUITM.DATA.THEIR_CCY = GDCSPNPR.VAL.CCY;
        AICPUITM.DATA.THEIR_AMT = GDCSPNPR.VAL.FEE_AMT;
        AICPUITM.DATA.ORI_AC = GDCSPNPR.VAL.FAC_NO;
        AICPUITM.DATA.PAY_MAN = CICACCU.DATA.AC_CNM;
        AICPUITM.DATA.PAY_BKNM = " ";
        AICPUITM.DATA.NARR_CD = " ";
        AICPUITM.DATA.DESC = " ";
        S000_CALL_AIZPUITM();
        if (pgmRtn) return;
    }
    public void R000_CALL_FEE_REG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSFHIS);
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.KEY.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRFEHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRNNO);
        BPRFEHIS.KEY.JRN_SEQ = 1;
        CEP.TRC(SCCGWA, BPRFEHIS.KEY.JRN_SEQ);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.ENTY_TYP = '1';
        CICACCU.DATA.AGR_NO = GDCSPNPR.KEY.AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        BPRFEHIS.TX_CI = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, BPRFEHIS.TX_CI);
        BPRFEHIS.TX_PROD = CICACCU.DATA.PROD_CD;
        BPRFEHIS.FEE_CODE = GDCSPNPR.VAL.FEE_CD;
        BPRFEHIS.DRCRFLG = 'D';
        BPRFEHIS.REQFM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BPRFEHIS.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPRFEHIS.CHG_BR = CICACCU.DATA.OPN_BR;
        BPRFEHIS.TX_AC = GDCSPNPR.KEY.AC;
        BPRFEHIS.CHG_AC = GDCSPNPR.VAL.FAC_NO;
        BPRFEHIS.CHG_AC_TY = '0';
        BPRFEHIS.FEE_CCY = GDCSPNPR.VAL.CCY;
        BPRFEHIS.FEE_BAS = GDCSPNPR.VAL.FEE_AMT;
        BPRFEHIS.FEE_AMT = GDCSPNPR.VAL.FEE_AMT;
        BPRFEHIS.CHG_CCY = GDCSPNPR.VAL.CCY;
        BPRFEHIS.CHG_BAS = GDCSPNPR.VAL.FEE_AMT;
        BPRFEHIS.CHG_AMT = GDCSPNPR.VAL.FEE_AMT;
        BPRFEHIS.ADJ_AMT = GDCSPNPR.VAL.FEE_AMT;
        BPRFEHIS.VAT_AMT = GDCSPNPR.VAL.FEE_AMT;
        CEP.TRC(SCCGWA, BPRFEHIS.VAT_AMT);
        BPRFEHIS.CHG_FLG = '0';
        BPRFEHIS.CCY_TYPE = '1';
        BPRFEHIS.REMARK = " ";
        BPRFEHIS.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRFEHIS.TX_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRFEHIS.TX_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRFEHIS.TX_STS = 'N';
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRFEHIS.TX_STS = 'R';
        }
        SCCGWA.COMM_AREA.FEE_IND = 'X';
        SCCGWA.COMM_AREA.FEE_DATA_IND = 'X';
        BPRFEHIS.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSFHIS.INP_DATA.DATA_LEN = 780;
        BPCSFHIS.INP_DATA.DATA_PTR = BPRFEHIS;
        S000_CALL_BPZSFHIS();
        if (pgmRtn) return;
    }
    public void B015_SET_FEE_INFO_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = GDCSPNPR.VAL.FAC_NO;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = GDCSPNPR.VAL.CCY;
        BPCFFTXI.TX_DATA.CCY_TYPE = GDCSPNPR.VAL.CCY_TYP;
        if (GDCSPNPR.VAL.CCY.equalsIgnoreCase("156")) {
            BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        }
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
    }
    public void B170_02_GET_RLT_BR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_OTHER_AC;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        IBS.init(SCCGWA, BPCPQORG);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = WS_OTHER_AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            BPCPQORG.BR = AICPQMIB.INPUT_DATA.BR;
        } else {
            BPCPQORG.BR = CICQACRI.O_DATA.O_OPN_BR;
        }
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
    }
    public void R000_UPD_MARG_BAL_PROC() throws IOException,SQLException,Exception {
        if (GDCSPNPR.VAL.AC_TYP == '0') {
            R000_UPD_DDTCCY_PROC();
            if (pgmRtn) return;
        } else {
            R000_UPD_TDTSMST_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_UPD_DDTCCY_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDCSPNPR.KEY.AC);
        CEP.TRC(SCCGWA, GDCSPNPR.VAL.CCY);
        CEP.TRC(SCCGWA, GDCSPNPR.VAL.CCY_TYP);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCSPNPR.KEY.AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRCCY.CUS_AC = GDCSPNPR.KEY.AC;
        DDRCCY.CCY = GDCSPNPR.VAL.CCY;
        DDRCCY.CCY_TYPE = GDCSPNPR.VAL.CCY_TYP;
        T000_READUPD_DDTCCY();
        if (pgmRtn) return;
        DDRCCY.MARGIN_BAL = DDRCCY.MARGIN_BAL + GDCSPNPR.VAL.RL_AMT;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DDTCCY();
        if (pgmRtn) return;
    }
    public void R000_UPD_TDTSMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCSPNPR.KEY.AC;
        CICQACAC.DATA.AGR_SEQ = GDCSPNPR.KEY.AC_SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READUPD_TDTSMST();
        if (pgmRtn) return;
        TDRSMST.GUAR_BAL = TDRSMST.GUAR_BAL + GDCSPNPR.VAL.RL_AMT;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_TDTSMST();
        if (pgmRtn) return;
    }
    public void R000_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA.BA_CNT = (short) (GWA_SC_AREA.BA_CNT + 1);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BA_CNT);
        IBS.init(SCCGWA, GDRHIS);
        GDCRHIS.FUNC = 'A';
        GDRHIS.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        GDRHIS.KEY.SEQ = GWA_SC_AREA.BA_CNT;
        GDRHIS.RSEQ = GDRPLDR.KEY.RSEQ;
        GDRHIS.AC = GDCSPNPR.KEY.AC;
        GDRHIS.AC_SEQ = GDCSPNPR.KEY.AC_SEQ;
        CEP.TRC(SCCGWA, GDCSPNPR.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, GDRHIS.KEY.SEQ);
        CEP.TRC(SCCGWA, GDRHIS.KEY.TR_DATE);
        CEP.TRC(SCCGWA, GDRHIS.KEY.JRNNO);
        GDRHIS.FUNC = '3';
        GDRHIS.DEAL_CD = GDCSPNPR.VAL.CTA_NO;
        GDRHIS.BSREF = GDCSPNPR.VAL.REF_NO;
        GDRHIS.CHNL_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        GDRHIS.TR_AMT = GDCSPNPR.VAL.RL_AMT;
        GDRHIS.CAN_FLG = 'N';
        GDRHIS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDRHIS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        GDRHIS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        GDCRHIS.REC_PTR = GDRHIS;
        GDCRHIS.REC_LEN = 281;
        S000_CALL_GDZRHIS();
        if (pgmRtn) return;
    }
    public void R000_WRITE_NON_FIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = GDCSPNPR.KEY.AC;
        BPCPNHIS.INFO.AC_SEQ = GDCSPNPR.KEY.AC_SEQ;
        BPCPNHIS.INFO.CCY = GDCSPNPR.VAL.CCY;
        BPCPNHIS.INFO.CCY_TYPE = GDCSPNPR.VAL.CCY_TYP;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = GDRPLDR.KEY.RSEQ;
        BPCPNHIS.INFO.CI_NO = GDCSPNPR.VAL.CI_NO;
        BPCPNHIS.INFO.TX_RMK = GDCSPNPR.VAL.RMK;
        BPCPNHIS.INFO.TX_TYP_CD = "A310";
        CEP.TRC(SCCGWA, "ADD");
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.NEW_DAT_PT = GDCUMPLD;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ( BAL _ HBAL _ GUAR_BAL ) > 0 "
            + "AND ACO_STS = '0'";
        TDTSMST_BR.rp.upd = true;
        TDTSMST_BR.rp.order = "VAL_DATE,ACO_AC";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACO-AC =");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_SMST_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB, true);
        CEP.TRC(SCCGWA, AICPQMIB.RC.RC_CODE);
        if (AICPQMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZRPLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRPLDR", GDCRPLDR);
        if (GDCUMPLD.FUNC != 'C') {
            if (GDCRPLDR.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRPLDR.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZRHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRC-GDZRHIS", GDCRHIS);
        if (GDCRHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, GDCRHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG, true);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_IBZQRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-INQ-RELA-AC", IBCQRAC, true);
        CEP.TRC(SCCGWA, IBCQRAC.RC);
        if (IBCQRAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCQRAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZCRAC", IBCPOSTA, true);
        CEP.TRC(SCCGWA, IBCPOSTA.RC);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZDRAC", IBCPOSTA, true);
        CEP.TRC(SCCGWA, IBCPOSTA.RC);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC.RC_CODE);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-WRITE-FHIS", BPCSFHIS, true);
        CEP.TRC(SCCGWA, BPCSFHIS.RC.RC_CODE);
        if (BPCSFHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSFHIS.RC);
        }
    }
    public void S000_CALL_AIZPUITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-ITM", AICPUITM, true);
        if (AICPUITM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICPUITM.RC);
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACO-AC =");
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READUPD_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC =");
            CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_CTA_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF "
            + "AND RELAT_STS = 'N'";
        GDTPLDR_RD.upd = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLDR_FLG = 'N';
        } else {
            WS_PLDR_FLG = 'Y';
        }
    }
    public void T000_READ_FIRST_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND DEAL_CD = :GDRPLDR.DEAL_CD "
            + "AND BSREF = :GDRPLDR.BSREF";
        GDTPLDR_RD.fst = true;
        IBS.READ(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLDR_FLG = 'N';
        } else {
            WS_PLDR_FLG = 'Y';
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZSRLSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSRLSR", GDCSRLSR, true);
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        IBS.REWRITE(SCCGWA, GDRPLDR, GDTPLDR_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
