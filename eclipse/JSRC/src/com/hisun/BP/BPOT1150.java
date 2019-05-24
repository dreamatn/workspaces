package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1150 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT = "BPX01";
    String CPN_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String CPN_FFAV_MAINTAIN = "BP-F-S-MAINTAIN-FFAV";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT1 = 0;
    double WS_AMT = 0;
    short WS_CNT = 0;
    short WS_INPT_CNT = 0;
    short WS_J = 0;
    String WS_FAV_TYP = " ";
    char WS_COLL_FLG = ' ';
    char WS_FAV_KND = ' ';
    char WS_CAL_MTH = ' ';
    char WS_CAL_CYC = ' ';
    char WS_COL_TYPE = ' ';
    char WS_ARG_SPL = ' ';
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFFAV BPCOFFAV = new BPCOFFAV();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB1100_AWA_1100 BPB1100_AWA_1100;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1150 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1100_AWA_1100>");
        BPB1100_AWA_1100 = (BPB1100_AWA_1100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCSUBS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_SET_NXT_TXN();
        B020_QUERY_FAVINF_PARM();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1100_AWA_1100.FAV_CD);
        CEP.TRC(SCCGWA, BPB1100_AWA_1100.FUNC);
        CEP.TRC(SCCGWA, BPB1100_AWA_1100.LST_SUP2);
        CEP.TRC(SCCGWA, BPB1100_AWA_1100.MSG_CODE);
        CEP.TRC(SCCGWA, BPB1100_AWA_1100.PRD_CD);
        CEP.TRC(SCCGWA, BPB1100_AWA_1100.REL_FLG);
        CEP.TRC(SCCGWA, BPB1100_AWA_1100);
        if (BPB1100_AWA_1100.FAV_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRFR_CD_MUSTINPUT;
            WS_FLD_NO = BPB1100_AWA_1100.FAV_CD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B015_SET_NXT_TXN() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB1100_AWA_1100.FUNC;
        if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 2208;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 1147;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 1148;
            S000_SET_SUBS_TRN();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + WS_FUNC_FLG + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_QUERY_FAVINF_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFFAV);
        BPCOFFAV.FUNC = 'I';
        BPCOFFAV.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCOFFAV.OUTPUT_FMT);
        R000_TRANS_DATA_PARAMETER();
        CEP.TRC(SCCGWA, BPCOFFAV.OUTPUT_FMT);
        S000_CALL_BPZFSFAV();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPB1100_AWA_1100.FAV_CD, BPCOFFAV.KEY.PRFR_CODE);
    }
    public void S000_CALL_BPZFSFAV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_FFAV_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCOFFAV;
        SCCCALL.ERR_FLDNO = BPB1100_AWA_1100.FAV_KND;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_SET_SBUS_TRN, SCCSUBS);
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
    public void S000_CALL_SCSSCKDT() throws IOException,SQLException,Exception {
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
