package com.hisun.DD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCQCCY;
import com.hisun.BP.BPCRMBPM;
import com.hisun.BP.BPRPARM;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class DDZIDPAM {
    boolean pgmRtn = false;
    String K_PDOM1_PRM_TYP = "PDD11";
    String K_PDOM2_PRM_TYP = "PDD12";
    short WS_IDX = 0;
    DDZIDPAM_WS_DPAM1_VAL WS_DPAM1_VAL = new DDZIDPAM_WS_DPAM1_VAL();
    DDZIDPAM_WS_DPAM2_VAL WS_DPAM2_VAL = new DDZIDPAM_WS_DPAM2_VAL();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCGWA SCCGWA;
    DDCIDPAM DDCIDPAM;
    DDVDPAM1 DDVDPAM1;
    DDVDPAM2 DDVDPAM2;
    public void MP(SCCGWA SCCGWA, DDCIDPAM DDCIDPAM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCIDPAM = DDCIDPAM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZIDPAM return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        DDVDPAM1 = (DDVDPAM1) DDCIDPAM.DDVDPAM1_PTR;
        DDVDPAM2 = (DDVDPAM2) DDCIDPAM.DDVDPAM2_PTR;
        BPCRMBPM.PTR = BPRPARM;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_PROC();
        if (pgmRtn) return;
        if (DDCIDPAM.DATA.CCY.trim().length() == 0) {
            B020_GET_DORM_PARM_PROC();
            if (pgmRtn) return;
        } else {
            B030_QUERY_DORM_PARM_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCIDPAM.DATA.CI_TYPE);
        CEP.TRC(SCCGWA, DDCIDPAM.DATA.CCY);
        if (DDCIDPAM.DATA.CI_TYPE == ' ') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CI_TYP_M_INPUT, DDCIDPAM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCIDPAM.DATA.CCY.trim().length() > 0 
            && !DDCIDPAM.DATA.CCY.equalsIgnoreCase("999")) {
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_DORM_PARM_PROC() throws IOException,SQLException,Exception {
        if (DDCIDPAM.DATA.CI_TYPE == '1') {
            B020_010_GET_PDORM1_PARM_PROC();
            if (pgmRtn) return;
        } else if (DDCIDPAM.DATA.CI_TYPE == '2') {
            B020_020_GET_PDORM2_PARM_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCIDPAM.DATA.CI_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_010_GET_PDORM1_PARM_PROC() throws IOException,SQLException,Exception {
        B020_010_010_STARTBR_PARM();
        if (pgmRtn) return;
        R000_R_NEXT_PARM();
        if (pgmRtn) return;
        while (WS_IDX <= 49 
            && BPCRMBPM.RETURN_INFO != 'L') {
            WS_IDX += 1;
            DDVDPAM1.TXT[WS_IDX-1].CD = BPRPARM.KEY.CODE;
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, DDVDPAM1.TXT[WS_IDX-1].PARM);
            CEP.TRC(SCCGWA, DDVDPAM1.TXT[WS_IDX-1].CD);
            CEP.TRC(SCCGWA, DDVDPAM1.TXT[WS_IDX-1].PARM);
            CEP.TRC(SCCGWA, DDVDPAM1.TXT[WS_IDX-1].PARM.DORM_CCY);
            CEP.TRC(SCCGWA, DDVDPAM1.TXT[WS_IDX-1].PARM.DORM_PERIOD1);
            CEP.TRC(SCCGWA, DDVDPAM1.TXT[WS_IDX-1].PARM.DORM_BAL);
            R000_R_NEXT_PARM();
            if (pgmRtn) return;
        }
        R000_END_BR_PARM();
        if (pgmRtn) return;
    }
    public void B020_020_GET_PDORM2_PARM_PROC() throws IOException,SQLException,Exception {
        B020_010_020_STARTBR_PARM();
        if (pgmRtn) return;
        R000_R_NEXT_PARM();
        if (pgmRtn) return;
        while (WS_IDX <= 49 
            && BPCRMBPM.RETURN_INFO != 'L') {
            WS_IDX += 1;
            DDVDPAM2.TXT[WS_IDX-1].CD = BPRPARM.KEY.CODE;
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, DDVDPAM2.TXT[WS_IDX-1].PARM);
            CEP.TRC(SCCGWA, DDVDPAM2.TXT[WS_IDX-1].CD);
            CEP.TRC(SCCGWA, DDVDPAM2.TXT[WS_IDX-1].PARM.DORM_PERIOD1);
            CEP.TRC(SCCGWA, DDVDPAM2.TXT[WS_IDX-1].PARM.DORM_PERIOD2);
            CEP.TRC(SCCGWA, DDVDPAM2.TXT[WS_IDX-1].PARM.DORM_BAL);
            CEP.TRC(SCCGWA, DDVDPAM2.TXT[WS_IDX-1].PARM.DORM_PERIOD3);
            CEP.TRC(SCCGWA, DDVDPAM2.TXT[WS_IDX-1].PARM.DORM_BAL2);
            CEP.TRC(SCCGWA, DDVDPAM2.TXT[WS_IDX-1].PARM);
            R000_R_NEXT_PARM();
            if (pgmRtn) return;
        }
        R000_END_BR_PARM();
        if (pgmRtn) return;
    }
    public void B020_010_010_STARTBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = K_PDOM1_PRM_TYP;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B020_010_020_STARTBR_PARM() throws IOException,SQLException,Exception {
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = K_PDOM2_PRM_TYP;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B030_QUERY_DORM_PARM_PROC() throws IOException,SQLException,Exception {
        if (DDCIDPAM.DATA.CI_TYPE == '1') {
            B030_010_GET_PDORM1_PARM_PROC();
            if (pgmRtn) return;
        } else if (DDCIDPAM.DATA.CI_TYPE == '2') {
            B030_020_GET_PDORM2_PARM_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCIDPAM.DATA.CI_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B030_010_GET_PDORM1_PARM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, WS_DPAM1_VAL);
        BPRPRMT.KEY.TYP = K_PDOM1_PRM_TYP;
        BPRPRMT.KEY.CD = DDCIDPAM.DATA.CCY;
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_DPAM1_VAL);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, DDCIDPAM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
