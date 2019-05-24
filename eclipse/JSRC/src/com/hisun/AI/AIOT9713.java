package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT9713 {
    String CPN_VAT_MAINTAIN = "AI-S-MAINT-VAT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICSVAT AICSVAT = new AICSVAT();
    SCCGWA SCCGWA;
    AIB9710_AWA_9710 AIB9710_AWA_9710;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT9713 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB9710_AWA_9710>");
        AIB9710_AWA_9710 = (AIB9710_AWA_9710) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, AICSVAT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_FUNCTION_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB9710_AWA_9710.VAT_CODE.trim().length() == 0 
            || AIB9710_AWA_9710.BR == 0 
            || AIB9710_AWA_9710.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_INPUT_ERROR);
        }
    }
    public void B020_FUNCTION_PROC() throws IOException,SQLException,Exception {
        AICSVAT.INFO.FUNC = 'D';
        AICSVAT.VAT_CODE = AIB9710_AWA_9710.VAT_CODE;
        AICSVAT.BR = AIB9710_AWA_9710.BR;
        AICSVAT.CCY = AIB9710_AWA_9710.CCY;
        AICSVAT.EFF_DATE = AIB9710_AWA_9710.EFF_DT;
        AICSVAT.EXP_DATE = AIB9710_AWA_9710.EXP_DT;
        AICSVAT.VAT_RAT = AIB9710_AWA_9710.VAT_RAT;
        AICSVAT.FREE_AMT = AIB9710_AWA_9710.FREE_AMT;
        AICSVAT.MAX_AMT = AIB9710_AWA_9710.MAX_AMT;
        AICSVAT.MIN_AMT = AIB9710_AWA_9710.MIN_AMT;
        AICSVAT.SUP_TLR1 = AIB9710_AWA_9710.SUP_TLR1;
        AICSVAT.SUP_TLR1 = AIB9710_AWA_9710.SUP_TLR2;
        AICSVAT.REMARK = AIB9710_AWA_9710.REMARK;
        S000_CALL_AIZSVAT();
    }
    public void S000_CALL_AIZSVAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_VAT_MAINTAIN, AICSVAT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
