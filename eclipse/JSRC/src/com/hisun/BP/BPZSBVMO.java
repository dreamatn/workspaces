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

public class BPZSBVMO {
    boolean pgmRtn = false;
    int K_CNT = 4;
    String K_OUTPUT_FMT = "BP151";
    String K_HIS_REMARKS = "TLR-BV-MOV-OUT";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_U_BV_OUT = "BP-U-TLR-BV-OUT     ";
    String CPN_F_TLR_PEND_MGM = "BP-F-TLR-PEND-MGM   ";
    String CPN_R_MGM_BMOV = "BP-R-MGM-BMOV       ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_S_BVRG_CHK = "BP-S-BVRG-CHK       ";
    String CPN_S_BVAPP_MAINTAIN = "BP-S-BVAPP-MAINTAIN";
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ        ";
    String K_MOV_SEQ_TYPE = "CMOVE";
    String K_MOV_SEQ_NAME = "CASHMOVE";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_BV_CNT = 0;
    BPZSBVMO_WS_TYPE_INFO[] WS_TYPE_INFO = new BPZSBVMO_WS_TYPE_INFO[10];
    int WS_CONF_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBMOV BPRBMOV = new BPRBMOV();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPCO151 BPCO151 = new BPCO151();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCUBVOU BPCUBVOU = new BPCUBVOU();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    BPCRBMOV BPCRBMOV = new BPCRBMOV();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCFRGCK BPCFRGCK = new BPCFRGCK();
    BPCOAPLL BPCOAPLL = new BPCOAPLL();
    BPRAPLI BPRAPLI = new BPRAPLI();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBVMO BPCSBVMO;
    public BPZSBVMO() {
        for (int i=0;i<10;i++) WS_TYPE_INFO[i] = new BPZSBVMO_WS_TYPE_INFO();
    }
    public void MP(SCCGWA SCCGWA, BPCSBVMO BPCSBVMO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVMO = BPCSBVMO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVMO return!");
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
            B020_01_GEN_SEQ();
            if (pgmRtn) return;
            B020_TLR_BV_OUT_CH();
            if (pgmRtn) return;
            B040_REC_BMOV();
            if (pgmRtn) return;
            B050_REC_NHIS();
            if (pgmRtn) return;
            if (BPCSBVMO.APP_NO != 0 
                && BPCSBVMO.APP_NO != ' ') {
                B040_UPDATE_BV_APP_INF_PROC();
                if (pgmRtn) return;
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                CEP.TRC(SCCGWA, BPCSBVMO.CNT1);
                if (BPCSBVMO.CNT1 < 3) {
                    B060_DATA_OUTPUT();
                    if (pgmRtn) return;
                }
                BPCSBVMO.CONF_NO = WS_CONF_NO;
                BPCSBVMO.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
                CEP.TRC(SCCGWA, BPCSBVMO.CONF_NO);
                CEP.TRC(SCCGWA, BPCSBVMO.MOV_DT);
            }
        } else {
            B010_CHK_INPUT();
            if (pgmRtn) return;
            B020_TLR_BV_OUT();
            if (pgmRtn) return;
            B040_REC_BMOV();
            if (pgmRtn) return;
            B050_REC_NHIS();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                B060_DATA_OUTPUT();
                if (pgmRtn) return;
                BPCSBVMO.CONF_NO = WS_CONF_NO;
                BPCSBVMO.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
                CEP.TRC(SCCGWA, BPCSBVMO.CONF_NO);
                CEP.TRC(SCCGWA, BPCSBVMO.MOV_DT);
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                if (GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE != SCCGWA.COMM_AREA.AC_DATE) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANCEL_NO_TERTIAN;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B011_CHK_BMOV_STS();
            if (pgmRtn) return;
        } else {
            B012_CHK_BR_TLR();
            if (pgmRtn) return;
        }
    }
    public void B010_CHK_INPUT_CH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_IND);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B011_CHK_BMOV_STS();
            if (pgmRtn) return;
        } else {
            B012_CHK_BR_TLR_CH();
            if (pgmRtn) return;
        }
    }
    public void B011_CHK_BMOV_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBMOV);
        IBS.init(SCCGWA, BPRBMOV);
        IBS.init(SCCGWA, BPCFBVQU);
        CEP.TRC(SCCGWA, BPCSBVMO.MOV_DT);
        CEP.TRC(SCCGWA, BPCSBVMO.CONF_NO);
        BPRBMOV.KEY.MOV_DT = BPCSBVMO.MOV_DT;
        BPRBMOV.KEY.CONF_NO = BPCSBVMO.CONF_NO;
        BPCRBMOV.INFO.FUNC = 'Q';
        S000_CALL_BPZRBMOV();
        if (pgmRtn) return;
        if (BPCRBMOV.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPRBMOV.MOV_STS != '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOVSTS_MUST_0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVMO.BV_DATA[1-1].BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
    }
    public void B012_CHK_BR_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        CEP.TRC(SCCGWA, BPCSBVMO.IN_BR);
        BPCPQORG.BR = BPCSBVMO.IN_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.ORG_STS);
        CEP.TRC(SCCGWA, BPCSBVMO.IN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (BPCPQORG.ORG_STS != 'O') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_SIGN_OFF;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSBVMO.IN_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_SAME_NOTALLOWED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCSBVMO.IN_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVMO.BV_DATA[1-1].BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
    }
    public void B012_CHK_BR_TLR_CH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        if (BPCSBVMO.IN_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_SAME_NOTALLOWED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_I = 1; WS_I <= BPCSBVMO.CNT 
            && BPCSBVMO.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVMO.BV_DATA[WS_I-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            WS_TYPE_INFO[WS_I-1].WS_BV_TYPE = BPCFBVQU.TX_DATA.TYPE;
            if (BPCFBVQU.TX_DATA.BV_RANGE == '0') {
                BPCFRGCK.BV_CODE = BPCFBVQU.TX_DATA.KEY.CODE;
                BPCFRGCK.BR = BPCSBVMO.IN_BR;
                S000_CALL_BPZFRGCK();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_TLR_BV_OUT() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= BPCSBVMO.CNT 
            && BPCSBVMO.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCUBVOU);
            BPCUBVOU.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUBVOU.TYPE = '0';
            BPCUBVOU.BV_CODE = BPCSBVMO.BV_DATA[WS_I-1].BV_CODE;
            BPCUBVOU.HEAD_NO = BPCSBVMO.BV_DATA[WS_I-1].HEAD_NO;
            BPCUBVOU.BEG_NO = BPCSBVMO.BV_DATA[WS_I-1].BEG_NO;
            BPCUBVOU.END_NO = BPCSBVMO.BV_DATA[WS_I-1].END_NO;
            BPCUBVOU.NUM = BPCSBVMO.BV_DATA[WS_I-1].NUM;
            BPCUBVOU.BV_STS = BPCSBVMO.BV_STS;
            BPCUBVOU.VB_FLG = '1';
            BPCUBVOU.AC_TYP = '0';
            S000_CALL_BPZUBVOU();
            if (pgmRtn) return;
        }
        WS_BV_CNT = WS_I;
    }
    public void B020_01_GEN_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        BPCSGSEQ.TYPE = K_MOV_SEQ_TYPE;
        BPCSGSEQ.CODE = K_MOV_SEQ_NAME;
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        WS_CONF_NO = (int) BPCSGSEQ.SEQ;
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSBVMO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_TLR_BV_OUT_CH() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= BPCSBVMO.CNT 
            && BPCSBVMO.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCUBVOU);
            BPCUBVOU.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUBVOU.TYPE = BPCFBVQU.TX_DATA.TYPE;
            BPCUBVOU.BV_CODE = BPCSBVMO.BV_DATA[WS_I-1].BV_CODE;
            BPCUBVOU.HEAD_NO = BPCSBVMO.BV_DATA[WS_I-1].HEAD_NO;
            BPCUBVOU.BEG_NO = BPCSBVMO.BV_DATA[WS_I-1].BEG_NO;
            BPCUBVOU.END_NO = BPCSBVMO.BV_DATA[WS_I-1].END_NO;
            BPCUBVOU.NUM = BPCSBVMO.BV_DATA[WS_I-1].NUM;
            BPCUBVOU.BV_STS = BPCSBVMO.BV_STS;
            BPCUBVOU.VIL_TYP = BPCSBVMO.VIL_TYP;
            CEP.TRC(SCCGWA, BPCUBVOU.BV_STS);
            if (BPCUBVOU.TYPE == '3') {
                BPCUBVOU.VB_FLG = BPCSBVMO.PB_FLG;
            } else {
                BPCUBVOU.VB_FLG = '1';
            }
            CEP.TRC(SCCGWA, BPCSBVMO.BR_FLG);
            if (BPCSBVMO.BR_FLG == 'Y') {
                BPCUBVOU.VB_FLG = BPCSBVMO.OUT_TYP;
            }
            CEP.TRC(SCCGWA, BPCUBVOU.VB_FLG);
            BPCUBVOU.AC_TYP = '0';
            BPCUBVOU.USECTL = 'N';
            IBS.init(SCCGWA, BPCPQORG);
            CEP.TRC(SCCGWA, BPCSBVMO.IN_BR);
            BPCPQORG.BR = BPCSBVMO.IN_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.TYP);
            CEP.TRC(SCCGWA, BPCSBVMO.BR_FLG);
            if (BPCSBVMO.BR_FLG == 'N' 
                && !BPCPQORG.TYP.equalsIgnoreCase("01") 
                && !BPCPQORG.TYP.equalsIgnoreCase("02") 
                && !BPCPQORG.TYP.equalsIgnoreCase("03")) {
                BPCUBVOU.VILCTL = 'Y';
            }
            CEP.TRC(SCCGWA, BPCUBVOU.VILCTL);
            S000_CALL_BPZUBVOU();
            if (pgmRtn) return;
        }
        WS_BV_CNT = WS_I - 1;
    }
    public void B021_BUSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVMO.BV_DATA[WS_I-1].BV_CODE;
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
        BPRBUSE.KEY.BV_CODE = BPCSBVMO.BV_DATA[WS_I-1].BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVMO.BV_DATA[WS_I-1].HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVMO.BV_DATA[WS_I-1].BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVMO.BV_DATA[WS_I-1].END_NO;
        BPRBUSE.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRBUSE.KEY.TX_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BPRBUSE.TYPE = '0';
        BPRBUSE.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPRBUSE.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.TX_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBUSE.TX_AUTH = " ";
        BPRBUSE.STS = '5';
        BPRBUSE.REC_STS = '0';
        BPCRBUSE.INFO.FUNC = 'A';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B022_ADD_BUSE_PROCESS_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBUSE);
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBUSE.KEY.BV_CODE = BPCSBVMO.BV_DATA[WS_I-1].BV_CODE;
        BPRBUSE.KEY.HEAD_NO = BPCSBVMO.BV_DATA[WS_I-1].HEAD_NO;
        BPRBUSE.KEY.BEG_NO = BPCSBVMO.BV_DATA[WS_I-1].BEG_NO;
        BPRBUSE.KEY.END_NO = BPCSBVMO.BV_DATA[WS_I-1].END_NO;
        BPRBUSE.KEY.TX_DT = BPCSBVMO.MOV_DT;
        BPRBUSE.KEY.TX_JRN = BPCSBVMO.CONF_NO;
        BPCRBUSE.INFO.FUNC = 'R';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCRBUSE);
        BPRBUSE.REC_STS = '1';
        BPCRBUSE.INFO.FUNC = 'U';
        S000_CALL_BPZRBUSE();
        if (pgmRtn) return;
    }
    public void B030_TLR_PEND_MGM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLPM);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCFTLPM.OP_CODE = 'D';
        } else {
            BPCFTLPM.OP_CODE = 'A';
        }
        BPCFTLPM.BUSS_TYP = "02";
        BPCFTLPM.TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCFTLPM.TLR);
        S000_CALL_BPZFTLPM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFTLPM);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCFTLPM.OP_CODE = 'D';
        } else {
            BPCFTLPM.OP_CODE = 'A';
        }
        BPCFTLPM.BUSS_TYP = "04";
        BPCFTLPM.TLR = BPCSBVMO.IN_TLR;
        CEP.TRC(SCCGWA, BPCSBVMO.IN_TLR);
        S000_CALL_BPZFTLPM();
        if (pgmRtn) return;
    }
    public void B040_REC_BMOV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRBMOV);
        IBS.init(SCCGWA, BPRBMOV);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRBMOV.KEY.CONF_NO = BPCSBVMO.CONF_NO;
            BPRBMOV.KEY.MOV_DT = BPCSBVMO.MOV_DT;
            BPCRBMOV.INFO.FUNC = 'R';
            S000_CALL_BPZRBMOV();
            if (pgmRtn) return;
            if (BPCRBMOV.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPRBMOV.MOV_STS = '1';
            BPCRBMOV.INFO.FUNC = 'U';
            S000_CALL_BPZRBMOV();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPRAPLI);
            BPRAPLI.CONF_NO = BPCSBVMO.CONF_NO;
            BPRAPLI.APP_STS = '5';
            S000_READ_BPTAPLI();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, BPRAPLI.APP_TYPE);
                if (BPRAPLI.APP_TYPE == '0') {
                    BPRAPLI.APP_STS = '4';
                } else {
                    if (BPRAPLI.APP_TYPE == '1') {
                        BPRAPLI.APP_STS = '0';
                    }
                }
                BPRAPLI.CONF_NO = 0;
                T000_REWRITE_BPTAPLI();
                if (pgmRtn) return;
            }
        } else {
            R010_REC_BMOV();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRBMOV);
            BPCRBMOV.INFO.FUNC = 'A';
            S000_CALL_BPZRBMOV();
            if (pgmRtn) return;
            if (BPCRBMOV.RETURN_INFO == 'D') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BMOV_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_READ_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        BPTAPLI_RD.where = "CONF_NO = :BPRAPLI.CONF_NO "
            + "AND APP_STS = :BPRAPLI.APP_STS";
        BPTAPLI_RD.upd = true;
        BPTAPLI_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRAPLI, this, BPTAPLI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0'
            || SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTAPLI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTAPLI() throws IOException,SQLException,Exception {
        BPTAPLI_RD = new DBParm();
        BPTAPLI_RD.TableName = "BPTAPLI";
        IBS.REWRITE(SCCGWA, BPRAPLI, BPTAPLI_RD);
    }
    public void B040_UPDATE_BV_APP_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPLL);
        BPCOAPLL.FUNC = 'M';
        BPCOAPLL.MODIFY_STS = 'K';
        CEP.TRC(SCCGWA, BPRBMOV.KEY.CONF_NO);
        CEP.TRC(SCCGWA, BPCSBVMO.APP_NO);
        BPCOAPLL.CONF_SEQ = (int) BPRBMOV.KEY.CONF_NO;
        BPCOAPLL.APP_NO = BPCSBVMO.APP_NO;
        CEP.TRC(SCCGWA, BPCOAPLL.CONF_SEQ);
        CEP.TRC(SCCGWA, BPCOAPLL.APP_NO);
        S000_CALL_BPZSAPLL();
        if (pgmRtn) return;
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
        R020_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO151;
