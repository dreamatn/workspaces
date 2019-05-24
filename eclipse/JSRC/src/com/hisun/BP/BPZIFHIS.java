package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZIFHIS {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm BPTFHIST_BR = new brParm();
    DBParm BPTFHIST_RD;
    boolean pgmRtn = false;
    String CPN_UPD_FHIST = "BP-UPD-FHIST";
    String CPN_BR_FHIS_TOD = "BP-BR-FHIS-TOD";
    String CPN_BR_FHIST_HIS = "BP-BR-FHIST-HIS";
    double WS_MAX_AMT = 9999999999999.99;
    double WS_MIN_AMT = -9999999999999.99;
    int WS_MAX_DT = 99991231;
    int WS_MIN_DT = 10101;
    String WS_ERR_MSG = " ";
    int WS_LENGTH = 0;
    short WS_MAX_JRN_SEQ = 0;
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    int WS_STR_TR_DATE = 0;
    int WS_END_TR_DATE = 0;
    int WS_STR_TR_TM = 0;
    int WS_END_TR_TM = 0;
    double WS_STR_AMT = 0;
    double WS_END_AMT = 0;
    String WS_AC = " ";
    String WS_TX_CCY = " ";
    String WS_REF_NO = " ";
    long WS_JRNNO = 0;
    String WS_TX_TOOL = " ";
    String WS_TX_TELR = " ";
    String WS_TX_CD = " ";
    String WS_CI_NO = " ";
    int WS_TX_BR = 0;
    short WS_TX_DP = 0;
    char WS_TX_DC = ' ';
    String WS_MAKER_ID = " ";
    String WS_CHECKER_ID = " ";
    int WS_COUNT_NO = 0;
    char WS_BRW_FHIS_FLAG = ' ';
    char WS_TABLE_FLG = ' ';
    char WS_HAVE_CALL_FLG = ' ';
    char WS_DATE_COND = ' ';
    char WS_BROWS_COND = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFHIST BPRFHIST = new BPRFHIST();
    SCCCALL SCCCALL = new SCCCALL();
    BPCBFHTD BPCBFHTD = new BPCBFHTD();
    BPCBFHHD BPCBFHHD = new BPCBFHHD();
    SCCGWA SCCGWA;
    BPCIFHIS BPCIFHIS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    BPRFHIST BPRFHISL;
    public void MP(SCCGWA SCCGWA, BPCIFHIS BPCIFHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIFHIS = BPCIFHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZIFHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRFHISL = (BPRFHIST) BPCIFHIS.INPUT.REC_PT;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        IBS.init(SCCGWA, BPRFHIST);
        WS_LENGTH = 690;
        CEP.TRC(SCCGWA, WS_LENGTH);
        if (WS_LENGTH == BPCIFHIS.INPUT.REC_LEN) {
            CEP.TRC(SCCGWA, "WS-LENGTH = IFHIS-REC-LEN");
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFHISL);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRFHISL, BPRFHIST);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCIFHIS.OUTPUT.RC);
        WS_HAVE_CALL_FLG = 'N';
    }
    public void B100_SUB_MAIN_PROC_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_TABLE_FLG);
        if (BPCIFHIS.INPUT.FUNC == '1' 
            || BPCIFHIS.INPUT.FUNC == '8') {
            B001_SET_COND_CN();
            if (pgmRtn) return;
        }
        if (BPCIFHIS.INPUT.FUNC == 'A') {
            BPCIFHIS.INPUT.FUNC = '1';
            CEP.TRC(SCCGWA, "BROWSE-BY-JRN-ALL");
            B003_SET_COND_ALL_CN();
            if (pgmRtn) return;
        }
        if (WS_TABLE_FLG == 'T') {
            CEP.TRC(SCCGWA, "GOTO_TOD_TABLE");
            B011_TRAN_DATA_BPCBFHTD();
            if (pgmRtn) return;
            S000_CALL_BPZBFHTD();
            if (pgmRtn) return;
        }
        if (WS_TABLE_FLG == 'H') {
            CEP.TRC(SCCGWA, "GOTO_HIS_TABLE");
            B013_TRAN_DATA_BPCBFHHD();
            if (pgmRtn) return;
            S000_CALL_BPZBFHHD();
            if (pgmRtn) return;
        }
        if (BPCIFHIS.INPUT.FUNC == '8') {
            BPCIFHIS.OUTPUT.COUNT_NO.COUNT_TOD = BPCBFHTD.OUTPUT.COUNT_NO;
            BPCIFHIS.OUTPUT.COUNT_NO.COUNT_HIS = BPCBFHHD.OUTPUT.COUNT_NO;
            BPCIFHIS.OUTPUT.COUNT_NO.COUNT_ALL = BPCIFHIS.OUTPUT.COUNT_NO.COUNT_TOD + BPCIFHIS.OUTPUT.COUNT_NO.COUNT_HIS;
            CEP.TRC(SCCGWA, BPCIFHIS.OUTPUT.COUNT_NO.COUNT_TOD);
            CEP.TRC(SCCGWA, BPCIFHIS.OUTPUT.COUNT_NO.COUNT_HIS);
            CEP.TRC(SCCGWA, BPCIFHIS.OUTPUT.COUNT_NO.COUNT_ALL);
        }
        CEP.TRC(SCCGWA, WS_HAVE_CALL_FLG);
        if (WS_DATE_COND == 'B' 
            && WS_HAVE_CALL_FLG == 'Y') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            if (BPCIFHIS.INPUT.FUNC == '8' 
                || (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE) 
                && (BPCIFHIS.INPUT.FUNC == '2' 
                || BPCIFHIS.INPUT.FUNC == '9'))) {
                B080_BROWSE_NEXT_TAB_CN();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.REC_LEN);
        WS_LENGTH = 690;
        CEP.TRC(SCCGWA, WS_LENGTH);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFHIST);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRFHIST, BPRFHISL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCIFHIS.INPUT.FUNC != '4' 
            && BPCIFHIS.INPUT.FUNC != '5') {
            B005_CHECK_INPUT();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "TRC_FHIST_AC_DT");
            BPRFHIST.CI_NO = "" + BPRFHIST.KEY.AC_DT;
            JIBS_tmp_int = BPRFHIST.CI_NO.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) BPRFHIST.CI_NO = "0" + BPRFHIST.CI_NO;
            CEP.TRC(SCCGWA, BPRFHIST.CI_NO);
            BPCIFHIS.INPUT.STR_AC_DT = BPRFHIST.KEY.AC_DT;
            BPCIFHIS.INPUT.END_AC_DT = BPRFHIST.KEY.AC_DT;
        }
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.FUNC);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            IBS.init(SCCGWA, BPCBFHTD);
            IBS.init(SCCGWA, BPCBFHHD);
            B070_ACDT_CHECK();
            if (pgmRtn) return;
            B100_SUB_MAIN_PROC_CN();
            if (pgmRtn) return;
            if (BPCIFHIS.INPUT.FUNC == '2') {
                CEP.TRC(SCCGWA, BPRFHIST.KEY.JRNNO);
            }
            CEP.TRC(SCCGWA, BPCIFHIS.OUTPUT.RC);
        } else {
            if (BPCIFHIS.INPUT.FUNC == '1') {
                B010_STARTBR_PROC();
                if (pgmRtn) return;
            } else if (BPCIFHIS.INPUT.FUNC == '2') {
                B020_READNEXT_PROC();
                if (pgmRtn) return;
            } else if (BPCIFHIS.INPUT.FUNC == '3') {
                B030_ENDBR_PROC();
                if (pgmRtn) return;
            } else if (BPCIFHIS.INPUT.FUNC == '4') {
                B040_GROUPMAX_PROC();
                if (pgmRtn) return;
            } else if (BPCIFHIS.INPUT.FUNC == '5') {
                B050_READ_PROC();
                if (pgmRtn) return;
            } else if (BPCIFHIS.INPUT.FUNC == '6') {
                B010_STARTBR_BAL_PROC();
                if (pgmRtn) return;
            } else if (BPCIFHIS.INPUT.FUNC == '7') {
                B010_STARTBR_STATEMENT_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCIFHIS.OUTPUT.RC);
                Z_RET();
                if (pgmRtn) return;
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "BPZIFHIS INVALID FUNC(" + BPCIFHIS.INPUT.FUNC + ")";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            }
            CEP.TRC(SCCGWA, BPCIFHIS.INPUT.REC_LEN);
            WS_LENGTH = 690;
            CEP.TRC(SCCGWA, WS_LENGTH);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRFHIST);
            JIBS_tmp_str[1] = JIBS_tmp_str[0];
            IBS.CLONE(SCCGWA, BPRFHIST, BPRFHISL);
        }
    }
    public void B070_ACDT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.STR_AC_DT);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.END_AC_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCIFHIS.OUTPUT.TABLE_FLG);
        if (BPCIFHIS.OUTPUT.TABLE_FLG == 'T' 
            || BPCIFHIS.INPUT.STR_AC_DT >= SCCGWA.COMM_AREA.AC_DATE) {
            WS_DATE_COND = 'T';
            WS_TABLE_FLG = 'T';
            CEP.TRC(SCCGWA, "WS-TABLE-TOD");
        }
        if (BPCIFHIS.OUTPUT.TABLE_FLG == 'H' 
            || (BPCIFHIS.INPUT.STR_AC_DT < SCCGWA.COMM_AREA.AC_DATE 
            && BPCIFHIS.INPUT.END_AC_DT < SCCGWA.COMM_AREA.AC_DATE)) {
            WS_DATE_COND = 'H';
            WS_TABLE_FLG = 'H';
            CEP.TRC(SCCGWA, "WS-TABLE-HIS");
        }
        if (BPCIFHIS.INPUT.END_AC_DT >= SCCGWA.COMM_AREA.AC_DATE 
            && BPCIFHIS.INPUT.STR_AC_DT < SCCGWA.COMM_AREA.AC_DATE 
            && (BPCIFHIS.OUTPUT.TABLE_FLG != 'H' 
            && BPCIFHIS.OUTPUT.TABLE_FLG != 'T')) {
            CEP.TRC(SCCGWA, "WS-TABLE-BOTH");
            WS_DATE_COND = 'B';
            if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
                WS_TABLE_FLG = 'T';
            } else {
                WS_TABLE_FLG = 'H';
            }
        }
        if (BPCIFHIS.INPUT.STR_AC_DT > BPCIFHIS.INPUT.END_AC_DT) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HIS_DATE_ERR, BPCIFHIS.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B005_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCIFHIS.INPUT.STR_AC_DT == 0) {
            WS_STR_DATE = 10101;
        } else {
            WS_STR_DATE = BPCIFHIS.INPUT.STR_AC_DT;
        }
        if (BPCIFHIS.INPUT.END_AC_DT == 0) {
            WS_END_DATE = 99991231;
        } else {
            WS_END_DATE = BPCIFHIS.INPUT.END_AC_DT;
        }
        if (BPCIFHIS.INPUT.STR_TR_DT == 0) {
            WS_STR_TR_DATE = 10101;
        } else {
            WS_STR_TR_DATE = BPCIFHIS.INPUT.STR_TR_DT;
        }
        if (BPCIFHIS.INPUT.END_TR_DT == 0) {
            WS_END_TR_DATE = 99991231;
        } else {
            WS_END_TR_DATE = BPCIFHIS.INPUT.END_TR_DT;
        }
        if (BPCIFHIS.INPUT.END_AMT == 0) {
            WS_END_AMT = 9999999999999.99;
        } else {
            WS_END_AMT = BPCIFHIS.INPUT.END_AMT;
        }
        if (WS_STR_DATE > WS_END_DATE 
            || WS_STR_TR_DATE > WS_END_TR_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_HIS_DATE_ERR, BPCIFHIS.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCIFHIS.INPUT.STR_AMT == 0) {
            WS_STR_AMT = -9999999999999.99;
        } else {
            WS_STR_AMT = BPCIFHIS.INPUT.STR_AMT;
        }
        if (BPCIFHIS.INPUT.END_TR_TM == 0) {
            WS_END_TR_TM = 235959;
        } else {
            WS_END_TR_TM = BPCIFHIS.INPUT.END_TR_TM;
        }
        WS_STR_TR_TM = BPCIFHIS.INPUT.STR_TR_TM;
        if (WS_STR_TR_TM > WS_END_TR_TM) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STR_END_TIME_ERR, BPCIFHIS.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_AC = BPCIFHIS.INPUT.AC;
        WS_TX_CCY = BPCIFHIS.INPUT.TX_CCY;
        WS_REF_NO = BPCIFHIS.INPUT.REF_NO;
        WS_JRNNO = BPCIFHIS.INPUT.JRNNO;
        WS_TX_TOOL = BPCIFHIS.INPUT.TX_TOOL;
        WS_TX_TELR = BPCIFHIS.INPUT.TX_TLR;
        WS_TX_CD = BPCIFHIS.INPUT.TX_CD;
        WS_CI_NO = BPCIFHIS.INPUT.CI_NO;
        WS_TX_BR = BPCIFHIS.INPUT.TX_BR;
        WS_TX_DP = BPCIFHIS.INPUT.TX_DP;
        WS_TX_DC = BPCIFHIS.INPUT.DC_FLG;
        WS_MAKER_ID = BPCIFHIS.INPUT.MAKER_ID;
        WS_CHECKER_ID = BPCIFHIS.INPUT.CHECKER_ID;
        CEP.TRC(SCCGWA, WS_JRNNO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.AC);
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_CCY);
        CEP.TRC(SCCGWA, WS_TX_CCY);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.REF_NO);
        CEP.TRC(SCCGWA, WS_REF_NO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.JRNNO);
        CEP.TRC(SCCGWA, WS_JRNNO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, WS_TX_TOOL);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_TLR);
        CEP.TRC(SCCGWA, WS_TX_TELR);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_CD);
        CEP.TRC(SCCGWA, WS_TX_CD);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.CI_NO);
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_BR);
        CEP.TRC(SCCGWA, WS_TX_BR);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_DP);
        CEP.TRC(SCCGWA, WS_TX_DP);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.DC_FLG);
        CEP.TRC(SCCGWA, WS_MAKER_ID);
        CEP.TRC(SCCGWA, WS_CHECKER_ID);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.PROD_CD);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.SORT_FLG);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.FETCH_NO);
    }
    public void B011_TRAN_DATA_BPCBFHTD() throws IOException,SQLException,Exception {
        BPCBFHTD.INPUT.FETCH_NO = BPCIFHIS.INPUT.FETCH_NO;
        BPCBFHTD.INPUT.FUNC = BPCIFHIS.INPUT.FUNC;
        BPCBFHTD.INPUT.JRNNO = BPCIFHIS.INPUT.JRNNO;
        BPCBFHTD.INPUT.CI_NO = BPCIFHIS.INPUT.CI_NO;
        BPCBFHTD.INPUT.AC = BPCIFHIS.INPUT.AC;
        BPCBFHTD.INPUT.REF_NO = BPCIFHIS.INPUT.REF_NO;
        BPCBFHTD.INPUT.TX_TOOL = BPCIFHIS.INPUT.TX_TOOL;
        BPCBFHTD.INPUT.TX_CCY = BPCIFHIS.INPUT.TX_CCY;
        BPCBFHTD.INPUT.TX_CD = BPCIFHIS.INPUT.TX_CD;
        BPCBFHTD.INPUT.TX_TLR = BPCIFHIS.INPUT.TX_TLR;
        BPCBFHTD.INPUT.STR_AC_DT = BPCIFHIS.INPUT.STR_AC_DT;
        BPCBFHTD.INPUT.END_AC_DT = BPCIFHIS.INPUT.END_AC_DT;
        BPCBFHTD.INPUT.STR_TR_DT = BPCIFHIS.INPUT.STR_TR_DT;
        BPCBFHTD.INPUT.END_TR_DT = BPCIFHIS.INPUT.END_TR_DT;
        BPCBFHTD.INPUT.STR_TR_TM = BPCIFHIS.INPUT.STR_TR_TM;
        BPCBFHTD.INPUT.END_TR_TM = BPCIFHIS.INPUT.END_TR_TM;
        BPCBFHTD.INPUT.STR_AMT = BPCIFHIS.INPUT.STR_AMT;
        BPCBFHTD.INPUT.END_AMT = BPCIFHIS.INPUT.END_AMT;
        BPCBFHTD.INPUT.TX_BR = BPCIFHIS.INPUT.TX_BR;
        BPCBFHTD.INPUT.TX_DP = BPCIFHIS.INPUT.TX_DP;
        BPCBFHTD.INPUT.DC_FLG = BPCIFHIS.INPUT.DC_FLG;
        BPCBFHTD.INPUT.MAKER_ID = BPCIFHIS.INPUT.MAKER_ID;
        BPCBFHTD.INPUT.CHECKER_ID = BPCIFHIS.INPUT.CHECKER_ID;
        BPCBFHTD.INPUT.SORT_FLG = BPCIFHIS.INPUT.SORT_FLG;
        BPCBFHTD.INPUT.PROD_CD = BPCIFHIS.INPUT.PROD_CD;
        BPCBFHTD.INPUT.BROWS_COND = WS_BROWS_COND;
        BPCBFHTD.INPUT.REC_PT = BPRFHIST;
        BPCBFHTD.INPUT.REC_LEN = 690;
    }
    public void B013_TRAN_DATA_BPCBFHHD() throws IOException,SQLException,Exception {
        BPCBFHHD.INPUT.FETCH_NO = BPCIFHIS.INPUT.FETCH_NO;
        BPCBFHHD.INPUT.FUNC = BPCIFHIS.INPUT.FUNC;
        BPCBFHHD.INPUT.JRNNO = BPCIFHIS.INPUT.JRNNO;
        BPCBFHHD.INPUT.CI_NO = BPCIFHIS.INPUT.CI_NO;
        BPCBFHHD.INPUT.AC = BPCIFHIS.INPUT.AC;
        BPCBFHHD.INPUT.REF_NO = BPCIFHIS.INPUT.REF_NO;
        BPCBFHHD.INPUT.TX_TOOL = BPCIFHIS.INPUT.TX_TOOL;
        BPCBFHHD.INPUT.TX_CCY = BPCIFHIS.INPUT.TX_CCY;
        BPCBFHHD.INPUT.TX_CD = BPCIFHIS.INPUT.TX_CD;
        BPCBFHHD.INPUT.TX_TLR = BPCIFHIS.INPUT.TX_TLR;
        BPCBFHHD.INPUT.STR_AC_DT = BPCIFHIS.INPUT.STR_AC_DT;
        BPCBFHHD.INPUT.END_AC_DT = BPCIFHIS.INPUT.END_AC_DT;
        BPCBFHHD.INPUT.STR_TR_DT = BPCIFHIS.INPUT.STR_TR_DT;
        BPCBFHHD.INPUT.END_TR_DT = BPCIFHIS.INPUT.END_TR_DT;
        BPCBFHHD.INPUT.STR_TR_TM = BPCIFHIS.INPUT.STR_TR_TM;
        BPCBFHHD.INPUT.END_TR_TM = BPCIFHIS.INPUT.END_TR_TM;
        BPCBFHHD.INPUT.STR_AMT = BPCIFHIS.INPUT.STR_AMT;
        BPCBFHHD.INPUT.END_AMT = BPCIFHIS.INPUT.END_AMT;
        BPCBFHHD.INPUT.TX_BR = BPCIFHIS.INPUT.TX_BR;
        BPCBFHHD.INPUT.TX_DP = BPCIFHIS.INPUT.TX_DP;
        BPCBFHHD.INPUT.DC_FLG = BPCIFHIS.INPUT.DC_FLG;
        BPCBFHHD.INPUT.MAKER_ID = BPCIFHIS.INPUT.MAKER_ID;
        BPCBFHHD.INPUT.CHECKER_ID = BPCIFHIS.INPUT.CHECKER_ID;
        BPCBFHHD.INPUT.SORT_FLG = BPCIFHIS.INPUT.SORT_FLG;
        BPCBFHHD.INPUT.PROD_CD = BPCIFHIS.INPUT.PROD_CD;
        BPCBFHHD.INPUT.BROWS_COND = WS_BROWS_COND;
        BPCBFHHD.INPUT.REC_PT = BPRFHIST;
        BPCBFHHD.INPUT.REC_LEN = 690;
    }
    public void B001_SET_COND_CN() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'N';
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.AC);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_CCY);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.REF_NO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_TLR);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_BR);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_CD);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.PROD_CD);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.CI_NO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.DC_FLG);
        if (BPCIFHIS.INPUT.JRNNO != 0 
                && BPCIFHIS.INPUT.AC.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_TOOL.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.PROD_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_BR == 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
                && WS_BRW_FHIS_FLAG == 'N' 
                && BPCIFHIS.OUTPUT.JUMP_FLG != 'Y') {
            WS_BRW_FHIS_FLAG = 'Y';
            WS_BROWS_COND = '1';
        } else if (BPCIFHIS.INPUT.AC.trim().length() > 0 
                && BPCIFHIS.INPUT.TX_TOOL.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.PROD_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_BR != 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() > 0 
                && WS_BRW_FHIS_FLAG == 'N' 
                && BPCIFHIS.OUTPUT.JUMP_FLG != 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC");
            WS_BRW_FHIS_FLAG = 'Y';
            WS_BROWS_COND = '3';
        } else if (BPCIFHIS.INPUT.AC.trim().length() > 0 
                && BPCIFHIS.INPUT.TX_TOOL.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.PROD_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_BR == 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
                && WS_BRW_FHIS_FLAG == 'N' 
                && BPCIFHIS.OUTPUT.JUMP_FLG != 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_AC");
            WS_BRW_FHIS_FLAG = 'Y';
            WS_BROWS_COND = '2';
        } else if (BPCIFHIS.INPUT.AC.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_TOOL.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.PROD_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_BR != 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() > 0 
                && WS_BRW_FHIS_FLAG == 'N' 
                && BPCIFHIS.OUTPUT.JUMP_FLG != 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR");
            WS_BRW_FHIS_FLAG = 'Y';
            WS_BROWS_COND = '5';
        } else if (BPCIFHIS.INPUT.AC.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_TOOL.trim().length() > 0 
                && BPCIFHIS.INPUT.TX_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.PROD_CD.trim().length() == 0 
                && WS_BRW_FHIS_FLAG == 'N' 
                && BPCIFHIS.OUTPUT.JUMP_FLG != 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TX_TOOL");
            WS_BRW_FHIS_FLAG = 'Y';
            WS_BROWS_COND = '4';
        } else if (BPCIFHIS.INPUT.AC.trim().length() > 0 
                && BPCIFHIS.INPUT.TX_TOOL.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.PROD_CD.trim().length() > 0 
                && BPCIFHIS.INPUT.TX_BR == 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
                && BPCIFHIS.INPUT.DC_FLG == ' ' 
                && WS_BRW_FHIS_FLAG == 'N' 
                && BPCIFHIS.OUTPUT.JUMP_FLG != 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_PROD_CD");
            WS_BRW_FHIS_FLAG = 'Y';
            WS_BROWS_COND = '7';
        } else if (BPCIFHIS.INPUT.AC.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_TOOL.trim().length() > 0 
                && BPCIFHIS.INPUT.TX_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.PROD_CD.trim().length() > 0 
                && BPCIFHIS.INPUT.TX_BR == 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
                && BPCIFHIS.INPUT.DC_FLG == ' ' 
                && WS_BRW_FHIS_FLAG == 'N' 
                && BPCIFHIS.OUTPUT.JUMP_FLG != 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_PROD_CD");
            WS_BRW_FHIS_FLAG = 'Y';
            WS_BROWS_COND = '8';
        } else if (BPCIFHIS.INPUT.AC.trim().length() > 0 
                && BPCIFHIS.INPUT.TX_TOOL.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.PROD_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_BR == 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
                && WS_BRW_FHIS_FLAG == 'N' 
                && BPCIFHIS.OUTPUT.JUMP_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_PROD_CD");
            WS_BRW_FHIS_FLAG = 'Y';
            WS_BROWS_COND = '9';
        } else {
            CEP.TRC(SCCGWA, "OTHER COND!!!");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCIFHIS.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_BROWS_COND);
    }
    public void B003_SET_COND_ALL_CN() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'N';
        CEP.TRC(SCCGWA, "BROWSE-BY-JRN-ALL");
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.AC);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_CCY);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.REF_NO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_TLR);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_BR);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_CD);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.PROD_CD);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.CI_NO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.DC_FLG);
        if (BPCIFHIS.INPUT.JRNNO != 0 
                && BPCIFHIS.INPUT.AC.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_TOOL.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.PROD_CD.trim().length() == 0 
                && BPCIFHIS.INPUT.TX_BR == 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
                && WS_BRW_FHIS_FLAG == 'N' 
                && BPCIFHIS.OUTPUT.JUMP_FLG != 'Y') {
            WS_BRW_FHIS_FLAG = 'Y';
            WS_BROWS_COND = 'A';
        } else {
            CEP.TRC(SCCGWA, "OTHER COND!!!");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONE_COND, BPCIFHIS.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZBFHTD() throws IOException,SQLException,Exception {
        WS_HAVE_CALL_FLG = 'Y';
        IBS.CALLCPN(SCCGWA, CPN_BR_FHIS_TOD, BPCBFHTD);
        CEP.TRC(SCCGWA, BPCBFHTD.OUTPUT.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCBFHTD.OUTPUT.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCIFHIS.OUTPUT.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            if (WS_DATE_COND != 'B' 
                || !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZBFHHD() throws IOException,SQLException,Exception {
        WS_HAVE_CALL_FLG = 'Y';
        IBS.CALLCPN(SCCGWA, CPN_BR_FHIST_HIS, BPCBFHHD);
        CEP.TRC(SCCGWA, BPCBFHHD.OUTPUT.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCBFHHD.OUTPUT.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCIFHIS.OUTPUT.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            if (WS_DATE_COND != 'B' 
                || !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B051_BROWSE_SECOND_TAB_CN() throws IOException,SQLException,Exception {
        BPCIFHIS.INPUT.FUNC = '1';
        CEP.TRC(SCCGWA, "STARTBR_SECOND_TABLE");
        B100_SUB_MAIN_PROC_CN();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            CEP.TRC(SCCGWA, "READNEXT_SECOND_TABLE");
            BPCIFHIS.INPUT.FUNC = '2';
            B100_SUB_MAIN_PROC_CN();
            if (pgmRtn) return;
        }
    }
    public void B080_BROWSE_NEXT_TAB_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_DATE_COND);
        CEP.TRC(SCCGWA, WS_TABLE_FLG);
        if (WS_DATE_COND == 'B') {
            if (BPCIFHIS.INPUT.FUNC == '2' 
                || BPCIFHIS.INPUT.FUNC == '9') {
                BPCIFHIS.INPUT.FUNC = '3';
                B100_SUB_MAIN_PROC_CN();
                if (pgmRtn) return;
                BPCIFHIS.INPUT.FUNC = '2';
            }
            if (WS_TABLE_FLG == 'T') {
                WS_TABLE_FLG = 'H';
                WS_DATE_COND = 'H';
                BPCIFHIS.OUTPUT.TABLE_FLG = 'H';
                if (BPCIFHIS.INPUT.FUNC == '2') {
                    CEP.TRC(SCCGWA, "BROWSE_SECOND_TABLE_HIS");
                    B051_BROWSE_SECOND_TAB_CN();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "COUNT_SECOND_TABLE_HIS");
                    B100_SUB_MAIN_PROC_CN();
                    if (pgmRtn) return;
                }
            } else {
                WS_TABLE_FLG = 'T';
                WS_DATE_COND = 'T';
                BPCIFHIS.OUTPUT.TABLE_FLG = 'T';
                if (BPCIFHIS.INPUT.FUNC == '2') {
                    CEP.TRC(SCCGWA, "BROWSE_SECOND_TABLE_TOD");
                    B051_BROWSE_SECOND_TAB_CN();
                    if (pgmRtn) return;
                } else {
                    CEP.TRC(SCCGWA, "COUNT_SECOND_TABLE_TOD");
                    B100_SUB_MAIN_PROC_CN();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, WS_DATE_COND);
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCIFHIS.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_BAL_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_FOR_BAL();
        if (pgmRtn) return;
    }
    public void B010_STARTBR_STATEMENT_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_FOR_STATEMENT();
        if (pgmRtn) return;
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'N';
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.AC);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_CCY);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.REF_NO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.JRNNO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_TLR);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_CD);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.TX_TOOL);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.CI_NO);
        CEP.TRC(SCCGWA, BPCIFHIS.INPUT.DC_FLG);
        if (BPCIFHIS.INPUT.AC.trim().length() == 0 
            && BPCIFHIS.INPUT.TX_CCY.trim().length() == 0 
            && BPCIFHIS.INPUT.REF_NO.trim().length() == 0 
            && BPCIFHIS.INPUT.JRNNO == 0 
            && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
            && BPCIFHIS.INPUT.TX_CD.trim().length() == 0 
            && BPCIFHIS.INPUT.TX_TOOL.trim().length() == 0 
            && BPCIFHIS.INPUT.CI_NO.trim().length() == 0 
            && BPCIFHIS.INPUT.TX_BR == 0 
            && BPCIFHIS.INPUT.TX_DP == 0 
            && BPCIFHIS.INPUT.MAKER_ID.trim().length() == 0 
            && BPCIFHIS.INPUT.CHECKER_ID.trim().length() == 0 
            && BPCIFHIS.INPUT.DC_FLG == ' ') {
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_NONE();
            if (pgmRtn) return;
        }
        if (BPCIFHIS.INPUT.JRNNO != 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_JRN");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_JRN();
            if (pgmRtn) return;
        } else if (BPCIFHIS.INPUT.DC_FLG != ' ' 
                && WS_BRW_FHIS_FLAG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_DC_AC");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_DC();
            if (pgmRtn) return;
        } else if ((BPCIFHIS.INPUT.AC.trim().length() > 0 
                && (BPCIFHIS.INPUT.TX_BR != 0 
                || BPCIFHIS.INPUT.TX_TLR.trim().length() > 0) 
                && WS_BRW_FHIS_FLAG == 'N')) {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_ACBRTLR();
            if (pgmRtn) return;
        } else if ((BPCIFHIS.INPUT.AC.trim().length() == 0 
                && (BPCIFHIS.INPUT.TX_BR != 0 
                || BPCIFHIS.INPUT.TX_TLR.trim().length() > 0) 
                && WS_BRW_FHIS_FLAG == 'N')) {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_BRTLR();
            if (pgmRtn) return;
        } else if (BPCIFHIS.INPUT.AC.trim().length() > 0 
                && BPCIFHIS.INPUT.TX_BR == 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
                && BPCIFHIS.INPUT.DC_FLG == ' ' 
                && WS_BRW_FHIS_FLAG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_AC");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_AC();
            if (pgmRtn) return;
        } else if (BPCIFHIS.INPUT.REF_NO.trim().length() > 0 
                && WS_BRW_FHIS_FLAG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_REF");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_REF();
            if (pgmRtn) return;
        } else if (BPCIFHIS.INPUT.TX_TOOL.trim().length() > 0 
                && WS_BRW_FHIS_FLAG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TOOL");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_TXTOOL();
            if (pgmRtn) return;
        } else if (BPCIFHIS.INPUT.CI_NO.trim().length() > 0 
                && BPCIFHIS.INPUT.TX_BR == 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
                && WS_BRW_FHIS_FLAG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_AMT_CI");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_CINO();
            if (pgmRtn) return;
        } else if (BPCIFHIS.INPUT.TX_CCY.trim().length() > 0 
                && WS_BRW_FHIS_FLAG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_CCY");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_CCY();
            if (pgmRtn) return;
        } else if (BPCIFHIS.INPUT.MAKER_ID.trim().length() > 0 
                && WS_BRW_FHIS_FLAG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_MAKER");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_MAKER();
            if (pgmRtn) return;
        } else if (BPCIFHIS.INPUT.CHECKER_ID.trim().length() > 0 
                && WS_BRW_FHIS_FLAG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_CHECKER");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_CHECKER();
            if (pgmRtn) return;
        } else if (BPCIFHIS.INPUT.TX_DP != 0 
                && WS_BRW_FHIS_FLAG == 'N') {
            CEP.TRC(SCCGWA, "BROWSE_BY_DP");
            WS_BRW_FHIS_FLAG = 'Y';
            T000_STARTBR_DP();
            if (pgmRtn) return;
        } else {
            if (WS_BRW_FHIS_FLAG == 'N') {
                CEP.TRC(SCCGWA, "BROWSE_BY_NONE");
                WS_BRW_FHIS_FLAG = 'Y';
                T000_STARTBR_NONE();
                if (pgmRtn) return;
            }
        }
        if (WS_BRW_FHIS_FLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCIFHIS.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTFHIST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRNNO);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.JRN_SEQ);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC);
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTFHIST();
        if (pgmRtn) return;
    }
    public void B040_GROUPMAX_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIST);
        T000_GROUPMAX_BPTFHIST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MAX_JRN_SEQ);
        BPCIFHIS.OUTPUT.MAX_JRN_SEQ = WS_MAX_JRN_SEQ;
        CEP.TRC(SCCGWA, BPRFHIST);
    }
    public void B050_READ_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRFHIST);
        T000_READ_BPTFHIST();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_FOR_BAL() throws IOException,SQLException,Exception {
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
            + "AND AC_DT <= :WS_END_DATE";
        BPTFHIST_BR.rp.order = "AC,TX_CCY,AC_DT,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
    }
    public void T000_STARTBR_FOR_STATEMENT() throws IOException,SQLException,Exception {
        BPTFHIST_BR.rp = new DBParm();
        BPTFHIST_BR.rp.TableName = "BPTFHIST";
        BPTFHIST_BR.rp.where = "AC = :WS_AC "
            + "AND TX_CCY = :WS_TX_CCY "
            + "AND AC_DT >= :WS_STR_DATE "
            + "AND AC_DT <= :WS_END_DATE";
        BPTFHIST_BR.rp.order = "AC,TX_CCY,TX_DT,TX_TM,JRNNO,JRN_SEQ";
        IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
    }
    public void T000_STARTBR_NONE() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            if (WS_STR_DATE == WS_END_DATE) {
                CEP.TRC(SCCGWA, "BROWSE_BY_NONE_SAMEDT");
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC_DT = :WS_STR_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            } else {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
        } else {
            if (WS_STR_DATE == WS_END_DATE) {
                CEP.TRC(SCCGWA, "BROWSE_BY_NONE_DIFFDT");
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC_DT = :WS_STR_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            } else {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND TX_AMT <= :WS_END_AMT";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
        }
    }
    public void T000_STARTBR_AC() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.TX_CCY.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BROWSE_BY_AC_CCY");
            if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC = :WS_AC "
                    + "AND TX_CCY = :WS_TX_CCY "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            } else {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC = :WS_AC "
                    + "AND TX_CCY = :WS_TX_CCY "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
        } else {
            CEP.TRC(SCCGWA, "BROWSE_BY_AC");
            if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC = :WS_AC "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            } else {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC = :WS_AC "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
        }
    }
    public void T000_STARTBR_CINO() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "CI_NO = :WS_CI_NO "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "CI_NO = :WS_CI_NO "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
    }
    public void T000_STARTBR_DP() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND TX_DP = :WS_TX_DP";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND TX_DP = :WS_TX_DP";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
    }
    public void T000_STARTBR_BR() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_BR = :WS_TX_BR";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_BR = :WS_TX_BR";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
    }
    public void T000_STARTBR_REF() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT <= :WS_END_DATE "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND REF_NO = :WS_REF_NO";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT <= :WS_END_DATE "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND REF_NO = :WS_REF_NO";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
    }
    public void T000_STARTBR_JRN() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        CEP.TRC(SCCGWA, WS_JRNNO);
        CEP.TRC(SCCGWA, WS_STR_AMT);
        CEP.TRC(SCCGWA, WS_END_AMT);
        CEP.TRC(SCCGWA, WS_STR_TR_DATE);
        CEP.TRC(SCCGWA, WS_END_TR_DATE);
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "JRNNO = :WS_JRNNO "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND AC_DT >= :WS_STR_DATE";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "JRNNO = :WS_JRNNO "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND AC_DT >= :WS_STR_DATE";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_TXTOOL() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT <= :WS_END_DATE "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND TX_TOOL = :WS_TX_TOOL";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT <= :WS_END_DATE "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND TX_TOOL = :WS_TX_TOOL";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
    }
    public void T000_STARTBR_TLR() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_TLR = :WS_TX_TELR";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_TLR = :WS_TX_TELR";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
    }
    public void T000_STARTBR_TLR_BR() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_BR = :WS_TX_BR";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND TX_BR = :WS_TX_BR";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
    }
    public void T000_STARTBR_CCY() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND TX_CCY = :WS_TX_CCY";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND TX_CCY = :WS_TX_CCY";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
    }
    public void T000_STARTBR_DC() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, WS_AC);
        CEP.TRC(SCCGWA, WS_TX_DC);
        if (BPCIFHIS.INPUT.AC.trim().length() > 0) {
            if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC = :WS_AC "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT "
                    + "AND DRCRFLG = :WS_TX_DC";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            } else {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC = :WS_AC "
                    + "AND AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT "
                    + "AND DRCRFLG = :WS_TX_DC";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
        } else {
            if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT "
                    + "AND DRCRFLG = :WS_TX_DC";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            } else {
                BPTFHIST_BR.rp = new DBParm();
                BPTFHIST_BR.rp.TableName = "BPTFHIST";
                BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                    + "AND AC_DT <= :WS_END_DATE "
                    + "AND TX_AMT >= :WS_STR_AMT "
                    + "AND TX_AMT <= :WS_END_AMT "
                    + "AND DRCRFLG = :WS_TX_DC";
                BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
                IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
            }
        }
    }
    public void T000_STARTBR_ACBRTLR() throws IOException,SQLException,Exception {
        if (BPCIFHIS.INPUT.TX_BR != 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() > 0 
                && BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCIFHIS.INPUT.TX_BR != 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() > 0 
                && BPCIFHIS.INPUT.SORT_FLG != 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_TLR_AC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCIFHIS.INPUT.TX_BR != 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
                && BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_AC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCIFHIS.INPUT.TX_BR != 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() == 0 
                && BPCIFHIS.INPUT.SORT_FLG != 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_BR_AC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_BR = :WS_TX_BR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCIFHIS.INPUT.TX_BR == 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() > 0 
                && BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TLR_AC DESC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else if (BPCIFHIS.INPUT.TX_BR == 0 
                && BPCIFHIS.INPUT.TX_TLR.trim().length() > 0 
                && BPCIFHIS.INPUT.SORT_FLG != 'Y') {
            CEP.TRC(SCCGWA, "BROWSE_BY_TLR_AC ASC");
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC = :WS_AC "
                + "AND TX_TLR = :WS_TX_TELR "
                + "AND AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            WS_BRW_FHIS_FLAG = 'N';
        }
    }
    public void T000_STARTBR_BRTLR() throws IOException,SQLException,Exception {
        if (BPCIFHIS.INPUT.TX_TLR.trim().length() > 0 
            && BPCIFHIS.INPUT.TX_BR != 0) {
            T000_STARTBR_TLR_BR();
            if (pgmRtn) return;
        } else {
            if (BPCIFHIS.INPUT.TX_TLR.trim().length() > 0) {
                T000_STARTBR_TLR();
                if (pgmRtn) return;
            } else {
                T000_STARTBR_BR();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_STARTBR_MAKER() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND MAKER = :WS_MAKER_ID";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND MAKER = :WS_MAKER_ID";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
    }
    public void T000_STARTBR_CHECKER() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        if (BPCIFHIS.INPUT.SORT_FLG == 'Y') {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND SUP1 = :WS_CHECKER_ID";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ DESC";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        } else {
            BPTFHIST_BR.rp = new DBParm();
            BPTFHIST_BR.rp.TableName = "BPTFHIST";
            BPTFHIST_BR.rp.where = "AC_DT >= :WS_STR_DATE "
                + "AND AC_DT <= :WS_END_DATE "
                + "AND TX_AMT >= :WS_STR_AMT "
                + "AND TX_AMT <= :WS_END_AMT "
                + "AND SUP1 = :WS_CHECKER_ID";
            BPTFHIST_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        }
    }
    public void T000_READNEXT_BPTFHIST() throws IOException,SQLException,Exception {
        WS_BRW_FHIS_FLAG = 'Y';
        CEP.TRC(SCCGWA, BPRFHIST);
        IBS.READNEXT(SCCGWA, BPRFHIST, this, BPTFHIST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_END_OF_TABLE, BPCIFHIS.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_ENDBR_BPTFHIST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFHIST_BR);
    }
    public void T000_GROUPMAX_BPTFHIST() throws IOException,SQLException,Exception {
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.set = "WS-MAX-JRN-SEQ=IFNULL(MAX(JRN_SEQ),0)";
        BPTFHIST_RD.where = "AC_DT = :BPRFHIST.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIST.KEY.JRNNO";
        IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
    }
    public void T000_READ_BPTFHIST() throws IOException,SQLException,Exception {
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.where = "AC_DT = :BPRFHIST.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIST.KEY.JRNNO "
            + "AND JRN_SEQ = :BPRFHIST.KEY.JRN_SEQ";
        BPTFHIST_RD.fst = true;
        IBS.READ(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REC_NOTFND, BPCIFHIS.OUTPUT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIFHIS.OUTPUT.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCIFHIS.OUTPUT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCIFHIS = ");
            CEP.TRC(SCCGWA, BPCIFHIS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
