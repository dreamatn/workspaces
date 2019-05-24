package com.hisun.FS;

import com.hisun.DD.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class FSZFRESL {
    int JIBS_tmp_int;
    DBParm FSTTXD_RD;
    String K_MAN_REV = "9999999";
    String K_ORG_TXN_NO = "0581100";
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    double WS_TRAN_AMT = 0;
    String WS_PAY_NAME = " ";
    String WS_RES_NAME = " ";
    String WS_UPACC_NO = " ";
    short WS_I = 0;
    int WS_LOOP_CNT = 0;
    FSZFRESL_WS_JRNL_ARRAY[] WS_JRNL_ARRAY = new FSZFRESL_WS_JRNL_ARRAY[9];
    char WS_TBL_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    FSCENTR FSCENTR = new FSCENTR();
    FSRTXD FSRTXD = new FSRTXD();
    SCCGWA SCCGWA;
    FSCFRESL FSCFRESL;
    public FSZFRESL() {
        for (int i=0;i<9;i++) WS_JRNL_ARRAY[i] = new FSZFRESL_WS_JRNL_ARRAY();
    }
    public void MP(SCCGWA SCCGWA, FSCFRESL FSCFRESL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCFRESL = FSCFRESL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FSZFRESL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_INFO();
        B020_TRAN_PROC();
    }
    public void B010_CHECK_INPUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRTXD);
        FSRTXD.KEY.TRAN_DATE = FSCFRESL.CAN_AC_DATE;
        FSRTXD.KEY.SERIAL_NO = FSCFRESL.CAN_JRN_NO;
        CEP.TRC(SCCGWA, "START LOOP FETCH FSTTXD");
        CEP.TRC(SCCGWA, FSCFRESL.CAN_AC_DATE);
        CEP.TRC(SCCGWA, FSCFRESL.CAN_JRN_NO);
        T000_READ_FSTTXD();
        while (WS_TBL_FLAG != 'N') {
            WS_I += 1;
            WS_JRNL_ARRAY[WS_I-1].WS_JRN_NO = FSRTXD.KEY.SERIAL_NO;
            WS_JRNL_ARRAY[WS_I-1].WS_SUB_JRN_NO = FSRTXD.KEY.SUB_SERIAL_NO;
            CEP.TRC(SCCGWA, WS_JRNL_ARRAY[WS_I-1]);
            FSRTXD.KEY.TRAN_DATE = FSCFRESL.CAN_AC_DATE;
            FSRTXD.KEY.SERIAL_NO = FSRTXD.KEY.SUB_SERIAL_NO;
            T000_READ_FSTTXD();
        }
        WS_LOOP_CNT = WS_I;
        CEP.TRC(SCCGWA, WS_LOOP_CNT);
    }
    public void B020_TRAN_PROC() throws IOException,SQLException,Exception {
        for (WS_I = WS_LOOP_CNT; WS_I != 0; WS_I += -1) {
            if (WS_JRNL_ARRAY[WS_I-1].WS_SUB_JRN_NO != ' ') {
                FSCFRESL.CAN_JRN_NO = WS_JRNL_ARRAY[WS_I-1].WS_SUB_JRN_NO;
                FSCFRESL.CAN_SERV_ID = K_ORG_TXN_NO;
                FSCFRESL.CAN_VCH_NO = "" + WS_JRNL_ARRAY[WS_I-1].WS_SUB_JRN_NO;
                JIBS_tmp_int = FSCFRESL.CAN_VCH_NO.length();
                for (int i=0;i<12-JIBS_tmp_int;i++) FSCFRESL.CAN_VCH_NO = "0" + FSCFRESL.CAN_VCH_NO;
                FSCFRESL.CAN_STM_IND = ' ';
                FSCENTR.TR_CODE = K_MAN_REV;
                FSCENTR.INP_DATA = IBS.CLS2CPY(SCCGWA, FSCFRESL);
                CEP.TRC(SCCGWA, FSCFRESL.CAN_AC_DATE);
                CEP.TRC(SCCGWA, FSCFRESL.CAN_JRN_NO);
                S000_CALL_FSOENTR();
            }
        }
    }
    public void T000_READ_FSTTXD() throws IOException,SQLException,Exception {
        FSTTXD_RD = new DBParm();
        FSTTXD_RD.TableName = "FSTTXD";
        FSTTXD_RD.where = "TRAN_DATE = :FSRTXD.KEY.TRAN_DATE "
            + "AND SERIAL_NO = :FSRTXD.KEY.SERIAL_NO";
        IBS.READ(SCCGWA, FSRTXD, this, FSTTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE FSTTXD ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTTXD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void S000_CALL_FSOENTR() throws IOException,SQLException,Exception {
        FSOENTR FSOENTR = new FSOENTR();
        FSOENTR.MP(SCCGWA, FSCENTR);
        if (FSCENTR.RT_MSG.RC != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, FSCENTR.RT_MSG);
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
