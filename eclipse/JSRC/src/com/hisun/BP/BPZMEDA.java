package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;

public class BPZMEDA {
    boolean pgmRtn = false;
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY      ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_R_EXRD_B = "BP-R-EXRD-B         ";
    String CPN_R_SPCA_M = "BP-R-SPCA-M         ";
    String CPN_R_SPCA_B = "BP-R-SPCA-B         ";
    short K_MAX_ARRAY = 10;
    String K_HIS_REMARKS = "AUTH RECORD";
    String K_HIS_COPYBOOK_NAME = "BPRSPCA";
    String K_OUTPUT_FMT = "BP274";
    String K_OUTPUT_FMT_X = "BPX01";
    int K_MAX_ROW = 50;
    int K_MAX_COL = 99;
    int K_COL_CNT = 6;
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    BPZMEDA_WS_MPAG_DATA WS_MPAG_DATA = new BPZMEDA_WS_MPAG_DATA();
    BPZMEDA_WS_BPRSPCA WS_BPRSPCA = new BPZMEDA_WS_BPRSPCA();
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
    BPCO274 BPCO274 = new BPCO274();
    BPREXRT BPREXRT = new BPREXRT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPRPARM BPRPARM = new BPRPARM();
    SCCGWA SCCGWA;
    BPCMEDA BPCMEDA;
    public void MP(SCCGWA SCCGWA, BPCMEDA BPCMEDA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCMEDA = BPCMEDA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZMEDA return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMEDA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (BPCMEDA.FUNC == 'I'
            || BPCMEDA.FUNC == 'P') {
            B020_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (BPCMEDA.FUNC == 'A') {
            B030_CREATE_PROC();
            if (pgmRtn) return;
        } else if (BPCMEDA.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCMEDA.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else if (BPCMEDA.FUNC == 'B') {
            B060_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCMEDA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCMEDA.FUNC == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_FUNC, BPCMEDA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if ((BPCMEDA.FUNC != 'I' 
                && BPCMEDA.FUNC != 'A' 
                && BPCMEDA.FUNC != 'M' 
                && BPCMEDA.FUNC != 'D' 
                && BPCMEDA.FUNC != 'B' 
                && BPCMEDA.FUNC != 'P')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_FUNC, BPCMEDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCMEDA.FUNC == 'I' 
            || BPCMEDA.FUNC == 'A' 
            || BPCMEDA.FUNC == 'M' 
            || BPCMEDA.FUNC == 'D') {
            if (BPCMEDA.INP_DATA.CCY.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_CCY, BPCMEDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCMEDA.FUNC == 'A' 
            || BPCMEDA.FUNC == 'M') {
            if (BPCMEDA.INP_DATA.AUTH_SET[1-1].FLT_LMT == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_AUTH_SET, BPCMEDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCMEDA.FUNC == 'A' 
            || BPCMEDA.FUNC == 'M') {
            IBS.init(SCCGWA, BPREXRD);
            IBS.init(SCCGWA, BPCREXRS);
            BPREXRD.KEY.CCY = BPCMEDA.INP_DATA.CCY;
            BPREXRD.KEY.EXR_TYP = BPCMEDA.INP_DATA.EXR_TYP;
            BPREXRD.KEY.FWD_TENOR = BPCMEDA.INP_DATA.TENOR;
            BPCREXRS.INFO.FUNC = '3';
            R000_BRW_EXRD_PROC();
            if (pgmRtn) return;
            R000_RDNXT_EXRD_PROC();
            if (pgmRtn) return;
            if (BPCREXRS.INFO.RTN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_BE_DEF_CCY, BPCMEDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            R000_END_BRW_EXRD_PROC();
            if (pgmRtn) return;
        }
        for (WS_K = 1; WS_K <= 10; WS_K += 1) {
            if (BPCMEDA.INP_DATA.AUTH_SET[WS_K-1].MSG_CODE.trim().length() > 0) {
                IBS.init(SCCGWA, BPRPARM);
                IBS.init(SCCGWA, BPCPRMR);
                BPCMEDA.INP_DATA.AUTH_SET[WS_K-1].MSG_CODE = BPCMSG_ERROR_MSG.BP_MUST_IMPOWER;
                BPRPARM.KEY.TYPE = "MSG";
                BPRPARM.KEY.CODE = BPCMEDA.INP_DATA.AUTH_SET[WS_K-1].MSG_CODE;
                BPCPRMR.TYP = "MSG";
                BPCPRMR.CD = BPCMEDA.INP_DATA.AUTH_SET[WS_K-1].MSG_CODE;
                BPCPRMR.DAT_PTR = BPRPARM;
                S000_CALL_BPZPRMR();
                if (pgmRtn) return;
                if (BPCPRMR.RC.RC_RTNCODE != 0) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MSG_CODE_NOTFND, BPCMEDA.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCMEDA.FUNC == 'I' 
            || BPCMEDA.FUNC == 'A' 
            || BPCMEDA.FUNC == 'M' 
            || BPCMEDA.FUNC == 'D') {
            if (BPCMEDA.INP_DATA.EXR_TYP.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_EXR_TYPE, BPCMEDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCMEDA.INP_DATA.EXR_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPREXRT);
            IBS.init(SCCGWA, BPCPRMR);
            BPREXRT.KEY.TYP = "EXRT";
            BPREXRT.KEY.CD = BPCMEDA.INP_DATA.EXR_TYP;
            CEP.TRC(SCCGWA, BPCMEDA.INP_DATA.EXR_TYP);
            BPCPRMR.DAT_PTR = BPREXRT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXRGROP, BPCMEDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPREXRT.DAT_TXT.FWD_IND == '1' 
                && BPCMEDA.INP_DATA.TENOR.trim().length() == 0 
                && (BPCMEDA.FUNC == 'A' 
                || BPCMEDA.FUNC == 'M')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_FWD_TENOR, BPCMEDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPREXRT.DAT_TXT.FWD_IND == '0' 
                && BPCMEDA.INP_DATA.TENOR.trim().length() > 0 
                && (BPCMEDA.FUNC == 'A' 
                || BPCMEDA.FUNC == 'M')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NONEED_FWD_TENOR, BPCMEDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCMEDA.INP_DATA.TENOR.trim().length() > 0) {
            R000_CHECK_FWD_TENOR();
            if (pgmRtn) return;
        }
        if (BPCMEDA.INP_DATA.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = BPCMEDA.INP_DATA.CCY;
            IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
            if (BPCQCCY.RC.RTNCODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCMEDA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BPRSPCA);
        IBS.init(SCCGWA, BPCTSPCM);
        WS_BPRSPCA.WS_SPCA_KEY.WS_SPCA_CHK_TYP = '1';
        WS_BPRSPCA.WS_SPCA_KEY.WS_SPCA_EXR_TYP = BPCMEDA.INP_DATA.EXR_TYP;
        WS_BPRSPCA.WS_SPCA_KEY.WS_SPCA_CCY = BPCMEDA.INP_DATA.CCY;
        WS_BPRSPCA.WS_SPCA_KEY.WS_SPCA_TENOR = BPCMEDA.INP_DATA.TENOR;
        WS_BPRSPCA.WS_SPCA_CMP_FLG = BPCMEDA.INP_DATA.CMP_FLG;
        R000_RD_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B030_CREATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_BPRSPCA);
        IBS.init(SCCGWA, BPCTSPCM);
        IBS.init(SCCGWA, BPRSPCAO);
        IBS.init(SCCGWA, BPRSPCAN);
        WS_BPRSPCA.WS_SPCA_KEY.WS_SPCA_CHK_TYP = '1';
        WS_BPRSPCA.WS_SPCA_KEY.WS_SPCA_EXR_TYP = BPCMEDA.INP_DATA.EXR_TYP;
        WS_BPRSPCA.WS_SPCA_KEY.WS_SPCA_CCY = BPCMEDA.INP_DATA.CCY;
        WS_BPRSPCA.WS_SPCA_KEY.WS_SPCA_TENOR = BPCMEDA.INP_DATA.TENOR;
        WS_BPRSPCA.WS_SPCA_CMP_FLG = BPCMEDA.INP_DATA.CMP_FLG;
        for (WS_I = 1; WS_I <= K_MAX_ARRAY; WS_I += 1) {
            WS_BPRSPCA.WS_SPCA_AUTH_SET[WS_I-1].WS_SPCA_FLT_LMT = BPCMEDA.INP_DATA.AUTH_SET[WS_I-1].FLT_LMT;
            WS_BPRSPCA.WS_SPCA_AUTH_SET[WS_I-1].WS_SPCA_ATH_LVL = BPCMEDA.INP_DATA.AUTH_SET[WS_I-1].ATH_LVL;
            WS_BPRSPCA.WS_SPCA_AUTH_SET[WS_I-1].WS_SPCA_MSG_CODE = BPCMEDA.INP_DATA.AUTH_SET[WS_I-1].MSG_CODE;
        }
        WS_BPRSPCA.WS_SPCA_CNT = (short) (WS_I - 1);
        WS_BPRSPCA.WS_SPCA_CRE_DT = SCCGWA.COMM_AREA.TR_DATE;
        WS_BPRSPCA.WS_SPCA_CRE_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_BPRSPCA.WS_SPCA_CRE_TLR = SCCGWA.COMM_AREA.TL_ID;
        R000_WRT_PROC();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_BPRSPCA);
