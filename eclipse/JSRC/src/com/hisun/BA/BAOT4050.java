package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT4050 {
    String K_OUTPUT_FMT = "BAY01";
    String K_HIS_REMARKS = "BILL STATUS CHANGE";
    BAOT4050_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BAOT4050_WS_TEMP_VARIABLE();
    BAOT4050_WS_OUT_DATA WS_OUT_DATA = new BAOT4050_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BACUIMPA BACUIMPA = new BACUIMPA();
    SCCGWA SCCGWA;
    BAB4050_AWA_4050 BAB4050_AWA_4050;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA, BAB4050_AWA_4050 BAB4050_AWA_4050) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BAB4050_AWA_4050 = BAB4050_AWA_4050;
        CEP.TRC(SCCGWA);
        A00_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BAOT4050 return!");
        Z_RET();
    }
    public void A00_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB4050_AWA_4050>");
        BAB4050_AWA_4050 = (BAB4050_AWA_4050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        CEP.TRC(SCCGWA, BAB4050_AWA_4050.BILL_NO);
        CEP.TRC(SCCGWA, BAB4050_AWA_4050.OPT_CODE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BAB4050_AWA_4050.OPT_CODE != '1' 
            && BAB4050_AWA_4050.OPT_CODE != '2') {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_OPT_CODE_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUIMPA);
        BACUIMPA.DATA.BILL_NO = BAB4050_AWA_4050.BILL_NO;
        BACUIMPA.DATA.FUN_COD = BAB4050_AWA_4050.OPT_CODE;
        S000_CALL_BAZUIMPA();
    }
    public void S000_CALL_BAZUIMPA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-IMP-REM", BACUIMPA);
        if (BACUIMPA.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUIMPA.RC);
            S000_ERR_MSG_PROC();
        }
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
