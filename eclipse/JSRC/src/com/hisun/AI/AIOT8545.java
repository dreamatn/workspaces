package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFTLRQ;
import com.hisun.BP.BPCOCLWD;
import com.hisun.BP.BPCOQCAL;
import com.hisun.BP.BPCOQPCD;
import com.hisun.BP.BPCPORLO;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQBNK_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQVCH;
import com.hisun.BP.BPCPRMB;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPCSIC;
import com.hisun.BP.BPRCTL;
import com.hisun.BP.BPRPRMT;
import com.hisun.BP.BPRTLT;
import com.hisun.BP.BPRTRT;
import com.hisun.BP.BPRVCH01;
import com.hisun.BP.BPRVCH02;
import com.hisun.BP.BPRVCHH;
import com.hisun.BP.BPRVCHT;
import com.hisun.SC.SCCAWAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGCPT;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class AIOT8545 {
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUT_FMT = "AIX01";
    int K_MAX_DAYS = 365;
    char K_D_TABLE = 'D';
    int K_BEGDATE = 0;
    int K_ENDDATE = 99991231;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    short WS_I = 0;
    short WS_L = 0;
    short WS_J = 0;
    short WS_K = 0;
    String WS_ERR_MSG = " ";
    String WS_INFO = " ";
    String WS_ERR_INFO = " ";
    int WS_OP_CNT = 0;
    short WS_DAYS = 0;
    int WS_DATE_FR = 0;
    int WS_DATE_TO = 0;
    short WS_SENS_LVL1 = 0;
    String WS_LAST_ITM = " ";
    String WS_LAST_CHS_NM = " ";
    char WS_FUNC = ' ';
    int WS_CNTA = 0;
    int WS_CNT = 0;
    int WS_DT_NUM = 0;
    int WS_DT_NUM1 = 0;
    char WS_LVL = ' ';
    char WS_LVL1 = ' ';
    int WS_IDX = 0;
    AIOT8545_WS_SUPR_GRP WS_SUPR_GRP = new AIOT8545_WS_SUPR_GRP();
    char WS_FLG = ' ';
    char WS_FLG1 = ' ';
    char WS_FLG2 = ' ';
    int WS_LAST_BR = 0;
    int WS_PORLO_BR = 0;
    char WS_INQ_FLG = ' ';
    AIOT8545_WS_GRP_NO_OUTPUT WS_GRP_NO_OUTPUT = new AIOT8545_WS_GRP_NO_OUTPUT();
    short WS_AC_NUM = 0;
    short WS_AC_NUM1 = 0;
    AIOT8545_WS_OUTPUT WS_OUTPUT = new AIOT8545_WS_OUTPUT();
    AIOT8545_WS_OUTPUT2 WS_OUTPUT2 = new AIOT8545_WS_OUTPUT2();
    AIOT8545_WS_FLD_NAME WS_FLD_NAME = new AIOT8545_WS_FLD_NAME();
    AIOT8545_WS_D_FLD WS_D_FLD = new AIOT8545_WS_D_FLD();
    String WS_T_CTL = " ";
    AIOT8545_REDEFINES78 REDEFINES78 = new AIOT8545_REDEFINES78();
    AIOT8545_REDEFINES87 REDEFINES87 = new AIOT8545_REDEFINES87();
    int WS_TBEGDAT = 0;
    int WS_TENDDAT = 0;
    int WS_VBEGDAT = 0;
    int WS_VENDDAT = 0;
    String WS_MINITM = " ";
    String WS_MAXITM = " ";
    int WS_BR_FR = 0;
    int WS_BR_TO = 0;
    double WS_END_AMT = 0;
    AIOT8545_WS_CONSTANT WS_CONSTANT = new AIOT8545_WS_CONSTANT();
    char WS_BPRVCHT_FLG = ' ';
    char WS_BPRVCHH_FLG = ' ';
    char WS_AITITM_FLG = ' ';
    char WS_REC2_INFO = ' ';
    char WS_TBL_FLAG = ' ';
    BPRCTL BPRCTLT = new BPRCTL();
    BPRCTL BPRCTLD = new BPRCTL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCPAGE SCCPAGE = new SCCPAGE();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCPQVCH BPCQVCH = new BPCPQVCH();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCSIC BPCSIC = new BPCSIC();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQCAL BPCOQCAL = new BPCOQCAL();
    AICX506 AICX506 = new AICX506();
    AICX513 AICX513 = new AICX513();
    AICX538 AICX538 = new AICX538();
    AICPQITM AICPQITM = new AICPQITM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPCOA_COMMON9_VAR AICPCOA_COMMON9_VAR = new AICPCOA_COMMON9_VAR();
    BPCPORLO BPCPORLO = new BPCPORLO();
    SCCQJRN SCCQJRN = new SCCQJRN();
    BPRVCH01 BPRVCH01 = new BPRVCH01();
    BPRVCH02 BPRVCH02 = new BPRVCH02();
    BPRVCHT BPRVCHT = new BPRVCHT();
    BPRVCHH BPRVCHH = new BPRVCHH();
    AIRMST AIRMST = new AIRMST();
    AIRHMST AIRHMST = new AIRHMST();
    CMRPOSTC CMRPOSTC = new CMRPOSTC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    BPRTLT BPRTLT;
    AIB8545_AWA_8545 AIB8545_AWA_8545;
    SCCAWAC SCCAWAC;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT8545 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB8545_AWA_8545>");
        AIB8545_AWA_8545 = (AIB8545_AWA_8545) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        IBS.init(SCCGWA, SCCMSG);
        CEP.TRC(SCCGWA, BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL);
        WS_T_CTL = BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL;
        IBS.CPY2CLS(SCCGWA, WS_T_CTL, REDEFINES78);
        IBS.CPY2CLS(SCCGWA, WS_T_CTL, REDEFINES87);
        SCCGSCA_SC_AREA.CURS_POS = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B03_OUTPUT_INFO_TO_SCREEN();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTVCHT() throws IOException,SQLException,Exception {
        BPTVCHT_BR.rp = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTVCHT_BR.rp.TableName = "BPTVCHT1";
        else BPTVCHT_BR.rp.TableName = "BPTVCHT2";
        BPTVCHT_BR.rp.where = "BR = :WS_BR_FR "
            + "AND ITM = :WS_MINITM "
            + "AND ( CCY = :BPRVCHH.CCY "
            + "OR ' ' = :BPRVCHH.CCY ) "
            + "AND AC_DATE = :WS_TBEGDAT "
            + "AND BOOK_FLG = :BPRVCHH.BOOK_FLG "
            + "AND ( ' ' = :BPRVCHT.SIGN "
            + "OR SIGN = :BPRVCHT.SIGN ) "
            + "AND ( ( ' ' = :BPRVCHT.AMT "
            + "AND 0 = :WS_END_AMT ) "
            + "OR ( AMT BETWEEN :BPRVCHT.AMT "
            + "AND :WS_END_AMT ) )";
        BPTVCHT_BR.rp.order = "ITM,BR,CCY,AC_DATE,TR_DATE,TR_TIME";
        IBS.STARTBR(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARBR TABLE BPTVCHT ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTVCHT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRVCHT, this, BPTVCHT_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPRVCHH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPRVCHH_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READNEXT TABLE BPTVCHT ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTVCHT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_CLOSE_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVCHT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CLOSE BPTVCHT ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTVCHT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B01_02_OUTPUT_INFO_BPTVCHT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICX513);
        AICX513.L_FLAG = BPRVCHT.BOOK_FLG;
        if (BPRVCHT.ODE_GRP_NO == 0) {
            AICX513.L_GRP_NO = 0;
        } else {
            if (BPRVCHT.FLR == null) BPRVCHT.FLR = "";
            JIBS_tmp_int = BPRVCHT.FLR.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) BPRVCHT.FLR += " ";
            if (BPRVCHT.FLR.substring(6 - 1, 6 + 8 - 1).trim().length() > 0) {
                if (BPRVCHT.FLR == null) BPRVCHT.FLR = "";
                JIBS_tmp_int = BPRVCHT.FLR.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) BPRVCHT.FLR += " ";
                if (BPRVCHT.FLR.substring(6 - 1, 6 + 8 - 1).trim().length() == 0) WS_GRP_NO_OUTPUT.WS_INPUT_DT = 0;
                else WS_GRP_NO_OUTPUT.WS_INPUT_DT = Integer.parseInt(BPRVCHT.FLR.substring(6 - 1, 6 + 8 - 1));
                WS_GRP_NO_OUTPUT.WS_GRP_NO = BPRVCHT.ODE_GRP_NO;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_GRP_NO_OUTPUT);
                if (JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1).trim().length() == 0) AICX513.L_GRP_NO = 0;
                else AICX513.L_GRP_NO = Integer.parseInt(JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1));
            } else {
                AICX513.L_GRP_NO = 0;
            }
        }
        AICX513.L_ACCENTRE = BPRVCHT.BR;
        AICX513.L_COANO = BPRVCHT.ITM;
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.BOOK_FLG = BPRVCHT.BOOK_FLG;
        AICPQITM.INPUT_DATA.NO = BPRVCHT.ITM;
        CEP.TRC(SCCGWA, WS_LAST_ITM);
        if (!AICPQITM.INPUT_DATA.NO.equalsIgnoreCase(WS_LAST_ITM)) {
            WS_LAST_ITM = AICPQITM.INPUT_DATA.NO;
            S00_CALL_AIZPQITM();
            if (pgmRtn) return;
            AICX513.L_CHS_NM = AICPQITM.OUTPUT_DATA.CHS_NM;
            WS_LAST_CHS_NM = AICPQITM.OUTPUT_DATA.CHS_NM;
        } else {
            AICX513.L_CHS_NM = WS_LAST_CHS_NM;
        }
        CEP.TRC(SCCGWA, WS_LAST_ITM);
        CEP.TRC(SCCGWA, WS_LAST_CHS_NM);
        AICX513.L_CURCODE = BPRVCHT.CCY;
        AICX513.L_VCHNO = BPRVCHT.KEY.SET_NO;
        AICX513.L_SET_SEQ = BPRVCHT.KEY.SET_SEQ;
        AICX513.L_VALDATE = BPRVCHT.VAL_DATE;
        AICX513.L_TRNDATE = BPRVCHT.KEY.AC_DATE;
        if (BPRVCHT.ODE_GRP_NO == 0) {
            AICX513.L_GRP_NO = 0;
        } else {
            if (BPRVCHT.FLR == null) BPRVCHT.FLR = "";
            JIBS_tmp_int = BPRVCHT.FLR.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) BPRVCHT.FLR += " ";
            if (BPRVCHT.FLR.substring(6 - 1, 6 + 8 - 1).trim().length() > 0) {
                if (BPRVCHT.FLR == null) BPRVCHT.FLR = "";
                JIBS_tmp_int = BPRVCHT.FLR.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) BPRVCHT.FLR += " ";
                if (BPRVCHT.FLR.substring(6 - 1, 6 + 8 - 1).trim().length() == 0) WS_GRP_NO_OUTPUT.WS_INPUT_DT = 0;
                else WS_GRP_NO_OUTPUT.WS_INPUT_DT = Integer.parseInt(BPRVCHT.FLR.substring(6 - 1, 6 + 8 - 1));
                WS_GRP_NO_OUTPUT.WS_GRP_NO = BPRVCHT.ODE_GRP_NO;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_GRP_NO_OUTPUT);
                if (JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1).trim().length() == 0) AICX513.L_GRP_NO = 0;
                else AICX513.L_GRP_NO = Integer.parseInt(JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1));
            } else {
                AICX513.L_GRP_NO = 0;
            }
        }
        AICX513.L_DRCRIND = BPRVCHT.SIGN;
        AICX513.L_AMT = BPRVCHT.AMT;
        CEP.TRC(SCCGWA, WS_CONSTANT.WS_DATE_FUNC);
        AICX513.L_REFNO = BPRVCHT.REF_NO;
        AICX513.L_PROD_TYPE = BPRVCHT.PROD_CODE;
        AICX513.L_EVENCODE = BPRVCHT.EVENT_CODE;
        AICX513.L_CINO = BPRVCHT.CI_NO;
        AICX513.L_MIB_AC = BPRVCHT.AC_NO;
        AICX513.L_CONTTYPE = BPRVCHT.CNTR_TYPE_REL;
        AICX513.L_POST_NARR = BPRVCHT.POST_NARR;
        AICX513.L_NARR_CD = BPRVCHT.NARR_CD;
        AICX513.L_CHNL_NO = BPRVCHT.CHNL_NO;
        AICX513.L_OTHSYS_KEY = BPRVCHT.OTHSYS_KEY;
        AICX513.L_APP = BPRVCHT.AP_MMO;
        AICX513.L_TRNTYPE = BPRVCHT.TR_TYPE;
        AICX513.L_GLMSTNO = BPRVCHT.GLMST;
        AICX513.L_DESC = BPRVCHT.DESC;
        CEP.TRC(SCCGWA, AICX513.L_FLAG);
        CEP.TRC(SCCGWA, AICX513.L_ACCENTRE);
        CEP.TRC(SCCGWA, AICX513.L_COANO);
        CEP.TRC(SCCGWA, AICX513.L_CHS_NM);
        CEP.TRC(SCCGWA, AICX513.L_CURCODE);
        CEP.TRC(SCCGWA, AICX513.L_VCHNO);
        CEP.TRC(SCCGWA, AICX513.L_SET_SEQ);
        CEP.TRC(SCCGWA, AICX513.L_VALDATE);
        CEP.TRC(SCCGWA, AICX513.L_TRNDATE);
        CEP.TRC(SCCGWA, AICX513.L_GRP_NO);
        CEP.TRC(SCCGWA, AICX513.L_DRCRIND);
        CEP.TRC(SCCGWA, AICX513.L_AMT);
        CEP.TRC(SCCGWA, AICX513.L_REFNO);
        CEP.TRC(SCCGWA, AICX513.L_PROD_TYPE);
        CEP.TRC(SCCGWA, AICX513.L_EVENCODE);
        CEP.TRC(SCCGWA, AICX513.L_CINO);
        CEP.TRC(SCCGWA, AICX513.L_MIB_AC);
        CEP.TRC(SCCGWA, AICX513.L_CONTTYPE);
        CEP.TRC(SCCGWA, AICX513.L_POST_NARR);
        CEP.TRC(SCCGWA, AICX513.L_NARR_CD);
        CEP.TRC(SCCGWA, AICX513.L_CHNL_NO);
        CEP.TRC(SCCGWA, AICX513.L_OTHSYS_KEY);
        CEP.TRC(SCCGWA, AICX513.L_APP);
        CEP.TRC(SCCGWA, AICX513.L_TRNTYPE);
        CEP.TRC(SCCGWA, AICX513.L_GLMSTNO);
        CEP.TRC(SCCGWA, AICX513.L_DESC);
        WS_FLG2 = 'Y';
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, AICX513);
        SCCMPAG.DATA_LEN = 577;
        SCCMPAG.FUNC = 'D';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R00_01_OUTPUT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRVCHT.BOOK_FLG);
        CEP.TRC(SCCGWA, BPRVCHT.BR);
        CEP.TRC(SCCGWA, BPRVCHT.CCY);
        CEP.TRC(SCCGWA, BPRVCHT.VAL_DATE);
        CEP.TRC(SCCGWA, BPRVCHT.KEY.SET_NO);
        CEP.TRC(SCCGWA, BPRVCHT.KEY.SET_SEQ);
        CEP.TRC(SCCGWA, BPRVCHT.JRN_NO);
        CEP.TRC(SCCGWA, BPRVCHT.AP_MMO);
        CEP.TRC(SCCGWA, BPRVCHT.EVENT_CODE_REL);
        CEP.TRC(SCCGWA, BPRVCHT.SIGN);
        CEP.TRC(SCCGWA, BPRVCHT.AMT);
        CEP.TRC(SCCGWA, BPRVCHT.TR_TYPE);
        CEP.TRC(SCCGWA, BPRVCHT.CI_NO);
        CEP.TRC(SCCGWA, BPRVCHT.AC_NO_REL);
        CEP.TRC(SCCGWA, BPRVCHT.CNTR_TYPE_REL);
        CEP.TRC(SCCGWA, BPRVCHT.PROD_CODE);
        CEP.TRC(SCCGWA, BPRVCHT.REF_NO);
        CEP.TRC(SCCGWA, AICX506);
        CEP.TRC(SCCGWA, BPRVCHT.TR_DATE);
        CEP.TRC(SCCGWA, BPRVCHT.TR_TIME);
        CEP.TRC(SCCGWA, BPRVCHT.KEY.AC_DATE);
        CEP.TRC(SCCGWA, BPRVCHT.VAL_DATE);
        CEP.TRC(SCCGWA, BPRVCHT.ORG_VCHNO);
        CEP.TRC(SCCGWA, BPRVCHT.KEY.SET_SEQ);
        CEP.TRC(SCCGWA, BPRVCHT.JRN_NO);
        CEP.TRC(SCCGWA, BPRVCHT.TR_CODE);
        CEP.TRC(SCCGWA, BPRVCHT.GEN_TYPE);
        CEP.TRC(SCCGWA, BPRVCHT.TR_BR);
        CEP.TRC(SCCGWA, BPRVCHT.BR);
        CEP.TRC(SCCGWA, BPRVCHT.ITM);
        CEP.TRC(SCCGWA, BPRVCHT.CCY);
        CEP.TRC(SCCGWA, BPRVCHT.SIGN);
        CEP.TRC(SCCGWA, BPRVCHT.AMT);
        CEP.TRC(SCCGWA, BPRVCHT.EC_IND);
        CEP.TRC(SCCGWA, BPRVCHT.AC_NO);
        CEP.TRC(SCCGWA, BPRVCHT.MIB_AC);
        CEP.TRC(SCCGWA, BPRVCHT.CI_NO);
        CEP.TRC(SCCGWA, BPRVCHT.AC_NO);
        CEP.TRC(SCCGWA, BPRVCHT.OTHSYS_KEY);
        CEP.TRC(SCCGWA, BPRVCHT.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPRVCHT.PROD_CODE);
        CEP.TRC(SCCGWA, BPRVCHT.EVENT_CODE);
        CEP.TRC(SCCGWA, BPRVCHT.GLMST);
        CEP.TRC(SCCGWA, BPRVCHT.AP_MMO);
        CEP.TRC(SCCGWA, BPRVCHT.CHNL_NO);
        CEP.TRC(SCCGWA, BPRVCHT.TR_TELLER);
        CEP.TRC(SCCGWA, BPRVCHT.TM_NO);
        CEP.TRC(SCCGWA, BPRVCHT.TR_TYPE);
        CEP.TRC(SCCGWA, BPRVCHT.ODE_FLG);
        CEP.TRC(SCCGWA, BPRVCHT.ODE_GRP_NO);
        CEP.TRC(SCCGWA, BPRVCHT.RED_FLG);
        CEP.TRC(SCCGWA, BPRVCHT.REF_NO);
        CEP.TRC(SCCGWA, BPRVCHT.CRVS_NO);
        CEP.TRC(SCCGWA, BPRVCHT.CRVS_SEQ);
        CEP.TRC(SCCGWA, BPRVCHT.TR_MMO);
        CEP.TRC(SCCGWA, BPRVCHT.NARR_CD);
        CEP.TRC(SCCGWA, BPRVCHT.POST_NARR);
        CEP.TRC(SCCGWA, BPRVCHT.DESC);
        CEP.TRC(SCCGWA, BPRVCHT.KEY.AC_DATE);
        CEP.TRC(SCCGWA, BPRVCHT.PORTFO_CD);
        AICX506.Q_TR_DT = BPRVCHT.TR_DATE;
        AICX506.Q_TR_TM = BPRVCHT.TR_TIME;
        AICX506.Q_AC_DT = BPRVCHT.KEY.AC_DATE;
        AICX506.Q_VAL_DT = BPRVCHT.VAL_DATE;
        AICX506.Q_VCHNO = BPRVCHT.KEY.SET_NO;
        AICX506.Q_SEQ = BPRVCHT.KEY.SET_SEQ;
        AICX506.Q_JRNNO = BPRVCHT.JRN_NO;
        CEP.TRC(SCCGWA, AICX506.Q_JRNNO);
        AICX506.Q_TX_CD = BPRVCHT.TR_CODE;
        AICX506.Q_TYP = BPRVCHT.GEN_TYPE;
        AICX506.Q_TR_BR = BPRVCHT.TR_BR;
        AICX506.Q_BR = BPRVCHT.BR;
        AICX506.Q_ITM = BPRVCHT.ITM;
        AICX506.Q_CCY = BPRVCHT.CCY;
        AICX506.Q_DRCRFLG = BPRVCHT.SIGN;
        AICX506.Q_AMT = BPRVCHT.AMT;
        AICX506.Q_EC_IND = BPRVCHT.EC_IND;
        AICX506.Q_VCHRF_NO = BPRVCHT.AC_NO;
        AICX506.Q_BR_AC = BPRVCHT.MIB_AC;
        AICX506.Q_CI_NO = BPRVCHT.CI_NO;
        AICX506.Q_OPPO_NO = BPRVCHT.THEIR_AC;
        AICX506.Q_BSREF_NO = BPRVCHT.OTHSYS_KEY;
        AICX506.Q_PRDMO_CD = BPRVCHT.CNTR_TYPE;
        AICX506.Q_PROD_CD = BPRVCHT.PROD_CODE;
        AICX506.Q_EVEN_CD = BPRVCHT.EVENT_CODE;
        AICX506.Q_GL_MSTNO = BPRVCHT.GLMST;
        AICX506.Q_APP = BPRVCHT.AP_MMO;
        AICX506.Q_CHNL_NO = BPRVCHT.CHNL_NO;
        AICX506.Q_TLR_NO = BPRVCHT.TR_TELLER;
        AICX506.Q_TRM_NO = BPRVCHT.TM_NO;
        AICX506.Q_TRN_TYP = BPRVCHT.TR_TYPE;
        AICX506.Q_ODE_FLG = BPRVCHT.ODE_FLG;
        AICX506.Q_VCHGR_NO = BPRVCHT.ODE_GRP_NO;
        AICX506.Q_RED_FLG = BPRVCHT.RED_FLG;
        AICX506.Q_REF_NO = BPRVCHT.REF_NO;
        AICX506.Q_CREV_NO = BPRVCHT.CRVS_NO;
        AICX506.Q_CRVS_SEQ = BPRVCHT.CRVS_SEQ;
        AICX506.Q_TR_MMO = BPRVCHT.TR_MMO;
        AICX506.Q_NARR_CD = BPRVCHT.NARR_CD;
        if (BPRVCHT.ODE_GRP_NO == 0) {
            AICX506.Q_TXN_NARR = BPRVCHT.POST_NARR;
        } else {
            AICX506.Q_TXN_NARR = " ";
        }
        AICX506.Q_DESC = BPRVCHT.DESC;
        AICX506.Q_CLEAN_DT = BPRVCHT.SETTLE_DT;
        AICX506.Q_PORT_CD = BPRVCHT.PORTFO_CD;
        AICX506.Q_RES_CENT = BPRVCHT.RES_CENT;
        AICX506.Q_LINE = BPRVCHT.LINE;
        AICX506.Q_INSIDE = BPRVCHT.INTER_CLEAR;
        AICX506.Q_SPARE = BPRVCHT.RESERVE;
        AICX506.Q_TLR_BR = BPRVCHT.TLR_BR;
        AICX506.Q_TLR_ID = BPRVCHT.TLR_ID;
        S000_03_01_OUTPUT();
        if (pgmRtn) return;
    }
    public void S000_03_01_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AIX23";
        SCCFMT.DATA_PTR = AICX506;
        SCCFMT.DATA_LEN = 622;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_04_01_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "AIX27";
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 76;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_OPEN_AITHMST_OPEN() throws IOException,SQLException,Exception {
        AITHMST_RD = new DBParm();
        AITHMST_RD.TableName = "AITHMST";
        AITHMST_RD.where = "ITM_NO = :AIRHMST.KEY.ITM_NO "
            + "AND CCY = :AIRHMST.CCY "
            + "AND BR = :AIRHMST.KEY.BR "
            + "AND GL_BOOK_FLG = :AIRHMST.KEY.GL_BOOK_FLG "
            + "AND AC_DATE < :AIRHMST.KEY.AC_DATE";
        AITHMST_RD.fst = true;
        AITHMST_RD.order = "AC_DATE DESC";
        IBS.READ(SCCGWA, AIRHMST, this, AITHMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, AIRHMST.CDDBAL);
        CEP.TRC(SCCGWA, AIRHMST.CDCBAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_OUTPUT.WS_O_OPN_DBAL = AIRHMST.CDDBAL;
            WS_OUTPUT.WS_O_OPN_CBAL = AIRHMST.CDCBAL;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_OUTPUT.WS_O_OPN_DBAL = 0;
            WS_OUTPUT.WS_O_OPN_CBAL = 0;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARBR TABLE AITHMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITHMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_OPEN_AITHMST_CLOSE() throws IOException,SQLException,Exception {
        AITHMST_RD = new DBParm();
        AITHMST_RD.TableName = "AITHMST";
        AITHMST_RD.where = "ITM_NO = :AIRHMST.KEY.ITM_NO "
            + "AND CCY = :AIRHMST.CCY "
            + "AND BR = :AIRHMST.KEY.BR "
            + "AND AC_DATE <= :AIRHMST.KEY.AC_DATE "
            + "AND GL_BOOK_FLG = :AIRHMST.KEY.GL_BOOK_FLG";
        AITHMST_RD.fst = true;
        AITHMST_RD.order = "AC_DATE DESC";
        IBS.READ(SCCGWA, AIRHMST, this, AITHMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, AIRHMST.CDDBAL);
        CEP.TRC(SCCGWA, AIRHMST.CDCBAL);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_OUTPUT.WS_O_CLS_DBAL = AIRHMST.CDDBAL;
            WS_OUTPUT.WS_O_CLS_CBAL = AIRHMST.CDCBAL;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_OUTPUT.WS_O_CLS_DBAL = 0;
            WS_OUTPUT.WS_O_CLS_CBAL = 0;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "STARBR TABLE AITHMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITHMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_LIMIT_DAYS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = WS_DATE_FR;
        SCCCLDT.DATE2 = WS_DATE_TO;
        WS_DAYS = (short) SCCCLDT.DAYS;
        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
        CEP.TRC(SCCGWA, WS_DAYS);
        if (WS_DAYS > K_MAX_DAYS) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_DAYS_EXCEED_LIMIT_1Y);
        }
    }
    public void B01_02_OUTPUT_INFO_BPTVCHH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICX513);
        AICX513.L_FLAG = BPRVCHH.BOOK_FLG;
        if (BPRVCHH.ODE_GRP_NO == 0) {
            AICX513.L_GRP_NO = 0;
        } else {
            if (BPRVCHH.FLR == null) BPRVCHH.FLR = "";
            JIBS_tmp_int = BPRVCHH.FLR.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) BPRVCHH.FLR += " ";
            if (BPRVCHH.FLR.substring(6 - 1, 6 + 8 - 1).trim().length() > 0) {
                if (BPRVCHH.FLR == null) BPRVCHH.FLR = "";
                JIBS_tmp_int = BPRVCHH.FLR.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) BPRVCHH.FLR += " ";
                if (BPRVCHH.FLR.substring(6 - 1, 6 + 8 - 1).trim().length() == 0) WS_GRP_NO_OUTPUT.WS_INPUT_DT = 0;
                else WS_GRP_NO_OUTPUT.WS_INPUT_DT = Integer.parseInt(BPRVCHH.FLR.substring(6 - 1, 6 + 8 - 1));
                WS_GRP_NO_OUTPUT.WS_GRP_NO = BPRVCHH.ODE_GRP_NO;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_GRP_NO_OUTPUT);
                if (JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1).trim().length() == 0) AICX513.L_GRP_NO = 0;
                else AICX513.L_GRP_NO = Integer.parseInt(JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1));
            } else {
                AICX513.L_GRP_NO = 0;
            }
        }
        AICX513.L_ACCENTRE = BPRVCHH.BR;
        AICX513.L_COANO = BPRVCHH.ITM;
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.BOOK_FLG = BPRVCHH.BOOK_FLG;
        AICPQITM.INPUT_DATA.NO = BPRVCHH.ITM;
        CEP.TRC(SCCGWA, WS_LAST_ITM);
        if (!AICPQITM.INPUT_DATA.NO.equalsIgnoreCase(WS_LAST_ITM)) {
            WS_LAST_ITM = AICPQITM.INPUT_DATA.NO;
            S00_CALL_AIZPQITM();
            if (pgmRtn) return;
            AICX513.L_CHS_NM = AICPQITM.OUTPUT_DATA.CHS_NM;
            WS_LAST_CHS_NM = AICPQITM.OUTPUT_DATA.CHS_NM;
        } else {
            AICX513.L_CHS_NM = WS_LAST_CHS_NM;
        }
        CEP.TRC(SCCGWA, WS_LAST_ITM);
        CEP.TRC(SCCGWA, WS_LAST_CHS_NM);
        AICX513.L_CURCODE = BPRVCHH.CCY;
        AICX513.L_VCHNO = BPRVCHH.KEY.SET_NO;
        AICX513.L_SET_SEQ = BPRVCHH.KEY.SET_SEQ;
        AICX513.L_VALDATE = BPRVCHH.VAL_DATE;
        AICX513.L_TRNDATE = BPRVCHH.KEY.AC_DATE;
        if (BPRVCHH.ODE_GRP_NO == 0) {
            AICX513.L_GRP_NO = 0;
        } else {
            if (BPRVCHH.FLR == null) BPRVCHH.FLR = "";
            JIBS_tmp_int = BPRVCHH.FLR.length();
            for (int i=0;i<60-JIBS_tmp_int;i++) BPRVCHH.FLR += " ";
            if (BPRVCHH.FLR.substring(6 - 1, 6 + 8 - 1).trim().length() > 0) {
                if (BPRVCHH.FLR == null) BPRVCHH.FLR = "";
                JIBS_tmp_int = BPRVCHH.FLR.length();
                for (int i=0;i<60-JIBS_tmp_int;i++) BPRVCHH.FLR += " ";
                if (BPRVCHH.FLR.substring(6 - 1, 6 + 8 - 1).trim().length() == 0) WS_GRP_NO_OUTPUT.WS_INPUT_DT = 0;
                else WS_GRP_NO_OUTPUT.WS_INPUT_DT = Integer.parseInt(BPRVCHH.FLR.substring(6 - 1, 6 + 8 - 1));
                WS_GRP_NO_OUTPUT.WS_GRP_NO = BPRVCHH.ODE_GRP_NO;
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_GRP_NO_OUTPUT);
                if (JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1).trim().length() == 0) AICX513.L_GRP_NO = 0;
                else AICX513.L_GRP_NO = Integer.parseInt(JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1));
            } else {
                AICX513.L_GRP_NO = 0;
            }
        }
        AICX513.L_DRCRIND = BPRVCHH.SIGN;
        AICX513.L_AMT = BPRVCHH.AMT;
        CEP.TRC(SCCGWA, WS_CONSTANT.WS_DATE_FUNC);
        AICX513.L_REFNO = BPRVCHH.REF_NO;
        AICX513.L_PROD_TYPE = BPRVCHH.PROD_CODE;
        AICX513.L_EVENCODE = BPRVCHH.EVENT_CODE;
        AICX513.L_CINO = BPRVCHH.CI_NO;
        AICX513.L_MIB_AC = BPRVCHH.AC_NO;
        AICX513.L_CONTTYPE = BPRVCHH.CNTR_TYPE_REL;
        AICX513.L_POST_NARR = BPRVCHH.POST_NARR;
        AICX513.L_NARR_CD = BPRVCHH.NARR_CD;
        AICX513.L_CHNL_NO = BPRVCHH.CHNL_NO;
        AICX513.L_OTHSYS_KEY = BPRVCHH.OTHSYS_KEY;
        AICX513.L_APP = BPRVCHH.AP_MMO;
        AICX513.L_TRNTYPE = BPRVCHH.TR_TYPE;
        AICX513.L_GLMSTNO = BPRVCHH.GLMST;
        AICX513.L_DESC = BPRVCHH.DESC;
        CEP.TRC(SCCGWA, AICX513.L_FLAG);
        CEP.TRC(SCCGWA, AICX513.L_ACCENTRE);
        CEP.TRC(SCCGWA, AICX513.L_COANO);
        CEP.TRC(SCCGWA, AICX513.L_CHS_NM);
        CEP.TRC(SCCGWA, AICX513.L_CURCODE);
        CEP.TRC(SCCGWA, AICX513.L_VCHNO);
        CEP.TRC(SCCGWA, AICX513.L_SET_SEQ);
        CEP.TRC(SCCGWA, AICX513.L_VALDATE);
        CEP.TRC(SCCGWA, AICX513.L_TRNDATE);
        CEP.TRC(SCCGWA, AICX513.L_GRP_NO);
        CEP.TRC(SCCGWA, AICX513.L_DRCRIND);
        CEP.TRC(SCCGWA, AICX513.L_AMT);
        CEP.TRC(SCCGWA, AICX513.L_REFNO);
        CEP.TRC(SCCGWA, AICX513.L_PROD_TYPE);
        CEP.TRC(SCCGWA, AICX513.L_EVENCODE);
        CEP.TRC(SCCGWA, AICX513.L_CINO);
        CEP.TRC(SCCGWA, AICX513.L_MIB_AC);
        CEP.TRC(SCCGWA, AICX513.L_CONTTYPE);
        CEP.TRC(SCCGWA, AICX513.L_POST_NARR);
        CEP.TRC(SCCGWA, AICX513.L_NARR_CD);
        CEP.TRC(SCCGWA, AICX513.L_CHNL_NO);
        CEP.TRC(SCCGWA, AICX513.L_OTHSYS_KEY);
        CEP.TRC(SCCGWA, AICX513.L_APP);
        CEP.TRC(SCCGWA, AICX513.L_TRNTYPE);
        CEP.TRC(SCCGWA, AICX513.L_GLMSTNO);
        CEP.TRC(SCCGWA, AICX513.L_DESC);
        WS_FLG2 = 'Y';
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, AICX513);
        SCCMPAG.DATA_LEN = 577;
        SCCMPAG.FUNC = 'D';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B03_OUTPUT_INFO_TO_SCREEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQVCH);
        CEP.TRC(SCCGWA, AIB8545_AWA_8545.ACDATE);
        BPCQVCH.DATA.KEY.AC_DATE = AIB8545_AWA_8545.ACDATE;
        if (AIB8545_AWA_8545.SETNO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AWA-SETNO AND AWA-OUTSETNO ALL SPACE");
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_VCHNO_EXREF_NO_SPACE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AIB8545_AWA_8545.SETNO);
        WS_DT_NUM = AIB8545_AWA_8545.ACDATE;
        WS_DT_NUM1 = (int) ((WS_DT_NUM - WS_DT_NUM % 10) / 10);
        WS_AC_NUM = (short) (WS_DT_NUM - WS_DT_NUM1 * 10);
        CEP.TRC(SCCGWA, WS_AC_NUM);
        CEP.TRC(SCCGWA, AIB8545_AWA_8545.ACDATE);
        CEP.TRC(SCCGWA, WS_DT_NUM);
        WS_AC_NUM1 = (short) ((WS_AC_NUM - WS_AC_NUM % 2) / 2);
        IBS.init(SCCGWA, BPRVCH01);
        BPRVCH01.KEY.AC_DATE = AIB8545_AWA_8545.ACDATE;
        BPRVCH01.KEY.SET_NO = AIB8545_AWA_8545.SETNO;
        CEP.TRC(SCCGWA, "TEST001");
        CEP.TRC(SCCGWA, BPRVCH01.KEY.AC_DATE);
        CEP.TRC(SCCGWA, BPRVCH01.KEY.SET_NO);
        T000_STARTBR_BPTVCH01();
        if (pgmRtn) return;
        T000_READNEXT_BPTVCH01();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            S000_OUTPUT_TITLE2();
            if (pgmRtn) return;
            for (WS_CNT = 1; WS_TBL_FLAG != 'N'; WS_CNT += 1) {
                B03_02_OUTPUT_INFO_BPTVCH_01();
                if (pgmRtn) return;
                T000_READNEXT_BPTVCH01();
                if (pgmRtn) return;
            }
            T000_ENDBR_BPTVCH01();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPRVCH02);
            BPRVCH02.KEY.AC_DATE = AIB8545_AWA_8545.ACDATE;
            BPRVCH02.KEY.SET_NO = AIB8545_AWA_8545.SETNO;
            CEP.TRC(SCCGWA, "TEST002");
            CEP.TRC(SCCGWA, BPRVCH02.KEY.AC_DATE);
            CEP.TRC(SCCGWA, BPRVCH02.KEY.SET_NO);
            T000_STARTBR_BPTVCH02();
            if (pgmRtn) return;
            T000_READNEXT_BPTVCH02();
            if (pgmRtn) return;
            S000_OUTPUT_TITLE2();
            if (pgmRtn) return;
            for (WS_CNT = 1; WS_TBL_FLAG != 'N'; WS_CNT += 1) {
                B03_02_OUTPUT_INFO_BPTVCH_02();
                if (pgmRtn) return;
                T000_READNEXT_BPTVCH02();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_STARTBR_BPTVCH01() throws IOException,SQLException,Exception {
        BPTVCH01_BR.rp = new DBParm();
        BPTVCH01_BR.rp.TableName = "BPTVCH01";
        BPTVCH01_BR.rp.where = "AC_DATE = :BPRVCH01.KEY.AC_DATE "
            + "AND SET_NO = :BPRVCH01.KEY.SET_NO";
        IBS.STARTBR(SCCGWA, BPRVCH01, this, BPTVCH01_BR);
    }
    public void T000_READNEXT_BPTVCH01() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRVCH01, this, BPTVCH01_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTVCH01() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVCH01_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTVCH02() throws IOException,SQLException,Exception {
        BPTVCH02_BR.rp = new DBParm();
        BPTVCH02_BR.rp.TableName = "BPTVCH02";
        BPTVCH02_BR.rp.where = "AC_DATE = :BPRVCH02.KEY.AC_DATE "
            + "AND SET_NO = :BPRVCH02.KEY.SET_NO";
        IBS.STARTBR(SCCGWA, BPRVCH02, this, BPTVCH02_BR);
    }
    public void T000_READNEXT_BPTVCH02() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRVCH02, this, BPTVCH02_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTVCH02() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTVCH02_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void S000_OUTPUT_TITLE2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 531;
        SCCMPAG.SCR_ROW_CNT = 20;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B03_02_OUTPUT_INFO_BPTVCH_01() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICX538);
        AICX538.TR_DT = BPRVCH01.TR_DATE;
        AICX538.TR_TM = BPRVCH01.TR_TIME;
        AICX538.AC_DT = BPRVCH01.KEY.AC_DATE;
        AICX538.VAL_DT = BPRVCH01.VAL_DATE;
        AICX538.VCHNO = BPRVCH01.KEY.SET_NO;
        AICX538.SEQ = BPRVCH01.KEY.SET_SEQ;
        AICX538.JRNNO = BPRVCH01.KEY.JRN_NO;
        AICX538.TX_CD = BPRVCH01.KEY.TR_CODE;
        AICX538.TYP = BPRVCH01.GEN_TYPE;
        AICX538.TR_BR = BPRVCH01.TR_BR;
        AICX538.BR = BPRVCH01.BR;
        AICX538.ITM = BPRVCH01.ITM;
        AICX538.CCY = BPRVCH01.CCY;
        AICX538.DRCRFLG = BPRVCH01.SIGN;
        AICX538.AMT = BPRVCH01.AMT;
        AICX538.EC_IND = BPRVCH01.EC_IND;
        AICX538.VCHRF_NO = BPRVCH01.AC_NO;
        AICX538.BR_AC = BPRVCH01.MIB_NO;
        AICX538.CI_NO = BPRVCH01.CI_NO;
        AICX538.OPPO_NO = BPRVCH01.THEIR_AC;
        AICX538.BSREF_NO = BPRVCH01.OTHSYS_KEY;
        AICX538.PRDMO_CD = BPRVCH01.CNTR_TYPE;
        AICX538.PROD_CD = BPRVCH01.PROD_CODE;
        AICX538.EVEN_CD = BPRVCH01.EVENT_CODE;
        AICX538.GL_MSTNO = BPRVCH01.GLMST;
        AICX538.APP = BPRVCH01.KEY.AP_MMO;
        AICX538.CHNL_NO = BPRVCH01.CHNL_NO;
        AICX538.TLR_NO = BPRVCH01.TR_TELLER;
        AICX538.ODE_FLG = BPRVCH01.ODE_FLG;
        AICX538.REF_NO = BPRVCH01.REF_NO;
        AICX538.RED_FLG = BPRVCH01.RED_FLG;
        AICX538.CREV_NO = BPRVCH01.CRVS_NO;
        AICX538.NARR_CD = BPRVCH01.NARR_CD;
        AICX538.TXN_NARR = BPRVCH01.POST_NARR;
        AICX538.DESC = BPRVCH01.VCH_DESC;
        CEP.TRC(SCCGWA, BPRVCH01.TR_DATE);
        CEP.TRC(SCCGWA, BPRVCH01.TR_TIME);
        CEP.TRC(SCCGWA, BPRVCH01.KEY.AC_DATE);
        CEP.TRC(SCCGWA, BPRVCH01.VAL_DATE);
        CEP.TRC(SCCGWA, BPRVCH01.ORG_VCHNO);
        CEP.TRC(SCCGWA, BPRVCH01.KEY.SET_SEQ);
        CEP.TRC(SCCGWA, BPRVCH01.KEY.JRN_NO);
        CEP.TRC(SCCGWA, BPRVCH01.KEY.TR_CODE);
        CEP.TRC(SCCGWA, BPRVCH01.GEN_TYPE);
        CEP.TRC(SCCGWA, BPRVCH01.TR_BR);
        CEP.TRC(SCCGWA, BPRVCH01.BR);
        CEP.TRC(SCCGWA, BPRVCH01.ITM);
        CEP.TRC(SCCGWA, BPRVCH01.CCY);
        CEP.TRC(SCCGWA, BPRVCH01.SIGN);
        CEP.TRC(SCCGWA, BPRVCH01.AMT);
        CEP.TRC(SCCGWA, BPRVCH01.EC_IND);
        CEP.TRC(SCCGWA, BPRVCH01.AC_NO);
        CEP.TRC(SCCGWA, BPRVCH01.MIB_NO);
        CEP.TRC(SCCGWA, BPRVCH01.CI_NO);
        CEP.TRC(SCCGWA, BPRVCH01.THEIR_AC);
        CEP.TRC(SCCGWA, BPRVCH01.OTHSYS_KEY);
        CEP.TRC(SCCGWA, BPRVCH01.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPRVCH01.PROD_CODE);
        CEP.TRC(SCCGWA, BPRVCH01.EVENT_CODE);
        CEP.TRC(SCCGWA, BPRVCH01.GLMST);
        CEP.TRC(SCCGWA, BPRVCH01.KEY.AP_MMO);
        CEP.TRC(SCCGWA, BPRVCH01.CHNL_NO);
        CEP.TRC(SCCGWA, BPRVCH01.TR_TELLER);
        CEP.TRC(SCCGWA, BPRVCH01.ODE_FLG);
        CEP.TRC(SCCGWA, BPRVCH01.REF_NO);
        CEP.TRC(SCCGWA, BPRVCH01.RED_FLG);
        CEP.TRC(SCCGWA, BPRVCH01.CRVS_NO);
        CEP.TRC(SCCGWA, BPRVCH01.NARR_CD);
        CEP.TRC(SCCGWA, BPRVCH01.POST_NARR);
        CEP.TRC(SCCGWA, BPRVCH01.VCH_DESC);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, AICX538);
        SCCMPAG.DATA_LEN = 531;
        SCCMPAG.FUNC = 'D';
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B03_02_OUTPUT_INFO_BPTVCH_02() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICX538);
        AICX538.TR_DT = BPRVCH02.TR_DATE;
        AICX538.TR_TM = BPRVCH02.TR_TIME;
        AICX538.AC_DT = BPRVCH02.KEY.AC_DATE;
        AICX538.VAL_DT = BPRVCH02.VAL_DATE;
        AICX538.VCHNO = BPRVCH02.KEY.SET_NO;
        AICX538.SEQ = BPRVCH02.KEY.SET_SEQ;
        AICX538.JRNNO = BPRVCH02.KEY.JRN_NO;
        AICX538.TX_CD = BPRVCH02.KEY.TR_CODE;
        AICX538.TYP = BPRVCH02.GEN_TYPE;
        AICX538.TR_BR = BPRVCH02.TR_BR;
        AICX538.BR = BPRVCH02.BR;
        AICX538.ITM = BPRVCH02.ITM;
        AICX538.CCY = BPRVCH02.CCY;
        AICX538.DRCRFLG = BPRVCH02.SIGN;
        AICX538.AMT = BPRVCH02.AMT;
        AICX538.EC_IND = BPRVCH02.EC_IND;
        AICX538.VCHRF_NO = BPRVCH02.AC_NO;
        AICX538.BR_AC = BPRVCH02.MIB_NO;
        AICX538.CI_NO = BPRVCH02.CI_NO;
        AICX538.OPPO_NO = BPRVCH02.THEIR_AC;
        AICX538.BSREF_NO = BPRVCH02.OTHSYS_KEY;
        AICX538.PRDMO_CD = BPRVCH02.CNTR_TYPE;
        AICX538.PROD_CD = BPRVCH02.PROD_CODE;
        AICX538.EVEN_CD = BPRVCH02.EVENT_CODE;
        AICX538.GL_MSTNO = BPRVCH02.GLMST;
        AICX538.APP = BPRVCH02.KEY.AP_MMO;
        AICX538.CHNL_NO = BPRVCH02.CHNL_NO;
        AICX538.TLR_NO = BPRVCH02.TR_TELLER;
        AICX538.ODE_FLG = BPRVCH02.ODE_FLG;
        AICX538.REF_NO = BPRVCH02.REF_NO;
        AICX538.RED_FLG = BPRVCH02.RED_FLG;
        AICX538.CREV_NO = BPRVCH02.CRVS_NO;
        AICX538.NARR_CD = BPRVCH02.NARR_CD;
        AICX538.TXN_NARR = BPRVCH02.POST_NARR;
        AICX538.DESC = BPRVCH02.VCH_DESC;
        CEP.TRC(SCCGWA, BPRVCH02.TR_DATE);
        CEP.TRC(SCCGWA, BPRVCH02.TR_TIME);
        CEP.TRC(SCCGWA, BPRVCH02.KEY.AC_DATE);
        CEP.TRC(SCCGWA, BPRVCH02.VAL_DATE);
        CEP.TRC(SCCGWA, BPRVCH02.ORG_VCHNO);
        CEP.TRC(SCCGWA, BPRVCH02.KEY.SET_SEQ);
        CEP.TRC(SCCGWA, BPRVCH02.KEY.JRN_NO);
        CEP.TRC(SCCGWA, BPRVCH02.KEY.TR_CODE);
        CEP.TRC(SCCGWA, BPRVCH02.GEN_TYPE);
        CEP.TRC(SCCGWA, BPRVCH02.TR_BR);
        CEP.TRC(SCCGWA, BPRVCH02.BR);
        CEP.TRC(SCCGWA, BPRVCH02.ITM);
        CEP.TRC(SCCGWA, BPRVCH02.CCY);
        CEP.TRC(SCCGWA, BPRVCH02.SIGN);
        CEP.TRC(SCCGWA, BPRVCH02.AMT);
        CEP.TRC(SCCGWA, BPRVCH02.EC_IND);
        CEP.TRC(SCCGWA, BPRVCH02.AC_NO);
        CEP.TRC(SCCGWA, BPRVCH02.MIB_NO);
        CEP.TRC(SCCGWA, BPRVCH02.CI_NO);
        CEP.TRC(SCCGWA, BPRVCH02.THEIR_AC);
        CEP.TRC(SCCGWA, BPRVCH02.OTHSYS_KEY);
        CEP.TRC(SCCGWA, BPRVCH02.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPRVCH02.PROD_CODE);
        CEP.TRC(SCCGWA, BPRVCH02.EVENT_CODE);
        CEP.TRC(SCCGWA, BPRVCH02.GLMST);
        CEP.TRC(SCCGWA, BPRVCH02.KEY.AP_MMO);
        CEP.TRC(SCCGWA, BPRVCH02.CHNL_NO);
        CEP.TRC(SCCGWA, BPRVCH02.TR_TELLER);
        CEP.TRC(SCCGWA, BPRVCH02.ODE_FLG);
        CEP.TRC(SCCGWA, BPRVCH02.REF_NO);
        CEP.TRC(SCCGWA, BPRVCH02.RED_FLG);
        CEP.TRC(SCCGWA, BPRVCH02.CRVS_NO);
        CEP.TRC(SCCGWA, BPRVCH02.NARR_CD);
        CEP.TRC(SCCGWA, BPRVCH02.POST_NARR);
        CEP.TRC(SCCGWA, BPRVCH02.VCH_DESC);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, AICX538);
        SCCMPAG.DATA_LEN = 531;
        SCCMPAG.FUNC = 'D';
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        CEP.TRC(SCCGWA, AICX538);
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQVCH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCQVCH.DATA.KEY.AC_DATE);
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-VCH", BPCQVCH);
        if (BPCQVCH.RETURN_INFO == 'N') {
            WS_REC2_INFO = 'N';
        }
        CEP.TRC(SCCGWA, BPCQVCH.RC);
        if (BPCQVCH.RC.RC_CODE != 0) {
