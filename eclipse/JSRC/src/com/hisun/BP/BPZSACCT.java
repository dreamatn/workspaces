package com.hisun.BP;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSACCT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_OUTPUT_FMT = "BP088";
    String K_HIS_REMARKS = "FEE BOOKING CENTER CHANGE";
    String K_CPY_BPCHFCHG = "BPCHACCT";
    String K_CTRT_TYPE_FEES = "FEES";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_IDX = 0;
    double WS_AMT = 0;
    String WS_PROD_CODE = " ";
    String WS_CCY = " ";
    char WS_FCTR_STS = ' ';
    char WS_FSCH_STS = ' ';
    BPZSACCT_WS_FMT_DATA WS_FMT_DATA = new BPZSACCT_WS_FMT_DATA();
    char WS_FEE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCACCTO BPCACCTO = new BPCACCTO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHACCT BPCNACCT = new BPCHACCT();
    BPCHACCT BPCOACCT = new BPCHACCT();
    BPCHFSTS BPCOFSTS = new BPCHFSTS();
    BPCHFSTS BPCNFSTS = new BPCHFSTS();
    BPCRFCTR BPCRFCTR = new BPCRFCTR();
    BPRFCTR BPRFCTR = new BPRFCTR();
    BPCRFSCH BPCRFSCH = new BPCRFSCH();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPCRFAMO BPCRFAMO = new BPCRFAMO();
    BPRFAMO BPRFAMO = new BPRFAMO();
    BPCRACCR BPCRACCR = new BPCRACCR();
    BPRPACCR BPRPACCR = new BPRPACCR();
    BPCUGMC BPCUGMC = new BPCUGMC();
    CICCUST CICCUST = new CICCUST();
    BPCUMENT BPCUMENT = new BPCUMENT();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPQCTR BPCPQCTR = new BPCPQCTR();
    BPCAMMDP BPCOMMDP = new BPCAMMDP();
    BPCAMMDP BPCNMMDP = new BPCAMMDP();
    SCCGWA SCCGWA;
    BPCSACCT BPCSACCT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSACCT BPCSACCT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSACCT = BPCSACCT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSACCT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRFCTR);
        IBS.init(SCCGWA, BPRFCTR);
        IBS.init(SCCGWA, BPCRFSCH);
        IBS.init(SCCGWA, BPRFSCH);
        IBS.init(SCCGWA, BPCACCTO);
        IBS.init(SCCGWA, BPCRFAMO);
        IBS.init(SCCGWA, BPRFAMO);
        IBS.init(SCCGWA, BPCRACCR);
        IBS.init(SCCGWA, BPRPACCR);
        IBS.init(SCCGWA, BPCUGMC);
        IBS.init(SCCGWA, BPCOMMDP);
        IBS.init(SCCGWA, BPCNMMDP);
        IBS.init(SCCGWA, CICCUST);
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPCNACCT);
        IBS.init(SCCGWA, BPCOACCT);
        IBS.init(SCCGWA, BPCOFSTS);
        IBS.init(SCCGWA, BPCNFSTS);
        IBS.init(SCCGWA, BPCPQCTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_CHECK_PROCESS();
        if (pgmRtn) return;
        if (BPCSACCT.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSACCT.FUNC == 'A') {
            B020_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSACCT.FUNC == 'U') {
            B030_UPDATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSACCT.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSACCT.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSACCT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B000_CHECK_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSACCT.FUNC == 'B' 
            && BPCSACCT.CTA_NO.trim().length() > 0 
            && BPCSACCT.REL_CTA_NO.trim().length() > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CONTACT_NO_INPUT_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B030_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQCTR);
        BPCPQCTR.KEY.CTRT_NO = BPCSACCT.CTA_NO;
        S000_CALL_BPZPQCTR();
        if (pgmRtn) return;
        if (BPCPQCTR.FLAG.CTRT_FLAG == 'C') {
            IBS.init(SCCGWA, BPCRFCTR);
            IBS.init(SCCGWA, BPRFCTR);
            BPRFCTR.KEY.CTRT_NO = BPCSACCT.CTA_NO;
            BPCRFCTR.INFO.FUNC = 'R';
            BPCRFCTR.INFO.REC_LEN = 889;
            BPCRFCTR.INFO.POINTER = BPRFCTR;
            S010_CALL_BPZRFCTR();
            if (pgmRtn) return;
            if (BPCRFCTR.RC.RC_CODE != 0 
                || BPCRFCTR.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_CTRT_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (BPRFCTR.FEE_STATUS == '2' 
                    || BPRFCTR.FEE_STATUS == '3') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_CTRT_STS_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            BPRFCTR.KEY.CTRT_NO = BPCSACCT.CTA_NO;
            BPRFCTR.REL_CTRT_NO = BPCSACCT.REL_CTA_NO;
            BPRFCTR.FEE_TYPE = BPCSACCT.FEE_TYPE;
            BPRFCTR.CI_NO = BPCSACCT.CI_NO;
            BPRFCTR.START_DATE = BPCSACCT.START_DT;
            BPRFCTR.MATURITY_DATE = BPCSACCT.MATURITY_DT;
            BPRFCTR.BOOK_CENTRE = BPCSACCT.BR_NEW;
            BPRFCTR.CR_TO_BR = BPCSACCT.BR_NEW;
            BPRFCTR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFCTR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPCRFCTR.INFO.FUNC = 'U';
            BPCRFCTR.INFO.REC_LEN = 889;
            BPCRFCTR.INFO.POINTER = BPRFCTR;
            S010_CALL_BPZRFCTR();
            if (pgmRtn) return;
            if (BPCRFCTR.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFCTR.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCRFCTR.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPDATE_REC_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_PROD_CODE = BPRFCTR.PRD_TYPE;
            WS_CCY = BPRFCTR.CHARGE_CCY;
            if (BPRFCTR.PAYMENT_METHOD == 'P') {
                R010_GET_FAMO_INFO();
                if (pgmRtn) return;
                R010_CHANGE_BR();
                if (pgmRtn) return;
            } else {
                R010_GET_ACCR_INFO();
                if (pgmRtn) return;
                R010_CHANGE_BR();
                if (pgmRtn) return;
            }
            ROOO_WRITE_WF_REF_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCPQCTR.FLAG.CTRT_FLAG == 'S') {
            IBS.init(SCCGWA, BPCRFSCH);
            IBS.init(SCCGWA, BPRFSCH);
            BPRFSCH.KEY.CTRT_NO = BPCSACCT.CTA_NO;
            BPCRFSCH.INFO.FUNC = 'R';
            BPCRFSCH.INFO.REC_LEN = 816;
            BPCRFSCH.INFO.POINTER = BPRFSCH;
            S020_CALL_BPZRFSCH();
            if (pgmRtn) return;
            if (BPCRFSCH.RC.RC_CODE != 0 
                || BPCRFSCH.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                if (BPRFSCH.FEE_STATUS == '2' 
                    || BPRFSCH.FEE_STATUS == '3') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_CTRT_STS_INVALID;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            BPRFSCH.BOOK_CENTRE = BPCSACCT.BR_NEW;
            BPRFSCH.CR_TO_BR = BPCSACCT.BR_NEW;
            BPRFSCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFSCH.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPCRFSCH.INFO.FUNC = 'U';
            S020_CALL_BPZRFSCH();
            if (pgmRtn) return;
            if (BPCRFSCH.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFSCH.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCRFSCH.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPDATE_REC_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_PROD_CODE = BPRFSCH.PRD_TYPE;
            WS_CCY = BPRFSCH.CHARGE_CCY;
            R010_GET_ACCR_INFO();
            if (pgmRtn) return;
            R010_CHANGE_BR();
            if (pgmRtn) return;
            ROOO_WRITE_WF_REF_PROCESS();
            if (pgmRtn) return;
        }
        R000_WRITE_HISTORY_PROCESS();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSACCT.FCTR_STATUS != ' ') {
            IBS.init(SCCGWA, BPCRFCTR);
            IBS.init(SCCGWA, BPRFCTR);
            BPRFCTR.KEY.CTRT_NO = BPCSACCT.CTA_NO;
            BPCRFCTR.INFO.FUNC = 'R';
            BPCRFCTR.INFO.REC_LEN = 889;
            BPCRFCTR.INFO.POINTER = BPRFCTR;
            S010_CALL_BPZRFCTR();
            if (pgmRtn) return;
            if (BPCRFCTR.RC.RC_CODE != 0 
                || BPCRFCTR.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_CTRT_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSACCT.FCTR_STATUS == BPRFCTR.FEE_STATUS) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_CTRT_STS_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPRFCTR.FEE_STATUS = BPCSACCT.FCTR_STATUS;
            WS_FCTR_STS = BPCSACCT.FCTR_STATUS;
            BPRFCTR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFCTR.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPCRFCTR.INFO.FUNC = 'U';
            BPCRFCTR.INFO.REC_LEN = 889;
            BPCRFCTR.INFO.POINTER = BPRFCTR;
            S010_CALL_BPZRFCTR();
            if (pgmRtn) return;
            if (BPCRFCTR.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFCTR.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCRFCTR.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPDATE_REC_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            ROOO_WRITE_WF_REF_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSACCT.FSCH_STATUS != ' ') {
            IBS.init(SCCGWA, BPCRFSCH);
            IBS.init(SCCGWA, BPRFSCH);
            BPRFSCH.KEY.CTRT_NO = BPCSACCT.CTA_NO;
            BPCRFSCH.INFO.FUNC = 'R';
            BPCRFSCH.INFO.REC_LEN = 816;
            BPCRFSCH.INFO.POINTER = BPRFSCH;
            S020_CALL_BPZRFSCH();
            if (pgmRtn) return;
            if (BPCRFSCH.RC.RC_CODE != 0 
                || BPCRFSCH.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FSCH_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSACCT.FSCH_STATUS == BPRFSCH.FEE_STATUS) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_F_CTRT_STS_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPRFSCH.FEE_STATUS = BPCSACCT.FSCH_STATUS;
            WS_FSCH_STS = BPCSACCT.FSCH_STATUS;
            BPRFSCH.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRFSCH.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
            BPCRFSCH.INFO.FUNC = 'U';
            S020_CALL_BPZRFSCH();
            if (pgmRtn) return;
            if (BPCRFSCH.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFSCH.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCRFSCH.RETURN_INFO != 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPDATE_REC_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            ROOO_WRITE_WF_REF_PROCESS();
            if (pgmRtn) return;
        }
        R000_WRITE_HISTORY_PROCESS();
        if (pgmRtn) return;
        R000_EVENT_PROCESS();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSACCT.CTA_NO.trim().length() > 0) {
            IBS.init(SCCGWA, BPCRFCTR);
            IBS.init(SCCGWA, BPRFCTR);
            BPRFCTR.KEY.CTRT_NO = BPCSACCT.CTA_NO;
            BPCRFCTR.INFO.OPT = '0';
            BPCRFCTR.INFO.FUNC = 'B';
            BPCRFCTR.INFO.REC_LEN = 889;
            BPCRFCTR.INFO.POINTER = BPRFCTR;
            S010_CALL_BPZRFCTR();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 172;
            SCCMPAG.SCR_ROW_CNT = 0;
            SCCMPAG.SCR_COL_CNT = 0;
            B_MPAG();
            if (pgmRtn) return;
            BPCRFCTR.INFO.FUNC = 'N';
            S010_CALL_BPZRFCTR();
            if (pgmRtn) return;
            while (BPCRFCTR.RETURN_INFO != 'N' 
                && BPCRFCTR.RC.RC_CODE == 0 
                && SCCMPAG.FUNC != 'E') {
                CEP.TRC(SCCGWA, BPRFCTR.KEY.CTRT_NO);
                CEP.TRC(SCCGWA, BPCSACCT.CTA_NO);
                if (BPRFCTR.KEY.CTRT_NO.equalsIgnoreCase(BPCSACCT.CTA_NO)) {
                    R010_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
                BPCRFCTR.INFO.FUNC = 'N';
                S010_CALL_BPZRFCTR();
                if (pgmRtn) return;
            }
            BPCRFCTR.INFO.FUNC = 'E';
            S010_CALL_BPZRFCTR();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCRFSCH);
            IBS.init(SCCGWA, BPRFSCH);
            BPRFSCH.KEY.CTRT_NO = BPCSACCT.CTA_NO;
            BPCRFSCH.INFO.OPT = '0';
            BPCRFSCH.INFO.FUNC = 'B';
            BPCRFSCH.INFO.REC_LEN = 816;
            BPCRFSCH.INFO.POINTER = BPRFSCH;
            S020_CALL_BPZRFSCH();
            if (pgmRtn) return;
            BPCRFSCH.INFO.FUNC = 'N';
            S020_CALL_BPZRFSCH();
            if (pgmRtn) return;
            while (BPCRFSCH.RC.RC_CODE == 0 
                && BPCRFSCH.RETURN_INFO != 'N' 
                && SCCMPAG.FUNC != 'E') {
                if (BPCSACCT.CTA_NO.equalsIgnoreCase(BPRFSCH.KEY.CTRT_NO)) {
                    R010_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
                BPCRFSCH.INFO.FUNC = 'N';
                S020_CALL_BPZRFSCH();
                if (pgmRtn) return;
            }
            BPCRFSCH.INFO.FUNC = 'E';
            S020_CALL_BPZRFSCH();
            if (pgmRtn) return;
        }
        if (BPCSACCT.REL_CTA_NO.trim().length() > 0) {
            IBS.init(SCCGWA, BPCRFCTR);
            IBS.init(SCCGWA, BPRFCTR);
            BPRFCTR.REL_CTRT_NO = BPCSACCT.REL_CTA_NO;
            BPCRFCTR.INFO.OPT = '4';
            BPCRFCTR.INFO.FUNC = 'B';
            BPCRFCTR.INFO.REC_LEN = 889;
            BPCRFCTR.INFO.POINTER = BPRFCTR;
            S010_CALL_BPZRFCTR();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 172;
            SCCMPAG.SCR_ROW_CNT = 0;
            SCCMPAG.SCR_COL_CNT = 0;
            B_MPAG();
            if (pgmRtn) return;
            BPCRFCTR.INFO.FUNC = 'N';
            S010_CALL_BPZRFCTR();
            if (pgmRtn) return;
            while (BPCRFCTR.RETURN_INFO != 'N' 
                && BPCRFCTR.RC.RC_CODE == 0 
                && SCCMPAG.FUNC != 'E') {
                R010_FMT_OUTPUT_DATA();
                if (pgmRtn) return;
                BPCRFCTR.INFO.FUNC = 'N';
                S010_CALL_BPZRFCTR();
                if (pgmRtn) return;
            }
            BPCRFCTR.INFO.FUNC = 'E';
            S010_CALL_BPZRFCTR();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCRFSCH);
            IBS.init(SCCGWA, BPRFSCH);
            BPRFSCH.REL_CTRT_NO = BPCSACCT.REL_CTA_NO;
            BPCRFSCH.INFO.FUNC = 'B';
            BPCRFSCH.INFO.OPT = '4';
            BPCRFSCH.INFO.REC_LEN = 816;
            BPCRFSCH.INFO.POINTER = BPRFSCH;
            S020_CALL_BPZRFSCH();
            if (pgmRtn) return;
            BPCRFSCH.INFO.FUNC = 'N';
            S020_CALL_BPZRFSCH();
            if (pgmRtn) return;
            while (BPCRFSCH.RC.RC_CODE == 0 
                && BPCRFSCH.RETURN_INFO != 'N' 
                && SCCMPAG.FUNC != 'E') {
                R010_FMT_OUTPUT_DATA();
                if (pgmRtn) return;
                BPCRFSCH.INFO.FUNC = 'N';
                S020_CALL_BPZRFSCH();
                if (pgmRtn) return;
            }
            BPCRFSCH.INFO.FUNC = 'E';
            S020_CALL_BPZRFSCH();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPCNACCT);
        IBS.init(SCCGWA, BPCOACCT);
        IBS.init(SCCGWA, BPCNFSTS);
        IBS.init(SCCGWA, BPCOFSTS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("9999164") 
            || BPCSACCT.FUNC == 'U') {
            BPCNACCT.CTA_NO = BPCSACCT.CTA_NO;
            BPCNACCT.REL_CTA_NO = BPCSACCT.REL_CTA_NO;
            BPCNACCT.FEE_TYPE = BPCSACCT.FEE_TYPE;
            BPCNACCT.CI_NO = BPCSACCT.CI_NO;
            BPCNACCT.CLIENT_NAME = BPCSACCT.CLIENT_NAME;
            BPCNACCT.START_DT = BPCSACCT.START_DT;
            BPCNACCT.MATURITY_DT = BPCSACCT.MATURITY_DT;
            BPCNACCT.BR_OLD = BPCSACCT.BR_OLD;
            BPCNACCT.BR_NEW = BPCSACCT.BR_NEW;
            BPCPNHIS.INFO.TX_RMK = "FEE BOOKING CENTER CHANGE";
            BPCPNHIS.INFO.FMT_ID = "BPCHACCT";
            BPCPNHIS.INFO.FMT_ID_LEN = 168;
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOACCT;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNACCT;
            S000_CALL_BPZPNHIS();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (JIBS_tmp_str[0].equalsIgnoreCase("9999166") 
            || BPCSACCT.FUNC == 'D') {
            BPCNFSTS.CTA_NO = BPCSACCT.CTA_NO;
            BPCNFSTS.REL_CTA_NO = BPCSACCT.REL_CTA_NO;
            BPCNFSTS.FEE_TYPE = BPCSACCT.FEE_TYPE;
            BPCNFSTS.CI_NO = BPCSACCT.CI_NO;
            BPCNFSTS.CLIENT_NAME = BPCSACCT.CLIENT_NAME;
            BPCNFSTS.START_DT = BPCSACCT.START_DT;
            BPCNFSTS.MATURITY_DT = BPCSACCT.MATURITY_DT;
            BPCNFSTS.FSCH_OSTS = WS_FSCH_STS;
            BPCNFSTS.FSCH_NSTS = BPCSACCT.FSCH_STATUS;
            BPCNFSTS.FCTR_OSTS = WS_FCTR_STS;
            BPCNFSTS.FCTR_NSTS = BPCSACCT.FCTR_STATUS;
            BPCPNHIS.INFO.TX_RMK = "FEE STATUS CHANGE";
            BPCPNHIS.INFO.FMT_ID = "BPCHFSTS";
            BPCPNHIS.INFO.FMT_ID_LEN = 168;
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOFSTS;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNFSTS;
            S000_CALL_BPZPNHIS();
            if (pgmRtn) return;
        }
    }
    public void R000_EVENT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "DYBATNO";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCUMENT);
        BPCUMENT.DIARY_DATA.CONTRACT_NO = BPCSACCT.CTA_NO;
        BPCUMENT.DIARY_DATA.DIARY_BATNO = BPCSGSEQ.SEQ;
        BPCUMENT.PROC_DATA.FUNC_CODE = "D1";
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.CONTRACT_NO);
        CEP.TRC(SCCGWA, BPCUMENT.DIARY_DATA.DIARY_BATNO);
        S000_CALL_BPZUMENT();
        if (pgmRtn) return;
    }
    public void ROOO_WRITE_WF_REF_PROCESS() throws IOException,SQLException,Exception {
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCACCTO);
        IBS.init(SCCGWA, SCCFMT);
        BPCACCTO.CTA_NO = BPCSACCT.CTA_NO;
        BPCACCTO.REL_CTA_NO = BPCSACCT.REL_CTA_NO;
        BPCACCTO.FEE_TYPE = BPCSACCT.FEE_TYPE;
        BPCACCTO.CI_NO = BPCSACCT.CI_NO;
        BPCACCTO.CLIENT_NAME = BPCSACCT.CLIENT_NAME;
        BPCACCTO.START_DT = BPCSACCT.START_DT;
        BPCACCTO.MATURITY_DT = BPCSACCT.MATURITY_DT;
        BPCACCTO.BR_OLD = BPCSACCT.BR_OLD;
        BPCACCTO.BR_NEW = BPCSACCT.BR_NEW;
        BPCACCTO.FCTR_STS = BPCSACCT.FSCH_STATUS;
        BPCACCTO.FSCH_STS = BPCSACCT.FCTR_STATUS;
        SCCFMT.FMTID = BPCSACCT.FMT_ID;
        SCCFMT.DATA_PTR = BPCACCTO;
        SCCFMT.DATA_LEN = 168;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R010_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        if (BPCRFCTR.INFO.FUNC == 'N') {
            WS_FMT_DATA.WS_CTA_NO = BPRFCTR.KEY.CTRT_NO;
            WS_FMT_DATA.WS_REL_CTA_NO = BPRFCTR.REL_CTRT_NO;
            WS_FMT_DATA.WS_FEE_TYPE = BPRFCTR.FEE_TYPE;
            WS_FMT_DATA.WS_BR = BPRFCTR.BOOK_CENTRE;
            WS_FMT_DATA.WS_PRODUCT_TYPE = BPRFCTR.PRD_TYPE;
            WS_FMT_DATA.WS_FEE_STATUS = BPRFCTR.FEE_STATUS;
            WS_FMT_DATA.WS_START_DATE = BPRFCTR.START_DATE;
            WS_FMT_DATA.WS_MATURITY_DATE = BPRFCTR.MATURITY_DATE;
            WS_FMT_DATA.WS_CI_NO = BPRFCTR.CI_NO;
            if (BPRFCTR.CI_NO.trim().length() > 0) {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = BPRFCTR.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
            }
            WS_FMT_DATA.WS_FEE_OPT = 'C';
            WS_FMT_DATA.WS_CLIENT_NAME = CICCUST.O_DATA.O_CI_NM;
        }
        if (BPCRFSCH.INFO.FUNC == 'N') {
            WS_FMT_DATA.WS_CTA_NO = BPRFSCH.KEY.CTRT_NO;
            WS_FMT_DATA.WS_REL_CTA_NO = BPRFSCH.REL_CTRT_NO;
            WS_FMT_DATA.WS_FEE_TYPE = BPRFSCH.FEE_TYPE;
            WS_FMT_DATA.WS_BR = BPRFSCH.BOOK_CENTRE;
            WS_FMT_DATA.WS_PRODUCT_TYPE = BPRFSCH.PRD_TYPE;
            WS_FMT_DATA.WS_FEE_STATUS = BPRFSCH.FEE_STATUS;
            WS_FMT_DATA.WS_START_DATE = BPRFSCH.START_DATE;
            WS_FMT_DATA.WS_MATURITY_DATE = BPRFSCH.MATURITY_DATE;
            WS_FMT_DATA.WS_CI_NO = BPRFSCH.CI_NO;
            if (BPRFSCH.CI_NO.trim().length() > 0) {
                IBS.init(SCCGWA, CICCUST);
                CICCUST.DATA.CI_NO = BPRFSCH.CI_NO;
                CICCUST.FUNC = 'C';
                S000_CALL_CIZCUST();
                if (pgmRtn) return;
            }
            WS_FMT_DATA.WS_FEE_OPT = 'S';
            WS_FMT_DATA.WS_CLIENT_NAME = CICCUST.O_DATA.O_CI_NM;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_FMT_DATA);
        SCCMPAG.DATA_LEN = 172;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R010_GET_FAMO_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRFAMO);
        IBS.init(SCCGWA, BPRFAMO);
        BPRFAMO.KEY.CTRT_NO = BPCSACCT.CTA_NO;
        BPCRFAMO.INFO.FUNC = 'Q';
        BPCRFAMO.INFO.POINTER = BPRFAMO;
        BPCRFAMO.INFO.REC_LEN = 155;
        S000_CALL_BPZRFAMO();
        if (pgmRtn) return;
        if (BPCRFAMO.RETURN_INFO == 'F' 
            || BPCRFAMO.RC.RC_CODE == 0) {
            WS_AMT = BPRFCTR.CHARGE_AMT - BPRFAMO.AMO_AMT_TOTAL;
        } else {
            WS_AMT = 0;
        }
    }
    public void R010_GET_ACCR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRACCR);
        IBS.init(SCCGWA, BPRPACCR);
        BPRPACCR.KEY.CTRT_NO = BPCSACCT.CTA_NO;
        BPCRACCR.INFO.FUNC = 'Q';
        BPCRACCR.INFO.POINTER = BPRPACCR;
        BPCRACCR.INFO.REC_LEN = 192;
        S000_CALL_BPZRACCR();
        if (pgmRtn) return;
        if (BPCRACCR.RETURN_INFO == 'F' 
            || BPCRACCR.RC.RC_CODE == 0) {
            WS_AMT = BPRPACCR.ACCRUAL_AMT_TOTAL;
        } else {
            WS_AMT = 0;
        }
    }
    public void R010_CHANGE_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOMMDP);
        IBS.init(SCCGWA, BPCNMMDP);
        IBS.init(SCCGWA, BPCUGMC);
        BPCUGMC.INFO.CHG_FLG = 'B';
        BPCUGMC.INFO.CNTR_TYPE = K_CTRT_TYPE_FEES;
        BPCUGMC.INFO.CCY_INFO[1-1].CCY = WS_CCY;
        BPCUGMC.INFO.CI_NO = BPCSACCT.CI_NO;
        BPCUGMC.INFO.AC_NO = BPCSACCT.CTA_NO;
        BPCUGMC.INFO.BR_OLD = BPCSACCT.BR_OLD;
        BPCUGMC.INFO.BR_NEW = BPCSACCT.BR_NEW;
        BPCOMMDP.PROD_TYPE = WS_PROD_CODE;
        BPCNMMDP.PROD_TYPE = WS_PROD_CODE;
        BPCUGMC.INFO.OTH_PTR_LEN = 21;
        BPCUGMC.INFO.OTH_PTR_OLD = BPCOMMDP;
        BPCUGMC.INFO.OTH_PTR_NEW = BPCNMMDP;
        if (BPCPQCTR.FLAG.CTRT_FLAG == 'S') {
            if (BPRFSCH.PAY_IND == 'R') {
                BPCUGMC.INFO.AMTS[17-1].AMT = WS_AMT;
            } else {
                BPCUGMC.INFO.AMTS[18-1].AMT = WS_AMT;
            }
        } else {
            if (BPRFCTR.PAYMENT_METHOD == 'P') {
                if (BPRFCTR.PAY_IND == 'R') {
                    BPCUGMC.INFO.AMTS[15-1].AMT = WS_AMT;
                } else {
                    BPCUGMC.INFO.AMTS[16-1].AMT = WS_AMT;
                }
            } else {
                if (BPRFCTR.PAY_IND == 'R') {
                    BPCUGMC.INFO.AMTS[17-1].AMT = WS_AMT;
                } else {
                    BPCUGMC.INFO.AMTS[18-1].AMT = WS_AMT;
                }
            }
        }
        CEP.TRC(SCCGWA, BPCUGMC.INFO.BR_OLD);
        CEP.TRC(SCCGWA, BPCUGMC.INFO.BR_NEW);
        CEP.TRC(SCCGWA, BPCUGMC.INFO.AMTS[15-1].AMT);
        CEP.TRC(SCCGWA, BPCUGMC.INFO.AMTS[16-1].AMT);
        CEP.TRC(SCCGWA, BPCUGMC.INFO.AMTS[17-1].AMT);
        CEP.TRC(SCCGWA, BPCUGMC.INFO.AMTS[18-1].AMT);
        S00_CALL_BPZUGMC();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUMENT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-MENT-MAINT", BPCUMENT);
        if (BPCUMENT.PROC_DATA.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUMENT.PROC_DATA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQCTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-FEECTRSCH", BPCPQCTR);
        CEP.TRC(SCCGWA, BPCPQCTR.RC);
        if (BPCPQCTR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQCTR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S010_CALL_BPZRFCTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEECTR", BPCRFCTR);
        CEP.TRC(SCCGWA, BPCRFSCH.RC);
        CEP.TRC(SCCGWA, BPCRFCTR.RETURN_INFO);
    }
    public void S020_CALL_BPZRFSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FEESCH", BPCRFSCH);
        CEP.TRC(SCCGWA, BPCRFSCH.RC);
        CEP.TRC(SCCGWA, BPCRFSCH.RETURN_INFO);
    }
    public void S000_CALL_BPZRFAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-FAMO", BPCRFAMO);
        CEP.TRC(SCCGWA, BPCRFSCH.RC);
        CEP.TRC(SCCGWA, BPCRFSCH.RETURN_INFO);
    }
    public void S000_CALL_BPZRACCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-ACCR", BPCRACCR);
        CEP.TRC(SCCGWA, BPCRACCR.RC);
        CEP.TRC(SCCGWA, BPCRACCR.RETURN_INFO);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC);
    }
    public void S00_CALL_BPZUGMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-GLM-CHANGE", BPCUGMC);
        if (BPCUGMC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUGMC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
