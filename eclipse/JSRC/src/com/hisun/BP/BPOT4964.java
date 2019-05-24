package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4964 {
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    String CPN_P_INQ_BNK = "BP-P-QUERY-BANK     ";
    String CPN_MAINT_CORG = "BP-S-MAINT-CORG     ";
    String K_PARM_CONNO = "CONNO";
    String K_PARM_ORGTP = "ORGTP";
    String K_PARM_ORGRT = "ORGRT";
    char K_ERROR = 'E';
    BPOT4964_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT4964_WS_TEMP_VARIABLE();
    int WS_EFF_DATE = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    BPCSMCOG BPCSMCOG = new BPCSMCOG();
    SCCGWA SCCGWA;
    BPB4960_AWA_4960 BPB4960_AWA_4960;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT4964 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4960_AWA_4960>");
        BPB4960_AWA_4960 = (BPB4960_AWA_4960) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_DELETE_PROCESS();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        BPCPQBNK.DATA_INFO.BNK = BPB4960_AWA_4960.BANK;
        S000_CALL_BPZPQBNK();
        if (BPCPQBNK.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.BANK_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_PARM_CONNO;
        BPCOQPCD.INPUT_DATA.CODE = BPB4960_AWA_4960.CON_NO;
        S000_CALL_BPZPQPCD();
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.CON_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_PARM_ORGTP;
        BPCOQPCD.INPUT_DATA.CODE = BPB4960_AWA_4960.BRANCH_T;
        S000_CALL_BPZPQPCD();
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.BRANCH_T_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.OPER_TYP != 'M' 
            && BPB4960_AWA_4960.OPER_TYP != 'I') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.OPER_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_PARM_ORGRT;
        BPCOQPCD.INPUT_DATA.CODE = BPB4960_AWA_4960.RELA_TYP;
        S000_CALL_BPZPQPCD();
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.RELA_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        R00_CHECK_ERROR();
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMCOG);
        BPCSMCOG.I_FUNC = 'D';
        BPCSMCOG.BNK = BPB4960_AWA_4960.BANK;
        BPCSMCOG.TAB_CD = BPB4960_AWA_4960.CON_NO;
        BPCSMCOG.BR_TYP = BPB4960_AWA_4960.BRANCH_T;
        BPCSMCOG.OPT_TYP = BPB4960_AWA_4960.OPER_TYP;
        BPCSMCOG.REL_TYP = BPB4960_AWA_4960.RELA_TYP;
        BPCSMCOG.EFF_DT = BPB4960_AWA_4960.EFF_DATE;
        CEP.TRC(SCCGWA, "AAAAAAAAA");
        CEP.TRC(SCCGWA, BPCSMCOG.EFF_DT);
        S000_CALL_BPZSMCOG();
    }
    public void S000_CALL_BPZSMCOG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_MAINT_CORG, BPCSMCOG);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_BNK, BPCPQBNK);
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
