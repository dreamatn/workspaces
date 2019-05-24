package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5903 {
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSMPRD DDCSMPRD = new DDCSMPRD();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DDB5900_AWA_5900 DDB5900_AWA_5900;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5903 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5900_AWA_5900>");
        DDB5900_AWA_5900 = (DDB5900_AWA_5900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DELETE_PRD_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.PRD_CD);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.PRD_CD);
        CEP.TRC(SCCGWA, DDB5900_AWA_5900.EFFDT);
        if (DDB5900_AWA_5900.PRD_CD.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PROD_CD_M_INPUT;
            WS_FLD_NO = DDB5900_AWA_5900.PRD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB5900_AWA_5900.EFFDT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = DDB5900_AWA_5900.EFFDT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DT_INVALID;
                WS_FLD_NO = DDB5900_AWA_5900.EFFDT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EFF_DT_M_INPUT;
            WS_FLD_NO = DDB5900_AWA_5900.EFFDT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_DELETE_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMPRD);
        DDCSMPRD.KEY.PARM_CODE = DDB5900_AWA_5900.PRD_CD;
        DDCSMPRD.EFF_DATE = DDB5900_AWA_5900.EFFDT;
        DDCSMPRD.FUNC = 'D';
        S000_CALL_DDZSMPRD();
    }
    public void S000_CALL_DDZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MAIN-PRD", DDCSMPRD);
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
