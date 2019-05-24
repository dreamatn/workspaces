package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6000 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSDORM DDCSDORM = new DDCSDORM();
    SCCGWA SCCGWA;
    DDB6000_AWA_6000 DDB6000_AWA_6000;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT6000 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6000_AWA_6000>");
        DDB6000_AWA_6000 = (DDB6000_AWA_6000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.BR_NO);
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.STR_DT);
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.END_DT2);
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.TYPE);
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.STS);
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.TSK_STS);
        if (DDB6000_AWA_6000.BR_NO == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            WS_FLD_NO = DDB6000_AWA_6000.BR_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6000_AWA_6000.STR_DT == 0 
            || DDB6000_AWA_6000.STR_DT > SCCGWA.COMM_AREA.AC_DATE 
            || DDB6000_AWA_6000.STR_DT > DDB6000_AWA_6000.END_DT2) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STR_DT_INVALID;
            WS_FLD_NO = DDB6000_AWA_6000.STR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6000_AWA_6000.END_DT2 == 0 
            || DDB6000_AWA_6000.END_DT2 > SCCGWA.COMM_AREA.AC_DATE 
            || DDB6000_AWA_6000.END_DT2 < DDB6000_AWA_6000.STR_DT) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_DT_INVALID;
            WS_FLD_NO = DDB6000_AWA_6000.END_DT2_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6000_AWA_6000.TYPE != ' ' 
            && DDB6000_AWA_6000.TYPE != '1' 
            && DDB6000_AWA_6000.TYPE != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REQ_TYP_INVALID;
            WS_FLD_NO = DDB6000_AWA_6000.TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6000_AWA_6000.STS != ' ' 
            && DDB6000_AWA_6000.STS != 'N' 
            && DDB6000_AWA_6000.STS != 'D') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DORM_STS_INVALID;
            WS_FLD_NO = DDB6000_AWA_6000.STS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6000_AWA_6000.TSK_STS != ' ' 
            && DDB6000_AWA_6000.TSK_STS != 'W' 
            && DDB6000_AWA_6000.TSK_STS != 'S') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TSK_STS_INVALID;
            WS_FLD_NO = DDB6000_AWA_6000.TSK_STS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSDORM);
        DDCSDORM.BR_NO = DDB6000_AWA_6000.BR_NO;
        DDCSDORM.STR_DT = DDB6000_AWA_6000.STR_DT;
        DDCSDORM.END_DT2 = DDB6000_AWA_6000.END_DT2;
        DDCSDORM.TYPE = DDB6000_AWA_6000.TYPE;
        DDCSDORM.STS = DDB6000_AWA_6000.STS;
        DDCSDORM.TSK_STS = DDB6000_AWA_6000.TSK_STS;
        DDCSDORM.FUNC = 'B';
        S000_CALL_DDZSDORM();
    }
    public void S000_CALL_DDZSDORM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSDORM", DDCSDORM);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
