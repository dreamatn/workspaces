package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5120 {
    String K_CMP_CAL_MAINTAIN = "BP-S-IRAT-MAINT";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_FMT_CD = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT5120_WS_FMT_DATA WS_FMT_DATA = new BPOT5120_WS_FMT_DATA();
    int WS_RESP = 0;
    String WS_WORD = " ";
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
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
        CEP.TRC(SCCGWA, "BPOT5120 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5120_AWA_5120>");
        BPB5120_AWA_5120 = (BPB5120_AWA_5120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_SET_RETURN_INFO();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5120_AWA_5120);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.FLG);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.BR);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.CCY);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.B_TYPE);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.TENOR);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.FMT);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.HIGH);
        CEP.TRC(SCCGWA, BPB5120_AWA_5120.LOW);
        WS_FUNC_FLG = BPB5120_AWA_5120.FUNC_CD;
        if ((WS_FUNC_FLG != 'U' 
            && WS_FUNC_FLG != 'Q' 
            && WS_FUNC_FLG != 'D' 
            && WS_FUNC_FLG != 'B' 
            && WS_FUNC_FLG != 'A')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_FUNC_CD_ERR;
            WS_FLD_NO = BPB5120_AWA_5120.FUNC_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_FUNC_FLG == 'B') {
            if (BPB5120_AWA_5120.CCY.trim().length() == 0 
                && BPB5120_AWA_5120.B_TYPE.trim().length() == 0 
                && BPB5120_AWA_5120.TENOR.equalsIgnoreCase("0") 
                && BPB5120_AWA_5120.BR == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
                WS_FLD_NO = BPB5120_AWA_5120.BR_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (WS_FUNC_FLG == 'A' 
            || WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D' 
            || WS_FUNC_FLG == 'Q') {
            if (BPB5120_AWA_5120.FLG != 'Y' 
                && BPB5120_AWA_5120.FLG != 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_FLAG_ERR;
                WS_FLD_NO = BPB5120_AWA_5120.FLG_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB5120_AWA_5120.FLG == 'Y') {
                if (BPB5120_AWA_5120.CCY.equalsIgnoreCase("0") 
                    || BPB5120_AWA_5120.B_TYPE.equalsIgnoreCase("0") 
                    || BPB5120_AWA_5120.TENOR.equalsIgnoreCase("0")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_2;
                    WS_FLD_NO = BPB5120_AWA_5120.B_TYPE_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
            } else {
                if (BPB5120_AWA_5120.CCY.equalsIgnoreCase("0") 
                    || BPB5120_AWA_5120.B_TYPE.equalsIgnoreCase("0") 
                    || BPB5120_AWA_5120.TENOR.equalsIgnoreCase("0") 
                    || BPB5120_AWA_5120.BR == 0) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_2;
                    WS_FLD_NO = BPB5120_AWA_5120.B_TYPE_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                }
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
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIATM);
        BPCSIATM.FLG = BPB5120_AWA_5120.FLG;
        BPCSIATM.BR = BPB5120_AWA_5120.BR;
        BPCSIATM.CCY = BPB5120_AWA_5120.CCY;
        BPCSIATM.B_TYPE = BPB5120_AWA_5120.B_TYPE;
        BPCSIATM.TENOR = BPB5120_AWA_5120.TENOR;
        BPCSIATM.FMT = BPB5120_AWA_5120.FMT;
        BPCSIATM.HIGH = BPB5120_AWA_5120.HIGH;
        BPCSIATM.LOW = BPB5120_AWA_5120.LOW;
        if (WS_FUNC_FLG == 'A'
            || WS_FUNC_FLG == 'Q'
            || WS_FUNC_FLG == 'U'
            || WS_FUNC_FLG == 'D') {
            BPCSIATM.FUNC = 'C';
            BPCSIATM.OUTPUT_FLG = 'N';
        } else if (WS_FUNC_FLG == 'B') {
            BPCSIATM.FUNC = 'B';
            BPCSIATM.OUTPUT_FLG = 'Y';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            WS_FLD_NO = BPB5120_AWA_5120.FUNC_CD_NO;
            S000_ERR_MSG_PROC();
        }
        S000_CALL_BPZSIATM();
        if (WS_FUNC_FLG == 'A') {
            if (BPCSIATM.CHECK_RESULT == 'E') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_EXIST;
                WS_FLD_NO = BPB5120_AWA_5120.FUNC_CD_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_FUNC_FLG == 'Q' 
            || WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D') {
            if (BPCSIATM.CHECK_RESULT == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND;
                WS_FLD_NO = BPB5120_AWA_5120.FUNC_CD_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        if (WS_FUNC_FLG == 'B') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5121;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5122;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5123;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5124;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5125;
            S000_SET_SUBS_TRN();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            WS_FLD_NO = BPB5120_AWA_5120.FUNC_CD_NO;
            S000_ERR_MSG_PROC();
        }
        if (WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D' 
            || WS_FUNC_FLG == 'Q' 
            || WS_FUNC_FLG == 'A') {
            B310_SET_RETURN_DATA();
        }
    }
    public void B310_SET_RETURN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_FMT_DATA);
        WS_FMT_DATA.WS_FMT_FLG = BPB5120_AWA_5120.FLG;
        WS_FMT_DATA.WS_FMT_BR = BPB5120_AWA_5120.BR;
        WS_FMT_DATA.WS_FMT_CCY = BPB5120_AWA_5120.CCY;
        WS_FMT_DATA.WS_FMT_B_TYPE = BPB5120_AWA_5120.B_TYPE;
        WS_FMT_DATA.WS_FMT_TENOR = BPB5120_AWA_5120.TENOR;
        WS_FMT_DATA.WS_FMT_FMT = BPCSIATM.FMT;
        WS_FMT_DATA.WS_FMT_HIGH = BPCSIATM.HIGH;
        WS_FMT_DATA.WS_FMT_LOW = BPCSIATM.LOW;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 44;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSIATM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_CAL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSIATM;
        SCCCALL.ERR_FLDNO = BPB5120_AWA_5120.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
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
