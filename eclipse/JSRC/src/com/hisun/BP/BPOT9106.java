package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class BPOT9106 {
    boolean pgmRtn = false;
    String CPN_S_FEEPRD_DEF = "BP-S-MGM-FEEPRD  ";
    String CPN_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    short K_AP_CODE = 999;
    short K_NEXT_TXN_CTRT = 9122;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_J = 0;
    BPOT9106_WS_COND_FLG WS_COND_FLG = new BPOT9106_WS_COND_FLG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCSUBS SCCSUBS = new SCCSUBS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCFPDD1 BPCFPDD1 = new BPCFPDD1();
    BPCOUFEC BPCOUFEC = new BPCOUFEC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB9100_AWA_9100 BPB9100_AWA_9100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT9106 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9100_AWA_9100>");
        BPB9100_AWA_9100 = (BPB9100_AWA_9100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB9100_AWA_9100.ACCT_CTR);
        CEP.TRC(SCCGWA, BPB9100_AWA_9100.PRD_TYPE);
        CEP.TRC(SCCGWA, BPB9100_AWA_9100.EFF_DATE);
        CEP.TRC(SCCGWA, BPB9100_AWA_9100.EXP_DATE);
        CEP.TRC(SCCGWA, BPB9100_AWA_9100.CINO);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_SET_NEXT_TXN();
        if (pgmRtn) return;
        B030_INQ_PRDT_DEFAULT_INFO();
        if (pgmRtn) return;
        B040_MOVE_DATA_TO_OUT();
        if (pgmRtn) return;
        B050_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_SET_NEXT_TXN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        SCCSUBS.AP_CODE = K_AP_CODE;
        SCCSUBS.TR_CODE = K_NEXT_TXN_CTRT;
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCSUBS.AP_CODE);
        CEP.TRC(SCCGWA, SCCSUBS.TR_CODE);
    }
    public void B030_INQ_PRDT_DEFAULT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCFPDD1);
        BPRPRMT.KEY.TYP = "PRDPR";
        BPCFPDD1.KEY.ACCT_CENTRE = BPB9100_AWA_9100.ACCT_CTR;
        BPCFPDD1.KEY.PROD_TYPE = BPB9100_AWA_9100.PRD_TYPE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCFPDD1.KEY);
        BPCPRMM.FUNC = '3';
        BPCPRMM.EFF_DT = BPB9100_AWA_9100.EFF_DATE;
        BPCPRMM.EXP_DT = BPB9100_AWA_9100.EXP_DATE;
        CEP.TRC(SCCGWA, BPCFPDD1.KEY);
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "11111");
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            IBS.init(SCCGWA, BPCFPDD1);
            CEP.TRC(SCCGWA, "ACCT:999999999999");
            BPRPRMT.KEY.TYP = "PRDPR";
            BPCFPDD1.KEY.ACCT_CENTRE = 999999;
            BPCFPDD1.KEY.PROD_TYPE = BPB9100_AWA_9100.PRD_TYPE;
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCFPDD1.KEY);
            CEP.TRC(SCCGWA, BPCFPDD1.KEY);
            BPCPRMM.FUNC = '3';
            BPCPRMM.EFF_DT = BPB9100_AWA_9100.EFF_DATE;
            BPCPRMM.EXP_DT = BPB9100_AWA_9100.EXP_DATE;
            BPCPRMM.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "22222");
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRDT_NO_DEFT_INFO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
