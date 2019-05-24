package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3681 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP189";
    String CPN_S_SC_TLSCQUR = "BP-S-SC-TO-SCOWQUR";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN  ";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEMP_PLBOX_NO = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCSTLSC BPCSTLSC = new BPCSTLSC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCSSCOW BPCSSCOW = new BPCSSCOW();
    SCCGWA SCCGWA;
    BPB3600_AWA_3600 BPB3600_AWA_3600;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3681 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3600_AWA_3600>");
        BPB3600_AWA_3600 = (BPB3600_AWA_3600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSTLSC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUE_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.SC_DATE);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.SC_TYPE);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.CODE_NO);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.MC_NO);
        CEP.TRC(SCCGWA, BPB3600_AWA_3600.BR);
        if (BPB3600_AWA_3600.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB3600_AWA_3600.BR;
            S000_CALL_BPZPQORG();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                WS_FLD_NO = BPB3600_AWA_3600.BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3600_AWA_3600.IN_BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB3600_AWA_3600.IN_BR;
            S000_CALL_BPZPQORG();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                WS_FLD_NO = BPB3600_AWA_3600.BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3600_AWA_3600.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
        } else {
            if (BPB3600_AWA_3600.BR != 0) {
                IBS.init(SCCGWA, BPCPRGST);
                BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCPRGST.BR2 = BPB3600_AWA_3600.BR;
                S000_CALL_BPZPRGST();
                if (BPCPRGST.LVL_RELATION == 'C') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_LOW_BR;
                    WS_FLD_NO = BPB3600_AWA_3600.BR_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        if (BPB3600_AWA_3600.TLR.trim().length() == 0) {
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB3600_AWA_3600.TLR;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
                WS_FLD_NO = BPB3600_AWA_3600.TLR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPCFTLRQ.INFO.TX_LVL == '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_DIRECTOR_TLR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUE_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSSCOW);
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSSCOW();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (BPB3600_AWA_3600.BR == 0) {
            BPCSSCOW.BR = 0;
        } else {
            BPCSSCOW.BR = BPB3600_AWA_3600.BR;
        }
        if (BPB3600_AWA_3600.TLR.trim().length() == 0) {
            BPCSSCOW.TLR = "%%%%%%%%";
        } else {
            BPCSSCOW.TLR = BPB3600_AWA_3600.TLR;
        }
        if (BPB3600_AWA_3600.MOV_STS == ' ') {
            BPCSSCOW.MOV_STS = ALL.charAt(0);
        } else {
            BPCSSCOW.MOV_STS = BPB3600_AWA_3600.MOV_STS;
        }
        BPCSSCOW.IN_BR = BPB3600_AWA_3600.BR;
    }
    public void S000_CALL_BPZSSCOW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_SC_TLSCQUR, BPCSSCOW);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = BPB3600_AWA_3600.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB3600_AWA_3600.BR_NO;
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
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
