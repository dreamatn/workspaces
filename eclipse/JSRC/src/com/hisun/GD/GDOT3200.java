package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT3200 {
    String CPN_GD_SRV_GDZSBAAC = "GD-SRV-GDZSBAAC";
    String WS_ERR_MSG = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSTRAC GDCSTRAC = new GDCSTRAC();
    SCCGWA SCCGWA;
    GDB3200_AWA_3200 GDB3200_AWA_3200;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT3200 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB3200_AWA_3200>");
        GDB3200_AWA_3200 = (GDB3200_AWA_3200) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_DEPOSIT_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB3200_AWA_3200.TXFUCTYP);
        CEP.TRC(SCCGWA, GDB3200_AWA_3200.TXRSEQ);
        CEP.TRC(SCCGWA, GDB3200_AWA_3200.TXAMT);
        CEP.TRC(SCCGWA, GDB3200_AWA_3200.TXSTLT);
        CEP.TRC(SCCGWA, GDB3200_AWA_3200.TXCR_AC);
        if (GDB3200_AWA_3200.TXFUCTYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_FUNC_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB3200_AWA_3200.TXRSEQ.trim().length() == 0 
            && GDB3200_AWA_3200.TXCTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSCL_BOTH_SPACE;
            S000_ERR_MSG_PROC();
        }
        if (GDB3200_AWA_3200.TXPN_AMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (GDB3200_AWA_3200.TXSTLT == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STL_MTH_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB3200_AWA_3200.TXCR_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DEPOSIT_TRANSFER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSTRAC);
        GDCSTRAC.TXFUCTYP = GDB3200_AWA_3200.TXFUCTYP;
        GDCSTRAC.TXRSEQ = GDB3200_AWA_3200.TXRSEQ;
        GDCSTRAC.TXAC = GDB3200_AWA_3200.TX_AC;
        GDCSTRAC.TXTMAC = GDB3200_AWA_3200.TXTMAC;
        GDCSTRAC.TXSEQ = GDB3200_AWA_3200.TXSEQ;
        GDCSTRAC.TXCCY = GDB3200_AWA_3200.TXCCY;
        GDCSTRAC.TXTYP = GDB3200_AWA_3200.CCY_TYP;
        GDCSTRAC.TXCTA_NO = GDB3200_AWA_3200.TXCTA_NO;
        GDCSTRAC.TXREF_NO = GDB3200_AWA_3200.TXREF_NO;
        GDCSTRAC.TXAMT = GDB3200_AWA_3200.TXAMT;
        GDCSTRAC.TXSTLT = GDB3200_AWA_3200.TXSTLT;
        GDCSTRAC.TXTDD_AC = GDB3200_AWA_3200.TXCR_AC;
        GDCSTRAC.TXPN_AMT = GDB3200_AWA_3200.TXPN_AMT;
        GDCSTRAC.TXALLO_F = GDB3200_AWA_3200.TXALLO_F;
        GDCSTRAC.TXALLO_B = GDB3200_AWA_3200.TXALLO_B;
        GDCSTRAC.TXBR_TYP = GDB3200_AWA_3200.TXBR_TYP;
        GDCSTRAC.TXPN_MTH = GDB3200_AWA_3200.TXPN_MTH;
        GDCSTRAC.TXINT_F = GDB3200_AWA_3200.INT_FLG;
        GDCSTRAC.INT_AC = GDB3200_AWA_3200.INT_AC;
        GDCSTRAC.CANL_FLG = GDB3200_AWA_3200.CANL_FLG;
        GDCSTRAC.RMN_AC = GDB3200_AWA_3200.TXRMN_AC;
        GDCSTRAC.TXMMO = GDB3200_AWA_3200.TXMMO;
        GDCSTRAC.TXSMR = GDB3200_AWA_3200.TXRMK;
        S000_CALL_GDZSTRAC();
    }
    public void S000_CALL_GDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GD_SRV_GDZSBAAC, GDCSTRAC);
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
