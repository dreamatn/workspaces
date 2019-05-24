package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6223 {
    char K_ERROR = 'E';
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_MAINTAIN_THOL = "BP-S-MAINTAIN-THOL";
    String CPN_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY     ";
    String CPN_BP_I_INQ_CNTY = "BP-I-INQ-CNTY";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSTHOL BPCSTHOL = new BPCSTHOL();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCIQCIT BPCIQCIT = new BPCIQCIT();
    BPCIQCNT BPCIQCNT = new BPCIQCNT();
    SCCGWA SCCGWA;
    BPB6220_AWA_6220 BPB6220_AWA_6220;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT6223 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6220_AWA_6220>");
        BPB6220_AWA_6220 = (BPB6220_AWA_6220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_HOL_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6220_AWA_6220.EFF_DT);
        if (BPB6220_AWA_6220.EFF_DT != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB6220_AWA_6220.EFF_DT;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            CEP.TRC(SCCGWA, SCCCKDT.RC);
            if (SCCCKDT.RC != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DT_INVALID;
                WS_FLD_NO = BPB6220_AWA_6220.EFF_DT_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B200_SET_NXT_TXN() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB6220_AWA_6220.FUNC;
        CEP.TRC(SCCGWA, WS_FUNC_FLG);
        if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 6222;
            S020_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 6226;
            S020_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 6223;
            S020_SET_SUBS_TRN();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_FUNC_ERR;
            WS_FLD_NO = BPB6220_AWA_6220.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_BROWSE_HOL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTHOL);
        BPCSTHOL.KEY.CAL_CD = BPB6220_AWA_6220.CAL_CD;
        BPCSTHOL.KEY.EFF_DT = BPB6220_AWA_6220.EFF_DT;
        BPCSTHOL.FUNC = 'D';
        CEP.TRC(SCCGWA, BPCSTHOL.KEY);
        CEP.TRC(SCCGWA, BPCSTHOL.FUNC);
        S000_CALL_BPZSTHOL();
    }
    public void S000_CALL_BPZSTHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MAINTAIN_THOL, BPCSTHOL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZIQCIT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CITY, BPCIQCIT);
    }
    public void S020_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB6220_AWA_6220.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZIQCNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIQCNT);
        IBS.CALLCPN(SCCGWA, CPN_BP_I_INQ_CNTY, BPCIQCNT);
        CEP.TRC(SCCGWA, BPCIQCNT.RC.RC_CODE);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
