package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1420 {
    String WS_ERR_MSG = " ";
    char WS_TXREA_FL = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    GDCSEQTR GDCSEQTR = new GDCSEQTR();
    SCCGWA SCCGWA;
    GDB1420_AWA_1420 GDB1420_AWA_1420;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1420 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1420_AWA_1420>");
        GDB1420_AWA_1420 = (GDB1420_AWA_1420) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1420_AWA_1420.PAGE_ROW);
        CEP.TRC(SCCGWA, GDB1420_AWA_1420.PAGE_NUM);
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((GDB1420_AWA_1420.PAGE_ROW > 25) 
            || (GDB1420_AWA_1420.PAGE_ROW == 0)) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PAGE_ROW_INVAILD;
            CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSEQTR);
        GDCSEQTR.ADV_BR = GDB1420_AWA_1420.TXADV_BR;
        GDCSEQTR.SDT = GDB1420_AWA_1420.TXSDT;
        GDCSEQTR.EDT = GDB1420_AWA_1420.TXEDT;
        GDCSEQTR.PAGE_ROW = GDB1420_AWA_1420.PAGE_ROW;
        GDCSEQTR.PAGE_NUM = GDB1420_AWA_1420.PAGE_NUM;
        GDCSEQTR.CTA_NO = GDB1420_AWA_1420.TXCTA_NO;
        GDCSEQTR.REF_NO = GDB1420_AWA_1420.TXREF_NO;
        CEP.TRC(SCCGWA, GDB1420_AWA_1420.TXADV_BR);
        CEP.TRC(SCCGWA, GDB1420_AWA_1420.TXSDT);
        CEP.TRC(SCCGWA, GDB1420_AWA_1420.TXEDT);
        CEP.TRC(SCCGWA, GDB1420_AWA_1420.PAGE_ROW);
        CEP.TRC(SCCGWA, GDB1420_AWA_1420.PAGE_NUM);
        CEP.TRC(SCCGWA, GDB1420_AWA_1420.TXCTA_NO);
        CEP.TRC(SCCGWA, GDB1420_AWA_1420.TXREF_NO);
        CEP.TRC(SCCGWA, GDCSEQTR.PAGE_ROW);
        S000_CALL_GDZSEQTR();
    }
    public void S000_CALL_GDZSEQTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSEQTR", GDCSEQTR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
