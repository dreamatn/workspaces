package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT0031 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    int WS_I = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCSTSMC DCCSTSMC = new DCCSTSMC();
    SCCGWA SCCGWA;
    DCB0031_AWA_0031 DCB0031_AWA_0031;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BEGENING");
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT0031 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB0031_AWA_0031>");
        DCB0031_AWA_0031 = (DCB0031_AWA_0031) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSTSMC);
        CEP.TRC(SCCGWA, DCB0031_AWA_0031.CRT_TYP);
        CEP.TRC(SCCGWA, DCB0031_AWA_0031.CRT_FLG);
        CEP.TRC(SCCGWA, DCB0031_AWA_0031.CARD_NO);
        CEP.TRC(SCCGWA, DCB0031_AWA_0031.CI_CNM);
        CEP.TRC(SCCGWA, DCB0031_AWA_0031.ID_TYPE);
        CEP.TRC(SCCGWA, DCB0031_AWA_0031.ID_NO);
        CEP.TRC(SCCGWA, DCB0031_AWA_0031.PHONE);
        DCCSTSMC.CRT_TYP = DCB0031_AWA_0031.CRT_TYP;
        DCCSTSMC.CRT_FLG = DCB0031_AWA_0031.CRT_FLG;
        DCCSTSMC.CARD_NO = DCB0031_AWA_0031.CARD_NO;
        DCCSTSMC.CI_CNM = DCB0031_AWA_0031.CI_CNM;
        DCCSTSMC.ID_TYPE = DCB0031_AWA_0031.ID_TYPE;
        DCCSTSMC.ID_NO = DCB0031_AWA_0031.ID_NO;
        DCCSTSMC.PHONE = DCB0031_AWA_0031.PHONE;
        DCCSTSMC.CARD_MODU = DCB0031_AWA_0031.MODU;
        DCCSTSMC.ISSUE_MTH = DCB0031_AWA_0031.IS_MTH;
        CEP.TRC(SCCGWA, DCCSTSMC.CRT_TYP);
        CEP.TRC(SCCGWA, DCCSTSMC.CRT_FLG);
        CEP.TRC(SCCGWA, DCCSTSMC.CARD_NO);
        CEP.TRC(SCCGWA, DCCSTSMC.CI_CNM);
        CEP.TRC(SCCGWA, DCCSTSMC.ID_TYPE);
        CEP.TRC(SCCGWA, DCCSTSMC.ID_NO);
        CEP.TRC(SCCGWA, DCCSTSMC.PHONE);
        S000_CALL_DCZSTSMC();
    }
    public void S000_CALL_DCZSTSMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-S-DCZSTSMC", DCCSTSMC);
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
