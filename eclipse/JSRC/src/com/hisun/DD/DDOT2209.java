package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT2209 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCUTRVS DDCUTRVS = new DDCUTRVS();
    SCCGWA SCCGWA;
    DDB2200_AWA_2200 DDB2200_AWA_2200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT2209 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB2200_AWA_2200>");
        DDB2200_AWA_2200 = (DDB2200_AWA_2200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB2200_AWA_2200.VS_DRAC);
        CEP.TRC(SCCGWA, DDB2200_AWA_2200.VS_CCY);
        CEP.TRC(SCCGWA, DDB2200_AWA_2200.VS_TYP);
        CEP.TRC(SCCGWA, DDB2200_AWA_2200.VS_AMT);
        CEP.TRC(SCCGWA, DDB2200_AWA_2200.VS_STLT);
        CEP.TRC(SCCGWA, DDB2200_AWA_2200.VS_CRAC);
        if (DDB2200_AWA_2200.VS_DRAC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB2200_AWA_2200.VS_DRAC_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (DDB2200_AWA_2200.VS_CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB2200_AWA_2200.VS_CCY_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (DDB2200_AWA_2200.VS_AMT < 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_INVALID;
            WS_FLD_NO = DDB2200_AWA_2200.VS_AMT_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (DDB2200_AWA_2200.VS_AMT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            WS_FLD_NO = DDB2200_AWA_2200.VS_AMT_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (DDB2200_AWA_2200.VS_STLT == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STL_MTH_M_INPT;
            WS_FLD_NO = DDB2200_AWA_2200.VS_STLT_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (DDB2200_AWA_2200.VS_CRAC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB2200_AWA_2200.VS_CRAC_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (DDB2200_AWA_2200.VS_SPFLG == '2' 
            && DDB2200_AWA_2200.OLD_CRAC.trim().length() == 0) {
            DDB2200_AWA_2200.OLD_CRAC = DDB2200_AWA_2200.VS_DRAC;
        }
    }
    public void B200_TRANS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUTRVS);
        DDCUTRVS.VS_DRAC = DDB2200_AWA_2200.VS_DRAC;
        DDCUTRVS.VS_DRNM = DDB2200_AWA_2200.VS_DRNM;
        DDCUTRVS.TXCCY = DDB2200_AWA_2200.VS_CCY;
        DDCUTRVS.CCY_TYP = DDB2200_AWA_2200.VS_TYP;
        DDCUTRVS.BAL = DDB2200_AWA_2200.VS_BAL;
        DDCUTRVS.VS_OPT = DDB2200_AWA_2200.VS_OPT;
        DDCUTRVS.VS_SPFLG = DDB2200_AWA_2200.VS_SPFLG;
        DDCUTRVS.VS_SPAMT = DDB2200_AWA_2200.VS_SPAMT;
        DDCUTRVS.VS_AMT = DDB2200_AWA_2200.VS_AMT;
        DDCUTRVS.VS_STLT = DDB2200_AWA_2200.VS_STLT;
        DDCUTRVS.OLD_CRAC = DDB2200_AWA_2200.OLD_CRAC;
        DDCUTRVS.VS_CRAC = DDB2200_AWA_2200.VS_CRAC;
        DDCUTRVS.VS_CRNM = DDB2200_AWA_2200.VS_CRNM;
        DDCUTRVS.MMO = DDB2200_AWA_2200.VS_MMO;
        DDCUTRVS.REMARK = DDB2200_AWA_2200.REMARK;
        S000_CALL_DDZUTRVS();
    }
    public void S000_CALL_DDZUTRVS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-VSTRN-PROC", DDCUTRVS);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
