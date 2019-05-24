package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT3940 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP185";
    String K_BRANCH = "11";
    String CPN_S_BVRG_MAINTAIN = "BP-S-BVRG-MAINTAIN";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_IDX = 0;
    int WS_J = 0;
    int WS_BR = 0;
    int WS_BR3 = 0;
    int WS_BR4 = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    int WS_K = 0;
    int WS_L = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOLIBM BPCOLIBM = new BPCOLIBM();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCSBVRG BPCSBVRG = new BPCSBVRG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPB3940_AWA_3940 BPB3940_AWA_3940;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT3940 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB3940_AWA_3940>");
        BPB3940_AWA_3940 = (BPB3940_AWA_3940) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3940_AWA_3940.FUNC);
        CEP.TRC(SCCGWA, BPB3940_AWA_3940.BV_CODE);
        CEP.TRC(SCCGWA, BPB3940_AWA_3940.BR_INFO[1-1].BR);
        B010_CHECK_INPUT();
        B020_DEL_BPTBVRG();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB3940_AWA_3940.FUNC);
        WS_FUNC_FLG = BPB3940_AWA_3940.FUNC;
        if (WS_FUNC_FLG != 'A') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB3940_AWA_3940.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB3940_AWA_3940.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            WS_FLD_NO = BPB3940_AWA_3940.BV_CODE_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPB3940_AWA_3940.BV_CODE;
            S000_CALL_BPZFBVQU();
            if (BPCFBVQU.TX_DATA.BV_RANGE == '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_NOT_FEAT;
                WS_FLD_NO = BPB3940_AWA_3940.BV_CODE_NO;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, "123");
        if (BPB3940_AWA_3940.BR_INFO[1-1].BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB3940_AWA_3940.BR_INFO[1-1].BR_NO;
            S000_ERR_MSG_PROC();
        }
        for (WS_I = 1; WS_I <= 30 
            && BPB3940_AWA_3940.BR_INFO[WS_I-1].BR != 0; WS_I += 1) {
        }
        for (WS_K = WS_I; WS_K <= 30; WS_K += 1) {
            CEP.TRC(SCCGWA, "345");
            CEP.TRC(SCCGWA, WS_K);
            if (BPB3940_AWA_3940.BR_INFO[WS_K-1].BR != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                WS_FLD_NO = BPB3940_AWA_3940.BR_INFO[WS_K-1].BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        for (WS_J = 1; WS_J <= 30 
            && BPB3940_AWA_3940.BR_INFO[WS_J-1].BR != 0; WS_J += 1) {
            CEP.TRC(SCCGWA, BPB3940_AWA_3940.BR_INFO[WS_J-1].BR);
            CEP.TRC(SCCGWA, WS_J);
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPB3940_AWA_3940.BR_INFO[WS_J-1].BR;
            S000_CALL_BPZPQORG();
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
                WS_FLD_NO = BPB3940_AWA_3940.BR_INFO[WS_J-1].BR_NO;
                S000_ERR_MSG_PROC();
            }
            if (!BPCPQORG.TYP.equalsIgnoreCase(K_BRANCH)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_SUBBR;
                WS_FLD_NO = BPB3940_AWA_3940.BR_INFO[WS_J-1].BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B020_DEL_BPTBVRG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSBVRG);
        BPCSBVRG.FUNC = 'A';
        BPCSBVRG.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSBVRG();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSBVRG.FUNC = BPB3940_AWA_3940.FUNC;
        BPCSBVRG.BV_CODE = BPB3940_AWA_3940.BV_CODE;
        for (WS_J = 1; WS_J <= 30 
            && BPB3940_AWA_3940.BR_INFO[WS_J-1].BR != ' '; WS_J += 1) {
            BPCSBVRG.BR_INFO[WS_J-1].BR = BPB3940_AWA_3940.BR_INFO[WS_J-1].BR;
        }
        CEP.TRC(SCCGWA, BPCSBVRG.FUNC);
        CEP.TRC(SCCGWA, BPCSBVRG.BV_CODE);
        CEP.TRC(SCCGWA, BPCSBVRG.BR_INFO[1-1].BR);
    }
    public void S000_CALL_BPZSBVRG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_BVRG_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSBVRG;
        SCCCALL.ERR_FLDNO = BPB3940_AWA_3940.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSBVRG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSBVRG.RC);
            WS_FLD_NO = BPB3940_AWA_3940.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
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