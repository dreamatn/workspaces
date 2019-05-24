package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5383 {
    boolean pgmRtn = false;
    String CPN_BP_FAVM = "BP-FAVM ";
    short K_MAX_ARRAY = 10;
    short WS_I = 0;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCMEDA BPCMEDA = new BPCMEDA();
    BPCMCS BPCMCS = new BPCMCS();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRFAVM BPCRFAVM = new BPCRFAVM();
    BPREXFAV BPREXFAV = new BPREXFAV();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5380_AWA_5380 BPB5380_AWA_5380;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT5383 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5380_AWA_5380>");
        BPB5380_AWA_5380 = (BPB5380_AWA_5380) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_UPDATE_REC_PROC();
        if (pgmRtn) return;
        B030_WRITE_HISTORY();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_UPDATE_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5380_AWA_5380.FAV_CODE);
        CEP.TRC(SCCGWA, BPB5380_AWA_5380.EFF_DT);
        CEP.TRC(SCCGWA, BPB5380_AWA_5380.EXP_DT);
        CEP.TRC(SCCGWA, BPB5380_AWA_5380.CCY);
        CEP.TRC(SCCGWA, BPB5380_AWA_5380.FEE_TYPE);
        IBS.init(SCCGWA, BPREXFAV);
        IBS.init(SCCGWA, BPCRFAVM);
        BPREXFAV.KEY.FAV_CODE = BPB5380_AWA_5380.FAV_CODE;
        BPREXFAV.KEY.CCY = BPB5380_AWA_5380.CCY;
        BPCRFAVM.INFO.FUNC = 'R';
        BPCRFAVM.INFO.LEN = 2915;
        BPCRFAVM.INFO.POINTER = BPREXFAV;
        IBS.CALLCPN(SCCGWA, CPN_BP_FAVM, BPCRFAVM);
        if (BPCRFAVM.RETURN_INFO == 'F') {
        } else if (BPCRFAVM.RETURN_INFO == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND);
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFAVM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPREXFAV);
        IBS.init(SCCGWA, BPCRFAVM);
        BPREXFAV.KEY.FAV_CODE = BPB5380_AWA_5380.FAV_CODE;
        BPREXFAV.KEY.CCY = BPB5380_AWA_5380.CCY;
        BPCRFAVM.INFO.FUNC = 'D';
        BPCRFAVM.INFO.LEN = 2915;
        BPCRFAVM.INFO.POINTER = BPREXFAV;
        IBS.CALLCPN(SCCGWA, CPN_BP_FAVM, BPCRFAVM);
        if (BPCRFAVM.RETURN_INFO == 'F') {
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFAVM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_WRITE_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = BPREXFAV.KEY.FAV_CODE;
        BPCPNHIS.INFO.TX_RMK = "EXFAV-CODE";
        BPCPNHIS.INFO.FMT_ID = "BPREXFAV";
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
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
