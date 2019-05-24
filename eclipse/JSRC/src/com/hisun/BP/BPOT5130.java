package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5130 {
    String K_CMP_IDEV_MAINT = "BP-S-IDEV-MAINT";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_CMP_IRPD_MAINT = "BP-S-IRPD-MAINT";
    String K_FMT_CD = "BPX01";
    String K_CMP_CAL_MAINTAIN = "BP-S-IRAT-MAINT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_AC_DATE = 0;
    BPOT5130_REDEFINES4 REDEFINES4 = new BPOT5130_REDEFINES4();
    short WS_I = 0;
    short WS_II = 0;
    short WS_III = 0;
    char WS_FORMAT = ' ';
    BPOT5130_WS_FMT_DATA WS_FMT_DATA = new BPOT5130_WS_FMT_DATA();
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSDEVM BPCSDEVM = new BPCSDEVM();
    BPCSIPDM BPCSIPDM = new BPCSIPDM();
    SCCGWA SCCGWA;
    BPB5130_AWA_5130 BPB5130_AWA_5130;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5130 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5130_AWA_5130>");
        BPB5130_AWA_5130 = (BPB5130_AWA_5130) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_SET_RETURN_INFO();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.FUNC_CD);
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.TYPE);
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.BR);
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.CCY);
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.BASE_TYP);
        CEP.TRC(SCCGWA, BPB5130_AWA_5130.TENOR);
        WS_FUNC_FLG = BPB5130_AWA_5130.FUNC_CD;
        if ((WS_FUNC_FLG != 'U' 
            && WS_FUNC_FLG != 'Q' 
            && WS_FUNC_FLG != 'D' 
            && WS_FUNC_FLG != 'B' 
            && WS_FUNC_FLG != 'A')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_FUNC_CD_ERR;
            WS_FLD_NO = BPB5130_AWA_5130.FUNC_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB5130_AWA_5130.TYPE == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
            WS_FLD_NO = BPB5130_AWA_5130.TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB5130_AWA_5130.TYPE != 'P' 
            && BPB5130_AWA_5130.TYPE != 'M') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_DATA_ERR;
            WS_FLD_NO = BPB5130_AWA_5130.TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_FUNC_FLG == 'B') {
            if (BPB5130_AWA_5130.BASE_TYP.trim().length() == 0 
                && BPB5130_AWA_5130.TENOR.equalsIgnoreCase("0")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
                WS_FLD_NO = BPB5130_AWA_5130.BASE_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        } else {
            if (BPB5130_AWA_5130.CCY.equalsIgnoreCase("0")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
                WS_FLD_NO = BPB5130_AWA_5130.CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB5130_AWA_5130.BASE_TYP.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
                WS_FLD_NO = BPB5130_AWA_5130.BASE_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB5130_AWA_5130.TENOR.equalsIgnoreCase("0")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_1;
                WS_FLD_NO = BPB5130_AWA_5130.TENOR_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "GOHERE");
        IBS.init(SCCGWA, BPCSDEVM);
        BPCSDEVM.INFO.TYPE = BPB5130_AWA_5130.TYPE;
        BPCSDEVM.INFO.BR = BPB5130_AWA_5130.BR;
        BPCSDEVM.INFO.CCY = BPB5130_AWA_5130.CCY;
        BPCSDEVM.INFO.BASE_TYP = BPB5130_AWA_5130.BASE_TYP;
        BPCSDEVM.INFO.TENOR = BPB5130_AWA_5130.TENOR;
        if (WS_FUNC_FLG == 'A'
            || WS_FUNC_FLG == 'Q'
            || WS_FUNC_FLG == 'U'
            || WS_FUNC_FLG == 'D') {
            B210_CALL_BPZSIPDM_CHECK();
            BPCSDEVM.INFO.FUNC = 'C';
            BPCSDEVM.OUTPUT_FLG = 'N';
        } else if (WS_FUNC_FLG == 'B') {
            BPCSDEVM.INFO.FUNC = 'B';
            BPCSDEVM.OUTPUT_FLG = 'Y';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            WS_FLD_NO = BPB5130_AWA_5130.FUNC_CD_NO;
            S000_ERR_MSG_PROC();
        }
        S000_CALL_BPZSDEVM();
        if (WS_FUNC_FLG == 'A') {
            if (BPCSDEVM.EXIST_FLG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_EXIST;
                WS_FLD_NO = BPB5130_AWA_5130.FUNC_CD_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_FUNC_FLG == 'Q' 
            || WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D') {
            if (BPCSDEVM.EXIST_FLG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND;
                WS_FLD_NO = BPB5130_AWA_5130.FUNC_CD_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B210_CALL_BPZSIPDM_CHECK() throws IOException,SQLException,Exception {
        if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, BPCSIPDM);
            BPCSIPDM.CCY = BPB5130_AWA_5130.CCY;
            BPCSIPDM.B_TYPE = BPB5130_AWA_5130.BASE_TYP;
            BPCSIPDM.TENOR = BPB5130_AWA_5130.TENOR;
            BPCSIPDM.FUNC = 'C';
            BPCSIPDM.OUTPUT_FLG = 'N';
            S000_CALL_BPZSIPDM();
            if (BPCSIPDM.CHECK_RESULT == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_DATA_ERR;
                WS_FLD_NO = BPB5130_AWA_5130.CCY_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        if (WS_FUNC_FLG == 'B') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5131;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5132;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5133;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5134;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5135;
            S000_SET_SUBS_TRN();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            WS_FLD_NO = BPB5130_AWA_5130.FUNC_CD_NO;
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
        WS_FMT_DATA.WS_FMT_TYPE = BPB5130_AWA_5130.TYPE;
        WS_FMT_DATA.WS_FMT_BR = BPB5130_AWA_5130.BR;
        WS_FMT_DATA.WS_FMT_CCY = BPB5130_AWA_5130.CCY;
        WS_FMT_DATA.WS_FMT_BASE_TYP = BPB5130_AWA_5130.BASE_TYP;
        WS_FMT_DATA.WS_FMT_TENOR = BPB5130_AWA_5130.TENOR;
        WS_FMT_DATA.WS_FMT_FMT = BPCSDEVM.INFO.FMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        SCCFMT.DATA_LEN = 18;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSDEVM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_IDEV_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSDEVM;
        SCCCALL.ERR_FLDNO = BPB5130_AWA_5130.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB5130_AWA_5130.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZSIPDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_IRPD_MAINT;
        SCCCALL.COMMAREA_PTR = BPCSIPDM;
        SCCCALL.ERR_FLDNO = BPB5130_AWA_5130.FUNC_CD_NO;
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
