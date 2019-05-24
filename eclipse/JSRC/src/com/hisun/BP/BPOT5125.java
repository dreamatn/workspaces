package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5125 {
    String K_CMP_CAL_MAINTAIN = "BP-S-IRAT-MAINT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSIATM BPCSIATM = new BPCSIATM();
    SCCGWA SCCGWA;
    BPB5120_AWA_5120 BPB5120_AWA_5120;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5125 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5120_AWA_5120>");
        BPB5120_AWA_5120 = (BPB5120_AWA_5120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.FLG);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.BR);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.CCY);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.B_TYPE);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.TENOR);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.FMT);
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB5120_AWA_5120.FLG != 'Y' 
            && BPB5120_AWA_5120.FLG != 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_FLAG_ERR;
            WS_FLD_NO = BPB5120_AWA_5120.FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB5120_AWA_5120.HIGH < BPB5120_AWA_5120.LOW) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_DATA_ERR1;
            WS_FLD_NO = BPB5120_AWA_5120.HIGH_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB5120_AWA_5120.FMT != 'P' 
            && BPB5120_AWA_5120.FMT != 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_FMT_INPUT_ERR;
            WS_FLD_NO = BPB5120_AWA_5120.FMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.FLG);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.BR);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.CCY);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.B_TYPE);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.TENOR);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.FMT);
        IBS.init(SCCGWA, BPCSIATM);
        BPCSIATM.FLG = BPB5120_AWA_5120.FLG;
        if (BPB5120_AWA_5120.FLG == 'N') {
            BPCSIATM.BR = BPB5120_AWA_5120.BR;
        } else {
            BPCSIATM.BR = SCCGWA.COMM_AREA.HQT_BANK;
        }
        BPCSIATM.CCY = BPB5120_AWA_5120.CCY;
        BPCSIATM.B_TYPE = BPB5120_AWA_5120.B_TYPE;
        BPCSIATM.TENOR = BPB5120_AWA_5120.TENOR;
        BPCSIATM.FMT = BPB5120_AWA_5120.FMT;
        BPCSIATM.HIGH = BPB5120_AWA_5120.HIGH;
        BPCSIATM.LOW = BPB5120_AWA_5120.LOW;
        BPCSIATM.OUTPUT_FLG = 'Y';
        BPCSIATM.FUNC = 'A';
        S000_CALL_BPZSIATM();
    }
    public void S000_CALL_BPZSIATM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_CAL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSIATM;
        SCCCALL.ERR_FLDNO = BPB5120_AWA_5120.FUNC_CD_NO;
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
