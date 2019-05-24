package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMORR {
    int JIBS_tmp_int;
    String CPN_R_MAINT_ORG_REL = "BP-R-MAINT-ORG-REL  ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "ORG RELATION INFO MAINTAIN";
    String K_CPY_BPRORGR = "BPRORGR ";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRORGR BPRORGR = new BPRORGR();
    BPRORGR BPROORGR = new BPRORGR();
    BPCSORRO BPCSORRO = new BPCSORRO();
    BPCRMORR BPCRMORR = new BPCRMORR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCSMORR BPCSMORR;
    public void MP(SCCGWA SCCGWA, BPCSMORR BPCSMORR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMORR = BPCSMORR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSMORR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMORR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSMORR.FUNC == 'I') {
            B010_QUERY_PROCESS();
        } else if (BPCSMORR.FUNC == 'A') {
            B020_CREATE_PROCESS();
        } else if (BPCSMORR.FUNC == 'U') {
            B030_MODIFY_PROCESS();
        } else if (BPCSMORR.FUNC == 'D') {
            B040_DELETE_PROCESS();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        B040_TRANS_DATA_OUTPUT();
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGR);
        IBS.init(SCCGWA, BPCRMORR);
        BPRORGR.KEY.BNK = BPCSMORR.BNK;
        BPRORGR.KEY.TYP = BPCSMORR.TYP;
        BPRORGR.KEY.BR = BPCSMORR.BR;
        BPCRMORR.INFO.FUNC = 'Q';
        BPCRMORR.INFO.POINTER = BPRORGR;
        BPCRMORR.INFO.REC_LEN = 169;
        S000_CALL_BPZRMORR();
        if (BPCRMORR.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGR);
        R000_TRANS_DATA_PARAMETER();
        IBS.init(SCCGWA, BPCRMORR);
        BPCRMORR.INFO.POINTER = BPRORGR;
        BPCRMORR.INFO.REC_LEN = 169;
        BPCRMORR.INFO.FUNC = 'C';
        S000_CALL_BPZRMORR();
        if (BPCRMORR.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
            S000_ERR_MSG_PROC();
        }
        B020_01_HISTORY_RECORD();
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = "" + BPCSMORR.BR;
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO = "0" + BPCPNHIS.INFO.REF_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRORGR;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRORGR;
        S000_CALL_BPZPNHIS();
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGR);
        BPRORGR.KEY.BNK = BPCSMORR.BNK;
        BPRORGR.KEY.TYP = BPCSMORR.TYP;
        BPRORGR.KEY.BR = BPCSMORR.BR;
        IBS.init(SCCGWA, BPCRMORR);
        BPCRMORR.INFO.POINTER = BPRORGR;
        BPCRMORR.INFO.REC_LEN = 169;
        BPCRMORR.INFO.FUNC = 'R';
        S000_CALL_BPZRMORR();
        IBS.CLONE(SCCGWA, BPRORGR, BPROORGR);
        R000_TRANS_DATA_PARAMETER();
        IBS.init(SCCGWA, BPCRMORR);
        BPCRMORR.INFO.POINTER = BPRORGR;
        BPCRMORR.INFO.REC_LEN = 169;
        BPCRMORR.INFO.FUNC = 'U';
        S000_CALL_BPZRMORR();
        B030_01_HISTORY_RECORD();
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = "" + BPCSMORR.BR;
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO = "0" + BPCPNHIS.INFO.REF_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRORGR;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROORGR;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRORGR;
        S000_CALL_BPZPNHIS();
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRORGR);
        BPRORGR.KEY.BNK = BPCSMORR.BNK;
        BPRORGR.KEY.TYP = BPCSMORR.TYP;
        BPRORGR.KEY.BR = BPCSMORR.BR;
        IBS.init(SCCGWA, BPCRMORR);
        BPCRMORR.INFO.POINTER = BPRORGR;
        BPCRMORR.INFO.REC_LEN = 169;
        BPCRMORR.INFO.FUNC = 'R';
        S000_CALL_BPZRMORR();
        IBS.CLONE(SCCGWA, BPRORGR, BPROORGR);
        B040_01_HISTORY_RECORD();
        IBS.init(SCCGWA, BPCRMORR.RC);
        BPCRMORR.INFO.FUNC = 'D';
        S000_CALL_BPZRMORR();
    }
    public void B040_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = "" + BPCSMORR.BR;
        JIBS_tmp_int = BPCPNHIS.INFO.REF_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPNHIS.INFO.REF_NO = "0" + BPCPNHIS.INFO.REF_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRORGR;
        S000_CALL_BPZPNHIS();
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R010_TRANS_DATA_OUPUT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSMORR.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCSORRO;
        SCCFMT.DATA_LEN = 154;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRORGR.KEY.BNK = BPCSMORR.BNK;
        BPRORGR.KEY.TYP = BPCSMORR.TYP;
        BPRORGR.KEY.BR = BPCSMORR.BR;
        BPRORGR.REL_BR = BPCSMORR.REL_BR;
        BPRORGR.EFF_DT = BPCSMORR.EFF_DT;
        BPRORGR.EXP_DT = BPCSMORR.EXP_DT;
        BPRORGR.UPT_DT = BPCSMORR.UPT_DT;
        BPRORGR.UPT_TLR = BPCSMORR.UPT_TLR;
        BPRORGR.REMARK = BPCSMORR.REMARK;
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCSORRO.FUNC = BPCSMORR.FUNC;
        BPCSORRO.BNK = BPRORGR.KEY.BNK;
        BPCSORRO.TYP = BPRORGR.KEY.TYP;
        BPCSORRO.BR = BPRORGR.KEY.BR;
        BPCSORRO.REL_BR = BPRORGR.REL_BR;
        BPCSORRO.EFF_DT = BPRORGR.EFF_DT;
        BPCSORRO.EXP_DT = BPRORGR.EXP_DT;
        BPCSORRO.REMARK = BPRORGR.REMARK;
    }
    public void S000_CALL_BPZRMORR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_ORG_REL, BPCRMORR);
        if (BPCRMORR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMORR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
