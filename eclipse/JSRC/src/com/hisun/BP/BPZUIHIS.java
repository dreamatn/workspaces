package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICACCU;
import com.hisun.CI.CICQACAC;
import com.hisun.DC.DCCMSG_ERROR_MSG;
import com.hisun.DC.DCCPACTY;
import com.hisun.DD.DDCIQPRD;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCTLM_MSG;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZUIHIS {
    boolean pgmRtn = false;
    String CPN_R_INQ_FHIST = "BP-R-INQ-FHIST";
    String CPN_R_PROC_NHIST = "BP-R-PROC-NHIST";
    String CPN_R_INQ_FHISA = "BP-R-INQ-FHISA";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_CI_CIZACCU_CN = "CI-INQ-ACCU";
    String CPN_CI_DDZUINVS_CN = "DD-I-VS-INFO";
    String CPN_DC_DCZUMPOS_CN = "DC-U-POS-RECORD";
    String CPN_CI_CIZACCU_MO = "CI-CIZACCU";
    String CPN_P_QUE_ACTY = "DC-INQ-AC-INF";
    int K_MAX_OUTPUT_CNT = 500;
    int K_MAX_OUTPUT_CNT2 = 999999;
    short K_MAX_OUTPUT_PAGE = 9999;
    int K_FHIS_MAX = 200;
    String K_DC_TX_CD = "POS";
    String K_OUTPUT_FMT = "BP056";
    short K_DEF_RECNO = 10;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPZUIHIS_WS_SUB_AC_DATA WS_SUB_AC_DATA = new BPZUIHIS_WS_SUB_AC_DATA();
    String WS_VASUB_AC = " ";
    int WS_I = 0;
    int WS_VA_REC = 0;
    int WS_RECORD_NUM = 0;
    int WS_FIRST_FETCH = 0;
    int WS_COUNT_HIS = 0;
    int WS_COUNT_TOD = 0;
    int WS_PAGE_STRNO = 0;
    int WS_PAGE_ENDNO = 0;
    int WS_HIS_REC_NUM = 0;
    double WS_CUR_BAL = 0;
    int WS_CUR_CNT = 0;
    int WS_COND_CNT = 0;
    char WS_AC_COND = ' ';
    char WS_FND_FLG = ' ';
    int WS_CCY_CNT = 0;
    int WS_REC_CNT = 0;
    int WS_FHIS_CNT = 0;
    int WS_CNT = 0;
    int WS_PAGE_TOT = 0;
    BPZUIHIS_WS_CCY_BALS[] WS_CCY_BALS = new BPZUIHIS_WS_CCY_BALS[100];
    BPZUIHIS_WS_OUTPUT_TIT_CN WS_OUTPUT_TIT_CN = new BPZUIHIS_WS_OUTPUT_TIT_CN();
    BPZUIHIS_WS_TS_QUEUE WS_TS_QUEUE = new BPZUIHIS_WS_TS_QUEUE();
    BPZUIHIS_WS_TS_QUEUE_CN WS_TS_QUEUE_CN = new BPZUIHIS_WS_TS_QUEUE_CN();
    BPZUIHIS_PRODUCT_TABLE[] PRODUCT_TABLE = new BPZUIHIS_PRODUCT_TABLE[100];
    int WS_CNT_AC = 0;
    BPZUIHIS_WS_AC_TABLE[] WS_AC_TABLE = new BPZUIHIS_WS_AC_TABLE[100];
    BPZUIHIS_WS_LOCAL_ACTY WS_LOCAL_ACTY = new BPZUIHIS_WS_LOCAL_ACTY();
    char WS_FHIST_FLG = ' ';
    char WS_NHIST_FLG = ' ';
    char WS_INQ_FHIST = ' ';
    char WS_INQ_NHIST = ' ';
    char WS_LOOP_FLG = ' ';
    char WS_DDAC_FLG = ' ';
    char WS_NEED_BROW_FLG = ' ';
    char WS_BAL_FLG = ' ';
    char WS_INP_AC_TYP = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPRNHIST BPRNHIST = new BPRNHIST();
    BPCTHIST BPCTHIST = new BPCTHIST();
    BPRFHISA BPRFHISA = new BPRFHISA();
    BPCIFHSA BPCIFHSA = new BPCIFHSA();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    BPRFHIS BPRFHIS = new BPRFHIS();
    SCCFMT SCCFMT = new SCCFMT();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    CICACCU CICACCU2 = new CICACCU();
    DCCUMPOS DCCUMPOS = new DCCUMPOS();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCUINVS DDCUINVS = new DDCUINVS();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    BPCIPHIS BPCIPHIS;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCUIHIS BPCUIHIS;
    public BPZUIHIS() {
        for (int i=0;i<100;i++) WS_CCY_BALS[i] = new BPZUIHIS_WS_CCY_BALS();
        for (int i=0;i<100;i++) PRODUCT_TABLE[i] = new BPZUIHIS_PRODUCT_TABLE();
        for (int i=0;i<100;i++) WS_AC_TABLE[i] = new BPZUIHIS_WS_AC_TABLE();
    }
    public void MP(SCCGWA SCCGWA, BPCUIHIS BPCUIHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUIHIS = BPCUIHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUIHIS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUIHIS.RC);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            BPCIPHIS = (BPCIPHIS) BPCUIHIS.DATA.PAGE_POINTER;
            if (BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT > 0) {
                CEP.TRC(SCCGWA, BPCIPHIS.FORWORD.INP_FI_LEN);
                if (BPCIPHIS.INPUT.FILLER == null) BPCIPHIS.INPUT.FILLER = "";
                JIBS_tmp_int = BPCIPHIS.INPUT.FILLER.length();
                for (int i=0;i<124-JIBS_tmp_int;i++) BPCIPHIS.INPUT.FILLER += " ";
                if (BPCIPHIS.INPUT.FILLER.substring(0, BPCIPHIS.FORWORD.INP_FI_LEN).trim().length() == 0) WS_OUTPUT_TIT_CN.WS_TOD_REC_NUM = 0;
                else WS_OUTPUT_TIT_CN.WS_TOD_REC_NUM = Integer.parseInt(BPCIPHIS.INPUT.FILLER.substring(0, BPCIPHIS.FORWORD.INP_FI_LEN));
                CEP.TRC(SCCGWA, WS_OUTPUT_TIT_CN.WS_TOD_REC_NUM);
            }
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT_CN();
            if (pgmRtn) return;
            B020_BROWSE_HIST_CN();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_BROWSE_HIST();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUIHIS);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.REC_NUM);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.REC_FLG);
        CEP.TRC(SCCGWA, BPCIPHIS.INPUT.PAGE_NO);
        CEP.TRC(SCCGWA, BPCIPHIS.INPUT.PAGE_RECNO);
        if (BPCIPHIS.INPUT.PAGE_NO < 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PAGE_NO_MUST);
        }
        if (BPCIPHIS.INPUT.PAGE_RECNO == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PAGE_RECNO_NONE);
            BPCIPHIS.INPUT.PAGE_RECNO = K_DEF_RECNO;
        }
        if (BPCIPHIS.INPUT.PAGE_RECNO > K_DEF_RECNO) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_OVER_PG_LIMIT);
            BPCIPHIS.INPUT.PAGE_RECNO = K_DEF_RECNO;
        }
        BPCUIHIS.DATA.REC_NUM = (short) BPCIPHIS.INPUT.PAGE_RECNO;
        if (BPCUIHIS.DATA.REC_FLG != 'Y' 
            && BPCUIHIS.DATA.REC_FLG != 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_REC_FLG_MUST);
        }
        WS_INQ_FHIST = 'N';
        WS_INQ_NHIST = 'N';
        B010_CHECK_RECORD_CN();
        if (pgmRtn) return;
        if (BPCUIHIS.DATA.REC_FLG == 'Y') {
            WS_INQ_FHIST = 'Y';
        } else {
            WS_INQ_NHIST = 'Y';
        }
    }
    public void B010_CHECK_RECORD_CN() throws IOException,SQLException,Exception {
        if (BPCUIHIS.DATA.JRNNO == 0 
            && BPCUIHIS.DATA.AC.trim().length() == 0 
            && BPCUIHIS.DATA.CI_NO.trim().length() == 0 
            && BPCUIHIS.DATA.REF_NO.trim().length() == 0 
            && BPCUIHIS.DATA.TX_TOOL.trim().length() == 0 
            && BPCUIHIS.DATA.TX_CD.trim().length() == 0 
            && BPCUIHIS.DATA.BR == 0 
            && BPCUIHIS.DATA.TLR.trim().length() == 0 
            && BPCUIHIS.DATA.STR_AMT == 0 
            && BPCUIHIS.DATA.END_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCUIHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_COND_CNT = 0;
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.JRNNO);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.FINANCE_FLG);
        CEP.TRC(SCCGWA, WS_INQ_FHIST);
        CEP.TRC(SCCGWA, WS_INQ_NHIST);
        if (BPCUIHIS.DATA.JRNNO != 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.CI_NO);
        if (BPCUIHIS.DATA.CI_NO.trim().length() > 0) {
            WS_COND_CNT += 1;
            WS_DDAC_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.AC);
        if (BPCUIHIS.DATA.AC.trim().length() > 0) {
            WS_COND_CNT += 1;
            WS_AC_COND = 'Y';
            WS_DDAC_FLG = 'Y';
            CEP.TRC(SCCGWA, "TTTTTTTTTTTTTTT");
        } else {
            WS_AC_COND = 'N';
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.REF_NO);
        if (BPCUIHIS.DATA.REF_NO.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.TX_TOOL);
        if (BPCUIHIS.DATA.TX_TOOL.trim().length() > 0) {
            WS_COND_CNT += 1;
            B070_CONFIRM_TOOL_TP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.TX_CD);
        if (BPCUIHIS.DATA.TX_CD.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.TX_TYPE_CD);
        if (BPCUIHIS.DATA.TX_TYPE_CD.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.TLR);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.DC_FLG);
        if (BPCUIHIS.DATA.DC_FLG != ' ') {
            if ((BPCUIHIS.DATA.AC.trim().length() == 0 
                && BPCUIHIS.DATA.TX_TOOL.trim().length() == 0)) {
                WS_COND_CNT += 1;
            }
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.MAKER_ID);
        if (BPCUIHIS.DATA.MAKER_ID.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.CHECKER_ID);
        if (BPCUIHIS.DATA.CHECKER_ID.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, WS_COND_CNT);
        if (WS_COND_CNT > 1) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MORE_THAN_ONE_COND, BPCUIHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B021_CYCL_BROWSE_CN() throws IOException,SQLException,Exception {
        if (WS_INQ_FHIST == 'Y') {
            if (WS_NEED_BROW_FLG == 'Y') {
                R000_STARTBR_BPTFHIST_CN();
                if (pgmRtn) return;
                R000_FETCH_BPTFHIST();
                if (pgmRtn) return;
                WS_NHIST_FLG = 'N';
            }
        } else {
            CEP.TRC(SCCGWA, "ANANANN");
            R000_STARTBR_BPTNHIST();
            if (pgmRtn) return;
            R000_FETCH_BPTNHIST();
            if (pgmRtn) return;
            WS_FHIST_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_INQ_FHIST);
        WS_REC_CNT = 1;
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.REC_NUM);
        CEP.TRC(SCCGWA, WS_REC_CNT);
        while ((WS_FHIST_FLG != 'N' 
            || WS_NHIST_FLG != 'N') 
            && (WS_REC_CNT <= WS_RECORD_NUM) 
            && WS_NEED_BROW_FLG != 'N') {
            CEP.TRC(SCCGWA, WS_REC_CNT);
            if (WS_FHIST_FLG == 'Y' 
                && WS_NHIST_FLG == 'N') {
                if ((BPCUIHIS.DATA.CCY.trim().length() == 0 
                    || BPCUIHIS.DATA.CCY.equalsIgnoreCase(BPRFHIST.TX_CCY)) 
                    && (BPCUIHIS.DATA.DC_FLG == ' ' 
                    || BPCUIHIS.DATA.DC_FLG == BPRFHIST.DRCRFLG) 
                    && (BPRFHIST.TX_STS != 'C' 
                    || BPRFHIST.TX_REV_DT != BPRFHIST.ORG_AC_DT)) {
                    R000_OUTPUT_FHIST_REC_CN();
                    if (pgmRtn) return;
                    R000_CHECK_OUTPUT_CNT();
                    if (pgmRtn) return;
                }
                R000_READNEXT_BPTFHIST();
                if (pgmRtn) return;
            } else {
                if ((BPRNHIST.TX_STS != 'C' 
                    || BPRNHIST.TX_REV_DT != BPRNHIST.ORG_AC_DT)) {
                    R000_OUTPUT_NHIST_REC_CN();
                    if (pgmRtn) return;
                    R000_CHECK_OUTPUT_CNT();
                    if (pgmRtn) return;
                }
                R000_READNEXT_BPTNHIST();
                if (pgmRtn) return;
            }
        }
        if (WS_INQ_FHIST == 'Y') {
            if (WS_NEED_BROW_FLG == 'Y') {
                R000_ENDBR_BPTFHIST();
                if (pgmRtn) return;
            }
        } else {
            R000_ENDBR_BPTNHIST();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_HIST_CN() throws IOException,SQLException,Exception {
        WS_NEED_BROW_FLG = 'N';
        if (BPCIPHIS.INPUT.PAGE_NO > 0) {
            WS_NEED_BROW_FLG = 'Y';
        }
        R000_WRITE_PAGE_TITLE_CN();
        if (pgmRtn) return;
        B021_CYCL_BROWSE_CN();
        if (pgmRtn) return;
        BPCIPHIS.OUTPUT.REC_TITLE.RECORD_NUM = (short) (WS_REC_CNT - 1);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.RECORD_NUM);
    }
    public void B060_CALC_BOTH_TB_CN() throws IOException,SQLException,Exception {
        WS_HIS_REC_NUM = BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT - WS_OUTPUT_TIT_CN.WS_TOD_REC_NUM;
        WS_COUNT_HIS = WS_HIS_REC_NUM;
        WS_COUNT_TOD = WS_OUTPUT_TIT_CN.WS_TOD_REC_NUM;
        CEP.TRC(SCCGWA, WS_COUNT_HIS);
        CEP.TRC(SCCGWA, WS_COUNT_TOD);
        if (BPCUIHIS.DATA.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "SORT_DESC_UNCOUNT");
            if (WS_PAGE_STRNO > WS_COUNT_TOD) {
                CEP.TRC(SCCGWA, "JUST FETCH HIS TABLE");
                R000_GET_LAST_AC_DT_CN();
                if (pgmRtn) return;
                BPCUIHIS.DATA.END_AC_DT = BPCOCLWD.DATE2;
                WS_FIRST_FETCH = WS_PAGE_STRNO - WS_COUNT_TOD;
            }
        } else {
            CEP.TRC(SCCGWA, "SORT_ASC_UNCOUNT");
            if (WS_PAGE_STRNO > WS_COUNT_HIS) {
                CEP.TRC(SCCGWA, "JUST FETCH TOD TABLE");
                BPCUIHIS.DATA.STR_AC_DT = SCCGWA.COMM_AREA.AC_DATE;
                WS_FIRST_FETCH = WS_PAGE_STRNO - WS_COUNT_HIS;
            }
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCBJPRM: BPZPCLWD ERROR ");
            CEP.TRC(SCCGWA, BPCOCLWD.RC.RC_CODE);
            SCCGWA.RETURN_CODE = 8;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_LAST_AC_DT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
        BPCOCLWD.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        BPCOCLWD.DAYS = -2;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        CEP.TRC(SCCGWA, BPCOCLWD.CAL_CODE);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
    }
    public void R000_WRITE_PAGE_TITLE_CN_OLD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIPHIS);
        CEP.TRC(SCCGWA, BPCIPHIS.INPUT.PAGE_NO);
        CEP.TRC(SCCGWA, BPCIPHIS.INPUT.PAGE_RECNO);
        if (BPCIPHIS.INPUT.PAGE_NO == 0) {
            CEP.TRC(SCCGWA, "FIRST_CHECK_RECORD");
            WS_PAGE_ENDNO = BPCIPHIS.INPUT.PAGE_RECNO;
        } else {
            CEP.TRC(SCCGWA, "NOT_FIRST_CHECK_RECORD");
            WS_RECORD_NUM = BPCIPHIS.INPUT.PAGE_RECNO;
            WS_PAGE_ENDNO = BPCIPHIS.INPUT.PAGE_NO * BPCIPHIS.INPUT.PAGE_RECNO;
        }
        WS_PAGE_STRNO = WS_PAGE_ENDNO - BPCIPHIS.INPUT.PAGE_RECNO;
        WS_FIRST_FETCH = WS_PAGE_STRNO;
        R010_CALC_IPHIS_CN();
        if (pgmRtn) return;
        if (BPCIPHIS.INPUT.PAGE_NO <= 1 
            || BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT == BPCIPHIS.INPUT.PAGE_NO) {
            BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT = 0;
            if (WS_INQ_FHIST == 'Y') {
                R000_BROW_COUNT_BPTFHIST();
                if (pgmRtn) return;
                BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT = BPCIFHIS.OUTPUT.COUNT_NO.COUNT_ALL;
                WS_OUTPUT_TIT_CN.WS_TOD_REC_NUM = BPCIFHIS.OUTPUT.COUNT_NO.COUNT_TOD;
                WS_HIS_REC_NUM = BPCIFHIS.OUTPUT.COUNT_NO.COUNT_HIS;
            } else {
                R000_BROW_COUNT_BPTNHIST();
                if (pgmRtn) return;
            }
            if (BPCIPHIS.INPUT.PAGE_NO == 0 
                && BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT > 0) {
                BPCIPHIS.INPUT.PAGE_NO = 1;
            }
            R010_CALC_IPHIS_CN();
            if (pgmRtn) return;
        }
        if (WS_INQ_FHIST == 'Y') {
            B060_CALC_BOTH_TB_CN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.RECORD_NUM);
        CEP.TRC(SCCGWA, BPCIPHIS.INPUT.PAGE_NO);
        BPCIPHIS.OUTPUT.REC_TITLE.NOW_PAGE_NO = (short) BPCIPHIS.INPUT.PAGE_NO;
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.NOW_PAGE_NO);
        if (BPCIPHIS.OUTPUT.REC_TITLE.RECORD_NUM > 0) {
            CEP.TRC(SCCGWA, "NEED_TO_BROWSE");
            WS_NEED_BROW_FLG = 'Y';
        }
        BPCIPHIS.FORWORD.FMT_CODE = K_OUTPUT_FMT;
        if (BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT == BPCIPHIS.OUTPUT.REC_TITLE.NOW_PAGE_NO) {
            BPCIPHIS.OUTPUT.REC_TITLE.LAST_PAGE_FLG = 'Y';
        } else {
            BPCIPHIS.OUTPUT.REC_TITLE.LAST_PAGE_FLG = 'N';
        }
        WS_FIRST_FETCH += 1;
        R010_GET_AC_NAME_CN();
        if (pgmRtn) return;
    }
    public void R010_CALC_IPHIS_CN() throws IOException,SQLException,Exception {
        WS_RECORD_NUM = BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT % BPCIPHIS.INPUT.PAGE_RECNO;
        BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT = (short) ((BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT - WS_RECORD_NUM) / BPCIPHIS.INPUT.PAGE_RECNO);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT);
        CEP.TRC(SCCGWA, BPCIPHIS.INPUT.PAGE_RECNO);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT);
        if (WS_RECORD_NUM > 0) {
            BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT = (short) (BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT + 1);
        }
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT);
        if (BPCIPHIS.INPUT.PAGE_NO < BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT 
            || (BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT > 0 
            && WS_RECORD_NUM == 0)) {
            WS_RECORD_NUM = BPCIPHIS.INPUT.PAGE_RECNO;
        }
        BPCIPHIS.OUTPUT.REC_TITLE.RECORD_NUM = (short) WS_RECORD_NUM;
        BPCUIHIS.DATA.REC_NUM = (short) WS_RECORD_NUM;
    }
    public void R010_GET_AC_NAME_CN() throws IOException,SQLException,Exception {
        if (BPCUIHIS.DATA.AC.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU2);
            CICACCU2.DATA.AGR_NO = BPCUIHIS.DATA.AC;
            S000_CALL_CIZACCU_CN();
            if (pgmRtn) return;
            if (CICACCU2.DATA.AC_CNM.trim().length() > 0) {
                WS_OUTPUT_TIT_CN.WS_TX_AC_CHNM = CICACCU2.DATA.AC_CNM;
            } else if (CICACCU2.DATA.AC_ENM.trim().length() > 0) {
                WS_OUTPUT_TIT_CN.WS_TX_AC_CHNM = CICACCU2.DATA.AC_ENM;
            } else if (CICACCU2.DATA.CI_CNM.trim().length() > 0) {
                WS_OUTPUT_TIT_CN.WS_TX_AC_CHNM = CICACCU2.DATA.CI_CNM;
            } else if (CICACCU2.DATA.CI_ENM.trim().length() > 0) {
                WS_OUTPUT_TIT_CN.WS_TX_AC_CHNM = CICACCU2.DATA.CI_ENM;
            } else {
                WS_OUTPUT_TIT_CN.WS_TX_AC_CHNM = " ";
            }
            WS_OUTPUT_TIT_CN.WS_TX_OPN_BR_CN = CICACCU2.DATA.OPN_BR;
            WS_AC_TABLE[1-1].WS_AC_NO = CICACCU2.DATA.AGR_NO;
            WS_AC_TABLE[1-1].WS_AC_TYPE = CICACCU2.DATA.CNTRCT_TYP;
            if (WS_OUTPUT_TIT_CN.WS_TX_OPN_BR_CN != 0) {
                CEP.TRC(SCCGWA, WS_OUTPUT_TIT_CN.WS_TX_OPN_BR_CN);
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = WS_OUTPUT_TIT_CN.WS_TX_OPN_BR_CN;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                WS_OUTPUT_TIT_CN.WS_TX_OPN_BR_CHN_CN = BPCPQORG.CHN_NM;
            }
        }
        BPCIPHIS.OUTPUT.REC_TITLE.TIT_FILLER = " ";
        BPCIPHIS.OUTPUT.REC_TITLE.TIT_FILLER = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_TIT_CN);
        CEP.TRC(SCCGWA, WS_FIRST_FETCH);
        CEP.TRC(SCCGWA, WS_PAGE_STRNO);
        CEP.TRC(SCCGWA, WS_PAGE_ENDNO);
        CEP.TRC(SCCGWA, WS_OUTPUT_TIT_CN.WS_TOD_REC_NUM);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.LAST_PAGE_FLG);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.NOW_PAGE_NO);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.RECORD_NUM);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.TIT_FILLER);
    }
    public void B011_GET_ACTY_CN() throws IOException,SQLException,Exception {
        WS_LOCAL_ACTY.WS_ACTY_FND_FLG = 'N';
        CEP.TRC(SCCGWA, WS_LOCAL_ACTY.WS_CHECK_AC);
        CEP.TRC(SCCGWA, WS_LOCAL_ACTY.WS_CHECK_CCY);
        CEP.TRC(SCCGWA, WS_LOCAL_ACTY.WS_ACTY_NUM);
        for (WS_LOCAL_ACTY.WS_ACTY_CNT = 1; WS_LOCAL_ACTY.WS_ACTY_CNT <= WS_LOCAL_ACTY.WS_ACTY_NUM 
            && WS_LOCAL_ACTY.WS_ACTY_FND_FLG != 'Y'; WS_LOCAL_ACTY.WS_ACTY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_LOCAL_ACTY.WS_ACTY_CNT);
            CEP.TRC(SCCGWA, WS_LOCAL_ACTY.WS_ACTY_TABLE[WS_LOCAL_ACTY.WS_ACTY_CNT-1].WS_STD_AC);
            if (WS_LOCAL_ACTY.WS_ACTY_TABLE[WS_LOCAL_ACTY.WS_ACTY_CNT-1].WS_STD_AC.equalsIgnoreCase(WS_LOCAL_ACTY.WS_CHECK_AC)) {
                CEP.TRC(SCCGWA, "FND ACTY!!");
                WS_LOCAL_ACTY.WS_ACTY_FND_FLG = 'Y';
            }
        }
        if (WS_LOCAL_ACTY.WS_ACTY_FND_FLG == 'N') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = WS_LOCAL_ACTY.WS_CHECK_AC;
            CICQACAC.DATA.CCY_ACAC = WS_LOCAL_ACTY.WS_CHECK_CCY;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            WS_LOCAL_ACTY.WS_ACTY_NUM += 1;
            WS_LOCAL_ACTY.WS_ACTY_TABLE[WS_LOCAL_ACTY.WS_ACTY_NUM-1].WS_STD_AC = CICQACAC.DATA.AGR_NO;
            WS_LOCAL_ACTY.WS_ACTY_TABLE[WS_LOCAL_ACTY.WS_ACTY_NUM-1].WS_N_CARD_NO = CICQACAC.DATA.AGR_NO;
            WS_LOCAL_ACTY.WS_ACTY_TABLE[WS_LOCAL_ACTY.WS_ACTY_NUM-1].WS_AC_TP = CICQACAC.O_DATA.O_ACR_DATA.O_AC_REL.charAt(0);
            CEP.TRC(SCCGWA, WS_LOCAL_ACTY.WS_ACTY_TABLE[WS_LOCAL_ACTY.WS_ACTY_NUM-1].WS_AC_TP);
            WS_LOCAL_ACTY.WS_ACTY_TABLE[WS_LOCAL_ACTY.WS_ACTY_NUM-1].WS_STD_APP = CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR;
            CEP.TRC(SCCGWA, WS_LOCAL_ACTY.WS_ACTY_TABLE[WS_LOCAL_ACTY.WS_ACTY_NUM-1].WS_STD_APP);
            WS_LOCAL_ACTY.WS_ACTY_CNT = WS_LOCAL_ACTY.WS_ACTY_NUM;
            CEP.TRC(SCCGWA, WS_LOCAL_ACTY.WS_ACTY_CNT);
        } else {
            WS_LOCAL_ACTY.WS_ACTY_CNT = WS_LOCAL_ACTY.WS_ACTY_CNT - 1;
        }
        CEP.TRC(SCCGWA, WS_LOCAL_ACTY.WS_ACTY_NUM);
        CEP.TRC(SCCGWA, WS_LOCAL_ACTY.WS_ACTY_CNT);
    }
    public void R010_GET_AC_ORGM_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TS_QUEUE_CN.WS_REL_AC_CN);
        if (WS_TS_QUEUE_CN.WS_REL_AC_CN.trim().length() > 0) {
            IBS.init(SCCGWA, CICACCU2);
            CICACCU2.DATA.AGR_NO = WS_TS_QUEUE_CN.WS_REL_AC_CN;
            S000_CALL_CIZACCU_CN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFHIST.RLT_BANK);
            if (CICACCU2.DATA.OPN_BR != 0 
                && CICACCU2.RC.RC_CODE == 0) {
                if (CICACCU2.DATA.AC_CNM.trim().length() > 0) {
                    WS_TS_QUEUE_CN.WS_REL_CI_NAME_CN = CICACCU2.DATA.AC_CNM;
                } else if (CICACCU2.DATA.AC_ENM.trim().length() > 0) {
                    WS_TS_QUEUE_CN.WS_REL_CI_NAME_CN = CICACCU2.DATA.AC_ENM;
                } else if (CICACCU2.DATA.CI_CNM.trim().length() > 0) {
                    WS_TS_QUEUE_CN.WS_REL_CI_NAME_CN = CICACCU2.DATA.CI_CNM;
                } else if (CICACCU2.DATA.CI_ENM.trim().length() > 0) {
                    WS_TS_QUEUE_CN.WS_REL_CI_NAME_CN = CICACCU2.DATA.CI_ENM;
                } else {
                    WS_TS_QUEUE_CN.WS_REL_CI_NAME_CN = " ";
                }
                CEP.TRC(SCCGWA, CICACCU2.DATA.AC_CNM);
                WS_TS_QUEUE_CN.WS_RLT_BANK_CN = "" + CICACCU2.DATA.OPN_BR;
                JIBS_tmp_int = WS_TS_QUEUE_CN.WS_RLT_BANK_CN.length();
                for (int i=0;i<6-JIBS_tmp_int;i++) WS_TS_QUEUE_CN.WS_RLT_BANK_CN = "0" + WS_TS_QUEUE_CN.WS_RLT_BANK_CN;
                CEP.TRC(SCCGWA, WS_TS_QUEUE_CN.WS_RLT_BANK_CN);
            } else {
            }
        }
        CEP.TRC(SCCGWA, WS_TS_QUEUE_CN.WS_REL_CI_NAME_CN);
        CEP.TRC(SCCGWA, WS_TS_QUEUE_CN.WS_RLT_BANK_CN);
    }
    public void R011_GET_VSSUB_AC_ORGM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUINVS);
        DDCUINVS.I_FUNC = '3';
        DDCUINVS.VS_AC = WS_SUB_AC_DATA.WS_SUB_AC_CN;
        DDCUINVS.CCY = WS_SUB_AC_DATA.WS_SUB_CCY;
        DDCUINVS.CCY_TYP = WS_SUB_AC_DATA.WS_SUB_CCY_TYPE;
        S000_CALL_DDZUINVS_CN();
        if (pgmRtn) return;
        WS_SUB_AC_DATA.WS_SUB_AC_NM = DDCUINVS.NAM;
        WS_SUB_AC_DATA.WS_SUB_AC_BR = DDCUINVS.BR;
    }
    public void R000_WRITE_PAGE_TITLE_CN() throws IOException,SQLException,Exception {
        R000_TITLE_PREPARE_CN();
        if (pgmRtn) return;
        if (BPCIPHIS.INPUT.PAGE_NO <= 1 
            || BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT == BPCIPHIS.INPUT.PAGE_NO) {
            R000_UPDATE_COUNT_CN();
            if (pgmRtn) return;
        }
        if (BPCUIHIS.DATA.STR_AC_DT < SCCGWA.COMM_AREA.AC_DATE 
            && BPCUIHIS.DATA.END_AC_DT >= SCCGWA.COMM_AREA.AC_DATE 
            && WS_INQ_FHIST == 'Y') {
            B060_CALC_BOTH_TB_CN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCIPHIS.INPUT.PAGE_RECNO);
        CEP.TRC(SCCGWA, BPCIPHIS.INPUT.PAGE_NO);
        BPCIPHIS.OUTPUT.REC_TITLE.NOW_PAGE_NO = (short) BPCIPHIS.INPUT.PAGE_NO;
        if (BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT == BPCIPHIS.OUTPUT.REC_TITLE.NOW_PAGE_NO) {
            BPCIPHIS.OUTPUT.REC_TITLE.LAST_PAGE_FLG = 'Y';
        } else {
            BPCIPHIS.OUTPUT.REC_TITLE.LAST_PAGE_FLG = 'N';
        }
        WS_FIRST_FETCH += 1;
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        if (WS_RECORD_NUM > 0) {
            CEP.TRC(SCCGWA, "NEED_TO_BROWSE");
            WS_NEED_BROW_FLG = 'Y';
        }
        R010_GET_AC_NAME_CN();
        if (pgmRtn) return;
    }
    public void R000_TITLE_PREPARE_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT);
        CEP.TRC(SCCGWA, WS_OUTPUT_TIT_CN.WS_TOD_REC_NUM);
        if (BPCIPHIS.INPUT.PAGE_NO == 0) {
            CEP.TRC(SCCGWA, "FIRST_CHECK_RECORD");
            WS_PAGE_ENDNO = BPCIPHIS.INPUT.PAGE_RECNO;
            WS_PAGE_STRNO = 0;
        } else {
            CEP.TRC(SCCGWA, "NOT_FIRST_CHECK_RECORD");
            WS_PAGE_ENDNO = BPCIPHIS.INPUT.PAGE_NO * BPCIPHIS.INPUT.PAGE_RECNO;
            WS_PAGE_STRNO = WS_PAGE_ENDNO - BPCIPHIS.INPUT.PAGE_RECNO;
        }
        CEP.TRC(SCCGWA, WS_PAGE_ENDNO);
        CEP.TRC(SCCGWA, WS_PAGE_STRNO);
        WS_FIRST_FETCH = WS_PAGE_STRNO;
        if (BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT > 0 
            && BPCIPHIS.INPUT.PAGE_RECNO > 0) {
            R010_CALC_ROW_CN();
            if (pgmRtn) return;
        }
        BPCIPHIS.FORWORD.FMT_CODE = K_OUTPUT_FMT;
    }
    public void R000_UPDATE_COUNT_CN() throws IOException,SQLException,Exception {
        BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT = 0;
        if (WS_INQ_FHIST == 'Y') {
            R000_BROW_COUNT_BPTFHIST();
            if (pgmRtn) return;
            BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT = BPCIFHIS.OUTPUT.COUNT_NO.COUNT_ALL;
            WS_OUTPUT_TIT_CN.WS_TOD_REC_NUM = BPCIFHIS.OUTPUT.COUNT_NO.COUNT_TOD;
            WS_HIS_REC_NUM = BPCIFHIS.OUTPUT.COUNT_NO.COUNT_HIS;
        } else {
            R000_BROW_COUNT_BPTNHIST();
            if (pgmRtn) return;
        }
        if (BPCIPHIS.INPUT.PAGE_NO == 0 
            && BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT > 0) {
            BPCIPHIS.INPUT.PAGE_NO = 1;
        }
        if (BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT > 0) {
            if (BPCIPHIS.INPUT.PAGE_RECNO > 0) {
                R010_CALC_ROW_CN();
                if (pgmRtn) return;
            }
        } else {
            BPCIPHIS.INPUT.PAGE_RECNO = 0;
        }
        CEP.TRC(SCCGWA, BPCIPHIS.INPUT.PAGE_RECNO);
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
    }
    public void R010_CALC_ROW_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIPHIS.INPUT.PAGE_RECNO);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT);
        WS_RECORD_NUM = BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT % BPCIPHIS.INPUT.PAGE_RECNO;
        BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT = (short) ((BPCIPHIS.OUTPUT.REC_TITLE.RECORD_COUNT - WS_RECORD_NUM) / BPCIPHIS.INPUT.PAGE_RECNO);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT);
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        if (WS_RECORD_NUM > 0) {
            BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT = (short) (BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT + 1);
        }
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        CEP.TRC(SCCGWA, BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT);
        if (BPCIPHIS.INPUT.PAGE_NO < BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT 
            || (BPCIPHIS.OUTPUT.REC_TITLE.PAGE_TOT > 0 
            && WS_RECORD_NUM == 0)) {
            WS_RECORD_NUM = BPCIPHIS.INPUT.PAGE_RECNO;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUIHIS);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.REC_NUM);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.REC_FLG);
        WS_INQ_FHIST = 'N';
        WS_INQ_NHIST = 'N';
        if (BPCUIHIS.DATA.REC_FLG == 'Y') {
            B010_CHECK_RECORD();
            if (pgmRtn) return;
            WS_INQ_FHIST = 'Y';
        }
        if (BPCUIHIS.DATA.REC_FLG == 'N') {
            B010_CHECK_RECORD();
            if (pgmRtn) return;
            WS_INQ_NHIST = 'Y';
        }
        if (BPCUIHIS.DATA.REC_FLG != 'Y' 
            && BPCUIHIS.DATA.REC_FLG != 'N') {
            B010_CHECK_RECORD();
            if (pgmRtn) return;
            WS_INQ_FHIST = 'Y';
            WS_INQ_NHIST = 'Y';
        }
    }
    public void B010_CHECK_RECORD() throws IOException,SQLException,Exception {
        WS_COND_CNT = 0;
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.JRNNO);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.FINANCE_FLG);
        CEP.TRC(SCCGWA, WS_INQ_FHIST);
        CEP.TRC(SCCGWA, WS_INQ_NHIST);
        if (BPCUIHIS.DATA.JRNNO != 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.CI_NO);
        if (BPCUIHIS.DATA.CI_NO.trim().length() > 0) {
            WS_COND_CNT += 1;
            WS_DDAC_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.AC);
        if (BPCUIHIS.DATA.AC.trim().length() > 0) {
            WS_COND_CNT += 1;
            WS_AC_COND = 'Y';
        } else {
            WS_AC_COND = 'N';
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.REF_NO);
        if (BPCUIHIS.DATA.REF_NO.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.TX_TOOL);
        if (BPCUIHIS.DATA.TX_TOOL.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.TX_CD);
        if (BPCUIHIS.DATA.TX_CD.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.TX_TYPE_CD);
        if (BPCUIHIS.DATA.TX_TYPE_CD.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.TLR);
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.DC_FLG);
        if (BPCUIHIS.DATA.DC_FLG != ' ') {
            if (BPCUIHIS.DATA.AC.trim().length() == 0) {
                WS_COND_CNT += 1;
            }
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.MAKER_ID);
        if (BPCUIHIS.DATA.MAKER_ID.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, BPCUIHIS.DATA.CHECKER_ID);
        if (BPCUIHIS.DATA.CHECKER_ID.trim().length() > 0) {
            WS_COND_CNT += 1;
        }
        CEP.TRC(SCCGWA, WS_COND_CNT);
        if (WS_COND_CNT > 1) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MORE_THAN_ONE_COND, BPCUIHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_HIST() throws IOException,SQLException,Exception {
        R000_WRITE_PAGE_TITLE();
        if (pgmRtn) return;
        if (WS_INQ_FHIST == 'Y') {
            R000_STARTBR_BPTFHIST();
            if (pgmRtn) return;
            R000_READNEXT_BPTFHIST();
            if (pgmRtn) return;
        } else {
            WS_FHIST_FLG = 'N';
        }
        if (WS_INQ_NHIST == 'Y') {
            R000_STARTBR_BPTNHIST();
            if (pgmRtn) return;
            R000_READNEXT_BPTNHIST();
            if (pgmRtn) return;
        } else {
            WS_NHIST_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_NHIST_FLG);
        CEP.TRC(SCCGWA, WS_FHIST_FLG);
        WS_FHIS_CNT = 1;
        while ((WS_FHIST_FLG != 'N' 
            || WS_NHIST_FLG != 'N') 
            && (WS_FHIS_CNT <= K_FHIS_MAX) 
            && SCCMPAG.FUNC != 'E') {
            CEP.TRC(SCCGWA, WS_FHIS_CNT);
            if (WS_FHIST_FLG == 'N' 
                && WS_NHIST_FLG == 'Y') {
                R000_OUTPUT_NHIST_REC();
                if (pgmRtn) return;
                R000_READNEXT_BPTNHIST();
                if (pgmRtn) return;
            }
            if (WS_FHIST_FLG == 'Y' 
                && WS_NHIST_FLG == 'N') {
                if (BPCUIHIS.DATA.CCY.trim().length() == 0 
                    || BPCUIHIS.DATA.CCY.equalsIgnoreCase(BPRFHIST.TX_CCY)) {
                    CEP.TRC(SCCGWA, "AAAA");
                    R000_OUTPUT_FHIST_REC();
                    if (pgmRtn) return;
                }
                R000_READNEXT_BPTFHIST();
                if (pgmRtn) return;
            }
            if (WS_FHIST_FLG == 'Y' 
                && WS_NHIST_FLG == 'Y') {
                if ((BPRFHIST.KEY.AC_DT < BPRNHIST.KEY.AC_DT) 
                    || (BPRFHIST.KEY.AC_DT == BPRNHIST.KEY.AC_DT 
                    && BPRFHIST.KEY.JRNNO < BPRNHIST.KEY.JRNNO)) {
                    if (BPCUIHIS.DATA.CCY.trim().length() == 0 
                        || BPCUIHIS.DATA.CCY.equalsIgnoreCase(BPRFHIST.TX_CCY)) {
                        R000_OUTPUT_FHIST_REC();
                        if (pgmRtn) return;
                    }
                    R000_READNEXT_BPTFHIST();
                    if (pgmRtn) return;
                } else {
                    R000_OUTPUT_NHIST_REC();
                    if (pgmRtn) return;
                    R000_READNEXT_BPTNHIST();
                    if (pgmRtn) return;
                }
            }
            R000_CHECK_OUTPUT_CNT();
            if (pgmRtn) return;
        }
        if (WS_INQ_FHIST == 'Y') {
            R000_ENDBR_BPTFHIST();
            if (pgmRtn) return;
        }
        if (WS_INQ_NHIST == 'Y') {
            R000_ENDBR_BPTNHIST();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_OUTPUT_CNT() throws IOException,SQLException,Exception {
        WS_REC_CNT += 1;
        if (BPCUIHIS.DATA.REC_NUM != 0) {
            if (WS_REC_CNT > K_MAX_OUTPUT_CNT 
                || WS_REC_CNT > BPCUIHIS.DATA.REC_NUM) {
                WS_FHIST_FLG = 'N';
                WS_NHIST_FLG = 'N';
            }
        } else {
            if (WS_REC_CNT >= K_MAX_OUTPUT_CNT) {
                CEP.ERR(SCCGWA, SCCCTLM_MSG.SC_ERR_ROW_LIMIT);
                WS_FHIST_FLG = 'N';
                WS_NHIST_FLG = 'N';
            }
        }
    }
    public void R000_OUTPUT_NHIST_REC_CN() throws IOException,SQLException,Exception {
        R000_TRANS_NHIST_REC_CN();
        if (pgmRtn) return;
        R000_WRITE_PAGE_RECORD_CN();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_FHIST_REC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, WS_TS_QUEUE_CN.WS_TS_AC_CN);
        if (!WS_TS_QUEUE_CN.WS_TS_AC_CN.equalsIgnoreCase("0")) {
            R000_GET_AC_INFO_CN();
            if (pgmRtn) return;
        }
        if (BPRFHIST.SUMUP_FLG != '5') {
            R000_GET_AC_BAL_CN();
            if (pgmRtn) return;
        }
        R000_TRANS_FHIST_REC_CN();
        if (pgmRtn) return;
        R000_WRITE_PAGE_RECORD_CN();
        if (pgmRtn) return;
    }
    public void R000_GET_AC_INFO_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TS_QUEUE_CN.WS_TS_AC_CN);
        WS_LOOP_FLG = 'N';
        for (WS_CNT_AC = 1; WS_LOOP_FLG != 'Y'; WS_CNT_AC += 1) {
            CEP.TRC(SCCGWA, "CHECK_AC_NO");
            CEP.TRC(SCCGWA, WS_CNT_AC);
            CEP.TRC(SCCGWA, WS_AC_TABLE[WS_CNT_AC-1].WS_AC_NO);
            if (WS_TS_QUEUE_CN.WS_TS_AC_CN.equalsIgnoreCase(WS_AC_TABLE[WS_CNT_AC-1].WS_AC_NO)) {
                CEP.TRC(SCCGWA, "FOUND_AC_NO_ON_LOCAL");
                WS_LOOP_FLG = 'Y';
            } else {
                if (WS_AC_TABLE[WS_CNT_AC-1].WS_AC_NO.trim().length() == 0) {
                    IBS.init(SCCGWA, CICACCU2);
                    CICACCU2.DATA.AGR_NO = WS_TS_QUEUE_CN.WS_TS_AC_CN;
                    S000_CALL_CIZACCU_CN();
                    if (pgmRtn) return;
                    WS_AC_TABLE[WS_CNT_AC-1].WS_AC_NO = CICACCU2.DATA.AGR_NO;
                    WS_AC_TABLE[WS_CNT_AC-1].WS_AC_TYPE = CICACCU2.DATA.CNTRCT_TYP;
                    WS_LOOP_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, WS_AC_TABLE[WS_CNT_AC-1].WS_AC_TYPE);
        if (WS_AC_TABLE[WS_CNT_AC-1].WS_AC_TYPE.equalsIgnoreCase("DD")) {
            WS_DDAC_FLG = 'Y';
        }
    }
    public void R000_OUTPUT_NHIST_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TS_QUEUE);
        WS_TS_QUEUE.WS_TS_TX_DT = BPRNHIST.TX_DT;
        WS_TS_QUEUE.WS_TS_AC_DT = BPRNHIST.KEY.AC_DT;
        WS_TS_QUEUE.WS_TS_JRNNO = BPRNHIST.KEY.JRNNO;
        WS_TS_QUEUE.WS_TS_JRN_SEQ = BPRNHIST.KEY.JRN_SEQ;
        WS_TS_QUEUE.WS_TS_AC = BPRNHIST.AC;
        WS_TS_QUEUE.WS_TS_REF_NO = BPRNHIST.REF_NO;
        WS_TS_QUEUE.WS_TS_TX_TOOL = BPRNHIST.TX_TOOL;
        WS_TS_QUEUE.WS_TS_APP_MMO = BPRNHIST.APP_MMO;
        WS_TS_QUEUE.WS_TS_TX_CD = BPRNHIST.TX_CD;
        WS_TS_QUEUE.WS_TS_TX_BR = BPRNHIST.TX_BR;
        WS_TS_QUEUE.WS_TS_TX_DP = BPRNHIST.TX_DP;
        WS_TS_QUEUE.WS_TS_TX_TLR = BPRNHIST.TX_TLR;
        WS_TS_QUEUE.WS_TS_SUP1 = BPRNHIST.SUP1;
        WS_TS_QUEUE.WS_TS_SUP2 = BPRNHIST.SUP2;
        WS_TS_QUEUE.WS_TS_TX_CHNL = BPRNHIST.TX_CHNL;
        WS_TS_QUEUE.WS_TS_FIN_FLG = 'N';
        WS_TS_QUEUE.WS_TS_VCHNO = " ";
        WS_TS_QUEUE.WS_TS_CI_NO = BPRNHIST.CI_NO;
        WS_TS_QUEUE.WS_TS_DRCR_FLG = ' ';
        WS_TS_QUEUE.WS_TS_TX_CCY = " ";
        WS_TS_QUEUE.WS_TS_TX_AMT = 0;
        WS_TS_QUEUE.WS_TS_CUR_BAL = 0;
        WS_TS_QUEUE.WS_TS_TX_VAL_DT = 0;
        WS_TS_QUEUE.WS_TS_PROD_CD = " ";
        WS_TS_QUEUE.WS_TS_STS = BPRNHIST.TX_STS;
        WS_TS_QUEUE.WS_TS_TX_TM = BPRNHIST.TX_TM;
        WS_TS_QUEUE.WS_TS_REMARK = BPRNHIST.TX_RMK;
        WS_TS_QUEUE.WS_TS_TX_TYPE = BPRNHIST.TX_TYP;
        WS_TS_QUEUE.WS_TX_TYP_CD = BPRNHIST.TX_TYP_CD;
        WS_TS_QUEUE.WS_MAKER_TLR = BPRNHIST.MAKER_TLR;
        WS_TS_QUEUE.WS_STAFF_FLG = 'N';
        R000_WRITE_PAGE_RECORD();
        if (pgmRtn) return;
    }
    public void R000_TRANS_NHIST_REC_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_TS_QUEUE_CN);
        WS_TS_QUEUE_CN.WS_TS_TX_DT_CN = BPRNHIST.TX_DT;
        WS_TS_QUEUE_CN.WS_TS_AC_DT_CN = BPRNHIST.KEY.AC_DT;
        WS_TS_QUEUE_CN.WS_TS_JRNNO_CN = BPRNHIST.KEY.JRNNO;
        WS_TS_QUEUE_CN.WS_TS_JRN_SEQ_CN = BPRNHIST.KEY.JRN_SEQ;
        WS_TS_QUEUE_CN.WS_TS_AC_CN = BPRNHIST.AC;
        WS_TS_QUEUE_CN.WS_TS_REF_NO_CN = BPRNHIST.REF_NO;
        WS_TS_QUEUE_CN.WS_TS_TX_TOOL_CN = BPRNHIST.TX_TOOL;
        WS_TS_QUEUE_CN.WS_TS_APP_MMO_CN = BPRNHIST.APP_MMO;
        WS_TS_QUEUE_CN.WS_TS_TX_CD_CN = BPRNHIST.TX_CD;
        WS_TS_QUEUE_CN.WS_TS_TX_BR_CN = BPRNHIST.TX_BR;
        WS_TS_QUEUE_CN.WS_TS_TX_DP_CN = BPRNHIST.TX_DP;
        WS_TS_QUEUE_CN.WS_TS_TX_TLR_CN = BPRNHIST.TX_TLR;
        WS_TS_QUEUE_CN.WS_TS_SUP1_CN = BPRNHIST.SUP1;
        WS_TS_QUEUE_CN.WS_TS_SUP2_CN = BPRNHIST.SUP2;
        WS_TS_QUEUE_CN.WS_TS_TX_CHNL_CN = BPRNHIST.TX_CHNL;
        WS_TS_QUEUE_CN.WS_TS_FIN_FLG_CN = 'N';
        WS_TS_QUEUE_CN.WS_TS_VCHNO_CN = " ";
        WS_TS_QUEUE_CN.WS_TS_CI_NO_CN = BPRNHIST.CI_NO;
        WS_TS_QUEUE_CN.WS_TS_DRCR_FLG_CN = ' ';
        WS_TS_QUEUE_CN.WS_TS_TX_CCY_CN = " ";
        WS_TS_QUEUE_CN.WS_TS_TX_AMT_CN = 0;
        WS_TS_QUEUE_CN.WS_TS_CUR_BAL_CN = 0;
        WS_TS_QUEUE_CN.WS_TS_TX_VAL_DT_CN = 0;
        WS_TS_QUEUE_CN.WS_TS_PROD_CD_CN = " ";
        WS_TS_QUEUE_CN.WS_TS_STS_CN = BPRNHIST.TX_STS;
        WS_TS_QUEUE_CN.WS_TS_TX_TM_CN = BPRNHIST.TX_TM;
        WS_TS_QUEUE_CN.WS_TS_REMARK_CN = BPRNHIST.TX_RMK;
        WS_TS_QUEUE_CN.WS_TS_TX_TYPE_CN = BPRNHIST.TX_TYP;
        WS_TS_QUEUE_CN.WS_TX_TYP_CD_CN = BPRNHIST.TX_TYP_CD;
        WS_TS_QUEUE_CN.WS_MAKER_TLR_CN = BPRNHIST.MAKER_TLR;
        WS_TS_QUEUE_CN.WS_STAFF_FLG_CN = 'N';
    }
    public void R000_OUTPUT_FHIST_REC() throws IOException,SQLException,Exception {
        R000_GET_AC_BAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BBBB");
        if (WS_AC_COND == 'N' 
            || (WS_AC_COND == 'Y' 
            && (BPCUIHIS.DATA.STR_TX_DT == 0 
            || BPCUIHIS.DATA.STR_TX_DT <= BPRFHIST.TX_DT) 
            && (BPCUIHIS.DATA.END_TX_DT == 0 
            || BPCUIHIS.DATA.END_TX_DT >= BPRFHIST.TX_DT) 
            && (BPCUIHIS.DATA.STR_TX_TM == 0 
            || BPCUIHIS.DATA.STR_TX_TM <= BPRFHIST.TX_TM) 
            && (BPCUIHIS.DATA.END_TX_TM == 0 
            || BPCUIHIS.DATA.END_TX_TM >= BPRFHIST.TX_TM) 
            && (BPCUIHIS.DATA.STR_AMT == 0 
            || BPCUIHIS.DATA.STR_AMT <= BPRFHIST.TX_AMT) 
            && (BPCUIHIS.DATA.END_AMT == 0 
            || BPCUIHIS.DATA.END_AMT >= BPRFHIST.TX_AMT))) {
            IBS.init(SCCGWA, WS_TS_QUEUE);
            WS_TS_QUEUE.WS_TS_TX_DT = BPRFHIST.TX_DT;
            WS_TS_QUEUE.WS_TS_AC_DT = BPRFHIST.KEY.AC_DT;
            WS_TS_QUEUE.WS_TS_JRNNO = BPRFHIST.KEY.JRNNO;
            WS_TS_QUEUE.WS_TS_JRN_SEQ = BPRFHIST.KEY.JRN_SEQ;
            WS_TS_QUEUE.WS_TS_AC = BPRFHIST.KEY.AC;
            WS_TS_QUEUE.WS_TS_REF_NO = BPRFHIST.REF_NO;
            WS_TS_QUEUE.WS_TS_TX_TOOL = BPRFHIST.TX_TOOL;
            WS_TS_QUEUE.WS_TS_APP_MMO = BPRFHIST.APP_MMO;
            WS_TS_QUEUE.WS_TS_TX_CD = BPRFHIST.TX_CD;
            WS_TS_QUEUE.WS_TS_TX_BR = BPRFHIST.TX_BR;
            WS_TS_QUEUE.WS_TS_TX_DP = BPRFHIST.TX_DP;
            WS_TS_QUEUE.WS_TS_TX_TLR = BPRFHIST.TX_TLR;
            WS_TS_QUEUE.WS_TS_SUP1 = BPRFHIST.SUP1;
            WS_TS_QUEUE.WS_TS_SUP2 = BPRFHIST.SUP2;
            WS_TS_QUEUE.WS_TS_TX_CHNL = BPRFHIST.TX_CHNL;
            WS_TS_QUEUE.WS_TS_FIN_FLG = 'F';
            WS_TS_QUEUE.WS_TS_VCHNO = BPRFHIST.VCHNO;
            WS_TS_QUEUE.WS_TS_CI_NO = BPRFHIST.CI_NO;
            WS_TS_QUEUE.WS_TS_DRCR_FLG = BPRFHIST.DRCRFLG;
            WS_TS_QUEUE.WS_TS_TX_CCY = BPRFHIST.TX_CCY;
            WS_TS_QUEUE.WS_TS_TX_AMT = BPRFHIST.TX_AMT;
            WS_TS_QUEUE.WS_TS_CUR_BAL = WS_CUR_BAL;
            WS_TS_QUEUE.WS_TS_TX_VAL_DT = BPRFHIST.TX_VAL_DT;
            WS_TS_QUEUE.WS_TS_PROD_CD = BPRFHIST.PROD_CD;
            WS_TS_QUEUE.WS_TS_STS = BPRFHIST.TX_STS;
            WS_TS_QUEUE.WS_TS_TX_TM = BPRFHIST.TX_TM;
            WS_TS_QUEUE.WS_TS_REMARK = BPRFHIST.REMARK;
            WS_TS_QUEUE.WS_MAKER_TLR = BPRFHIST.MAKER;
            WS_TS_QUEUE.WS_NARRATIVE = BPRFHIST.NARRATIVE;
            WS_TS_QUEUE.WS_TX_MMO = BPRFHIST.TX_MMO;
            WS_TS_QUEUE.WS_REL_AC = BPRFHIST.RLT_AC;
            WS_TS_QUEUE.WS_STAFF_FLG = 'N';
