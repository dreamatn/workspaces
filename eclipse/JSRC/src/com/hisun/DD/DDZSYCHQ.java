package com.hisun.DD;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSYCHQ {
    int JIBS_tmp_int;
    brParm DDTCHQ_BR = new brParm();
    DBParm DDTSCHQ_RD;
    DBParm DUPKEY_RD;
    DBParm DDTMST_RD;
    String WS_ERR_MSG = " ";
    long WS_CUR_POS = 0;
    long WS_SYCHQ_CHQ_NO = 0;
    long WS_CHQ_STR_CHQ_NO = 0;
    char WS_FLG = ' ';
    int WS_LEN = 0;
    char WS_CHQ_FLG = ' ';
    char WS_SCHQ_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRCCY DDRCCY = new DDRCCY();
    DDRCHQ DDRCHQ = new DDRCHQ();
    DDRSCHQ DDRSCHQ = new DDRSCHQ();
    DDRMST DDRMST = new DDRMST();
    DDRPAID DDRPAID = new DDRPAID();
    SCCGWA SCCGWA;
    DDCSYCHQ DDCSYCHQ;
    public void MP(SCCGWA SCCGWA, DDCSYCHQ DDCSYCHQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSYCHQ = DDCSYCHQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSYCHQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        if (DDCSYCHQ.STS == '1') {
            DDCSYCHQ.STS = '0';
        } else {
            DDCSYCHQ.STS = '1';
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_QUERY_CHQB_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSYCHQ.CHQ_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_CHQ_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_CHQB_PROC() throws IOException,SQLException,Exception {
        WS_LEN = 0;
        WS_LEN = DDCSYCHQ.CHQ_NO.trim().length();
        if (DDCSYCHQ.CHQ_NO.trim().length() > 0) {
            if (WS_LEN != 8 
                && WS_LEN != 16) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_LEN_NOT_EQUAL_E;
                S000_ERR_MSG_PROC();
            }
            if (DDCSYCHQ.CHQ_NO == null) DDCSYCHQ.CHQ_NO = "";
            JIBS_tmp_int = DDCSYCHQ.CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSYCHQ.CHQ_NO += " ";
            if (!IBS.isNumeric(DDCSYCHQ.CHQ_NO.substring(0, WS_LEN))) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
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
            if (DDCSYCHQ.CHQ_NO == null) DDCSYCHQ.CHQ_NO = "";
            JIBS_tmp_int = DDCSYCHQ.CHQ_NO.length();
            for (int i=0;i<16-JIBS_tmp_int;i++) DDCSYCHQ.CHQ_NO += " ";
            if (DDCSYCHQ.CHQ_NO.substring(0, WS_LEN).trim().length() == 0) WS_SYCHQ_CHQ_NO = 0;
            else WS_SYCHQ_CHQ_NO = Long.parseLong(DDCSYCHQ.CHQ_NO.substring(0, WS_LEN));
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
                if (DDCSYCHQ.CHQ_NO == null) DDCSYCHQ.CHQ_NO = "";
                JIBS_tmp_int = DDCSYCHQ.CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDCSYCHQ.CHQ_NO += " ";
                if (DDCSYCHQ.CHQ_NO.substring(0, 8).trim().length() == 0) WS_SYCHQ_CHQ_NO = 0;
                else WS_SYCHQ_CHQ_NO = Long.parseLong(DDCSYCHQ.CHQ_NO.substring(0, 8));
                if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                if (DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
                else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(9 - 1, 9 + 8 - 1));
            } else {
                if (DDCSYCHQ.CHQ_NO == null) DDCSYCHQ.CHQ_NO = "";
                JIBS_tmp_int = DDCSYCHQ.CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDCSYCHQ.CHQ_NO += " ";
                if (DDCSYCHQ.CHQ_NO.substring(0, 8).trim().length() == 0) WS_SYCHQ_CHQ_NO = 0;
                else WS_SYCHQ_CHQ_NO = Long.parseLong(DDCSYCHQ.CHQ_NO.substring(0, 8));
                if (DDRCHQ.KEY.STR_CHQ_NO == null) DDRCHQ.KEY.STR_CHQ_NO = "";
                JIBS_tmp_int = DDRCHQ.KEY.STR_CHQ_NO.length();
                for (int i=0;i<16-JIBS_tmp_int;i++) DDRCHQ.KEY.STR_CHQ_NO += " ";
                if (DDRCHQ.KEY.STR_CHQ_NO.substring(0, 8).trim().length() == 0) WS_CHQ_STR_CHQ_NO = 0;
                else WS_CHQ_STR_CHQ_NO = Long.parseLong(DDRCHQ.KEY.STR_CHQ_NO.substring(0, 8));
            }
        }
        CEP.TRC(SCCGWA, WS_SYCHQ_CHQ_NO);
        CEP.TRC(SCCGWA, WS_CHQ_STR_CHQ_NO);
        WS_CUR_POS = WS_SYCHQ_CHQ_NO - WS_CHQ_STR_CHQ_NO + 1;
        if (DDRCHQ.CHQ_STS == null) DDRCHQ.CHQ_STS = "";
        JIBS_tmp_int = DDRCHQ.CHQ_STS.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) DDRCHQ.CHQ_STS += " ";
        if (DDRCHQ.CHQ_STS.substring(WS_CUR_POS - 1, WS_CUR_POS + 1 - 1).equalsIgnoreCase("2")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQ_STS_INVALID;
            S000_ERR_MSG_PROC();
        }
        while (WS_CHQ_FLG != 'N') {
            T000_READUP_DDTSCHQ();
            if (WS_SCHQ_FLG == 'N') {
                DDRSCHQ.KEY.AC = DDCSYCHQ.AC_NO;
                DDRSCHQ.KEY.TYP = '2';
                DDRSCHQ.KEY.CHQ_NO = DDCSYCHQ.CHQ_NO;
                DDRSCHQ.AMT = DDCSYCHQ.AMT;
                DDRSCHQ.CHQ_STS = DDCSYCHQ.STS;
                DDRSCHQ.TXN_NARR = DDCSYCHQ.TXN_NARR;
                DDRSCHQ.REMARK = DDCSYCHQ.REMARK;
                DDRSCHQ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRSCHQ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRSCHQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRSCHQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRSCHQ.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_WRITE_DDTSCHQ();
            } else {
                DDRSCHQ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DDRSCHQ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                DDRSCHQ.CHQ_STS = DDCSYCHQ.STS;
                T000_REWRITE_DDTSCHQ();
            }
            T000_READNEXT_DDTCHQ();
        }
        T000_ENDBR_DDTCHQ();
    }
    public void T000_STARTBR_DDTCHQ() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, "11111111");
        IBS.init(SCCGWA, DDRCHQ);
        CEP.TRC(SCCGWA, DDCSYCHQ.CHQ_NO);
        DDRCHQ.KEY.AC = DDCSYCHQ.AC_NO;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSYCHQ.CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSYCHQ.CHQ_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND END_CHQ_NO >= :DDRCHQ.KEY.STR_CHQ_NO "
            + "AND STR_CHQ_NO <= :DDRCHQ.KEY.END_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ  END");
    }
    public void T000_STARTBR_DDTCHQ1() throws IOException,SQLException,Exception {
        WS_CHQ_FLG = 'N';
        CEP.TRC(SCCGWA, "11111111");
        IBS.init(SCCGWA, DDRCHQ);
        CEP.TRC(SCCGWA, DDCSYCHQ.CHQ_NO);
        DDRCHQ.KEY.AC = DDCSYCHQ.AC_NO;
        DDRCHQ.KEY.STR_CHQ_NO = DDCSYCHQ.CHQ_NO;
        DDRCHQ.KEY.END_CHQ_NO = DDCSYCHQ.CHQ_NO;
        DDTCHQ_BR.rp = new DBParm();
        DDTCHQ_BR.rp.TableName = "DDTCHQ";
        DDTCHQ_BR.rp.where = "AC = :DDRCHQ.KEY.AC "
            + "AND SUBSTR ( STR_CHQ_NO , 9 , 8 ) <= :DDRCHQ.KEY.END_CHQ_NO "
            + "AND SUBSTR ( END_CHQ_NO , 9 , 8 ) >= :DDRCHQ.KEY.STR_CHQ_NO";
        IBS.STARTBR(SCCGWA, DDRCHQ, this, DDTCHQ_BR);
        CEP.TRC(SCCGWA, "T000-STARTBR-DDTCHQ  END");
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
    public void T000_READUP_DDTSCHQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRSCHQ);
        DDRSCHQ.KEY.AC = DDRCHQ.KEY.AC;
        DDRSCHQ.KEY.CHQ_NO = DDCSYCHQ.CHQ_NO;
        DDTSCHQ_RD = new DBParm();
        DDTSCHQ_RD.TableName = "DDTSCHQ";
        DDTSCHQ_RD.upd = true;
        IBS.READ(SCCGWA, DDRSCHQ, DDTSCHQ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_SCHQ_FLG = 'N';
        } else {
            WS_SCHQ_FLG = 'Y';
        }
    }
    public void T000_WRITE_DDTSCHQ() throws IOException,SQLException,Exception {
        DDTSCHQ_RD = new DBParm();
        DDTSCHQ_RD.TableName = "DDTSCHQ";
        DDTSCHQ_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRSCHQ, DDTSCHQ_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CHQB_REC_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_DDTSCHQ() throws IOException,SQLException,Exception {
        DDTSCHQ_RD = new DBParm();
        DDTSCHQ_RD.TableName = "DDTSCHQ";
        DDTSCHQ_RD.errhdl = true;
        DUPKEY_RD = new DBParm();
        DUPKEY_RD.TableName = "DUPKEY";
        IBS.REWRITE(SCCGWA, DURKEY, DUPKEY_RD);
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
