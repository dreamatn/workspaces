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
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSRMA {
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
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDCQIRUL TDCQIRUL = new TDCQIRUL();
    TDCIRULP TDCIRULP = new TDCIRULP();
    TDCIRULP TDCIRULPO = new TDCIRULP();
    SCCGWA SCCGWA;
    TDCSRMA TDCSRMA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCSRMA TDCSRMA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSRMA = TDCSRMA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSRMA return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B110_WRT_IRUL_PARM();
        B210_WRT_NHIS_A();
        B300_OUTPUT_INF();
    }
    public void B110_WRT_IRUL_PARM() throws IOException,SQLException,Exception {
        R000_MOVE_TO_PARM();
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.FUNC = '0';
        BPRPRMT.KEY.TYP = K_TIRUL_TYP;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, TDCSRMA.CD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCIRULP);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPRPRMT.DESC = TDCSRMA.DESC;
        BPRPRMT.CDESC = TDCSRMA.CDESC;
        BPCPRMM.EFF_DT = TDCSRMA.EFF_DT;
        BPCPRMM.EXP_DT = TDCSRMA.EXP_DT;
        BPCPRMM.DAT_PTR = BPRPRMT;
        CEP.TRC(SCCGWA, BPRPRMT);
        S000_CALL_BPZPRMM();
    }
    public void B210_WRT_NHIS_A() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, TDCSRMA.CD);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_FMT;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.NEW_DAT_PT = TDCIRULP;
        S000_CALL_BPZPNHIS();
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQIRUL);
        TDCQIRUL.FUNC = 'A';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCSRMA.CD);
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
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_SPRD_FMT;
        SCCFMT.DATA_PTR = TDCQIRUL;
        SCCFMT.DATA_LEN = 2498;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_MOVE_TO_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCIRULP);
        TDCIRULP.TIER_TYPE = TDCSRMA.TIER_TYPE;
        TDCIRULP.TIER_TYPE2 = TDCSRMA.TIER_TYPE2;
        TDCIRULP.SPRD_TYPE = TDCSRMA.SPRD_TYPE;
        TDCIRULP.TIER_RULE = TDCSRMA.TIER_RULE;
        TDCIRULP.EQU_CCY = TDCSRMA.EQU_CCY;
        TDCIRULP.SPRD_OPT = TDCSRMA.SPRD_OPT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCSRMA.CCY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP.CCY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCSRMA.SPRD_DATA2);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP.SPRD_DATA2);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCSRMA.SPRD_DATA3);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP.SPRD_DATA3);
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
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
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
