package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT8600 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSMHQB DDCSMHQB = new DDCSMHQB();
    SCCGWA SCCGWA;
    DDB8600_AWA_8600 DDB8600_AWA_8600;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT8600 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB8600_AWA_8600>");
        DDB8600_AWA_8600 = (DDB8600_AWA_8600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB8600_AWA_8600.FUNC);
        CEP.TRC(SCCGWA, DDB8600_AWA_8600.CUS_AC);
        CEP.TRC(SCCGWA, DDB8600_AWA_8600.CCY);
        CEP.TRC(SCCGWA, DDB8600_AWA_8600.CCY_TYP);
        CEP.TRC(SCCGWA, DDB8600_AWA_8600.FLG);
        CEP.TRC(SCCGWA, DDB8600_AWA_8600.STRDT);
        CEP.TRC(SCCGWA, DDB8600_AWA_8600.EXPDT);
        CEP.TRC(SCCGWA, DDB8600_AWA_8600.STS);
        if (DDB8600_AWA_8600.FUNC == '1'
            || DDB8600_AWA_8600.FUNC == '2'
            || DDB8600_AWA_8600.FUNC == '3') {
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSMHQB);
        DDCSMHQB.FUNC = DDB8600_AWA_8600.FUNC;
        DDCSMHQB.CUS_AC = DDB8600_AWA_8600.CUS_AC;
        DDCSMHQB.CCY = DDB8600_AWA_8600.CCY;
        DDCSMHQB.CCY_TYP = DDB8600_AWA_8600.CCY_TYP;
        DDCSMHQB.FLG = DDB8600_AWA_8600.FLG;
        DDCSMHQB.STRDT = DDB8600_AWA_8600.STRDT;
        DDCSMHQB.EXPDT = DDB8600_AWA_8600.EXPDT;
        DDCSMHQB.STS = DDB8600_AWA_8600.STS;
        DDCSMHQB.PSWD = DDB8600_AWA_8600.PSWD;
        DDCSMHQB.CI_NO = DDB8600_AWA_8600.CI_NO;
        S000_CALL_DDZSMHQB();
    }
    public void S000_CALL_DDZSMHQB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSMHQB", DDCSMHQB);
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
