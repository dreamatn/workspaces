package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZTXBR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBG50   ";
    short WS_I = 0;
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCOTXBR IBCOTXBR = new IBCOTXBR();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCTXBR IBCTXBR;
    public void MP(SCCGWA SCCGWA, IBCTXBR IBCTXBR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCTXBR = IBCTXBR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZTXBR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CHECK_PROC();
        if (pgmRtn) return;
        B030_OUTPUT_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCTXBR.BR);
        if (IBCTXBR.BR == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.BR_MUST_INPUT);
        }
    }
    public void B020_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCTXBR.BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        if (!BPCPQORG.VIL_TYP.equalsIgnoreCase("00") 
            || !BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            if (IBCTXBR.BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_TXNBR_FLAG = 'N';
            } else {
                WS_TXNBR_FLAG = 'Y';
            }
        } else {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, IBCPMORG);
            IBCPMORG.KEY.TYP = "PIB09";
            IBCPMORG.KEY.CD = IBS.CLS2CPY(SCCGWA, IBCTXBR.TR_ID);
            BPCPRMR.DAT_PTR = IBCPMORG;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBCPMORG.DATA_TXT.CTL_TYP);
            if (IBCPMORG.DATA_TXT.CTL_TYP == '0') {
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCTXBR.BR) {
                    WS_TXNBR_FLAG = 'N';
                } else {
                    WS_TXNBR_FLAG = 'Y';
                }
            } else if (IBCPMORG.DATA_TXT.CTL_TYP == '1') {
                for (WS_I = 1; WS_TXNBR_FLAG != 'Y' 
                    && WS_I <= 10; WS_I += 1) {
                    if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == IBCPMORG.DATA_TXT.BR[WS_I-1]) {
                        WS_TXNBR_FLAG = 'Y';
                    } else {
                        WS_TXNBR_FLAG = 'N';
                    }
                }
                if (WS_TXNBR_FLAG != 'Y') {
                    WS_TXNBR_FLAG = 'N';
                }
            } else {
                WS_TXNBR_FLAG = 'Y';
            }
        }
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOTXBR);
        CEP.TRC(SCCGWA, WS_TXNBR_FLAG);
        IBCOTXBR.FLAG = WS_TXNBR_FLAG;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOTXBR;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
