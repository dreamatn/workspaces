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

public class BPZSBVMI {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP152";
    String K_HIS_REMARKS = "TLR-BV-MOV-IN";
    String CPN_U_BV_IN = "BP-U-TLR-BV-IN      ";
    String CPN_F_TLR_PEND_MGM = "BP-F-TLR-PEND-MGM   ";
    String CPN_R_MGM_BMOV = "BP-R-MGM-BMOV       ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_F_F_TX_INFO = "BP-F-F-TX-INFO      ";
    String CPN_P_QUERY_BANK = "BP-P-QUERY-BANK     ";
    String CPN_S_BVAPP_MAINTAIN = "BP-S-BVAPP-MAINTAIN";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_A = 0;
    int WS_B = 0;
    int WS_C = 0;
    int WS_D = 0;
    int WS_BV_CNT = 0;
    String WS_FEE_CCY = " ";
    BPZSBVMI_WS_TYPE_INFO[] WS_TYPE_INFO = new BPZSBVMI_WS_TYPE_INFO[10];
    BPZSBVMI_WS_CODE_INFO[] WS_CODE_INFO = new BPZSBVMI_WS_CODE_INFO[10];
    char WS_ERROR_INFO = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBMOV BPRBMOV = new BPRBMOV();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCO152 BPCO152 = new BPCO152();
    BPCUBVIN BPCUBVIN = new BPCUBVIN();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    BPCRBMOV BPCRBMOV = new BPCRBMOV();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCOAPLL BPCOAPLL = new BPCOAPLL();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBVMI BPCSBVMI;
    public BPZSBVMI() {
        for (int i=0;i<10;i++) WS_TYPE_INFO[i] = new BPZSBVMI_WS_TYPE_INFO();
        for (int i=0;i<10;i++) WS_CODE_INFO[i] = new BPZSBVMI_WS_CODE_INFO();
    }
    public void MP(SCCGWA SCCGWA, BPCSBVMI BPCSBVMI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVMI = BPCSBVMI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVMI return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHK_INPUT_CH();
            if (pgmRtn) return;
            B020_TLR_BV_IN_CH();
            if (pgmRtn) return;
            B040_MOD_BMOV_REC();
            if (pgmRtn) return;
            B050_REC_NHIS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCSBVMI.APP_NO);
            if (BPCSBVMI.APP_NO != 0 
                && BPCSBVMI.APP_NO != ' ') {
                B040_UPDATE_BV_APP_INF_PROC();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (BPCSBVMI.CNT1 < 3) {
                    B060_DATA_OUTPUT_CN();
                    if (pgmRtn) return;
                }
            }
        } else {
            B010_CHK_INPUT();
            if (pgmRtn) return;
            B020_TLR_BV_IN();
            if (pgmRtn) return;
            B040_MOD_BMOV_REC();
            if (pgmRtn) return;
            B050_REC_NHIS();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B060_DATA_OUTPUT();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBMOV);
        IBS.init(SCCGWA, BPRBMOV);
        BPRBMOV.KEY.MOV_DT = BPCSBVMI.MOV_DT;
        BPRBMOV.KEY.CONF_NO = BPCSBVMI.CONF_NO;
        BPCRBMOV.INFO.FUNC = 'Q';
        S000_CALL_BPZRBMOV();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRBMOV.KEY.CONF_NO);
        CEP.TRC(SCCGWA, BPRBMOV.MOV_STS);
        if (BPCRBMOV.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRBMOV.MOV_STS == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_CANCEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRBMOV.MOV_STS == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_CONFIRM;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSBVMI.OUT_BR != BPRBMOV.OUT_BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_OURBR_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!BPCSBVMI.OUT_TLR.equalsIgnoreCase(BPRBMOV.OUT_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_OURTLR_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPRBMOV.IN_BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_INBR_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPRBMOV.IN_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_INTLR_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].BV_CODE);
        CEP.TRC(SCCGWA, BPRBMOV.CODE1);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].HEAD_NO);
        CEP.TRC(SCCGWA, BPRBMOV.HEAD_NO1);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].BEG_NO);
        CEP.TRC(SCCGWA, BPRBMOV.BEG_NO1);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].END_NO);
        CEP.TRC(SCCGWA, BPRBMOV.END_NO1);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].NUM);
        CEP.TRC(SCCGWA, BPRBMOV.NUM1);
        if (BPCSBVMI.BV_STS != BPRBMOV.BV_STS) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_DETAIL_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSBVMI.BV_DATA[1-1].BV_CODE.trim().length() > 0) {
            if (!BPCSBVMI.BV_DATA[1-1].BV_CODE.equalsIgnoreCase(BPRBMOV.CODE1) 
                || !BPCSBVMI.BV_DATA[1-1].HEAD_NO.equalsIgnoreCase(BPRBMOV.HEAD_NO1) 
                || !BPCSBVMI.BV_DATA[1-1].BEG_NO.equalsIgnoreCase(BPRBMOV.BEG_NO1) 
                || !BPCSBVMI.BV_DATA[1-1].END_NO.equalsIgnoreCase(BPRBMOV.END_NO1) 
                || BPCSBVMI.BV_DATA[1-1].NUM != BPRBMOV.NUM1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_DETAIL_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[2-1].BV_CODE);
        if (BPCSBVMI.BV_DATA[2-1].BV_CODE.trim().length() > 0) {
            if (!BPCSBVMI.BV_DATA[2-1].BV_CODE.equalsIgnoreCase(BPRBMOV.CODE2) 
                || !BPCSBVMI.BV_DATA[2-1].HEAD_NO.equalsIgnoreCase(BPRBMOV.HEAD_NO2) 
                || !BPCSBVMI.BV_DATA[2-1].BEG_NO.equalsIgnoreCase(BPRBMOV.BEG_NO2) 
                || !BPCSBVMI.BV_DATA[2-1].END_NO.equalsIgnoreCase(BPRBMOV.END_NO2) 
                || BPCSBVMI.BV_DATA[2-1].NUM != BPRBMOV.NUM2) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_DETAIL_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSBVMI.BV_DATA[3-1].BV_CODE.trim().length() > 0) {
            if (!BPCSBVMI.BV_DATA[3-1].BV_CODE.equalsIgnoreCase(BPRBMOV.CODE3) 
                || !BPCSBVMI.BV_DATA[3-1].HEAD_NO.equalsIgnoreCase(BPRBMOV.HEAD_NO3) 
                || !BPCSBVMI.BV_DATA[3-1].BEG_NO.equalsIgnoreCase(BPRBMOV.BEG_NO3) 
                || !BPCSBVMI.BV_DATA[3-1].END_NO.equalsIgnoreCase(BPRBMOV.END_NO3) 
                || BPCSBVMI.BV_DATA[3-1].NUM != BPRBMOV.NUM3) {
                CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[3-1].BV_CODE);
                CEP.TRC(SCCGWA, BPRBMOV.CODE3);
                CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[3-1].BEG_NO);
                CEP.TRC(SCCGWA, BPRBMOV.BEG_NO3);
                CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[3-1].END_NO);
                CEP.TRC(SCCGWA, BPRBMOV.END_NO3);
                CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[3-1].NUM);
                CEP.TRC(SCCGWA, BPRBMOV.NUM3);
                CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[3-1].HEAD_NO);
                CEP.TRC(SCCGWA, BPRBMOV.HEAD_NO3);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_DETAIL_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSBVMI.BV_DATA[4-1].BV_CODE.trim().length() > 0) {
            if (!BPCSBVMI.BV_DATA[4-1].BV_CODE.equalsIgnoreCase(BPRBMOV.CODE4) 
                || !BPCSBVMI.BV_DATA[4-1].HEAD_NO.equalsIgnoreCase(BPRBMOV.HEAD_NO4) 
                || !BPCSBVMI.BV_DATA[4-1].BEG_NO.equalsIgnoreCase(BPRBMOV.BEG_NO4) 
                || !BPCSBVMI.BV_DATA[4-1].END_NO.equalsIgnoreCase(BPRBMOV.END_NO4) 
                || BPCSBVMI.BV_DATA[4-1].NUM != BPRBMOV.NUM4) {
                CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[4-1].BV_CODE);
                CEP.TRC(SCCGWA, BPRBMOV.CODE4);
                CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[4-1].BEG_NO);
                CEP.TRC(SCCGWA, BPRBMOV.BEG_NO4);
                CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[4-1].END_NO);
                CEP.TRC(SCCGWA, BPRBMOV.END_NO4);
                CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[4-1].NUM);
                CEP.TRC(SCCGWA, BPRBMOV.NUM4);
                CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[4-1].HEAD_NO);
                CEP.TRC(SCCGWA, BPRBMOV.HEAD_NO4);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_DETAIL_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHK_INPUT_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBMOV);
        IBS.init(SCCGWA, BPRBMOV);
        BPRBMOV.KEY.MOV_DT = BPCSBVMI.MOV_DT;
        CEP.TRC(SCCGWA, BPRBMOV.KEY.MOV_DT);
        BPRBMOV.KEY.CONF_NO = BPCSBVMI.CONF_NO;
        BPCRBMOV.INFO.FUNC = 'Q';
        S000_CALL_BPZRBMOV();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRBMOV.KEY.CONF_NO);
        CEP.TRC(SCCGWA, BPRBMOV.MOV_STS);
        if (BPCRBMOV.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRBMOV.MOV_STS == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_CANCEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRBMOV.MOV_STS == '2') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_CONFIRM;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSBVMI.OUT_BR != BPRBMOV.OUT_BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_OURBR_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!BPCSBVMI.OUT_TLR.equalsIgnoreCase(BPRBMOV.OUT_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_OURTLR_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPRBMOV.IN_BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_INBR_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPRBMOV.IN_TLR) 
            && BPCSBVMI.BR_FLG == 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_DETAIL_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].BV_CODE);
        CEP.TRC(SCCGWA, BPRBMOV.CODE1);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].HEAD_NO);
        CEP.TRC(SCCGWA, BPRBMOV.HEAD_NO1);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].BEG_NO);
        CEP.TRC(SCCGWA, BPRBMOV.BEG_NO1);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].END_NO);
        CEP.TRC(SCCGWA, BPRBMOV.END_NO1);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].NUM);
        CEP.TRC(SCCGWA, BPRBMOV.NUM1);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_STS);
        if (BPCSBVMI.BV_STS != BPRBMOV.BV_STS) {
            CEP.TRC(SCCGWA, "111");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_DETAIL_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CODE_INFO[1-1].WS_CODE = BPRBMOV.CODE1;
        WS_CODE_INFO[1-1].WS_HEAD_NO = BPRBMOV.HEAD_NO1;
        WS_CODE_INFO[1-1].WS_BEG_NO = BPRBMOV.BEG_NO1;
        WS_CODE_INFO[1-1].WS_END_NO = BPRBMOV.END_NO1;
        WS_CODE_INFO[1-1].WS_NUM = BPRBMOV.NUM1;
        WS_CODE_INFO[2-1].WS_CODE = BPRBMOV.CODE2;
        WS_CODE_INFO[2-1].WS_HEAD_NO = BPRBMOV.HEAD_NO2;
        WS_CODE_INFO[2-1].WS_BEG_NO = BPRBMOV.BEG_NO2;
        WS_CODE_INFO[2-1].WS_END_NO = BPRBMOV.END_NO2;
        WS_CODE_INFO[2-1].WS_NUM = BPRBMOV.NUM2;
        WS_CODE_INFO[3-1].WS_CODE = BPRBMOV.CODE3;
        WS_CODE_INFO[3-1].WS_HEAD_NO = BPRBMOV.HEAD_NO3;
        WS_CODE_INFO[3-1].WS_BEG_NO = BPRBMOV.BEG_NO3;
        WS_CODE_INFO[3-1].WS_END_NO = BPRBMOV.END_NO3;
        WS_CODE_INFO[3-1].WS_NUM = BPRBMOV.NUM3;
        WS_CODE_INFO[4-1].WS_CODE = BPRBMOV.CODE4;
        WS_CODE_INFO[4-1].WS_HEAD_NO = BPRBMOV.HEAD_NO4;
        WS_CODE_INFO[4-1].WS_BEG_NO = BPRBMOV.BEG_NO4;
        WS_CODE_INFO[4-1].WS_END_NO = BPRBMOV.END_NO4;
        WS_CODE_INFO[4-1].WS_NUM = BPRBMOV.NUM4;
        WS_CODE_INFO[5-1].WS_CODE = BPRBMOV.CODE5;
        WS_CODE_INFO[5-1].WS_HEAD_NO = BPRBMOV.HEAD_NO5;
        WS_CODE_INFO[5-1].WS_BEG_NO = BPRBMOV.BEG_NO5;
        WS_CODE_INFO[5-1].WS_END_NO = BPRBMOV.END_NO5;
        WS_CODE_INFO[5-1].WS_NUM = BPRBMOV.NUM5;
        WS_CODE_INFO[6-1].WS_CODE = BPRBMOV.CODE6;
        WS_CODE_INFO[6-1].WS_HEAD_NO = BPRBMOV.HEAD_NO6;
        WS_CODE_INFO[6-1].WS_BEG_NO = BPRBMOV.BEG_NO6;
        WS_CODE_INFO[6-1].WS_END_NO = BPRBMOV.END_NO6;
        WS_CODE_INFO[6-1].WS_NUM = BPRBMOV.NUM6;
        WS_CODE_INFO[7-1].WS_CODE = BPRBMOV.CODE7;
        WS_CODE_INFO[7-1].WS_HEAD_NO = BPRBMOV.HEAD_NO7;
        WS_CODE_INFO[7-1].WS_BEG_NO = BPRBMOV.BEG_NO7;
        WS_CODE_INFO[7-1].WS_END_NO = BPRBMOV.END_NO7;
        WS_CODE_INFO[7-1].WS_NUM = BPRBMOV.NUM7;
        WS_CODE_INFO[8-1].WS_CODE = BPRBMOV.CODE8;
        WS_CODE_INFO[8-1].WS_HEAD_NO = BPRBMOV.HEAD_NO8;
        WS_CODE_INFO[8-1].WS_BEG_NO = BPRBMOV.BEG_NO8;
        WS_CODE_INFO[8-1].WS_END_NO = BPRBMOV.END_NO8;
        WS_CODE_INFO[8-1].WS_NUM = BPRBMOV.NUM8;
        WS_CODE_INFO[9-1].WS_CODE = BPRBMOV.CODE9;
        WS_CODE_INFO[9-1].WS_HEAD_NO = BPRBMOV.HEAD_NO9;
        WS_CODE_INFO[9-1].WS_BEG_NO = BPRBMOV.BEG_NO9;
        WS_CODE_INFO[9-1].WS_END_NO = BPRBMOV.END_NO9;
        WS_CODE_INFO[9-1].WS_NUM = BPRBMOV.NUM9;
        WS_CODE_INFO[10-1].WS_CODE = BPRBMOV.CODE10;
        WS_CODE_INFO[10-1].WS_HEAD_NO = BPRBMOV.HEAD_NO10;
        WS_CODE_INFO[10-1].WS_BEG_NO = BPRBMOV.BEG_NO10;
        WS_CODE_INFO[10-1].WS_END_NO = BPRBMOV.END_NO10;
        WS_CODE_INFO[10-1].WS_NUM = BPRBMOV.NUM10;
        for (WS_A = 1; WS_A <= BPCSBVMI.CNT 
            && BPCSBVMI.BV_DATA[WS_A-1].BV_CODE.trim().length() != 0; WS_A += 1) {
        }
        for (WS_B = 1; WS_B <= BPCSBVMI.CNT 
            && WS_CODE_INFO[WS_B-1].WS_CODE.trim().length() != 0; WS_B += 1) {
        }
        CEP.TRC(SCCGWA, WS_A);
        CEP.TRC(SCCGWA, WS_B);
        if (WS_A != WS_B) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_DETAIL_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_C = 1; WS_C <= BPCSBVMI.CNT 
            && BPCSBVMI.BV_DATA[WS_C-1].BV_CODE.trim().length() != 0; WS_C += 1) {
            WS_ERROR_INFO = 'F';
            for (WS_D = 1; WS_D <= BPCSBVMI.CNT 
                && WS_CODE_INFO[WS_D-1].WS_CODE.trim().length() != 0; WS_D += 1) {
                if (BPCSBVMI.BV_DATA[WS_C-1].BV_CODE.equalsIgnoreCase(WS_CODE_INFO[WS_D-1].WS_CODE) 
                    && BPCSBVMI.BV_DATA[WS_C-1].HEAD_NO.equalsIgnoreCase(WS_CODE_INFO[WS_D-1].WS_HEAD_NO) 
                    && BPCSBVMI.BV_DATA[WS_C-1].BEG_NO.equalsIgnoreCase(WS_CODE_INFO[WS_D-1].WS_BEG_NO) 
                    && BPCSBVMI.BV_DATA[WS_C-1].END_NO.equalsIgnoreCase(WS_CODE_INFO[WS_D-1].WS_END_NO) 
                    && BPCSBVMI.BV_DATA[WS_C-1].NUM == WS_CODE_INFO[WS_D-1].WS_NUM) {
                    WS_ERROR_INFO = 'T';
                }
            }
            if (WS_ERROR_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_DETAIL_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPRBMOV.BEG_NO1);
        CEP.TRC(SCCGWA, BPRBMOV.BEG_NO2);
        CEP.TRC(SCCGWA, BPRBMOV.BEG_NO3);
        CEP.TRC(SCCGWA, BPRBMOV.BEG_NO4);
        for (WS_I = 1; WS_I <= BPCSBVMI.CNT 
            && BPCSBVMI.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVMI.BV_DATA[WS_I-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            WS_TYPE_INFO[WS_I-1].WS_BV_TYPE = BPCFBVQU.TX_DATA.TYPE;
        }
    }
    public void B020_TLR_BV_IN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVMI.CNT);
        for (WS_I = 1; WS_I <= BPCSBVMI.CNT 
            && BPCSBVMI.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCUBVIN);
            BPCUBVIN.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUBVIN.TYPE = '0';
            BPCUBVIN.BV_CODE = BPCSBVMI.BV_DATA[WS_I-1].BV_CODE;
            BPCUBVIN.HEAD_NO = BPCSBVMI.BV_DATA[WS_I-1].HEAD_NO;
            BPCUBVIN.BEG_NO = BPCSBVMI.BV_DATA[WS_I-1].BEG_NO;
            BPCUBVIN.END_NO = BPCSBVMI.BV_DATA[WS_I-1].END_NO;
            BPCUBVIN.NUM = BPCSBVMI.BV_DATA[WS_I-1].NUM;
            BPCUBVIN.BV_STS = BPCSBVMI.BV_STS;
            BPCUBVIN.VB_FLG = '1';
            BPCUBVIN.AC_TYP = '0';
            S000_CALL_BPZUBVIN();
            if (pgmRtn) return;
        }
        WS_BV_CNT = WS_I;
    }
    public void B020_TLR_BV_IN_CH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVMI.CNT);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[1-1].BV_CODE);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[2-1].BV_CODE);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[3-1].BV_CODE);
        CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[4-1].BV_CODE);
        CEP.TRC(SCCGWA, BPCUBVIN.TYPE);
        CEP.TRC(SCCGWA, BPCSBVMI.PB_FLG);
        for (WS_I = 1; WS_I <= BPCSBVMI.CNT 
            && BPCSBVMI.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, BPCSBVMI.BV_DATA[WS_I-1].BV_CODE);
            IBS.init(SCCGWA, BPCUBVIN);
            BPCUBVIN.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUBVIN.TYPE = WS_TYPE_INFO[WS_I-1].WS_BV_TYPE;
            BPCUBVIN.BV_CODE = BPCSBVMI.BV_DATA[WS_I-1].BV_CODE;
            BPCUBVIN.HEAD_NO = BPCSBVMI.BV_DATA[WS_I-1].HEAD_NO;
            BPCUBVIN.BEG_NO = BPCSBVMI.BV_DATA[WS_I-1].BEG_NO;
            BPCUBVIN.END_NO = BPCSBVMI.BV_DATA[WS_I-1].END_NO;
            BPCUBVIN.NUM = BPCSBVMI.BV_DATA[WS_I-1].NUM;
            BPCUBVIN.BV_STS = BPCSBVMI.BV_STS;
            CEP.TRC(SCCGWA, BPCUBVIN.TYPE);
            if (BPCUBVIN.TYPE == '3') {
                BPCUBVIN.VB_FLG = BPCSBVMI.PB_FLG;
                CEP.TRC(SCCGWA, BPCUBVIN.VB_FLG);
            } else {
                BPCUBVIN.VB_FLG = '1';
            }
            CEP.TRC(SCCGWA, BPCUBVIN.VB_FLG);
            CEP.TRC(SCCGWA, BPCSBVMI.BR_FLG);
            if (BPCUBVIN.TYPE != '3' 
                && BPCSBVMI.BR_FLG == 'Y') {
                BPCUBVIN.VB_FLG = BPCSBVMI.IN_TYP;
            }
            CEP.TRC(SCCGWA, BPCUBVIN.VB_FLG);
            BPCUBVIN.AC_TYP = '0';
            if (BPCSBVMI.BRFLG == 'N') {
                IBS.init(SCCGWA, BPCPQORG);
                CEP.TRC(SCCGWA, BPCSBVMI.OUT_BR);
                BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPQORG.TYP);
                if (!BPCPQORG.TYP.equalsIgnoreCase("01") 
                    && !BPCPQORG.TYP.equalsIgnoreCase("02") 
                    && !BPCPQORG.TYP.equalsIgnoreCase("03")) {
                    CEP.TRC(SCCGWA, "TEST001");
                    CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
                } else {
                    IBS.init(SCCGWA, BPCPQORG);
                    BPCPQORG.BR = BPCSBVMI.OUT_BR;
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "TEST002");
                    CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
                    BPCUBVIN.VIL_TYP = BPCPQORG.VIL_TYP;
                }
            } else {
            }
            S000_CALL_BPZUBVIN();
            if (pgmRtn) return;
        }
        WS_BV_CNT = BPCSBVMI.CNT;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVMI.BV_DATA[WS_I-1].BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        if (BPCFBVQU.TX_DATA.TYPE == '0' 
            && BPCFBVQU.TX_DATA.CTL_FLG != '0') {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B021_ADD_BUSE_PROCESS();
                if (pgmRtn) return;
            } else {
                B022_ADD_BUSE_PROCESS_CANCEL();
                if (pgmRtn) return;
            }
        }
    }
    public void B021_ADD_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVMI.BV_DATA[WS_I-1].BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVMI.BV_DATA[WS_I-1].HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVMI.BV_DATA[WS_I-1].BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVMI.BV_DATA[WS_I-1].END_NO;
        BPRBUSE.KEY.TX_DT = BPCSBVMI.MOV_DT;
        BPRBUSE.KEY.TX_JRN = BPCSBVMI.CONF_NO;
        BPRBUSE.TYPE = '0';
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '0';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B022_ADD_BUSE_PROCESS_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVMI.BV_DATA[WS_I-1].BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVMI.BV_DATA[WS_I-1].HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVMI.BV_DATA[WS_I-1].BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVMI.BV_DATA[WS_I-1].END_NO;
        BPRBUSE.KEY.TX_DT = BPCSBVMI.MOV_DT;
        BPRBUSE.KEY.TX_JRN = BPCSBVMI.CONF_NO;
        BPCRBUSE.INFO.FUNC = 'R';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.REC_STS = '1';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B030_TLR_PEND_MGM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLPM);
        BPCFTLPM.OP_CODE = 'D';
        BPCFTLPM.BUSS_TYP = "04";
        BPCFTLPM.TLR = SCCGWA.COMM_AREA.TL_ID;
        if (BPCFTLPM.OP_CODE == 'D') {
            CEP.TRC(SCCGWA, "AAAAAAAAAAAA");
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        S000_CALL_BPZFTLPM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFTLPM);
        BPCFTLPM.OP_CODE = 'D';
        BPCFTLPM.BUSS_TYP = "02";
        BPCFTLPM.TLR = BPCSBVMI.OUT_TLR;
        CEP.TRC(SCCGWA, BPCSBVMI.OUT_TLR);
        S000_CALL_BPZFTLPM();
        if (pgmRtn) return;
    }
    public void B040_MOD_BMOV_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBMOV);
        IBS.init(SCCGWA, BPRBMOV);
        BPRBMOV.KEY.CONF_NO = BPCSBVMI.CONF_NO;
        BPRBMOV.KEY.MOV_DT = BPCSBVMI.MOV_DT;
        BPCRBMOV.INFO.FUNC = 'R';
        S000_CALL_BPZRBMOV();
        if (pgmRtn) return;
        if (BPCRBMOV.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRBMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBMOV.MOV_STS = '2';
        BPCRBMOV.INFO.FUNC = 'U';
        S000_CALL_BPZRBMOV();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_BV_APP_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPLL);
        BPCOAPLL.FUNC = 'M';
        BPCOAPLL.MODIFY_STS = 'I';
        BPCOAPLL.APP_NO = BPCSBVMI.APP_NO;
        S000_CALL_BPZSAPLL();
        if (pgmRtn) return;
        BPCSBVMI.OUT_BR = 0;
        BPCSBVMI.OUT_TLR = " ";
        BPCSBVMI.MOV_DT = 0;
        BPCSBVMI.CONF_NO = 0;
        BPCSBVMI.APP_TYPE = ' ';
        BPCSBVMI.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSBVMI.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSBVMI.MOV_DT = BPCOAPLL.MOV_DT;
        BPCSBVMI.CONF_NO = BPCOAPLL.CONF_SEQ;
        BPCSBVMI.APP_TYPE = BPCOAPLL.APP_TYPE;
    }
    public void S000_CALL_BPZSAPLL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_BVAPP_MAINTAIN, BPCOAPLL);
    }
    public void B050_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R010_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO152;
