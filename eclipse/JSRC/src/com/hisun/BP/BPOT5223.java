package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5223 {
    String JIBS_tmp_str[] = new String[10];
    String K_MMO = "BP";
    String K_OUTPUT_FMT = "BPX01";
    String K_CALL_NAME = "BP-U-S-UOD";
    String K_CALL_BPZPQORG = "BP-P-INQ-ORG";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    short WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCOUOD BPCOUOD = new BPCOUOD();
    BPCOSUOD BPCOSUOD = new BPCOSUOD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB5220_AWA_5220 BPB5220_AWA_5220;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5223 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5220_AWA_5220>");
        BPB5220_AWA_5220 = (BPB5220_AWA_5220) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOUOD);
        IBS.init(SCCGWA, BPCOSUOD);
        IBS.init(SCCGWA, BPCPQORG);
        IBS.init(SCCGWA, SCCCKDT);
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT();
        B20_CREATE_UOD_RECORD();
    }
    public void B10_CHECK_INPUT() throws IOException,SQLException,Exception {
        S00_CALL_BPZPQORG();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORG_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND_UOD;
            WS_FLD_NO = BPB5220_AWA_5220.UOD_BR_NO;
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB5220_AWA_5220.EFF_DT;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR_UOD;
            WS_FLD_NO = BPB5220_AWA_5220.EFF_DT_NO;
            S00_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB5220_AWA_5220.EXP_DT;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERR_UOD;
            WS_FLD_NO = BPB5220_AWA_5220.EXP_DT_NO;
            S00_ERR_MSG_PROC();
        }
        if (BPB5220_AWA_5220.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_LT_ACDT_UOD;
            WS_FLD_NO = BPB5220_AWA_5220.EFF_DT_NO;
            S00_ERR_MSG_PROC();
        }
        if (BPB5220_AWA_5220.EFF_DT > BPB5220_AWA_5220.EXP_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_GT_EXPDT_UOD;
            WS_FLD_NO = BPB5220_AWA_5220.EXP_DT_NO;
            S00_ERR_MSG_PROC();
        }
        for (WS_CNT = 1; WS_CNT < 10; WS_CNT += 1) {
            if (BPB5220_AWA_5220.UOD_AMT[WS_CNT-1].UOD_LMT < 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_LMT_LESS_ZERO;
                WS_FLD_NO = BPB5220_AWA_5220.UOD_AMT[WS_CNT-1].UOD_LMT_NO;
            }
        }
    }
    public void B20_CREATE_UOD_RECORD() throws IOException,SQLException,Exception {
        R00_TRANS_DATA_PARAMETER();
        S00_CALL_BPZSUOD();
    }
    public void R00_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCOUOD.OUTPUT_FMT = K_OUTPUT_FMT;
        BPCOSUOD.KEY.UOD_BK = BPB5220_AWA_5220.UOD_BK;
        BPCOSUOD.KEY.UOD_BR = BPB5220_AWA_5220.UOD_BR;
        BPCOSUOD.VAL.EFF_DT = BPB5220_AWA_5220.EFF_DT;
        BPCOSUOD.VAL.EXP_DT = BPB5220_AWA_5220.EXP_DT;
        BPCOSUOD.VAL.UOD_AMT[1-1].UOD_LMT = BPB5220_AWA_5220.UOD_AMT[1-1].UOD_LMT;
        BPCOSUOD.VAL.UOD_AMT[2-1].UOD_LMT = BPB5220_AWA_5220.UOD_AMT[2-1].UOD_LMT;
        BPCOSUOD.VAL.UOD_AMT[3-1].UOD_LMT = BPB5220_AWA_5220.UOD_AMT[3-1].UOD_LMT;
        BPCOSUOD.VAL.UOD_AMT[4-1].UOD_LMT = BPB5220_AWA_5220.UOD_AMT[4-1].UOD_LMT;
        BPCOSUOD.VAL.UOD_AMT[5-1].UOD_LMT = BPB5220_AWA_5220.UOD_AMT[5-1].UOD_LMT;
        BPCOSUOD.VAL.UOD_AMT[6-1].UOD_LMT = BPB5220_AWA_5220.UOD_AMT[6-1].UOD_LMT;
        BPCOSUOD.VAL.UOD_AMT[7-1].UOD_LMT = BPB5220_AWA_5220.UOD_AMT[7-1].UOD_LMT;
        BPCOSUOD.VAL.UOD_AMT[8-1].UOD_LMT = BPB5220_AWA_5220.UOD_AMT[8-1].UOD_LMT;
        BPCOSUOD.VAL.UOD_AMT[9-1].UOD_LMT = BPB5220_AWA_5220.UOD_AMT[9-1].UOD_LMT;
        BPCOSUOD.VAL.UOD_AMT[10-1].UOD_LMT = BPB5220_AWA_5220.UOD_AMT[10-1].UOD_LMT;
        BPCOSUOD.UPT_DT = BPB5220_AWA_5220.UPT_DT;
        BPCOSUOD.UPT_TLR = BPB5220_AWA_5220.UPT_TLR;
        CEP.TRC(SCCGWA, BPB5220_AWA_5220.UOD_BR);
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        BPCPQORG.BR = BPB5220_AWA_5220.UOD_BR;
        IBS.CALLCPN(SCCGWA, K_CALL_BPZPQORG, BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC);
    }
    public void S00_CALL_BPZSUOD() throws IOException,SQLException,Exception {
        BPCOUOD.FUNC = 'A';
        BPCOUOD.POINTER = BPCOSUOD;
        IBS.CALLCPN(SCCGWA, K_CALL_NAME, BPCOUOD);
        if (BPCOUOD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOUOD.RC);
            S00_ERR_MSG_PROC();
        }
    }
    public void S00_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
