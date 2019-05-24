package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6010 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSCVTR DDCSCVTR = new DDCSCVTR();
    SCCGWA SCCGWA;
    DDB6010_AWA_6010 DDB6010_AWA_6010;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT6010 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6010_AWA_6010>");
        DDB6010_AWA_6010 = (DDB6010_AWA_6010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB6010_AWA_6010.FUNC);
        CEP.TRC(SCCGWA, DDB6010_AWA_6010.AC);
        CEP.TRC(SCCGWA, DDB6010_AWA_6010.CCY);
        CEP.TRC(SCCGWA, DDB6010_AWA_6010.CCY_TYPE);
        CEP.TRC(SCCGWA, DDB6010_AWA_6010.TYPE);
        CEP.TRC(SCCGWA, DDB6010_AWA_6010.AG_AC);
        CEP.TRC(SCCGWA, DDB6010_AWA_6010.PERIOD);
        CEP.TRC(SCCGWA, DDB6010_AWA_6010.FEQ);
        CEP.TRC(SCCGWA, DDB6010_AWA_6010.AMT);
        if (DDB6010_AWA_6010.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            if (DDB6010_AWA_6010.AC.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB6010_AWA_6010.AC);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6010_AWA_6010.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            if (DDB6010_AWA_6010.CCY.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB6010_AWA_6010.CCY);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB6010_AWA_6010.CCY_TYPE == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT;
            if (DDB6010_AWA_6010.CCY_TYPE == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+DDB6010_AWA_6010.CCY_TYPE);
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCVTR);
        DDCSCVTR.FUNC = DDB6010_AWA_6010.FUNC;
        DDCSCVTR.AC = DDB6010_AWA_6010.AC;
        DDCSCVTR.CCY = DDB6010_AWA_6010.CCY;
        DDCSCVTR.CCY_TYPE = DDB6010_AWA_6010.CCY_TYPE;
        DDCSCVTR.TYPE = DDB6010_AWA_6010.TYPE;
        DDCSCVTR.AG_AC = DDB6010_AWA_6010.AG_AC;
        DDCSCVTR.PERIOD = DDB6010_AWA_6010.PERIOD;
        DDCSCVTR.FEQ = DDB6010_AWA_6010.FEQ;
        DDCSCVTR.AMT = DDB6010_AWA_6010.AMT;
        CEP.TRC(SCCGWA, DDCSCVTR.FUNC);
        CEP.TRC(SCCGWA, DDCSCVTR.AC);
        CEP.TRC(SCCGWA, DDCSCVTR.CCY);
        CEP.TRC(SCCGWA, DDCSCVTR.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSCVTR.TYPE);
        CEP.TRC(SCCGWA, DDCSCVTR.AG_AC);
        CEP.TRC(SCCGWA, DDCSCVTR.PERIOD);
        CEP.TRC(SCCGWA, DDCSCVTR.FEQ);
        CEP.TRC(SCCGWA, DDCSCVTR.AMT);
        DDCSCVTR.FUNC = 'A';
        S000_CALL_DDZSCVTR();
    }
    public void S000_CALL_DDZSCVTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSCVTR", DDCSCVTR);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
