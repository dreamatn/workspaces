package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4714 {
    String K_CMP_AGC_MAINTAIN = "BP-MAINT-AUTOGEN-CAL";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_AC_DATE = 0;
    BPOT4714_REDEFINES4 REDEFINES4 = new BPOT4714_REDEFINES4();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMAGC BPCSMAGC = new BPCSMAGC();
    SCCGWA SCCGWA;
    BPB4700_AWA_4700 BPB4700_AWA_4700;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4714 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4700_AWA_4700>");
        BPB4700_AWA_4700 = (BPB4700_AWA_4700) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_MAIN_PROCESS();
    }
    public void B100_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMAGC);
        BPCSMAGC.FUNC = 'D';
        BPCSMAGC.OUTPUT_FLG = 'Y';
        BPCSMAGC.CAL_CODE = BPB4700_AWA_4700.CALCD;
        BPCSMAGC.CAL_NAME = BPB4700_AWA_4700.CAL_NAME;
        BPCSMAGC.B_CAL_CODE = BPB4700_AWA_4700.T_CALCD;
        BPCSMAGC.B_CAL_NAME = BPB4700_AWA_4700.T_CAL_NE;
        BPCSMAGC.YEAR = BPB4700_AWA_4700.YEAR;
        BPCSMAGC.EFF_DATE = BPB4700_AWA_4700.EFF_DATE;
        BPCSMAGC.EXP_DATE = BPB4700_AWA_4700.EXP_DATE;
        BPCSMAGC.MON_NO = BPB4700_AWA_4700.MONTH;
        S000_CALL_BPZSMAGC();
    }
    public void S000_CALL_BPZSMAGC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_AGC_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSMAGC;
        SCCCALL.ERR_FLDNO = BPB4700_AWA_4700.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
