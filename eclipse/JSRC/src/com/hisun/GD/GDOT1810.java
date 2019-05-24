package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1810 {
    DBParm DDTMST_RD;
    String CPN_TD_ZM_ACC_PROC = "TD-ZM-ACC-PROC";
    String WS_ERR_MSG = " ";
    double WS_CURR_BAL = 0;
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    GDCSCLDD GDCSCLDD = new GDCSCLDD();
    DDRMST DDRMST = new DDRMST();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    SCCGWA SCCGWA;
    GDB1810_AWA_1810 GDB1810_AWA_1810;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1810 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1810_AWA_1810>");
        GDB1810_AWA_1810 = (GDB1810_AWA_1810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B015_CHECK_AC_PROC();
        B020_CLOSE_DDAC_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1810_AWA_1810.TXDRAC);
        CEP.TRC(SCCGWA, GDB1810_AWA_1810.TXAC_NM1);
        CEP.TRC(SCCGWA, GDB1810_AWA_1810.TXCCY);
        CEP.TRC(SCCGWA, GDB1810_AWA_1810.TXTYP);
        CEP.TRC(SCCGWA, GDB1810_AWA_1810.TXSTLT);
        CEP.TRC(SCCGWA, GDB1810_AWA_1810.TXCRAC);
        CEP.TRC(SCCGWA, GDB1810_AWA_1810.TXAC_NM2);
        CEP.TRC(SCCGWA, GDB1810_AWA_1810.TXMMO);
        CEP.TRC(SCCGWA, GDB1810_AWA_1810.TXRMK);
        if (GDB1810_AWA_1810.TXDRAC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_DR_AC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1810_AWA_1810.TXCCY.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (GDB1810_AWA_1810.TXSTLT == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STLT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1810_AWA_1810.TXCRAC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CR_AC_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B015_CHECK_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = GDB1810_AWA_1810.TXDRAC;
        DDCIQBAL.DATA.CCY = GDB1810_AWA_1810.TXCCY;
        DDCIQBAL.DATA.CCY_TYPE = GDB1810_AWA_1810.TXTYP;
        S000_CALL_DDZIQBAL();
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
        WS_CURR_BAL = DDCIQBAL.DATA.CURR_BAL;
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = GDB1810_AWA_1810.TXDRAC;
        T000_READ_DDTMST();
        CEP.TRC(SCCGWA, DDRMST.YW_TYP);
    }
    public void B020_CLOSE_DDAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSCLDD);
        GDCSCLDD.DRAC = GDB1810_AWA_1810.TXDRAC;
        GDCSCLDD.DRAC_NM = GDB1810_AWA_1810.TXAC_NM1;
        GDCSCLDD.CCY = GDB1810_AWA_1810.TXCCY;
        GDCSCLDD.CCY_TYP = GDB1810_AWA_1810.TXTYP;
        GDCSCLDD.STLT = GDB1810_AWA_1810.TXSTLT;
        GDCSCLDD.CRAC = GDB1810_AWA_1810.TXCRAC;
        GDCSCLDD.CRAC_NM = GDB1810_AWA_1810.TXAC_NM2;
        GDCSCLDD.MMO = GDB1810_AWA_1810.TXMMO;
        GDCSCLDD.RMK = GDB1810_AWA_1810.TXRMK;
        GDCSCLDD.BAL = WS_CURR_BAL;
        S000_CALL_GDZSCLDD();
    }
    public void S000_CALL_GDZSCLDD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSCLDD", GDCSCLDD);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "AC =");
            CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
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
