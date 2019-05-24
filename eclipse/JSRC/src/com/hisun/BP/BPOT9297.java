package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCTLM_MSG;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWOUT;

public class BPOT9297 {
    boolean pgmRtn = false;
    BPOT9297_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT9297_WS_TEMP_VARIABLE();
    BPOT9297_WS_ALL_DATA WS_ALL_DATA = new BPOT9297_WS_ALL_DATA();
    BPOT9297_WS_HD_DATA WS_HD_DATA = new BPOT9297_WS_HD_DATA();
    BPOT9297_WS_P_AREA_9297 WS_P_AREA_9297 = new BPOT9297_WS_P_AREA_9297();
    BPOT9297_WS_COND_FLG WS_COND_FLG = new BPOT9297_WS_COND_FLG();
    BPCOOTLS BPCOOTLS = new BPCOOTLS();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRHSEQ BPRHSEQ = new BPRHSEQ();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCQHSEQ BPCQHSEQ = new BPCQHSEQ();
    CICACCU CICACCU = new CICACCU();
    SCCKDGAC SCCKDGAC = new SCCKDGAC();
    SCCCTLM_MSG SCCCTLM_MSG = new SCCCTLM_MSG();
    CICCUST CICCUST = new CICCUST();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    BPB9297_AWA_9297 BPB9297_AWA_9297;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT9297 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB9297_AWA_9297>");
        BPB9297_AWA_9297 = (BPB9297_AWA_9297) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_OUT_HEAD_PROC();
        if (pgmRtn) return;
        B300_PREVIEW_PROC();
        if (pgmRtn) return;
        Z_RET();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "INPUT");
        CEP.TRC(SCCGWA, BPB9297_AWA_9297.CINO);
        CEP.TRC(SCCGWA, BPB9297_AWA_9297.CCY);
        CEP.TRC(SCCGWA, BPB9297_AWA_9297.PRDT_TYP);
        if (BPB9297_AWA_9297.CINO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CI_NO_MUST_INPUT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPB9297_AWA_9297.PRDT_TYP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_TYP_MST_IPT, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = BPB9297_AWA_9297.CINO;
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (BPB9297_AWA_9297.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPB9297_AWA_9297.CCY;
            CEP.TRC(SCCGWA, BPCQCCY.DATA.CCY);
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCQCCY.RC);
            if (BPCQCCY.RC.RTNCODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_OUT_HEAD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 18;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 100;
        B_MPAG();
        if (pgmRtn) return;
        WS_HD_DATA.WS_HD_CINO = BPB9297_AWA_9297.CINO;
        WS_HD_DATA.WS_HD_CCY = BPB9297_AWA_9297.CCY;
        WS_HD_DATA.WS_HD_PRDT_TYP = BPB9297_AWA_9297.PRDT_TYP;
        WS_ALL_DATA.WS_ALL_CINO = BPB9297_AWA_9297.CINO;
        WS_ALL_DATA.WS_ALL_CCY = BPB9297_AWA_9297.CCY;
        WS_ALL_DATA.WS_ALL_PRDT_TYP = BPB9297_AWA_9297.PRDT_TYP;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_HD_DATA);
        SCCMPAG.DATA_LEN = 18;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_HD_DATA.WS_HD_CINO);
        CEP.TRC(SCCGWA, WS_HD_DATA.WS_HD_CCY);
        CEP.TRC(SCCGWA, WS_HD_DATA.WS_HD_PRDT_TYP);
        CEP.TRC(SCCGWA, WS_ALL_DATA.WS_ALL_CINO);
        CEP.TRC(SCCGWA, WS_ALL_DATA.WS_ALL_CCY);
        CEP.TRC(SCCGWA, WS_ALL_DATA.WS_ALL_PRDT_TYP);
    }
    public void B300_PREVIEW_PROC() throws IOException,SQLException,Exception {
        if (BPB9297_AWA_9297.CCY.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_TMP_CCY = "ALL";
        } else {
            WS_TEMP_VARIABLE.WS_TMP_CCY = BPB9297_AWA_9297.CCY;
        }
        WS_TEMP_VARIABLE.WS_TMP_PRDT_TYP = BPB9297_AWA_9297.PRDT_TYP;
        WS_TEMP_VARIABLE.WS_TMP_CINO = BPB9297_AWA_9297.CINO;
        B200_10_GET_P_TABLE();
        if (pgmRtn) return;
        WS_COND_FLG.WS_END_FLG = 'N';
        for (WS_TEMP_VARIABLE.WS_I = 1; WS_TEMP_VARIABLE.WS_I <= 100 
            && WS_COND_FLG.WS_END_FLG != 'Y'; WS_TEMP_VARIABLE.WS_I += 1) {
            if (WS_P_AREA_9297.WS_P_DATA_9297[WS_TEMP_VARIABLE.WS_I-1].BPOT9297_FILLER34.WS_CCY.equalsIgnoreCase(WS_TEMP_VARIABLE.WS_TMP_CCY) 
                && WS_P_AREA_9297.WS_P_DATA_9297[WS_TEMP_VARIABLE.WS_I-1].BPOT9297_FILLER34.WS_PRDT_TYP.equalsIgnoreCase(WS_TEMP_VARIABLE.WS_TMP_PRDT_TYP)) {
                WS_TEMP_VARIABLE.WS_TMP_BEG_NO = WS_P_AREA_9297.WS_P_DATA_9297[WS_TEMP_VARIABLE.WS_I-1].BPOT9297_FILLER34.WS_BEG_NO;
                WS_TEMP_VARIABLE.WS_TMP_END_NO = WS_P_AREA_9297.WS_P_DATA_9297[WS_TEMP_VARIABLE.WS_I-1].BPOT9297_FILLER34.WS_END_NO;
                WS_TEMP_VARIABLE.WS_TMP_NUM = WS_P_AREA_9297.WS_P_DATA_9297[WS_TEMP_VARIABLE.WS_I-1].BPOT9297_FILLER34.WS_HOW_MANY;
                WS_COND_FLG.WS_END_FLG = 'Y';
            }
        }
        if (WS_COND_FLG.WS_END_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_TYP_NOT_DEF, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TMP_BEG_NO);
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TMP_END_NO);
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TMP_NUM);
        WS_TEMP_VARIABLE.WS_TMP_SUFF = WS_TEMP_VARIABLE.WS_TMP_BEG_NO;
        for (WS_TEMP_VARIABLE.WS_J = 1; WS_TEMP_VARIABLE.WS_J <= WS_TEMP_VARIABLE.WS_TMP_NUM 
            && SCCMPAG.FUNC != 'E'; WS_TEMP_VARIABLE.WS_J += 1) {
            if (WS_TEMP_VARIABLE.WS_VRF_IN == null) WS_TEMP_VARIABLE.WS_VRF_IN = "";
            JIBS_tmp_int = WS_TEMP_VARIABLE.WS_VRF_IN.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_VRF_IN += " ";
            if (WS_TEMP_VARIABLE.WS_TMP_CINO == null) WS_TEMP_VARIABLE.WS_TMP_CINO = "";
            JIBS_tmp_int = WS_TEMP_VARIABLE.WS_TMP_CINO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_TMP_CINO += " ";
            WS_TEMP_VARIABLE.WS_VRF_IN = WS_TEMP_VARIABLE.WS_TMP_CINO + WS_TEMP_VARIABLE.WS_VRF_IN.substring(8);
            if (WS_TEMP_VARIABLE.WS_VRF_IN == null) WS_TEMP_VARIABLE.WS_VRF_IN = "";
            JIBS_tmp_int = WS_TEMP_VARIABLE.WS_VRF_IN.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_VRF_IN += " ";
            JIBS_tmp_str[0] = "" + WS_TEMP_VARIABLE.WS_TMP_SUFF;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<3-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            WS_TEMP_VARIABLE.WS_VRF_IN = WS_TEMP_VARIABLE.WS_VRF_IN.substring(0, 9 - 1) + JIBS_tmp_str[0] + WS_TEMP_VARIABLE.WS_VRF_IN.substring(9 + 3 - 1);
            B200_20_GET_VERIFY_DIG();
            if (pgmRtn) return;
            if (WS_COND_FLG.WS_VRF_SUC_FLG == 'Y') {
                if (WS_TEMP_VARIABLE.WS_ACNO == null) WS_TEMP_VARIABLE.WS_ACNO = "";
                JIBS_tmp_int = WS_TEMP_VARIABLE.WS_ACNO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_ACNO += " ";
                if (WS_TEMP_VARIABLE.WS_TMP_CINO == null) WS_TEMP_VARIABLE.WS_TMP_CINO = "";
                JIBS_tmp_int = WS_TEMP_VARIABLE.WS_TMP_CINO.length();
                for (int i=0;i<12-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_TMP_CINO += " ";
                WS_TEMP_VARIABLE.WS_ACNO = WS_TEMP_VARIABLE.WS_TMP_CINO + WS_TEMP_VARIABLE.WS_ACNO.substring(8);
                if (WS_TEMP_VARIABLE.WS_ACNO == null) WS_TEMP_VARIABLE.WS_ACNO = "";
                JIBS_tmp_int = WS_TEMP_VARIABLE.WS_ACNO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_ACNO += " ";
                JIBS_tmp_str[0] = "" + SCCKDGAC.DIG;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_TEMP_VARIABLE.WS_ACNO = WS_TEMP_VARIABLE.WS_ACNO.substring(0, 9 - 1) + JIBS_tmp_str[0] + WS_TEMP_VARIABLE.WS_ACNO.substring(9 + 1 - 1);
                if (WS_TEMP_VARIABLE.WS_ACNO == null) WS_TEMP_VARIABLE.WS_ACNO = "";
                JIBS_tmp_int = WS_TEMP_VARIABLE.WS_ACNO.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_TEMP_VARIABLE.WS_ACNO += " ";
                JIBS_tmp_str[0] = "" + WS_TEMP_VARIABLE.WS_TMP_SUFF;
                JIBS_tmp_int = JIBS_tmp_str[0].length();
                for (int i=0;i<3-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                WS_TEMP_VARIABLE.WS_ACNO = WS_TEMP_VARIABLE.WS_ACNO.substring(0, 10 - 1) + JIBS_tmp_str[0] + WS_TEMP_VARIABLE.WS_ACNO.substring(10 + 3 - 1);
                CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_ACNO);
                B200_30_CHECK_AC_USED_PROC();
                if (pgmRtn) return;
                B200_40_CHECK_AC_RSVD_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_COND_FLG.WS_AC_USED_FLG);
                CEP.TRC(SCCGWA, WS_COND_FLG.WS_AC_RSVD_FLG);
                if (WS_COND_FLG.WS_AC_USED_FLG == 'N' 
                    && WS_COND_FLG.WS_AC_RSVD_FLG == 'N') {
                    B300_20_OUTPUT_AC_PROC();
                    if (pgmRtn) return;
                }
            } else {
            }
            WS_TEMP_VARIABLE.WS_TMP_SUFF += 1;
        }
    }
    public void B200_10_GET_P_TABLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = "9999297" + BPRPRMT.KEY.CD.substring(7);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        BPRPRMT.KEY.TYP = "P";
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
