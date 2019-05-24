package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3912 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BP165";
    String CPN_S_MGM_BPRD = "BP-S-MGM-BPRD ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT3912_WS_BPRD_KEY WS_BPRD_KEY = new BPOT3912_WS_BPRD_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBPRD BPCSBPRD = new BPCSBPRD();
    SCCGWA SCCGWA;
    BPB3910_AWA_3910 BPB3910_AWA_3910;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3912 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3910_AWA_3910>");
        BPB3910_AWA_3910 = (BPB3910_AWA_3910) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSBPRD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_BPRD_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3910_AWA_3910.BPRD_CD);
        CEP.TRC(SCCGWA, BPB3910_AWA_3910.EFF_DATE);
        CEP.TRC(SCCGWA, BPB3910_AWA_3910.BR);
        CEP.TRC(SCCGWA, BPB3910_AWA_3910.CODE);
    }
    public void B020_QUERY_BPRD_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBPRD);
        BPCSBPRD.FUNC = 'I';
        BPCSBPRD.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSBPRD();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSBPRD.FUNC_CODE = BPB3910_AWA_3910.FUNC;
        CEP.TRC(SCCGWA, BPCSBPRD.FUNC_CODE);
        BPCSBPRD.BR = BPB3910_AWA_3910.BR;
        BPCSBPRD.CODE = BPB3910_AWA_3910.CODE;
        BPCSBPRD.EFF_DATE = BPB3910_AWA_3910.EFF_DATE;
    }
    public void S000_CALL_BPZSBPRD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-CALL-BPZSBPRD");
        IBS.CALLCPN(SCCGWA, "BP-S-MGM-BPRD", BPCSBPRD);
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
