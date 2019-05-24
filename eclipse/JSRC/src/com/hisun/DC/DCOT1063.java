package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1063 {
    String CPN_CCY_QUERY = "BP-INQUIRE-CCY";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    int WS_TMP_DT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    DCCSACNO DCCSACNO = new DCCSACNO();
    SCCGWA SCCGWA;
    DCB1063_AWA_1063 DCB1063_AWA_1063;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT1063 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1063_AWA_1063>");
        DCB1063_AWA_1063 = (DCB1063_AWA_1063) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCB1063_AWA_1063.FUNC != 'Q') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (DCB1063_AWA_1063.CARDNO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCB1063_AWA_1063.BANK_FLG == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_BANK_FLG;
            S000_ERR_MSG_PROC();
        }
        if (DCB1063_AWA_1063.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCB1063_AWA_1063.CI_NM.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CI_NAME;
            S000_ERR_MSG_PROC();
        }
        if (DCB1063_AWA_1063.BANK_FLG == '1') {
            if (DCB1063_AWA_1063.BR == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_BR;
                S000_ERR_MSG_PROC();
            }
        } else if (DCB1063_AWA_1063.BANK_FLG == '2') {
            if (DCB1063_AWA_1063.OTH_BK.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_OTH_BR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSACNO);
        DCCSACNO.FUNC = DCB1063_AWA_1063.FUNC;
        DCCSACNO.CARD_NO = DCB1063_AWA_1063.CARDNO;
        DCCSACNO.BANK_FLG = DCB1063_AWA_1063.BANK_FLG;
        DCCSACNO.AC_NO = DCB1063_AWA_1063.AC_NO;
        DCCSACNO.CI_NO = DCB1063_AWA_1063.CI_NO;
        DCCSACNO.CI_CNAME = DCB1063_AWA_1063.CI_NM;
        DCCSACNO.BR = DCB1063_AWA_1063.BR;
        DCCSACNO.OTH_BANK_NO = DCB1063_AWA_1063.OTH_BK;
        S000_CALL_DCZSACNO();
    }
    public void S000_CALL_DCZSACNO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-DCZSACNO", DCCSACNO);
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
