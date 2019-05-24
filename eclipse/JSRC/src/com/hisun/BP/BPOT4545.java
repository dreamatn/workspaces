package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4545 {
    String K_OUTPUT_FMT = "BP411";
    String CPN_S_MAINT_ORG_REL = "BP-S-MAINT-ORG-REL  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMORR BPCSMORR = new BPCSMORR();
    SCCGWA SCCGWA;
    BPB4540_AWA_4540 BPB4540_AWA_4540;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4545 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4540_AWA_4540>");
        BPB4540_AWA_4540 = (BPB4540_AWA_4540) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_QUERY_ORG_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4540_AWA_4540.BNK.equalsIgnoreCase("0") 
            || BPB4540_AWA_4540.BNK.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4540_AWA_4540.BNK_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4540_AWA_4540.TYP.trim().length() == 0 
            || BPB4540_AWA_4540.TYP.charAt(0) == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4540_AWA_4540.TYP_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4540_AWA_4540.BR == 0 
            || BPB4540_AWA_4540.BR == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4540_AWA_4540.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_ORG_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMORR);
        BPCSMORR.FUNC = 'I';
        BPCSMORR.BNK = BPB4540_AWA_4540.BNK;
        BPCSMORR.TYP = BPB4540_AWA_4540.TYP;
        BPCSMORR.BR = BPB4540_AWA_4540.BR;
        BPCSMORR.OUTPUT_FMT = K_OUTPUT_FMT;
        CEP.TRC(SCCGWA, BPCSMORR);
        S000_CALL_BPZSMORR();
    }
    public void S000_CALL_BPZSMORR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_MAINT_ORG_REL;
        SCCCALL.COMMAREA_PTR = BPCSMORR;
        SCCCALL.ERR_FLDNO = BPB4540_AWA_4540.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSMORR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSMORR.RC);
            WS_FLD_NO = BPB4540_AWA_4540.BR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
