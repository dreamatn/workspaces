package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.LN.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT9120 {
    String JIBS_tmp_str[] = new String[10];
    BPOT9120_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT9120_WS_TEMP_VARIABLE();
    BPOT9120_WS_COND_FLG WS_COND_FLG = new BPOT9120_WS_COND_FLG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCFCTRB BPCFCTRB = new BPCFCTRB();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    BPRFBAS BPRFBAS = new BPRFBAS();
    LNCICIQ LNCICIQ = new LNCICIQ();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    BPB9120_AWA_9120 BPB9120_AWA_9120;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT9120 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9120_AWA_9120>");
        BPB9120_AWA_9120 = (BPB9120_AWA_9120) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, WS_COND_FLG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_BRW_FEECTR_PROC();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "IIIIIIIIII");
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.CTRT_NO);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.FEE_TYP);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.REL_CTNO);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.CI_NO);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.STR_DT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.MAT_DT);
        CEP.TRC(SCCGWA, BPB9120_AWA_9120.FEE_STS);
        if (BPB9120_AWA_9120.FEE_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPRFBAS);
            IBS.init(SCCGWA, BPCTFBAS);
            BPRFBAS.KEY.FEE_CODE = BPB9120_AWA_9120.FEE_TYP;
            BPCTFBAS.INFO.FUNC = 'Q';
            BPCTFBAS.INFO.POINTER = BPRFBAS;
            BPCTFBAS.INFO.REC_LEN = 312;
            S000_CALL_BPZTFBAS();
        }
        if (BPB9120_AWA_9120.MAT_DT != 0 
            && BPB9120_AWA_9120.STR_DT != 0 
            && BPB9120_AWA_9120.MAT_DT < BPB9120_AWA_9120.STR_DT) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MATUR_DATE_LESS, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB9120_AWA_9120.STR_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_BRW_FEECTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCTRB);
        BPCFCTRB.CTRT_NO = BPB9120_AWA_9120.CTRT_NO;
        BPCFCTRB.FEE_TYPE = BPB9120_AWA_9120.FEE_TYP;
        BPCFCTRB.REL_CTRT = BPB9120_AWA_9120.REL_CTNO;
        BPCFCTRB.CI_NO = BPB9120_AWA_9120.CI_NO;
        BPCFCTRB.START_DATE = BPB9120_AWA_9120.STR_DT;
        BPCFCTRB.MATURITY_DATE = BPB9120_AWA_9120.MAT_DT;
        BPCFCTRB.FEE_STATUS = BPB9120_AWA_9120.FEE_STS;
        S000_CALL_BPZFCTRB();
    }
    public void S000_CALL_BPZFCTRB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-CTRT-BROWSE", BPCFCTRB);
        CEP.TRC(SCCGWA, BPCFCTRB.RC);
        if (BPCFCTRB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFCTRB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZICIQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-CI-INFO", LNCICIQ);
        CEP.TRC(SCCGWA, LNCICIQ.RC);
        if (LNCICIQ.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REL_CTR_NOT_FOUND, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB9120_AWA_9120.REL_CTNO_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-T-FEE-INFO", BPCTFBAS);
        CEP.TRC(SCCGWA, BPCTFBAS.RC);
        if (BPCTFBAS.RC.RC_CODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB9120_AWA_9120.FEE_TYP_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
