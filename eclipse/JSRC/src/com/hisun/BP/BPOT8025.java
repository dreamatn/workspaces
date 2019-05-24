package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8025 {
    String K_MMO = "BP";
    String K_OUTPUT_FMT = "BP806";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_CALL_BPZSFLTE = "BP-SET-FLT-EXG-FLG ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSFLTE BPCSFLTE = new BPCSFLTE();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB8025_AWA_8025 BPB8025_AWA_8025;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8025 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8025_AWA_8025>");
        BPB8025_AWA_8025 = (BPB8025_AWA_8025) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCSFLTE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_AWA_DATA();
        B020_TRANS_BPCSFLTE();
        S000_CALL_BPZSFLTE();
    }
    public void B010_CHECK_AWA_DATA() throws IOException,SQLException,Exception {
        if (BPB8025_AWA_8025.FUNC != 'A' 
            || BPB8025_AWA_8025.FUNC != 'M' 
            || BPB8025_AWA_8025.FUNC != 'I') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_FUNC_ERR;
            WS_FLD_NO = BPB8025_AWA_8025.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB8025_AWA_8025.END_FLG != 'Y' 
            || BPB8025_AWA_8025.END_FLG != 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB8025_AWA_8025.END_FLG_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB8025_AWA_8025.EFF_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EFF_DATE_ERR;
            WS_FLD_NO = BPB8025_AWA_8025.EFF_DT_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB8025_AWA_8025.EXP_DT < BPB8025_AWA_8025.EFF_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
            WS_FLD_NO = BPB8025_AWA_8025.EXP_DT_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB8025_AWA_8025.EFF_DT;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
            WS_FLD_NO = BPB8025_AWA_8025.EXP_DT_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPB8025_AWA_8025.EXP_DT;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, SCCCKDT.RC);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_EXP_DATE_ERR;
            WS_FLD_NO = BPB8025_AWA_8025.EXP_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_TRANS_BPCSFLTE() throws IOException,SQLException,Exception {
        BPCSFLTE.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCSFLTE.OUTPUT_FMT);
        BPCSFLTE.FUNC = BPB8025_AWA_8025.FUNC;
        BPCSFLTE.VAL.PARM_TYP = BPB8025_AWA_8025.PARM_TYP;
        BPCSFLTE.VAL.BNK = BPB8025_AWA_8025.BNK;
        BPCSFLTE.VAL.DESC = BPB8025_AWA_8025.DESC;
        BPCSFLTE.VAL.CDESC = BPB8025_AWA_8025.CDESC;
        BPCSFLTE.VAL.EFF_DT = BPB8025_AWA_8025.EFF_DT;
        BPCSFLTE.VAL.EXP_DT = BPB8025_AWA_8025.EXP_DT;
        BPCSFLTE.VAL.END_FLG = BPB8025_AWA_8025.END_FLG;
        BPCSFLTE.VAL.INIT_FLG = BPB8025_AWA_8025.INIT_FLG;
        BPCSFLTE.VAL.INIT_DT = BPB8025_AWA_8025.INIT_DT;
    }
    public void S000_CALL_BPZSFLTE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZSFLTE, BPCSFLTE);
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
