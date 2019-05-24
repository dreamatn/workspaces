package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4507 {
    String K_OUTPUT_FMT = "BP408";
    String K_CNP_BP_P_INQ_ORG = "BP-P-INQ-ORG";
    String K_CNP_BP_P_QUERY_BANK = "BP-P-QUERY-BANK";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCBANK BPCBANK = new BPCBANK();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    SCCGWA SCCGWA;
    BPB4507_AWA_4507 BPB4507_AWA_4507;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4507 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4507_AWA_4507>");
        BPB4507_AWA_4507 = (BPB4507_AWA_4507) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCPQORG);
        IBS.init(SCCGWA, BPCBANK);
        IBS.init(SCCGWA, BPCPQBNK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROCESS();
        B030_OUTPUT_PROCESS();
    }
    public void B010_CHECK_PROCESS() throws IOException,SQLException,Exception {
        if (BPB4507_AWA_4507.BR_NO != 0) {
            B020_CALL_BPZPQORG_PROCESS();
        }
        if (!BPB4507_AWA_4507.BK_NO.equalsIgnoreCase("0")) {
            B040_CALL_BPZPQBNK_PROCESS();
        }
    }
    public void B020_CALL_BPZPQORG_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB4507_AWA_4507.BR_NO;
        IBS.CALLCPN(SCCGWA, K_CNP_BP_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B040_CALL_BPZPQBNK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = BPB4507_AWA_4507.BK_NO;
        IBS.CALLCPN(SCCGWA, K_CNP_BP_P_QUERY_BANK, BPCPQBNK);
        CEP.TRC(SCCGWA, BPCPQBNK.RC);
        CEP.TRC(SCCGWA, BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCBANK);
        BPCBANK.NO = BPB4507_AWA_4507.BR_NO;
        BPCBANK.ENM = BPCPQORG.ENG_NM;
        BPCBANK.CNM = BPCPQORG.CHN_NM;
        BPCBANK.NO = BPB4507_AWA_4507.BK_NO;
        BPCBANK.ENM = BPCPQBNK.DATA_INFO.ENG_NM;
        BPCBANK.CNM = BPCPQBNK.DATA_INFO.CHN_NM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCBANK;
        SCCFMT.DATA_LEN = 269;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, BPB4507_AWA_4507.BR_NO_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
