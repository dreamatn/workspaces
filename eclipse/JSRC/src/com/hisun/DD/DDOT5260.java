package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5260 {
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    DDCSPASS DDCSPASS = new DDCSPASS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    DDB5260_AWA_5260 DDB5260_AWA_5260;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDOT5260 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5260_AWA_5260>");
        DDB5260_AWA_5260 = (DDB5260_AWA_5260) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5260_AWA_5260.AC_NO);
        CEP.TRC(SCCGWA, DDB5260_AWA_5260.PASS_NO);
        CEP.TRC(SCCGWA, DDB5260_AWA_5260.STS);
        CEP.TRC(SCCGWA, DDB5260_AWA_5260.TR_DT);
        CEP.TRC(SCCGWA, DDB5260_AWA_5260.STA_DT);
        CEP.TRC(SCCGWA, DDB5260_AWA_5260.END_DT);
        if ((DDB5260_AWA_5260.STS != '2' 
            && DDB5260_AWA_5260.STS != '4')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PASS_STS_NOT_EXIST);
        }
        if (DDB5260_AWA_5260.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDB5260_AWA_5260.PASS_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_PASS_NO_M_INPUT);
        }
        if (DDB5260_AWA_5260.STA_DT == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_DT_M_INPUT);
        }
        if (DDB5260_AWA_5260.STS == '2') {
            if (DDB5260_AWA_5260.STA_DT < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_STRDT_M_GTEQ_ACDT);
            }
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = DDB5260_AWA_5260.STA_DT;
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_BAK_DT_INVALID);
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSPASS);
        DDCSPASS.AC_NO = DDB5260_AWA_5260.AC_NO;
        DDCSPASS.PASS_NO = DDB5260_AWA_5260.PASS_NO;
        DDCSPASS.STS = DDB5260_AWA_5260.STS;
        DDCSPASS.TR_DT = DDB5260_AWA_5260.TR_DT;
        DDCSPASS.STA_DT = DDB5260_AWA_5260.STA_DT;
        DDCSPASS.END_DT = DDB5260_AWA_5260.END_DT;
        CEP.TRC(SCCGWA, DDCSPASS.AC_NO);
        CEP.TRC(SCCGWA, DDCSPASS.PASS_NO);
        CEP.TRC(SCCGWA, DDCSPASS.STS);
        CEP.TRC(SCCGWA, DDCSPASS.TR_DT);
        S000_CALL_DDZSPASS();
    }
    public void S000_CALL_DDZSPASS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSPASS", DDCSPASS);
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
