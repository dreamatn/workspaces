package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT1210 {
    String WS_MSGID = " ";
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    GDCSTGDR GDCSTGDR = new GDCSTGDR();
    SCCGWA SCCGWA;
    GDB1210_AWA_1210 GDB1210_AWA_1210;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "GDOT1210 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB1210_AWA_1210>");
        GDB1210_AWA_1210 = (GDB1210_AWA_1210) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXCCY);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.DRWAMT_S);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXSTLT);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.EIADD_AC);
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (GDB1210_AWA_1210.TXCCY.trim().length() == 0) {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_CCY_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (GDB1210_AWA_1210.DRWAMT_S == 0) {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_DRAWAMT_MST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (GDB1210_AWA_1210.TXSTLT == ' ') {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_STLT_INVALID;
            S000_ERR_MSG_PROC();
        }
        if (GDB1210_AWA_1210.EIADD_AC.trim().length() == 0) {
            WS_MSGID = GDCMSG_ERROR_MSG.GD_EIAAC_MST_INPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSTGDR);
        GDCSTGDR.VAL.MAC = GDB1210_AWA_1210.TXMAC;
        GDCSTGDR.VAL.SEQ = GDB1210_AWA_1210.TXSEQ;
        GDCSTGDR.VAL.DD_AC = GDB1210_AWA_1210.TXDD_AC;
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXAC_NM);
        GDCSTGDR.VAL.DDAC_NM = GDB1210_AWA_1210.TXAC_NM;
        GDCSTGDR.VAL.CCY = GDB1210_AWA_1210.TXCCY;
        GDCSTGDR.VAL.TYP = GDB1210_AWA_1210.TXTYP;
        GDCSTGDR.VAL.DRAWAMT_S = GDB1210_AWA_1210.DRWAMT_S;
        GDCSTGDR.VAL.INT_F = GDB1210_AWA_1210.TXINT_F;
        GDCSTGDR.VAL.INTAMT_S = GDB1210_AWA_1210.INTAMT_S;
        GDCSTGDR.VAL.STLT = GDB1210_AWA_1210.TXSTLT;
        GDCSTGDR.VAL.EIADD_AC = GDB1210_AWA_1210.EIADD_AC;
        GDCSTGDR.VAL.INTDD_AC = GDB1210_AWA_1210.INTDD_AC;
        GDCSTGDR.VAL.SMR = GDB1210_AWA_1210.TXSMR;
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXMAC);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXSEQ);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXDD_AC);
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.DD_AC);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXAC_NM);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXCCY);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXTYP);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.DRWAMT_S);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXINT_F);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.INTAMT_S);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXSTLT);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.EIADD_AC);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.INTDD_AC);
        CEP.TRC(SCCGWA, GDCSTGDR.VAL.INTDD_AC);
        CEP.TRC(SCCGWA, GDB1210_AWA_1210.TXSMR);
        S000_CALL_GDZSTGDR();
    }
    public void S000_CALL_GDZSTGDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSTGDR", GDCSTGDR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}