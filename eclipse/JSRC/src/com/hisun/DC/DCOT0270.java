package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0270 {
    String K_PROD_MDEL = "IRAC";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 1;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSPFDE DCCSPFDE = new DCCSPFDE();
    SCCGWA SCCGWA;
    DCB0270_AWA_0270 DCB0270_AWA_0270;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT0270 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0270_AWA_0270>");
        DCB0270_AWA_0270 = (DCB0270_AWA_0270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_SET_SUB();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB0270_AWA_0270.FUNC);
        CEP.TRC(SCCGWA, DCB0270_AWA_0270.AGT_NO);
        CEP.TRC(SCCGWA, DCB0270_AWA_0270.AGT_TYP);
        CEP.TRC(SCCGWA, DCB0270_AWA_0270.LN_AC);
        CEP.TRC(SCCGWA, DCB0270_AWA_0270.DD_AC);
        CEP.TRC(SCCGWA, DCB0270_AWA_0270.INC_AMT);
        CEP.TRC(SCCGWA, DCB0270_AWA_0270.DED_AMT);
        CEP.TRC(SCCGWA, DCB0270_AWA_0270.DED_PER);
        CEP.TRC(SCCGWA, DCB0270_AWA_0270.DED_DATE);
    }
    public void B020_SET_SUB() throws IOException,SQLException,Exception {
        DCCSPFDE.FUNC = DCB0270_AWA_0270.FUNC;
        DCCSPFDE.AGT_NO = DCB0270_AWA_0270.AGT_NO;
        DCCSPFDE.AGT_TYP = DCB0270_AWA_0270.AGT_TYP;
        DCCSPFDE.LN_AC = DCB0270_AWA_0270.LN_AC;
        DCCSPFDE.DD_AC = DCB0270_AWA_0270.DD_AC;
        DCCSPFDE.INC_AMT = DCB0270_AWA_0270.INC_AMT;
        DCCSPFDE.DED_AMT = DCB0270_AWA_0270.DED_AMT;
        DCCSPFDE.DED_PER = DCB0270_AWA_0270.DED_PER;
        DCCSPFDE.DED_DATE = DCB0270_AWA_0270.DED_DATE;
        CEP.TRC(SCCGWA, DCCSPFDE);
        if (DCB0270_AWA_0270.FUNC == 'Q') {
            DCCSPFDE.FUNC = 'Q';
        } else if (DCB0270_AWA_0270.FUNC == 'A') {
            DCCSPFDE.FUNC = 'A';
        } else if (DCB0270_AWA_0270.FUNC == 'M') {
            DCCSPFDE.FUNC = 'M';
        } else if (DCB0270_AWA_0270.FUNC == 'D') {
            DCCSPFDE.FUNC = 'D';
        } else {
        }
        S000_CALL_DCZSPFDE();
    }
    public void S000_CALL_DCZSPFDE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-SPF-DE", DCCSPFDE);
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
