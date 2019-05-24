package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4765 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP466";
    String CPN_S_HCHK_MAINTAIN = "BP-S-HCHK-MAINTAIN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSHCHK BPCSHCHK = new BPCSHCHK();
    SCCGWA SCCGWA;
    BPB4760_AWA_4760 BPB4760_AWA_4760;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4765 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4760_AWA_4760>");
        BPB4760_AWA_4760 = (BPB4760_AWA_4760) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DELETE_HCHK_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_DELETE_HCHK_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSHCHK);
        CEP.TRC(SCCGWA, BPB4760_AWA_4760.EFF_DATE);
        BPCSHCHK.FUNC = 'D';
        BPCSHCHK.FUNC_CODE = 'D';
        BPCSHCHK.CD = BPB4760_AWA_4760.HCHK_CD;
        BPCSHCHK.EFF_DATE = BPB4760_AWA_4760.EFF_DATE;
        BPCSHCHK.OUTPUT_FMT = K_OUTPUT_FMT;
        S000_CALL_BPZSHCHK();
    }
    public void S000_CALL_BPZSHCHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_HCHK_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSHCHK;
        SCCCALL.ERR_FLDNO = BPB4760_AWA_4760.HCHK_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSHCHK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSHCHK.RC);
            WS_FLD_NO = BPB4760_AWA_4760.HCHK_CD_NO;
            S000_ERR_MSG_PROC();
        }
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
