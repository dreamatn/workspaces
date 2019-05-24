package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6002 {
    char K_ERROR = 'E';
    BPOT6002_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT6002_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRTRT BPRTRT = new BPRTRT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCSMPDM BPCSMPDM = new BPCSMPDM();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    BPB6000_AWA_6000 BPB6000_AWA_6000;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT6002 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6000_AWA_6000>");
        BPB6000_AWA_6000 = (BPB6000_AWA_6000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPQPDM);
        IBS.init(SCCGWA, BPCPQPRD);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_ADD_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.PRDT_MOD);
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.ENG_DESC);
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.CHN_DESC);
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.INPD_IND);
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.PARM_TX);
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.DESC);
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.EFF_DT);
        if (BPB6000_AWA_6000.PRDT_MOD.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_MODEL);
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_MODEL;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB6000_AWA_6000.PRDT_MOD_NO;
            S000_ERR_MSG_PROC();
        } else {
            BPCPQPDM.PRDT_MODEL = BPB6000_AWA_6000.PRDT_MOD;
            BPCPQPDM.EFF_DT = BPB6000_AWA_6000.EFF_DT;
            S000_CALL_BPZPQPDM();
            CEP.TRC(SCCGWA, BPB6000_AWA_6000.PRDT_MOD);
            if (BPCPQPDM.RC.RC_CODE == 0) {
                WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_PRD_MODEL_EXIST;
                WS_TEMP_VARIABLE.WS_FLD_NO = BPB6000_AWA_6000.PRDT_MOD_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB6000_AWA_6000.INPD_IND == 'N' 
            && BPB6000_AWA_6000.DEFT_PRD.trim().length() > 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_DEFT_PRDT_MUST_SPACE;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB6000_AWA_6000.DEFT_PRD_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPDM);
        BPCSMPDM.I_FUNC = 'A';
        BPCSMPDM.PRDT_MODEL = BPB6000_AWA_6000.PRDT_MOD;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.PRDT_MOD);
        BPCSMPDM.CONTRACT_GROUP = BPB6000_AWA_6000.CONT_G;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.CONT_G);
        BPCSMPDM.ENG_DESC = BPB6000_AWA_6000.ENG_DESC;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.ENG_DESC);
        BPCSMPDM.CHN_DESC = BPB6000_AWA_6000.CHN_DESC;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.CHN_DESC);
        BPCSMPDM.EFF_DT = BPB6000_AWA_6000.EFF_DT;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.EFF_DT);
        BPCSMPDM.EXP_DT = BPB6000_AWA_6000.EXP_DT;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.EXP_DT);
        BPCSMPDM.DESC = BPB6000_AWA_6000.DESC;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.DESC);
        BPCSMPDM.PARM_TX = BPB6000_AWA_6000.PARM_TX;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.PARM_TX);
        BPCSMPDM.CREATE_TX = BPB6000_AWA_6000.CREATE_T;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.CREATE_T);
        BPCSMPDM.INNER_PRDT_IND = BPB6000_AWA_6000.INPD_IND;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.INPD_IND);
        BPCSMPDM.DEFT_PRDT = BPB6000_AWA_6000.DEFT_PRD;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.DEFT_PRD);
        BPCSMPDM.MODEL_TYP = BPB6000_AWA_6000.MOD_TYP;
        CEP.TRC(SCCGWA, BPB6000_AWA_6000.MOD_TYP);
        S000_CALL_BPZSMPDM();
    }
    public void S000_CALL_BPZSMPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-PRD-MODEL", BPCSMPDM);
    }
    public void S000_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-MODEL", BPCPQPDM);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}