package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT4150 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCSADJI TDCSADJI = new TDCSADJI();
    SCCGWA SCCGWA;
    TDB4150_AWA_4150 TDB4150_AWA_4150;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT4150 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB4150_AWA_4150>");
        TDB4150_AWA_4150 = (TDB4150_AWA_4150) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CALL_TDZSADJI();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB4150_AWA_4150.AC);
        CEP.TRC(SCCGWA, TDB4150_AWA_4150.AC_SEQ);
        CEP.TRC(SCCGWA, TDB4150_AWA_4150.CR_INT);
        CEP.TRC(SCCGWA, TDB4150_AWA_4150.DR_INT);
        if (TDB4150_AWA_4150.AC.trim().length() == 0 
            && TDB4150_AWA_4150.MAIN_AC.trim().length() == 0 
            && TDB4150_AWA_4150.CARD.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB4150_AWA_4150.MAIN_AC.trim().length() > 0 
            && TDB4150_AWA_4150.AC_SEQ == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_MAIN_AC_SEQ_ERR;
            S000_ERR_MSG_PROC();
        }
        if (TDB4150_AWA_4150.MAIN_AC.trim().length() == 0 
            && TDB4150_AWA_4150.AC_SEQ != 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_MAIN_AC_SEQ_ERR;
            S000_ERR_MSG_PROC();
        }
        if (TDB4150_AWA_4150.CR_INT == 0 
            && TDB4150_AWA_4150.DR_INT == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INPUT_ONE_INT;
            S000_ERR_MSG_PROC();
        }
        if (TDB4150_AWA_4150.CR_INT < 0 
            || TDB4150_AWA_4150.DR_INT < 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AMT_LESS_ZERO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CALL_TDZSADJI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCSADJI);
        TDCSADJI.MAIN_AC = TDB4150_AWA_4150.MAIN_AC;
        TDCSADJI.AC_SEQ = TDB4150_AWA_4150.AC_SEQ;
        TDCSADJI.AC = TDB4150_AWA_4150.AC;
        TDCSADJI.CARD = TDB4150_AWA_4150.CARD;
        TDCSADJI.CR_INT = TDB4150_AWA_4150.CR_INT;
        TDCSADJI.DR_INT = TDB4150_AWA_4150.DR_INT;
        S000_CALL_TDZSADJI();
    }
    public void S000_CALL_TDZSADJI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-ADJ-INT-MAIN", TDCSADJI);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
