package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT8540 {
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSIDIB AICSIDIB = new AICSIDIB();
    SCCGWA SCCGWA;
    AIB8540_AWA_8540 AIB8540_AWA_8540;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT8540 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB8540_AWA_8540>");
        AIB8540_AWA_8540 = (AIB8540_AWA_8540) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_INQ_DETAIL_ITM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_DETAIL_ITM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSIDIB);
        CEP.TRC(SCCGWA, AIB8540_AWA_8540);
        AICSIDIB.FUNC = 'Q';
        AICSIDIB.BOOK_FLG = AIB8540_AWA_8540.BOOK_FLG;
        AICSIDIB.AC_NO = AIB8540_AWA_8540.AC_NO;
        AICSIDIB.GROUP = AIB8540_AWA_8540.GROUP;
        AICSIDIB.ISSGRP_NO = AIB8540_AWA_8540.S_GRP_NO;
        AICSIDIB.ENDGRP_NO = AIB8540_AWA_8540.E_GRP_NO;
        AICSIDIB.CCY = AIB8540_AWA_8540.CCY;
        AICSIDIB.ISSDAT = AIB8540_AWA_8540.ISSDAT;
        AICSIDIB.ENDDAT = AIB8540_AWA_8540.ENDDAT;
        S000_CALL_AIZSIDIB();
    }
    public void R000_CHECK_RESULT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZSIDIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-S-INQ-DTL-ITM-BAL", AICSIDIB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
