package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1303 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    BPOT1303_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1303_WS_TEMP_VARIABLE();
    String WS_BCH = " ";
    BPOT1303_WS_COND_FLG WS_COND_FLG = new BPOT1303_WS_COND_FLG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSHSEQ BPCSHSEQ = new BPCSHSEQ();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    SCCGWA SCCGWA;
    BPB1310_AWA_1310 BPB1310_AWA_1310;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1303 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1310_AWA_1310>");
        BPB1310_AWA_1310 = (BPB1310_AWA_1310) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, WS_COND_FLG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        B007_BRW_PTR_PROC();
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.TYPE);
        CEP.TRC(SCCGWA, BPB1310_AWA_1310.CODE);
        IBS.init(SCCGWA, BPCPQBCH);
        BPCPQBCH.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQBCH.ACCT = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, BPCPQBCH.BNK);
        CEP.TRC(SCCGWA, BPCPQBCH.ACCT);
        S000_CALL_BPZPQBCH();
        if (BPB1310_AWA_1310.CODE == null) BPB1310_AWA_1310.CODE = "";
        JIBS_tmp_int = BPB1310_AWA_1310.CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPB1310_AWA_1310.CODE += " ";
        WS_BCH = BPB1310_AWA_1310.CODE.substring(0, 3);
        CEP.TRC(SCCGWA, BPCPQBCH.BCH);
        CEP.TRC(SCCGWA, WS_BCH);
        if (!BPCPQBCH.BCH.equalsIgnoreCase(WS_BCH)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CANT_RES_OTH_BR_CI, WS_TEMP_VARIABLE.WS_MSGID);
        }
    }
    public void B007_BRW_PTR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSHSEQ);
        BPCSHSEQ.TYPE = BPB1310_AWA_1310.TYPE;
        BPCSHSEQ.CODE = BPB1310_AWA_1310.CODE;
        BPCSHSEQ.AC_OFFICER = BPB1310_AWA_1310.AC_OFC;
        BPCSHSEQ.AC_ACCT = BPB1310_AWA_1310.ACCT;
        CEP.TRC(SCCGWA, BPCSHSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSHSEQ.CODE);
        CEP.TRC(SCCGWA, BPCSHSEQ.AC_OFFICER);
        BPCSHSEQ.FUNC_CODE = 'B';
        S000_CALL_BPZSHSEQ();
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
        CEP.TRC(SCCGWA, BPCPQBCH.RC);
        if (BPCPQBCH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBCH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSHSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-HSEQ-MAINT", BPCSHSEQ);
        if (BPCSHSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSHSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
