package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CMOT2300 {
    String WS_MSGID = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    CMCSCPUL CMCSCPUL = new CMCSCPUL();
    SCCGWA SCCGWA;
    CMB2300_AWA_2300 CMB2300_AWA_2300;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CMOT2300 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CMB2300_AWA_2300>");
        CMB2300_AWA_2300 = (CMB2300_AWA_2300) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B030_GET_LIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CMB2300_AWA_2300.AC.trim().length() == 0) {
            WS_MSGID = CMCMSG_ERROR_MSG.CM_ERR_INPUT_AC;
            S000_ERR_MSG_PROC();
        }
        if (CMB2300_AWA_2300.LOST_FLG != ' ' 
            && CMB2300_AWA_2300.RLST_FLG != ' ') {
            WS_MSGID = CMCMSG_ERROR_MSG.CM_INVALID_TYPE;
            S000_ERR_MSG_PROC();
        }
        if (CMB2300_AWA_2300.LOST_FLG == ' ' 
            && CMB2300_AWA_2300.RLST_FLG == ' ') {
            WS_MSGID = CMCMSG_ERROR_MSG.CM_INVALID_TYPE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMB2300_AWA_2300.AC);
        CEP.TRC(SCCGWA, CMB2300_AWA_2300.SEQ);
        CEP.TRC(SCCGWA, CMB2300_AWA_2300.LOST_FLG);
        CEP.TRC(SCCGWA, CMB2300_AWA_2300.RLST_FLG);
        CEP.TRC(SCCGWA, CMB2300_AWA_2300.ID_TYP);
        CEP.TRC(SCCGWA, CMB2300_AWA_2300.ID_NO);
        IBS.init(SCCGWA, CMCSCPUL);
        CMCSCPUL.AC = CMB2300_AWA_2300.AC;
        CMCSCPUL.SEQ = CMB2300_AWA_2300.SEQ;
        CMCSCPUL.LOST_FLG = CMB2300_AWA_2300.LOST_FLG;
        CMCSCPUL.RLST_FLG = CMB2300_AWA_2300.RLST_FLG;
        CMCSCPUL.ID_TYP = CMB2300_AWA_2300.ID_TYP;
        CMCSCPUL.ID_NO = CMB2300_AWA_2300.ID_NO;
        CEP.TRC(SCCGWA, CMCSCPUL.AC);
        CEP.TRC(SCCGWA, CMCSCPUL.SEQ);
        CEP.TRC(SCCGWA, CMCSCPUL.LOST_FLG);
        CEP.TRC(SCCGWA, CMCSCPUL.RLST_FLG);
        CEP.TRC(SCCGWA, CMCSCPUL.ID_TYP);
        CEP.TRC(SCCGWA, CMCSCPUL.ID_NO);
        S000_CALL_CMZSCPUL();
    }
    public void S000_CALL_CMZSCPUL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CM-S-CMZSCPUL", CMCSCPUL);
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
