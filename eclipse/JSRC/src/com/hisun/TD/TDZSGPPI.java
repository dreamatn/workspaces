package com.hisun.TD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSGPPI {
    int JIBS_tmp_int;
    brParm TDTGROP_BR = new brParm();
    DBParm TDTGROP_RD;
    DBParm TDTSMST_RD;
    DBParm TDTCMST_RD;
    String JIBS_tmp_str[] = new String[10];
    String K_AP_MMO = "TD";
    String K_OUTPUT_FMT = "DD129";
    String K_OUTPUT_FMT1 = "TD016";
    String WS_MSGID = " ";
    String WS_CI_NO = " ";
    String WS_OPP_CI_NO = " ";
    double WS_GROP_AMT = 0;
    String WS_PROD_CD = " ";
    String WS_AC_TYPE = " ";
    double WS_PAYING_INT = 0;
    short WS_COUNT1 = 0;
    short WS_COUNT = 0;
    TDZSGPPI_WS_TDRFHIS WS_TDRFHIS = new TDZSGPPI_WS_TDRFHIS();
    char WS_CRE_AC_FLG = ' ';
    char WS_READ_FLAG = ' ';
    char WS_READ_GROP_FLAG = ' ';
    int WS_SMST_CNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUABOX BPCUABOX = new BPCUABOX();
    TDCACCRU TDCACCRU = new TDCACCRU();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    SCCFMT SCCFMT = new SCCFMT();
    TDCOTRAC TDCOTRAC = new TDCOTRAC();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    CICACCU CICACCU = new CICACCU();
    TDCOTZZC TDCOTZZC = new TDCOTZZC();
    DCCPFTCK DCCPFTCK = new DCCPFTCK();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDCMACO TDCMACO = new TDCMACO();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICQACRL CICQACRL = new CICQACRL();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DDCIMPAY DDCIMPAY = new DDCIMPAY();
    TDCMACC TDCMACC = new TDCMACC();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    CICSACAC CICSACAC = new CICSACAC();
    TDRGROP TDRGROP = new TDRGROP();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    TDRFHIS TDRFHIS = new TDRFHIS();
    SCCGWA SCCGWA;
    TDCGPPI TDCGPPI;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCGPPI TDCGPPI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCGPPI = TDCGPPI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSGPPI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCGPPI.AC_NO);
        CEP.TRC(SCCGWA, TDCGPPI.ACO_AC);
        CEP.TRC(SCCGWA, TDCGPPI.CCY);
        CEP.TRC(SCCGWA, TDCGPPI.PAYING_INT);
        CEP.TRC(SCCGWA, TDCGPPI.PAYING_TAX);
        CEP.TRC(SCCGWA, TDCGPPI.CI_NO);
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDCGPPI.AC_NO;
        T000_READ_SMST_FX();
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        R000_GET_SALE_PROD_INFO();
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = TDRSMST.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = "DR";
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = 0;
        BPCPOEWA.DATA.AMT_INFO[5-1].AMT = 0;
        BPCPOEWA.DATA.AMT_INFO[4-1].AMT = TDCGPPI.PAYING_INT;
        BPCPOEWA.DATA.AMT_INFO[3-1].AMT = TDCGPPI.PAYING_TAX;
        BPCPOEWA.DATA.DESC = "S104";
        BPCPOEWA.DATA.BR_OLD = TDRSMST.CHE_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = TDCGPPI.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = TDRSMST.KEY.ACO_AC;
        BPCPOEWA.DATA.CI_NO = TDCGPPI.CI_NO;
        BPCPOEWA.DATA.REF_NO = " ";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[4-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[5-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[6-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[7-1].AMT);
        if (BPCPOEWA.DATA.AMT_INFO[1-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[2-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[3-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[4-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[5-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[6-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[7-1].AMT == 0) {
        } else {
            S000_CALL_BPZPOEWA();
            WS_PAYING_INT = TDCGPPI.PAYING_INT - TDCGPPI.PAYING_TAX;
        }
        IBS.init(SCCGWA, TDRGROP);
        TDRGROP.AC_NO = TDCGPPI.AC_NO;
        TDRGROP.KEY.DRAW_DT = SCCGWA.COMM_AREA.AC_DATE;
        T000_STARTBR_TDTGROP();
        T000_READNEXT_TDTGROP();
        while (WS_READ_GROP_FLAG != 'Y') {
            TDRGROP.TYPE = "N";
            T000_REWRITE_TDTGROP();
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = TDRGROP.KEY.ACO_AC;
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            WS_GROP_AMT += TDRGROP.INT_BAL;
            T000_READU_SMST_XH();
            CEP.TRC(SCCGWA, TDRSMST.BAL);
            if (TDRSMST.BAL == 0 
                && TDRSMST.ACO_STS == '0') {
                TDRSMST.ACO_STS = '1';
                T000_REWRITE_SMST();
                B250_GEN_CI_AC_INF();
                B260_GEN_EVET();
            }
            T000_READNEXT_TDTGROP();
        }
        T000_ENDBR_TDTGROP();
        CEP.TRC(SCCGWA, WS_GROP_AMT);
        CEP.TRC(SCCGWA, TDCGPPI.PAYING_INT);
        if (TDCGPPI.PAYING_INT > 0) {
            if (WS_GROP_AMT != TDCGPPI.PAYING_INT) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AMOUNT_ERROR);
            }
            B230_CALL_DD_CR_UNT();
        }
    }
    public void B260_GEN_EVET() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = TDRSMST.PROD_CD;
        BPCPOEWA.DATA.EVENT_CODE = "DR";
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = 0;
        if (TDRSMST.DRW_BUD_DATE >= SCCGWA.COMM_AREA.AC_DATE) {
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = TDRSMST.BUD_INT - TDRSMST.DRW_BUD_INT;
        } else {
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = TDRSMST.BUD_INT;
        }
        BPCPOEWA.DATA.AMT_INFO[5-1].AMT = 0;
        BPCPOEWA.DATA.AMT_INFO[4-1].AMT = 0;
        BPCPOEWA.DATA.AMT_INFO[3-1].AMT = 0;
        BPCPOEWA.DATA.DESC = "S104";
        BPCPOEWA.DATA.BR_OLD = TDRSMST.CHE_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = TDCGPPI.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = TDRSMST.KEY.ACO_AC;
        BPCPOEWA.DATA.CI_NO = TDCGPPI.CI_NO;
        BPCPOEWA.DATA.REF_NO = " ";
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[1-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[3-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[4-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[5-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[6-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[7-1].AMT);
        if (BPCPOEWA.DATA.AMT_INFO[1-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[2-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[3-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[4-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[5-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[6-1].AMT == 0 
            && BPCPOEWA.DATA.AMT_INFO[7-1].AMT == 0) {
        } else {
            S000_CALL_BPZPOEWA();
        }
    }
    public void B630_WRT_BPTOCAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = TDRSMST.AC_NO;
        BPCSOCAC.ACO_AC = TDRSMST.KEY.ACO_AC;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.WORK_TYP = "12";
        BPCSOCAC.CI_TYPE = '1';
        BPCSOCAC.SEQ = 1;
        BPCSOCAC.CCY = "156";
        BPCSOCAC.CCY_TYPE = '1';
        BPCSOCAC.AUT_TLR = " ";
        BPCSOCAC.CLOSE_DATE = TDRSMST.CLO_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.OPEN_DATE = TDRSMST.OPEN_DATE;
        BPCSOCAC.OPEN_TLR = TDRSMST.CRT_TLR;
        BPCSOCAC.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BPCSOCAC.OPEN_AMT = TDRSMST.BAL;
        BPCSOCAC.PROD_CD = TDRSMST.PROD_CD;
        BPCSOCAC.BR = TDRSMST.OWNER_BR;
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        BPCSOCAC.LOSS_INT = 0;
        BPCSOCAC.LOSS_TAX = 0;
        BPCSOCAC.LOSS_AMT = 0;
        S000_CALL_BPZSOCAC();
    }
    public void B270_WRT_FHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PAYING_INT);
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.TX_TOOL = TDRSMST.OPEN_DR_AC;
        DCCPFTCK.VAL.TXN_TYPE = "03";
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.CI_NO = TDCGPPI.CI_NO;
        BPCPFHIS.DATA.ACO_AC = TDRSMST.KEY.ACO_AC;
        BPCPFHIS.DATA.AC = TDCGPPI.AC_NO;
        BPCPFHIS.DATA.OTH_AC = TDRSMST.OPEN_DR_AC;
        BPCPFHIS.DATA.OTH_TX_TOOL = TDRSMST.OPEN_DR_AC;
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.TX_CCY = "156";
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_AMT = WS_PAYING_INT;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCPFHIS.DATA.TX_AMT = BPCPFHIS.DATA.TX_AMT * -1;
        }
        BPCPFHIS.DATA.TX_MMO = "S104";
        BPCPFHIS.DATA.PROD_CD = TDRSMST.PROD_CD;
        WS_TDRFHIS.WS_FHIS_SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        WS_TDRFHIS.WS_FHIS_TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBS.init(SCCGWA, CICACCU);
        CEP.TRC(SCCGWA, TDCGPPI.AC_NO);
        CICACCU.DATA.AGR_NO = TDCGPPI.AC_NO;
        S000_CALL_CIZACCU();
        WS_TDRFHIS.WS_FHIS_AC_NAME = CICACCU.DATA.AC_CNM;
        WS_COUNT1 = 0;
        WS_COUNT1 = 275;
        CEP.TRC(SCCGWA, WS_COUNT1);
        IBS.init(SCCGWA, TDRFHIS);
        TDRFHIS.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        TDRFHIS.TX_TIME = SCCGWA.COMM_AREA.TR_TIME;
        if (TDRFHIS.DATA_FIELD_TEXT.trim().length() == 0 
            && TDRFHIS.INSTR_MTH == ' ') {
            BPCPFHIS.DATA.FMT_LEN = WS_COUNT1;
        } else {
            BPCPFHIS.DATA.FMT_LEN = 1276;
        }
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, TDRFHIS);
        CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_DRCR_FLG);
        CEP.TRC(SCCGWA, BPCPFHIS);
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_CCY_TYP);
        BPCPFHIS.DATA.TX_TYPE = 'T';
        BPCPFHIS.DATA.RLT_AC = TDRSMST.OPEN_DR_AC;
        IBS.init(SCCGWA, CICACCU);
        CEP.TRC(SCCGWA, TDRSMST.OPEN_DR_AC);
        CICACCU.DATA.AGR_NO = TDRSMST.OPEN_DR_AC;
        S000_CALL_CIZACCU();
        BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_CNM;
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            BPCPFHIS.DATA.RLT_AC_NAME = CICACCU.DATA.AC_ENM;
        }
        BPCPFHIS.DATA.RLT_BANK = "" + CICACCU.DATA.OPN_BR;
        JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
        S000_CALL_BPZPFHIS();
    }
    public void B230_CALL_DD_CR_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = TDRSMST.OPEN_DR_AC;
        DDCUCRAC.AC = TDRSMST.OPEN_DR_AC;
        DDCUCRAC.CCY = TDRSMST.CCY;
        DDCUCRAC.CCY_TYPE = TDRSMST.CCY_TYP;
        DDCUCRAC.TX_AMT = TDCGPPI.PAYING_INT - TDCGPPI.PAYING_TAX;
        DDCUCRAC.OTHER_AC = TDRSMST.AC_NO;
        DDCUCRAC.OTH_TX_TOOL = TDRSMST.AC_NO;
        DDCUCRAC.OTHER_CCY = TDRSMST.CCY;
        DDCUCRAC.TX_MMO = "S104";
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'N';
        S000_CALL_DDZUCRAC();
    }
    public void B250_GEN_CI_AC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'D';
        CICSACAC.DATA.PROD_CD = TDRSMST.PROD_CD;
        CICSACAC.DATA.ACAC_NO = TDRSMST.KEY.ACO_AC;
        CICSACAC.DATA.AGR_NO = TDRSMST.AC_NO;
        CICSACAC.DATA.CCY = "156";
        CICSACAC.DATA.CR_FLG = '1';
        CICSACAC.DATA.FRM_APP = "TD";
        CICSACAC.DATA.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSACAC.DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSACAC();
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            CEP.ERR(SCCGWA, WS_MSGID);
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_BPZSOCAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-OCAC-INFO", BPCSOCAC);
        if (BPCSOCAC.RC.RC_CODE != 0) {
            WS_MSGID = "" + BPCSOCAC.RC.RC_CODE;
            JIBS_tmp_int = WS_MSGID.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_MSGID = "0" + WS_MSGID;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZMACC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ZM-ACC-PROC", TDCMACC);
    }
    public void R000_GET_SALE_PROD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = TDRSMST.PROD_CD;
        S000_CALL_BPZPQPRD();
        WS_AC_TYPE = BPCPQPRD.AC_TYPE;
    }
    public void T000_STARTBR_TDTGROP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRGROP.KEY.DRAW_DT);
        TDTGROP_BR.rp = new DBParm();
        TDTGROP_BR.rp.TableName = "TDTGROP";
        TDTGROP_BR.rp.where = "AC_NO = :TDRGROP.AC_NO "
            + "AND DRAW_DT < :TDRGROP.KEY.DRAW_DT "
            + "AND TYPE = 'Y'";
        TDTGROP_BR.rp.upd = true;
        TDTGROP_BR.rp.order = "DRAW_DT";
        IBS.STARTBR(SCCGWA, TDRGROP, this, TDTGROP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_READ_GROP_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_READ_GROP_FLAG = 'Y';
        }
    }
    public void T000_READNEXT_TDTGROP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRGROP, this, TDTGROP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_READ_GROP_FLAG = 'Y';
        }
    }
    public void T000_ENDBR_TDTGROP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTGROP_BR);
    }
    public void T000_REWRITE_TDTGROP() throws IOException,SQLException,Exception {
        TDTGROP_RD = new DBParm();
        TDTGROP_RD.TableName = "TDTGROP";
        IBS.REWRITE(SCCGWA, TDRGROP, TDTGROP_RD);
    }
    public void T000_READ_SMST_FX() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND SUBSTR ( STSW , 52 , 1 ) < > '1'";
        TDTSMST_RD.fst = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_SMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READU_SMST_XH() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_SMST() throws IOException,SQLException,Exception {
        TDRSMST.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSMST.CLO_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CPN-DCZUPSWM", DCCUPSWM);
        if (DCCUPSWM.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCUPSWM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_GET_CI_INFO() throws IOException,SQLException,Exception {
    }
    public void R000_CHECK_DD_CARD_PROC() throws IOException,SQLException,Exception {
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_COUNT_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.set = "WS-SMST-CNT=COUNT(*)";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        IBS.GROUP(SCCGWA, TDRSMST, this, TDTSMST_RD);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void S000_CALL_DCZPFTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-P-FINANCE-TR-CHK", DCCPFTCK);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_TDZACCRU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACCR-UNT", TDCACCRU);
    }
    public void S000_CALL_TDZACCRU_NOFMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ACCR-UNT", TDCACCRU, true);
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCZPDRAW() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void S000_CALL_TDZMACO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-MACO-CR", TDCMACO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
