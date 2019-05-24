package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTBVC {
    String REC_NHIS = "BP-REC-NHIS         ";
    String R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    int MAX_PAR_CNT = 99;
    BPZSTBVC_WS_VARIABLES WS_VARIABLES = new BPZSTBVC_WS_VARIABLES();
    BPZSTBVC_WS_HIS_REMARKS WS_HIS_REMARKS = new BPZSTBVC_WS_HIS_REMARKS();
    BPZSTBVC_WS_HIS_REMARKS_CN WS_HIS_REMARKS_CN = new BPZSTBVC_WS_HIS_REMARKS_CN();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCO168 BPCO168 = new BPCO168();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCSTBVC BPCSTBVC;
    public void MP(SCCGWA SCCGWA, BPCSTBVC BPCSTBVC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTBVC = BPCSTBVC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSTBVC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B100_CHK_INPUT();
            B020_CALL_BPZRVHPB_CN();
            B040_REC_NHIS_CN();
            B090_DATA_OUTPUT_CN();
        } else {
            B100_CHK_INPUT();
            B020_CALL_BPZRVHPB();
            B040_REC_NHIS();
            B090_DATA_OUTPUT();
        }
    }
    public void B100_CHK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.POLL_BOX_IND = BPCSTBVC.VB_FLG;
        BPRVHPB.RELATE_FLG = 'Y';
        BPRVHPB.STS = 'N';
        BPCRVHPB.INFO.FUNC = 'F';
        S000_CALL_BPZRVHPB();
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        if (BPCRVHPB.RETURN_INFO == 'F') {
            WS_VARIABLES.TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            CEP.TRC(SCCGWA, WS_VARIABLES.TEMP_PLBOX_NO);
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_NOT_BV_TLR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.KEY.POOL_BOX_NO = WS_VARIABLES.TEMP_PLBOX_NO;
        BPCRVHPB.INFO.FUNC = 'R';
        S000_CALL_BPZRVHPB();
        IBS.init(SCCGWA, BPCRVHPB);
        if (BPRVHPB.BV_CHK_FLG == 'Y' 
            && BPRVHPB.BL_CHK_FLG == 'Y') {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_TLR_HAS_CLOSE_BALANC;
        }
        BPRVHPB.BV_CHK_FLG = 'Y';
        BPRVHPB.BL_CHK_FLG = 'Y';
        BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRVHPB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRVHPB.INFO.FUNC = 'U';
        S000_CALL_BPZRVHPB();
    }
    public void B040_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, WS_HIS_REMARKS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "01";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REMARKS.HIS_VB_FLG = BPCSTBVC.VB_FLG;
        WS_HIS_REMARKS.HIS_BV_CODE1 = BPCSTBVC.INFO[1-1].BV_CODE;
        if (BPCSTBVC.INFO[1-1].BV_STS == ' ') WS_HIS_REMARKS.HIS_STS1 = 0;
        else WS_HIS_REMARKS.HIS_STS1 = Double.parseDouble(""+BPCSTBVC.INFO[1-1].BV_STS);
        WS_HIS_REMARKS.HIS_NUM1 = BPCSTBVC.INFO[1-1].NUM;
        WS_HIS_REMARKS.HIS_BV_CODE2 = BPCSTBVC.INFO[2-1].BV_CODE;
        if (BPCSTBVC.INFO[2-1].BV_STS == ' ') WS_HIS_REMARKS.HIS_STS2 = 0;
        else WS_HIS_REMARKS.HIS_STS2 = Double.parseDouble(""+BPCSTBVC.INFO[2-1].BV_STS);
        WS_HIS_REMARKS.HIS_NUM2 = BPCSTBVC.INFO[2-1].NUM;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HIS_REMARKS);
        S000_CALL_BPZPNHIS();
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO168);
        BPCO168.VB_FLG = BPCSTBVC.VB_FLG;
        for (WS_VARIABLES.I = 1; BPCSTBVC.INFO[WS_VARIABLES.I-1].BV_CODE.trim().length() != 0 
            && WS_VARIABLES.I <= MAX_PAR_CNT; WS_VARIABLES.I += 1) {
            BPCO168.INFO[WS_VARIABLES.I-1].BV_CODE = BPCSTBVC.INFO[WS_VARIABLES.I-1].BV_CODE;
            BPCO168.INFO[WS_VARIABLES.I-1].BV_STS = BPCSTBVC.INFO[WS_VARIABLES.I-1].BV_STS;
            BPCO168.INFO[WS_VARIABLES.I-1].NUM = BPCSTBVC.INFO[WS_VARIABLES.I-1].NUM;
            BPCO168.CNT = WS_VARIABLES.I;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSTBVC.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO168;
        SCCFMT.DATA_LEN = 2688;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_CALL_BPZRVHPB_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (BPCSTBVC.REP_TLR.trim().length() == 0) {
            BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            BPRVHPB.CUR_TLR = BPCSTBVC.REP_TLR;
        }
        BPRVHPB.POLL_BOX_IND = BPCSTBVC.VB_FLG;
        BPRVHPB.RELATE_FLG = 'Y';
        BPRVHPB.STS = 'N';
        BPCRVHPB.INFO.FUNC = 'L';
        S000_CALL_BPZRVHPB();
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        if (BPCRVHPB.RETURN_INFO == 'F') {
            WS_VARIABLES.TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
            CEP.TRC(SCCGWA, WS_VARIABLES.TEMP_PLBOX_NO);
        } else {
            if (BPCSTBVC.VB_FLG == '0') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_TLR_HVNT_BVB;
            } else {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_TLR_HVNT_BVP;
            }
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCSTBVC.CK_TYP);
        if (BPCSTBVC.CK_TYP == '2') {
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.KEY.POOL_BOX_NO = WS_VARIABLES.TEMP_PLBOX_NO;
            BPCRVHPB.INFO.FUNC = 'R';
            S000_CALL_BPZRVHPB();
            IBS.init(SCCGWA, BPCRVHPB);
            if (BPRVHPB.BV_CHK_FLG == 'Y' 
                && BPRVHPB.BL_CHK_FLG == 'Y') {
                WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_TLR_HAS_CLOSE_BALANC;
            }
            CEP.TRC(SCCGWA, BPRVHPB.BIND_TYPE);
            if (BPRVHPB.BIND_TYPE == 'N') {
                BPRVHPB.CUR_TLR = " ";
                BPRVHPB.LST_TLR = BPCSTBVC.REP_TLR;
                BPRVHPB.RELATE_FLG = 'N';
            }
            BPRVHPB.BV_CHK_FLG = 'Y';
            BPRVHPB.BL_CHK_FLG = 'Y';
            BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRVHPB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCRVHPB.INFO.FUNC = 'U';
            S000_CALL_BPZRVHPB();
        }
    }
    public void B040_REC_NHIS_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, WS_HIS_REMARKS_CN);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REMARKS_CN.HIS_VB_FLG_CN = BPCSTBVC.VB_FLG;
        WS_HIS_REMARKS_CN.HIS_BV_CODE1_CN = BPCSTBVC.INFO[1-1].BV_CODE;
        if (BPCSTBVC.INFO[1-1].BV_STS == ' ') WS_HIS_REMARKS_CN.HIS_STS1_CN = 0;
        else WS_HIS_REMARKS_CN.HIS_STS1_CN = Double.parseDouble(""+BPCSTBVC.INFO[1-1].BV_STS);
        WS_HIS_REMARKS_CN.HIS_NUM1_CN = BPCSTBVC.INFO[1-1].NUM;
        WS_HIS_REMARKS_CN.HIS_BV_CODE2_CN = BPCSTBVC.INFO[2-1].BV_CODE;
        if (BPCSTBVC.INFO[2-1].BV_STS == ' ') WS_HIS_REMARKS_CN.HIS_STS2_CN = 0;
        else WS_HIS_REMARKS_CN.HIS_STS2_CN = Double.parseDouble(""+BPCSTBVC.INFO[2-1].BV_STS);
        WS_HIS_REMARKS_CN.HIS_NUM2_CN = BPCSTBVC.INFO[2-1].NUM;
        WS_HIS_REMARKS_CN.HIS_CK_TYP = BPCSTBVC.CK_TYP;
        WS_HIS_REMARKS_CN.HIS_REP_TLR = BPCSTBVC.REP_TLR;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HIS_REMARKS_CN);
        S000_CALL_BPZPNHIS();
    }
    public void B090_DATA_OUTPUT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO168);
        BPCO168.VB_FLG = BPCSTBVC.VB_FLG;
        for (WS_VARIABLES.I = 1; BPCSTBVC.INFO[WS_VARIABLES.I-1].BV_CODE.trim().length() != 0 
            && WS_VARIABLES.I <= MAX_PAR_CNT; WS_VARIABLES.I += 1) {
            BPCO168.INFO[WS_VARIABLES.I-1].BV_CODE = BPCSTBVC.INFO[WS_VARIABLES.I-1].BV_CODE;
            BPCO168.INFO[WS_VARIABLES.I-1].BV_STS = BPCSTBVC.INFO[WS_VARIABLES.I-1].BV_STS;
            BPCO168.INFO[WS_VARIABLES.I-1].HEAD_NO = BPCSTBVC.INFO[WS_VARIABLES.I-1].HEAD_NO;
            BPCO168.INFO[WS_VARIABLES.I-1].NUM = BPCSTBVC.INFO[WS_VARIABLES.I-1].NUM;
            BPCO168.CNT = WS_VARIABLES.I;
        }
        BPCO168.CK_TYP = BPCSTBVC.CK_TYP;
        BPCO168.REP_TLR = BPCSTBVC.REP_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSTBVC.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO168;
        SCCFMT.DATA_LEN = 2688;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 740;
        IBS.CALLCPN(SCCGWA, R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
