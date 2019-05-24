package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1064 {
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
    DCB1064_AWA_1064 DCB1064_AWA_1064;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.FUNC);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.CARDNO);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.BANK_FLG);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.AC_NO);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.CI_NO);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.CI_NM);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.BR);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.OTH_BK);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.BK_FLG_N);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.AC_NO_N);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.CI_NM_N);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.BK_N);
        CEP.TRC(SCCGWA, DCB1064_AWA_1064.OTH_BK_N);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DCOT1064 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1064_AWA_1064>");
        DCB1064_AWA_1064 = (DCB1064_AWA_1064) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_TRANS_DATA_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCB1064_AWA_1064.FUNC != 'U') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (DCB1064_AWA_1064.CARDNO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCB1064_AWA_1064.BANK_FLG == ' ' 
            || DCB1064_AWA_1064.BK_FLG_N == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_BANK_FLG;
            S000_ERR_MSG_PROC();
        }
        if (DCB1064_AWA_1064.AC_NO.trim().length() == 0 
            || DCB1064_AWA_1064.AC_NO_N.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            S000_ERR_MSG_PROC();
        }
        if (DCB1064_AWA_1064.CI_NM.trim().length() == 0 
            || DCB1064_AWA_1064.CI_NM_N.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CI_NAME;
            S000_ERR_MSG_PROC();
        }
        if (DCB1064_AWA_1064.BANK_FLG == '1') {
            if (DCB1064_AWA_1064.BR == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_BR;
                S000_ERR_MSG_PROC();
            }
        } else if (DCB1064_AWA_1064.BANK_FLG == '2') {
            if (DCB1064_AWA_1064.OTH_BK.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_OTH_BR;
                S000_ERR_MSG_PROC();
            }
        }
        if (DCB1064_AWA_1064.BK_FLG_N == '1') {
            if (DCB1064_AWA_1064.BK_N == ' ') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_BR;
                S000_ERR_MSG_PROC();
            }
        } else if (DCB1064_AWA_1064.BK_FLG_N == '2') {
            if (DCB1064_AWA_1064.OTH_BK_N.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_OTH_BR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSACNO);
        DCCSACNO.FUNC = DCB1064_AWA_1064.FUNC;
        DCCSACNO.CARD_NO = DCB1064_AWA_1064.CARDNO;
        DCCSACNO.BANK_FLG = DCB1064_AWA_1064.BANK_FLG;
        DCCSACNO.AC_NO = DCB1064_AWA_1064.AC_NO;
        DCCSACNO.CI_NO = DCB1064_AWA_1064.CI_NO;
        DCCSACNO.CI_CNAME = DCB1064_AWA_1064.CI_NM;
        DCCSACNO.BR = DCB1064_AWA_1064.BR;
        DCCSACNO.OTH_BANK_NO = DCB1064_AWA_1064.OTH_BK;
        DCCSACNO.BANK_FLG_N = DCB1064_AWA_1064.BK_FLG_N;
        DCCSACNO.AC_NO_N = DCB1064_AWA_1064.AC_NO_N;
        DCCSACNO.CI_CNAME_N = DCB1064_AWA_1064.CI_NM_N;
        DCCSACNO.BR_N = DCB1064_AWA_1064.BK_N;
        DCCSACNO.OTH_BANK_NO_N = DCB1064_AWA_1064.OTH_BK_N;
        CEP.TRC(SCCGWA, DCCSACNO.BANK_FLG_N);
        CEP.TRC(SCCGWA, DCCSACNO.AC_NO_N);
        CEP.TRC(SCCGWA, DCCSACNO.CI_CNAME_N);
        CEP.TRC(SCCGWA, DCCSACNO.BR_N);
        CEP.TRC(SCCGWA, DCCSACNO.OTH_BANK_NO_N);
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
