package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT1900 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    TDCSGRP TDCSGRP = new TDCSGRP();
    SCCGWA SCCGWA;
    TDB1900_AWA_1900 TDB1900_AWA_1900;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT1900 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1900_AWA_1900>");
        TDB1900_AWA_1900 = (TDB1900_AWA_1900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_LINK_TDZSGRP_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_LINK_TDZSGRP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCSGRP);
        TDCSGRP.AC_NO = TDB1900_AWA_1900.AC_NO;
        TDCSGRP.PROD_CD = TDB1900_AWA_1900.PROD_CD;
        TDCSGRP.DRAW_MTH = TDB1900_AWA_1900.DRAW_MTH;
        TDCSGRP.PSW = TDB1900_AWA_1900.PSW;
        TDCSGRP.ID_TYP = TDB1900_AWA_1900.ID_TYP;
        TDCSGRP.ID_NO = TDB1900_AWA_1900.ID_NO;
        TDCSGRP.DRAW_TYP = TDB1900_AWA_1900.DRAW_TYP;
        TDCSGRP.TXN_AMT = TDB1900_AWA_1900.TXN_AMT;
        TDCSGRP.GT_FLG = TDB1900_AWA_1900.GT_FLG;
        TDCSGRP.OP_AC = TDB1900_AWA_1900.OP_AC;
        TDCSGRP.AUTO_FLG = 'Y';
        S000_CALL_TDZSGRP();
    }
    public void S000_CALL_TDZSGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-SER-GROUP", TDCSGRP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
