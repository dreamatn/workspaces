package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2020 {
    String K_OUTPUT_FMT = "BAY01";
    String K_HIS_REMARKS = "BILL STATUS CHANGE";
    String WS_ERR_MSG = " ";
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BACUCRTC BACUCRTC = new BACUCRTC();
    BACUIMPA BACUIMPA = new BACUIMPA();
    SCCGWA SCCGWA;
    BAB2020_AWA_2020 BAB2020_AWA_2020;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA, BAB2020_AWA_2020 BAB2020_AWA_2020) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BAB2020_AWA_2020 = BAB2020_AWA_2020;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BAOT2020 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2020_AWA_2020>");
        BAB2020_AWA_2020 = (BAB2020_AWA_2020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        CEP.TRC(SCCGWA, BAB2020_AWA_2020.BILL_NO);
        CEP.TRC(SCCGWA, BAB2020_AWA_2020.RMK);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUCRTC);
        BACUCRTC.DATA.BILL_NO = BAB2020_AWA_2020.BILL_NO;
        BACUCRTC.DATA.RMK = BAB2020_AWA_2020.RMK;
        S000_CALL_BAZUCRTC();
    }
    public void S000_CALL_BAZUCRTC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-CRT-ECRD", BACUCRTC);
        CEP.TRC(SCCGWA, "121212");
        CEP.TRC(SCCGWA, BACUCRTC.RC);
        if (BACUCRTC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "343434");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUCRTC.RC);
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
