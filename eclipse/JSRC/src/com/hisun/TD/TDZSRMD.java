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

public class TDZSRMD {
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
    TDCIRULP TDCIRULPO = new TDCIRULP();
    SCCGWA SCCGWA;
    TDCSRMD TDCSRMD;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCSRMD TDCSRMD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSRMD = TDCSRMD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSRMD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B130_DEL_IRUL_PARM();
        B230_WRT_NHIS_D();
        B300_OUTPUT_INF();
    }
    public void B130_DEL_IRUL_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = K_TIRUL_TYP;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, TDCSRMD.CD);
        BPCPRMM.EFF_DT = TDCSRMD.EFF_DT;
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULPO);
    }
    public void B230_WRT_NHIS_D() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, TDCSRMD.CD);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_FMT;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.OLD_DAT_PT = TDCIRULPO;
        S000_CALL_BPZPNHIS();
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQIRUL);
        TDCQIRUL.FUNC = 'D';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCSRMD.CD);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQIRUL.CD);
        TDCQIRUL.DESC = TDCSRMD.DESC;
        TDCQIRUL.CDESC = TDCSRMD.CDESC;
        TDCQIRUL.EFF_DT = TDCSRMD.EFF_DT;
        TDCQIRUL.EXP_DT = TDCSRMD.EXP_DT;
        TDCQIRUL.TIER_TYPE = TDCSRMD.TIER_TYPE;
        TDCQIRUL.TIER_TYPE2 = TDCSRMD.TIER_TYPE2;
        TDCQIRUL.SPRD_TYPE = TDCSRMD.SPRD_TYPE;
        TDCQIRUL.TIER_RULE = TDCSRMD.TIER_RULE;
        TDCQIRUL.EQU_CCY = TDCSRMD.EQU_CCY;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCIRULPO.SPRD_DATA2);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCQIRUL.SPRD_DATA2);
        for (WS_I = 1; WS_I <= 25; WS_I += 1) {
            TDCQIRUL.SPRD_DATA3.SPRD_PCTS[WS_I-1].SPRD_PCT = TDCIRULPO.SPRD_DATA3.SPRD_PCTS[WS_I-1].SPRD_PCT;
            TDCQIRUL.SPRD_DATA3.SPRD_PNTS[WS_I-1].SPRD_PNT = TDCIRULPO.SPRD_DATA3.SPRD_PNTS[WS_I-1].SPRD_PNT;
            TDCQIRUL.SPRD_DATA3.BASE_RATS[WS_I-1].BASE_RAT = TDCIRULPO.SPRD_DATA3.BASE_RATS[WS_I-1].BASE_RAT;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_SPRD_FMT;
        SCCFMT.DATA_PTR = TDCQIRUL;
        SCCFMT.DATA_LEN = 2498;
        CEP.TRC(SCCGWA, TDCQIRUL);
        IBS.FMT(SCCGWA, SCCFMT);
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
