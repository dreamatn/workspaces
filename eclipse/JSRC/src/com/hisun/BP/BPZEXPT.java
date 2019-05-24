package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.FX.FXCMSG_ERROR_MSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZEXPT {
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_R_EXPS = "BP-R-EXRP-B       ";
    String CPN_R_EXPM = "BP-R-EXRP-M       ";
    String CPN_REC_NHIS = "BP-REC-NHIS       ";
    String CPY_BPREXRP = "BPREXRP ";
    String K_OUTPUT_FMT = "BP355";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_FWD_TENOR = "FWDT ";
    int K_MAX_ROW = 50;
    int K_MAX_COL = 99;
    int K_COL_CNT = 13;
    int K_MAX_DATE = 99991231;
    String K_CCY_USD = "USD";
    String K_CCY_HKD = "HKD";
    String WS_CCY = " ";
    String WS_FWD_TENOR = " ";
    short WS_MPAG_CNT = 0;
    BPZEXPT_WS_MPAG_DATA WS_MPAG_DATA = new BPZEXPT_WS_MPAG_DATA();
    char WS_EX_RATE_USE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCO355 BPCO355 = new BPCO355();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCTEXPM BPCTEXPM = new BPCTEXPM();
    BPCPRGST BPCPRGST = new BPCPRGST();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPREXRD BPREXRD = new BPREXRD();
    BPREXRD BPREXRDO = new BPREXRD();
    BPREXRD BPREXRDN = new BPREXRD();
    BPREXRP BPREXRP = new BPREXRP();
    BPREXRP BPREXRPO = new BPREXRP();
    BPREXRP BPREXRPN = new BPREXRP();
    BPREXRPH BPREXRPH = new BPREXRPH();
    BPCREXPS BPCREXPS = new BPCREXPS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCEXPT BPCEXPT;
    public void MP(SCCGWA SCCGWA, BPCEXPT BPCEXPT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCEXPT = BPCEXPT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZEXPT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCEXPT.RC);
        IBS.init(SCCGWA, BPREXRDN);
        IBS.init(SCCGWA, BPREXRDO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCEXPT);
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCEXPT.FUNC == 'I') {
            B010_INQUIRE_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCEXPT.FUNC == 'A') {
            B020_CREATE_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCEXPT.FUNC == 'M') {
            B030_MODIFY_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCEXPT.FUNC == 'B') {
            B040_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCEXPT.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCEXPT.FUNC);
        if ((BPCEXPT.FUNC != 'I' 
            && BPCEXPT.FUNC != 'A' 
            && BPCEXPT.FUNC != 'M' 
            && BPCEXPT.FUNC != 'D' 
            && BPCEXPT.FUNC != 'B' 
            && BPCEXPT.FUNC != 'N')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCEXPT.CCY.trim().length() > 0) {
            WS_CCY = BPCEXPT.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        if (BPCEXPT.EXR_TYP.equalsIgnoreCase("MDR")) {
            IBS.CPY2CLS(SCCGWA, FXCMSG_ERROR_MSG.FX_NOT_MDR, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCEXPT.EXR_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPREXRT);
            IBS.init(SCCGWA, BPCPRMR);
            BPREXRT.KEY.TYP = "EXRT";
            BPREXRT.KEY.CD = BPCEXPT.EXR_TYP;
            BPCPRMR.DAT_PTR = BPREXRT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_EXR_TYPE, BPCEXPT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCEXPT.BR);
        if ((BPCEXPT.BR == 999999) 
            || (BPCEXPT.FUNC == 'A' 
            && BPCEXPT.BR == 0)) {
            IBS.CPY2CLS(SCCGWA, FXCMSG_ERROR_MSG.FX_BRANCH_ERR, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCEXPT.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCEXPT.BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
        }
    }
    public void B010_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRP);
        IBS.init(SCCGWA, BPCTEXPM);
        BPREXRP.KEY.BR = BPCEXPT.BR;
        BPREXRP.KEY.EXR_TYP = BPCEXPT.EXR_TYP;
        BPREXRP.KEY.CCY = BPCEXPT.CCY;
        BPCTEXPM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXPM();
        if (pgmRtn) return;
        if (BPCTEXPM.RETURN_INFO == 'F') {
        } else if (BPCTEXPM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTEXPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRP);
        IBS.init(SCCGWA, BPCREXPS);
        BPREXRP.KEY.BR = BPCEXPT.BR;
        BPREXRP.KEY.EXR_TYP = BPCEXPT.EXR_TYP;
        BPREXRP.KEY.CCY = BPCEXPT.CCY;
        BPCREXPS.INFO.FUNC = '6';
        S000_CALL_BPZTEXPS();
        if (pgmRtn) return;
        BPCREXPS.INFO.FUNC = 'R';
        S000_CALL_BPZTEXPS();
        if (pgmRtn) return;
        if (BPCREXPS.INFO.RTN_INFO == 'N') {
        } else {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        }
        BPCREXPS.INFO.FUNC = 'E';
        S000_CALL_BPZTEXPS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTEXPM);
        IBS.init(SCCGWA, BPREXRP);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCTEXPM.INFO.FUNC = 'C';
        S000_CALL_BPZTEXPM();
        if (pgmRtn) return;
        if (BPCTEXPM.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_TYPE_EXIST, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S040_ADD_HIS_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTEXPM);
        IBS.init(SCCGWA, BPREXRPH);
        BPCTEXPM.INFO.FUNC = 'C';
        BPREXRPH.KEY.EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPREXRPH.KEY.EXP_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPREXRPH.KEY.BR = BPCEXPT.BR;
        BPREXRPH.KEY.EXR_TYP = BPCEXPT.EXR_TYP;
        BPREXRPH.KEY.CCY = BPCEXPT.CCY;
        BPREXRPH.CMP_FLG = BPCEXPT.CMP_FLG;
        BPREXRPH.HCS_BP = BPCEXPT.HCS_BP;
        BPREXRPH.HFX_BP = BPCEXPT.HFX_BP;
        BPREXRPH.HCS_SP = BPCEXPT.HCS_SP;
        BPREXRPH.HFX_SP = BPCEXPT.HFX_SP;
        BPREXRPH.CS_BP = BPCEXPT.CS_BP;
        BPREXRPH.FX_BP = BPCEXPT.FX_BP;
        BPREXRPH.CS_SP = BPCEXPT.CS_SP;
        BPREXRPH.FX_SP = BPCEXPT.FX_SP;
        BPREXRPH.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPREXRPH.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPREXRPH.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPREXRPH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZTEXPH();
        if (pgmRtn) return;
        if (BPCTEXPM.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_TYPE_EXIST, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRP);
        IBS.init(SCCGWA, BPCTEXPM);
        BPREXRP.KEY.EXR_TYP = BPCEXPT.EXR_TYP;
        BPREXRP.KEY.CCY = BPCEXPT.CCY;
        BPCTEXPM.INFO.FUNC = 'R';
        S000_CALL_BPZTEXPM();
        if (pgmRtn) return;
        if (BPCTEXPM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPREXRP, BPREXRPO);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, BPREXRP, BPREXRPN);
        BPCTEXPM.INFO.FUNC = 'U';
        S000_CALL_BPZTEXPM();
        if (pgmRtn) return;
    }
    public void B040_BROWSE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCEXPT.BR);
        CEP.TRC(SCCGWA, BPCEXPT.EXR_TYP);
        CEP.TRC(SCCGWA, BPCEXPT.CCY);
        IBS.init(SCCGWA, BPREXRP);
        IBS.init(SCCGWA, BPCREXPS);
        CEP.TRC(SCCGWA, BPCEXPT.BR);
        if (BPCEXPT.BR != 0) {
            BPREXRP.KEY.BR = BPCEXPT.BR;
        }
        CEP.TRC(SCCGWA, BPCEXPT.EXR_TYP);
        CEP.TRC(SCCGWA, BPCEXPT.CCY);
        if (BPCEXPT.EXR_TYP.trim().length() == 0) {
            BPREXRP.KEY.EXR_TYP = "%%%";
        } else {
            BPREXRP.KEY.EXR_TYP = BPCEXPT.EXR_TYP;
        }
        if (BPCEXPT.CCY.trim().length() == 0) {
            BPREXRP.KEY.CCY = "%%%";
        } else {
            BPREXRP.KEY.CCY = BPCEXPT.CCY;
        }
        BPCREXPS.INFO.FUNC = '1';
        S000_CALL_BPZTEXPS();
        if (pgmRtn) return;
        BPCREXPS.INFO.FUNC = 'R';
        S000_CALL_BPZTEXPS();
        if (pgmRtn) return;
        if (BPCREXPS.INFO.RTN_INFO == 'Y') {
            B040_01_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCREXPS.INFO.RTN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B030_COMPARE_BR();
            if (pgmRtn) return;
            if (BPCPRGST.LVL_RELATION == 'A') {
                CEP.TRC(SCCGWA, "MPAG DEVHZ");
                WS_MPAG_CNT += 1;
                B040_01_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            BPCREXPS.INFO.FUNC = 'R';
            S000_CALL_BPZTEXPS();
            if (pgmRtn) return;
        }
        BPCREXPS.INFO.FUNC = 'E';
        S000_CALL_BPZTEXPS();
        if (pgmRtn) return;
    }
    public void B030_COMPARE_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCPRGST.BR1 == BPCPRGST.BR2) {
            BPCPRGST.FLAG = 'Y';
            BPCPRGST.LVL_RELATION = 'A';
        } else {
            S000_CALL_BPZPRGST();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPRGST.FLAG);
        CEP.TRC(SCCGWA, BPCPRGST.LVL_RELATION);
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRP);
        IBS.init(SCCGWA, BPCTEXPM);
        BPREXRP.KEY.BR = BPCEXPT.BR;
        BPREXRP.KEY.EXR_TYP = BPCEXPT.EXR_TYP;
        BPREXRP.KEY.CCY = BPCEXPT.CCY;
        BPCTEXPM.INFO.FUNC = 'R';
        S000_CALL_BPZTEXPM();
        if (pgmRtn) return;
        if (BPCTEXPM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            S040_ADD_HIS_RATE();
            if (pgmRtn) return;
        }
        BPCTEXPM.INFO.FUNC = 'D';
        S000_CALL_BPZTEXPM();
        if (pgmRtn) return;
        if (BPCTEXPM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
        CEP.TRC(SCCGWA, BPCQCCY.RC.RTNCODE);
        CEP.TRC(SCCGWA, BPCQCCY.RC);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCQCCY.DATA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCEXPT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZTEXPH() throws IOException,SQLException,Exception {
        BPCTEXPM.POINTER = BPREXRPH;
        BPCTEXPM.LEN = 249;
        IBS.CALLCPN(SCCGWA, "BP-R-EX-HIS", BPCTEXPM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_BPZTEXPM() throws IOException,SQLException,Exception {
        BPCTEXPM.POINTER = BPREXRP;
        CEP.TRC(SCCGWA, "DEVHZ1722");
        BPCTEXPM.LEN = 235;
        IBS.CALLCPN(SCCGWA, CPN_R_EXPM, BPCTEXPM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_BPZTEXPS() throws IOException,SQLException,Exception {
        BPCREXPS.INFO.POINTER = BPREXRP;
        BPCREXPS.INFO.LEN = 235;
        IBS.CALLCPN(SCCGWA, CPN_R_EXPS, BPCREXPS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 12;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPREXRP.KEY.BR = BPCEXPT.BR;
        BPREXRP.KEY.EXR_TYP = BPCEXPT.EXR_TYP;
        BPREXRP.KEY.CCY = BPCEXPT.CCY;
        BPREXRP.CMP_FLG = BPCEXPT.CMP_FLG;
        BPREXRP.CCS_BP = BPCEXPT.CCS_BP;
        BPREXRP.CFX_BP = BPCEXPT.CFX_BP;
        BPREXRP.CCS_SP = BPCEXPT.CCS_SP;
        BPREXRP.CFX_SP = BPCEXPT.CFX_SP;
        BPREXRP.HCS_BP = BPCEXPT.HCS_BP;
        BPREXRP.HFX_BP = BPCEXPT.HFX_BP;
        BPREXRP.HCS_SP = BPCEXPT.HCS_SP;
        BPREXRP.HFX_SP = BPCEXPT.HFX_SP;
        BPREXRP.CS_BP = BPCEXPT.CS_BP;
        BPREXRP.FX_BP = BPCEXPT.FX_BP;
        BPREXRP.CS_SP = BPCEXPT.CS_SP;
        BPREXRP.FX_SP = BPCEXPT.FX_SP;
        BPREXRP.UPD_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPREXRP.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPREXRP.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPREXRP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_MPAG_DATA);
        WS_MPAG_DATA.WS_L_BR = BPREXRP.KEY.BR;
        WS_MPAG_DATA.WS_L_EXR_TYP = BPREXRP.KEY.EXR_TYP;
        WS_MPAG_DATA.WS_L_CCY = BPREXRP.KEY.CCY;
        CEP.TRC(SCCGWA, WS_MPAG_CNT);
        CEP.TRC(SCCGWA, BPREXRP.KEY.BR);
        CEP.TRC(SCCGWA, BPREXRP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPREXRP.KEY.CCY);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_MPAG_DATA);
        SCCMPAG.DATA_LEN = 12;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCEXPT.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPREXRP.KEY);
