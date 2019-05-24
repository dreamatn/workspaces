package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5110 {
    String K_CMP_CAL_MAINTAIN = "BP-S-IRPD-MAINT";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_FMT_CD = "BPX01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT5110_WS_FMT_DATA WS_FMT_DATA = new BPOT5110_WS_FMT_DATA();
    int WS_RESP = 0;
    String WS_WORD = " ";
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRIRPD BPRIRPD = new BPRIRPD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSIPDM BPCSIPDM = new BPCSIPDM();
    SCCGWA SCCGWA;
    BPB5110_AWA_5110 BPB5110_AWA_5110;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5110 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5110_AWA_5110>");
        BPB5110_AWA_5110 = (BPB5110_AWA_5110) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_MAIN_PROCESS();
        B300_SET_RETURN_INFO();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB5110_AWA_5110);
        CEP.TRC(SCCGWA, BPB5110_AWA_5110.CCY);
        CEP.TRC(SCCGWA, BPB5110_AWA_5110.TENOR);
        CEP.TRC(SCCGWA, BPB5110_AWA_5110.B_TYPE);
        CEP.TRC(SCCGWA, BPB5110_AWA_5110.EFF_FLG);
        CEP.TRC(SCCGWA, BPB5110_AWA_5110.DEL_FLG);
        CEP.TRC(SCCGWA, "YWSTEST");
        CEP.TRC(SCCGWA, BPB5110_AWA_5110.FUNC_CD);
        WS_FUNC_FLG = BPB5110_AWA_5110.FUNC_CD;
        if ((WS_FUNC_FLG != 'U' 
            && WS_FUNC_FLG != 'Q' 
            && WS_FUNC_FLG != 'D' 
            && WS_FUNC_FLG != 'B' 
            && WS_FUNC_FLG != 'A')) {
            CEP.TRC(SCCGWA, "DBTEST1");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_FUNC_CD_ERR;
            WS_FLD_NO = BPB5110_AWA_5110.FUNC_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (WS_FUNC_FLG == 'A' 
            || WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D' 
            || WS_FUNC_FLG == 'Q') {
            if (BPB5110_AWA_5110.CCY.equalsIgnoreCase("0") 
                || BPB5110_AWA_5110.B_TYPE.equalsIgnoreCase("0") 
                || BPB5110_AWA_5110.TENOR.equalsIgnoreCase("0")) {
                CEP.TRC(SCCGWA, "DBTEST2");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_MUST_INPUT_2;
                WS_FLD_NO = BPB5110_AWA_5110.B_TYPE_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (BPB5110_AWA_5110.EFF_FLG != 'Y' 
                && BPB5110_AWA_5110.EFF_FLG != 'N') {
                CEP.TRC(SCCGWA, "DBTEST3");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_FLAG_ERR;
                WS_FLD_NO = BPB5110_AWA_5110.EFF_FLG_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPB5110_AWA_5110.DEL_FLG != 'Y' 
                && BPB5110_AWA_5110.DEL_FLG != 'N') {
                CEP.TRC(SCCGWA, "DBTEST4");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_FLAG_ERR;
                WS_FLD_NO = BPB5110_AWA_5110.DEL_FLG_NO;
                S000_ERR_MSG_PROC();
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIPDM);
        BPCSIPDM.CCY = BPB5110_AWA_5110.CCY;
        BPCSIPDM.TENOR = BPB5110_AWA_5110.TENOR;
        BPCSIPDM.B_TYPE = BPB5110_AWA_5110.B_TYPE;
        BPCSIPDM.EFF_FLG = BPB5110_AWA_5110.EFF_FLG;
        BPCSIPDM.DEL_FLG = BPB5110_AWA_5110.DEL_FLG;
        if (WS_FUNC_FLG == 'A'
            || WS_FUNC_FLG == 'Q'
            || WS_FUNC_FLG == 'U'
            || WS_FUNC_FLG == 'D') {
            BPCSIPDM.FUNC = 'C';
            BPCSIPDM.OUTPUT_FLG = 'N';
        } else if (WS_FUNC_FLG == 'B') {
            BPCSIPDM.FUNC = 'B';
            BPCSIPDM.OUTPUT_FLG = 'Y';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            WS_FLD_NO = BPB5110_AWA_5110.FUNC_CD_NO;
            S000_ERR_MSG_PROC();
        }
        S000_CALL_BPZSIPDM();
        CEP.TRC(SCCGWA, "DBTEST5");
        if (WS_FUNC_FLG == 'A') {
            if (BPCSIPDM.CHECK_RESULT == 'E') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_EXIST;
                WS_FLD_NO = BPB5110_AWA_5110.FUNC_CD_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_FUNC_FLG == 'Q' 
            || WS_FUNC_FLG == 'U' 
            || WS_FUNC_FLG == 'D') {
            if (BPCSIPDM.CHECK_RESULT == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RATE_RECORD_NOTFND;
                WS_FLD_NO = BPB5110_AWA_5110.FUNC_CD_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B300_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        if (WS_FUNC_FLG == 'B') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5111;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5112;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5113;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5114;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'A') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 5115;
            S000_SET_SUBS_TRN();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            WS_FLD_NO = BPB5110_AWA_5110.FUNC_CD_NO;
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
        IBS.init(SCCGWA, BPRIRPD);
        WS_FMT_DATA.WS_FMT_CCY = BPB5110_AWA_5110.CCY;
        WS_FMT_DATA.WS_FMT_B_TYPE = BPB5110_AWA_5110.B_TYPE;
        WS_FMT_DATA.WS_FMT_TENOR = BPB5110_AWA_5110.TENOR;
        WS_FMT_DATA.WS_FMT_EFF_FLG = BPCSIPDM.EFF_FLG;
        WS_FMT_DATA.WS_FMT_DEL_FLG = BPCSIPDM.DEL_FLG;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CD;
        SCCFMT.DATA_PTR = WS_FMT_DATA;
        WS_WORD = IBS.CLS2CPY(SCCGWA, WS_FMT_DATA);
        SCCFMT.DATA_LEN = 12;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZSIPDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_CAL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSIPDM;
        SCCCALL.ERR_FLDNO = BPB5110_AWA_5110.FUNC_CD_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = K_CMP_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB5110_AWA_5110.FUNC_CD_NO;
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
