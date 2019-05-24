package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUBVOU {
    int JIBS_tmp_int;
    int BUSE_DRW_NM_LEN;
    String JIBS_tmp_str[] = new String[10];
    char K_STS_NORMAL = '0';
    char K_STS_PAYOUT = '3';
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String CPN_R_MGM_TBV = "BP-R-MGM-TBV";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD";
    String CPN_R_MGM_TBVD = "BP-R-MGM-TBVD";
    String CPN_F_INQ_BV_ACT = "BP-F-INQ-BV-ACT";
    String CPN_P_VCH_CPNT = "BP-P-VWA-WRITE";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_CKG_LVBF = "BP-R-ADW-TLVB";
    String CPN_P_TLAM = "BP-F-TLR-ACCU-MGM";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_F_BVLT_CHK = "BP-F-BVLT-CHK       ";
    String CPN_S_NUM_CHK = "BP-S-BV-NO-CHK";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String BP_P_INQ_ORG_REL = "BP-P-INQ-ORG-REL";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_BVNO_LEN = 0;
    String WS_BEG_NO = " ";
    long WS_COMP_BEGNO = 0;
    String WS_END_NO = " ";
    long WS_COMP_ENDNO = 0;
    int WS_TOTAL_NUM = 0;
    String WS_STORAGE = " ";
    String WS_TEMP_PLBOX_NO = " ";
    int WS_CNT = 0;
    int WS_POS = 0;
    int WS_TR_BRANCH = 0;
    BPZUBVOU_WS_EWA_AC_NO WS_EWA_AC_NO = new BPZUBVOU_WS_EWA_AC_NO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPCRTBVD BPCRTBVD = new BPCRTBVD();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPRTBVD BPRTEMP = new BPRTBVD();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPCIFHIS BPCIFHIS = new BPCIFHIS();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCCBVIO BPCCBVIO = new BPCCBVIO();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPEBAS BPCPEBAS = new BPCPEBAS();
    BPCFLTCK BPCFLTCK = new BPCFLTCK();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQORR BPCPQORR = new BPCPQORR();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCUBVOU BPCUBVOU;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRTLT BPRTLT;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, BPCUBVOU BPCUBVOU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUBVOU = BPCUBVOU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZUBVOU return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        BPRTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHK_INPUT_CH();
            B020_CHK_BV_PARM();
            B030_CHK_BV_TLR();
            B050_PROCESS_MOV_OUT_CH();
            B060_BUSE_HISTORY_PROC();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B070_CHALK_IT_UP_CH();
            }
            B100_UPDATE_BPTTVHPB();
        } else {
            B010_CHK_INPUT();
            B020_CHK_BV_PARM();
            B030_CHK_BV_TLR();
            B050_PROCESS_MOV_OUT();
            B060_BUSE_HISTORY_PROC();
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B070_CHALK_IT_UP();
            }
            B100_UPDATE_BPTTVHPB();
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        if (BPCUBVOU.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBVOU.NUM == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBVOU.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBVOU.VB_FLG == ' ') {
            CEP.TRC(SCCGWA, BPCUBVOU.VB_FLG);
            BPCUBVOU.VB_FLG = '0';
        } else {
            CEP.TRC(SCCGWA, BPCUBVOU.VB_FLG);
            if ((BPCUBVOU.VB_FLG != '0' 
                && BPCUBVOU.VB_FLG != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBFLG_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCUBVOU.BV_STS == ' ') {
            BPCUBVOU.BV_STS = '0';
        } else {
            if ((BPCUBVOU.BV_STS != '0' 
                && BPCUBVOU.BV_STS != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVSTS_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCUBVOU.AC_TYP == ' ') {
            BPCUBVOU.AC_TYP = '0';
        } else {
            if ((BPCUBVOU.AC_TYP != '0' 
                && BPCUBVOU.AC_TYP != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACTYP_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if ((BPCUBVOU.TYPE != '0' 
            && BPCUBVOU.TYPE != '1' 
            && BPCUBVOU.TYPE != '2' 
            && BPCUBVOU.TYPE != '3' 
            && BPCUBVOU.TYPE != '4')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVTYPE_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCIFHIS);
            IBS.init(SCCGWA, BPRFHIST);
            BPCIFHIS.INPUT.FUNC = '5';
            BPRFHIST.KEY.AC_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPRFHIST.KEY.JRNNO = GWA_BP_AREA.CANCEL_AREA.CAN_JRN_NO;
            BPRFHIST.KEY.JRN_SEQ = 1;
            BPCIFHIS.INPUT.REC_PT = BPRFHIST;
            BPCIFHIS.INPUT.REC_LEN = 690;
            S000_CALL_BPZIFHIS();
            CEP.TRC(SCCGWA, BPRFHIST.TX_BR);
            CEP.TRC(SCCGWA, BPRFHIST.TX_TLR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            if (BPRFHIST.TX_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
                S000_ERR_MSG_PROC();
            }
            if (!BPRFHIST.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_CHK_INPUT_CH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TST00002");
        CEP.TRC(SCCGWA, BPCUBVOU.TLR);
        CEP.TRC(SCCGWA, BPCUBVOU.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBVOU.HEAD_NO);
        CEP.TRC(SCCGWA, BPCUBVOU.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBVOU.END_NO);
        CEP.TRC(SCCGWA, BPCUBVOU.NUM);
        if (BPCUBVOU.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBVOU.NUM == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBVOU.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCUBVOU.VB_FLG == ' ') {
            CEP.TRC(SCCGWA, BPCUBVOU.VB_FLG);
            BPCUBVOU.VB_FLG = '0';
        } else {
            CEP.TRC(SCCGWA, BPCUBVOU.VB_FLG);
            if ((BPCUBVOU.VB_FLG != '0' 
                && BPCUBVOU.VB_FLG != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBFLG_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCUBVOU.BV_STS == ' ') {
            BPCUBVOU.BV_STS = '0';
        } else {
            if ((BPCUBVOU.BV_STS != '0' 
                && BPCUBVOU.BV_STS != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVSTS_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCUBVOU.AC_TYP == ' ') {
            BPCUBVOU.AC_TYP = '0';
        } else {
            if ((BPCUBVOU.AC_TYP != '0' 
                && BPCUBVOU.AC_TYP != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACTYP_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if ((BPCUBVOU.TYPE != '0' 
            && BPCUBVOU.TYPE != '1' 
            && BPCUBVOU.TYPE != '2' 
            && BPCUBVOU.TYPE != '3' 
            && BPCUBVOU.TYPE != '4')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVTYPE_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CHK_BV_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCUBVOU.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y' 
            && (BPCFBVQU.TX_DATA.CTL_FLG != '0')) {
            IBS.init(SCCGWA, BPRBUSE);
            IBS.init(SCCGWA, BPCRBUSE);
            BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRBUSE.KEY.BV_CODE = BPCUBVOU.BV_CODE;
            BPRBUSE.KEY.HEAD_NO = BPCUBVOU.HEAD_NO;
            BPRBUSE.KEY.BEG_NO = BPCUBVOU.BEG_NO;
            BPRBUSE.KEY.END_NO = BPCUBVOU.END_NO;
            BPRBUSE.KEY.TX_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            BPCRBUSE.INFO.FUNC = 'Q';
            S000_CALL_BPZRBUSE();
            CEP.TRC(SCCGWA, BPRBUSE.TX_BR);
            CEP.TRC(SCCGWA, BPRBUSE.TX_TLR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            if (BPRBUSE.TX_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_BR;
                S000_ERR_MSG_PROC();
            }
            if (!BPRBUSE.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ORIGIN_TLR;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, BPCUBVOU.AC_TYP);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.TYPE);
        if (BPCUBVOU.HEAD_NO.trim().length() > 0) {
            if (BPCUBVOU.HEAD_NO == null) BPCUBVOU.HEAD_NO = "";
            JIBS_tmp_int = BPCUBVOU.HEAD_NO.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPCUBVOU.HEAD_NO += " ";
            for (WS_I = 1; WS_I <= 10 
                && BPCUBVOU.HEAD_NO.substring(WS_I - 1, WS_I + 1 - 1).trim().length() != 0; WS_I += 1) {
            }
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
            CEP.TRC(SCCGWA, BPCUBVOU.HEAD_NO);
            if (BPCFBVQU.TX_DATA.HEAD_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_HEADNO_LEN;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, "333");
        }
        CEP.TRC(SCCGWA, "111");
        if (BPCUBVOU.BEG_NO.trim().length() > 0) {
            if (BPCUBVOU.BEG_NO == null) BPCUBVOU.BEG_NO = "";
            JIBS_tmp_int = BPCUBVOU.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBVOU.BEG_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPCUBVOU.BEG_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, "222");
        if (BPCUBVOU.END_NO.trim().length() > 0) {
            if (BPCUBVOU.END_NO == null) BPCUBVOU.END_NO = "";
            JIBS_tmp_int = BPCUBVOU.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBVOU.END_NO += " ";
            for (WS_I = 1; WS_I <= 20 
                && IBS.isNumeric(BPCUBVOU.END_NO.substring(WS_I - 1, WS_I + 1 - 1)); WS_I += 1) {
            }
            if (BPCFBVQU.TX_DATA.NO_LENGTH != WS_I - 1) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVNO_LEN;
                S000_ERR_MSG_PROC();
            }
        }
        WS_BVNO_LEN = BPCFBVQU.TX_DATA.NO_LENGTH;
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            if (BPCUBVOU.BEG_NO.trim().length() > 0 
                || BPCUBVOU.END_NO.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_BEGEND_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFBVQU.TX_DATA.CTL_FLG == '1' 
            || BPCFBVQU.TX_DATA.CTL_FLG == '2') {
            if (BPCUBVOU.BEG_NO == null) BPCUBVOU.BEG_NO = "";
            JIBS_tmp_int = BPCUBVOU.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBVOU.BEG_NO += " ";
            if (BPCUBVOU.BEG_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_BEGNO = 0;
            else WS_COMP_BEGNO = Long.parseLong(BPCUBVOU.BEG_NO.substring(0, WS_BVNO_LEN));
            if (BPCUBVOU.END_NO == null) BPCUBVOU.END_NO = "";
            JIBS_tmp_int = BPCUBVOU.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCUBVOU.END_NO += " ";
            if (BPCUBVOU.END_NO.substring(0, WS_BVNO_LEN).trim().length() == 0) WS_COMP_ENDNO = 0;
            else WS_COMP_ENDNO = Long.parseLong(BPCUBVOU.END_NO.substring(0, WS_BVNO_LEN));
            if (WS_COMP_BEGNO > WS_COMP_ENDNO) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BEG_END;
                S000_ERR_MSG_PROC();
            }
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCUBVOU.BV_CODE;
            BPCSNOCK.BEG_NO = BPCUBVOU.BEG_NO;
            BPCSNOCK.END_NO = BPCUBVOU.END_NO;
            BPCSNOCK.NUM = BPCUBVOU.NUM;
            BPCSNOCK.FUNC = '1';
            CEP.TRC(SCCGWA, "ABCD");
            CEP.TRC(SCCGWA, BPCUBVOU.NUM);
            S000_CALL_BPZSNOCK();
        }
        if (BPCFBVQU.TX_DATA.TYPE == '1') {
            if (BPCUBVOU.CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
                S000_ERR_MSG_PROC();
            } else {
                if (!BPCUBVOU.CCY.equalsIgnoreCase(BPCFBVQU.TX_DATA.CCY)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCUBVOU.PVAL == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VAL_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (BPCUBVOU.AMT == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (BPCUBVOU.AMT != BPCUBVOU.PVAL * BPCUBVOU.NUM) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AMT_VAL_NUM;
                S000_ERR_MSG_PROC();
            }
        } else {
            BPCUBVOU.PVAL = 0;
        }
    }
    public void B030_CHK_BV_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCUBVOU.TLR;
        S000_CALL_BPZFTLRQ();
        if (BPCFBVQU.TX_DATA.TYPE == '0'
            || BPCFBVQU.TX_DATA.TYPE == '2'
            || BPCFBVQU.TX_DATA.TYPE == '3'
            || BPCFBVQU.TX_DATA.TYPE == '4') {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, "NCB032801");
            CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
            CEP.TRC(SCCGWA, BPCPQORG.INT_BR_FLG);
            if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
                && BPCPQORG.INT_BR_FLG == 'Y') {
                IBS.init(SCCGWA, BPCPQORR);
                BPCPQORR.TYP = "12";
                BPCPQORR.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                S000_CALL_BPZPQORR();
                WS_TR_BRANCH = BPCPQORR.REL_BR;
            } else {
                WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            }
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.BR = WS_TR_BRANCH;
            BPRVHPB.CUR_TLR = BPCUBVOU.TLR;
            BPRVHPB.POLL_BOX_IND = BPCUBVOU.VB_FLG;
            BPRVHPB.RELATE_FLG = 'Y';
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            if (BPCRVHPB.RETURN_INFO == 'F') {
                WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
            } else {
                if (BPCUBVOU.VB_FLG == '0') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_HVNT_BVB;
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_HVNT_BVP;
                }
                S000_ERR_MSG_PROC();
            }
        } else if (BPCFBVQU.TX_DATA.TYPE == '1') {
            if (BPCUBVOU.VB_FLG == '0') {
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                if (BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("0") 
                    && BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_VLT_TLR;
                    S000_ERR_MSG_PROC();
                }
                if (BPRTLVB.KEY.PLBOX_NO.equalsIgnoreCase("3")) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_CASH_BOX;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R_CHK_USE_METHOD();
        }
    }
    public void B040_CHK_PROC_O() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.OUT_FLG);
        CEP.TRC(SCCGWA, BPCUBVOU.VB_FLG);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if ((BPCFBVQU.TX_DATA.OUT_FLG == '0' 
                && BPCUBVOU.VB_FLG == '1') 
                || (BPCFBVQU.TX_DATA.OUT_FLG == '1' 
                && BPCUBVOU.VB_FLG == '0')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVOU_MTH;
                S000_ERR_MSG_PROC();
            }
            if (BPCFBVQU.TX_DATA.USE_MODE == '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_MANUAL;
                S000_ERR_MSG_PROC();
            }
            if (BPCFBVQU.TX_DATA.CTL_FLG == '2') {
                R_CHK_BV_LEAST_NO();
            }
        }
    }
    public void B050_PROCESS_MOV_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCBVIO);
        BPCCBVIO.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCCBVIO.TLR = BPCUBVOU.TLR;
        BPCCBVIO.PLBOX_NO = WS_TEMP_PLBOX_NO;
        BPCCBVIO.VB_FLG = BPCUBVOU.VB_FLG;
        BPCCBVIO.BV_CODE = BPCUBVOU.BV_CODE;
        BPCCBVIO.PVAL = BPCUBVOU.PVAL;
        BPCCBVIO.TYPE = BPCUBVOU.TYPE;
        BPCCBVIO.HEAD_NO = BPCUBVOU.HEAD_NO;
        BPCCBVIO.BEG_NO = BPCUBVOU.BEG_NO;
        BPCCBVIO.END_NO = BPCUBVOU.END_NO;
        BPCCBVIO.NUM = BPCUBVOU.NUM;
        BPCCBVIO.BV_STS = BPCUBVOU.BV_STS;
        BPCCBVIO.CTL_FLG = BPCFBVQU.TX_DATA.CTL_FLG;
        BPCCBVIO.BVNO_LEN = WS_BVNO_LEN;
        BPCCBVIO.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R_DELETE_PROC();
        } else {
            R_ADD_PROC();
        }
        R_CHK_BV_NUM();
    }
    public void B050_PROCESS_MOV_OUT_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCBVIO);
        BPCCBVIO.BR = WS_TR_BRANCH;
        BPCCBVIO.TLR = BPCUBVOU.TLR;
        BPCCBVIO.PLBOX_NO = WS_TEMP_PLBOX_NO;
        BPCCBVIO.VB_FLG = BPCUBVOU.VB_FLG;
        BPCCBVIO.BV_CODE = BPCUBVOU.BV_CODE;
        BPCCBVIO.PVAL = BPCUBVOU.PVAL;
        BPCCBVIO.TYPE = BPCUBVOU.TYPE;
        BPCCBVIO.HEAD_NO = BPCUBVOU.HEAD_NO;
        BPCCBVIO.BEG_NO = BPCUBVOU.BEG_NO;
        BPCCBVIO.END_NO = BPCUBVOU.END_NO;
        BPCCBVIO.NUM = BPCUBVOU.NUM;
        BPCCBVIO.BV_STS = BPCUBVOU.BV_STS;
        BPCCBVIO.CTL_FLG = BPCFBVQU.TX_DATA.CTL_FLG;
        BPCCBVIO.BVNO_LEN = WS_BVNO_LEN;
        BPCCBVIO.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCCBVIO.VIL_TYP = BPCUBVOU.VIL_TYP;
        CEP.TRC(SCCGWA, BPCCBVIO.BV_STS);
        CEP.TRC(SCCGWA, BPCUBVOU.VILCTL);
        if (BPCUBVOU.VILCTL == 'Y') {
            BPCCBVIO.VILCTL = 'Y';
        }
        CEP.TRC(SCCGWA, BPCCBVIO.VILCTL);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            R_DELETE_PROC();
        } else {
            R_ADD_PROC();
        }
    }
    public void B060_BUSE_HISTORY_PROC() throws IOException,SQLException,Exception {
        if (BPCUBVOU.TYPE != '1' 
            && (BPCFBVQU.TX_DATA.CTL_FLG != '0')) {
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B060_01_ADD_BUSE_PROCESS();
            } else {
                B060_02_ADD_BUSE_PROC_CANCEL();
            }
        }
    }
    public void B060_01_ADD_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCUBVOU.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCUBVOU.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCUBVOU.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCUBVOU.END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.DRW_NM = BPCUBVOU.DRW_NM;
        BUSE_DRW_NM_LEN = BPRBUSE.DRW_NM.length();
        BPRBUSE.DRW_ID_TYP = BPCUBVOU.DRW_ID_TYP;
        BPRBUSE.DRW_ID_NO = BPCUBVOU.DRW_ID_NO;
        BPRBUSE.TYPE = BPCFBVQU.TX_DATA.TYPE;
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        if (BPCUBVOU.BV_STS == '1') {
            BPRBUSE.STS = '1';
        } else {
            BPRBUSE.STS = '3';
        }
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
    }
    public void B060_02_ADD_BUSE_PROC_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCUBVOU.BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCUBVOU.HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCUBVOU.BEG_NO;
        BPRBUSE.KEY.END_NO = BPCUBVOU.END_NO;
        BPRBUSE.KEY.TX_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        BPCRBUSE.INFO.FUNC = 'R';
        S000_CALL_BPZRBUSE();
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.REC_STS = '1';
        BPCRBUSE.INFO.FUNC = 'U';
        S000_CALL_BPZRBUSE();
    }
    public void B070_CHALK_IT_UP() throws IOException,SQLException,Exception {
        if (BPCUBVOU.AC_TYP == '0' 
            && BPCFBVQU.TX_DATA.AC_TYP == '0') {
            B071_SET_EWA_BASIC_INF();
            B072_SET_EWA_EVENTS();
        }
    }
    public void B070_CHALK_IT_UP_CH() throws IOException,SQLException,Exception {
        if (BPCUBVOU.AC_TYP == '0' 
            && BPCFBVQU.TX_DATA.AC_TYP == '0') {
            B071_SET_EWA_BASIC_INF();
            B072_SET_EWA_EVENTS_CH();
        }
    }
    public void B071_SET_EWA_BASIC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEBAS);
        S000_CALL_BPZPEBAS();
    }
    public void B072_SET_EWA_EVENTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.EVENT_CODE = "BVZUBVOU";
        BPCPOEWA.DATA.BR_OLD = BPCFTLRQ.INFO.TLR_BR;
        BPCPOEWA.DATA.BR_NEW = BPCFTLRQ.INFO.TLR_BR;
        if (BPCFBVQU.TX_DATA.TYPE == '0') {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = (double)BPCUBVOU.NUM;
        } else {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUBVOU.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUBVOU.AMT;
        }
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_EWA_AC_NO.WS_BACT_CODE = BPCUBVOU.BV_CODE;
        WS_EWA_AC_NO.WS_BACT_STAT = BPCUBVOU.VB_FLG;
        WS_EWA_AC_NO.WS_BACT_BV_KIND = BPCUBVOU.BV_STS;
        BPCPOEWA.DATA.PORT = BPCUBVOU.BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = BPCUBVOU.BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void B072_SET_EWA_EVENTS_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        if (BPCUBVOU.BV_STS == '0') {
            BPCPOEWA.DATA.EVENT_CODE = "CR";
        } else {
            BPCPOEWA.DATA.EVENT_CODE = "BVZUCR";
        }
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCFBVQU.TX_DATA.TYPE != '1') {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCRBANK.LOC_CCY1;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = (double)BPCUBVOU.NUM;
        } else {
            BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCUBVOU.CCY;
            BPCPOEWA.DATA.AMT_INFO[1-1].AMT = BPCUBVOU.AMT;
        }
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_EWA_AC_NO.WS_BACT_CODE = BPCUBVOU.BV_CODE;
        WS_EWA_AC_NO.WS_BACT_STAT = BPCUBVOU.VB_FLG;
        WS_EWA_AC_NO.WS_BACT_BV_KIND = BPCUBVOU.BV_STS;
        BPCPOEWA.DATA.PORT = BPCUBVOU.BV_CODE;
        BPCPOEWA.DATA.PROD_CODE = BPCUBVOU.BV_CODE;
        S000_CALL_BPZPOEWA();
    }
    public void B090_UPDATE_TLR_ACCU() throws IOException,SQLException,Exception {
        if (BPCUBVOU.AC_TYP == '0' 
            && BPCFBVQU.TX_DATA.AC_TYP == '0') {
            IBS.init(SCCGWA, BPCFTLAM);
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                BPCFTLAM.OP_CODE = 'D';
            } else {
                BPCFTLAM.OP_CODE = 'A';
            }
            BPCFTLAM.ACCU_TYP = "04";
            BPCFTLAM.TLR = BPCUBVOU.TLR;
            BPCFTLAM.CCY = BPCRBANK.LOC_CCY1;
            if (BPCFBVQU.TX_DATA.TYPE == '0') {
                BPCFTLAM.AMT = (double)BPCUBVOU.NUM;
            } else {
                BPCFTLAM.AMT = BPCUBVOU.AMT;
            }
            S000_CALL_BPZFTLAM();
        }
    }
    public void R_CHK_BV_NUM() throws IOException,SQLException,Exception {
        if (BPCCBVIO.CTL_FLG == '0') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = BPCPORUP.DATA_INFO.BR;
            BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCUBVOU.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCUBVOU.PVAL;
            BPRTBVD.KEY.STS = K_STS_NORMAL;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'R';
            S000_CALL_BPZRTBVD();
            if (BPCRTBVD.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_LIMIT_ERR;
                S000_ERR_MSG_PROC();
            } else {
                if ((BPCUBVOU.VB_FLG == '1' 
                    && BPRTBVD.NUM > BPCFBVQU.TX_DATA.V_LMTUP) 
                    || (BPCUBVOU.VB_FLG == '0' 
                    && BPRTBVD.NUM > BPCFBVQU.TX_DATA.B_LMTUP)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LARGE_UP_LIMIT;
                    S000_ERR_MSG_PROC();
                }
                if ((BPCUBVOU.VB_FLG == '1' 
                    && BPRTBVD.NUM < BPCFBVQU.TX_DATA.V_LMT) 
                    || (BPCUBVOU.VB_FLG == '0' 
                    && BPRTBVD.NUM < BPCFBVQU.TX_DATA.B_LMT)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_LOW_LIMIT;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            IBS.init(SCCGWA, BPRTBVD);
            WS_TOTAL_NUM = 0;
            BPRTBVD.KEY.BR = BPCPORUP.DATA_INFO.BR;
            BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCUBVOU.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCUBVOU.PVAL;
            BPRTBVD.KEY.HEAD_NO = BPCUBVOU.HEAD_NO;
            BPRTBVD.KEY.STS = K_STS_NORMAL;
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '2';
            S000_CALL_BPZRTBDB();
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_LIMIT_ERR;
                S000_ERR_MSG_PROC();
            }
            while (BPCRTBDB.RETURN_INFO != 'N') {
                WS_TOTAL_NUM += BPRTBVD.NUM;
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
            }
            BPCRTBDB.INFO.FUNC = 'E';
            S000_CALL_BPZRTBDB();
            if ((BPCUBVOU.VB_FLG == '1' 
                && WS_TOTAL_NUM > BPCFBVQU.TX_DATA.V_LMTUP) 
                || (BPCUBVOU.VB_FLG == '0' 
                && WS_TOTAL_NUM > BPCFBVQU.TX_DATA.B_LMTUP)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LARGE_UP_LIMIT;
                S000_ERR_MSG_PROC();
            }
            if ((BPCUBVOU.VB_FLG == '1' 
                && WS_TOTAL_NUM < BPCFBVQU.TX_DATA.V_LMT) 
                || (BPCUBVOU.VB_FLG == '0' 
                && WS_TOTAL_NUM < BPCFBVQU.TX_DATA.B_LMT)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LESS_LOW_LIMIT;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R_CHK_BV_NUM_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFLTCK);
        BPCFLTCK.TX_DATA.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCFLTCK.TX_DATA.TL_ID = BPCUBVOU.TLR;
        BPCFLTCK.TX_DATA.BV_CODE = BPCUBVOU.BV_CODE;
        BPCFLTCK.TX_DATA.VB_FLG = WS_TEMP_PLBOX_NO.charAt(0);
        BPCFLTCK.TX_DATA.PVAL = BPCUBVOU.PVAL;
        BPCFLTCK.TX_DATA.HEAD_NO = BPCUBVOU.HEAD_NO;
        BPCFLTCK.TX_DATA.STS = K_STS_NORMAL;
        S000_CALL_BPZFLTCK();
    }
    public void R_CHK_USE_METHOD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.USE_CTL);
        if (BPCFBVQU.TX_DATA.USE_CTL == '1') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = WS_TR_BRANCH;
            BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCUBVOU.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCUBVOU.PVAL;
            BPRTBVD.KEY.HEAD_NO = BPCUBVOU.HEAD_NO;
            BPRTBVD.KEY.STS = BPCUBVOU.BV_STS;
            CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
            CEP.TRC(SCCGWA, BPCUBVOU.TLR);
            CEP.TRC(SCCGWA, BPCUBVOU.BV_CODE);
            CEP.TRC(SCCGWA, BPCUBVOU.VB_FLG);
            CEP.TRC(SCCGWA, BPCUBVOU.PVAL);
            CEP.TRC(SCCGWA, BPCUBVOU.HEAD_NO);
            CEP.TRC(SCCGWA, BPCUBVOU.BV_STS);
            CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
            CEP.TRC(SCCGWA, "TST00003");
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.HEAD_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '2';
            S000_CALL_BPZRTBDB();
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, "TST00001");
            CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
            CEP.TRC(SCCGWA, BPCUBVOU.BEG_NO);
            CEP.TRC(SCCGWA, BPCUBVOU.USECTL);
            if (!BPRTBVD.BEG_NO.equalsIgnoreCase(BPCUBVOU.BEG_NO) 
                && BPCUBVOU.USECTL != 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LEAST_NO_VB;
                S000_ERR_MSG_PROC();
            }
            BPCRTBDB.INFO.FUNC = 'E';
            S000_CALL_BPZRTBDB();
        }
    }
    public void R_CHK_BV_LEAST_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTBVD);
        BPRTBVD.KEY.BR = BPCPORUP.DATA_INFO.BR;
        BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = BPCUBVOU.BV_CODE;
        BPRTBVD.KEY.VALUE = BPCUBVOU.PVAL;
        BPRTBVD.KEY.HEAD_NO = BPCUBVOU.HEAD_NO;
        BPRTBVD.BEG_NO = BPCUBVOU.BEG_NO;
        BPRTBVD.KEY.END_NO = BPCUBVOU.END_NO;
        BPRTBVD.KEY.STS = BPCUBVOU.BV_STS;
        CEP.TRC(SCCGWA, BPCPORUP.DATA_INFO.BR);
        CEP.TRC(SCCGWA, BPCUBVOU.TLR);
        CEP.TRC(SCCGWA, BPCUBVOU.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBVOU.PVAL);
        CEP.TRC(SCCGWA, BPCUBVOU.HEAD_NO);
        CEP.TRC(SCCGWA, BPCUBVOU.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBVOU.END_NO);
        CEP.TRC(SCCGWA, BPCUBVOU.VB_FLG);
        CEP.TRC(SCCGWA, BPCUBVOU.BV_STS);
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = '3';
        S000_CALL_BPZRTBDB();
        BPCRTBDB.INFO.FUNC = 'N';
        S000_CALL_BPZRTBDB();
        if (BPCRTBDB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
            CEP.TRC(SCCGWA, WS_ERR_MSG);
            S000_ERR_MSG_PROC();
        }
        if (!BPRTBVD.BEG_NO.equalsIgnoreCase(BPCUBVOU.BEG_NO)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_LEAST_NO_SEG;
            S000_ERR_MSG_PROC();
        }
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
    }
    public void B100_UPDATE_BPTTVHPB() throws IOException,SQLException,Exception {
        BPRVHPB.KEY.POOL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPCRVHPB.INFO.FUNC = 'R';
        S000_CALL_BPZRVHPB();
        if (BPCFBVQU.TX_DATA.TYPE == '0') {
            BPRVHPB.BV_CHK_FLG = 'N';
            BPRVHPB.BL_CHK_FLG = 'N';
            BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRVHPB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCRVHPB.INFO.FUNC = 'U';
            S000_CALL_BPZRVHPB();
        }
    }
    public void S000_CALL_BPZSNOCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_NUM_CHK, BPCSNOCK);
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, BP_P_INQ_ORG_REL, BPCPQORR);
        if (BPCPQORR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBVD() throws IOException,SQLException,Exception {
        BPCRTBVD.INFO.POINTER = BPRTBVD;
        BPCRTBVD.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_TBVD, BPCRTBVD);
        if (BPCRTBVD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBVD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPEBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-BASIC-EWA", BPCPEBAS);
        if (BPCPEBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPEBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CKG_LVBF, BPCTLVBF);
        if (BPCTLVBF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLVBF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBUSE() throws IOException,SQLException,Exception {
        BPCRBUSE.INFO.POINTER = BPRBUSE;
        BPCRBUSE.INFO.LEN = 189;
        IBS.CALLCPN(SCCGWA, CPN_R_MGM_BUSE, BPCRBUSE);
        if (BPCRBUSE.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBUSE.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFLTCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BVLT_CHK, BPCFLTCK);
        if (BPCFLTCK.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFLTCK.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_TLAM, BPCFTLAM);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZIFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INQ-FHIST", BPCIFHIS);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCIFHIS.OUTPUT.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void R_DELETE_PROC() throws IOException,SQLException,Exception {
        if (BPCCBVIO.CTL_FLG == '0') {
            R_DELETE_NONUMBER_PROC();
        } else if (BPCCBVIO.CTL_FLG == '1'
            || BPCCBVIO.CTL_FLG == '2') {
            WS_POS = 20;
            CEP.TRC(SCCGWA, WS_POS);
            CEP.TRC(SCCGWA, BPCCBVIO.BVNO_LEN);
            WS_POS = WS_POS - BPCCBVIO.BVNO_LEN + 1;
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = BPCCBVIO.BR;
            BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
            BPRTBVD.KEY.HEAD_NO = BPCCBVIO.HEAD_NO;
            BPRTBVD.BEG_NO = BPCCBVIO.BEG_NO;
            BPRTBVD.KEY.END_NO = BPCCBVIO.END_NO;
            BPRTBVD.NUM = BPCCBVIO.NUM;
            CEP.TRC(SCCGWA, BPRTBVD.NUM);
            CEP.TRC(SCCGWA, "140133");
            BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.HEAD_NO);
            CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
            CEP.TRC(SCCGWA, BPCCBVIO.COMP_BEGNO);
            CEP.TRC(SCCGWA, WS_POS);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '3';
            S000_CALL_BPZRTBDB();
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, "TEST002");
            CEP.TRC(SCCGWA, BPRTBVD.VIL_TYP);
            CEP.TRC(SCCGWA, BPCCBVIO.VIL_TYP);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            CEP.TRC(SCCGWA, BPCCBVIO.VILCTL);
            if (!BPCCBVIO.VIL_TYP.equalsIgnoreCase(BPRTBVD.VIL_TYP) 
                && BPCCBVIO.VILCTL == 'Y' 
                && BPRTBVD.VIL_TYP.trim().length() > 0 
                && !BPRTBVD.VIL_TYP.equalsIgnoreCase("00")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_VIL_TYP_NOTSAME);
            }
            R_DELETE_COMMON_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_CTL_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void R_DELETE_NO_HEAD_PROC() throws IOException,SQLException,Exception {
        if (BPCCBVIO.CTL_FLG == '0') {
            R_DELETE_NONUMBER_PROC();
        } else if (BPCCBVIO.CTL_FLG == '1'
            || BPCCBVIO.CTL_FLG == '2') {
            WS_POS = 20;
            WS_POS = WS_POS - BPCCBVIO.BVNO_LEN + 1;
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = BPCCBVIO.BR;
            BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
            BPRTBVD.KEY.HEAD_NO = BPCCBVIO.HEAD_NO;
            BPRTBVD.BEG_NO = BPCCBVIO.BEG_NO;
            BPRTBVD.KEY.END_NO = BPCCBVIO.END_NO;
            BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.HEAD_NO);
            CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = '8';
            S000_CALL_BPZRTBDB();
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'N';
            S000_CALL_BPZRTBDB();
            if (BPCRTBDB.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
                S000_ERR_MSG_PROC();
            }
            BPCCBVIO.HEAD_NO = BPRTBVD.KEY.HEAD_NO;
            R_DELETE_COMMON_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_CTL_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void R_DELETE_COMMON_PROC() throws IOException,SQLException,Exception {
        if (BPCCBVIO.BEG_NO.equalsIgnoreCase(BPRTBVD.BEG_NO) 
                && BPCCBVIO.END_NO.equalsIgnoreCase(BPRTBVD.KEY.END_NO)) {
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'D';
            CEP.TRC(SCCGWA, BPCRTBDB.INFO.FUNC);
            S000_CALL_BPZRTBDB();
        } else if (BPCCBVIO.BEG_NO.equalsIgnoreCase(BPRTBVD.BEG_NO) 
                && BPCCBVIO.END_NO.compareTo(BPRTBVD.KEY.END_NO) < 0) {
            if (BPCCBVIO.END_NO == null) BPCCBVIO.END_NO = "";
            JIBS_tmp_int = BPCCBVIO.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCCBVIO.END_NO += " ";
            if (BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN));
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
            BPCSNOCK.BV_NO = BPCCBVIO.END_NO;
            BPCSNOCK.FUNC = '4';
            S000_CALL_BPZSNOCK();
            if (BPCSNOCK.NEXT_NO.trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPCSNOCK.NEXT_NO);
            WS_STORAGE = "" + BPCCBVIO.COMP_ENDNO;
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
            if (WS_STORAGE == null) WS_STORAGE = "";
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
            BPRTBVD.BEG_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
            CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
            BPCSNOCK.END_NO = BPRTBVD.KEY.END_NO;
            BPCSNOCK.FUNC = '2';
            S000_CALL_BPZSNOCK();
            BPRTBVD.NUM = BPCSNOCK.NUM;
            CEP.TRC(SCCGWA, "111");
            CEP.TRC(SCCGWA, WS_POS);
            CEP.TRC(SCCGWA, WS_STORAGE);
            CEP.TRC(SCCGWA, BPRTBVD.NUM);
            CEP.TRC(SCCGWA, BPCCBVIO.NUM);
            BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'W';
            S000_CALL_BPZRTBDB();
        } else if (BPCCBVIO.BEG_NO.compareTo(BPRTBVD.BEG_NO) > 0 
                && BPCCBVIO.END_NO.equalsIgnoreCase(BPRTBVD.KEY.END_NO)) {
            IBS.CLONE(SCCGWA, BPRTBVD, BPRTEMP);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'D';
            S000_CALL_BPZRTBDB();
            IBS.CLONE(SCCGWA, BPRTEMP, BPRTBVD);
            if (BPCCBVIO.BEG_NO == null) BPCCBVIO.BEG_NO = "";
            JIBS_tmp_int = BPCCBVIO.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCCBVIO.BEG_NO += " ";
            if (BPCCBVIO.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCCBVIO.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN));
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
            BPCSNOCK.BV_NO = BPCCBVIO.BEG_NO;
            BPCSNOCK.FUNC = '5';
            S000_CALL_BPZSNOCK();
            if (BPCSNOCK.PRE_NO.trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCSNOCK.PRE_NO);
            WS_STORAGE = "" + BPCCBVIO.COMP_BEGNO;
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
            if (WS_STORAGE == null) WS_STORAGE = "";
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
            BPRTBVD.KEY.END_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
            BPCSNOCK.END_NO = BPRTBVD.KEY.END_NO;
            BPCSNOCK.FUNC = '2';
            S000_CALL_BPZSNOCK();
            BPRTBVD.NUM = BPCSNOCK.NUM;
            BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'A';
            S000_CALL_BPZRTBVD();
        } else if (BPCCBVIO.BEG_NO.compareTo(BPRTBVD.BEG_NO) > 0 
                && BPCCBVIO.END_NO.compareTo(BPRTBVD.KEY.END_NO) < 0) {
            BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
            IBS.CLONE(SCCGWA, BPRTBVD, BPRTEMP);
            if (BPRTBVD.KEY.END_NO == null) BPRTBVD.KEY.END_NO = "";
            JIBS_tmp_int = BPRTBVD.KEY.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRTBVD.KEY.END_NO += " ";
            if (BPRTBVD.KEY.END_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPRTBVD.KEY.END_NO.substring(0, BPCCBVIO.BVNO_LEN));
            if (BPCCBVIO.END_NO == null) BPCCBVIO.END_NO = "";
            JIBS_tmp_int = BPCCBVIO.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCCBVIO.END_NO += " ";
            if (BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN));
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
            BPCSNOCK.BV_NO = BPCCBVIO.END_NO;
            BPCSNOCK.FUNC = '4';
            S000_CALL_BPZSNOCK();
            if (BPCSNOCK.NEXT_NO.trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCSNOCK.NEXT_NO);
            WS_STORAGE = "" + BPCCBVIO.COMP_BEGNO;
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
            if (WS_STORAGE == null) WS_STORAGE = "";
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
            BPRTBVD.BEG_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
            BPCSNOCK.END_NO = BPRTBVD.KEY.END_NO;
            BPCSNOCK.FUNC = '2';
            S000_CALL_BPZSNOCK();
            BPRTBVD.NUM = BPCSNOCK.NUM;
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'W';
            S000_CALL_BPZRTBDB();
            IBS.CLONE(SCCGWA, BPRTEMP, BPRTBVD);
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
            BPCSNOCK.BV_NO = BPCCBVIO.BEG_NO;
            BPCSNOCK.FUNC = '5';
            S000_CALL_BPZSNOCK();
            if (BPCSNOCK.PRE_NO.trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPCSNOCK.PRE_NO);
            WS_STORAGE = "" + BPCCBVIO.COMP_ENDNO;
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
            if (WS_STORAGE == null) WS_STORAGE = "";
            JIBS_tmp_int = WS_STORAGE.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
            BPRTBVD.KEY.END_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
            BPCSNOCK.END_NO = BPRTBVD.KEY.END_NO;
            BPCSNOCK.FUNC = '2';
            S000_CALL_BPZSNOCK();
            BPRTBVD.NUM = BPCSNOCK.NUM;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'A';
            S000_CALL_BPZRTBVD();
        } else {
        }
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
    }
    public void R_DELETE_NONUMBER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTBVD);
        BPRTBVD.KEY.BR = BPCCBVIO.BR;
        BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
        BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
        BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
        CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
        IBS.init(SCCGWA, BPCRTBVD);
        BPCRTBVD.INFO.FUNC = 'R';
        S000_CALL_BPZRTBVD();
        if (BPCRTBVD.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPRTBVD.NUM = BPRTBVD.NUM - BPCCBVIO.NUM;
        if (BPRTBVD.NUM > 0) {
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'U';
            S000_CALL_BPZRTBVD();
        } else if (BPRTBVD.NUM == 0) {
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'D';
            S000_CALL_BPZRTBVD();
        } else if (BPRTBVD.NUM < 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_NOT_SUFFICIENT;
            S000_ERR_MSG_PROC();
        } else {
        }
    }
    public void R_ADD_PROC() throws IOException,SQLException,Exception {
        if (BPCCBVIO.CTL_FLG == '0') {
            IBS.init(SCCGWA, BPRTBVD);
            BPRTBVD.KEY.BR = BPCCBVIO.BR;
            BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
            BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
            BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
            BPRTBVD.VIL_TYP = BPCCBVIO.VIL_TYP;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'R';
            S000_CALL_BPZRTBVD();
            if (BPCRTBVD.RETURN_INFO == 'N') {
                BPRTBVD.TYPE = BPCCBVIO.TYPE;
                BPRTBVD.NUM = BPCCBVIO.NUM;
                BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
                IBS.init(SCCGWA, BPCRTBVD);
                BPCRTBVD.INFO.FUNC = 'A';
                S000_CALL_BPZRTBVD();
            } else {
                BPRTBVD.NUM = BPRTBVD.NUM + BPCCBVIO.NUM;
                BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
                IBS.init(SCCGWA, BPCRTBVD);
                BPCRTBVD.INFO.FUNC = 'U';
                S000_CALL_BPZRTBVD();
            }
        } else if (BPCCBVIO.CTL_FLG == '1'
            || BPCCBVIO.CTL_FLG == '2') {
            IBS.init(SCCGWA, BPRTBVD);
            WS_POS = 20;
            CEP.TRC(SCCGWA, WS_POS);
            CEP.TRC(SCCGWA, BPCCBVIO.BVNO_LEN);
            WS_POS = WS_POS - BPCCBVIO.BVNO_LEN + 1;
            if (BPCCBVIO.BEG_NO == null) BPCCBVIO.BEG_NO = "";
            JIBS_tmp_int = BPCCBVIO.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCCBVIO.BEG_NO += " ";
            if (BPCCBVIO.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCCBVIO.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN));
            if (BPCCBVIO.END_NO == null) BPCCBVIO.END_NO = "";
            JIBS_tmp_int = BPCCBVIO.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPCCBVIO.END_NO += " ";
            if (BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPCCBVIO.END_NO.substring(0, BPCCBVIO.BVNO_LEN));
            BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
            CEP.TRC(SCCGWA, BPCCBVIO.VIL_TYP);
            R_FIND_RESEMBLE_REC();
            BPRTBVD.KEY.BR = BPCCBVIO.BR;
            BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
            BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
            BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
            BPRTBVD.KEY.HEAD_NO = BPCCBVIO.HEAD_NO;
            BPRTBVD.TYPE = BPCCBVIO.TYPE;
            BPRTBVD.NOW_NO = " ";
            CEP.TRC(SCCGWA, BPCCBVIO.VIL_TYP);
            BPRTBVD.VIL_TYP = BPCCBVIO.VIL_TYP;
            CEP.TRC(SCCGWA, BPRTBVD.VIL_TYP);
            if (BPCCBVIO.FORWARD_FLG == 'Y'
                && BPCCBVIO.BACKWARD_FLG == 'Y') {
                BPRTBVD.KEY.END_NO = BPCCBVIO.COMBINE_ENDNO_X;
                BPRTBVD.BEG_NO = BPCCBVIO.COMBINE_BEGNO_X;
                CEP.TRC(SCCGWA, "1");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else if (BPCCBVIO.FORWARD_FLG == 'Y'
                && BPCCBVIO.BACKWARD_FLG == 'N') {
                BPRTBVD.KEY.END_NO = BPCCBVIO.COMBINE_ENDNO_X;
                BPRTBVD.BEG_NO = BPCCBVIO.BEG_NO;
                CEP.TRC(SCCGWA, "2");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else if (BPCCBVIO.FORWARD_FLG == 'N'
                && BPCCBVIO.BACKWARD_FLG == 'Y') {
                BPRTBVD.BEG_NO = BPCCBVIO.COMBINE_BEGNO_X;
                BPRTBVD.KEY.END_NO = BPCCBVIO.END_NO;
                CEP.TRC(SCCGWA, "3");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else if (BPCCBVIO.FORWARD_FLG == 'N'
                && BPCCBVIO.BACKWARD_FLG == 'N') {
                BPRTBVD.BEG_NO = BPCCBVIO.BEG_NO;
                BPRTBVD.KEY.END_NO = BPCCBVIO.END_NO;
                CEP.TRC(SCCGWA, "4");
                CEP.TRC(SCCGWA, BPRTBVD.BEG_NO);
                CEP.TRC(SCCGWA, BPRTBVD.KEY.END_NO);
            } else {
            }
            if (BPRTBVD.BEG_NO == null) BPRTBVD.BEG_NO = "";
            JIBS_tmp_int = BPRTBVD.BEG_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRTBVD.BEG_NO += " ";
            if (BPRTBVD.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
            else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPRTBVD.BEG_NO.substring(0, BPCCBVIO.BVNO_LEN));
            if (BPRTBVD.KEY.END_NO == null) BPRTBVD.KEY.END_NO = "";
            JIBS_tmp_int = BPRTBVD.KEY.END_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) BPRTBVD.KEY.END_NO += " ";
            if (BPRTBVD.KEY.END_NO.substring(0, BPCCBVIO.BVNO_LEN).trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
            else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPRTBVD.KEY.END_NO.substring(0, BPCCBVIO.BVNO_LEN));
            IBS.init(SCCGWA, BPCSNOCK);
            BPCSNOCK.BV_CODE = BPRTBVD.KEY.BV_CODE;
            BPCSNOCK.BEG_NO = BPRTBVD.BEG_NO;
            BPCSNOCK.END_NO = BPRTBVD.KEY.END_NO;
            BPCSNOCK.FUNC = '2';
            S000_CALL_BPZSNOCK();
            BPRTBVD.NUM = BPCSNOCK.NUM;
            BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
            BPRTBVD.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRTBVD.UPD_TLR = BPCCBVIO.UPD_TLR;
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'A';
            S000_CALL_BPZRTBVD();
            if (BPCRTBVD.RETURN_INFO == 'D') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NTOT_DUPLICATE;
                S000_ERR_MSG_PROC();
            }
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_CTL_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void R_FIND_RESEMBLE_REC() throws IOException,SQLException,Exception {
        BPRTBVD.KEY.BR = BPCCBVIO.BR;
        BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
        BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
        BPRTBVD.KEY.HEAD_NO = BPCCBVIO.HEAD_NO;
        IBS.init(SCCGWA, BPCSNOCK);
        BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
        BPCSNOCK.BV_NO = BPCCBVIO.BEG_NO;
        BPCSNOCK.FUNC = '5';
        S000_CALL_BPZSNOCK();
        if (BPCSNOCK.PRE_NO.trim().length() == 0) BPCCBVIO.COMP_BEGNO = 0;
        else BPCCBVIO.COMP_BEGNO = Long.parseLong(BPCSNOCK.PRE_NO);
        WS_STORAGE = "" + BPCCBVIO.COMP_BEGNO;
        JIBS_tmp_int = WS_STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
        CEP.TRC(SCCGWA, WS_STORAGE);
        CEP.TRC(SCCGWA, WS_POS);
        CEP.TRC(SCCGWA, BPCCBVIO.BVNO_LEN);
        if (WS_STORAGE == null) WS_STORAGE = "";
        JIBS_tmp_int = WS_STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
        BPRTBVD.KEY.END_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
        BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
        IBS.init(SCCGWA, BPCRTBVD);
        BPCRTBVD.INFO.FUNC = 'R';
        S000_CALL_BPZRTBVD();
        if (BPCRTBVD.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "STEP1");
            CEP.TRC(SCCGWA, BPCCBVIO.VIL_TYP);
            BPCCBVIO.BACKWARD_FLG = 'N';
        } else {
            BPCCBVIO.BACKWARD_FLG = 'Y';
            BPCCBVIO.COMBINE_BEGNO_X = BPRTBVD.BEG_NO;
            CEP.TRC(SCCGWA, BPCCBVIO.COMBINE_BEGNO_X);
            IBS.init(SCCGWA, BPCRTBVD);
            BPCRTBVD.INFO.FUNC = 'D';
            S000_CALL_BPZRTBVD();
        }
        CEP.TRC(SCCGWA, "STEP2");
        CEP.TRC(SCCGWA, BPCCBVIO.VIL_TYP);
        BPRTBVD.KEY.BR = BPCCBVIO.BR;
        BPRTBVD.KEY.PL_BOX_NO = BPCCBVIO.PLBOX_NO;
        BPRTBVD.KEY.BV_CODE = BPCCBVIO.BV_CODE;
        BPRTBVD.KEY.VALUE = BPCCBVIO.PVAL;
        BPRTBVD.KEY.HEAD_NO = BPCCBVIO.HEAD_NO;
        IBS.init(SCCGWA, BPCSNOCK);
        BPCSNOCK.BV_CODE = BPCCBVIO.BV_CODE;
        BPCSNOCK.BV_NO = BPCCBVIO.END_NO;
        BPCSNOCK.FUNC = '4';
        S000_CALL_BPZSNOCK();
        if (BPCSNOCK.NEXT_NO.trim().length() == 0) BPCCBVIO.COMP_ENDNO = 0;
        else BPCCBVIO.COMP_ENDNO = Long.parseLong(BPCSNOCK.NEXT_NO);
        WS_STORAGE = "" + BPCCBVIO.COMP_ENDNO;
        JIBS_tmp_int = WS_STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE = "0" + WS_STORAGE;
        if (WS_STORAGE == null) WS_STORAGE = "";
        JIBS_tmp_int = WS_STORAGE.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_STORAGE += " ";
        BPRTBVD.BEG_NO = WS_STORAGE.substring(WS_POS - 1, WS_POS + BPCCBVIO.BVNO_LEN - 1);
        BPRTBVD.KEY.STS = BPCCBVIO.BV_STS;
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = '4';
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, "STEP3");
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'N';
        S000_CALL_BPZRTBDB();
        if (BPCRTBDB.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, "STEP4");
            BPCCBVIO.FORWARD_FLG = 'N';
        } else {
            BPCCBVIO.FORWARD_FLG = 'Y';
            BPCCBVIO.COMBINE_ENDNO_X = BPRTBVD.KEY.END_NO;
            CEP.TRC(SCCGWA, BPCCBVIO.COMBINE_ENDNO_X);
            IBS.init(SCCGWA, BPCRTBDB);
            BPCRTBDB.INFO.FUNC = 'D';
            S000_CALL_BPZRTBDB();
        }
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
        CEP.TRC(SCCGWA, "STEP5");
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
