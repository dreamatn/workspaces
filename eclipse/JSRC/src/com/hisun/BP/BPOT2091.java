package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2091 {
    int JIBS_tmp_int;
    DBParm BPTTLVB_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP225";
    String CPN_S_CASAPP_MAINTAIN = "BP-S-CASAPP-MAINTAIN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    int K_CNT = 5;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_APP_UPBR = 0;
    int WS_UP_UPBR = 0;
    char WS_APP_LVL = ' ';
    char WS_UP_LVL = ' ';
    int WS_I = 0;
    int WS_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCSARAP BPCSARAP = new BPCSARAP();
    SCCGWA SCCGWA;
    BPB2090_AWA_2090 BPB2090_AWA_2090;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2091 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2090_AWA_2090>");
        BPB2090_AWA_2090 = (BPB2090_AWA_2090) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CREATE_RECOED();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR);
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TX_LVL == '0') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_DIRECTOR_TLR);
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_HAS_NO_AUTH);
        }
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, "NCB1234");
        CEP.TRC(SCCGWA, BPRTLVB.KEY.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        T000_READ_BPTTLVB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        CEP.TRC(SCCGWA, BPRTLVB.KEY.PLBOX_NO);
        if (BPRTLVB.PLBOX_TP == '1' 
            || BPRTLVB.PLBOX_TP == '2' 
            || BPRTLVB.PLBOX_TP == '5') {
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR122);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB2090_AWA_2090.APP_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_APP_UPBR = BPCPQORG.SUPR_BR;
        WS_APP_LVL = BPCPQORG.LVL;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB2090_AWA_2090.UP_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_UP_UPBR = BPCPQORG.SUPR_BR;
        WS_UP_LVL = BPCPQORG.LVL;
        CEP.TRC(SCCGWA, BPB2090_AWA_2090.APP_BR);
        CEP.TRC(SCCGWA, BPB2090_AWA_2090.UP_BR);
        CEP.TRC(SCCGWA, WS_APP_UPBR);
        CEP.TRC(SCCGWA, WS_UP_UPBR);
        CEP.TRC(SCCGWA, WS_UP_LVL);
        CEP.TRC(SCCGWA, WS_APP_LVL);
        if (WS_APP_UPBR != WS_UP_UPBR 
            || WS_UP_LVL <= WS_APP_LVL) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOT_BELONG_UPBR);
        }
    }
    public void B020_CREATE_RECOED() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSARAP);
        BPCSARAP.INFO.FUNC = 'A';
        BPCSARAP.APP_BR = BPB2090_AWA_2090.APP_BR;
        BPCSARAP.UP_BR = BPB2090_AWA_2090.UP_BR;
        BPCSARAP.APP_DT = BPB2090_AWA_2090.APP_DT;
        CEP.TRC(SCCGWA, BPCSARAP.UP_BR);
        CEP.TRC(SCCGWA, BPCSARAP.APP_STS);
        CEP.TRC(SCCGWA, BPCSARAP.UP_BR);
        for (WS_I = 1; WS_I <= K_CNT; WS_I += 1) {
            BPCSARAP.APP_INFO[WS_I-1].APP_CCY = BPB2090_AWA_2090.APP_INFO[WS_I-1].APP_CCY;
            BPCSARAP.APP_INFO[WS_I-1].APP_AMT = BPB2090_AWA_2090.APP_INFO[WS_I-1].APP_AMT;
        }
        S000_CALL_BPZSARAP();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND ( CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR )";
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR123);
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLVB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.FX_BUSI);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZSARAP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CASAPP_MAINTAIN, BPCSARAP);
        if (BPCSARAP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSARAP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
