package com.hisun.LN;

import com.hisun.CI.*;
import com.hisun.DC.*;
import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;
import com.hisun.AI.*;
import com.hisun.VT.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT2000 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm LNTFWDH_RD;
    DBParm LNTFUND_RD;
    DBParm LNTBALZ_RD;
    DBParm LNTAGRE_RD;
    DBParm LNTCONT_RD;
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "LN200";
    String K_OUTPUT_FMT_2161 = "LNC10";
    String K_DRAW_AC_FMT = "DD160";
    String K_HIS_REMARKS = "ACCRUAL DRAWDOWN CONTRACT DRAWDOWN";
    String K_NEXT_SEQ = "00000001";
    char K_CLDD = 'D';
    char K_NORMAL_CONT = '0';
    char K_PAY_ONETIME = '1';
    char K_PAY_PRE_INT = '0';
    char K_REPAY_8 = '8';
    char K_REPAY_9 = '9';
    String K_DD_AC = "01";
    String K_IA_AC = "02";
    String K_IB_AC = "03";
    String K_DC_AC = "05";
    char K_OUR_BANK = '0';
    char K_OTH_BANK = '1';
    String K_INP_OLD_TYPE = "RMDKLL";
    String PGM_SCSSCKDT = "SCSSCKDT";
    char K_CKPD_INQ = '0';
    LNOT2000_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new LNOT2000_WS_TEMP_VARIABLE();
    String WS_ERR_INFO = " ";
    short WS_I = 0;
    short WS_II = 0;
    String WS_TR_MMO = " ";
    LNOT2000_WS_PRM_KEY WS_PRM_KEY = new LNOT2000_WS_PRM_KEY();
    LNOT2000_WS_AC_DATE WS_AC_DATE = new LNOT2000_WS_AC_DATE();
    LNOT2000_WS_IA_AC WS_IA_AC = new LNOT2000_WS_IA_AC();
    double WS_AMT = 0;
    double WS_AMT1 = 0;
    char WS_VRTU_FLG = ' ';
    String WS_TEMP_CMMT_NO = " ";
    String WS_TEMP_CONT_NO = " ";
    String WS_DRAW_NO = " ";
    String WS_DRAW_NO1 = " ";
    char WS_BANK_TYPE = ' ';
    String WS_SETL_AC = " ";
    String WS_SETL_AC_TYPE = " ";
    String WS_INP_ACT = " ";
    char WS_PARTI_ROLE = ' ';
    LNOT2000_WS_OUT_DATA WS_OUT_DATA = new LNOT2000_WS_OUT_DATA();
    LNOT2000_WS_CHECK_INFO WS_CHECK_INFO = new LNOT2000_WS_CHECK_INFO();
    char WS_CMMT_FLG = ' ';
    char WS_TAX = ' ';
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    LNCSWLAD LNCSWLAD = new LNCSWLAD();
    LNCOWLAD LNCOWLAD = new LNCOWLAD();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    LNCIGECR LNCIGECR = new LNCIGECR();
    LNCCTLPM LNCCTLPM = new LNCCTLPM();
    CICCUST CICCUST = new CICCUST();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    CICACCU CICACCU = new CICACCU();
    LNCFFSDT LNCFFSDT = new LNCFFSDT();
    BPCCGAC BPCCGAC = new BPCCGAC();
    LNRAGRE LNRAGRE = new LNRAGRE();
    BPCEX BPCEX = new BPCEX();
    LNCSCMMT LNCSCMMT = new LNCSCMMT();
    LNCSEXEQ LNCSEXEQ = new LNCSEXEQ();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    DDCIMMST DDCIMMST = new DDCIMMST();
    DDCIMCCY DDCIMCCY = new DDCIMCCY();
    LNCSCMRT LNCSCMRT = new LNCSCMRT();
    LNCSCKPD LNCSCKPD = new LNCSCKPD();
    AICPQIA AICPQIA = new AICPQIA();
    LNCSSTBL LNCSSTBL = new LNCSSTBL();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCSCORT BPCSCORT = new BPCSCORT();
    AICPQITM AICPQITM = new AICPQITM();
    LNRFUND LNRFUND = new LNRFUND();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    CICQACRI CICQACRI = new CICQACRI();
    LNCO2000 LNCO2000 = new LNCO2000();
    LNCRFWDH LNCRFWDH = new LNCRFWDH();
    LNRFWDH LNRFWDH = new LNRFWDH();
    SCCTPCL SCCTPCL = new SCCTPCL();
    VTCUJMDR VTCUJMDR = new VTCUJMDR();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    LNRBALZ LNRBALZ = new LNRBALZ();
    LNRCONT LNRCONT = new LNRCONT();
    CICQCIAC CICQCIAC = new CICQCIAC();
    SCCGWA SCCGWA;
    LNB2000_AWA_2000 LNB2000_AWA_2000;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNOT2000 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB2000_AWA_2000>");
        LNB2000_AWA_2000 = (LNB2000_AWA_2000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REVERSAL_IND);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B101_GET_INI_DATA();
            if (pgmRtn) return;
            B220_MAIN_PROCESS();
            if (pgmRtn) return;
            if (SCCGAPV.TYPE == 'L' 
                || SCCGAPV.TYPE == ' ') {
                if (LNB2000_AWA_2000.START_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                    B600_FINANCE_HIS();
                    if (pgmRtn) return;
                }
            }
        } else {
            B100_CHECK_INPUT();
            if (pgmRtn) return;
            B110_GET_PRDT_PARM();
            if (pgmRtn) return;
            B220_MAIN_PROCESS();
            if (pgmRtn) return;
            B300_OUTPUT_PROCESS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGAPV.TYPE);
            if (SCCGAPV.TYPE == 'L' 
                || SCCGAPV.TYPE == ' ') {
                if (LNB2000_AWA_2000.START_DT <= SCCGWA.COMM_AREA.AC_DATE) {
                    B600_FINANCE_HIS();
                    if (pgmRtn) return;
                }
            }
        }
        B990_SEND_CLINFO_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.SUBSTTRM);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CI_NO);
        if (LNB2000_AWA_2000.CI_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CINO_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PROD_TYP);
        if (LNB2000_AWA_2000.PROD_TYP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PRDCD_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.RAT_MTH == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RAT_MTH_NOT_EMPT, WS_TEMP_VARIABLE.WS_MSGID);
        }
        if (LNB2000_AWA_2000.RAT_MTH == '1') {
            if (LNB2000_AWA_2000.FLPD_UN == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FLT_PERD_UNIT_EMPT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (LNB2000_AWA_2000.INT_FLPD == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_FLT_PERD_EMPT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (LNB2000_AWA_2000.FST_DAY == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_NO_FST_FLT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (LNB2000_AWA_2000.FST_DAY <= SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FST_FLT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, LNCSCKPD);
        LNCSCKPD.FUNC = K_CKPD_INQ;
        LNCSCKPD.PROD_CD = LNB2000_AWA_2000.PROD_TYP;
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PROD_TYP);
        S000_CALL_LNZSCKPD();
        if (pgmRtn) return;
        WS_OUT_DATA.WS_PROD_CLS = LNCSCKPD.PROD_CLS;
        CEP.TRC(SCCGWA, LNCSCKPD.PROD_CLS);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_PROD_CLS);
        if (LNB2000_AWA_2000.START_DT >= LNCSCKPD.EXP_DATE) {
            CEP.ERR(SCCGWA, LNCMSG_ERROR_MSG.PROD_ERR);
        }
        if (LNB2000_AWA_2000.PAYI_PER != '8' 
            && (LNB2000_AWA_2000.CAL_PERU != ' ' 
            || LNB2000_AWA_2000.CAL_PERD != 0)) {
            CEP.TRC(SCCGWA, "1111");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PERD_CANNOT_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.PAYI_PER == '8' 
            && (LNB2000_AWA_2000.CAL_PERU == ' ' 
            || LNB2000_AWA_2000.CAL_PERD == 0)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CAL_PERD_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.PYP_CIRC != '8' 
            && (LNB2000_AWA_2000.PYP_PERU != ' ' 
            || LNB2000_AWA_2000.PYP_PERD != 0)) {
            CEP.TRC(SCCGWA, "2222");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PERD_CANNOT_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.PYP_CIRC == '8' 
            && (LNB2000_AWA_2000.PYP_PERU == ' ' 
            || LNB2000_AWA_2000.PYP_PERD == 0)) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PYP_CIRC_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.PAY_DTYP == '2') {
            if (LNB2000_AWA_2000.CAL_DAY == 0) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CAL_DAY_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (LNB2000_AWA_2000.CAL_PERU == 'M' 
                && (LNB2000_AWA_2000.CAL_DAY < 1 
                || LNB2000_AWA_2000.CAL_DAY > 31) 
                || LNB2000_AWA_2000.CAL_PERU == 'D' 
                && (LNB2000_AWA_2000.CAL_DAY < 1 
                || LNB2000_AWA_2000.CAL_DAY > 7)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CAL_DAY_VAL_ERR, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB2000_AWA_2000.PAY_DTYP == '1' 
            && LNB2000_AWA_2000.CAL_FSDT != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FST_DT_NALOW, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.CAL_FSDT != 0 
            && LNB2000_AWA_2000.CAL_DAY != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = LNB2000_AWA_2000.CAL_FSDT;
            R000_CALL_SCSSCKDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCKDT.MTH_DAYS);
            if (LNB2000_AWA_2000.CAL_PERU == 'M') {
                JIBS_tmp_str[0] = "" + LNB2000_AWA_2000.CAL_FSDT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (LNB2000_AWA_2000.CAL_DAY <= SCCCKDT.MTH_DAYS 
                    && !JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase(LNB2000_AWA_2000.CAL_DAY+"")) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FSDT_CALDY_NM, WS_TEMP_VARIABLE.WS_MSGID);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                JIBS_tmp_str[0] = "" + LNB2000_AWA_2000.CAL_FSDT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (LNB2000_AWA_2000.CAL_DAY > SCCCKDT.MTH_DAYS 
                    && !JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).equalsIgnoreCase(SCCCKDT.MTH_DAYS+"")) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FSDT_CALDY_NM, WS_TEMP_VARIABLE.WS_MSGID);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (LNB2000_AWA_2000.CAL_PERU == 'D' 
                    && SCCCKDT.WEEK_NO != LNB2000_AWA_2000.CAL_DAY) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FSDT_CALDY_NM, WS_TEMP_VARIABLE.WS_MSGID);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PAPER_NO);
        if (LNB2000_AWA_2000.DRAW_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_E_INPUT_PAPER_NO, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.DW_BK_TP != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.PA_BK_TP != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CONT_AMT);
        if (LNB2000_AWA_2000.CONT_AMT <= 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CONT_AMT_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.BOOK_CRE == 0) {
            CEP.TRC(SCCGWA, "1");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BK_BR_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = LNB2000_AWA_2000.BOOK_CRE;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.DOMI_BR == 0) {
            CEP.TRC(SCCGWA, "2");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BK_BR_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = LNB2000_AWA_2000.DOMI_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
        B200_GET_CONTRCT_NO();
        if (pgmRtn) return;
        R000_CHECK_CI_STS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = LNB2000_AWA_2000.CCY;
        S00_CALL_BPZQCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, VTCUJMDR);
        IBS.init(SCCGWA, CICQCIAC);
        IBS.init(SCCGWA, LNRBALZ);
        IBS.init(SCCGWA, LNRCONT);
        if (LNB2000_AWA_2000.CCY.equalsIgnoreCase("156")) {
            while (CICQCIAC.DATA.LAST_FLG != 'Y') {
                R000_CALL_CIZQCIAC();
                if (pgmRtn) return;
            }
            if (WS_TEMP_VARIABLE.WS_TOT_AMT < 10000000) {
                CICCUST.FUNC = 'C';
                CICCUST.DATA.CI_NO = LNB2000_AWA_2000.CI_NO;
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SIZE);
                CEP.TRC(SCCGWA, CICCUST.O_DATA.O_SUB_TYP);
                if (CICCUST.O_DATA.O_IDE_STSW == null) CICCUST.O_DATA.O_IDE_STSW = "";
                JIBS_tmp_int = CICCUST.O_DATA.O_IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CICCUST.O_DATA.O_IDE_STSW += " ";
                CEP.TRC(SCCGWA, CICCUST.O_DATA.O_IDE_STSW.substring(0, 10));
                if (CICCUST.O_DATA.O_SIZE == '3' 
                    || CICCUST.O_DATA.O_SIZE == '4') {
                    VTCUJMDR.INPUT_DATA.OTH.WEI_FLG = 'Y';
                }
                if (CICCUST.O_DATA.O_SUB_TYP.equalsIgnoreCase("219070")) {
                    VTCUJMDR.INPUT_DATA.OTH.IIC_FLG = 'Y';
                }
                if (CICCUST.O_DATA.O_IDE_STSW == null) CICCUST.O_DATA.O_IDE_STSW = "";
                JIBS_tmp_int = CICCUST.O_DATA.O_IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CICCUST.O_DATA.O_IDE_STSW += " ";
                if (CICCUST.O_DATA.O_IDE_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                    VTCUJMDR.INPUT_DATA.OTH.PEA_FLG = 'Y';
                }
                if (CICCUST.O_DATA.O_IDE_STSW == null) CICCUST.O_DATA.O_IDE_STSW = "";
                JIBS_tmp_int = CICCUST.O_DATA.O_IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CICCUST.O_DATA.O_IDE_STSW += " ";
                if (CICCUST.O_DATA.O_IDE_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                    VTCUJMDR.INPUT_DATA.OTH.NCB_FLG = 'Y';
                }
                VTCUJMDR.INPUT_DATA.FUNC = 'A';
                VTCUJMDR.INPUT_DATA.AC = WS_OUT_DATA.WS_OUT_CONT_NO;
                VTCUJMDR.INPUT_DATA.PROD_CD = LNB2000_AWA_2000.PROD_TYP;
                VTCUJMDR.INPUT_DATA.BR = LNB2000_AWA_2000.BOOK_CRE;
                VTCUJMDR.INPUT_DATA.CCY = LNB2000_AWA_2000.CCY;
                VTCUJMDR.INPUT_DATA.OTH.OVER_FLG = CICCUST.O_DATA.O_SID_FLG;
                S000_CALL_VTZUJMDR();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.INT_D_BA);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CALR_STD);
        if (LNB2000_AWA_2000.INT_D_BA.trim().length() == 0) {
            LNB2000_AWA_2000.INT_D_BA = BPCQCCY.DATA.CALR_STD;
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.INT_D_BA);
        if (LNB2000_AWA_2000.INT_D_BA.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INT_D_BA, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.START_DT == 0 
            || LNB2000_AWA_2000.START_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_START_DT_L_AC_DT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.MATU_DT == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_I_MATU_DT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.MATU_DT == SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MAT_DT_GE_AC_DT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.CPND_USE != 'Y' 
            && LNB2000_AWA_2000.CPND_USE != 'N') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_CPND_USE_VAL_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.CPND_USE == 'Y' 
            && LNB2000_AWA_2000.CPNDRATT.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_RATT_INVAILD, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.REPY_MTH == ' ') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REPYMTH_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.REPY_MTH == '4') {
            if (LNB2000_AWA_2000.PHS_FLG != 'Y' 
                && LNB2000_AWA_2000.PHS_FLG != 'N') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PHS_FLG_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (LNB2000_AWA_2000.REPY_MTH == '4' 
            && LNB2000_AWA_2000.PHS_FLG == 'N') {
            if (LNB2000_AWA_2000.INST_MTH == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_INST_MTH_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (LNB2000_AWA_2000.YHS_FLG != 'Y' 
            && LNB2000_AWA_2000.YHS_FLG != 'N') {
            CEP.TRC(SCCGWA, "4");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_YHS_FLG_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.YHS_FLG == 'Y' 
            && LNB2000_AWA_2000.YHS_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_YHSAC_M_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.ATO_DFLG != 'Y' 
            && LNB2000_AWA_2000.ATO_DFLG != 'N') {
            CEP.TRC(SCCGWA, "5");
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ATO_FLG_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.PYP_PERD > 999) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PERD_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.CAL_PERD > 999) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_PERD_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        B110_GET_INITIALIZE_DATA();
        if (pgmRtn) return;
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B110_GET_PRDT_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = LNB2000_AWA_2000.CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        WS_CHECK_INFO.WS_CI_NM = CICCUST.O_DATA.O_CI_NM;
        WS_CHECK_INFO.WS_ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        WS_CHECK_INFO.WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CI_TYP);
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_PRODC_MOD);
        if (WS_TEMP_VARIABLE.WS_CI_TYP == '1' 
            && WS_TEMP_VARIABLE.WS_PRODC_MOD != 'R') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PROD_CI_TYP, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_TEMP_VARIABLE.WS_CI_TYP == '2' 
            && WS_TEMP_VARIABLE.WS_PRODC_MOD != 'C') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PROD_CI_TYP, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_TEMP_VARIABLE.WS_CI_TYP == '3' 
            && WS_TEMP_VARIABLE.WS_PRODC_MOD != 'G') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PROD_CI_TYP, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B101_GET_INI_DATA() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            IBS.init(SCCGWA, LNRAGRE);
            LNRAGRE.PAPER_NO = LNB2000_AWA_2000.PAPER_NO;
            LNRAGRE.DRAW_NO = LNB2000_AWA_2000.DRAW_NO;
            T000_READ_AGRE_CONT_R();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNRAGRE.KEY.CONTRACT_NO);
            CEP.TRC(SCCGWA, LNRAGRE.VRTU_FLG);
            WS_OUT_DATA.WS_OUT_CONT_NO = LNRAGRE.KEY.CONTRACT_NO;
            WS_VRTU_FLG = LNRAGRE.VRTU_FLG;
        }
    }
    public void B200_GET_CONTRCT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.DRAW_NO = LNB2000_AWA_2000.DRAW_NO;
        LNRAGRE.PAPER_NO = LNB2000_AWA_2000.PAPER_NO;
        LNRAGRE.CTA_FROM = '0';
        T000_READ_AGRE_CONT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, LNCIGECR);
        LNCIGECR.INPUT_INFO.PAPER_NO = LNB2000_AWA_2000.PAPER_NO;
        LNCIGECR.INPUT_INFO.DRAW_NO = LNB2000_AWA_2000.DRAW_NO;
        LNCIGECR.INPUT_INFO.CI_NO = LNB2000_AWA_2000.CI_NO;
        LNCIGECR.INPUT_INFO.BOOK_BR = LNB2000_AWA_2000.BOOK_CRE;
        LNCIGECR.INPUT_INFO.CCY = LNB2000_AWA_2000.CCY;
        CEP.TRC(SCCGWA, LNCSCKPD.PROD_CLS);
        CEP.TRC(SCCGWA, "20180001");
        LNCIGECR.INPUT_INFO.PROD_TYP = WS_OUT_DATA.WS_PROD_CLS;
        CEP.TRC(SCCGWA, LNCIGECR.INPUT_INFO.CI_NO);
        LNCIGECR.INPUT_INFO.CTA_FROM = K_NORMAL_CONT;
        S000_CALL_LNZIGECR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCIGECR.OUTPOUT_INFO.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNCIGECR.OUTPOUT_INFO.CMMT_NO);
        WS_OUT_DATA.WS_OUT_CONT_NO = LNCIGECR.OUTPOUT_INFO.CONTRACT_NO;
        WS_TEMP_CMMT_NO = LNCIGECR.OUTPOUT_INFO.CMMT_NO;
    }
    public void B110_GET_INITIALIZE_DATA() throws IOException,SQLException,Exception {
        WS_TEMP_VARIABLE.WS_PRODC_MOD = LNCSCKPD.PROD_MOD;
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_PRODC_MOD);
        if (LNB2000_AWA_2000.GRA_TYP == '2' 
            && LNB2000_AWA_2000.GRA_DAYA == 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GRA_DAYS, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.GRA_DAYA);
        if (LNB2000_AWA_2000.GRA_TYP == '1' 
            && LNB2000_AWA_2000.GRA_DAYA != 0) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_GRA_DAYS, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.P_GRA_MT == ' ') {
            LNB2000_AWA_2000.P_GRA_MT = LNCSCKPD.PGRAMT;
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.P_GRA_MT);
        if (LNB2000_AWA_2000.C_GRA_MT == ' ') {
            LNB2000_AWA_2000.C_GRA_MT = LNCSCKPD.CGRAMT;
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PYP_PERD);
        if (LNB2000_AWA_2000.PYP_CIRC == ' ' 
            && (LNB2000_AWA_2000.REPY_MTH != '4' 
            && LNB2000_AWA_2000.REPY_MTH != '5')) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PYP_CIRC_M, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CAL_FSDT);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y' 
            && LNB2000_AWA_2000.CAL_FSDT != 0 
            && (LNB2000_AWA_2000.REPY_MTH != '0' 
            && LNB2000_AWA_2000.REPY_MTH != '1')) {
            LNB2000_AWA_2000.PAY_DTYP = '2';
            JIBS_tmp_str[0] = "" + LNB2000_AWA_2000.CAL_FSDT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) LNB2000_AWA_2000.CAL_DAY = 0;
            else LNB2000_AWA_2000.CAL_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
            CEP.TRC(SCCGWA, LNB2000_AWA_2000.CAL_DAY);
            CEP.TRC(SCCGWA, LNB2000_AWA_2000.PAYI_PER);
            if (LNB2000_AWA_2000.PAYI_PER == '2' 
                || LNB2000_AWA_2000.PAYI_PER == '3') {
                IBS.init(SCCGWA, SCCCKDT);
                CEP.TRC(SCCGWA, LNB2000_AWA_2000.CAL_FSDT);
                SCCCKDT.DATE = LNB2000_AWA_2000.CAL_FSDT;
                CEP.TRC(SCCGWA, SCCCKDT.DATE);
                R000_CALL_SCSSCKDT();
                if (pgmRtn) return;
                LNB2000_AWA_2000.CAL_DAY = SCCCKDT.WEEK_NO;
                CEP.TRC(SCCGWA, LNB2000_AWA_2000.CAL_DAY);
            }
        }
        if ((LNB2000_AWA_2000.REPY_MTH == '0' 
            || LNB2000_AWA_2000.REPY_MTH == '1')) {
            LNB2000_AWA_2000.PAYI_PER = '1';
            if (LNB2000_AWA_2000.CAL_FSDT == 0) {
                LNB2000_AWA_2000.CAL_FSDT = LNB2000_AWA_2000.MATU_DT;
            }
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PAY_DTYP);
        if (LNB2000_AWA_2000.PAY_DTYP == '1') {
            if (LNB2000_AWA_2000.PAYI_PER != '4') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PAYI_PER_INP, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                JIBS_tmp_str[0] = "" + LNB2000_AWA_2000.START_DT;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) LNB2000_AWA_2000.CAL_DAY = 0;
                else LNB2000_AWA_2000.CAL_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
            }
        }
        if (LNB2000_AWA_2000.PAY_DTYP == '2' 
            && LNB2000_AWA_2000.CAL_DAY == 0 
            && LNB2000_AWA_2000.CAL_FSDT == 0) {
            JIBS_tmp_str[0] = "" + LNB2000_AWA_2000.START_DT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) LNB2000_AWA_2000.CAL_DAY = 0;
            else LNB2000_AWA_2000.CAL_DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CAL_DAY);
        R000_GET_REPAY_MTH();
        if (pgmRtn) return;
        LNB2000_AWA_2000.CAL_PERD = LNCFFSDT.CAL_PERD;
        LNB2000_AWA_2000.CAL_PERU = LNCFFSDT.CAL_PERU;
        LNB2000_AWA_2000.PYP_PERU = LNCFFSDT.PYP_PERU;
        LNB2000_AWA_2000.PYP_PERD = LNCFFSDT.PYP_PERD;
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PYP_PERD);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CAL_PERD);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PYP_PERU);
        if (LNB2000_AWA_2000.REPY_MTH == '3') {
            if (LNB2000_AWA_2000.PYP_PERD == 0 
                || LNB2000_AWA_2000.CAL_PERD == 0 
                || LNB2000_AWA_2000.PYP_CIRC == ' ') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_PERD_CIRC, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_II = (short) (LNB2000_AWA_2000.CAL_PERD % LNB2000_AWA_2000.PYP_PERD);
            WS_I = (short) ((LNB2000_AWA_2000.CAL_PERD - WS_II) / LNB2000_AWA_2000.PYP_PERD);
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_II);
            if (WS_II != 0 
                || LNB2000_AWA_2000.PYP_PERD == LNB2000_AWA_2000.CAL_PERD 
                || LNB2000_AWA_2000.PYP_PERU != LNB2000_AWA_2000.CAL_PERU) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_REPY_PRIN_ERR, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CAL_FSDT);
        if (LNB2000_AWA_2000.CAL_FSDT == 0 
            && (LNB2000_AWA_2000.REPY_MTH != '0' 
            && LNB2000_AWA_2000.REPY_MTH != '1')) {
            R000_GET_FSTDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, LNCFFSDT.CAL_FSDT);
            if (LNCFFSDT.CAL_FSDT < SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_INT_END_LES_ACDT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                LNB2000_AWA_2000.CAL_FSDT = LNCFFSDT.CAL_FSDT;
            }
        }
        WS_BANK_TYPE = LNB2000_AWA_2000.DW_BK_TP;
        WS_SETL_AC = LNB2000_AWA_2000.DRAW_AC;
        WS_INP_ACT = LNB2000_AWA_2000.DRAW_ACT;
        B110_GET_AC_INFO();
        if (pgmRtn) return;
        LNB2000_AWA_2000.DRAW_ACT = WS_INP_ACT;
        LNB2000_AWA_2000.DRAW_AC = WS_SETL_AC;
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PAY_AC_T);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PA_BK_TP);
        WS_BANK_TYPE = LNB2000_AWA_2000.PA_BK_TP;
        WS_SETL_AC = LNB2000_AWA_2000.PAY_AC;
        WS_INP_ACT = LNB2000_AWA_2000.PAY_AC_T;
        B110_GET_AC_INFO();
        if (pgmRtn) return;
        LNB2000_AWA_2000.PAY_AC_T = WS_INP_ACT;
        LNB2000_AWA_2000.PAY_AC = WS_SETL_AC;
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.FEE_AC);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.FEE_ACT);
        if (LNB2000_AWA_2000.FEE_AC.trim().length() > 0) {
            WS_BANK_TYPE = '0';
            WS_SETL_AC = LNB2000_AWA_2000.FEE_AC;
            WS_INP_ACT = LNB2000_AWA_2000.FEE_ACT;
            B110_GET_AC_INFO();
            if (pgmRtn) return;
            LNB2000_AWA_2000.FEE_ACT = WS_INP_ACT;
        }
    }
    public void B110_GET_AC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_BANK_TYPE);
        if (WS_BANK_TYPE == K_OUR_BANK) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = WS_SETL_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
                WS_SETL_AC_TYPE = K_IB_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
                WS_SETL_AC_TYPE = K_DD_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                WS_SETL_AC_TYPE = K_DC_AC;
            } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                WS_SETL_AC_TYPE = K_IA_AC;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_AC_TYP_NOT_MATCH, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_INP_ACT.trim().length() == 0) {
                WS_INP_ACT = WS_SETL_AC_TYPE;
            } else {
                if (!WS_SETL_AC_TYPE.equalsIgnoreCase(WS_INP_ACT)) {
                    IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ACT_NOT_MATCH, WS_TEMP_VARIABLE.WS_MSGID);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            if (WS_BANK_TYPE == K_OTH_BANK) {
                IBS.init(SCCGWA, AICPQIA);
                AICPQIA.CD.AC_TYP = "3";
                AICPQIA.CD.BUSI_KND = "LNDWSUS";
                AICPQIA.BR = LNB2000_AWA_2000.BOOK_CRE;
                AICPQIA.CCY = LNB2000_AWA_2000.CCY;
                AICPQIA.SIGN = 'D';
                S000_CALL_AIZPQIA();
                if (pgmRtn) return;
                WS_SETL_AC = AICPQIA.AC;
                WS_INP_ACT = K_IA_AC;
            } else {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_BKTP_INP, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B210_WRITE_LNTFWDH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFWDH);
        LNCRFWDH.FUNC = 'A';
        LNRFWDH.KEY.TR_VAL_DATE = LNB2000_AWA_2000.START_DT;
        LNRFWDH.KEY.CONTRACT_NO = LNB2000_AWA_2000.PAPER_NO;
        LNRFWDH.KEY.RECEIPT_NO = LNB2000_AWA_2000.DRAW_NO;
        LNRFWDH.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNRFWDH.LOAN_AC = WS_OUT_DATA.WS_OUT_CONT_NO;
        LNRFWDH.TRAN_STS = '0';
        LNRFWDH.TR_CODE = "0132000";
        LNRFWDH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRFWDH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRFWDH.BLOB_TRAN_DATA = IBS.CLS2CPY(SCCGWA, LNCSWLAD);
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_LEN);
        CEP.TRC(SCCGWA, LNRFWDH.KEY.CONTRACT_NO);
        CEP.TRC(SCCGWA, LNRFWDH.BLOB_TRAN_DATA);
        S000_WRITE_LNTFWDH();
        if (pgmRtn) return;
    }
    public void B220_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSWLAD);
        LNCSWLAD.COMM_DATA.PAPER_NO = LNB2000_AWA_2000.PAPER_NO;
        LNCSWLAD.COMM_DATA.DRAWR_NO = LNB2000_AWA_2000.DRAW_NO;
        LNCSWLAD.COMM_DATA.CTA_NO = WS_OUT_DATA.WS_OUT_CONT_NO;
        LNCSWLAD.COMM_DATA.BOOK_BR = LNB2000_AWA_2000.BOOK_CRE;
        LNCSWLAD.COMM_DATA.DOMI_BR = LNB2000_AWA_2000.DOMI_BR;
        LNCSWLAD.COMM_DATA.CI_NO = LNB2000_AWA_2000.CI_NO;
        LNCSWLAD.COMM_DATA.PROD_CD = LNB2000_AWA_2000.PROD_TYP;
        LNCSWLAD.COMM_DATA.VAL_DT = LNB2000_AWA_2000.START_DT;
        LNCSWLAD.COMM_DATA.INT_PER_STRT = LNB2000_AWA_2000.START_DT;
        LNCSWLAD.COMM_DATA.ACTV_DT = LNB2000_AWA_2000.START_DT;
        LNCSWLAD.COMM_DATA.DUE_DT = LNB2000_AWA_2000.MATU_DT;
        LNCSWLAD.COMM_DATA.CCY = LNB2000_AWA_2000.CCY;
        LNCSWLAD.COMM_DATA.CCY_TYP = LNB2000_AWA_2000.CCY_TYP;
        LNCSWLAD.COMM_DATA.CONT_AMT = LNB2000_AWA_2000.CONT_AMT;
        LNCSWLAD.COMM_DATA.PRINCIPAL = LNB2000_AWA_2000.CONT_AMT;
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.PRINCIPAL);
        LNCSWLAD.COMM_DATA.FLT_MTH = LNB2000_AWA_2000.RAT_MTH;
        LNCSWLAD.COMM_DATA.FLT_PERD_UNIT = LNB2000_AWA_2000.FLPD_UN;
        LNCSWLAD.COMM_DATA.FLT_PERD = LNB2000_AWA_2000.INT_FLPD;
        LNCSWLAD.COMM_DATA.FLT_IPER = LNB2000_AWA_2000.FLT_IPER;
        LNCSWLAD.COMM_DATA.FLT_ADJF = LNB2000_AWA_2000.FLT_ADJF;
        LNCSWLAD.COMM_DATA.HAND_CHG_MTH = LNB2000_AWA_2000.HAND_CHM;
        LNCSWLAD.COMM_DATA.FIRST_FLT_FLG = LNB2000_AWA_2000.FLT_FLG;
        LNCSWLAD.COMM_DATA.FIRST_FIX_DATE = LNB2000_AWA_2000.FLT_DAY;
        LNCSWLAD.COMM_DATA.FIRST_FLT_DT = LNB2000_AWA_2000.FST_DAY;
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.FLT_MTH);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.RAT_TYP);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.RAT_PD);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.RAT_VAR);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.RAT_PCT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.RAT_INT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.IN_RATE);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PEN_RRAT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PEN_TYP);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PEN_RATT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PEN_RATC);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PEN_SPR);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PEN_PCT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PEN_IRAT);
        LNCSWLAD.COMM_DATA.NFLT_METHOD = LNB2000_AWA_2000.FLT_MTH;
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.RAT_TYP);
        LNCSWLAD.COMM_DATA.INT_RATE_TYPE = LNB2000_AWA_2000.RAT_TYP;
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.INT_RATE_TYPE);
        LNCSWLAD.COMM_DATA.INT_RATE_CLAS = LNB2000_AWA_2000.RAT_PD;
        LNCSWLAD.COMM_DATA.RATE_VAR = LNB2000_AWA_2000.RAT_VAR;
        LNCSWLAD.COMM_DATA.RATE_PCT = LNB2000_AWA_2000.RAT_PCT;
        LNCSWLAD.COMM_DATA.RAT_INT = LNB2000_AWA_2000.RAT_INT;
        LNCSWLAD.COMM_DATA.ALL_IN_RATE = LNB2000_AWA_2000.FUND_RT;
        LNCSWLAD.COMM_DATA.PEN_RRAT = LNB2000_AWA_2000.PEN_RRAT;
        LNCSWLAD.COMM_DATA.PEN_TYPE = LNB2000_AWA_2000.PEN_TYP;
        LNCSWLAD.COMM_DATA.PEN_RATT = LNB2000_AWA_2000.PEN_RATT;
        LNCSWLAD.COMM_DATA.PEN_RATC = LNB2000_AWA_2000.PEN_RATC;
        LNCSWLAD.COMM_DATA.PEN_SPR = LNB2000_AWA_2000.PEN_SPR;
        LNCSWLAD.COMM_DATA.PEN_PCT = LNB2000_AWA_2000.PEN_PCT;
        LNCSWLAD.COMM_DATA.PEN_IRAT = LNB2000_AWA_2000.PEN_IRAT;
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.PEN_IRAT);
        LNB2000_AWA_2000.INT_MTH = 'N';
        LNCSWLAD.COMM_DATA.CPND_USE = LNB2000_AWA_2000.CPND_USE;
        LNCSWLAD.COMM_DATA.INT_MTH = LNB2000_AWA_2000.INT_MTH;
        LNCSWLAD.COMM_DATA.CPND_RTYPE = LNB2000_AWA_2000.CPND_RTY;
        LNCSWLAD.COMM_DATA.CPND_TYP = LNB2000_AWA_2000.CPND_TYP;
        LNCSWLAD.COMM_DATA.CPND_RATT = LNB2000_AWA_2000.CPNDRATT;
        LNCSWLAD.COMM_DATA.CPND_RATC = LNB2000_AWA_2000.CPNDRATC;
        LNCSWLAD.COMM_DATA.CPND_SPR = LNB2000_AWA_2000.CPND_SPR;
        LNCSWLAD.COMM_DATA.CPND_PCT = LNB2000_AWA_2000.CPND_PCT;
        LNCSWLAD.COMM_DATA.CPND_IRATE = LNB2000_AWA_2000.CPNDRATE;
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CPND_USE);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.INT_MTH);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CPND_RTY);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CPND_TYP);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CPNDRATT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CPNDRATC);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CPND_SPR);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CPND_PCT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CPNDRATE);
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.CPND_IRATE);
        LNCSWLAD.COMM_DATA.ABUS_RAT_TYP = LNB2000_AWA_2000.ABUS_RAT;
        LNCSWLAD.COMM_DATA.ABUS_FLT_TYP = LNB2000_AWA_2000.ABUSRATT;
        LNCSWLAD.COMM_DATA.ABUS_RATTYPE = LNB2000_AWA_2000.ABUS_TYP;
        LNCSWLAD.COMM_DATA.ABUS_RAT_TERM = LNB2000_AWA_2000.ABUSRATC;
        LNCSWLAD.COMM_DATA.ABUS_SPR = LNB2000_AWA_2000.ABUSSPR;
        LNCSWLAD.COMM_DATA.ABUS_PCT = LNB2000_AWA_2000.ABUSPCT;
        LNCSWLAD.COMM_DATA.ABUS_IRAT = LNB2000_AWA_2000.ABUSIRAT;
        LNCSWLAD.COMM_DATA.REPY_MTH = LNB2000_AWA_2000.REPY_MTH;
        LNCSWLAD.COMM_DATA.INST_MTH = LNB2000_AWA_2000.INST_MTH;
        LNCSWLAD.COMM_DATA.PAYI_PERD = LNB2000_AWA_2000.PAYI_PER;
        LNCSWLAD.COMM_DATA.CAL_PERD_UNIT = LNB2000_AWA_2000.CAL_PERU;
        LNCSWLAD.COMM_DATA.CAL_PERD = LNB2000_AWA_2000.CAL_PERD;
        LNCSWLAD.COMM_DATA.CAL_PAY_DAY_TYP = LNB2000_AWA_2000.PAY_DTYP;
        LNCSWLAD.COMM_DATA.CAL_PAY_DAY = LNB2000_AWA_2000.CAL_DAY;
        LNCSWLAD.COMM_DATA.CAL_FST_DT = LNB2000_AWA_2000.CAL_FSDT;
        LNCSWLAD.COMM_DATA.PYP_CIRC = LNB2000_AWA_2000.PYP_CIRC;
        LNCSWLAD.COMM_DATA.PAYP_PERU = LNB2000_AWA_2000.PYP_PERU;
        LNCSWLAD.COMM_DATA.PAYP_PERD = LNB2000_AWA_2000.PYP_PERD;
        LNCSWLAD.COMM_DATA.SP_LNFLG = LNB2000_AWA_2000.SP_LNFLG;
        LNCSWLAD.COMM_DATA.PHS_FLG = LNB2000_AWA_2000.PHS_FLG;
        LNCSWLAD.COMM_DATA.PHS_NUM = LNB2000_AWA_2000.PHS_NUM;
        for (WS_TEMP_VARIABLE.WS_IDX = 1; WS_TEMP_VARIABLE.WS_IDX <= 5; WS_TEMP_VARIABLE.WS_IDX += 1) {
            LNCSWLAD.COMM_DATA.PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PHS_TOT_TERM = LNB2000_AWA_2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_TTRM;
            LNCSWLAD.COMM_DATA.PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PERU = LNB2000_AWA_2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PERU;
            LNCSWLAD.COMM_DATA.PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PERD = LNB2000_AWA_2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PERD;
            LNCSWLAD.COMM_DATA.PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PRIN_AMT = LNB2000_AWA_2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PAMT;
            LNCSWLAD.COMM_DATA.PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PHS_INST_MTH = LNB2000_AWA_2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_ISMT;
        }
        LNCSWLAD.COMM_DATA.OCAL_PERD_UNIT = LNB2000_AWA_2000.OLC_PERU;
        LNCSWLAD.COMM_DATA.OCAL_PERD = LNB2000_AWA_2000.OLC_PERD;
        LNCSWLAD.COMM_DATA.DRAW_AC_TYP = LNB2000_AWA_2000.DRAW_ACT;
        LNCSWLAD.COMM_DATA.DRAW_AC = LNB2000_AWA_2000.DRAW_AC;
        LNCSWLAD.COMM_DATA.DRAW_BK_TP = LNB2000_AWA_2000.DW_BK_TP;
        LNCSWLAD.COMM_DATA.DRAW_SEQ = LNB2000_AWA_2000.DRAW_SEQ;
        LNCSWLAD.COMM_DATA.PAY_AC_TYP = LNB2000_AWA_2000.PAY_AC_T;
        LNCSWLAD.COMM_DATA.PAY_AC = LNB2000_AWA_2000.PAY_AC;
        LNCSWLAD.COMM_DATA.PAY_BK_TP = LNB2000_AWA_2000.PA_BK_TP;
        LNCSWLAD.COMM_DATA.FS_CL_MTH = LNB2000_AWA_2000.FS_CLMTH;
        LNCSWLAD.COMM_DATA.FS_CL_AMT = LNB2000_AWA_2000.FS_CLAMT;
        LNCSWLAD.COMM_DATA.PAYI_ACT = LNB2000_AWA_2000.PAYI_ACT;
        LNCSWLAD.COMM_DATA.PAYI_AC = LNB2000_AWA_2000.PAYI_AC;
        LNCSWLAD.COMM_DATA.PAYI_SEQ = LNB2000_AWA_2000.PAYI_SEQ;
        LNCSWLAD.COMM_DATA.AUTO_DFLG = LNB2000_AWA_2000.ATO_DFLG;
        if (LNB2000_AWA_2000.ATO_DFLG == 'Y') {
            LNB2000_AWA_2000.ATO_AMT = LNB2000_AWA_2000.CONT_AMT;
        }
        LNCSWLAD.COMM_DATA.AUTO_DSEQ = (short) LNB2000_AWA_2000.ATO_DSEQ;
        LNCSWLAD.COMM_DATA.AUTO_AMT = LNB2000_AWA_2000.ATO_AMT;
        LNCSWLAD.COMM_DATA.ATO_ACT = LNB2000_AWA_2000.ATO_ACT;
        LNCSWLAD.COMM_DATA.ATO_AC = LNB2000_AWA_2000.ATO_AC;
        LNCSWLAD.COMM_DATA.GRA_TYP = LNB2000_AWA_2000.GRA_TYP;
        LNCSWLAD.COMM_DATA.GRA_DAYS_ACC = LNB2000_AWA_2000.GRA_DAYA;
        LNCSWLAD.COMM_DATA.P_GRA_MTH = LNB2000_AWA_2000.P_GRA_MT;
        LNCSWLAD.COMM_DATA.I_GRA_MTH = LNB2000_AWA_2000.C_GRA_MT;
        LNCSWLAD.COMM_DATA.INT_DAY_BASIS = LNB2000_AWA_2000.INT_D_BA;
        LNCSWLAD.COMM_DATA.PSEQ_CD = LNB2000_AWA_2000.PSEQ_CD;
        LNCSWLAD.COMM_DATA.PREP_CHR = LNB2000_AWA_2000.PREP_CHR;
        LNCSWLAD.COMM_DATA.HAND_CHG_RATE = LNB2000_AWA_2000.HAND_CHR;
        LNCSWLAD.COMM_DATA.HAND_MTH = LNB2000_AWA_2000.HAND_CHM;
        LNCSWLAD.COMM_DATA.GUAP_REF = LNB2000_AWA_2000.GUAPREF;
        LNCSWLAD.COMM_DATA.GUAR_DUE_AP = LNB2000_AWA_2000.GUARDUAP;
        LNCSWLAD.COMM_DATA.GUAR_PAY_SEQ = LNB2000_AWA_2000.GUARPSEQ;
        LNCSWLAD.COMM_DATA.RCMPI_FLG = LNCSCKPD.CM_TYP;
        WS_TEMP_VARIABLE.WS_IDX = 1;
        for (WS_TEMP_VARIABLE.WS_IDX = 1; WS_TEMP_VARIABLE.WS_IDX <= 5; WS_TEMP_VARIABLE.WS_IDX += 1) {
            LNCSWLAD.COMM_DATA.FEE_DATE[WS_TEMP_VARIABLE.WS_IDX-1].FEE_CODE = LNB2000_AWA_2000.FEE_INF[WS_TEMP_VARIABLE.WS_IDX-1].FEE_CODE;
            LNCSWLAD.COMM_DATA.FEE_DATE[WS_TEMP_VARIABLE.WS_IDX-1].FEE_AMT = LNB2000_AWA_2000.FEE_INF[WS_TEMP_VARIABLE.WS_IDX-1].FEE_AMT;
            if (LNB2000_AWA_2000.FEE_INF[WS_TEMP_VARIABLE.WS_IDX-1].FEE_CODE.trim().length() > 0) {
                WS_TEMP_VARIABLE.WS_FEE_CNT += 1;
            }
        }
        LNCSWLAD.COMM_DATA.FEE_CNT = WS_TEMP_VARIABLE.WS_FEE_CNT;
        LNCSWLAD.COMM_DATA.FEE_AC_TYP = LNB2000_AWA_2000.FEE_ACT;
        LNCSWLAD.COMM_DATA.FEE_AC = LNB2000_AWA_2000.FEE_AC;
        LNCSWLAD.COMM_DATA.TR_MMO = LNB2000_AWA_2000.TR_MMO;
        LNCSWLAD.COMM_DATA.REMARK1 = LNB2000_AWA_2000.REMARK;
        LNCSWLAD.COMM_DATA.CUSTM1 = LNB2000_AWA_2000.CUSTM1;
        LNCSWLAD.COMM_DATA.CUSTM2 = LNB2000_AWA_2000.CUSTM2;
        LNCSWLAD.COMM_DATA.CUSTM3 = LNB2000_AWA_2000.CUSTM3;
        LNCSWLAD.COMM_DATA.BAR_CD = LNB2000_AWA_2000.BAR_CD;
        LNCSWLAD.COMM_DATA.UNIT_CD = LNB2000_AWA_2000.UNIT_CD;
        LNCSWLAD.COMM_DATA.YHS_FLG = LNB2000_AWA_2000.YHS_FLG;
        LNCSWLAD.COMM_DATA.YHS_AC = LNB2000_AWA_2000.YHS_AC;
        LNCSWLAD.COMM_DATA.PRO_AMT = LNB2000_AWA_2000.PRO_AMT;
        LNCSWLAD.COMM_DATA.PRO_AC = LNB2000_AWA_2000.PRO_AC;
        LNCSWLAD.COMM_DATA.FUND_AC_CONSIGN = LNB2000_AWA_2000.FUND_ACC;
        LNCSWLAD.COMM_DATA.SETTLE_AC_CONSIGN = LNB2000_AWA_2000.SETTLE_A;
        LNCSWLAD.COMM_DATA.SETL_AC_CONS_INT = LNB2000_AWA_2000.SETTLE_B;
        LNCSWLAD.COMM_DATA.GJ_PROJ_NO = LNB2000_AWA_2000.G_PRJ_NO;
        LNCSWLAD.COMM_DATA.PROJ_NO = LNB2000_AWA_2000.PROJ_NO;
        LNCSWLAD.COMM_DATA.SUBS_TTRM = LNB2000_AWA_2000.SUBSTTRM;
        LNCSWLAD.COMM_DATA.SUBS_BTRM = LNB2000_AWA_2000.SUBSBTRM;
        LNCSWLAD.COMM_DATA.W_I_FLG = LNB2000_AWA_2000.W_I_FLG;
        LNCSWLAD.COMM_DATA.W_I_PERU = LNB2000_AWA_2000.W_I_PERU;
        LNCSWLAD.COMM_DATA.W_I_PERD = LNB2000_AWA_2000.W_I_PERD;
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.W_I_PERD);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.FUND_ACC);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.SETTLE_A);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.SETTLE_B);
        if (LNB2000_AWA_2000.START_DT > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_ACDATE, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            S000_CALL_LNZSWLAD();
            if (pgmRtn) return;
        }
        R000_SEND_MESSAGE();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        B310_TRANS_DATA_TO_OUT_FMT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("132161")) {
            SCCFMT.FMTID = K_OUTPUT_FMT_2161;
        } else {
            SCCFMT.FMTID = K_OUTPUT_FMT;
        }
        SCCFMT.DATA_PTR = LNCO2000;
        SCCFMT.DATA_LEN = 1669;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, LNCSWLAD.RC);
    }
    public void B310_TRANS_DATA_TO_OUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = LNCSWLAD.COMM_DATA.CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_OUT_DRAW_NO = LNCSWLAD.COMM_DATA.PAPER_NO;
        WS_OUT_DATA.WS_OUT_PAPER_NO = LNCSWLAD.COMM_DATA.DRAWR_NO;
        WS_OUT_DATA.WS_OUT_CONT_NO = LNCSWLAD.COMM_DATA.CTA_NO;
        WS_OUT_DATA.WS_OUT_BOOK_CRE = LNCSWLAD.COMM_DATA.BOOK_BR;
        WS_OUT_DATA.WS_OUT_DOMI_BR = LNCSWLAD.COMM_DATA.DOMI_BR;
        WS_OUT_DATA.WS_OUT_CI_NO = LNCSWLAD.COMM_DATA.CI_NO;
        WS_OUT_DATA.WS_OUT_CI_CNM = CICCUST.O_DATA.O_CI_NM;
        WS_OUT_DATA.WS_OUT_PROD_TYP = LNCSWLAD.COMM_DATA.PROD_CD;
        WS_OUT_DATA.WS_OUT_VCH_FLG = LNCSWLAD.COMM_DATA.VCH_FLG;
        WS_OUT_DATA.WS_OUT_START_DT = LNCSWLAD.COMM_DATA.VAL_DT;
        WS_OUT_DATA.WS_OUT_MATU_DT = LNCSWLAD.COMM_DATA.DUE_DT;
        WS_OUT_DATA.WS_OUT_CCY = LNCSWLAD.COMM_DATA.CCY;
        WS_OUT_DATA.WS_OUT_CONT_AMT = LNB2000_AWA_2000.CONT_AMT;
        WS_OUT_DATA.WS_OUT_PEN_RATT = LNCSWLAD.COMM_DATA.PEN_RATT.charAt(0);
        WS_OUT_DATA.WS_OUT_CPND_USE = LNCSWLAD.COMM_DATA.CPND_USE;
        WS_OUT_DATA.WS_OUT_REPY_MTH = LNB2000_AWA_2000.REPY_MTH;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_REPY_MTH);
        WS_OUT_DATA.WS_OUT_INST_MTH = LNCSWLAD.COMM_DATA.INST_MTH;
        WS_OUT_DATA.WS_OUT_PAYI_PER = LNCSWLAD.COMM_DATA.PAYI_PERD;
        WS_OUT_DATA.WS_OUT_CAL_PERU = LNB2000_AWA_2000.CAL_PERU;
        WS_OUT_DATA.WS_OUT_CAL_PERD = LNB2000_AWA_2000.CAL_PERD;
        WS_OUT_DATA.WS_OUT_PAY_DTYP = LNB2000_AWA_2000.PAY_DTYP;
        WS_OUT_DATA.WS_OUT_CAL_DAY = LNCSWLAD.COMM_DATA.CAL_PAY_DAY;
        WS_OUT_DATA.WS_OUT_CAL_FSDT = LNCSWLAD.COMM_DATA.CAL_FST_DT;
        WS_OUT_DATA.WS_OUT_PYP_CIRC = LNCSWLAD.COMM_DATA.PYP_CIRC;
        WS_OUT_DATA.WS_OUT_PYP_PERU = LNCSWLAD.COMM_DATA.PAYP_PERU;
        WS_OUT_DATA.WS_OUT_PYP_PERD = LNCSWLAD.COMM_DATA.PAYP_PERD;
        WS_OUT_DATA.WS_OUT_SP_LNFLG = LNB2000_AWA_2000.SP_LNFLG;
        WS_OUT_DATA.WS_OUT_PHS_FLG = LNB2000_AWA_2000.PHS_FLG;
        WS_OUT_DATA.WS_OUT_PHS_NUM = LNB2000_AWA_2000.PHS_NUM;
        WS_TEMP_VARIABLE.WS_IDX = 1;
        while (WS_TEMP_VARIABLE.WS_IDX <= 5) {
            WS_OUT_DATA.WS_OUT_PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].WS_OUT_PHS_TTRM = LNB2000_AWA_2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_TTRM;
            WS_OUT_DATA.WS_OUT_PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].WS_OUT_PHS_PERU = LNB2000_AWA_2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PERU;
            WS_OUT_DATA.WS_OUT_PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].WS_OUT_PHS_PERD = LNB2000_AWA_2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PERD;
            WS_OUT_DATA.WS_OUT_PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].WS_OUT_PHS_PAMT = LNB2000_AWA_2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PAMT;
            WS_OUT_DATA.WS_OUT_PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].WS_OUT_PHS_ISMT = LNB2000_AWA_2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_ISMT;
            WS_TEMP_VARIABLE.WS_IDX += 1;
        }
        WS_OUT_DATA.WS_OUT_OLC_PERU = LNCSWLAD.COMM_DATA.OCAL_PERD_UNIT;
        WS_OUT_DATA.WS_OUT_OLC_PERD = LNCSWLAD.COMM_DATA.OCAL_PERD;
        WS_OUT_DATA.WS_OUT_DRAW_ACT = LNCSWLAD.COMM_DATA.DRAW_AC_TYP;
        WS_OUT_DATA.WS_OUT_DRAW_AC = LNCSWLAD.COMM_DATA.DRAW_AC;
        WS_OUT_DATA.WS_OUT_DW_BK_TP = LNB2000_AWA_2000.DW_BK_TP;
        WS_OUT_DATA.WS_OUT_DRAW_SEQ = LNCSWLAD.COMM_DATA.DRAW_SEQ;
        WS_OUT_DATA.WS_OUT_PAY_AC_T = LNCSWLAD.COMM_DATA.PAY_AC_TYP;
        WS_OUT_DATA.WS_OUT_PAY_AC = LNCSWLAD.COMM_DATA.PAY_AC;
        WS_OUT_DATA.WS_OUT_PA_BK_TP = LNB2000_AWA_2000.PA_BK_TP;
        WS_OUT_DATA.WS_OUT_FS_CLMTH = LNCSWLAD.COMM_DATA.FS_CL_MTH;
        WS_OUT_DATA.WS_OUT_FS_CLAMT = LNCSWLAD.COMM_DATA.FS_CL_AMT;
        WS_OUT_DATA.WS_OUT_PAYI_ACT = LNCSWLAD.COMM_DATA.PAYI_ACT;
        WS_OUT_DATA.WS_OUT_PAYI_AC = LNCSWLAD.COMM_DATA.PAYI_AC;
        WS_OUT_DATA.WS_OUT_PAYI_SEQ = LNCSWLAD.COMM_DATA.PAYI_SEQ;
        WS_OUT_DATA.WS_OUT_YHS_FLG = LNB2000_AWA_2000.YHS_FLG;
        WS_OUT_DATA.WS_OUT_YHS_AC = LNB2000_AWA_2000.YHS_AC;
        WS_OUT_DATA.WS_OUT_ATO_DFLG = LNB2000_AWA_2000.ATO_DFLG;
        WS_OUT_DATA.WS_OUT_ATO_DSEQ = LNB2000_AWA_2000.ATO_DSEQ;
        WS_OUT_DATA.WS_OUT_ATO_AMT = LNB2000_AWA_2000.ATO_AMT;
        WS_OUT_DATA.WS_OUT_ATO_ACT = LNB2000_AWA_2000.ATO_ACT;
        WS_OUT_DATA.WS_OUT_ATO_AC = LNB2000_AWA_2000.ATO_AC;
        WS_OUT_DATA.WS_OUT_PRO_AC = LNB2000_AWA_2000.PRO_AC;
        WS_OUT_DATA.WS_OUT_PRO_AMT = LNB2000_AWA_2000.PRO_AMT;
        WS_OUT_DATA.WS_OUT_GRA_TYP = LNB2000_AWA_2000.GRA_TYP;
        WS_OUT_DATA.WS_OUT_GRA_DAYA = LNB2000_AWA_2000.GRA_DAYA;
        WS_OUT_DATA.WS_OUT_P_GRA_MT = LNCSWLAD.COMM_DATA.P_GRA_MTH;
        WS_OUT_DATA.WS_OUT_C_GRA_MT = LNCSWLAD.COMM_DATA.I_GRA_MTH;
        WS_OUT_DATA.WS_OUT_INT_D_BA = LNCSWLAD.COMM_DATA.INT_DAY_BASIS;
        WS_OUT_DATA.WS_OUT_PSEQ_CD = LNCSWLAD.COMM_DATA.PSEQ_CD;
        WS_OUT_DATA.WS_OUT_PREP_CHR = LNCSWLAD.COMM_DATA.PREP_CHR;
        WS_OUT_DATA.WS_OUT_HAND_CHR = LNB2000_AWA_2000.HAND_CHR;
        WS_OUT_DATA.WS_OUT_HAND_CHM = LNB2000_AWA_2000.HAND_CHM;
        WS_OUT_DATA.WS_OUT_GUAPREF = LNB2000_AWA_2000.GUAPREF;
        WS_OUT_DATA.WS_OUT_GUARDUAP = LNB2000_AWA_2000.GUARDUAP;
        WS_OUT_DATA.WS_OUT_GUARPSEQ = LNB2000_AWA_2000.GUARPSEQ;
        WS_OUT_DATA.WS_OUT_FUND_ACC = LNCSWLAD.COMM_DATA.FUND_AC_CONSIGN;
        WS_OUT_DATA.WS_OUT_SETTLE_A = LNCSWLAD.COMM_DATA.SETTLE_AC_CONSIGN;
        WS_OUT_DATA.WS_OUT_SETTLE_B = LNCSWLAD.COMM_DATA.SETL_AC_CONS_INT;
        WS_OUT_DATA.WS_OUT_PROJ_NO = LNB2000_AWA_2000.PROJ_NO;
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.SUBSTTRM);
        WS_OUT_DATA.WS_OUT_SUBSTTRM = LNB2000_AWA_2000.SUBSTTRM;
        WS_OUT_DATA.WS_OUT_SUBSBTRM = LNB2000_AWA_2000.SUBSBTRM;
        WS_TEMP_VARIABLE.WS_IDX = 1;
        while (WS_TEMP_VARIABLE.WS_IDX <= 5) {
            WS_OUT_DATA.WS_OUT_FEE_DATA[WS_TEMP_VARIABLE.WS_IDX-1].WS_OUT_FEE_CODE = LNB2000_AWA_2000.FEE_INF[WS_TEMP_VARIABLE.WS_IDX-1].FEE_CODE;
            WS_OUT_DATA.WS_OUT_FEE_DATA[WS_TEMP_VARIABLE.WS_IDX-1].WS_OUT_FEE_AMT = LNB2000_AWA_2000.FEE_INF[WS_TEMP_VARIABLE.WS_IDX-1].FEE_AMT;
            WS_TEMP_VARIABLE.WS_IDX += 1;
        }
        WS_OUT_DATA.WS_OUT_FEE_ACT = LNB2000_AWA_2000.FEE_ACT;
        WS_OUT_DATA.WS_OUT_FEE_AC = LNB2000_AWA_2000.FEE_AC;
        WS_OUT_DATA.WS_OUT_TR_MMO = LNB2000_AWA_2000.TR_MMO;
        WS_OUT_DATA.WS_OUT_REMARK1 = LNCSWLAD.COMM_DATA.REMARK1;
        WS_OUT_DATA.WS_OUT_HOLD_NO = LNCSWLAD.COMM_DATA.HLD_NO;
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.HLD_NO);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_HOLD_NO);
        WS_OUT_DATA.WS_OUT_CUSTM1 = LNCSWLAD.COMM_DATA.CUSTM1;
        WS_OUT_DATA.WS_OUT_CUSTM2 = LNCSWLAD.COMM_DATA.CUSTM2;
        WS_OUT_DATA.WS_OUT_CUSTM3 = LNCSWLAD.COMM_DATA.CUSTM3;
        WS_OUT_DATA.WS_OUT_BAR_CD = LNCSWLAD.COMM_DATA.BAR_CD;
        WS_OUT_DATA.WS_OUT_UNIT_CD = LNCSWLAD.COMM_DATA.UNIT_CD;
        IBS.init(SCCGWA, LNCO2000);
        CEP.TRC(SCCGWA, " 111");
        LNCO2000.PAPER_NO = LNB2000_AWA_2000.PAPER_NO;
        LNCO2000.DRAW_NO = LNB2000_AWA_2000.DRAW_NO;
        LNCO2000.CONT_NO = WS_OUT_DATA.WS_OUT_CONT_NO;
        LNCO2000.BOOK_CRE = LNCSWLAD.COMM_DATA.BOOK_BR;
        LNCO2000.DOMI_BR = LNCSWLAD.COMM_DATA.DOMI_BR;
        LNCO2000.CI_NO = LNCSWLAD.COMM_DATA.CI_NO;
        LNCO2000.PROD_TYP = LNCSWLAD.COMM_DATA.PROD_CD;
        LNCO2000.START_DT = LNCSWLAD.COMM_DATA.VAL_DT;
        LNCO2000.START_DT = LNCSWLAD.COMM_DATA.INT_PER_STRT;
        LNCO2000.START_DT = LNCSWLAD.COMM_DATA.ACTV_DT;
        LNCO2000.MATU_DT = LNCSWLAD.COMM_DATA.DUE_DT;
        LNCO2000.CCY = LNCSWLAD.COMM_DATA.CCY;
        LNCO2000.CCY_TYP = LNCSWLAD.COMM_DATA.CCY_TYP;
        LNCO2000.CONT_AMT = LNCSWLAD.COMM_DATA.CONT_AMT;
        LNCO2000.CONT_AMT = LNCSWLAD.COMM_DATA.PRINCIPAL;
        LNCO2000.RAT_MTH = LNCSWLAD.COMM_DATA.FLT_MTH;
        LNCO2000.FLPD_UN = LNCSWLAD.COMM_DATA.FLT_PERD_UNIT;
        LNCO2000.INT_FLPD = LNCSWLAD.COMM_DATA.FLT_PERD;
        LNCO2000.FLT_FLG = LNCSWLAD.COMM_DATA.FIRST_FLT_FLG;
        LNCO2000.FLT_DAY = LNCSWLAD.COMM_DATA.FIRST_FIX_DATE;
        LNCO2000.FST_DAY = LNCSWLAD.COMM_DATA.FIRST_FLT_DT;
        LNCO2000.FLT_MTH = LNCSWLAD.COMM_DATA.NFLT_METHOD;
        LNCO2000.RAT_TYP = LNCSWLAD.COMM_DATA.INT_RATE_TYPE;
        LNCO2000.RAT_PD = LNCSWLAD.COMM_DATA.INT_RATE_CLAS;
        LNCO2000.RAT_VAR = LNCSWLAD.COMM_DATA.RATE_VAR;
        LNCO2000.RAT_PCT = LNCSWLAD.COMM_DATA.RATE_PCT;
        LNCO2000.RAT_INT = LNCSWLAD.COMM_DATA.RAT_INT;
        LNCO2000.IN_RATE = LNCSWLAD.COMM_DATA.ALL_IN_RATE;
        LNCO2000.PEN_RRAT = LNCSWLAD.COMM_DATA.PEN_RRAT;
        LNCO2000.PEN_TYP = LNCSWLAD.COMM_DATA.PEN_TYPE;
        LNCO2000.PEN_RATT = LNCSWLAD.COMM_DATA.PEN_RATT;
        LNCO2000.PEN_RATC = LNCSWLAD.COMM_DATA.PEN_RATC;
        LNCO2000.PEN_SPR = LNCSWLAD.COMM_DATA.PEN_SPR;
        LNCO2000.PEN_PCT = LNCSWLAD.COMM_DATA.PEN_PCT;
        LNCO2000.PEN_IRAT = LNCSWLAD.COMM_DATA.PEN_IRAT;
        LNCO2000.CPND_USE = LNCSWLAD.COMM_DATA.CPND_USE;
        LNCO2000.INT_MTH = LNCSWLAD.COMM_DATA.INT_MTH;
        LNCO2000.CPND_RTY = LNCSWLAD.COMM_DATA.CPND_RTYPE;
        LNCO2000.CPND_TYP = LNCSWLAD.COMM_DATA.CPND_TYP;
        LNCO2000.CPNDRATT = LNCSWLAD.COMM_DATA.CPND_RATT;
        LNCO2000.CPNDRATC = LNCSWLAD.COMM_DATA.CPND_RATC;
        LNCO2000.CPND_SPR = LNCSWLAD.COMM_DATA.CPND_SPR;
        LNCO2000.CPND_PCT = LNCSWLAD.COMM_DATA.CPND_PCT;
        LNCO2000.CPNDRATE = LNCSWLAD.COMM_DATA.CPND_IRATE;
        LNCO2000.ABUS_RAT = LNCSWLAD.COMM_DATA.ABUS_RAT_TYP;
        LNCO2000.ABUSRATT = LNCSWLAD.COMM_DATA.ABUS_FLT_TYP;
        LNCO2000.ABUS_TYP = LNCSWLAD.COMM_DATA.ABUS_RATTYPE;
        LNCO2000.ABUSRATC = LNCSWLAD.COMM_DATA.ABUS_RAT_TERM;
        LNCO2000.ABUSSPR = LNCSWLAD.COMM_DATA.ABUS_SPR;
        LNCO2000.ABUSPCT = LNCSWLAD.COMM_DATA.ABUS_PCT;
        LNCO2000.ABUSIRAT = LNCSWLAD.COMM_DATA.ABUS_IRAT;
        LNCO2000.REPY_MTH = LNCSWLAD.COMM_DATA.REPY_MTH;
        LNCO2000.INST_MTH = LNCSWLAD.COMM_DATA.INST_MTH;
        LNCO2000.PAYI_PER = LNCSWLAD.COMM_DATA.PAYI_PERD;
        LNCO2000.CAL_PERU = LNCSWLAD.COMM_DATA.CAL_PERD_UNIT;
        LNCO2000.CAL_PERD = LNCSWLAD.COMM_DATA.CAL_PERD;
        LNCO2000.PAY_DTYP = LNCSWLAD.COMM_DATA.CAL_PAY_DAY_TYP;
        LNCO2000.CAL_DAY = LNCSWLAD.COMM_DATA.CAL_PAY_DAY;
        LNCO2000.CAL_FSDT = LNCSWLAD.COMM_DATA.CAL_FST_DT;
        LNCO2000.PYP_CIRC = LNCSWLAD.COMM_DATA.PYP_CIRC;
        LNCO2000.PYP_PERU = LNCSWLAD.COMM_DATA.PAYP_PERU;
        LNCO2000.PYP_PERD = LNCSWLAD.COMM_DATA.PAYP_PERD;
        LNCO2000.W_I_FLG = LNB2000_AWA_2000.W_I_FLG;
        LNCO2000.W_I_PERU = LNB2000_AWA_2000.W_I_PERU;
        LNCO2000.W_I_PERD = LNB2000_AWA_2000.W_I_PERD;
        LNCO2000.SP_LNFLG = LNCSWLAD.COMM_DATA.SP_LNFLG;
        LNCO2000.PHS_FLG = LNCSWLAD.COMM_DATA.PHS_FLG;
        LNCO2000.PHS_NUM = LNCSWLAD.COMM_DATA.PHS_NUM;
        WS_TEMP_VARIABLE.WS_IDX = 1;
        while (WS_TEMP_VARIABLE.WS_IDX <= 5) {
            LNCO2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_TTRM = LNCSWLAD.COMM_DATA.PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PHS_TOT_TERM;
            LNCO2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PERU = LNCSWLAD.COMM_DATA.PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PERU;
            LNCO2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PERD = LNCSWLAD.COMM_DATA.PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PERD;
            LNCO2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PAMT = LNCSWLAD.COMM_DATA.PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PHS_PRIN_AMT;
            LNCO2000.PHS_INF[WS_TEMP_VARIABLE.WS_IDX-1].PHS_ISMT = LNCSWLAD.COMM_DATA.PHS_DATA[WS_TEMP_VARIABLE.WS_IDX-1].PHS_INST_MTH;
            WS_TEMP_VARIABLE.WS_IDX += 1;
        }
        LNCO2000.OLC_PERU = LNCSWLAD.COMM_DATA.OCAL_PERD_UNIT;
        LNCO2000.OLC_PERD = LNCSWLAD.COMM_DATA.OCAL_PERD;
        LNCO2000.DRAW_ACT = LNCSWLAD.COMM_DATA.DRAW_AC_TYP;
        LNCO2000.DRAW_AC = LNCSWLAD.COMM_DATA.DRAW_AC;
        LNCO2000.DW_BK_TP = LNCSWLAD.COMM_DATA.DRAW_BK_TP;
        LNCO2000.DRAW_SEQ = LNCSWLAD.COMM_DATA.DRAW_SEQ;
        LNCO2000.PAY_AC_T = LNCSWLAD.COMM_DATA.PAY_AC_TYP;
        LNCO2000.PAY_AC = LNCSWLAD.COMM_DATA.PAY_AC;
        LNCO2000.PA_BK_TP = LNCSWLAD.COMM_DATA.PAY_BK_TP;
        LNCO2000.FS_CLMTH = LNCSWLAD.COMM_DATA.FS_CL_MTH;
        LNCO2000.FS_CLAMT = LNCSWLAD.COMM_DATA.FS_CL_AMT;
        LNCO2000.PAYI_ACT = LNCSWLAD.COMM_DATA.PAYI_ACT;
        LNCO2000.PAYI_AC = LNCSWLAD.COMM_DATA.PAYI_AC;
        LNCO2000.PAYI_SEQ = LNCSWLAD.COMM_DATA.PAYI_SEQ;
        LNCO2000.ATO_DFLG = LNCSWLAD.COMM_DATA.AUTO_DFLG;
        LNCO2000.ATO_DSEQ = LNCSWLAD.COMM_DATA.AUTO_DSEQ;
        LNCO2000.ATO_AMT = LNCSWLAD.COMM_DATA.AUTO_AMT;
        LNCO2000.ATO_ACT = LNCSWLAD.COMM_DATA.ATO_ACT;
        LNCO2000.ATO_AC = LNCSWLAD.COMM_DATA.ATO_AC;
        LNCO2000.GRA_TYP = LNCSWLAD.COMM_DATA.GRA_TYP;
        LNCO2000.GRA_DAYA = LNCSWLAD.COMM_DATA.GRA_DAYS_ACC;
        LNCO2000.P_GRA_MT = LNCSWLAD.COMM_DATA.P_GRA_MTH;
        LNCO2000.C_GRA_MT = LNCSWLAD.COMM_DATA.I_GRA_MTH;
        LNCO2000.INT_D_BA = LNCSWLAD.COMM_DATA.INT_DAY_BASIS;
        LNCO2000.PSEQ_CD = LNCSWLAD.COMM_DATA.PSEQ_CD;
        LNCO2000.PREP_CHR = LNCSWLAD.COMM_DATA.PREP_CHR;
        LNCO2000.HAND_CHR = LNCSWLAD.COMM_DATA.HAND_CHG_RATE;
        LNCO2000.HAND_CHM = LNCSWLAD.COMM_DATA.HAND_MTH;
        LNCO2000.GUAPREF = LNCSWLAD.COMM_DATA.GUAP_REF;
        LNCO2000.GUARDUAP = LNCSWLAD.COMM_DATA.GUAR_DUE_AP;
        LNCO2000.GUARPSEQ = LNCSWLAD.COMM_DATA.GUAR_PAY_SEQ;
        WS_TEMP_VARIABLE.WS_IDX = 1;
        while (WS_TEMP_VARIABLE.WS_IDX <= 5) {
            LNCO2000.FEE_INF[WS_TEMP_VARIABLE.WS_IDX-1].FEE_CODE = LNCSWLAD.COMM_DATA.FEE_DATE[WS_TEMP_VARIABLE.WS_IDX-1].FEE_CODE;
            LNCO2000.FEE_INF[WS_TEMP_VARIABLE.WS_IDX-1].FEE_AMT = LNCSWLAD.COMM_DATA.FEE_DATE[WS_TEMP_VARIABLE.WS_IDX-1].FEE_AMT;
            WS_TEMP_VARIABLE.WS_IDX += 1;
        }
        LNCO2000.FEE_ACT = LNCSWLAD.COMM_DATA.FEE_AC_TYP;
        LNCO2000.FEE_AC = LNCSWLAD.COMM_DATA.FEE_AC;
        LNCO2000.TR_MMO = LNCSWLAD.COMM_DATA.TR_MMO;
        LNCO2000.REMARK = LNCSWLAD.COMM_DATA.REMARK1;
        LNCO2000.CUSTM1 = LNCSWLAD.COMM_DATA.CUSTM1;
        LNCO2000.CUSTM2 = LNCSWLAD.COMM_DATA.CUSTM2;
        LNCO2000.CUSTM3 = LNCSWLAD.COMM_DATA.CUSTM3;
        LNCO2000.BAR_CD = LNCSWLAD.COMM_DATA.BAR_CD;
        LNCO2000.UNIT_CD = LNCSWLAD.COMM_DATA.UNIT_CD;
        LNCO2000.YHS_FLG = LNCSWLAD.COMM_DATA.YHS_FLG;
        LNCO2000.YHS_AC = LNCSWLAD.COMM_DATA.YHS_AC;
        LNCO2000.PRO_AMT = LNCSWLAD.COMM_DATA.PRO_AMT;
        LNCO2000.PRO_AC = LNCSWLAD.COMM_DATA.PRO_AC;
        LNCO2000.FUND_ACC = LNCSWLAD.COMM_DATA.FUND_AC_CONSIGN;
        LNCO2000.SETTLE_A = LNCSWLAD.COMM_DATA.SETTLE_AC_CONSIGN;
        LNCO2000.SETTLE_B = LNCSWLAD.COMM_DATA.SETL_AC_CONS_INT;
        LNCO2000.G_PRJ_NO = LNCSWLAD.COMM_DATA.GJ_PROJ_NO;
        LNCO2000.PROJ_NO = LNCSWLAD.COMM_DATA.PROJ_NO;
        LNCO2000.SUBSTTRM = LNCSWLAD.COMM_DATA.SUBS_TTRM;
        LNCO2000.SUBSBTRM = LNCSWLAD.COMM_DATA.SUBS_BTRM;
        LNCO2000.HLD_NO = LNCSWLAD.COMM_DATA.HLD_NO;
        LNCO2000.HAND_CHM = LNCSWLAD.COMM_DATA.HAND_CHG_MTH;
        CEP.TRC(SCCGWA, " 222");
    }
    public void B600_FINANCE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.REF_NO = LNCSWLAD.COMM_DATA.CTA_NO;
        BPCPFHIS.DATA.AC = LNCSWLAD.COMM_DATA.CTA_NO;
        BPCPFHIS.DATA.CI_NO = LNCSWLAD.COMM_DATA.CI_NO;
        BPCPFHIS.DATA.TX_VAL_DT = LNCSWLAD.COMM_DATA.VAL_DT;
        BPCPFHIS.DATA.TX_CCY = LNCSWLAD.COMM_DATA.CCY;
        BPCPFHIS.DATA.TX_AMT = LNCSWLAD.COMM_DATA.PRINCIPAL;
        BPCPFHIS.DATA.PROD_CD = LNCSWLAD.COMM_DATA.PROD_CD;
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.TR_MMO);
        if (LNB2000_AWA_2000.TR_MMO.trim().length() == 0) {
            LNB2000_AWA_2000.TR_MMO = "12010001";
        }
        BPCPFHIS.DATA.TX_MMO = "D103";
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_MMO);
        CEP.TRC(SCCGWA, LNCSWLAD.COMM_DATA.DRAW_AC);
        BPCPFHIS.DATA.RLT_AC = LNCSWLAD.COMM_DATA.DRAW_AC;
        BPCPFHIS.DATA.OTH_AC = LNCSWLAD.COMM_DATA.DRAW_AC;
        BPCPFHIS.DATA.ACO_AC = WS_OUT_DATA.WS_OUT_CONT_NO;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC);
        BPCPFHIS.DATA.FMT_CODE = "LNY01";
        BPCPFHIS.DATA.FMT_LEN = 1520;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, WS_OUT_DATA);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B990_SEND_CLINFO_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTPCL);
        SCCTPCL.SERV_AREA.OBJ_SYSTEM = "ESBP";
        SCCTPCL.SERV_AREA.SERV_CODE = "3002200000901";
        SCCTPCL.SERV_AREA.SERV_TYPE = 'N';
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_SEQ = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_SEQ.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_SEQ = "0" + SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_SEQ;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_CTRNO = LNB2000_AWA_2000.PAPER_NO;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_DBLNO = LNB2000_AWA_2000.DRAW_NO;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_CCY = LNB2000_AWA_2000.CCY;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_AMT = LNB2000_AWA_2000.CONT_AMT;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_TYP = LNB2000_AWA_2000.PROD_TYP;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_INT = 999;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        if (LNB2000_AWA_2000.REPY_MTH == '1') {
            SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_PAYTYP = "RPT-03";
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        } else if (LNB2000_AWA_2000.REPY_MTH == '2') {
            SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_PAYTYP = "RPT-04";
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        } else if (LNB2000_AWA_2000.REPY_MTH == '4') {
            SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_PAYTYP = "RPT-01";
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        }
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_CYCLE = LNB2000_AWA_2000.CAL_PERD;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_RATE = LNCO2000.IN_RATE;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_BEGDT = LNB2000_AWA_2000.START_DT;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_PAYDT = LNB2000_AWA_2000.MATU_DT;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = LNB2000_AWA_2000.DRAW_AC;
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_AC = LNB2000_AWA_2000.DRAW_AC;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_ACNM = CICQACRI.O_DATA.O_AC_CNM;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_BKNM = "" + CICQACRI.O_DATA.O_OWNER_BK;
        JIBS_tmp_int = SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_BKNM.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_BKNM = "0" + SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_BKNM;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_BK = CICQACRI.O_DATA.O_OWNER_BK;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_CUSTNM = WS_CHECK_INFO.WS_CI_NM;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_IDTYP = WS_CHECK_INFO.WS_ID_TYPE;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_ID = WS_CHECK_INFO.WS_ID_NO;
        SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE);
        SCCTPCL.INP_AREA.SERV_DATA_LEN = 1313;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PAPER_NO);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.DRAW_NO);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CCY);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CONT_AMT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.REQ_SYSTEM);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PROD_TYP);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CAL_PERD);
        CEP.TRC(SCCGWA, LNCO2000.IN_RATE);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.START_DT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.MATU_DT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.DRAW_AC);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_AC_CNM);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OWNER_BK);
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OWNER_BK);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_SEQ);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_CTRNO);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_DBLNO);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_CCY);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_AMT);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_CHNL);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_TYP);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_INT);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_PAYTYP);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_CYCLE);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_RATE);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_BEGDT);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_PAYDT);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_USETYP);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_RMK);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_VHTYP);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_AC);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_ACNM);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_BKNM);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_BK);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_MSGCHN);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_CUSTNM);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_IDTYP);
        CEP.TRC(SCCGWA, SCCTPCL.INP_AREA.BD_LOAN_NOTICE.LOAN_ID);
        if (!SCCGWA.COMM_AREA.REQ_SYSTEM.equalsIgnoreCase("300100")) {
            S000_CALL_SCZTPCL();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_REPAY_MTH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCFFSDT);
        LNCFFSDT.FUNC = 'R';
        LNCFFSDT.PAYI_PERD = LNB2000_AWA_2000.PAYI_PER;
        LNCFFSDT.PYP_CIRC = LNB2000_AWA_2000.PYP_CIRC;
        LNCFFSDT.CAL_PERD = LNB2000_AWA_2000.CAL_PERD;
        LNCFFSDT.CAL_PERU = LNB2000_AWA_2000.CAL_PERU;
        LNCFFSDT.PYP_PERU = LNB2000_AWA_2000.PYP_PERU;
        LNCFFSDT.PYP_PERD = LNB2000_AWA_2000.PYP_PERD;
        CEP.TRC(SCCGWA, LNCFFSDT.FUNC);
        S000_CALL_LNZFFSDT();
        if (pgmRtn) return;
    }
    public void R000_GET_FSTDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCFFSDT);
        LNCFFSDT.FUNC = 'C';
        LNCFFSDT.REPAY_MTH = LNB2000_AWA_2000.REPY_MTH;
        LNCFFSDT.PAYI_PERD = LNB2000_AWA_2000.PAYI_PER;
        LNCFFSDT.START_DT = LNB2000_AWA_2000.START_DT;
        LNCFFSDT.MATU_DT = LNB2000_AWA_2000.MATU_DT;
        LNCFFSDT.PAY_DTYP = LNB2000_AWA_2000.PAY_DTYP;
        LNCFFSDT.CAL_DAY = LNB2000_AWA_2000.CAL_DAY;
        LNCFFSDT.PYP_CIRC = LNB2000_AWA_2000.PYP_CIRC;
        LNCFFSDT.CAL_PERD = LNB2000_AWA_2000.CAL_PERD;
        LNCFFSDT.CAL_PERU = LNB2000_AWA_2000.CAL_PERU;
        LNCFFSDT.CAL_FSDT = LNB2000_AWA_2000.CAL_FSDT;
        LNCFFSDT.PYP_PERU = LNB2000_AWA_2000.PYP_PERU;
        LNCFFSDT.PYP_PERD = LNB2000_AWA_2000.PYP_PERD;
        S000_CALL_LNZFFSDT();
        if (pgmRtn) return;
    }
    public void R000_CHECK_CI_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CI_NO);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.DRAW_ACT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.DW_BK_TP);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.DRAW_AC);
        if (LNB2000_AWA_2000.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, LNCSSTBL);
            LNCSSTBL.CI_NO = LNB2000_AWA_2000.CI_NO;
            LNCSSTBL.S_CODE = "2161";
            S000_CALL_LNZSSTBL();
            if (pgmRtn) return;
            if (LNB2000_AWA_2000.DRAW_ACT.equalsIgnoreCase(K_DD_AC) 
                && LNB2000_AWA_2000.DW_BK_TP == K_OUR_BANK 
                && LNB2000_AWA_2000.DRAW_AC.trim().length() > 0) {
                IBS.init(SCCGWA, LNCSSTBL);
                LNCSSTBL.CI_NO = LNB2000_AWA_2000.CI_NO;
                LNCSSTBL.DRAW_AC = LNB2000_AWA_2000.DRAW_AC;
                LNCSSTBL.CCY = LNB2000_AWA_2000.CCY;
                LNCSSTBL.CCY_TYP = LNB2000_AWA_2000.CCY_TYP;
                LNCSSTBL.S_CODE = "2161";
                S000_CALL_LNZSSTBL();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CI_NO);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PAY_AC_T);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PA_BK_TP);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.PAY_AC);
        if (LNB2000_AWA_2000.PAY_AC_T.equalsIgnoreCase(K_DD_AC) 
            && LNB2000_AWA_2000.PA_BK_TP == K_OUR_BANK 
            && LNB2000_AWA_2000.CI_NO.trim().length() > 0 
            && LNB2000_AWA_2000.PAY_AC.trim().length() > 0) {
            IBS.init(SCCGWA, LNCSSTBL);
            LNCSSTBL.CI_NO = LNB2000_AWA_2000.CI_NO;
            LNCSSTBL.DRAW_AC = LNB2000_AWA_2000.PAY_AC;
            S000_CALL_LNZSSTBL();
            if (pgmRtn) return;
        }
        if (LNB2000_AWA_2000.PAY_AC_T.equalsIgnoreCase(K_DD_AC) 
            && LNB2000_AWA_2000.PA_BK_TP == K_OUR_BANK 
            && LNB2000_AWA_2000.CI_NO.trim().length() > 0 
            && LNB2000_AWA_2000.PAY_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = LNB2000_AWA_2000.PAY_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_TYPE);
            if (DDCIMMST.DATA.AC_TYPE == 'M') {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_LN0353, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB2000_AWA_2000.PAY_AC_T.equalsIgnoreCase(K_DD_AC) 
            && LNB2000_AWA_2000.PA_BK_TP == K_OUR_BANK 
            && LNB2000_AWA_2000.CI_NO.trim().length() > 0 
            && LNB2000_AWA_2000.PAY_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCIMCCY);
            DDCIMCCY.DATA[1-1].AC = LNB2000_AWA_2000.PAY_AC;
            DDCIMCCY.DATA[1-1].CCY = LNB2000_AWA_2000.CCY;
            DDCIMCCY.DATA[1-1].CCY_TYPE = LNB2000_AWA_2000.CCY_TYP;
            S000_CALL_DDZIMCCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY);
            CEP.TRC(SCCGWA, LNB2000_AWA_2000.CCY);
            CEP.TRC(SCCGWA, DDCIMCCY.DATA[1-1].CCY_TYPE);
            if (!DDCIMCCY.DATA[1-1].CCY.equalsIgnoreCase(LNB2000_AWA_2000.CCY)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CCY_NOT_MATCH, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB2000_AWA_2000.PAY_AC_T.equalsIgnoreCase(K_IA_AC) 
            && LNB2000_AWA_2000.PA_BK_TP == K_OUR_BANK 
            && LNB2000_AWA_2000.CI_NO.trim().length() > 0 
            && LNB2000_AWA_2000.PAY_AC.trim().length() > 0) {
            if (LNB2000_AWA_2000.PAY_AC == null) LNB2000_AWA_2000.PAY_AC = "";
            JIBS_tmp_int = LNB2000_AWA_2000.PAY_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) LNB2000_AWA_2000.PAY_AC += " ";
            CEP.TRC(SCCGWA, LNB2000_AWA_2000.PAY_AC.substring(7 - 1, 7 + 3 - 1));
            if (LNB2000_AWA_2000.PAY_AC == null) LNB2000_AWA_2000.PAY_AC = "";
            JIBS_tmp_int = LNB2000_AWA_2000.PAY_AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) LNB2000_AWA_2000.PAY_AC += " ";
            if (!LNB2000_AWA_2000.PAY_AC.substring(7 - 1, 7 + 3 - 1).equalsIgnoreCase(LNB2000_AWA_2000.CCY)) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_CCY_NOT_MATCH, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB2000_AWA_2000.DRAW_ACT.equalsIgnoreCase(K_IA_AC) 
            && LNB2000_AWA_2000.DW_BK_TP == K_OUR_BANK 
            && LNB2000_AWA_2000.CI_NO.trim().length() > 0 
            && LNB2000_AWA_2000.DRAW_AC.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, LNB2000_AWA_2000.DRAW_AC, WS_IA_AC);
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
            AICPQITM.INPUT_DATA.NO = WS_IA_AC.WS_IA_AC_KEMU;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.TYPE);
            if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6")) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IA_AC_OUT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (LNB2000_AWA_2000.PAY_AC_T.equalsIgnoreCase(K_IA_AC) 
            && LNB2000_AWA_2000.PA_BK_TP == K_OUR_BANK 
            && LNB2000_AWA_2000.CI_NO.trim().length() > 0 
            && LNB2000_AWA_2000.PAY_AC.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, LNB2000_AWA_2000.PAY_AC, WS_IA_AC);
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.BOOK_FLG = "BK001";
            AICPQITM.INPUT_DATA.NO = WS_IA_AC.WS_IA_AC_KEMU;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.TYPE);
            if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6")) {
                IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_IA_AC_OUT, WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_SEND_MESSAGE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CI_NO);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CONT_AMT);
        CEP.TRC(SCCGWA, LNB2000_AWA_2000.CCY);
        IBS.init(SCCGWA, SCCWRMSG);
        SCCWRMSG.DATE = SCCGWA.COMM_AREA.AC_DATE;
        SCCWRMSG.JRNNO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = SCCWRMSG.JRNNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCWRMSG.JRNNO = "0" + SCCWRMSG.JRNNO;
        SCCWRMSG.AP_CODE = "33";
        SCCWRMSG.CI_NO = LNB2000_AWA_2000.CI_NO;
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) SCCWRMSG.YEAR = 0;
        else SCCWRMSG.YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) SCCWRMSG.MONTH = 0;
        else SCCWRMSG.MONTH = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) SCCWRMSG.DAY = 0;
        else SCCWRMSG.DAY = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        SCCWRMSG.CCY = LNB2000_AWA_2000.CCY;
        SCCWRMSG.AMT = LNB2000_AWA_2000.CONT_AMT;
        R000_CALL_SCZWRMSG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG, true);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            WS_ERR_INFO = "" + BPCPQORG.BR;
            JIBS_tmp_int = WS_ERR_INFO.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) WS_ERR_INFO = "0" + WS_ERR_INFO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_WRITE_LNTFWDH() throws IOException,SQLException,Exception {
        LNTFWDH_RD = new DBParm();
        LNTFWDH_RD.TableName = "LNTFWDH";
        IBS.WRITE(SCCGWA, LNRFWDH, LNTFWDH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, LNCRFWDH.RC);
            LNCRFWDH.RETURN_INFO = 'D';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_FUND_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRFUND.KEY.PROJ_NO);
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        IBS.READ(SCCGWA, LNRFUND, LNTFUND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUND_NOT_EXIST, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY, true);
        if (BPCQCCY.RC.RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCQCCY.RC.RTNCODE+"", WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-CCY", DDCIMCCY, true);
        if (DDCIMCCY.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "CI-INQ-CUST-ACR";
        SCCCALL.COMMAREA_PTR = CICQCIAC;
        SCCCALL.NOFMT_FLG = 'Y';
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (CICQCIAC.RC.RC_CODE != 0 
            && CICQCIAC.RC.RC_CODE != 8051) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCIAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSCORT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-SEL-RT", BPCSCORT, true);
        if (BPCSCORT.OUTPUT_INFO.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCORT.OUTPUT_INFO.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSSTBL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-CHECK-CI-STS", LNCSSTBL, true);
        if (LNCSSTBL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSSTBL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM, true);
        if (AICPQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFCSTS);
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS, true);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSWLAD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-WLLOAN-ADD2DD", LNCSWLAD);
        if (LNCSWLAD.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSWLAD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZCTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-PRM-CTLPM-MAINT", LNCCTLPM);
        if (LNCCTLPM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = LNCCTLPM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = LNCCTLPM.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCMMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-TRANCH-MAINTAIN", LNCSCMMT, true);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA, true);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0 
            || JIBS_tmp_str[1].trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        CICQCIAC.FUNC = '2';
        WS_TEMP_VARIABLE.WS_TOT_AMT = LNB2000_AWA_2000.CONT_AMT;
        CICQCIAC.DATA.FRM_APP = "LN";
        CICQCIAC.DATA.CI_NO = LNB2000_AWA_2000.CI_NO;
        CICQCIAC.DATA.LAST_ENTY_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[100-1].ENTY_NO;
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        for (WS_TEMP_VARIABLE.WS_N = 1; WS_TEMP_VARIABLE.WS_N <= 100 
            && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TEMP_VARIABLE.WS_N-1].ENTY_NO.trim().length() != 0; WS_TEMP_VARIABLE.WS_N += 1) {
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_N);
            LNRBALZ.KEY.CONTRACT_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TEMP_VARIABLE.WS_N-1].ENTY_NO;
            LNRCONT.KEY.CONTRACT_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_TEMP_VARIABLE.WS_N-1].ENTY_NO;
            T000_READ_LNTCONT();
            if (pgmRtn) return;
            if (LNRCONT.CCY.equalsIgnoreCase("156")) {
                T000_READ_LNTBALZ();
                if (pgmRtn) return;
                WS_TEMP_VARIABLE.WS_TOT_AMT = WS_TEMP_VARIABLE.WS_TOT_AMT + LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06;
            }
        }
    }
    public void T000_READ_LNTBALZ() throws IOException,SQLException,Exception {
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void S000_CALL_BPZSEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX, true);
        if (BPCEX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSEXEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-WLEXCHG-AMT", LNCSEXEQ, true);
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI, true);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCMRT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CMRT", LNCSCMRT, true);
    }
    public void S000_CALL_LNZFFSDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CAL-FSDT", LNCFFSDT, true);
        if (LNCFFSDT.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCFFSDT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_BPZGACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-GENERTE-ACNO", BPCCGAC, true);
        if (BPCCGAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCGAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIGECR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-GENERATE-CNTRNO", LNCIGECR, true);
        if (LNCIGECR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIGECR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST, true);
        if (DDCIMMST.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_NOT_FOUND_OR_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AGRE_CONT_R() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "DRAW_NO = :LNRAGRE.DRAW_NO "
            + "AND PAPER_NO = :LNRAGRE.PAPER_NO";
        LNTAGRE_RD.fst = true;
        LNTAGRE_RD.order = "PAPER_NO DESC";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_AGRE_CONT() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "DRAW_NO = :LNRAGRE.DRAW_NO "
            + "AND PAPER_NO = :LNRAGRE.PAPER_NO";
        LNTAGRE_RD.fst = true;
        LNTAGRE_RD.order = "PAPER_NO DESC";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DRAW_DUPKEY, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "DRAW_NO = :LNRAGRE.DRAW_NO";
        LNTAGRE_RD.fst = true;
        LNTAGRE_RD.order = "NEXT_SEQ DESC";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMMT_FLG = 'Y';
        } else {
            WS_CMMT_FLG = 'N';
        }
    }
    public void T000_READUPD_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.upd = true;
        IBS.READ(SCCGWA, LNRAGRE, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_WRITE_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.WRITE(SCCGWA, LNRAGRE, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_DUPKEY, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_AGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        IBS.REWRITE(SCCGWA, LNRAGRE, LNTAGRE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void S000_CALL_LNZSCKPD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-S-CHK-PROD", LNCSCKPD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU, true);
    }
    public void S000_CALL_VTZUJMDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-P-JMAC-REGISTER", VTCUJMDR, true);
        if (VTCUJMDR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, VTCUJMDR.RC);
        }
    }
    public void R000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = "SC";
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = SCCCKDT.RC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CALL_SCZWRMSG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SCZWRMSG-MMSG", SCCWRMSG, true);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
