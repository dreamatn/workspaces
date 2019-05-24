package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSNFTP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_NONTERM_FTP = "BP-R-NONTERM-FTP";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "FTP PRODUCT INFO MAINTENANCE";
    int K_MAX_CNT = 500;
    int K_MAX_ROW = 50;
    String K_OUTPUT_FMT = "BP905";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSNFTP_WS_BRW_FTP_PROD WS_BRW_FTP_PROD = new BPZSNFTP_WS_BRW_FTP_PROD();
    BPZSNFTP_WS_FTP_PROD WS_FTP_PROD = new BPZSNFTP_WS_FTP_PROD();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCTNFTP BPCTNFTP = new BPCTNFTP();
    BPRNTRAT BPRNTRAT = new BPRNTRAT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHNFTP BPCONFTP = new BPCHNFTP();
    BPCHNFTP BPCNNFTP = new BPCHNFTP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSNFTP BPCSNFTP;
    public void MP(SCCGWA SCCGWA, BPCSNFTP BPCSNFTP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSNFTP = BPCSNFTP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSNFTP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSNFTP.FUNC == 'B') {
            B010_BROWSE_RECORDS();
            if (pgmRtn) return;
        } else if (BPCSNFTP.FUNC == 'A') {
            B020_ADD_RECORDS();
            if (pgmRtn) return;
        } else if (BPCSNFTP.FUNC == 'U') {
            B030_UPDATE_RECORD();
            if (pgmRtn) return;
        } else if (BPCSNFTP.FUNC == 'D') {
            B040_DELETE_RECORD();
            if (pgmRtn) return;
        } else if (BPCSNFTP.FUNC == 'I') {
            B050_INQUIRE_RECORD();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUN_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_BROWSE_RECORDS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTNFTP);
        IBS.init(SCCGWA, BPRNTRAT);
        BPCTNFTP.INFO.FUNC = 'B';
        BPCTNFTP.INFO.OPT = 'S';
        BPRNTRAT.KEY.PROD_TYP = BPCSNFTP.INFO.PROD_TYPE;
        BPRNTRAT.KEY.ACC_CENTER = BPCSNFTP.INFO.ACC_CENTER;
        BPRNTRAT.KEY.CURRENCY_CODE = BPCSNFTP.INFO.CURRENCY_CODE;
        BPRNTRAT.KEY.B_O_CODE = BPCSNFTP.INFO.BID_CD;
        BPRNTRAT.KEY.FTP_PRD_TYPE = BPCSNFTP.INFO.FTP_PROD_TYPE;
        BPCTNFTP.INFO.POINTER = BPRNTRAT;
        BPCTNFTP.LEN = 97;
        S000_CALL_BPZTNFTP();
        if (pgmRtn) return;
        B011_OUTPUT_TITLE();
        if (pgmRtn) return;
        BPCTNFTP.RETURN_INFO = ' ';
        IBS.init(SCCGWA, BPCTNFTP.RC);
        BPCTNFTP.INFO.FUNC = 'B';
        BPCTNFTP.INFO.OPT = 'N';
        S000_CALL_BPZTNFTP();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_CNT < K_MAX_CNT 
            && BPCTNFTP.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B012_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCTNFTP.RETURN_INFO = ' ';
            IBS.init(SCCGWA, BPCTNFTP.RC);
            BPCTNFTP.INFO.FUNC = 'B';
            BPCTNFTP.INFO.OPT = 'N';
            S000_CALL_BPZTNFTP();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCTNFTP.RC);
        BPCTNFTP.RETURN_INFO = ' ';
        BPCTNFTP.INFO.OPT = 'E';
        S000_CALL_BPZTNFTP();
        if (pgmRtn) return;
    }
    public void B011_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 63;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B012_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BRW_FTP_PROD);
        WS_BRW_FTP_PROD.WS_BRW_PROD_TYPE = BPRNTRAT.KEY.PROD_TYP;
        WS_BRW_FTP_PROD.WS_BRW_ACC_CENTER = BPRNTRAT.KEY.ACC_CENTER;
        WS_BRW_FTP_PROD.WS_BRW_CURRENCY_CODE = BPRNTRAT.KEY.CURRENCY_CODE;
        WS_BRW_FTP_PROD.WS_BRW_BID_CODE = BPRNTRAT.KEY.B_O_CODE;
        WS_BRW_FTP_PROD.WS_BRW_FTP_PROD_TYPE = BPRNTRAT.KEY.FTP_PRD_TYPE;
        WS_BRW_FTP_PROD.WS_BRW_FTP_RATE = BPRNTRAT.FTP_RATE;
        WS_BRW_FTP_PROD.WS_BRW_FTP_RATE_ADJ1 = BPRNTRAT.RATE_ADJ1;
        WS_BRW_FTP_PROD.WS_BRW_FTP_RATE_ADJ2 = BPRNTRAT.RATE_ADJ2;
        WS_BRW_FTP_PROD.WS_BRW_LAST_USER = BPRNTRAT.L_M_USER;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_BRW_FTP_PROD);
        SCCMPAG.DATA_LEN = 63;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_ADD_RECORDS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNTRAT);
        BPRNTRAT.KEY.PROD_TYP = BPCSNFTP.INFO.PROD_TYPE;
        BPRNTRAT.KEY.ACC_CENTER = BPCSNFTP.INFO.ACC_CENTER;
        BPRNTRAT.KEY.CURRENCY_CODE = BPCSNFTP.INFO.CURRENCY_CODE;
        BPRNTRAT.KEY.B_O_CODE = BPCSNFTP.INFO.BID_CD;
        BPRNTRAT.KEY.FTP_PRD_TYPE = BPCSNFTP.INFO.FTP_PROD_TYPE;
        BPRNTRAT.FTP_RATE = BPCSNFTP.INFO.FTP_RATE_CD;
        BPRNTRAT.RATE_ADJ1 = BPCSNFTP.INFO.FTP_RATE_ADJ1;
        BPRNTRAT.RATE_ADJ2 = BPCSNFTP.INFO.FTP_RATE_ADJ2;
        BPRNTRAT.L_M_USER = SCCGWA.COMM_AREA.TL_ID;
        BPCTNFTP.INFO.FUNC = 'A';
        BPCTNFTP.INFO.POINTER = BPRNTRAT;
        BPCTNFTP.LEN = 97;
        S000_CALL_BPZTNFTP();
        if (pgmRtn) return;
        if (BPCTNFTP.RC.RC_CODE != 0) {
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
        IBS.init(SCCGWA, WS_FTP_PROD);
        WS_FTP_PROD.WS_PROD_TYPE = BPRNTRAT.KEY.PROD_TYP;
        WS_FTP_PROD.WS_ACC_CENTER = BPRNTRAT.KEY.ACC_CENTER;
        WS_FTP_PROD.WS_CURRENCY_CODE = BPRNTRAT.KEY.CURRENCY_CODE;
        WS_FTP_PROD.WS_BID_CODE = BPRNTRAT.KEY.B_O_CODE;
        WS_FTP_PROD.WS_FTP_PROD_TYPE = BPRNTRAT.KEY.FTP_PRD_TYPE;
        WS_FTP_PROD.WS_FTP_RATE = BPRNTRAT.FTP_RATE;
        WS_FTP_PROD.WS_FTP_RATE_ADJ1 = BPRNTRAT.RATE_ADJ1;
        WS_FTP_PROD.WS_FTP_RATE_ADJ2 = BPRNTRAT.RATE_ADJ2;
        CEP.TRC(SCCGWA, BPRNTRAT.KEY.PROD_TYP);
        CEP.TRC(SCCGWA, BPRNTRAT.KEY.ACC_CENTER);
        CEP.TRC(SCCGWA, BPRNTRAT.KEY.CURRENCY_CODE);
        CEP.TRC(SCCGWA, BPRNTRAT.KEY.B_O_CODE);
        CEP.TRC(SCCGWA, BPRNTRAT.KEY.FTP_PRD_TYPE);
        CEP.TRC(SCCGWA, BPRNTRAT.FTP_RATE);
        CEP.TRC(SCCGWA, BPRNTRAT.RATE_ADJ1);
        CEP.TRC(SCCGWA, BPRNTRAT.RATE_ADJ2);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FTP_PROD;
        SCCFMT.DATA_LEN = 55;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_UPDATE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNTRAT);
        BPCNNFTP.PROD_TYP = BPCSNFTP.INFO.PROD_TYPE;
        BPCNNFTP.ACCT_CENTER = BPCSNFTP.INFO.ACC_CENTER;
        BPCNNFTP.CURRENCY_CODE = BPCSNFTP.INFO.CURRENCY_CODE;
        BPCNNFTP.B_O_CODE = BPCSNFTP.INFO.BID_CD;
        BPCNNFTP.FTP_PROD_TYPE = BPCSNFTP.INFO.FTP_PROD_TYPE;
        BPCNNFTP.FTP_RATE = BPCSNFTP.INFO.FTP_RATE_CD;
        BPCNNFTP.FTP_RATE_ADJ1 = BPCSNFTP.INFO.FTP_RATE_ADJ1;
        BPCNNFTP.FTP_RATE_ADJ2 = BPCSNFTP.INFO.FTP_RATE_ADJ2;
        BPRNTRAT.KEY.PROD_TYP = BPCSNFTP.INFO.PROD_TYPE;
        BPRNTRAT.KEY.ACC_CENTER = BPCSNFTP.INFO.ACC_CENTER;
        BPRNTRAT.KEY.CURRENCY_CODE = BPCSNFTP.INFO.CURRENCY_CODE;
        BPRNTRAT.KEY.B_O_CODE = BPCSNFTP.INFO.BID_CD;
        BPRNTRAT.KEY.FTP_PRD_TYPE = BPCSNFTP.INFO.FTP_PROD_TYPE;
        BPCTNFTP.INFO.FUNC = 'R';
        BPCTNFTP.INFO.POINTER = BPRNTRAT;
        BPCTNFTP.LEN = 97;
        S000_CALL_BPZTNFTP();
        if (pgmRtn) return;
        if (BPCTNFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSNFTP.INFO.FTP_RATE_CD.equalsIgnoreCase(BPRNTRAT.FTP_RATE) 
            && BPCSNFTP.INFO.FTP_RATE_ADJ1 == BPRNTRAT.RATE_ADJ1 
            && BPCSNFTP.INFO.FTP_RATE_ADJ2 == BPRNTRAT.RATE_ADJ2) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_DATA_NOT_CHG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCONFTP.PROD_TYP = BPRNTRAT.KEY.PROD_TYP;
        BPCONFTP.ACCT_CENTER = BPRNTRAT.KEY.ACC_CENTER;
        BPCONFTP.CURRENCY_CODE = BPRNTRAT.KEY.CURRENCY_CODE;
        BPCONFTP.B_O_CODE = BPRNTRAT.KEY.B_O_CODE;
        BPCONFTP.FTP_PROD_TYPE = BPRNTRAT.KEY.FTP_PRD_TYPE;
        BPCONFTP.FTP_RATE = BPRNTRAT.FTP_RATE;
        BPCONFTP.FTP_RATE_ADJ1 = BPRNTRAT.RATE_ADJ1;
        BPCONFTP.FTP_RATE_ADJ2 = BPRNTRAT.RATE_ADJ2;
        BPRNTRAT.KEY.PROD_TYP = BPCSNFTP.INFO.PROD_TYPE;
        BPRNTRAT.KEY.ACC_CENTER = BPCSNFTP.INFO.ACC_CENTER;
        BPRNTRAT.KEY.CURRENCY_CODE = BPCSNFTP.INFO.CURRENCY_CODE;
        BPRNTRAT.KEY.B_O_CODE = BPCSNFTP.INFO.BID_CD;
        BPRNTRAT.KEY.FTP_PRD_TYPE = BPCSNFTP.INFO.FTP_PROD_TYPE;
        BPRNTRAT.FTP_RATE = BPCSNFTP.INFO.FTP_RATE_CD;
        BPRNTRAT.RATE_ADJ1 = BPCSNFTP.INFO.FTP_RATE_ADJ1;
        BPRNTRAT.RATE_ADJ2 = BPCSNFTP.INFO.FTP_RATE_ADJ2;
        BPRNTRAT.L_M_USER = SCCGWA.COMM_AREA.TL_ID;
        BPCTNFTP.INFO.FUNC = 'U';
        BPCTNFTP.INFO.POINTER = BPRNTRAT;
        BPCTNFTP.LEN = 97;
        S000_CALL_BPZTNFTP();
        if (pgmRtn) return;
        if (BPCTNFTP.RC.RC_CODE != 0) {
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
        IBS.init(SCCGWA, BPRNTRAT);
        BPRNTRAT.KEY.PROD_TYP = BPCSNFTP.INFO.PROD_TYPE;
        BPRNTRAT.KEY.ACC_CENTER = BPCSNFTP.INFO.ACC_CENTER;
        BPRNTRAT.KEY.CURRENCY_CODE = BPCSNFTP.INFO.CURRENCY_CODE;
        BPRNTRAT.KEY.B_O_CODE = BPCSNFTP.INFO.BID_CD;
        BPRNTRAT.KEY.FTP_PRD_TYPE = BPCSNFTP.INFO.FTP_PROD_TYPE;
        BPCTNFTP.INFO.FUNC = 'I';
        BPCTNFTP.INFO.POINTER = BPRNTRAT;
        BPCTNFTP.LEN = 97;
        S000_CALL_BPZTNFTP();
        if (pgmRtn) return;
        if (BPCTNFTP.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_ALREADY_DELETED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCTNFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INQ_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRNTRAT.KEY.PROD_TYP = BPCSNFTP.INFO.PROD_TYPE;
        BPRNTRAT.KEY.ACC_CENTER = BPCSNFTP.INFO.ACC_CENTER;
        BPRNTRAT.KEY.CURRENCY_CODE = BPCSNFTP.INFO.CURRENCY_CODE;
        BPRNTRAT.KEY.B_O_CODE = BPCSNFTP.INFO.BID_CD;
        BPRNTRAT.KEY.FTP_PRD_TYPE = BPCSNFTP.INFO.FTP_PROD_TYPE;
        BPCTNFTP.INFO.FUNC = 'D';
        BPCTNFTP.INFO.POINTER = BPRNTRAT;
        BPCTNFTP.LEN = 97;
        S000_CALL_BPZTNFTP();
        if (pgmRtn) return;
        if (BPCTNFTP.RC.RC_CODE != 0) {
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
        IBS.init(SCCGWA, BPRNTRAT);
        BPRNTRAT.KEY.PROD_TYP = BPCSNFTP.INFO.PROD_TYPE;
        BPRNTRAT.KEY.ACC_CENTER = BPCSNFTP.INFO.ACC_CENTER;
        BPRNTRAT.KEY.CURRENCY_CODE = BPCSNFTP.INFO.CURRENCY_CODE;
        BPRNTRAT.KEY.B_O_CODE = BPCSNFTP.INFO.BID_CD;
        BPRNTRAT.KEY.FTP_PRD_TYPE = BPCSNFTP.INFO.FTP_PROD_TYPE;
        BPCTNFTP.INFO.FUNC = 'I';
        BPCTNFTP.INFO.POINTER = BPRNTRAT;
        BPCTNFTP.LEN = 97;
        S000_CALL_BPZTNFTP();
        if (pgmRtn) return;
        if (BPCTNFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INQ_DB_DATA_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B021_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZTNFTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_NONTERM_FTP, BPCTNFTP);
        if (BPCTNFTP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTNFTP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S020_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSNFTP.FUNC == 'A' 
            || BPCSNFTP.FUNC == 'D') {
            S020_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSNFTP.FUNC == 'U') {
            S020_02_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S020_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSNFTP.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSNFTP.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.FMT_ID = "BPCHNFTP";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "BPFTPINF";
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S020_02_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "BPCHNFTP";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "BPFTPINFO";
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPCONFTP;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCNNFTP;
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
