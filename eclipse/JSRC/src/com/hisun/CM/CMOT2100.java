package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT2100 {
    int JIBS_tmp_int;
    CMOT2100_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new CMOT2100_WS_TEMP_VARIABLE();
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    CMCSBTRF CMCSBTRF = new CMCSBTRF();
    SCCGWA SCCGWA;
    CMB2100_AWA_2100 CMB2100_AWA_2100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMOT2100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB2100_AWA_2100>");
        CMB2100_AWA_2100 = (CMB2100_AWA_2100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B030_AC_TRANSFER_PROC();
    }
    public void B030_AC_TRANSFER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMCSBTRF);
        WS_TEMP_VARIABLE.WS_LEN = 3075;
        CEP.TRC(SCCGWA, CMB2100_AWA_2100.TXT_BIG);
        if (CMB2100_AWA_2100.TXT_BIG == null) CMB2100_AWA_2100.TXT_BIG = "";
        JIBS_tmp_int = CMB2100_AWA_2100.TXT_BIG.length();
        for (int i=0;i<5000-JIBS_tmp_int;i++) CMB2100_AWA_2100.TXT_BIG += " ";
        IBS.CPY2CLS(SCCGWA, CMB2100_AWA_2100.TXT_BIG.substring(0, WS_TEMP_VARIABLE.WS_LEN), CMCSBTRF);
        CEP.TRC(SCCGWA, CMCSBTRF.OTH_AC_NAME);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && CMCSBTRF.CHNL.equalsIgnoreCase("CLK")) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_TR_CHNL_CHK;
            S000_ERR_MSG_PROC();
        }
        S000_CALL_CMZSBTRF();
    }
    public void S000_CALL_CMZSBTRF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-SVR-AC-BTRF", CMCSBTRF);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
