package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5961 {
    String CPN_DC_S_IR_PRD = "DC-SVR-IR-PRD";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_INFO = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSFPRD DCCSFPRD = new DCCSFPRD();
    SCCGWA SCCGWA;
    DCB5961_AWA_5961 DCB5961_AWA_5961;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5961 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5961_AWA_5961>");
        DCB5961_AWA_5961 = (DCB5961_AWA_5961) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5961_AWA_5961.FUNC);
        CEP.TRC(SCCGWA, DCB5961_AWA_5961.PROD_COD);
        if (DCB5961_AWA_5961.PROD_COD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB5961_AWA_5961.PROD_COD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_INQ_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSFPRD);
        DCCSFPRD.PROD_CODE = DCB5961_AWA_5961.PROD_COD;
        CEP.TRC(SCCGWA, DCCSFPRD);
        DCCSFPRD.FUNC = 'I';
        S000_CALL_DCZSFPRD();
    }
    public void S000_CALL_DCZSFPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DC_S_IR_PRD, DCCSFPRD);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_INFO, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_INPUT_ERR);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
