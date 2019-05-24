package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1114 {
    char K_ERROR = 'E';
    String K_OUTPUT_FMT1 = "BPX01";
    String K_OUTPUT_FMT2 = "BP053";
    String CPN_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String CPN_F_S_MAIN_FCOM = "BP-F-S-MAINTAIN-FCOM";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOFCOM BPCOFCOM = new BPCOFCOM();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCGWA SCCGWA;
    BPB1000_AWA_1000 BPB1000_AWA_1000;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1114 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1000_AWA_1000>");
        BPB1000_AWA_1000 = (BPB1000_AWA_1000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B015_SET_NXT_TXN();
        B020_QUERY_COM_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B015_SET_NXT_TXN() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = BPB1000_AWA_1000.FUNC;
        CEP.TRC(SCCGWA, BPB1000_AWA_1000.FUNC);
        CEP.TRC(SCCGWA, WS_FUNC_FLG);
        if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 1112;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 2216;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 1113;
            S000_SET_SUBS_TRN();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, SCCSUBS.AP_CODE);
        CEP.TRC(SCCGWA, SCCSUBS.TR_CODE);
    }
    public void B020_QUERY_COM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFCOM);
        R000_TRANS_DATA_PARAMETER();
        BPCOFCOM.FUNC = 'I';
        BPCOFCOM.OUTPUT_FMT = K_OUTPUT_FMT1;
        CEP.TRC(SCCGWA, BPCOFCOM.OUTPUT_FMT);
        S000_CALL_BPZFSCOM();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOFCOM.KEY.FEE_CODE = BPB1000_AWA_1000.FEE_CD;
        CEP.TRC(SCCGWA, BPCOFCOM.KEY.FEE_CODE);
        BPCOFCOM.KEY.REG_CODE = BPB1000_AWA_1000.AREA;
        CEP.TRC(SCCGWA, BPCOFCOM.KEY.REG_CODE);
        BPCOFCOM.KEY.CHN_NO = BPB1000_AWA_1000.FEE_CHNL;
        CEP.TRC(SCCGWA, BPCOFCOM.KEY.CHN_NO);
        CEP.TRC(SCCGWA, BPB1000_AWA_1000.FEE_CHNL);
        BPCOFCOM.KEY.FREE_FMT = BPB1000_AWA_1000.FERR_FMT;
        CEP.TRC(SCCGWA, BPCOFCOM.KEY.FREE_FMT);
        CEP.TRC(SCCGWA, BPB1000_AWA_1000.FERR_FMT);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_SET_SBUS_TRN;
        SCCCALL.COMMAREA_PTR = SCCSUBS;
        SCCCALL.ERR_FLDNO = BPB1000_AWA_1000.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZFSCOM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_S_MAIN_FCOM, BPCOFCOM);
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
