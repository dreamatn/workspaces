package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACAC;
import com.hisun.DC.DCCMSG_ERROR_MSG;
import com.hisun.DC.DCCPACTY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCPRMM;
import com.hisun.SC.SCCSUBS;
import com.hisun.SC.SCRCWAT;
import com.hisun.SC.SCRPRMT;

public class BPOT7075 {
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_OUTPUT_FMT = "BPZ01";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_PC = "BP-P-INQ-PC       ";
    String K_RBASE = "RBASE";
    String K_RTENO = "RTENO";
    String K_PAMC_MMO = "MMO  ";
    int WS_LEN = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_J = 0;
    short WS_D = 0;
    int WS_AC_HASH = 0;
    String WS_H_ACO_AC = " ";
    String WS_OUT_CI_NO = " ";
    short WS_T = 0;
    int WS_MIN_NUM = 0;
    int WS_MAX_NUM = 0;
    short WS_REM = 0;
    int WS_BR = 0;
    String WS_AGR_NO = " ";
    String WS_CCY = " ";
    double WS_CUR_BAL = 0;
    double WS_OUT_CUR_BAL = 0;
    String WS_BASE_TYP = " ";
    String WS_TENOR = " ";
    short WS_PAGE_NO = 0;
    long WS_STR_POS = 0;
    char WS_END_FLG = ' ';
    char WS_BROWS_COND = ' ';
    BPOT7075_WS_FILE_NAME WS_FILE_NAME = new BPOT7075_WS_FILE_NAME();
    char WS_SORT_FLG = ' ';
    char WS_FRZ_FLG = ' ';
    char WS_BPTFHISA_FLG = ' ';
    int WS_STR_DT = 0;
    int WS_END_DT = 0;
    int WS_SET_DT = 0;
    int WS_SET_TM = 0;
    String WS_ACO_AC = " ";
    long WS_JRNNO = 0;
    short WS_JRN_SEQ = 0;
    int WS_PART_NO = 0;
    int WS_TX_BR = 0;
    String WS_TX_TLR = " ";
    int WS_AC_SEQ = 0;
    int WS_POS_DT = 0;
    long WS_POS_JRNNO = 0;
    short WS_POS_JRN_SEQ = 0;
    int WS_I = 0;
    int WS_K = 0;
    double WS_TX_AMT_FROM = 0;
    double WS_TX_AMT_TO = 0;
    char WS_DRCRFLG = ' ';
    String WS_RLT_AC_NAME = " ";
    String WS_CI_NO = " ";
    String WS_TX_MMO = " ";
    short WS_ROW_NUM = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCOTLRQ BPCOTLRQ = new BPCOTLRQ();
    BPRFHIS BPRFHIS = new BPRFHIS();
    DCCPACTY DCCPACTY = new DCCPACTY();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCQFHIS BPCQFHIS = new BPCQFHIS();
    CICQACAC CICQACAC = new CICQACAC();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    BPCF7075 BPCF7075 = new BPCF7075();
    BPCG7075 BPCG7075 = new BPCG7075();
    BPCO7075 BPCO7075 = new BPCO7075();
    BPCUIBAL BPCUIBAL = new BPCUIBAL();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRFHISA BPRFHISA = new BPRFHISA();
    CICCUST CICCUST = new CICCUST();
    SCCPRMM SCCSRMM = new SCCPRMM();
    SCRPRMT SCRSRMT = new SCRPRMT();
    SCCGWA SCCGWA;
    BPB7075_AWA_7075 BPB7075_AWA_7075;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT7075 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB7075_AWA_7075>");
        BPB7075_AWA_7075 = (BPB7075_AWA_7075) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB7075_AWA_7075.STR_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7075_AWA_7075.END_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7075_AWA_7075.END_DT > SCCGWA.COMM_AREA.AC_DATE) {
            BPB7075_AWA_7075.END_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, BPB7075_AWA_7075.STR_DT);
        CEP.TRC(SCCGWA, BPB7075_AWA_7075.END_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPB7075_AWA_7075.STR_DT > BPB7075_AWA_7075.END_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB7075_AWA_7075.STR_DT);
        CEP.TRC(SCCGWA, BPB7075_AWA_7075.END_DT);
        CEP.TRC(SCCGWA, BPB7075_AWA_7075.TX_AMT_F);
        CEP.TRC(SCCGWA, BPB7075_AWA_7075.TX_AMT_T);
        if (BPB7075_AWA_7075.TX_AMT_F != 0 
            && BPB7075_AWA_7075.TX_AMT_T == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRF_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7075_AWA_7075.TX_AMT_F > BPB7075_AWA_7075.TX_AMT_T) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TRS_AMT_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPB7075_AWA_7075.TX_MMO);
        CEP.TRC(SCCGWA, BPB7075_AWA_7075.ROW_NUM);
        if (BPB7075_AWA_7075.ROW_NUM > 1000) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (BPB7075_AWA_7075.ROW_NUM > 0) {
                WS_ROW_NUM = BPB7075_AWA_7075.ROW_NUM;
            } else {
                WS_ROW_NUM = 1000;
            }
        }
        if (BPB7075_AWA_7075.JRNNO == 0 
            && BPB7075_AWA_7075.AC.trim().length() == 0 
            && BPB7075_AWA_7075.TX_TOOL.trim().length() == 0 
            && BPB7075_AWA_7075.TX_TLR.trim().length() == 0 
            && BPB7075_AWA_7075.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INP_ONE_COND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB7075_AWA_7075.JRNNO != 0 
                && BPB7075_AWA_7075.STR_DT != 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_JRNNO");
            WS_BROWS_COND = '1';
        } else if (BPB7075_AWA_7075.AC.trim().length() == 0 
                && BPB7075_AWA_7075.TX_TOOL.trim().length() == 0 
                && BPB7075_AWA_7075.TX_CD.trim().length() == 0 
                && BPB7075_AWA_7075.TX_BR != 0 
                && BPB7075_AWA_7075.TX_TLR.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BROWSE BY BR TLR");
            WS_BROWS_COND = '2';
        } else if (((BPB7075_AWA_7075.AC.trim().length() > 0 
                || BPB7075_AWA_7075.CI_NO.trim().length() > 0) 
                && (BPB7075_AWA_7075.TX_AMT_F != 0 
                || BPB7075_AWA_7075.TX_AMT_T != 0 
                || BPB7075_AWA_7075.RL_AC_NM.trim().length() > 0 
                || BPB7075_AWA_7075.TX_MMO.trim().length() > 0))) {
            CEP.TRC(SCCGWA, "*** BROWSE BY AC/CI ***");
            WS_BROWS_COND = '4';
        } else {
            CEP.TRC(SCCGWA, "CI WAY");
            WS_BROWS_COND = '3';
        }
        CEP.TRC(SCCGWA, WS_BROWS_COND);
        CEP.TRC(SCCGWA, BPB7075_AWA_7075.SORT_FLG);
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB7075_AWA_7075.PAGE_NO);
        WS_MIN_NUM = ( ( BPB7075_AWA_7075.PAGE_NO - 1 ) * WS_ROW_NUM );
        WS_MAX_NUM = BPB7075_AWA_7075.PAGE_NO * WS_ROW_NUM;
        CEP.TRC(SCCGWA, WS_MIN_NUM);
        CEP.TRC(SCCGWA, WS_MAX_NUM);
        WS_FILE_NAME.WS_OUT_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_FILE_NAME.WS_FILE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_str[0] = "" + BPB7075_AWA_7075.STR_POS;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 8).trim().length() == 0) WS_POS_DT = 0;
        else WS_POS_DT = Integer.parseInt(JIBS_tmp_str[0].substring(0, 8));
        JIBS_tmp_str[0] = "" + BPB7075_AWA_7075.STR_POS;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(9 - 1, 9 + 12 - 1).trim().length() == 0) WS_POS_JRNNO = 0;
        else WS_POS_JRNNO = Long.parseLong(JIBS_tmp_str[0].substring(9 - 1, 9 + 12 - 1));
        JIBS_tmp_str[0] = "" + BPB7075_AWA_7075.STR_POS;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<24-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(21 - 1, 21 + 4 - 1).trim().length() == 0) WS_POS_JRN_SEQ = 0;
        else WS_POS_JRN_SEQ = Short.parseShort(JIBS_tmp_str[0].substring(21 - 1, 21 + 4 - 1));
