package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1410 {
    String CPN_GD_S_DTRE_PROC = "GD-S-DTRE-PROC";
    String WS_ERR_MSG = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSDTRE GDCSDTRE = new GDCSDTRE();
    SCCGWA SCCGWA;
    GDB1410_AWA_1410 GDB1410_AWA_1410;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1410 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1410_AWA_1410>");
        GDB1410_AWA_1410 = (GDB1410_AWA_1410) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        B020_DEPOSIT_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1410_AWA_1410.TXDD_AC);
        CEP.TRC(SCCGWA, GDB1410_AWA_1410.TXREF_NO);
        CEP.TRC(SCCGWA, GDB1410_AWA_1410.TXSYS_NO);
        CEP.TRC(SCCGWA, GDB1410_AWA_1410.TXCTA_NO);
        CEP.TRC(SCCGWA, GDB1410_AWA_1410.TXREF_NO);
        CEP.TRC(SCCGWA, GDB1410_AWA_1410.TXSTLT);
        CEP.TRC(SCCGWA, GDB1410_AWA_1410.TXPN_AMT);
        CEP.TRC(SCCGWA, GDB1410_AWA_1410.TXAMT);
        if (GDB1410_AWA_1410.TXDD_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1410_AWA_1410.TXCI_NM1.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_ACNM_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1410_AWA_1410.TXCCY.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1410_AWA_1410.TXCCY.trim().length() > 0 
            && GDB1410_AWA_1410.TXCTYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1410_AWA_1410.STL_MTH == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STL_MTH_INVAILD;
            S000_ERR_MSG_PROC();
        }
        if (GDB1410_AWA_1410.STL_MTH == '1' 
            && GDB1410_AWA_1410.TXREV_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_REV_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1410_AWA_1410.STL_MTH == '1' 
            && GDB1410_AWA_1410.TXAMT > GDB1410_AWA_1410.TXPN_AMT) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.TXAMT_BIGER_AVAMT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1410_AWA_1410.TXCTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSCL_BOTH_SPACE;
            S000_ERR_MSG_PROC();
        }
        if (GDB1410_AWA_1410.TXSTLT == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_STLT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1410_AWA_1410.TXAMT == 0 
            && GDB1410_AWA_1410.TXREF_NO.trim().length() > 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (GDB1410_AWA_1410.TXDT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_TXDT_M_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DEPOSIT_TRANSFER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSDTRE);
        GDCSDTRE.DRAC = GDB1410_AWA_1410.TXDD_AC;
        GDCSDTRE.AC_NM = GDB1410_AWA_1410.TXCI_NM1;
        GDCSDTRE.CCY = GDB1410_AWA_1410.TXCCY;
        GDCSDTRE.CCY_TYP = GDB1410_AWA_1410.TXCTYP;
        GDCSDTRE.STL_MTH = GDB1410_AWA_1410.STL_MTH;
        GDCSDTRE.REV_NO = GDB1410_AWA_1410.TXREV_NO;
        GDCSDTRE.SYS_NO = GDB1410_AWA_1410.TXSYS_NO;
        GDCSDTRE.CTA_NO = GDB1410_AWA_1410.TXCTA_NO;
        GDCSDTRE.REF_NO = GDB1410_AWA_1410.TXREF_NO;
        GDCSDTRE.STLT = GDB1410_AWA_1410.TXSTLT;
        GDCSDTRE.PN_AMT = GDB1410_AWA_1410.TXPN_AMT;
        GDCSDTRE.AMT = GDB1410_AWA_1410.TXAMT;
        GDCSDTRE.DT = GDB1410_AWA_1410.TXDT;
        GDCSDTRE.REMARK = GDB1410_AWA_1410.TXRMK;
        GDCSDTRE.MMO = GDB1410_AWA_1410.TXMMO;
        S000_CALL_GDZSDTRE();
    }
    public void S000_CALL_GDZSDTRE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GD_S_DTRE_PROC, GDCSDTRE);
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
