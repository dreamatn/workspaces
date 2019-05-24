package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4543 {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP411";
    String CPN_S_MAINT_ORG_REL = "BP-S-MAINT-ORG-REL  ";
    String CPN_P_INQ_BNK = "BP-P-QUERY-BANK     ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_P_INQ_ORG_REL = "BP-P-INQ-ORG-REL    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMORR BPCSMORR = new BPCSMORR();
    BPCSORRO BPCSORRO = new BPCSORRO();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQORR BPCPQORR = new BPCPQORR();
    SCCGWA SCCGWA;
    BPB4540_AWA_4540 BPB4540_AWA_4540;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4543 return!");
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
        B020_MODIFY_ORGREL_RECORD();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4540_AWA_4540.TYP.trim().length() == 0) {
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
        if (BPB4540_AWA_4540.REL_BR == 0 
            || BPB4540_AWA_4540.REL_BR == 0X00) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4540_AWA_4540.REL_BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB4540_AWA_4540.REL_BR == BPB4540_AWA_4540.BR) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_FLD_NO = BPB4540_AWA_4540.REL_BR_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = BPB4540_AWA_4540.BNK;
        S000_CALL_BPZPQBNK();
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BANK_NOTFND;
            WS_FLD_NO = BPB4540_AWA_4540.BNK_NO;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = BPB4540_AWA_4540.BNK;
        BPCPQORG.BR = BPB4540_AWA_4540.BR;
        S000_CALL_BPZPQORG();
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
            WS_FLD_NO = BPB4540_AWA_4540.BR_NO;
            S000_ERR_MSG_PROC();
        }
        if (!BPB4540_AWA_4540.TYP.equalsIgnoreCase("10")) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = BPB4540_AWA_4540.BNK;
            BPCPQORG.BR = BPB4540_AWA_4540.REL_BR;
            S000_CALL_BPZPQORG();
            if (BPCPQORG.RC.RC_CODE != 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORG_NOTFND;
                WS_FLD_NO = BPB4540_AWA_4540.REL_BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, BPCPQORR);
        BPCPQORR.BNK = BPB4540_AWA_4540.BNK;
        BPCPQORR.TYP = BPB4540_AWA_4540.TYP;
        BPCPQORR.BR = BPB4540_AWA_4540.REL_BR;
        S000_CALL_BPZPQORR();
        if (BPCPQORR.RC.RC_CODE == 0) {
            if (BPCPQORR.REL_BR == BPB4540_AWA_4540.BR) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
                WS_FLD_NO = BPB4540_AWA_4540.BR_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB4540_AWA_4540.EFF_DT > BPB4540_AWA_4540.EXP_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            WS_FLD_NO = BPB4540_AWA_4540.EFF_DT_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_MODIFY_ORGREL_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMORR);
        BPCSMORR.FUNC = 'U';
        BPCSMORR.OUTPUT_FMT = K_OUTPUT_FMT;
        R000_TRANS_DATA_PARAMETER();
        S000_CALL_BPZSMORR();
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPCSMORR.BNK = BPB4540_AWA_4540.BNK;
        BPCSMORR.TYP = BPB4540_AWA_4540.TYP;
        BPCSMORR.BR = BPB4540_AWA_4540.BR;
        BPCSMORR.REL_BR = BPB4540_AWA_4540.REL_BR;
        BPCSMORR.EFF_DT = BPB4540_AWA_4540.EFF_DT;
        BPCSMORR.EXP_DT = BPB4540_AWA_4540.EXP_DT;
        BPCSMORR.UPT_DT = SCCGWA.COMM_AREA.TR_DATE;
        BPCSMORR.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSMORR.REMARK = BPB4540_AWA_4540.REMARK;
    }
    public void S000_CALL_BPZSMORR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_MAINT_ORG_REL;
        SCCCALL.COMMAREA_PTR = BPCSMORR;
        SCCCALL.ERR_FLDNO = BPB4540_AWA_4540.BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_BNK, BPCPQBNK);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
        if (BPCPQBNK.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_BANK_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            WS_FLD_NO = BPB4540_AWA_4540.BNK_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (BPCPQORG.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORG_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            WS_FLD_NO = (short) BPB4540_AWA_4540.BR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_REL, BPCPQORR);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
        if (BPCPQORR.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORR.RC);
            WS_FLD_NO = BPB4540_AWA_4540.TYP_NO;
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
