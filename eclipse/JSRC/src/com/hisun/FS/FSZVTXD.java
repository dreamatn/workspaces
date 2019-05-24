package com.hisun.FS;

import com.hisun.DD.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class FSZVTXD {
    DBParm FSTVTXD_RD;
    brParm FSTVTXD_BR = new brParm();
    String CPN_PROC_FSTVTXD = "FS-R-WRIT-VTXD";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    FSRVTXD FSRVTXD = new FSRVTXD();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGMSG SCCGMSG;
    FSCSVTX FSCSVTX;
    public void MP(SCCGWA SCCGWA, FSCSVTX FSCSVTX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCSVTX = FSCSVTX;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FSZVTXD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B010_CHECK_INPUT_DATA();
            B020_RECORD_VTXD();
        } else {
            B030_RECORD_VTXD_CANCEL();
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_RECORD_VTXD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRVTXD);
        FSRVTXD.KEY.TRAN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRVTXD.KEY.SERIAL_NO = SCCGWA.COMM_AREA.JRN_NO;
        FSRVTXD.KEY.SUB_SERIAL_NO = SCCGWA.COMM_AREA.CALL_SEQ;
        FSRVTXD.PAY_ACCT = FSCSVTX.PAY_ACCT;
        FSRVTXD.PAY_NME = FSCSVTX.PAY_NME;
        FSRVTXD.CCY = FSCSVTX.CCY;
        FSRVTXD.CCY_TYP = FSCSVTX.CCY_TYP;
        FSRVTXD.RES_ACCT = FSCSVTX.RES_ACCT;
        FSRVTXD.RES_NME = FSCSVTX.RES_NME;
        FSRVTXD.DR_FLG = FSCSVTX.DR_FLG;
        FSRVTXD.TRANS_ATM = FSCSVTX.TRANS_ATM;
        FSRVTXD.VIR_ACCT = FSCSVTX.VIR_ACCT;
        FSRVTXD.TRAN_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRVTXD.ZY_CODE = FSCSVTX.ZY_CODE;
        FSRVTXD.REQFM_NO = FSCSVTX.REQFM_NO;
        FSRVTXD.EC_FLG = ' ';
        FSRVTXD.REMARK = FSCSVTX.REMARK;
        FSRVTXD.USE = FSCSVTX.USE;
        T000_WRITE_FSTVTXD();
    }
    public void B030_RECORD_VTXD_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRVTXD);
        FSRVTXD.KEY.TRAN_DATE = FSCSVTX.TRAN_DATE;
        FSRVTXD.KEY.SERIAL_NO = FSCSVTX.SERIAL_NO;
        T000_STARTBR_FSTVTXD();
        T000_READNEXT_FSTVTXD();
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                FSRVTXD.EC_FLG = 'Y';
                T000_REWRITE_FSTVTXD();
            }
            T000_READNEXT_FSTVTXD();
        }
        T000_ENDBR_FSTVTXD();
    }
    public void T000_READ_UPDATE_FSTVTXD() throws IOException,SQLException,Exception {
        FSTVTXD_RD = new DBParm();
        FSTVTXD_RD.TableName = "FSTVTXD";
        FSTVTXD_RD.upd = true;
        IBS.READ(SCCGWA, FSRVTXD, FSTVTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_LPAY_REC_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE FSTVTXD ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTVTXD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_FSTVTXD() throws IOException,SQLException,Exception {
        FSRVTXD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRVTXD.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRVTXD.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        FSTVTXD_RD = new DBParm();
        FSTVTXD_RD.TableName = "FSTVTXD";
        IBS.REWRITE(SCCGWA, FSRVTXD, FSTVTXD_RD);
    }
    public void T000_WRITE_FSTVTXD() throws IOException,SQLException,Exception {
        FSRVTXD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRVTXD.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRVTXD.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        FSTVTXD_RD = new DBParm();
        FSTVTXD_RD.TableName = "FSTVTXD";
        FSTVTXD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, FSRVTXD, FSTVTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "WRITE TABLE FSTVTXD ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTVTXD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_FSTVTXD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FSRVTXD.KEY.TRAN_DATE);
        CEP.TRC(SCCGWA, FSRVTXD.KEY.SERIAL_NO);
        FSTVTXD_BR.rp = new DBParm();
        FSTVTXD_BR.rp.TableName = "FSTVTXD";
        FSTVTXD_BR.rp.where = "TRAN_DATE = :FSRVTXD.KEY.TRAN_DATE "
            + "AND SERIAL_NO = :FSRVTXD.KEY.SERIAL_NO";
        FSTVTXD_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, FSRVTXD, this, FSTVTXD_BR);
    }
    public void T000_READNEXT_FSTVTXD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, FSRVTXD, this, FSTVTXD_BR);
    }
    public void T000_ENDBR_FSTVTXD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, FSTVTXD_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FSCSVTX.RC);
        if (FSCSVTX.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "FSCSVTX = ");
            CEP.TRC(SCCGWA, FSCSVTX);
        }
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
