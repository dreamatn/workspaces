package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSICHQ {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm DDTCHQ_BR = new brParm();
    DBParm DDTMST_RD;
    DBParm DDTPAID_RD;
    DBParm DDTCCY_RD;
    String CPN_DD_S_SIGN_PROC = "DD-S-SIGN-PROC";
    String K_OUTPUT_FMT = "DD402";
    String WS_ERR_MSG = " ";
    long WS_CUR_POS = 0;
    long WS_SICHQ_CHQ_NO = 0;
    long WS_CHQ_STR_CHQ_NO = 0;
    char WS_FLG = ' ';
    String WS_CHQ_NO = " ";
    int WS_LEN = 0;
    char WS_CHQ_FLG = ' ';
    char WS_PAID_FLG = ' ';
    char WS_CCY_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDCOICHQ DDCOICHQ = new DDCOICHQ();
    CICQACAC CICQACAC = new CICQACAC();
    DDRCCY DDRCCY = new DDRCCY();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRMST DDRMST = new DDRMST();
    DDRPAID DDRPAID = new DDRPAID();
    SCCGWA SCCGWA;
    DDCSICHQ DDCSICHQ;
    public void MP(SCCGWA SCCGWA, DDCSICHQ DDCSICHQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSICHQ = DDCSICHQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSICHQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_QUERY_CHQB_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSICHQ.CHQ_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (DDCSICHQ.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'C';
            CICQACAC.DATA.AGR_NO = DDCSICHQ.AC;
            CICQACAC.DATA.CCY_ACAC = "156";
            CICQACAC.DATA.CR_FLG = '1';
            S000_CALL_CICQACAC();
        }
    }
    public void B020_QUERY_CHQB_PROC() throws IOException,SQLException,Exception {
        WS_LEN = 0;
        WS_LEN = DDCSICHQ.CHQ_NO.trim().length();
        if (DDCSICHQ.CHQ_NO.trim().length() > 0) {
            if (WS_LEN != 8 
                && WS_LEN != 16) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_LEN_NOT_EQUAL_E;
                S000_ERR_MSG_PROC();
            }
            if (DDCSICHQ.CHQ_NO == null) DDCSICHQ.CHQ_NO = "";
            JIBS_tmp_int = DDCSICHQ.CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSICHQ.CHQ_NO += " ";
            if (!IBS.isNumeric(DDCSICHQ.CHQ_NO.substring(0, WS_LEN))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, DDCSICHQ.CHQ_NO);
        CEP.TRC(SCCGWA, WS_LEN);
        T000_STARTBR_DDTCHQ();
        T000_READNEXT_DDTCHQ();
        if (WS_CHQ_FLG == 'N') {
            if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                && WS_LEN == 8) {
                T000_STARTBR_DDTCHQ1();
                T000_READNEXT_DDTCHQ();
                if (WS_CHQ_FLG == 'N') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                }
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_LEN != 8) {
            if (DDCSICHQ.CHQ_NO == null) DDCSICHQ.CHQ_NO = "";
            JIBS_tmp_int = DDCSICHQ.CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSICHQ.CHQ_NO += " ";
            if (DDCSICHQ.CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_SICHQ_CHQ_NO = 0;
            else WS_SICHQ_CHQ_NO = Long.parseLong(DDCSICHQ.CHQ_NO.substring(0, WS_LEN));
            if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
            if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
            else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, WS_LEN));
        } else {
            if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
            if (DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() > 0) {
                if (DDCSICHQ.CHQ_NO == null) DDCSICHQ.CHQ_NO = "";
                JIBS_tmp_int = DDCSICHQ.CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDCSICHQ.CHQ_NO += " ";
                if (DDCSICHQ.CHQ_NO.substring(0, 8).trim().length() == 0) WS_SICHQ_CHQ_NO = 0;
                else WS_SICHQ_CHQ_NO = Long.parseLong(DDCSICHQ.CHQ_NO.substring(0, 8));
                if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                if (DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
                else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1));
            } else {
                if (DDCSICHQ.CHQ_NO == null) DDCSICHQ.CHQ_NO = "";
                JIBS_tmp_int = DDCSICHQ.CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDCSICHQ.CHQ_NO += " ";
                if (DDCSICHQ.CHQ_NO.substring(0, 8).trim().length() == 0) WS_SICHQ_CHQ_NO = 0;
                else WS_SICHQ_CHQ_NO = Long.parseLong(DDCSICHQ.CHQ_NO.substring(0, 8));
                if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, 8).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
                else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, 8));
            }
        }
        CEP.TRC(SCCGWA, WS_SICHQ_CHQ_NO);
        CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
        WS_CUR_POS = WS_SICHQ_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1;
        B030_GET_AC_MST_PROC();
        B030_GET_PAID_PROC();
        B030_GET_CCY_PROC();
        while (WS_CHQ_FLG != 'N') {
            B040_OUTPUT_PROC();
            T000_READNEXT_DDTCHQ();
        }
        T000_ENDBR_DDTCHQ();
    }
    public void B030_GET_AC_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCSICHQ.AC;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        T000_READ_DDTMST();
    }
    public void B030_GET_PAID_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRPAID);
        DDRPAID.KEY.AC = DDRCHQ.KEY.AC;
        DDRPAID.KEY.CHQ_TYP = DDRCHQ.KEY.CHQ_TYP;
        if (WS_LEN == 8) {
            CEP.TRC(SCCGWA, DDRCHQ.KEY.STR_CHQ_NO);
            if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
            JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
            if (DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() > 0) {
                if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                if (DDRPAID.KEY.CHQ_NO == null) DDRPAID.KEY.CHQ_NO = "";
                JIBS_tmp_int = DDRPAID.KEY.CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRPAID.KEY.CHQ_NO += " ";
                DDRPAID.KEY.CHQ_NO = DDRCHQ.KEY.STR_CHQ_NO.substring(0, 8) + DDRPAID.KEY.CHQ_NO.substring(8);
                JIBS_tmp_str[0] = "" + WS_SICHQ_CHQ_NO;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                if (DDRPAID.KEY.CHQ_NO == null) DDRPAID.KEY.CHQ_NO = "";
                JIBS_tmp_int = DDRPAID.KEY.CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRPAID.KEY.CHQ_NO += " ";
                DDRPAID.KEY.CHQ_NO = DDRPAID.KEY.CHQ_NO.substring(0, 9 - 1) + JIBS_tmp_str[0].substring(9 - 1, 9 + 8 - 1) + DDRPAID.KEY.CHQ_NO.substring(9 + 8 - 1);
                CEP.TRC(SCCGWA, "OUT");
                CEP.TRC(SCCGWA, DDCSICHQ.CHQ_NO);
                CEP.TRC(SCCGWA, DDRPAID.KEY.CHQ_NO);
            } else {
                DDRPAID.KEY.CHQ_NO = DDCSICHQ.CHQ_NO;
            }
        } else {
            DDRPAID.KEY.CHQ_NO = DDCSICHQ.CHQ_NO;
            CEP.TRC(SCCGWA, DDRPAID.KEY.CHQ_NO);
        }
        CEP.TRC(SCCGWA, DDRPAID.KEY.AC);
        CEP.TRC(SCCGWA, DDRPAID.KEY.CHQ_TYP);
        CEP.TRC(SCCGWA, DDRPAID.KEY.CHQ_NO);
        T000_READ_DDTPAID();
    }
    public void B030_GET_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDRCHQ.KEY.AC;
        T000_READ_DDTCCY();
    }
    public void B040_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOICHQ);
        DDCOICHQ.AC = DDRMST.KEY.CUS_AC;
        DDCOICHQ.CCY = DDRCHQ.CCY;
        DDCOICHQ.CCY_TYPE = DDRCHQ.CCY_TYPE;
        DDCOICHQ.CHQ_BV_CD = DDRCHQ.CHQ_BV_CD;
        DDCOICHQ.DOMICILE_BR = DDRMST.OWNER_BRDP;
        if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
        JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
        DDCOICHQ.CHQ_STS = DDRCHQ.CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).charAt(0);
        CEP.TRC(SCCGWA, DDCOICHQ.CHQ_STS);
        CEP.TRC(SCCGWA, WS_PAID_FLG);
        if (WS_PAID_FLG == 'Y') {
            DDCOICHQ.OPEN_DATE = DDRPAID.OPEN_DATE;
            CEP.TRC(SCCGWA, "11");
            DDCOICHQ.TR_DATE = DDRPAID.TR_DATE;
            CEP.TRC(SCCGWA, "22");
            CEP.TRC(SCCGWA, DDRPAID.OPEN_DATE);
            CEP.TRC(SCCGWA, DDRPAID.TR_DATE);
            CEP.TRC(SCCGWA, DDRPAID.AMT);
            DDCOICHQ.AMT = DDRPAID.AMT;
            CEP.TRC(SCCGWA, "33");
        }
        if (WS_CCY_FLG == 'Y') {
            DDCOICHQ.CURR_BAL = DDRCCY.CURR_BAL;
            CEP.TRC(SCCGWA, DDCOICHQ.CURR_BAL);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOICHQ;
        SCCFMT.DATA_LEN = 99;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CICQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_STARTBR_DDTCHQ() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.STR_CHQ_NO = DDCSICHQ.CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSICHQ.CHQ_NO;
        if (DDCSICHQ.AC.trim().length() > 0) {
            DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDTCHQ_BR.rp = new DBParm();
            DDTCHQ_BR.rp.TableName = "DDTCHQ";
            DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
                + "AND END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO "
                + "AND STR_CHQ_NO <= :DDRCHQ.KEY.END_CHQ_NO";
            IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        } else {
            DDTCHQ_BR.rp = new DBParm();
            DDTCHQ_BR.rp.TableName = "DDTCHQ";
            DDTCHQ_BR.rp.where = "END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO "
                + "AND STR_CHQ_NO <= :DDRCHQ.KEY.END_CHQ_NO";
            IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        }
    }
    public void T000_STARTBR_DDTCHQ1() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        IBS.init(SCCGWA, DDRCHQ);
        DDRCHQ.KEY.STR_CHQ_NO = DDCSICHQ.CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSICHQ.CHQ_NO;
        if (DDCSICHQ.AC.trim().length() > 0) {
            DDRCHQ.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            DDTCHQ_BR.rp = new DBParm();
            DDTCHQ_BR.rp.TableName = "DDTCHQ";
            DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
                + "AND SUBSTR ( END_CHQ_NO , 9 , 8 ) >= SUBSTR ( :DDRCHQ.KEY.STR_CHQ_NO , 1 , 8 ) "
                + "AND SUBSTR ( STR_CHQ_NO , 9 , 8 ) <= SUBSTR ( :DDRCHQ.KEY.END_CHQ_NO , 1 , 8 )";
            IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        } else {
            DDTCHQ_BR.rp = new DBParm();
            DDTCHQ_BR.rp.TableName = "DDTCHQ";
            DDTCHQ_BR.rp.where = "SUBSTR ( END_CHQ_NO , 9 , 8 ) >= SUBSTR ( :DDRCHQ.KEY.STR_CHQ_NO , 1 , 8 ) "
                + "AND SUBSTR ( STR_CHQ_NO , 9 , 8 ) <= SUBSTR ( :DDRCHQ.KEY.END_CHQ_NO , 1 , 8 )";
            IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        }
    }
    public void T000_READNEXT_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CHQ_FLG = 'Y';
        } else {
            WS_CHQ_FLG = 'N';
        }
    }
    public void T000_ENDBR_DDTCHQ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCHQ_BR);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_DDTPAID() throws IOException,SQLException,Exception {
        DDTPAID_RD = new DBParm();
        DDTPAID_RD.TableName = "DDTPAID";
        IBS.READ(SCCGWA, DDRPAID, DDTPAID_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PAID_FLG = 'Y';
        } else {
            WS_PAID_FLG = 'N';
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_FLG = 'Y';
        } else {
            WS_CCY_FLG = 'N';
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
