package com.hisun.FS;

import com.hisun.DD.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class FSZRSPAY {
    brParm FSTTXD_BR = new brParm();
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    char WS_READ_STS_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    FSCTLEG FSCTLEG = new FSCTLEG();
    FSRTXD FSRTXD = new FSRTXD();
    SCCGWA SCCGWA;
    FSCRSPAY FSCRSPAY;
    public void MP(SCCGWA SCCGWA, FSCRSPAY FSCRSPAY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCRSPAY = FSCRSPAY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FSZRSPAY return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_TRAN_PROC();
    }
    public void B010_TRAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRTXD);
        FSRTXD.KEY.TRAN_DATE = FSCRSPAY.CAN_AC_DATE;
        FSRTXD.KEY.SERIAL_NO = FSCRSPAY.CAN_JRN_NO;
        CEP.TRC(SCCGWA, "START LOOP FETCH FSTTXD");
        CEP.TRC(SCCGWA, FSCRSPAY.CAN_AC_DATE);
        CEP.TRC(SCCGWA, FSCRSPAY.CAN_JRN_NO);
        T000_STARTBR_FSTTXD();
        T000_READNEXT_FSTTXD();
        while (WS_READ_STS_FLAG != 'Y') {
            CEP.TRC(SCCGWA, FSRTXD.KEY.TRAN_DATE);
            CEP.TRC(SCCGWA, FSRTXD.KEY.SERIAL_NO);
            CEP.TRC(SCCGWA, FSRTXD.KEY.SUB_SERIAL_NO);
            B020_REVERSAL_PROC();
            T000_READNEXT_FSTTXD();
        }
        T000_ENDBR_FSTTXD();
    }
    public void B020_REVERSAL_PROC() throws IOException,SQLException,Exception {
        FSCTLEG.RES_ACCT = FSRTXD.RES_ACCT;
        FSCTLEG.CCY = FSRTXD.CCY;
        FSCTLEG.CCY_TYP = FSRTXD.CCY_TYP;
        FSCTLEG.TRANS_ATM = FSRTXD.TRANS_ATM;
        FSCTLEG.TRAN_DATE = FSRTXD.KEY.TRAN_DATE;
        FSCTLEG.TRAN_TIME = FSRTXD.TRAN_TIME;
        FSCTLEG.ZY_CODE = FSRTXD.ZY_CODE;
        FSCTLEG.JRN_NO = FSRTXD.KEY.SERIAL_NO;
        FSCTLEG.SUB_JRN_NO = FSRTXD.KEY.SUB_SERIAL_NO;
        FSCTLEG.USER = FSRTXD.REMARK;
        FSCTLEG.REMARK = FSRTXD.USE;
        S000_CALL_FSZTLEG();
    }
    public void T000_STARTBR_FSTTXD() throws IOException,SQLException,Exception {
        FSTTXD_BR.rp = new DBParm();
        FSTTXD_BR.rp.TableName = "FSTTXD";
        FSTTXD_BR.rp.eqWhere = "TRAN_DATE,SERIAL_NO";
        FSTTXD_BR.rp.order = "TRAN_DATE,SERIAL_NO,SUB_SERIAL_NO";
        IBS.STARTBR(SCCGWA, FSRTXD, FSTTXD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_READ_STS_FLAG = 'Y';
        }
    }
    public void T000_READNEXT_FSTTXD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, FSRTXD, this, FSTTXD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_READ_STS_FLAG = 'Y';
        }
    }
    public void T000_ENDBR_FSTTXD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, FSTTXD_BR);
    }
    public void S000_CALL_FSZTLEG() throws IOException,SQLException,Exception {
        FSZTLEG FSZTLEG = new FSZTLEG();
        FSZTLEG.MP(SCCGWA, FSCTLEG);
        if (FSCTLEG.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, FSCTLEG.RC);
            CEP.ERR(SCCGWA, WS_MSG_ID);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
