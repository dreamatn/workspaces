package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1200 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSDDDR GDCSDDDR = new GDCSDDDR();
    SCCGWA SCCGWA;
    GDB1200_AWA_1200 GDB1200_AWA_1200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "GDOT1200 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1200_AWA_1200>");
        GDB1200_AWA_1200 = (GDB1200_AWA_1200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_WITHDRAW_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXDRAC);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXCCY);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXTYP);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXAMT_S);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXSTLT);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXCRAC);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXINT_FL);
        CEP.TRC(SCCGWA, GDB1200_AWA_1200.TXINT_AC);
        if (GDB1200_AWA_1200.TXDRAC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            WS_FLD_NO = GDB1200_AWA_1200.TXDRAC_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB1200_AWA_1200.TXCCY.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            WS_FLD_NO = GDB1200_AWA_1200.TXCCY_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB1200_AWA_1200.TXAMT_S < 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB1200_AWA_1200.TXAMT_S == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_MST_IPT;
            WS_FLD_NO = GDB1200_AWA_1200.TXAMT_S_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB1200_AWA_1200.TXSTLT == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STL_MTH_M_INPUT;
            WS_FLD_NO = GDB1200_AWA_1200.TXSTLT_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB1200_AWA_1200.TXCRAC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            WS_FLD_NO = GDB1200_AWA_1200.TXCRAC_NO;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB1200_AWA_1200.TXCANL_F == '1') {
            if (GDB1200_AWA_1200.TXRMN_AC.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
                WS_FLD_NO = GDB1200_AWA_1200.TXCRAC_NO;
                CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
            }
        }
    }
    public void B200_WITHDRAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSDDDR);
        GDCSDDDR.DRAC = GDB1200_AWA_1200.TXDRAC;
        GDCSDDDR.AC_NM = GDB1200_AWA_1200.TXAC_NM;
        GDCSDDDR.CCY = GDB1200_AWA_1200.TXCCY;
        GDCSDDDR.CCY_TYP = GDB1200_AWA_1200.TXTYP;
        GDCSDDDR.AMT = GDB1200_AWA_1200.TXAMT_S;
        GDCSDDDR.STLT = GDB1200_AWA_1200.TXSTLT;
        GDCSDDDR.CRAC = GDB1200_AWA_1200.TXCRAC;
        GDCSDDDR.INT_FLG = GDB1200_AWA_1200.TXINT_FL;
        GDCSDDDR.INT_AC = GDB1200_AWA_1200.TXINT_AC;
        GDCSDDDR.CANL_FLG = GDB1200_AWA_1200.TXCANL_F;
        GDCSDDDR.RMN_AC = GDB1200_AWA_1200.TXRMN_AC;
        GDCSDDDR.MMO = GDB1200_AWA_1200.TXMMO;
        GDCSDDDR.REMARK = GDB1200_AWA_1200.TXSMR;
        S000_CALL_GDZSDDDR();
    }
    public void S000_CALL_GDZSDDDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSDDDR", GDCSDDDR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
