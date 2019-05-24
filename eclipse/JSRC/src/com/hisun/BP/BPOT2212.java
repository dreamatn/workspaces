package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2212 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP121";
    String CPN_P_CHECK_PAR_VALUE = "BP-P-CHECK-PAR-VALUE";
    String WS_ERR_MSG = " ";
    BPOT2212_WS_FMT_DATA WS_FMT_DATA = new BPOT2212_WS_FMT_DATA();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQVAL BPCPQVAL = new BPCPQVAL();
    SCCGWA SCCGWA;
    BPB2212_AWA_2212 BPB2212_AWA_2212;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2212 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2212_AWA_2212>");
        BPB2212_AWA_2212 = (BPB2212_AWA_2212) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_M_FLAG();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB2212_AWA_2212.CCY.trim().length() == 0 
            || BPB2212_AWA_2212.PVAL == 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_M_FLAG() throws IOException,SQLException,Exception {
        WS_FMT_DATA.WS_PAR_M_FLG = ' ';
        IBS.init(SCCGWA, BPCPQVAL);
        BPCPQVAL.CCY = BPB2212_AWA_2212.CCY;
        BPCPQVAL.PAR_VAL = BPB2212_AWA_2212.PVAL;
        BPCPQVAL.M_FLG = '0';
        S000_CALL_BPZPQVAL();
        if (pgmRtn) return;
        if (BPCPQVAL.RC.RC_CODE == 0) {
            WS_FMT_DATA.WS_PAR_M_FLG = '0';
        }
        IBS.init(SCCGWA, BPCPQVAL);
        BPCPQVAL.CCY = BPB2212_AWA_2212.CCY;
        BPCPQVAL.PAR_VAL = BPB2212_AWA_2212.PVAL;
        BPCPQVAL.M_FLG = '1';
        S000_CALL_BPZPQVAL();
        if (pgmRtn) return;
        if (BPCPQVAL.RC.RC_CODE == 0) {
            if (WS_FMT_DATA.WS_PAR_M_FLG == '0') {
                WS_FMT_DATA.WS_PAR_M_FLG = ' ';
            } else {
                WS_FMT_DATA.WS_PAR_M_FLG = '1';
            }
        } else {
            if (WS_FMT_DATA.WS_PAR_M_FLG == ' ') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQVAL.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQVAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CHECK_PAR_VALUE, BPCPQVAL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
