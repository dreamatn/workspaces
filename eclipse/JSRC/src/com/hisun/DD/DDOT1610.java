package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1610 {
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSCSHW DDCSCSHW = new DDCSCSHW();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDB1600_AWA_1600 DDB1600_AWA_1600;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1610 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1600_AWA_1600>");
        DDB1600_AWA_1600 = (DDB1600_AWA_1600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1600_AWA_1600.AC);
        CEP.TRC(SCCGWA, DDB1600_AWA_1600.CASH_AMT);
        if (DDB1600_AWA_1600.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDB1600_AWA_1600.CASH_AMT == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_M_INPUT);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CAN_NOT_ALLOW);
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCSHW);
        DDCSCSHW.BV_TYP = DDB1600_AWA_1600.BV_TYP;
        DDCSCSHW.AC = DDB1600_AWA_1600.AC;
        DDCSCSHW.CCY = "156";
        DDCSCSHW.CCY_TYPE = '1';
        DDCSCSHW.CASH_AMT = DDB1600_AWA_1600.CASH_AMT;
        DDCSCSHW.CHK_PSW = 'N';
        S000_CALL_DDZSCSHW();
    }
    public void S000_CALL_DDZSCSHW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSCSHW", DDCSCSHW);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
