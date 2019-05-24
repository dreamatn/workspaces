package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1450 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_GD_S_TRAC_PROC = "GD-SRV-GDSBPAC";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    String WS_CR_AC = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSBPAC GDCSBPAC = new GDCSBPAC();
    SCCGWA SCCGWA;
    GDB1450_AWA_1450 GDB1450_AWA_1450;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1450 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1450_AWA_1450>");
        GDB1450_AWA_1450 = (GDB1450_AWA_1450) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_DEPOSIT_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1450_AWA_1450.MN_FLG);
        CEP.TRC(SCCGWA, GDB1450_AWA_1450.AP_REF);
        CEP.TRC(SCCGWA, GDB1450_AWA_1450.TXAMT_S);
        CEP.TRC(SCCGWA, GDB1450_AWA_1450.TX_CRNO);
        if (GDB1450_AWA_1450.MN_FLG == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.DT_ONOFF_FLG_IPT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1450_AWA_1450.MN_FLG == 'M') {
            if (GDB1450_AWA_1450.TXAMT_S == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
                S000_ERR_MSG_PROC();
            }
        }
        if (GDB1450_AWA_1450.TX_CRNO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CNTR_NO_IPT;
            S000_ERR_MSG_PROC();
        }
        if (WS_CR_AC == null) WS_CR_AC = "";
        JIBS_tmp_int = WS_CR_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CR_AC += " ";
        JIBS_tmp_str[0] = "" + GDB1450_AWA_1450.TX_CRBR;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_CR_AC = JIBS_tmp_str[0] + WS_CR_AC.substring(6);
        if (WS_CR_AC == null) WS_CR_AC = "";
        JIBS_tmp_int = WS_CR_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CR_AC += " ";
        if (GDB1450_AWA_1450.TXCCY == null) GDB1450_AWA_1450.TXCCY = "";
        JIBS_tmp_int = GDB1450_AWA_1450.TXCCY.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) GDB1450_AWA_1450.TXCCY += " ";
        WS_CR_AC = WS_CR_AC.substring(0, 7 - 1) + GDB1450_AWA_1450.TXCCY + WS_CR_AC.substring(7 + 3 - 1);
        if (WS_CR_AC == null) WS_CR_AC = "";
        JIBS_tmp_int = WS_CR_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CR_AC += " ";
        if (GDB1450_AWA_1450.TX_CRNO == null) GDB1450_AWA_1450.TX_CRNO = "";
        JIBS_tmp_int = GDB1450_AWA_1450.TX_CRNO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) GDB1450_AWA_1450.TX_CRNO += " ";
        WS_CR_AC = WS_CR_AC.substring(0, 10 - 1) + GDB1450_AWA_1450.TX_CRNO + WS_CR_AC.substring(10 + 10 - 1);
        if (WS_CR_AC == null) WS_CR_AC = "";
        JIBS_tmp_int = WS_CR_AC.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CR_AC += " ";
        WS_CR_AC = WS_CR_AC.substring(0, 20 - 1) + "000001" + WS_CR_AC.substring(20 + 6 - 1);
        CEP.TRC(SCCGWA, WS_CR_AC);
    }
    public void B020_DEPOSIT_TRANSFER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSBPAC);
        CEP.TRC(SCCGWA, GDB1450_AWA_1450.REQ_DATE);
        GDCSBPAC.REQ_DATE = GDB1450_AWA_1450.REQ_DATE;
        GDCSBPAC.TR_BR = GDB1450_AWA_1450.TR_BR;
        GDCSBPAC.MN_FLG = GDB1450_AWA_1450.MN_FLG;
        GDCSBPAC.TX_CRAC = WS_CR_AC;
        CEP.TRC(SCCGWA, GDCSBPAC.TX_CRAC);
        GDCSBPAC.TX_CCY = GDB1450_AWA_1450.TXCCY;
        GDCSBPAC.TX_CCYTYP = GDB1450_AWA_1450.TXTYP;
        GDCSBPAC.TXAMT = GDB1450_AWA_1450.TXAMT_S;
        GDCSBPAC.PNAMT = GDB1450_AWA_1450.PN_AMT;
        GDCSBPAC.TX_CRBR = GDB1450_AWA_1450.TX_CRBR;
        GDCSBPAC.TX_CRNO = GDB1450_AWA_1450.TX_CRNO;
        GDCSBPAC.TX_BR = GDB1450_AWA_1450.TXBR;
        GDCSBPAC.ADV_FLG = GDB1450_AWA_1450.TXADV_F;
        GDCSBPAC.ADV_AC = "" + GDB1450_AWA_1450.TXADV_AC;
        JIBS_tmp_int = GDCSBPAC.ADV_AC.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) GDCSBPAC.ADV_AC = "0" + GDCSBPAC.ADV_AC;
        GDCSBPAC.CNTR_NO = GDB1450_AWA_1450.CNTR_NO;
        GDCSBPAC.TXMMO = GDB1450_AWA_1450.TXMMO;
        GDCSBPAC.TXRMK = GDB1450_AWA_1450.TXRMK;
        GDCSBPAC.ADV_TYPE = GDB1450_AWA_1450.ADV_TYPE;
        GDCSBPAC.CI_NO = GDB1450_AWA_1450.CI_NO;
        GDCSBPAC.PROD_TYP = GDB1450_AWA_1450.PROD_TYP;
        GDCSBPAC.OCAL_PD = GDB1450_AWA_1450.OCAL_PD;
        GDCSBPAC.OCAL_UT = GDB1450_AWA_1450.OCAL_UT;
        GDCSBPAC.DW_BK_TP = GDB1450_AWA_1450.DW_BK_TP;
        GDCSBPAC.DRAW_ACT = GDB1450_AWA_1450.DRAW_ACT;
        GDCSBPAC.DRAW_AC = GDB1450_AWA_1450.DRAW_AC;
        GDCSBPAC.PA_BK_TP = GDB1450_AWA_1450.PA_BK_TP;
        GDCSBPAC.PAY_AC_T = GDB1450_AWA_1450.PAY_AC_T;
        GDCSBPAC.PAY_AC = GDB1450_AWA_1450.PAY_AC;
        GDCSBPAC.PEN_RRAT = GDB1450_AWA_1450.PEN_RRAT;
        GDCSBPAC.PEN_TYP = GDB1450_AWA_1450.PEN_TYP;
        GDCSBPAC.PEN_RATT = GDB1450_AWA_1450.PEN_RATT;
        GDCSBPAC.PEN_RATC = GDB1450_AWA_1450.PEN_RATC;
        GDCSBPAC.PEN_SPR = GDB1450_AWA_1450.PEN_SPR;
        GDCSBPAC.PEN_PCT = GDB1450_AWA_1450.PEN_PCT;
        GDCSBPAC.PEN_IRAT = GDB1450_AWA_1450.PEN_IRAT;
        GDCSBPAC.CPND_USE = GDB1450_AWA_1450.CPND_USE;
        GDCSBPAC.INT_MTH = GDB1450_AWA_1450.INT_MTH;
        GDCSBPAC.CPND_RTY = GDB1450_AWA_1450.CPND_RTY;
        GDCSBPAC.CPND_TYP = GDB1450_AWA_1450.CPND_TYP;
        GDCSBPAC.CPNDRATT = GDB1450_AWA_1450.CPNDRATT;
        GDCSBPAC.CPNDRATC = GDB1450_AWA_1450.CPNDRATC;
        GDCSBPAC.CPND_SPR = GDB1450_AWA_1450.CPND_SPR;
        GDCSBPAC.CPND_PCT = GDB1450_AWA_1450.CPND_PCT;
        GDCSBPAC.CPNDIRAT = GDB1450_AWA_1450.CPNDIRAT;
        for (WS_CNT = 1; WS_CNT <= 5; WS_CNT += 1) {
            GDCSBPAC.DR_SEC[WS_CNT-1].TX_DR_AC = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TX_DRAC;
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, GDCSBPAC.DR_SEC[WS_CNT-1].TX_DR_AC);
            GDCSBPAC.DR_SEC[WS_CNT-1].TX_SEQ = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXSEQ_3;
            GDCSBPAC.DR_SEC[WS_CNT-1].TX_FLG = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TX_FLG;
            GDCSBPAC.DR_SEC[WS_CNT-1].TXALLO_FLG = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXALLO_F;
            GDCSBPAC.DR_SEC[WS_CNT-1].TXALLO_BR = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXALLO_B;
            GDCSBPAC.DR_SEC[WS_CNT-1].TXBR_TYP = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXBR_TYP;
            GDCSBPAC.DR_SEC[WS_CNT-1].SYS_NO = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXSYS_NO;
            GDCSBPAC.DR_SEC[WS_CNT-1].TXRSEQ = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXRSEQ;
            GDCSBPAC.DR_SEC[WS_CNT-1].TXCTA_NO = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXCTA_NO;
            GDCSBPAC.DR_SEC[WS_CNT-1].TXREF_NO = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXREF_NO;
            GDCSBPAC.DR_SEC[WS_CNT-1].DEB_FLG = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXDEB_F;
            GDCSBPAC.DR_SEC[WS_CNT-1].TXINT_AC = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXINT_AC;
            GDCSBPAC.DR_SEC[WS_CNT-1].AC_DRAMT = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXAMT;
            GDCSBPAC.DR_SEC[WS_CNT-1].CAN_FLG = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXCAN_F;
            GDCSBPAC.DR_SEC[WS_CNT-1].TXRES_AC = GDB1450_AWA_1450.DR_SEC[WS_CNT-1].TXREV_AC;
        }
        S000_CALL_GDZSBPAC();
    }
    public void S000_CALL_GDZSBPAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GD_S_TRAC_PROC, GDCSBPAC);
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
