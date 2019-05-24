package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSRULM {
    String JIBS_tmp_str[] = new String[10];
    String K_AP_MMO = "TD";
    String K_SPRD_FMT = "TD516";
    String K_TIRUL_TYP = "TIRUL";
    String K_HIS_FMT = "TDCIRULP";
    String K_HIS_RMK = "TD PRODUCT SPRD MAINTENANCE";
    String K_SYS_ERR = "SC6001";
    String WS_MSGID = " ";
    short WS_I = 0;
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    TDCQIRUL TDCQIRUL = new TDCQIRUL();
    TDCIRULP TDCIRULP = new TDCIRULP();
    TDCIRULP TDCIRULPO = new TDCIRULP();
    SCCGWA SCCGWA;
    TDCSRULM TDCSRULM;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCSRULM TDCSRULM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSRULM = TDCSRULM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSRULM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (TDCSRULM.FUNC == 'A') {
            if (TDCSRULM.CD_M.INT_RUL_CD_M.trim().length() > 0) {
                B140_INQ_IRUL_PARM();
                B300_OUTPUT_INF();
            }
        } else if (TDCSRULM.FUNC == 'D'
            || TDCSRULM.FUNC == 'M'
            || TDCSRULM.FUNC == 'I') {
            B140_INQ_IRUL_PARM();
            B300_OUTPUT_INF();
        } else {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
        }
        SCCSUBS.AP_CODE = 12;
        SCCSUBS.TR_CODE = 5021;
        S000_SET_SUBS_TRN();
    }
    public void B140_INQ_IRUL_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = K_TIRUL_TYP;
        if (TDCSRULM.FUNC == 'A') {
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, TDCSRULM.CD_M);
        } else {
            BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, TDCSRULM.CD);
        }
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP);
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQIRUL);
        TDCQIRUL.FUNC = TDCSRULM.FUNC;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCSRULM.CD);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQIRUL.CD);
        TDCQIRUL.DESC = BPRPRMT.DESC;
        TDCQIRUL.CDESC = BPRPRMT.CDESC;
        TDCQIRUL.EFF_DT = BPCPRMM.EFF_DT;
        TDCQIRUL.EXP_DT = BPCPRMM.EXP_DT;
        TDCQIRUL.TIER_TYPE = TDCIRULP.TIER_TYPE;
        TDCQIRUL.TIER_TYPE2 = TDCIRULP.TIER_TYPE2;
        TDCQIRUL.SPRD_TYPE = TDCIRULP.SPRD_TYPE;
        TDCQIRUL.TIER_RULE = TDCIRULP.TIER_RULE;
        TDCQIRUL.EQU_CCY = TDCIRULP.EQU_CCY;
        TDCQIRUL.SPRD_OPT = TDCIRULP.SPRD_OPT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCIRULP.CCY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQIRUL.CCY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCIRULP.SPRD_DATA2);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQIRUL.SPRD_DATA2);
        for (WS_I = 1; WS_I <= 25; WS_I += 1) {
            TDCQIRUL.SPRD_DATA3.SPRD_PCTS[WS_I-1].SPRD_PCT = TDCIRULP.SPRD_DATA3.SPRD_PCTS[WS_I-1].SPRD_PCT;
            TDCQIRUL.SPRD_DATA3.SPRD_PNTS[WS_I-1].SPRD_PNT = TDCIRULP.SPRD_DATA3.SPRD_PNTS[WS_I-1].SPRD_PNT;
            TDCQIRUL.SPRD_DATA3.BASE_RATS[WS_I-1].BASE_RAT = TDCIRULP.SPRD_DATA3.BASE_RATS[WS_I-1].BASE_RAT;
        }
        CEP.TRC(SCCGWA, TDCQIRUL.CD.INT_RUL_CD);
        CEP.TRC(SCCGWA, TDCQIRUL.DESC);
        CEP.TRC(SCCGWA, TDCQIRUL.CDESC);
        CEP.TRC(SCCGWA, TDCQIRUL.EFF_DT);
        CEP.TRC(SCCGWA, TDCQIRUL.EXP_DT);
        CEP.TRC(SCCGWA, TDCQIRUL.TIER_TYPE);
        for (WS_I = 1; WS_I <= 20; WS_I += 1) {
            CEP.TRC(SCCGWA, TDCQIRUL.CCY.OCCURS15[WS_I-1].SPT_CCY);
        }
        for (WS_I = 1; WS_I <= 25; WS_I += 1) {
            CEP.TRC(SCCGWA, TDCQIRUL.TIER_TYPE2);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA2.TERMS1[WS_I-1].SPRD_TERM2);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA2.AMTS[WS_I-1].SPRD_AMT2);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA2.LVLS[WS_I-1].SPRD_SRV_LVL2);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA2.GRPS_NOS[WS_I-1].SPRD_GRPS_NO2);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA2.CUS_AUMS[WS_I-1].CUS_AUM2);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA2.CUS_ISTRYS[WS_I-1].CUS_ISTRY2);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA2.TERMS[WS_I-1].RGN_NO2);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA2.CHNL_NOS[WS_I-1].CHNL_NO2);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA3.SPRD_PCTS[WS_I-1].SPRD_PCT);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA3.SPRD_PNTS[WS_I-1].SPRD_PNT);
            CEP.TRC(SCCGWA, TDCQIRUL.SPRD_DATA3.BASE_RATS[WS_I-1].BASE_RAT);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_SPRD_FMT;
        SCCFMT.DATA_PTR = TDCQIRUL;
        SCCFMT.DATA_LEN = 2498;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-SET-SUBS-TRANS", SCCSUBS);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
