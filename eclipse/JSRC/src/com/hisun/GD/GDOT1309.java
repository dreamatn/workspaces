package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1309 {
    String CPN_GD_S_TRAC_PROC = "GD-S-TRAC-PROC";
    String WS_ERR_MSG = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSTRAC GDCSTRAC = new GDCSTRAC();
    SCCGWA SCCGWA;
    GDB1300_AWA_1300 GDB1300_AWA_1300;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1309 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1300_AWA_1300>");
        GDB1300_AWA_1300 = (GDB1300_AWA_1300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_DEPOSIT_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1300_AWA_1300.TXFUCTYP);
        CEP.TRC(SCCGWA, GDB1300_AWA_1300.TXRSEQ);
        CEP.TRC(SCCGWA, GDB1300_AWA_1300.TXAMT);
        CEP.TRC(SCCGWA, GDB1300_AWA_1300.TXSTLT);
        CEP.TRC(SCCGWA, GDB1300_AWA_1300.TXTDD_AC);
        if (GDB1300_AWA_1300.TXFUCTYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1300_AWA_1300.TXRSEQ.trim().length() == 0 
            && GDB1300_AWA_1300.TXCTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSCL_BOTH_SPACE;
            S000_ERR_MSG_PROC();
        }
        if (GDB1300_AWA_1300.TXAMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (GDB1300_AWA_1300.TXSTLT == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STL_MTH_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1300_AWA_1300.TXTDD_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DEPOSIT_TRANSFER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSTRAC);
        GDCSTRAC.TXFUCTYP = GDB1300_AWA_1300.TXFUCTYP;
        GDCSTRAC.TXRSEQ = GDB1300_AWA_1300.TXRSEQ;
        GDCSTRAC.TXAC = GDB1300_AWA_1300.TXAC;
        GDCSTRAC.TXTMAC = GDB1300_AWA_1300.TXTMAC;
        GDCSTRAC.TXSEQ = GDB1300_AWA_1300.TXSEQ;
        GDCSTRAC.TXCCY = GDB1300_AWA_1300.TXCCY;
        GDCSTRAC.TXTYP = GDB1300_AWA_1300.TXTYP;
        GDCSTRAC.TXSEQ = GDB1300_AWA_1300.TXSEQ;
        GDCSTRAC.TXCTA_NO = GDB1300_AWA_1300.TXCTA_NO;
        GDCSTRAC.TXREF_NO = GDB1300_AWA_1300.TXREF_NO;
        GDCSTRAC.TXAMT = GDB1300_AWA_1300.TXAMT;
        GDCSTRAC.TXSTLT = GDB1300_AWA_1300.TXSTLT;
        GDCSTRAC.TXTDD_AC = GDB1300_AWA_1300.TXTDD_AC;
        GDCSTRAC.SYS_NO = GDB1300_AWA_1300.TXSYS_NO;
        GDCSTRAC.TXINT_F = GDB1300_AWA_1300.TXINT_F;
        GDCSTRAC.INT_AC = GDB1300_AWA_1300.TXINT_AC;
        GDCSTRAC.CANL_FLG = GDB1300_AWA_1300.CANL_FLG;
        GDCSTRAC.RMN_AC = GDB1300_AWA_1300.TXRMN_AC;
        GDCSTRAC.TXMMO = GDB1300_AWA_1300.TXMMO;
        GDCSTRAC.TXSMR = GDB1300_AWA_1300.TXSMR;
        S000_CALL_GDZSTRAC();
    }
    public void S000_CALL_GDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GD_S_TRAC_PROC, GDCSTRAC);
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
