package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2904 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTLVB_RD;
    DBParm BPTVHPB_RD;
    String K_OUTPUT_FMT = "BP220";
    String CPN_S_CSBV_BOX_MAINT = "BP-S-CSBV-BOX-MAINT";
    String CPN_R_ADW_BOXP = "BP-R-ADW-BOXP";
    int K_MAX_CNT = 60;
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    int WS_I = 0;
    int WS_J = 0;
    char WS_BPTVHPB_FLG = ' ';
    char WS_BPTTLVB_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSBOXM BPCSBOXM = new BPCSBOXM();
    BPCRBOXP BPCRBOXP = new BPCRBOXP();
    BPRBOXP BPRBOXP = new BPRBOXP();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    SCCGWA SCCGWA;
    BPB2904_AWA_2904 BPB2904_AWA_2904;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2904 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2904_AWA_2904>");
        BPB2904_AWA_2904 = (BPB2904_AWA_2904) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_BPTTLVB_FLG = 'N';
        WS_BPTVHPB_FLG = 'N';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2904_AWA_2904);
        CEP.TRC(SCCGWA, BPB2904_AWA_2904.PLAN_DT);
        CEP.TRC(SCCGWA, BPB2904_AWA_2904.BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        B010_CHECK_INPUT();
        B020_UPD_CSBV_BOX_PLAN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR144);
        }
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPRVHPB);
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        T000_READ_BPTTLVB();
        T000_READ_BPTVHPB();
        CEP.TRC(SCCGWA, WS_BPTTLVB_FLG);
        CEP.TRC(SCCGWA, WS_BPTVHPB_FLG);
        if (WS_BPTTLVB_FLG == 'N' 
            && WS_BPTVHPB_FLG == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR143);
        }
        CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
        CEP.TRC(SCCGWA, WS_BPTVHPB_FLG);
        if (BPRTLVB.PLBOX_TP != '1' 
            && BPRTLVB.PLBOX_TP != '2' 
            && BPRTLVB.PLBOX_TP != '5' 
            && WS_BPTVHPB_FLG == 'N') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR143);
        }
        WS_I = 0;
        for (WS_I = 1; WS_I <= 60; WS_I += 1) {
            if (BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR.trim().length() > 0) {
                IBS.init(SCCGWA, BPCFTLRQ);
                BPCFTLRQ.INFO.TLR = BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR;
                S000_CALL_BPZFTLRQ();
                if (BPB2904_AWA_2904.PLAN_INF[WS_I-1].BOX_TYPE == '0') {
                    if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                    JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                    if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR83, BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR_NO);
                    }
                }
                if (BPB2904_AWA_2904.PLAN_INF[WS_I-1].BOX_TYPE == '1') {
                    if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
                    JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
                    for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
                    if (!BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR84, BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR_NO);
                    }
                }
            }
        }
        WS_I = 0;
        for (WS_I = 1; WS_I <= 60; WS_I += 1) {
            CEP.TRC(SCCGWA, BPB2904_AWA_2904.PLAN_INF[WS_I-1].BOX_TYPE);
            if (BPB2904_AWA_2904.PLAN_INF[WS_I-1].BOX_TYPE == '1') {
                IBS.init(SCCGWA, BPRVHPB);
                IBS.init(SCCGWA, BPCRVHPB);
                BPRVHPB.CUR_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPRVHPB.POLL_BOX_IND = '1';
                BPRVHPB.STS = 'N';
                BPCRVHPB.INFO.FUNC = 'F';
                S000_CALL_BPZRVHPB();
                if (BPCRVHPB.RETURN_INFO == 'N' 
                    && BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR.trim().length() > 0) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_HAVE_BVBOX, BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR_NO);
                }
            }
            if (BPB2904_AWA_2904.PLAN_INF[WS_I-1].BOX_TYPE == '0') {
                IBS.init(SCCGWA, BPRTLVB);
                BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_READ_BPTTLVB();
                if (WS_BPTTLVB_FLG == 'N' 
                    && BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR.trim().length() > 0 
                    && (BPRTLVB.PLBOX_TP != '1' 
                    && BPRTLVB.PLBOX_TP != '2' 
                    && BPRTLVB.PLBOX_TP != '5')) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_HAVE_CASHBOX);
                }
            }
        }
        if (BPB2904_AWA_2904.PLAN_DT == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (BPB2904_AWA_2904.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRANCE_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        for (WS_I = 2; WS_I <= K_MAX_CNT 
            && BPB2904_AWA_2904.PLAN_INF[WS_I-1].BOX_TYPE != ' '; WS_I += 1) {
            for (WS_J = 1; WS_I != WS_J; WS_J += 1) {
                if (BPB2904_AWA_2904.PLAN_INF[WS_I-1].BOX_TYPE == BPB2904_AWA_2904.PLAN_INF[WS_J-1].BOX_TYPE 
                    && BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR.equalsIgnoreCase(BPB2904_AWA_2904.PLAN_INF[WS_J-1].PLAN_TLR) 
                    && BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR.trim().length() > 0) {
                    CEP.TRC(SCCGWA, BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR);
                    CEP.TRC(SCCGWA, BPB2904_AWA_2904.PLAN_INF[WS_J-1].PLAN_TLR);
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLANTLR_DUPKEY;
                    S000_ERR_MSG_PROC();
                }
            }
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
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND CRNT_TLR = :BPRTLVB.CRNT_TLR";
        BPTTLVB_RD.upd = true;
        BPTTLVB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTTLVB_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTTLVB_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLVB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_BPTVHPB() throws IOException,SQLException,Exception {
        BPTVHPB_RD = new DBParm();
        BPTVHPB_RD.TableName = "BPTVHPB";
        BPTVHPB_RD.where = "BR = :BPRVHPB.BR "
            + "AND CUR_TLR = :BPRVHPB.CUR_TLR "
            + "AND POLL_BOX_IND = '1'";
        BPTVHPB_RD.upd = true;
        BPTVHPB_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRVHPB, this, BPTVHPB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTVHPB_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTVHPB_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTVHPB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void B020_UPD_CSBV_BOX_PLAN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBOXM);
        BPCSBOXM.PLAN_DT = BPB2904_AWA_2904.PLAN_DT;
        BPCSBOXM.BR = BPB2904_AWA_2904.BR;
        for (WS_I = 1; WS_I <= K_MAX_CNT; WS_I += 1) {
            BPCSBOXM.PLAN_INFO[WS_I-1].BOX_TYPE = BPB2904_AWA_2904.PLAN_INF[WS_I-1].BOX_TYPE;
            BPCSBOXM.PLAN_INFO[WS_I-1].BOX_NO = BPB2904_AWA_2904.PLAN_INF[WS_I-1].BOX_NO;
            BPCSBOXM.PLAN_INFO[WS_I-1].CRNT_TLR = BPB2904_AWA_2904.PLAN_INF[WS_I-1].CRNT_TLR;
            BPCSBOXM.PLAN_INFO[WS_I-1].PLAN_TLR = BPB2904_AWA_2904.PLAN_INF[WS_I-1].PLAN_TLR;
        }
        BPCSBOXM.FUNC = 'M';
        BPCSBOXM.OUTPUT_FMT = K_OUTPUT_FMT;
        S000_CALL_BPZSBOXM();
    }
    public void S000_CALL_BPZSBOXM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CSBV_BOX_MAINT, BPCSBOXM);
        if (BPCSBOXM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBOXM.RC);
            S000_ERR_MSG_PROC();
        }
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
