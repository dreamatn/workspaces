package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4992 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_TLR_MAINTAIN = "BP-S-TLR-MAINTAIN   ";
    String CPN_S_AMTL_MAINTAIN = "BP-S-AMTL-MAINTAIN  ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_R_BRE_CLIB = "BP-R-BRE-CLIB       ";
    String CPN_R_MAINT_CLIB = "BP-R-ADW-CLIB";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPCSTLRM BPCSTLRM = new BPCSTLRM();
    BPCSAMTL BPCSAMTL = new BPCSAMTL();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCTLIBB BPCTLIBB = new BPCTLIBB();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    SCCGWA SCCGWA;
    BPB4900_AWA_4900 BPB4900_AWA_4900;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4992 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4900_AWA_4900>");
        BPB4900_AWA_4900 = (BPB4900_AWA_4900) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSAMTL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_DELETE_TLR_RECORD();
        B030_DELETE_TLR_MACH();
        B040_DELETE_CASH_BOX();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4900_AWA_4900.TLR.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BANK_NOTFND;
            WS_FLD_NO = BPB4900_AWA_4900.TLR_NO;
            S000_ERR_MSG_PROC();
        }
        BPCSAMTL.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSAMTL.MACH_NO = BPB4900_AWA_4900.TLR;
        BPCSAMTL.FUNC = 'I';
        S000_CALL_BPZSAMTL();
        if (BPB4900_AWA_4900.STAFNO.trim().length() == 0) {
        }
    }
    public void B020_DELETE_TLR_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTLRM);
        BPCSTLRM.TLR = BPCSAMTL.TLR_NO;
        BPCSTLRM.FUNC = 'D';
        S000_CALL_BPZSTLRM();
    }
    public void B030_DELETE_TLR_MACH() throws IOException,SQLException,Exception {
        BPCSAMTL.FUNC = 'D';
        S000_CALL_BPZSAMTL();
    }
    public void B040_DELETE_CASH_BOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        IBS.init(SCCGWA, BPCTLIBB);
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPRCLIB);
        BPRTLVB.PLBOX_TP = '4';
        BPRTLVB.CRNT_TLR = BPCSAMTL.TLR_NO;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        BPCTLVBF.INFO.FUNC = 'R';
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'F') {
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            BPCTLVBF.INFO.FUNC = 'D';
            S000_CALL_BPZTLVBF();
        }
        BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
        BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPCTLIBB.INFO.FUNC = '1';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        BPCTLIBB.INFO.FUNC = 'N';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND)) {
            CEP.TRC(SCCGWA, BPRCLIB.KEY.BR);
            CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
            BPCTLIBF.INFO.FUNC = 'R';
            BPCTLIBF.POINTER = BPRCLIB;
            BPCTLIBF.LEN = 352;
            S000_CALL_BPZTLIBF();
            BPCTLIBF.INFO.FUNC = 'D';
            BPCTLIBF.POINTER = BPRCLIB;
            BPCTLIBF.LEN = 352;
            S000_CALL_BPZTLIBF();
            BPCTLIBB.INFO.FUNC = 'N';
            BPCTLIBB.POINTER = BPRCLIB;
            BPCTLIBB.LEN = 352;
            S000_CALL_BPZTLIBB();
        }
        BPCTLIBB.INFO.FUNC = 'E';
        BPCTLIBB.POINTER = BPRCLIB;
        BPCTLIBB.LEN = 352;
        S000_CALL_BPZTLIBB();
    }
    public void S000_CALL_BPZSTLRM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_TLR_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSTLRM;
        SCCCALL.ERR_FLDNO = BPB4900_AWA_4900.TLR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZSAMTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_AMTL_MAINTAIN;
        SCCCALL.COMMAREA_PTR = BPCSAMTL;
        SCCCALL.ERR_FLDNO = BPB4900_AWA_4900.FUNC_NO;
        IBS.CALL(SCCGWA, SCCCALL);
        if (BPCSAMTL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSAMTL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRE_CLIB, BPCTLIBB);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLIB_NOTFND) 
            && BPCTLIBB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLIBB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLIB, BPCTLIBF);
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
