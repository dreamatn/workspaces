package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT6050 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSIQAC DCCSIQAC = new DCCSIQAC();
    SCCGWA SCCGWA;
    DCB6050_AWA_6050 DCB6050_AWA_6050;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT6050 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB6050_AWA_6050>");
        DCB6050_AWA_6050 = (DCB6050_AWA_6050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB6050_AWA_6050.AC);
        CEP.TRC(SCCGWA, DCB6050_AWA_6050.SEQ);
        if (DCB6050_AWA_6050.AC.trim().length() == 0 
            && DCB6050_AWA_6050.SUB_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ONE_AC_MUST_INPUT;
            S000_ERR_MSG_CONTINUE();
        }
        if (DCB6050_AWA_6050.AC.trim().length() > 0 
            && DCB6050_AWA_6050.SEQ == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SEQ_M_INPUT;
            WS_FLD_NO = DCB6050_AWA_6050.SEQ_NO;
            S000_ERR_MSG_CONTINUE();
        }
        S000_ERR_MSG_LAST();
    }
    public void B020_QUERY_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSIQAC);
        DCCSIQAC.AC = DCB6050_AWA_6050.AC;
        DCCSIQAC.SEQ = DCB6050_AWA_6050.SEQ;
        S000_CALL_DCZSIQAC();
    }
    public void S000_CALL_DCZSIQAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-SIQ-AC", DCCSIQAC);
    }
    public void S000_ERR_MSG_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
