package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT6060 {
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DCCSQBAL DCCSQBAL = new DCCSQBAL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DCB6060_AWA_6060 DCB6060_AWA_6060;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT6060 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB6060_AWA_6060>");
        DCB6060_AWA_6060 = (DCB6060_AWA_6060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_VIA_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB6060_AWA_6060.VIA_AC);
        CEP.TRC(SCCGWA, DCB6060_AWA_6060.CCY);
        CEP.TRC(SCCGWA, DCB6060_AWA_6060.CCY_TYP);
        CEP.TRC(SCCGWA, DCB6060_AWA_6060.DT);
        if (DCB6060_AWA_6060.VIA_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT;
            WS_FLD_NO = DCB6060_AWA_6060.VIA_AC_NO;
            S000_ERR_MSG_CONTINUE();
        }
        if (DCB6060_AWA_6060.DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DCB6060_AWA_6060.DT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VAL_DT_INVALID;
                WS_FLD_NO = DCB6060_AWA_6060.DT_NO;
                S000_ERR_MSG_CONTINUE();
            }
        }
        S000_ERR_MSG_LAST();
    }
    public void B020_QUERY_VIA_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSQBAL);
        DCCSQBAL.VIA_AC = DCB6060_AWA_6060.VIA_AC;
        DCCSQBAL.CCY = DCB6060_AWA_6060.CCY;
        DCCSQBAL.CCY_TYP = DCB6060_AWA_6060.CCY_TYP;
        DCCSQBAL.DT = DCB6060_AWA_6060.DT;
        S000_CALL_DCZSQBAL();
    }
    public void S000_CALL_DCZSQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-INQ-VIA-BAL", DCCSQBAL);
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
