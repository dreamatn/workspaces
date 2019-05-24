package com.hisun.SM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPRDSTR;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class SMZSDSTR {
    boolean pgmRtn = false;
    short K_ITM_CNT = 150;
    short K_Q_MAX_CNT = 5000;
    String K_NEXT_TXN_FMT = "SMX01";
    String CPN_DSTR_MAINTAIN = "SM-R_DSTR_MAINTAIN  ";
    String CPN_DSTR_BROWSE = "SM-U_DSTR_BROWSE    ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    char WS_END_FLG = ' ';
    short WS_LEN = 0;
    short WS_TOT_NUM = 0;
    short WS_TS_CNT = 0;
    String WS_TS_REC = " ";
    SMZSDSTR_WS_TS_HEAD WS_TS_HEAD = new SMZSDSTR_WS_TS_HEAD();
    SMZSDSTR_WS_TMP_TEXT WS_TMP_TEXT = new SMZSDSTR_WS_TMP_TEXT();
    SMZSDSTR_WS_ITM_TEXT WS_ITM_TEXT = new SMZSDSTR_WS_ITM_TEXT();
    char WS_TBL_BANK_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SMCX1374 SMCX1374 = new SMCX1374();
    SMCTSTRM SMCTSTRM = new SMCTSTRM();
    SMCTSTRB SMCTSTRB = new SMCTSTRB();
    SMCOSTRO SMCOSTRO = new SMCOSTRO();
    BPRDSTR BPRDSTR = new BPRDSTR();
    SCCGWA SCCGWA;
    SMCOSTRM SMCOSTRM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, SMCOSTRM SMCOSTRM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCOSTRM = SMCOSTRM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZSDSTR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRDSTR);
        IBS.init(SCCGWA, SMCTSTRM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SMCOSTRM.FUNC == 'I') {
            B010_INQUIRE_PROCESS();
            if (pgmRtn) return;
            R020_TRANS_DATA_INTERFACE();
            if (pgmRtn) return;
        } else if (SMCOSTRM.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            R100_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (SMCOSTRM.FUNC == 'U') {
            B030_UPDATE_PROCESS();
            if (pgmRtn) return;
            R100_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (SMCOSTRM.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            R100_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (SMCOSTRM.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, SMCOSTRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B090_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_INQUIRE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDSTR);
        BPRDSTR.KEY.NAME = SMCOSTRM.NAME;
        SMCTSTRM.INFO.FUNC = 'Q';
        SMCTSTRM.INFO.POINTER = BPRDSTR;
        SMCTSTRM.INFO.LEN = 138;
        S000_CALL_SMZTSTRM();
        if (pgmRtn) return;
        if (SMCTSTRM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_DSTR_NOTFND, SMCOSTRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDSTR);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        SMCTSTRM.INFO.POINTER = BPRDSTR;
        SMCTSTRM.INFO.LEN = 138;
        SMCTSTRM.INFO.FUNC = 'C';
        S000_CALL_SMZTSTRM();
        if (pgmRtn) return;
        if (SMCTSTRM.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_DSTR_EXIST, SMCOSTRM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R100_HISTORY_RECORD() throws IOException,SQLException,Exception {
    }
    public void B030_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDSTR);
        SMCTSTRM.INFO.FUNC = 'R';
        BPRDSTR.KEY.NAME = SMCOSTRM.NAME;
        SMCTSTRM.INFO.POINTER = BPRDSTR;
        SMCTSTRM.INFO.LEN = 138;
        S000_CALL_SMZTSTRM();
        if (pgmRtn) return;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        SMCTSTRM.INFO.FUNC = 'U';
        SMCTSTRM.INFO.POINTER = BPRDSTR;
        SMCTSTRM.INFO.LEN = 138;
        S000_CALL_SMZTSTRM();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDSTR);
        SMCTSTRM.INFO.FUNC = 'R';
        BPRDSTR.KEY.NAME = SMCOSTRM.NAME;
        SMCTSTRM.INFO.POINTER = BPRDSTR;
        SMCTSTRM.INFO.LEN = 138;
        S000_CALL_SMZTSTRM();
        if (pgmRtn) return;
        SMCTSTRM.INFO.FUNC = 'D';
        SMCTSTRM.INFO.POINTER = BPRDSTR;
        SMCTSTRM.INFO.LEN = 138;
        S000_CALL_SMZTSTRM();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_SMZTSTRB();
        if (pgmRtn) return;
    }
    public void B090_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (SMCOSTRM.OUTPUT_FMT.equalsIgnoreCase(K_NEXT_TXN_FMT)) {
            if (SMCOSTRM.FUNC == 'I') {
                R001_TRANS_DATA_N_OUTPUT();
                if (pgmRtn) return;
            } else if (SMCOSTRM.FUNC == 'U') {
                R002_TRANS_DATA_N_OUTPUT();
                if (pgmRtn) return;
                IBS.init(SCCGWA, SCCFMT);
                SCCFMT.FMTID = SMCOSTRM.OUTPUT_FMT;
                SCCFMT.DATA_PTR = SMCX1374;
                SCCFMT.DATA_LEN = 8;
                IBS.FMT(SCCGWA, SCCFMT);
            } else if (SMCOSTRM.FUNC == 'B') {
            } else {
            }
        } else {
            R010_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = SMCOSTRM.OUTPUT_FMT;
            SCCFMT.DATA_PTR = SMCOSTRO;
            SCCFMT.DATA_LEN = 9556;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R500_GET_MAX_ITEM() throws IOException,SQLException,Exception {
        WS_END_FLG = 'N';
        WS_I = 1;
        while (WS_END_FLG != 'Y' 
            && WS_I <= K_ITM_CNT) {
            if (BPRDSTR.REDEFINES19.REDEFINES21.ITM_TEXT[WS_I-1].ITM_NO == 0) {
                WS_END_FLG = 'Y';
            } else {
                WS_I += 1;
            }
        }
        if (WS_END_FLG == 'N') {
            WS_ERR_MSG = SMCMSG_ERROR_MSG.SM_DETAILS_REC_EXCEED;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void R200_DETAILS_MAINT() throws IOException,SQLException,Exception {
        WS_TMP_TEXT.WS_TMP_NO = SMCOSTRM.REDEFINES26.REDEFINES29.ITM_TEXT[1-1].ITM_NO;
        WS_TMP_TEXT.WS_TMP_LVL = SMCOSTRM.REDEFINES26.REDEFINES29.ITM_TEXT[1-1].ITM_LVL;
        WS_TMP_TEXT.WS_TMP_NAME = SMCOSTRM.REDEFINES26.REDEFINES29.ITM_TEXT[1-1].ITM_NAME;
        WS_TMP_TEXT.WS_TMP_DAT = SMCOSTRM.REDEFINES26.REDEFINES29.ITM_TEXT[1-1].ITM_DAT;
        WS_TMP_TEXT.WS_TMP_IND = SMCOSTRM.REDEFINES26.REDEFINES29.ITM_TEXT[1-1].ITM_IND;
        WS_TMP_TEXT.WS_TMP_CYC = SMCOSTRM.REDEFINES26.REDEFINES29.ITM_TEXT[1-1].ITM_CYC;
        WS_TMP_TEXT.WS_TMP_RDID = SMCOSTRM.REDEFINES26.REDEFINES29.ITM_TEXT[1-1].ITM_RDID;
        WS_TMP_TEXT.WS_TMP_VALUE = SMCOSTRM.REDEFINES26.REDEFINES29.ITM_TEXT[1-1].ITM_VALUE;
        WS_TMP_TEXT.WS_TMP_STYPE = SMCOSTRM.REDEFINES26.REDEFINES29.ITM_TEXT[1-1].ITM_STYPE;
