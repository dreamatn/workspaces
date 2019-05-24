package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5944 {
    char K_ERROR = 'E';
    String K_PROD_MDEL = "IRAC";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSIMAC DCCSIMAC = new DCCSIMAC();
    SCCGWA SCCGWA;
    DCB5941_AWA_5941 DCB5941_AWA_5941;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT5944 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5941_AWA_5941>");
        DCB5941_AWA_5941 = (DCB5941_AWA_5941) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUE_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.FUNC);
        CEP.TRC(SCCGWA, DCB5941_AWA_5941.PROD_CD);
        if (DCB5941_AWA_5941.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB5941_AWA_5941.PROD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_QUE_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSIMAC);
        DCCSIMAC.KEY.CON_MDEL = K_PROD_MDEL;
        DCCSIMAC.KEY.PROD_CODE = DCB5941_AWA_5941.PROD_CD;
        DCCSIMAC.CNAME = DCB5941_AWA_5941.CNAME;
        DCCSIMAC.PROD_INFO.DAYS = DCB5941_AWA_5941.DAYS;
        DCCSIMAC.PROD_INFO.PERD = DCB5941_AWA_5941.PERD;
        DCCSIMAC.PROD_INFO.PERD_UNIT = DCB5941_AWA_5941.PERD_UN;
        DCCSIMAC.PROD_INFO.REPY_MTH = DCB5941_AWA_5941.REPY_MTH;
        DCCSIMAC.FUNC = 'Q';
        S000_CALL_DCZSIMAC();
    }
    public void S000_CALL_DCZSIMAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-SIM-AC", DCCSIMAC);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
