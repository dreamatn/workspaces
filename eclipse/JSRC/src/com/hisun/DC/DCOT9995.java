package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT9995 {
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCUMDLF DCCUMDLF = new DCCUMDLF();
    SCCGWA SCCGWA;
    DCB9995_AWA_9995 DCB9995_AWA_9995;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT9995 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB9995_AWA_9995>");
        DCB9995_AWA_9995 = (DCB9995_AWA_9995) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B030_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB9995_AWA_9995.AGT_NO);
        CEP.TRC(SCCGWA, DCB9995_AWA_9995.LN_AC);
        if (DCB9995_AWA_9995.AGT_NO.trim().length() == 0 
            && DCB9995_AWA_9995.LN_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROL_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUMDLF);
        DCCUMDLF.IO_AREA.FUNC = 'Q';
        DCCUMDLF.IO_AREA.AGT_NO = DCB9995_AWA_9995.AGT_NO;
        DCCUMDLF.IO_AREA.LN_AC = DCB9995_AWA_9995.LN_AC;
        CEP.TRC(SCCGWA, DCCUMDLF.IO_AREA.AGT_NO);
        CEP.TRC(SCCGWA, DCCUMDLF.IO_AREA.LN_AC);
        S000_CALL_DCZUMDLF();
    }
    public void S000_CALL_DCZUMDLF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-DCZUMDLF", DCCUMDLF);
        if (DCCUMDLF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUMDLF.RC);
            S000_ERR_MSG_PROC();
        }
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
