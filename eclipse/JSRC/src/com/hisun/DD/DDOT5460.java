package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5460 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCSCXJS DDCSCXJS = new DDCSCXJS();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    DDB5460_AWA_5460 DDB5460_AWA_5460;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT5460 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5460_AWA_5460>");
        DDB5460_AWA_5460 = (DDB5460_AWA_5460) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5460_AWA_5460.FUNC);
        CEP.TRC(SCCGWA, DDB5460_AWA_5460.CUS_AC);
        CEP.TRC(SCCGWA, DDB5460_AWA_5460.AC_TYP_N);
        if (DDB5460_AWA_5460.FUNC != '1' 
            && DDB5460_AWA_5460.FUNC != '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
            S000_ERR_MSG_PROC();
        }
        if (DDB5460_AWA_5460.FUNC == '1') {
            if (DDB5460_AWA_5460.AC_TYP_N != 'A') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (DDB5460_AWA_5460.FUNC == '2') {
            if (DDB5460_AWA_5460.AC_TYP_N != 'B') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_ERR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_CHECK_AC_HK_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = DDB5460_AWA_5460.CUS_AC;
        S000_CALL_CISOCUST();
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_REG_CNTY);
        if (CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("334") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("446") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("158") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("HKG") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("MAC") 
            || CICCUST.O_DATA.O_REG_CNTY.equalsIgnoreCase("TWN")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HTM_CX_CANT_JS;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCXJS);
        DDCSCXJS.FUNC = DDB5460_AWA_5460.FUNC;
        DDCSCXJS.CUS_AC = DDB5460_AWA_5460.CUS_AC;
        DDCSCXJS.CI_CNM = DDB5460_AWA_5460.CI_CNM;
        DDCSCXJS.AC_TYP_N = DDB5460_AWA_5460.AC_TYP_N;
        S000_CALL_DDZSCXJS();
    }
    public void S000_CALL_CISOCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_CALL_DDZSCXJS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSCXJS", DDCSCXJS);
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
