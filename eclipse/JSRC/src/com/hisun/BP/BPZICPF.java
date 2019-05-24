package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;

public class BPZICPF {
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_R_ICSS = "BP-R-ICSP-B       ";
    String CPN_R_ICSM = "BP-R-ICSP-M       ";
    String CPN_REC_NHIS = "BP-REC-NHIS       ";
    String CPY_BPRICSP = "BPRICSP ";
    String K_OUTPUT_FMT = "BP356";
    String K_OUTPUT_FMT_X = "BPX01";
    int K_MAX_ROW = 50;
    int K_MAX_COL = 99;
    int K_COL_CNT = 13;
    int K_MAX_DATE = 99991231;
    String WS_CCY = " ";
    String WS_FWD_TENOR = " ";
    short WS_MPAG_CNT = 0;
    BPZICPF_WS_MPAG_DATA WS_MPAG_DATA = new BPZICPF_WS_MPAG_DATA();
    char WS_EX_RATE_USE_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCO356 BPCO356 = new BPCO356();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    CICCUST CICCUST = new CICCUST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCTICSM BPCTICSM = new BPCTICSM();
    BPREXRD BPREXRD = new BPREXRD();
    BPREXRD BPREXRDO = new BPREXRD();
    BPREXRD BPREXRDN = new BPREXRD();
    BPRICSP BPRICSP = new BPRICSP();
    BPRICSP BPRICSPO = new BPRICSP();
    BPRICSP BPRICSPN = new BPRICSP();
    BPCRICSS BPCRICSS = new BPCRICSS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCICPF BPCICPF;
    public void MP(SCCGWA SCCGWA, BPCICPF BPCICPF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCICPF = BPCICPF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZICPF return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCICPF.RC);
        IBS.init(SCCGWA, BPREXRDN);
        IBS.init(SCCGWA, BPREXRDO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCICPF);
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCICPF.FUNC == 'I') {
            B010_INQUIRE_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCICPF.FUNC == 'A') {
            B020_CREATE_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCICPF.FUNC == 'M') {
            B030_MODIFY_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCICPF.FUNC == 'B') {
            B040_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCICPF.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
            B080_HISTORY_PROC();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCICPF.FUNC);
        if ((BPCICPF.FUNC != 'I' 
            && BPCICPF.FUNC != 'A' 
            && BPCICPF.FUNC != 'M' 
            && BPCICPF.FUNC != 'D' 
            && BPCICPF.FUNC != 'B' 
            && BPCICPF.FUNC != 'N')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCICPF.CCY.trim().length() > 0) {
            WS_CCY = BPCICPF.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        }
        if (BPCICPF.EXR_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPREXRT);
            IBS.init(SCCGWA, BPCPRMR);
            BPREXRT.KEY.TYP = "EXRT";
            BPREXRT.KEY.CD = BPCICPF.EXR_TYP;
            BPCPRMR.DAT_PTR = BPREXRT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_EXR_TYPE, BPCICPF.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCICPF.CI_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, "XXXXXXXXX");
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPCICPF.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
        }
    }
    public void B010_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRICSP);
        IBS.init(SCCGWA, BPCTICSM);
        BPRICSP.KEY.CI_NO = BPCICPF.CI_NO;
        BPRICSP.EXR_TYP = BPCICPF.EXR_TYP;
        BPRICSP.CCY = BPCICPF.CCY;
        BPCTICSM.INFO.FUNC = 'Q';
        S000_CALL_BPZTICSM();
        if (pgmRtn) return;
        if (BPCTICSM.RETURN_INFO == 'F') {
        } else if (BPCTICSM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTICSM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRICSP);
        IBS.init(SCCGWA, BPCRICSS);
        BPRICSP.KEY.CI_NO = BPCICPF.CI_NO;
        BPRICSP.EXR_TYP = BPCICPF.EXR_TYP;
        BPRICSP.CCY = BPCICPF.CCY;
        BPCRICSS.INFO.FUNC = '6';
        S000_CALL_BPZTICSS();
        if (pgmRtn) return;
        BPCRICSS.INFO.FUNC = 'R';
        S000_CALL_BPZTICSS();
        if (pgmRtn) return;
        if (BPCRICSS.INFO.RTN_INFO == 'N') {
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST, BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRICSS.INFO.FUNC = 'E';
        S000_CALL_BPZTICSS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTICSM);
        IBS.init(SCCGWA, BPRICSP);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPRICSP.CRE_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRICSP.CRE_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRICSP.CRE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTICSM.INFO.FUNC = 'C';
        S000_CALL_BPZTICSM();
        if (pgmRtn) return;
        if (BPCTICSM.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_TYPE_EXIST, BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRICSP);
        IBS.init(SCCGWA, BPCTICSM);
        BPRICSP.KEY.CI_NO = BPCICPF.CI_NO;
        BPRICSP.EXR_TYP = BPCICPF.EXR_TYP;
        BPRICSP.CCY = BPCICPF.CCY;
        BPCTICSM.INFO.FUNC = 'R';
        S000_CALL_BPZTICSM();
        if (pgmRtn) return;
        if (BPCTICSM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCICPF.CI_ENM = BPRICSP.KEY.CI_ENM;
        BPCICPF.CI_CNM = BPRICSP.KEY.CI_CNM;
        IBS.CLONE(SCCGWA, BPRICSP, BPRICSPO);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPRICSP.UPD_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPRICSP.UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
        BPRICSP.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRICSP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.CLONE(SCCGWA, BPRICSP, BPRICSPN);
        BPCTICSM.INFO.FUNC = 'U';
        S000_CALL_BPZTICSM();
        if (pgmRtn) return;
    }
    public void B040_BROWSE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DEVHYY");
        CEP.TRC(SCCGWA, BPCICPF.CI_NO);
        CEP.TRC(SCCGWA, BPCICPF.EXR_TYP);
        CEP.TRC(SCCGWA, BPCICPF.CCY);
        IBS.init(SCCGWA, BPRICSP);
        IBS.init(SCCGWA, BPCRICSS);
        CEP.TRC(SCCGWA, BPCICPF.EXR_TYP);
        CEP.TRC(SCCGWA, BPCICPF.CCY);
        if (BPCICPF.CI_NO.trim().length() == 0) {
            BPRICSP.KEY.CI_NO = "%%%%%%%%%%%%";
        } else {
            BPRICSP.KEY.CI_NO = BPCICPF.CI_NO;
        }
        if (BPCICPF.EXR_TYP.trim().length() == 0) {
            BPRICSP.EXR_TYP = "%%%";
        } else {
            BPRICSP.EXR_TYP = BPCICPF.EXR_TYP;
        }
        if (BPCICPF.CCY.trim().length() == 0) {
            BPRICSP.CCY = "%%%";
        } else {
            BPRICSP.CCY = BPCICPF.CCY;
        }
        BPCRICSS.INFO.FUNC = '1';
        S000_CALL_BPZTICSS();
        if (pgmRtn) return;
        BPCRICSS.INFO.FUNC = 'R';
        S000_CALL_BPZTICSS();
        if (pgmRtn) return;
        if (BPCRICSS.INFO.RTN_INFO == 'Y') {
            B040_01_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (BPCRICSS.INFO.RTN_INFO != 'N') {
            WS_MPAG_CNT += 1;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = BPRICSP.KEY.CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            B040_01_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            BPCRICSS.INFO.FUNC = 'R';
            S000_CALL_BPZTICSS();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "TESTING");
        BPCRICSS.INFO.FUNC = 'E';
        S000_CALL_BPZTICSS();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRICSP);
        IBS.init(SCCGWA, BPCTICSM);
        BPRICSP.KEY.CI_NO = BPCICPF.CI_NO;
        BPRICSP.EXR_TYP = BPCICPF.EXR_TYP;
        BPRICSP.CCY = BPCICPF.CCY;
        CEP.TRC(SCCGWA, BPCICPF.CCY);
        CEP.TRC(SCCGWA, BPCICPF.CCY);
        CEP.TRC(SCCGWA, BPCICPF.CCY);
        BPCTICSM.INFO.FUNC = 'R';
        S000_CALL_BPZTICSM();
        if (pgmRtn) return;
        if (BPCTICSM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCTICSM.INFO.FUNC = 'D';
        S000_CALL_BPZTICSM();
        if (pgmRtn) return;
        if (BPCTICSM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_BPZTICSM() throws IOException,SQLException,Exception {
        BPCTICSM.POINTER = BPRICSP;
        BPCTICSM.LEN = 671;
        IBS.CALLCPN(SCCGWA, CPN_R_ICSM, BPCTICSM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCICPF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTICSS() throws IOException,SQLException,Exception {
        BPCRICSS.INFO.POINTER = BPRICSP;
        BPCRICSS.INFO.LEN = 671;
        IBS.CALLCPN(SCCGWA, CPN_R_ICSS, BPCRICSS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 524;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPRICSP.KEY.CI_NO = BPCICPF.CI_NO;
        BPRICSP.KEY.CI_ENM = BPCICPF.CI_ENM;
        BPRICSP.KEY.CI_CNM = BPCICPF.CI_CNM;
        BPRICSP.EXR_TYP = BPCICPF.EXR_TYP;
        BPRICSP.CCY = BPCICPF.CCY;
        BPRICSP.EFF_DT = BPCICPF.EFF_DT;
        BPRICSP.EXP_DT = BPCICPF.EXP_DT;
        BPRICSP.CMP_FLG = BPCICPF.CMP_FLG;
        BPRICSP.CS_BUY_P = BPCICPF.CS_BUY_P;
        BPRICSP.FX_BUY_P = BPCICPF.FX_BUY_P;
        BPRICSP.CS_SELL_P = BPCICPF.CS_SELL_P;
        BPRICSP.FX_SELL_P = BPCICPF.FX_SELL_P;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_MPAG_DATA);
        WS_MPAG_DATA.WS_L_CI_NO = BPRICSP.KEY.CI_NO;
        WS_MPAG_DATA.WS_L_CI_ENG_NAME = BPRICSP.KEY.CI_ENM;
        WS_MPAG_DATA.WS_L_CI_CHN_NAME = BPRICSP.KEY.CI_CNM;
        WS_MPAG_DATA.WS_L_EXR_TYP = BPRICSP.EXR_TYP;
        WS_MPAG_DATA.WS_L_CCY = BPRICSP.CCY;
        CEP.TRC(SCCGWA, WS_MPAG_CNT);
        CEP.TRC(SCCGWA, BPRICSP.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRICSP.EXR_TYP);
        CEP.TRC(SCCGWA, BPRICSP.CCY);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_MPAG_DATA);
        SCCMPAG.DATA_LEN = 524;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCICPF.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCICPF.FUNC == 'M') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPRICSPO;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRICSPN;
        }
        if (BPCICPF.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, BPRICSPO.KEY);
