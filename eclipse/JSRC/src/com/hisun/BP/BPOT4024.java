package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4024 {
    String CPN_S_MAINT_VCHS = "BP-S-MAINT-VCHS     ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short K_MAX_CASE_SEQ = 99;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSMVHS BPCSMVHS = new BPCSMVHS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB4000_AWA_4000 BPB4000_AWA_4000;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4024 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4000_AWA_4000>");
        BPB4000_AWA_4000 = (BPB4000_AWA_4000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        BPCSMVHS.FUNC = 'D';
        BPCSMVHS.OUTPUT_FLG = 'Y';
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.AP_MMO);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.TR_CD);
        CEP.TRC(SCCGWA, BPB4000_AWA_4000.CASE_NO);
        BPCSMVHS.AP_MMO = BPB4000_AWA_4000.AP_MMO;
        BPCSMVHS.TR_CD = BPB4000_AWA_4000.TR_CD;
        BPCSMVHS.CASE_NO = BPB4000_AWA_4000.CASE_NO;
        S000_CALL_BPZSMVHS();
    }
    public void S000_CALL_BPZSMVHS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MAINT_VCHS, BPCSMVHS);
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
