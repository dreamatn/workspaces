package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9164 {
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
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9162_AWA_9162 BPB9162_AWA_9162;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9164 return!");
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
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB9162_AWA_9162.BR_OLD == BPB9162_AWA_9162.BR_NEW) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SAME_BOOK_CENTER;
            WS_FLD_NO = BPB9162_AWA_9162.BR_OLD_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB9162_AWA_9162.BR_OLD != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB9162_AWA_9162.BR_OLD;
            S010_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                WS_FLD_NO = BPB9162_AWA_9162.BR_OLD_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB9162_AWA_9162.BR_NEW != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB9162_AWA_9162.BR_OLD;
            S010_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                WS_FLD_NO = BPB9162_AWA_9162.BR_NEW_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSACCT);
        BPCSACCT.FUNC = 'U';
        BPCSACCT.CTA_NO = BPB9162_AWA_9162.CTA_NO;
        BPCSACCT.REL_CTA_NO = BPB9162_AWA_9162.R_CTA_NO;
        BPCSACCT.FEE_TYPE = BPB9162_AWA_9162.FEE_TYPE;
        BPCSACCT.CI_NO = BPB9162_AWA_9162.CI_NO;
        BPCSACCT.CLIENT_NAME = BPB9162_AWA_9162.DESC_30;
        BPCSACCT.START_DT = BPB9162_AWA_9162.STR_DATE;
        BPCSACCT.MATURITY_DT = BPB9162_AWA_9162.MAT_DATE;
        BPCSACCT.BR_OLD = BPB9162_AWA_9162.BR_OLD;
        BPCSACCT.BR_NEW = BPB9162_AWA_9162.BR_NEW;
        BPCSACCT.FMT_ID = "BP087";
        S00_CALL_BPZSACCT();
    }
    public void S010_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
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
