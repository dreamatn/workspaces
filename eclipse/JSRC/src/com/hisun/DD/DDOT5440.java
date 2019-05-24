package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5440 {
    String CPN_DD_S_BSTP_PROC = "DD-S-BSTP-PROC";
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSBSTP DDCSBSTP = new DDCSBSTP();
    SCCGWA SCCGWA;
    DDB5440_AWA_5440 DDB5440_AWA_5440;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5440 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5440_AWA_5440>");
        DDB5440_AWA_5440 = (DDB5440_AWA_5440) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B20_QUERY_LOST_CHQB_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.AC_NO);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.QTY);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.STR_NO);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.END_NO);
        CEP.TRC(SCCGWA, DDB5440_AWA_5440.CHQ_TYP);
        if (DDB5440_AWA_5440.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B20_QUERY_LOST_CHQB_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSBSTP);
        DDCSBSTP.AC_NO = DDB5440_AWA_5440.AC_NO;
        DDCSBSTP.INQ_TYP = DDB5440_AWA_5440.QTY;
        DDCSBSTP.STR_CHQ_NO = DDB5440_AWA_5440.STR_NO;
        DDCSBSTP.END_CHQ_NO = DDB5440_AWA_5440.END_NO;
        DDCSBSTP.CHQ_TYP = DDB5440_AWA_5440.CHQ_TYP;
        if (DDB5440_AWA_5440.QTY == ' ') {
            DDCSBSTP.INQ_TYP = '1';
        }
        CEP.TRC(SCCGWA, DDCSBSTP.AC_NO);
        CEP.TRC(SCCGWA, DDCSBSTP.INQ_TYP);
        CEP.TRC(SCCGWA, DDCSBSTP.STR_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSBSTP.END_CHQ_NO);
        CEP.TRC(SCCGWA, DDCSBSTP.CHQ_TYP);
        S000_CALL_DDZSBSTP();
    }
    public void S000_CALL_DDZSBSTP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DD_S_BSTP_PROC, DDCSBSTP);
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
