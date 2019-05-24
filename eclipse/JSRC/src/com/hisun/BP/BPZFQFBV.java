package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFQFBV {
    int JIBS_tmp_int;
    char K_STS_NORMAL = '0';
    char K_STS_PAYOUT = '3';
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String CPN_R_MGM_TBV = "BP-R-MGM-TBV";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_CKG_CLIB = "BP-P-CKG-CLIB";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String BP_P_INQ_ORG_REL = "BP-P-INQ-ORG-REL";
    String WS_ERR_MSG = " ";
    String WS_TEMP_PLBOX_NO = " ";
    int WS_TR_BRANCH = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPCLIB BPCPCLIB = new BPCPCLIB();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPCRTBVD BPCRTBVD = new BPCRTBVD();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQORR BPCPQORR = new BPCPQORR();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCFQFBV BPCFQFBV;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, BPCFQFBV BPCFQFBV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFQFBV = BPCFQFBV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFQFBV return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        B020_CHK_BV_PARM();
        B030_CHK_BV_TLR();
        B040_CHK_PROC_O();
        B050_OUTPUT_BEGNO();
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        if (BPCFQFBV.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCFQFBV.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPCFQFBV.VB_FLG == ' ') {
            BPCFQFBV.VB_FLG = '0';
        } else {
            if ((BPCFQFBV.VB_FLG != '0' 
                && BPCFQFBV.VB_FLG != '1')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VBFLG_ERR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_CHK_BV_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCFQFBV.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (BPCFBVQU.TX_DATA.CTL_FLG == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_CTL_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPCFBVQU.TX_DATA.TYPE == '1') {
            if (BPCFQFBV.CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
                S000_ERR_MSG_PROC();
            } else {
                if (!BPCFQFBV.CCY.equalsIgnoreCase(BPCFBVQU.TX_DATA.CCY)) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
            if (BPCFQFBV.PVAL == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VAL_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
        } else {
            BPCFQFBV.PVAL = 0;
        }
    }
    public void B030_CHK_BV_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCFQFBV.TLR;
        S000_CALL_BPZFTLRQ();
        IBS.init(SCCGWA, BPCPCLIB);
        BPCPCLIB.TLR = BPCFQFBV.TLR;
        BPCPCLIB.VB_FLG = '3';
        S000_CALL_BPZPCLIB();
        if (BPCFBVQU.TX_DATA.TYPE == '0') {
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
            BPRVHPB.CUR_TLR = BPCFQFBV.TLR;
            BPRVHPB.POLL_BOX_IND = BPCFQFBV.VB_FLG;
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
            CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
            if (BPCRVHPB.RETURN_INFO == 'F') {
                WS_TEMP_PLBOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
                CEP.TRC(SCCGWA, WS_TEMP_PLBOX_NO);
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
                S000_ERR_MSG_PROC();
            }
            BPCRVHPB.INFO.FUNC = 'E';
            S000_CALL_BPZRVHPB();
        } else if (BPCFBVQU.TX_DATA.TYPE == '1') {
            if (BPCFQFBV.VB_FLG == '0') {
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
                if (BPCPCLIB.FLG == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_CASH_BOX;
                    S000_ERR_MSG_PROC();
                }
            } else {
                if (BPCPCLIB.FLG == 'N') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_CASH_VLT;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
        }
    }
    public void B040_CHK_PROC_O() throws IOException,SQLException,Exception {
        if ((BPCFBVQU.TX_DATA.OUT_FLG == '0' 
            && BPCFQFBV.VB_FLG == '1') 
            || (BPCFBVQU.TX_DATA.OUT_FLG == '1' 
            && BPCFQFBV.VB_FLG == '0')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_BVOU_MTH;
            S000_ERR_MSG_PROC();
        }
        if (BPCFBVQU.TX_DATA.USE_MODE == '1') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_MANUAL;
            S000_ERR_MSG_PROC();
        }
    }
    public void B050_OUTPUT_BEGNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTBVD);
        BPRTBVD.KEY.BR = BPCFTLRQ.INFO.TLR_BR;
        BPRTBVD.KEY.BV_CODE = BPCFQFBV.BV_CODE;
        BPRTBVD.KEY.PL_BOX_NO = WS_TEMP_PLBOX_NO;
        BPRTBVD.KEY.VALUE = BPCFQFBV.PVAL;
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BR);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.PL_BOX_NO);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
        CEP.TRC(SCCGWA, BPRTBVD.KEY.VALUE);
        BPRTBVD.KEY.STS = K_STS_NORMAL;
        IBS.init(SCCGWA, BPCRTBDB);
        BPCRTBDB.INFO.FUNC = '7';
        S000_CALL_BPZRTBDB();
        BPCRTBDB.INFO.FUNC = 'N';
        S000_CALL_BPZRTBDB();
        if (BPCRTBDB.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TBVD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPCFQFBV.BEG_NO = BPRTBVD.BEG_NO;
        BPCRTBDB.INFO.FUNC = 'E';
        S000_CALL_BPZRTBDB();
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
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
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
    public void S000_CALL_BPZPCLIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CKG_CLIB, BPCPCLIB);
        if (BPCPCLIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCLIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
