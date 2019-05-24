package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3932 {
    int JIBS_tmp_int;
    String CPN_PL_BOX_MAIN = "BP-S-PL-BOX-MAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "BVBOX INFOMATION MAINTAIN";
    String K_CPY_BPCSVHPB = "BPCSVHPB";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSVHPB BPCSVHPB = new BPCSVHPB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB3930_AWA_3930 BPB3930_AWA_3930;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3932 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3930_AWA_3930>");
        BPB3930_AWA_3930 = (BPB3930_AWA_3930) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSVHPB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_CREATE_PLBOX();
        B030_HISTORY_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3930_AWA_3930.BR);
        CEP.TRC(SCCGWA, BPB3930_AWA_3930.FUNC);
        CEP.TRC(SCCGWA, BPB3930_AWA_3930.BIND_TYP);
        if (BPB3930_AWA_3930.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB3930_AWA_3930.BR_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = BPB3930_AWA_3930.BR;
            S000_CALL_BPZPQORG();
        }
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            && BPCPQORG.INT_BR_FLG == 'Y') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TRA_BR_NOT_CASH_BV);
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
            if (SCCGWA.COMM_AREA.TL_ID.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(SCCGWA.COMM_AREA.TL_ID);
            S000_ERR_MSG_PROC();
        }
        if (BPB3930_AWA_3930.CUR_TLR.trim().length() > 0) {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB3930_AWA_3930.CUR_TLR;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPB3930_AWA_3930.PL_IND == '0' 
                && !BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
                WS_FLD_NO = BPB3930_AWA_3930.CUR_TLR_NO;
                S000_ERR_MSG_PROC();
            }
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPB3930_AWA_3930.PL_IND == '1' 
                && !BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BVLT_TLR;
                WS_FLD_NO = BPB3930_AWA_3930.CUR_TLR_NO;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPB3930_AWA_3930.CUR_TLR);
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.NEW_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_TYP == 'S' 
                && BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("3")) {
                if (BPCFTLRQ.INFO.TLR_BR != BPB3930_AWA_3930.BR) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTMATCH_ORG;
                    S000_ERR_MSG_PROC();
                }
                if (BPB3930_AWA_3930.PL_IND != '0') {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_STSW_NOT_VB);
                }
            } else {
                if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
                    && BPCFTLRQ.INFO.SIGN_STS != 'T') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TELLER_NOT_SIGN_ON;
                    WS_FLD_NO = BPB3930_AWA_3930.CUR_TLR_NO;
                    S000_ERR_MSG_PROC();
                }
                if (BPCFTLRQ.INFO.NEW_BR != BPB3930_AWA_3930.BR) {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTMATCH_ORG;
                    S000_ERR_MSG_PROC();
                }
            }
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.CUR_TLR = BPB3930_AWA_3930.CUR_TLR;
            BPRVHPB.POLL_BOX_IND = BPB3930_AWA_3930.PL_IND;
            BPRVHPB.STS = 'N';
            BPCRVHPB.INFO.FUNC = 'F';
            S000_CALL_BPZRVHPB();
            if (BPCRVHPB.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_BVB_EXIST;
                S000_ERR_MSG_PROC();
            }
        } else {
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_TYP == 'S' 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("3")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_TLR_STSW_NOT_VB);
        }
    }
    public void B020_CREATE_PLBOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSVHPB);
        BPCSVHPB.INFO.FUNC = 'A';
        BPCSVHPB.INFO.BR = BPB3930_AWA_3930.BR;
        BPCSVHPB.INFO.PLBOX_NO = BPB3930_AWA_3930.PLBOX_NO;
        BPCSVHPB.INFO.PLBOX_NM = BPB3930_AWA_3930.PLBOX_NM;
        BPCSVHPB.INFO.PL_IND = BPB3930_AWA_3930.PL_IND;
        CEP.TRC(SCCGWA, BPB3930_AWA_3930.CUR_TLR);
        BPCSVHPB.INFO.CUR_TLR = BPB3930_AWA_3930.CUR_TLR;
        BPCSVHPB.INFO.BIND_TYP = BPB3930_AWA_3930.BIND_TYP;
        S000_CALL_BPZSVHPB();
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPCSVHPB;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 97;
        BPCPNHIS.INFO.TX_TYP_CD = "P903";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.NEW_DAT_PT = BPCSVHPB;
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSVHPB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PL_BOX_MAIN, BPCSVHPB);
        CEP.TRC(SCCGWA, BPCSVHPB.RC.RC_RTNCODE);
        if (BPCSVHPB.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSVHPB.RC);
            if (BPB3930_AWA_3930.PLBOX_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BPB3930_AWA_3930.PLBOX_NO);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG        ", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = BPB3930_AWA_3930.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_F_TLR_INF_QUERY;
        SCCCALL.COMMAREA_PTR = BPCFTLRQ;
        SCCCALL.ERR_FLDNO = BPB3930_AWA_3930.CUR_TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = BPB3930_AWA_3930.CUR_TLR_NO;
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
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
