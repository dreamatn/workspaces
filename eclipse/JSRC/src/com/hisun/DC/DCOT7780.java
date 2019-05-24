package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT7780 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSDDCT DCCSDDCT = new DCCSDDCT();
    SCCGWA SCCGWA;
    DDB7780_AWA_7780 DDB7780_AWA_7780;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DCOT7780 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB7780_AWA_7780>");
        DDB7780_AWA_7780 = (DDB7780_AWA_7780) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DEDUCT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB7780_AWA_7780.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DUT_TYP_MUST_INPUT;
            WS_FLD_NO = DDB7780_AWA_7780.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7780_AWA_7780.FUNC != '1' 
            && DDB7780_AWA_7780.FUNC != '2' 
            && DDB7780_AWA_7780.FUNC != '3') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DUT_TYP_INVALID;
            WS_FLD_NO = DDB7780_AWA_7780.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7780_AWA_7780.FUNC == '2' 
            || DDB7780_AWA_7780.FUNC == '3') {
            if (DDB7780_AWA_7780.HLD_NO.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDNO_MUST_INPUT;
                WS_FLD_NO = DDB7780_AWA_7780.HLD_NO_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (DDB7780_AWA_7780.AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_AC_NO;
            WS_FLD_NO = DDB7780_AWA_7780.AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7780_AWA_7780.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ACCCY_MUST_INPUT;
            WS_FLD_NO = DDB7780_AWA_7780.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7780_AWA_7780.CCY_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYP_MUST_INPUT;
            WS_FLD_NO = DDB7780_AWA_7780.CCY_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7780_AWA_7780.ST_MTH == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ST_MTH_MUST_INPUT;
            WS_FLD_NO = DDB7780_AWA_7780.ST_MTH_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB7780_AWA_7780.ST_MTH == '1') {
            if (DDB7780_AWA_7780.CR_AC.trim().length() == 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CR_ACNO_MUST_INPUT;
                WS_FLD_NO = DDB7780_AWA_7780.CR_AC_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_DEDUCT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSDDCT);
        DCCSDDCT.INPUT_DATA.FUNC = DDB7780_AWA_7780.FUNC;
        DCCSDDCT.INPUT_DATA.AC = DDB7780_AWA_7780.AC;
        DCCSDDCT.INPUT_DATA.AC_SEQ = DDB7780_AWA_7780.AC_SEQ;
        DCCSDDCT.INPUT_DATA.HLD_NO = DDB7780_AWA_7780.HLD_NO;
        DCCSDDCT.INPUT_DATA.CCY = DDB7780_AWA_7780.CCY;
        DCCSDDCT.INPUT_DATA.CCY_TYPE = DDB7780_AWA_7780.CCY_TYP;
        DCCSDDCT.INPUT_DATA.TAMT = DDB7780_AWA_7780.TAMT;
        DCCSDDCT.INPUT_DATA.RHLD_TYP = DDB7780_AWA_7780.RHLD_TYP;
        DCCSDDCT.INPUT_DATA.CHG_NO = DDB7780_AWA_7780.CHG_NO;
        DCCSDDCT.INPUT_DATA.SPR_NM = DDB7780_AWA_7780.SPR_NM;
        DCCSDDCT.INPUT_DATA.RSN = DDB7780_AWA_7780.RSN;
        DCCSDDCT.INPUT_DATA.ST_MTH = DDB7780_AWA_7780.ST_MTH;
        DCCSDDCT.INPUT_DATA.CR_AC = DDB7780_AWA_7780.CR_AC;
        DCCSDDCT.INPUT_DATA.RLT_ACNM = DDB7780_AWA_7780.RLT_ACNM;
        DCCSDDCT.INPUT_DATA.RLT_BANK = DDB7780_AWA_7780.RLT_BANK;
        DCCSDDCT.INPUT_DATA.INT_AC = DDB7780_AWA_7780.INT_AC;
        DCCSDDCT.INPUT_DATA.RMK = DDB7780_AWA_7780.RMK;
        DCCSDDCT.INPUT_DATA.CHG_BR = DDB7780_AWA_7780.CHG_BR;
        DCCSDDCT.INPUT_DATA.DUCT_NM = DDB7780_AWA_7780.DUCT_NM;
        DCCSDDCT.INPUT_DATA.LAW_NM1 = DDB7780_AWA_7780.LAW_NM1;
        DCCSDDCT.INPUT_DATA.LAW_NO1 = DDB7780_AWA_7780.LAW_NO1;
        DCCSDDCT.INPUT_DATA.LAW_NM2 = DDB7780_AWA_7780.LAW_NM2;
        DCCSDDCT.INPUT_DATA.LAW_NO2 = DDB7780_AWA_7780.LAW_NO2;
        DCCSDDCT.INPUT_DATA.NARRATIVE = DDB7780_AWA_7780.NARR;
        S000_CALL_DCZSDDCT();
    }
    public void S000_CALL_DCZSDDCT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-DCZSDDCT", DCCSDDCT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
