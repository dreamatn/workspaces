package com.hisun.AI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.*;
import com.hisun.SC.*;

public class AIZUVHPO {
    String K_PROC_START_TLR = "SCPWAT93";
    String K_PROC_START_BSP = "SCPWAT94";
    String K_PROC_END = "AIPBSP05";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    Object WS_VWAX_PTR;
    Object WS_ANSX_PTR;
    Object WS_ITMX_PTR;
    Object WS_ITMA_PTR;
    char WS_FIN_START_FLG = ' ';
    char WS_FIN_END_FLG = ' ';
    char WS_REENTRY_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCBSTS SCCCBSTS = new SCCCBSTS();
    BPRVWA BPRVWAX = new BPRVWA();
    BPRANS BPRANSX = new BPRANS();
    AICITMA AICITMX = new AICITMA();
    AICITMA AICITMA = new AICITMA();
    AICOCKVH AICOCKVH = new AICOCKVH();
    AICOGLPO AICOGLPO = new AICOGLPO();
    AICOUMIB AICOUMIB = new AICOUMIB();
    AICOVLOG AICOVLOG = new AICOVLOG();
    BPRCHNTB BPRCHNTB = new BPRCHNTB();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPRVWA BPRVWA;
    BPRANS BPRANS;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIZUVHPO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        BPRVWA = (BPRVWA) GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
        BPRANS = (BPRANS) GWA_BP_AREA.ANS_AREA_PTR;
        BPRVWAX.BASIC_AREA.CNT = 0;
        BPRANSX.CNT = 0;
        AICITMX.CNT = 0;
        AICITMA.CNT = 0;
        WS_VWAX_PTR = BPRVWAX;
        WS_ANSX_PTR = BPRANSX;
        WS_ITMX_PTR = AICITMX;
        WS_ITMA_PTR = AICITMA;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPRVWA.BASIC_AREA.CNT > 0) {
            WS_REENTRY_FLG = 'N';
            B020_VCH_CHK();
            B040_IA_POST();
        }
    }
    public void B010_GET_REENTRY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCBSTS);
        SCCCBSTS.PROC_NAME = K_PROC_END;
        CEP.TRC(SCCGWA, SCCCBSTS);
        S000_CALL_SCZCBSTS();
        CEP.TRC(SCCGWA, SCCCBSTS);
        if (SCCCBSTS.PROC_STUS != 'F') {
            WS_REENTRY_FLG = 'Y';
        } else {
            WS_REENTRY_FLG = 'N';
        }
        CEP.TRC(SCCGWA, WS_FIN_START_FLG);
        CEP.TRC(SCCGWA, WS_FIN_END_FLG);
        CEP.TRC(SCCGWA, WS_REENTRY_FLG);
    }
    public void B020_VCH_CHK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICOCKVH);
        AICOCKVH.FUNC_CD = 'A';
        AICOCKVH.VCH_PTR = GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
        AICOCKVH.ITM_PTR = WS_ITMA_PTR;
        AICOCKVH.REENTRY_FLG = WS_REENTRY_FLG;
        S000_CALL_AIZPCKVH();
    }
    public void B030_GL_POST() throws IOException,SQLException,Exception {
        R000_GET_AREA_X();
        IBS.init(SCCGWA, AICOGLPO);
        AICOGLPO.VWA_PTR = GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
        AICOGLPO.ANS_PTR = GWA_BP_AREA.ANS_AREA_PTR;
        AICOGLPO.ITM_PTR = WS_ITMA_PTR;
        AICOGLPO.REENTRY_FLG = WS_REENTRY_FLG;
        AICOGLPO.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICOGLPO.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        AICOGLPO.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
        AICOGLPO.AP_MMO = SCCGWA.COMM_AREA.AP_MMO;
        AICOGLPO.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        AICOGLPO.TR_MMO = SCCGWA.COMM_AREA.TR_MMO;
        AICOGLPO.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
        AICOGLPO.TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
        AICOGLPO.TR_BANK = SCCGWA.COMM_AREA.TR_BANK;
        AICOGLPO.TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICOGLPO.TERM_ID = SCCGWA.COMM_AREA.TERM_ID;
        AICOGLPO.CANCEL_IND = SCCGWA.COMM_AREA.CANCEL_IND;
        if (BPRVWA.BASIC_AREA.CHNL.trim().length() == 0) {
            AICOGLPO.CHNL = SCCGWA.COMM_AREA.CHNL;
        } else {
            AICOGLPO.CHNL = BPRVWA.BASIC_AREA.CHNL;
        }
        AICOGLPO.TL_ID = SCCGWA.COMM_AREA.TL_ID;
        AICOGLPO.SUP1_ID = SCCGWA.COMM_AREA.SUP1_ID;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            AICOGLPO.GEN_TYPE = 'S';
        } else {
            AICOGLPO.GEN_TYPE = 'O';
        }
        S000_CALL_AIZPGLPO();
    }
    public void B040_IA_POST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICOUMIB);
        AICOUMIB.VWA_PTR = GWA_BP_AREA.VCH_AREA.VCH_AREA_PTR;
        AICOUMIB.ITM_PTR = WS_ITMA_PTR;
        AICOUMIB.REENTRY_FLG = WS_REENTRY_FLG;
        AICOUMIB.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICOUMIB.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        AICOUMIB.SET_NO = SCCGWA.COMM_AREA.VCH_NO;
        AICOUMIB.AP_MMO = SCCGWA.COMM_AREA.AP_MMO;
        AICOUMIB.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        AICOUMIB.TR_MMO = SCCGWA.COMM_AREA.TR_MMO;
        AICOUMIB.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
        AICOUMIB.TR_TIME = SCCGWA.COMM_AREA.TR_TIME;
        AICOUMIB.TR_BK = SCCGWA.COMM_AREA.TR_BANK;
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(1-1).PARTB.TLR_BR);
        if (BPRVWA.VCH_AREA.get(1-1).PARTB.TLR_BR == 0) {
            AICOUMIB.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            AICOUMIB.TR_BR = BPRVWA.VCH_AREA.get(1-1).PARTB.TLR_BR;
        }
        AICOUMIB.TM_NO = SCCGWA.COMM_AREA.TERM_ID;
        AICOUMIB.EC_IND = SCCGWA.COMM_AREA.CANCEL_IND;
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.CHNL);
        if (BPRVWA.BASIC_AREA.CHNL.trim().length() == 0) {
            AICOUMIB.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        } else {
            AICOUMIB.CHNL_NO = BPRVWA.BASIC_AREA.CHNL;
        }
        CEP.TRC(SCCGWA, BPRVWA.VCH_AREA.get(1-1).PARTB.TLR_ID);
        if (BPRVWA.VCH_AREA.get(1-1).PARTB.TLR_ID.trim().length() == 0) {
            AICOUMIB.TR_TELLER = SCCGWA.COMM_AREA.TL_ID;
        } else {
            AICOUMIB.TR_TELLER = BPRVWA.VCH_AREA.get(1-1).PARTB.TLR_ID;
        }
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            AICOUMIB.GEN_TYPE = 'S';
        } else {
            AICOUMIB.GEN_TYPE = 'O';
        }
        S000_CALL_AIZPUMIB();
    }
    public void R000_GET_AREA_X() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRVWA.BASIC_AREA.CNT);
        BPRVWAX.BASIC_AREA.CNT = BPRVWA.BASIC_AREA.CNT;
        IBS.CLONE(SCCGWA, BPRVWA, BPRVWAX);
        CEP.TRC(SCCGWA, BPRANS.CNT);
        BPRANSX.CNT = BPRANS.CNT;
        IBS.CLONE(SCCGWA, BPRANS, BPRANSX);
        CEP.TRC(SCCGWA, AICITMA.CNT);
        AICITMX.CNT = AICITMA.CNT;
        IBS.CLONE(SCCGWA, AICITMA, AICITMX);
    }
    public void S000_CALL_SCZCBSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-CHECK-BAT-STATUS", SCCCBSTS);
        if (SCCCBSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SCCCBSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPUMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-UPD-IA", AICOUMIB);
        CEP.TRC(SCCGWA, AICOUMIB.RC);
        if (AICOUMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICOUMIB.RC);
            if (AICOUMIB.RC_INFO.trim().length() > 0) {
                WS_ERR_INFO = "AC = " + AICOUMIB.RC_INFO;
            }
            CEP.TRC(SCCGWA, WS_ERR_INFO);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPCKVH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-CHK-VCH", AICOCKVH);
        if (AICOCKVH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICOCKVH.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPGLPO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-GL-POST", AICOGLPO);
        if (AICOGLPO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICOGLPO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
