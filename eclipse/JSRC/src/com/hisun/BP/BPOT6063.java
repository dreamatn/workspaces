package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6063 {
    char K_ERROR = 'E';
    BPOT6063_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT6063_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSMPDT BPCSMPDT = new BPCSMPDT();
    BPCPQPDT BPCPQPDT = new BPCPQPDT();
    SCCGWA SCCGWA;
    BPB6060_AWA_6060 BPB6060_AWA_6060;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT6063 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6060_AWA_6060>");
        BPB6060_AWA_6060 = (BPB6060_AWA_6060) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_ADD_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB6060_AWA_6060.PRDT_TYP.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_MUST_INPUT_PRD_TYPE;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, BPCPQPDT);
        BPCPQPDT.PRDT_TYPE = BPB6060_AWA_6060.PRDT_TYP;
        S000_CALL_BPZPQPDT();
        if (BPCPQPDT.RC.RC_CODE == 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_PRD_TYPE_EXIST;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB6060_AWA_6060.PRDT_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB6060_AWA_6060.BOTTOM_I != 'B' 
            && BPB6060_AWA_6060.BOTTOM_I != 'M' 
            && BPB6060_AWA_6060.BOTTOM_I != 'T') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB6060_AWA_6060.BOTTOM_I_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB6060_AWA_6060.BOTTOM_I != 'T' 
            && BPB6060_AWA_6060.SUPR_TYP.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_MUST_INPUT_SUPR_TYPE;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB6060_AWA_6060.SUPR_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB6060_AWA_6060.BOTTOM_I == 'T' 
            && BPB6060_AWA_6060.SUPR_TYP.trim().length() > 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB6060_AWA_6060.BOTTOM_I_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB6060_AWA_6060.SUPR_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQPDT);
            BPCPQPDT.PRDT_TYPE = BPB6060_AWA_6060.SUPR_TYP;
            S000_CALL_BPZPQPDT();
            if (BPCPQPDT.RC.RC_CODE != 0) {
                WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_SUPR_TYPE_NOTFND;
                WS_TEMP_VARIABLE.WS_FLD_NO = BPB6060_AWA_6060.SUPR_TYP_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        R00_CHECK_ERROR();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.PROD_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.PRDT_FUNC_CTRL_IND);
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_ADD_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB6060_AWA_6060.PRDT_TYP);
        CEP.TRC(SCCGWA, BPB6060_AWA_6060.BOTTOM_I);
        CEP.TRC(SCCGWA, BPB6060_AWA_6060.SUPR_TYP);
        CEP.TRC(SCCGWA, BPB6060_AWA_6060.DESC);
        CEP.TRC(SCCGWA, BPB6060_AWA_6060.DESCENG);
        IBS.init(SCCGWA, BPCSMPDT);
        BPCSMPDT.I_FUNC = 'A';
        BPCSMPDT.PRDT_TYPE = BPB6060_AWA_6060.PRDT_TYP;
        CEP.TRC(SCCGWA, BPB6060_AWA_6060.PRDT_TYP);
        BPCSMPDT.BOTTOM_IND = BPB6060_AWA_6060.BOTTOM_I;
        CEP.TRC(SCCGWA, BPB6060_AWA_6060.BOTTOM_I);
        BPCSMPDT.SUPR_TYPE = BPB6060_AWA_6060.SUPR_TYP;
        CEP.TRC(SCCGWA, BPB6060_AWA_6060.SUPR_TYP);
        BPCSMPDT.DESC = BPB6060_AWA_6060.DESC;
        CEP.TRC(SCCGWA, BPB6060_AWA_6060.DESC);
        BPCSMPDT.DESC_ENG = BPB6060_AWA_6060.DESCENG;
        S000_CALL_BPZSMPDT();
    }
    public void S000_CALL_BPZSMPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-PRD-TYPE", BPCSMPDT);
    }
    public void S000_CALL_BPZPQPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-TYPE", BPCPQPDT);
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
