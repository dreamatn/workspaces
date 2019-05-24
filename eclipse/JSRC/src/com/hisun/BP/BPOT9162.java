package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9162 {
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSACCT BPCSACCT = new BPCSACCT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9162_AWA_9162 BPB9162_AWA_9162;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9162 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9162_AWA_9162>");
        BPB9162_AWA_9162 = (BPB9162_AWA_9162) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSACCT);
        IBS.init(SCCGWA, SCCCKDT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB9162_AWA_9162.CTA_NO.trim().length() > 0 
            && BPB9162_AWA_9162.R_CTA_NO.trim().length() > 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CONTACT_NO_INPUT_ERR;
            WS_FLD_NO = BPB9162_AWA_9162.CTA_NO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSACCT);
        BPCSACCT.FUNC = 'B';
        BPCSACCT.CTA_NO = BPB9162_AWA_9162.CTA_NO;
        BPCSACCT.REL_CTA_NO = BPB9162_AWA_9162.R_CTA_NO;
        S00_CALL_BPZSACCT();
    }
    public void S00_CALL_BPZSACCT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FEE-ACCT-MAINT", BPCSACCT);
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
