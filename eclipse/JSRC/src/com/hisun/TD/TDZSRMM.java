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

public class TDZSRMM {
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
    TDCSRMM TDCSRMM;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, TDCSRMM TDCSRMM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSRMM = TDCSRMM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSRMM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B120_MOD_IRUL_PARM();
        B220_WRT_NHIS_M();
        B300_OUTPUT_INF();
    }
    public void B120_MOD_IRUL_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = K_TIRUL_TYP;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, TDCSRMM.CD);
        BPCPRMM.EFF_DT = TDCSRMM.EFF_DT;
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULPO);
        R000_MOVE_TO_PARM();
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, TDCIRULPO);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCIRULP);
        if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0]) 
            && TDCSRMM.DESC.equalsIgnoreCase(BPRPRMT.DESC) 
            && TDCSRMM.CDESC.equalsIgnoreCase(BPRPRMT.CDESC) 
            && TDCSRMM.EXP_DT == BPCPRMM.EXP_DT) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_NO_MOD;
            S000_ERR_MSG_PROC();
        }
        BPCPRMM.FUNC = '2';
        BPCPRMM.EXP_DT = TDCSRMM.EXP_DT;
        BPRPRMT.DESC = TDCSRMM.DESC;
        BPRPRMT.CDESC = TDCSRMM.CDESC;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCIRULP);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
    }
    public void B220_WRT_NHIS_M() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, TDCSRMM.CD);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.FMT_ID = K_HIS_FMT;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.OLD_DAT_PT = TDCIRULPO;
        BPCPNHIS.INFO.NEW_DAT_PT = TDCIRULP;
        S000_CALL_BPZPNHIS();
    }
    public void B300_OUTPUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCQIRUL);
        TDCQIRUL.FUNC = 'M';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCSRMM.CD);
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
        TDCIRULP.TIER_TYPE = TDCSRMM.TIER_TYPE;
        TDCIRULP.TIER_TYPE2 = TDCSRMM.TIER_TYPE2;
        TDCIRULP.SPRD_TYPE = TDCSRMM.SPRD_TYPE;
        TDCIRULP.TIER_RULE = TDCSRMM.TIER_RULE;
        TDCIRULP.EQU_CCY = TDCSRMM.EQU_CCY;
        TDCIRULP.SPRD_OPT = TDCSRMM.SPRD_OPT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCSRMM.CCY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP.CCY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCSRMM.SPRD_DATA2);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCIRULP.SPRD_DATA2);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, TDCSRMM.SPRD_DATA3);
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
