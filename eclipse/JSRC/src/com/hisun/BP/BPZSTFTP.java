package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTFTP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_TERM_FTP = "BP-R-TERM-FTP";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "TERM FTP RATE MAINTENANCE";
    int K_MAX_CNT = 500;
    int K_MAX_ROW = 99;
    String K_OUTPUT_FMT = "BP904";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSTFTP_WS_BRW_FTP_RATE WS_BRW_FTP_RATE = new BPZSTFTP_WS_BRW_FTP_RATE();
    BPZSTFTP_WS_FTP_RATE WS_FTP_RATE = new BPZSTFTP_WS_FTP_RATE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCTTFTP BPCTTFTP = new BPCTTFTP();
    BPRTRATE BPRTRATE = new BPRTRATE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHTFTP BPCOTFTP = new BPCHTFTP();
    BPCHTFTP BPCNTFTP = new BPCHTFTP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSTFTP BPCSTFTP;
    public void MP(SCCGWA SCCGWA, BPCSTFTP BPCSTFTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTFTP = BPCSTFTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSTFTP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSTFTP.FUNC == 'B') {
            B010_BROWSE_RECORDS();
            if (pgmRtn) return;
        } else if (BPCSTFTP.FUNC == 'A') {
            B020_ADD_RECORDS();
            if (pgmRtn) return;
        } else if (BPCSTFTP.FUNC == 'U') {
            B030_UPDATE_RECORD();
            if (pgmRtn) return;
        } else if (BPCSTFTP.FUNC == 'D') {
            B040_DELETE_RECORD();
            if (pgmRtn) return;
        } else if (BPCSTFTP.FUNC == 'I') {
            B050_INQUIRE_RECORD();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUN_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_RECORDS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTTFTP);
        IBS.init(SCCGWA, BPRTRATE);
        BPCTTFTP.INFO.FUNC = 'B';
        BPCTTFTP.INFO.OPT = 'S';
        if (BPCSTFTP.INFO.CURRENCY_CODE.trim().length() == 0 
            && BPCSTFTP.INFO.TENOR_CD.trim().length() == 0) {
            BPCTTFTP.INFO.INDEX_FLG = '1';
        } else {
            BPCTTFTP.INFO.INDEX_FLG = '2';
            BPRTRATE.KEY.CURRENCY_CODE = BPCSTFTP.INFO.CURRENCY_CODE;
            BPRTRATE.KEY.TENOR_CD = BPCSTFTP.INFO.TENOR_CD;
        }
        BPCTTFTP.INFO.POINTER = BPRTRATE;
        BPCTTFTP.LEN = 189;
        S000_CALL_BPZTTFTP();
        if (pgmRtn) return;
        B011_OUTPUT_TITLE();
        if (pgmRtn) return;
        BPCTTFTP.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCTTFTP.RC);
        BPCTTFTP.INFO.FUNC = 'B';
        BPCTTFTP.INFO.OPT = 'N';
        S000_CALL_BPZTTFTP();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT < K_MAX_CNT 
            && SCCMPAG.FUNC != 'E' 
            && BPCTTFTP.RETURN_INFO != 'N'; WS_CNT += 1) {
            B012_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCTTFTP.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCTTFTP.RC);
            BPCTTFTP.INFO.FUNC = 'B';
            BPCTTFTP.INFO.OPT = 'N';
            S000_CALL_BPZTTFTP();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCTTFTP.RC);
        BPCTTFTP.RETURN_INFO = ' ';
        BPCTTFTP.INFO.OPT = 'E';
        S000_CALL_BPZTTFTP();
        if (pgmRtn) return;
    }
    public void B011_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 15;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B012_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_FTP_RATE);
        WS_BRW_FTP_RATE.WS_BRW_CURRENCY_CODE = BPRTRATE.KEY.CURRENCY_CODE;
        WS_BRW_FTP_RATE.WS_BRW_TENOR_CODE = BPRTRATE.KEY.TENOR_CD;
        WS_BRW_FTP_RATE.WS_BRW_LAST_USER = BPRTRATE.L_M_USER;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_FTP_RATE);
        SCCMPAG.DATA_LEN = 15;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORDS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTRATE);
        BPRTRATE.KEY.CURRENCY_CODE = BPCSTFTP.INFO.CURRENCY_CODE;
        BPRTRATE.KEY.TENOR_CD = BPCSTFTP.INFO.TENOR_CD;
        BPRTRATE.FTP_O_RATE = BPCSTFTP.INFO.O_RATE;
        BPRTRATE.FTP_O_RATE_ADJ1 = BPCSTFTP.INFO.O_RATE_ADJ1;
        BPRTRATE.FTP_O_RATE_ADJ2 = BPCSTFTP.INFO.O_RATE_ADJ2;
        BPRTRATE.FTP_O_RATE_ADJ3 = BPCSTFTP.INFO.O_RATE_ADJ3;
        BPRTRATE.FTP_O_RATE_ADJ4 = BPCSTFTP.INFO.O_RATE_ADJ4;
        BPRTRATE.FTP_O_RATE_ADJ5 = BPCSTFTP.INFO.O_RATE_ADJ5;
        BPRTRATE.FTP_B_RATE = BPCSTFTP.INFO.B_RATE;
        BPRTRATE.FTP_B_RATE_ADJ1 = BPCSTFTP.INFO.B_RATE_ADJ1;
        BPRTRATE.FTP_B_RATE_ADJ2 = BPCSTFTP.INFO.B_RATE_ADJ2;
        BPRTRATE.FTP_B_RATE_ADJ3 = BPCSTFTP.INFO.B_RATE_ADJ3;
        BPRTRATE.FTP_B_RATE_ADJ4 = BPCSTFTP.INFO.B_RATE_ADJ4;
        BPRTRATE.FTP_B_RATE_ADJ5 = BPCSTFTP.INFO.B_RATE_ADJ5;
        BPRTRATE.FTP_B_RATE_ADJ5 = BPCSTFTP.INFO.B_RATE_ADJ5;
        BPRTRATE.L_M_USER = SCCGWA.COMM_AREA.TL_ID;
        BPCTTFTP.INFO.FUNC = 'A';
        BPCTTFTP.INFO.POINTER = BPRTRATE;
        BPCTTFTP.LEN = 189;
        S000_CALL_BPZTTFTP();
        if (pgmRtn) return;
        if (BPCTTFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ADD_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
        B021_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B021_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FTP_RATE);
        WS_FTP_RATE.WS_CURRENCY_CODE = BPRTRATE.KEY.CURRENCY_CODE;
        WS_FTP_RATE.WS_TENOR_CODE = BPRTRATE.KEY.TENOR_CD;
        WS_FTP_RATE.WS_O_RATE = BPRTRATE.FTP_O_RATE;
        WS_FTP_RATE.WS_O_RATE_ADJ1 = BPRTRATE.FTP_O_RATE_ADJ1;
        WS_FTP_RATE.WS_O_RATE_ADJ2 = BPRTRATE.FTP_O_RATE_ADJ2;
        WS_FTP_RATE.WS_O_RATE_ADJ3 = BPRTRATE.FTP_O_RATE_ADJ3;
        WS_FTP_RATE.WS_O_RATE_ADJ4 = BPRTRATE.FTP_O_RATE_ADJ4;
        WS_FTP_RATE.WS_O_RATE_ADJ5 = BPRTRATE.FTP_O_RATE_ADJ5;
        WS_FTP_RATE.WS_B_RATE = BPRTRATE.FTP_B_RATE;
        WS_FTP_RATE.WS_B_RATE_ADJ1 = BPRTRATE.FTP_B_RATE_ADJ1;
        WS_FTP_RATE.WS_B_RATE_ADJ2 = BPRTRATE.FTP_B_RATE_ADJ2;
        WS_FTP_RATE.WS_B_RATE_ADJ3 = BPRTRATE.FTP_B_RATE_ADJ3;
        WS_FTP_RATE.WS_B_RATE_ADJ4 = BPRTRATE.FTP_B_RATE_ADJ4;
        WS_FTP_RATE.WS_B_RATE_ADJ5 = BPRTRATE.FTP_B_RATE_ADJ5;
        WS_FTP_RATE.WS_LAST_USR = BPRTRATE.L_M_USER;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FTP_RATE;
        SCCFMT.DATA_LEN = 155;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_UPDATE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTRATE);
        IBS.init(SCCGWA, BPCNTFTP);
        IBS.init(SCCGWA, BPCOTFTP);
        BPCNTFTP.CURRENCY_CODE = BPCSTFTP.INFO.CURRENCY_CODE;
        BPCNTFTP.TENOR_CD = BPCSTFTP.INFO.TENOR_CD;
        BPCNTFTP.FTP_O_RATE = BPCSTFTP.INFO.O_RATE;
        BPCNTFTP.FTP_O_RATE_ADJ1 = BPCSTFTP.INFO.O_RATE_ADJ1;
        BPCNTFTP.FTP_O_RATE_ADJ2 = BPCSTFTP.INFO.O_RATE_ADJ2;
        BPCNTFTP.FTP_O_RATE_ADJ3 = BPCSTFTP.INFO.O_RATE_ADJ3;
        BPCNTFTP.FTP_O_RATE_ADJ4 = BPCSTFTP.INFO.O_RATE_ADJ4;
        BPCNTFTP.FTP_O_RATE_ADJ5 = BPCSTFTP.INFO.O_RATE_ADJ5;
        BPCNTFTP.FTP_B_RATE = BPCSTFTP.INFO.B_RATE;
        BPCNTFTP.FTP_B_RATE_ADJ1 = BPCSTFTP.INFO.B_RATE_ADJ1;
        BPCNTFTP.FTP_B_RATE_ADJ2 = BPCSTFTP.INFO.B_RATE_ADJ2;
        BPCNTFTP.FTP_B_RATE_ADJ3 = BPCSTFTP.INFO.B_RATE_ADJ3;
        BPCNTFTP.FTP_B_RATE_ADJ4 = BPCSTFTP.INFO.B_RATE_ADJ4;
        BPCNTFTP.FTP_B_RATE_ADJ5 = BPCSTFTP.INFO.B_RATE_ADJ5;
        BPRTRATE.KEY.CURRENCY_CODE = BPCSTFTP.INFO.CURRENCY_CODE;
        BPRTRATE.KEY.TENOR_CD = BPCSTFTP.INFO.TENOR_CD;
        BPCTTFTP.INFO.FUNC = 'R';
        BPCTTFTP.INFO.POINTER = BPRTRATE;
        BPCTTFTP.LEN = 189;
        S000_CALL_BPZTTFTP();
        if (pgmRtn) return;
        if (BPCTTFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSTFTP.INFO.O_RATE.equalsIgnoreCase(BPRTRATE.FTP_O_RATE) 
            && BPCSTFTP.INFO.O_RATE_ADJ1 == BPRTRATE.FTP_O_RATE_ADJ1 
            && BPCSTFTP.INFO.O_RATE_ADJ2 == BPRTRATE.FTP_O_RATE_ADJ2 
            && BPCSTFTP.INFO.O_RATE_ADJ3 == BPRTRATE.FTP_O_RATE_ADJ3 
            && BPCSTFTP.INFO.O_RATE_ADJ4 == BPRTRATE.FTP_O_RATE_ADJ4 
            && BPCSTFTP.INFO.O_RATE_ADJ5 == BPRTRATE.FTP_O_RATE_ADJ5 
            && BPCSTFTP.INFO.B_RATE.equalsIgnoreCase(BPRTRATE.FTP_B_RATE) 
            && BPCSTFTP.INFO.B_RATE_ADJ1 == BPRTRATE.FTP_B_RATE_ADJ1 
            && BPCSTFTP.INFO.B_RATE_ADJ2 == BPRTRATE.FTP_B_RATE_ADJ2 
            && BPCSTFTP.INFO.B_RATE_ADJ3 == BPRTRATE.FTP_B_RATE_ADJ3 
            && BPCSTFTP.INFO.B_RATE_ADJ4 == BPRTRATE.FTP_B_RATE_ADJ4 
            && BPCSTFTP.INFO.B_RATE_ADJ5 == BPRTRATE.FTP_B_RATE_ADJ5) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_DATA_NOT_CHG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCOTFTP.CURRENCY_CODE = BPRTRATE.KEY.CURRENCY_CODE;
        BPCOTFTP.TENOR_CD = BPRTRATE.KEY.TENOR_CD;
        BPCOTFTP.FTP_O_RATE = BPRTRATE.FTP_O_RATE;
        BPCOTFTP.FTP_O_RATE_ADJ1 = BPRTRATE.FTP_O_RATE_ADJ1;
        BPCOTFTP.FTP_O_RATE_ADJ2 = BPRTRATE.FTP_O_RATE_ADJ2;
        BPCOTFTP.FTP_O_RATE_ADJ3 = BPRTRATE.FTP_O_RATE_ADJ3;
        BPCOTFTP.FTP_O_RATE_ADJ4 = BPRTRATE.FTP_O_RATE_ADJ4;
        BPCOTFTP.FTP_O_RATE_ADJ5 = BPRTRATE.FTP_O_RATE_ADJ5;
        BPCOTFTP.FTP_B_RATE = BPRTRATE.FTP_B_RATE;
        BPCOTFTP.FTP_B_RATE_ADJ1 = BPRTRATE.FTP_B_RATE_ADJ1;
        BPCOTFTP.FTP_B_RATE_ADJ2 = BPRTRATE.FTP_B_RATE_ADJ2;
        BPCOTFTP.FTP_B_RATE_ADJ3 = BPRTRATE.FTP_B_RATE_ADJ3;
        BPCOTFTP.FTP_B_RATE_ADJ4 = BPRTRATE.FTP_B_RATE_ADJ4;
        BPCOTFTP.FTP_B_RATE_ADJ5 = BPRTRATE.FTP_B_RATE_ADJ5;
        BPRTRATE.KEY.CURRENCY_CODE = BPCSTFTP.INFO.CURRENCY_CODE;
        BPRTRATE.KEY.TENOR_CD = BPCSTFTP.INFO.TENOR_CD;
        BPRTRATE.FTP_O_RATE = BPCSTFTP.INFO.O_RATE;
        BPRTRATE.FTP_O_RATE_ADJ1 = BPCSTFTP.INFO.O_RATE_ADJ1;
        BPRTRATE.FTP_O_RATE_ADJ2 = BPCSTFTP.INFO.O_RATE_ADJ2;
        BPRTRATE.FTP_O_RATE_ADJ3 = BPCSTFTP.INFO.O_RATE_ADJ3;
        BPRTRATE.FTP_O_RATE_ADJ4 = BPCSTFTP.INFO.O_RATE_ADJ4;
        BPRTRATE.FTP_O_RATE_ADJ5 = BPCSTFTP.INFO.O_RATE_ADJ5;
        BPRTRATE.FTP_B_RATE = BPCSTFTP.INFO.B_RATE;
        BPRTRATE.FTP_B_RATE_ADJ1 = BPCSTFTP.INFO.B_RATE_ADJ1;
        BPRTRATE.FTP_B_RATE_ADJ2 = BPCSTFTP.INFO.B_RATE_ADJ2;
        BPRTRATE.FTP_B_RATE_ADJ3 = BPCSTFTP.INFO.B_RATE_ADJ3;
        BPRTRATE.FTP_B_RATE_ADJ4 = BPCSTFTP.INFO.B_RATE_ADJ4;
        BPRTRATE.FTP_B_RATE_ADJ5 = BPCSTFTP.INFO.B_RATE_ADJ5;
        BPRTRATE.L_M_USER = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, "KIAKIA");
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.CURRENCY_CODE);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.TENOR_CD);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE_ADJ1);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE_ADJ2);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE_ADJ3);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE_ADJ4);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.O_RATE_ADJ5);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE_ADJ1);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE_ADJ2);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE_ADJ3);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE_ADJ4);
        CEP.TRC(SCCGWA, BPCSTFTP.INFO.B_RATE_ADJ5);
        CEP.TRC(SCCGWA, BPRTRATE.KEY.CURRENCY_CODE);
        CEP.TRC(SCCGWA, BPRTRATE.KEY.TENOR_CD);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_O_RATE);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_O_RATE_ADJ1);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_O_RATE_ADJ2);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_O_RATE_ADJ3);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_O_RATE_ADJ4);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_O_RATE_ADJ5);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_B_RATE);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_B_RATE_ADJ1);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_B_RATE_ADJ2);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_B_RATE_ADJ3);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_B_RATE_ADJ4);
        CEP.TRC(SCCGWA, BPRTRATE.FTP_B_RATE_ADJ5);
        CEP.TRC(SCCGWA, BPRTRATE.L_M_USER);
        BPCTTFTP.INFO.FUNC = 'U';
        BPCTTFTP.INFO.POINTER = BPRTRATE;
        BPCTTFTP.LEN = 189;
        S000_CALL_BPZTTFTP();
        if (pgmRtn) return;
        if (BPCTTFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
        B021_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_DELETE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTRATE);
        BPRTRATE.KEY.CURRENCY_CODE = BPCSTFTP.INFO.CURRENCY_CODE;
        BPRTRATE.KEY.TENOR_CD = BPCSTFTP.INFO.TENOR_CD;
        BPCTTFTP.INFO.FUNC = 'I';
        BPCTTFTP.INFO.POINTER = BPRTRATE;
        BPCTTFTP.LEN = 189;
        S000_CALL_BPZTTFTP();
        if (pgmRtn) return;
        if (BPCTTFTP.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_ALREADY_DELETED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCTTFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INQ_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRTRATE.KEY.CURRENCY_CODE = BPCSTFTP.INFO.CURRENCY_CODE;
        BPRTRATE.KEY.TENOR_CD = BPCSTFTP.INFO.TENOR_CD;
        BPCTTFTP.INFO.FUNC = 'D';
        BPCTTFTP.INFO.POINTER = BPRTRATE;
        BPCTTFTP.LEN = 189;
        S000_CALL_BPZTTFTP();
        if (pgmRtn) return;
        if (BPCTTFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DEL_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
        B021_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B050_INQUIRE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTRATE);
        BPRTRATE.KEY.CURRENCY_CODE = BPCSTFTP.INFO.CURRENCY_CODE;
        BPRTRATE.KEY.TENOR_CD = BPCSTFTP.INFO.TENOR_CD;
        BPCTTFTP.INFO.FUNC = 'I';
        BPCTTFTP.INFO.POINTER = BPRTRATE;
        BPCTTFTP.LEN = 189;
        S000_CALL_BPZTTFTP();
        if (pgmRtn) return;
        if (BPCTTFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INQ_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B021_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZTTFTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_TERM_FTP, BPCTTFTP);
        if (BPCTTFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTTFTP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S020_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSTFTP.FUNC == 'A' 
            || BPCSTFTP.FUNC == 'D') {
            S020_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSTFTP.FUNC == 'U') {
            S020_02_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S020_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSTFTP.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSTFTP.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.FMT_ID = "BPCHTFTP";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "BPTERMFTP";
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S020_02_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "BPCHTFTP";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "BPTERMFTP";
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPCOTFTP;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCNTFTP;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
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
