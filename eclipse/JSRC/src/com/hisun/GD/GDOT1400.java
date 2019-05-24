package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1400 {
    String CPN_GD_S_TRAC_PROC = "GD-S-PAAC-PROC";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSPAAC GDCSPAAC = new GDCSPAAC();
    SCCGWA SCCGWA;
    GDB1400_AWA_1400 GDB1400_AWA_1400;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1400 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1400_AWA_1400>");
        GDB1400_AWA_1400 = (GDB1400_AWA_1400) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_DEPOSIT_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1400_AWA_1400.TXAMT);
        CEP.TRC(SCCGWA, GDB1400_AWA_1400.TX_CRAC);
        if (GDB1400_AWA_1400.TXAMT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (GDB1400_AWA_1400.TX_CRAC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DEPOSIT_TRANSFER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSPAAC);
        GDCSPAAC.TX_CRAC = GDB1400_AWA_1400.TX_CRAC;
        GDCSPAAC.TXCCY = GDB1400_AWA_1400.TXCCY;
        GDCSPAAC.TXCTYP = GDB1400_AWA_1400.TXCTYP;
        GDCSPAAC.TXAMT = GDB1400_AWA_1400.TXAMT;
        GDCSPAAC.ADV_FLG = GDB1400_AWA_1400.ADV_FLG;
        GDCSPAAC.TXMMO = GDB1400_AWA_1400.TXMMO;
        GDCSPAAC.TXRMK = GDB1400_AWA_1400.TXRMK;
        for (WS_CNT = 1; WS_CNT <= 10; WS_CNT += 1) {
            GDCSPAAC.DR_SEC[WS_CNT-1].TX_DRAC = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TX_DRAC;
            GDCSPAAC.DR_SEC[WS_CNT-1].TXSEQ_3 = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TXSEQ_3;
            GDCSPAAC.DR_SEC[WS_CNT-1].TX_FLG = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TX_FLG;
            GDCSPAAC.DR_SEC[WS_CNT-1].TXALLO_F = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TXALLO_F;
            GDCSPAAC.DR_SEC[WS_CNT-1].TXALLO_B = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TXALLO_B;
            GDCSPAAC.DR_SEC[WS_CNT-1].TXBR_TYP = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TXBR_TYP;
            GDCSPAAC.DR_SEC[WS_CNT-1].TXRSEQ = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TXRSEQ;
            GDCSPAAC.DR_SEC[WS_CNT-1].TXCTA_NO = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TXCTA_NO;
            GDCSPAAC.DR_SEC[WS_CNT-1].TXREF_NO = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TXREF_NO;
            GDCSPAAC.DR_SEC[WS_CNT-1].DEB_FLG = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].DEB_FLG;
            GDCSPAAC.DR_SEC[WS_CNT-1].TXINT_AC = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TXINT_AC;
            GDCSPAAC.DR_SEC[WS_CNT-1].CAN_FLG = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].CAN_FLG;
            GDCSPAAC.DR_SEC[WS_CNT-1].TXRES_AC = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].TXRES_AC;
            GDCSPAAC.DR_SEC[WS_CNT-1].DRAMT = GDB1400_AWA_1400.DR_SEC[WS_CNT-1].DRAMT;
        }
        S000_CALL_GDZSPAAC();
    }
    public void S000_CALL_GDZSPAAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GD_S_TRAC_PROC, GDCSPAAC);
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
