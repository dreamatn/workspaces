package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;

public class BPZMBDA {
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY      ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_R_EXRD_B = "BP-R-EXRD-B         ";
    String CPN_R_SPCA_M = "BP-R-SPCA-M         ";
    String CPN_R_SPCA_B = "BP-R-SPCA-B         ";
    short K_MAX_ARRAY = 10;
    String K_HIS_REMARKS = "AUTH RECORD";
    String K_HIS_COPYBOOK_NAME = "BPRSPCA";
    String K_OUTPUT_FMT = "BP275";
    String K_OUTPUT_FMT_X = "BPX01";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 6;
    short WS_I = 0;
    short WS_J = 0;
    BPZMBDA_WS_MPAG_DATA WS_MPAG_DATA = new BPZMBDA_WS_MPAG_DATA();
    BPZMBDA_WS_SPCA_KEY WS_SPCA_KEY = new BPZMBDA_WS_SPCA_KEY();
    char WS_SPCA_CMP_FLG = ' ';
    short WS_SPCA_CNT = 0;
    BPZMBDA_WS_SPCA_AUTH_SET[] WS_SPCA_AUTH_SET = new BPZMBDA_WS_SPCA_AUTH_SET[10];
    int WS_SPCA_UPD_DT = 0;
    int WS_SPCA_UPD_TM = 0;
    int WS_SPCA_UPD_BR = 0;
    String WS_SPCA_UPD_TLR = " ";
    String WS_SPCA_TS = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPREXRD BPREXRD = new BPREXRD();
    BPCREXRS BPCREXRS = new BPCREXRS();
    BPRSPCA BPRSPCA = new BPRSPCA();
    BPRSPCA BPRSPCAO = new BPRSPCA();
    BPRSPCA BPRSPCAN = new BPRSPCA();
    BPCTSPCM BPCTSPCM = new BPCTSPCM();
    BPCRSPCA BPCRSPCA = new BPCRSPCA();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCO275 BPCO275 = new BPCO275();
    SCCGWA SCCGWA;
    BPCMBDA BPCMBDA;
    public BPZMBDA() {
        for (int i=0;i<10;i++) WS_SPCA_AUTH_SET[i] = new BPZMBDA_WS_SPCA_AUTH_SET();
    }
    public void MP(SCCGWA SCCGWA, BPCMBDA BPCMBDA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCMBDA = BPCMBDA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZMBDA return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMBDA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (BPCMBDA.FUNC == 'I'
            || BPCMBDA.FUNC == 'P') {
            B020_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (BPCMBDA.FUNC == 'A') {
            B030_CREATE_PROC();
            if (pgmRtn) return;
        } else if (BPCMBDA.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCMBDA.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else if (BPCMBDA.FUNC == 'B') {
            B060_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCMBDA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCMBDA.FUNC == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_FUNC, BPCMBDA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if ((BPCMBDA.FUNC != 'I' 
                && BPCMBDA.FUNC != 'A' 
                && BPCMBDA.FUNC != 'M' 
                && BPCMBDA.FUNC != 'D' 
                && BPCMBDA.FUNC != 'B' 
                && BPCMBDA.FUNC != 'P')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_FUNC, BPCMBDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        for (WS_I = 1; WS_I <= K_MAX_ARRAY; WS_I += 1) {
            if ((BPCMBDA.INP_DATA.AUTH_SET[WS_I-1].FLT_LMT == 0 
                && BPCMBDA.INP_DATA.AUTH_SET[WS_I-1].ATH_LVL.trim().length() > 0) 
                || (BPCMBDA.INP_DATA.AUTH_SET[WS_I-1].FLT_LMT != 0 
                && BPCMBDA.INP_DATA.AUTH_SET[WS_I-1].ATH_LVL.trim().length() == 0)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_AUTH_LVL, BPCMBDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        for (WS_I = 1; WS_I <= K_MAX_ARRAY 
            && BPCMBDA.INP_DATA.AUTH_SET[WS_I-1].FLT_LMT != 0; WS_I += 1) {
        }
        for (WS_J = WS_I; WS_J <= K_MAX_ARRAY 
            && BPCMBDA.INP_DATA.AUTH_SET[WS_J-1].FLT_LMT == 0; WS_J += 1) {
        }
        if (WS_I < K_MAX_ARRAY 
            && WS_J <= K_MAX_ARRAY) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_ARR_IN_ORDER, BPCMBDA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_I = 2; WS_I <= K_MAX_ARRAY 
            && BPCMBDA.INP_DATA.AUTH_SET[WS_I-1].FLT_LMT != 0; WS_I += 1) {
            WS_J = (short) (WS_I - 1);
            if (BPCMBDA.INP_DATA.AUTH_SET[WS_I-1].FLT_LMT <= BPCMBDA.INP_DATA.AUTH_SET[WS_J-1].FLT_LMT) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_BE_ASCENDING, BPCMBDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCMBDA.FUNC == 'I' 
            || BPCMBDA.FUNC == 'A' 
            || BPCMBDA.FUNC == 'M' 
            || BPCMBDA.FUNC == 'D') {
            if (BPCMBDA.INP_DATA.CCY.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_CCY, BPCMBDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCMBDA.INP_DATA.CORR_CCY.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_CORR_CCY, BPCMBDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCMBDA.FUNC == 'A' 
            || BPCMBDA.FUNC == 'M') {
            if (BPCMBDA.INP_DATA.AUTH_SET[1-1].FLT_LMT == 0 
                || BPCMBDA.INP_DATA.AUTH_SET[1-1].ATH_LVL.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_AUTH_SET, BPCMBDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCMBDA.FUNC == 'A' 
            || BPCMBDA.FUNC == 'M') {
            IBS.init(SCCGWA, BPREXRD);
            IBS.init(SCCGWA, BPCREXRS);
            BPREXRD.KEY.CCY = BPCMBDA.INP_DATA.CCY;
            BPREXRD.KEY.CORR_CCY = BPCMBDA.INP_DATA.CORR_CCY;
            BPCREXRS.INFO.FUNC = '3';
            R000_STARTBR_EXRD_PROC();
            if (pgmRtn) return;
            R000_READNEXT_EXRD_PROC();
            if (pgmRtn) return;
            if (BPCREXRS.INFO.RTN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_BE_DEF_CCY, BPCMBDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            R000_ENDBR_EXRD_PROC();
            if (pgmRtn) return;
        }
        if (BPCMBDA.INP_DATA.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCMBDA.INP_DATA.BR;
            IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
            if (BPCPQORG.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCMBDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCMBDA.INP_DATA.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPCMBDA.INP_DATA.CCY;
            IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
            if (BPCQCCY.RC.RTNCODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCMBDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCMBDA.INP_DATA.CORR_CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPCMBDA.INP_DATA.CORR_CCY;
            IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
            if (BPCQCCY.RC.RTNCODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCMBDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTSPCM);
        WS_SPCA_KEY.WS_SPCA_CHK_TYP = '2';
        WS_SPCA_KEY.WS_SPCA_BR = BPCMBDA.INP_DATA.BR;
        WS_SPCA_KEY.WS_SPCA_CCY = BPCMBDA.INP_DATA.CCY;
        WS_SPCA_KEY.WS_SPCA_CORR_CCY = BPCMBDA.INP_DATA.CORR_CCY;
        R000_READ_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTSPCM);
        IBS.init(SCCGWA, BPRSPCAO);
        IBS.init(SCCGWA, BPRSPCAN);
        WS_SPCA_KEY.WS_SPCA_CHK_TYP = '2';
        WS_SPCA_KEY.WS_SPCA_BR = BPCMBDA.INP_DATA.BR;
        WS_SPCA_KEY.WS_SPCA_CCY = BPCMBDA.INP_DATA.CCY;
        WS_SPCA_KEY.WS_SPCA_CORR_CCY = BPCMBDA.INP_DATA.CORR_CCY;
        WS_SPCA_CMP_FLG = BPCMBDA.INP_DATA.CMP_FLG;
        for (WS_I = 1; WS_I <= K_MAX_ARRAY 
            && BPCMBDA.INP_DATA.AUTH_SET[WS_I-1].FLT_LMT != 0; WS_I += 1) {
            WS_SPCA_AUTH_SET[WS_I-1].WS_SPCA_FLT_LMT = BPCMBDA.INP_DATA.AUTH_SET[WS_I-1].FLT_LMT;
            WS_SPCA_AUTH_SET[WS_I-1].WS_SPCA_ATH_LVL = BPCMBDA.INP_DATA.AUTH_SET[WS_I-1].ATH_LVL;
        }
        WS_SPCA_CNT = (short) (WS_I - 1);
        WS_SPCA_UPD_DT = SCCGWA.COMM_AREA.TR_DATE;
        WS_SPCA_UPD_TM = SCCGWA.COMM_AREA.TR_TIME;
        WS_SPCA_UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_SPCA_UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        R000_WRITE_PROC();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPZMBDA_WS2);
