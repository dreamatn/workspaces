package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6003 {
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
        CEP.TRC(SCCGWA, "DDOT6003 return!");
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
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.FUNC);
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.BR_NO);
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.DATE);
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.END_DT);
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.TYPE);
        CEP.TRC(SCCGWA, DDB6000_AWA_6000.STS);
        if (DDB6000_AWA_6000.FUNC != 'A' 
            && DDB6000_AWA_6000.FUNC != 'M' 
            && DDB6000_AWA_6000.FUNC != 'D' 
            && DDB6000_AWA_6000.FUNC != 'I') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ACTION_INVALID;
            WS_FLD_NO = DDB6000_AWA_6000.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6000_AWA_6000.BR_NO == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BR_M_INPUT;
            WS_FLD_NO = DDB6000_AWA_6000.BR_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6000_AWA_6000.FUNC == 'A' 
            && DDB6000_AWA_6000.DATE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_DORM_DT_INVALID;
            WS_FLD_NO = DDB6000_AWA_6000.DATE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6000_AWA_6000.END_DT == 0 
            || DDB6000_AWA_6000.END_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_END_DT_INVALID;
            WS_FLD_NO = DDB6000_AWA_6000.END_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6000_AWA_6000.TYPE == ' ' 
            || (DDB6000_AWA_6000.TYPE != '1' 
            && DDB6000_AWA_6000.TYPE != '2')) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_REQ_TYP_INVALID;
            WS_FLD_NO = DDB6000_AWA_6000.TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSDORM);
        DDCSDORM.FUNC = DDB6000_AWA_6000.FUNC;
        DDCSDORM.BR_NO = DDB6000_AWA_6000.BR_NO;
        DDCSDORM.DATE = DDB6000_AWA_6000.DATE;
        DDCSDORM.END_DT = DDB6000_AWA_6000.END_DT;
        DDCSDORM.TYPE = DDB6000_AWA_6000.TYPE;
        DDCSDORM.REMARKS = DDB6000_AWA_6000.REMARKS;
        DDCSDORM.FLG1 = DDB6000_AWA_6000.FLG1;
        DDCSDORM.FLG2 = DDB6000_AWA_6000.FLG2;
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
