package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3805 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_S_TLR_BL_INQ = "BP-S-SOLD-BL-INQ ";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_TEXT = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSSBLI BPCSSBLI = new BPCSSBLI();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRGST BPCPRGST = new BPCPRGST();
    SCCGWA SCCGWA;
    BPB3800_AWA_3800 BPB3800_AWA_3800;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3805 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3800_AWA_3800>");
        BPB3800_AWA_3800 = (BPB3800_AWA_3800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BROWSE_TBVD_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB3800_AWA_3800.BR;
        S000_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB3800_AWA_3800.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3800_AWA_3800.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
        } else {
            IBS.init(SCCGWA, BPCPRGST);
            BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCPRGST.BR2 = BPB3800_AWA_3800.BR;
            S000_CALL_BPZPRGST();
            if (BPCPRGST.LVL_RELATION != 'C') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_LOW_BR;
                WS_FLD_NO = BPB3800_AWA_3800.BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB3800_AWA_3800.TLR.trim().length() == 0) {
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPB3800_AWA_3800.TLR;
            S000_CALL_BPZFTLRQ();
            if (BPCFTLRQ.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTFND;
                WS_FLD_NO = BPB3800_AWA_3800.TLR_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_BROWSE_TBVD_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3800_AWA_3800.BV_CODE);
        IBS.init(SCCGWA, BPCSSBLI);
        BPCSSBLI.FUNC = BPB3800_AWA_3800.INQ_TYP;
        BPCSSBLI.BR = BPB3800_AWA_3800.BR;
        BPCSSBLI.TLR = BPB3800_AWA_3800.TLR;
        BPCSSBLI.CODE = BPB3800_AWA_3800.BV_CODE;
        BPCSSBLI.VALUE = BPB3800_AWA_3800.VALUE;
        BPCSSBLI.HEAD_NO = BPB3800_AWA_3800.HEAD_NO;
        BPCSSBLI.NO = BPB3800_AWA_3800.BV_NO;
        BPCSSBLI.START_DT = BPB3800_AWA_3800.START_DT;
        BPCSSBLI.END_DT = BPB3800_AWA_3800.END_DT;
        if (BPCSSBLI.FUNC == '0') {
            BPCSSBLI.FUNC = '0';
        } else {
            BPCSSBLI.FUNC = '1';
        }
        S000_CALL_BPZSSBLI();
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_P_INQ_ORG_STATION;
        SCCCALL.COMMAREA_PTR = BPCPRGST;
        SCCCALL.ERR_FLDNO = BPB3800_AWA_3800.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            WS_FLD_NO = BPB3800_AWA_3800.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
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
    public void S000_CALL_BPZSSBLI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_BL_INQ, BPCSSBLI);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
