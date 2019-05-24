package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1660 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    SMOT1660_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1660_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP16 BPCXP16 = new BPCXP16();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRTMT BPRTMT = new BPRTMT();
    SCCGWA SCCGWA;
    SMB1600_AWA_1600 SMB1600_AWA_1600;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1660 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1600_AWA_1600>");
        SMB1600_AWA_1600 = (SMB1600_AWA_1600) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        BPCPRMM.RC.RC_APP = "SM";
        BPCPRMM.DAT_PTR = BPRPRMT;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (SMB1600_AWA_1600.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1600_AWA_1600.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1600_AWA_1600.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1600_AWA_1600.FUNC == 'D') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMB1600_AWA_1600);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(133 - 1, 133 + 20 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMB1600_AWA_1600);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(153 - 1, 153 + 20 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMB1600_AWA_1600);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(156 - 1, 156 + 7 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMB1600_AWA_1600);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(153 - 1, 153 + 1 - 1));
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMB1600_AWA_1600);
        CEP.TRC(SCCGWA, JIBS_tmp_str[0].substring(165 - 1, 165 + 1 - 1));
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.TYPE);
        CEP.TRC(SCCGWA, SMB1600_AWA_1600.USE_IN);
        if (SMB1600_AWA_1600.TYPE != '0' 
            && SMB1600_AWA_1600.TYPE != '1' 
            && SMB1600_AWA_1600.TYPE != '2' 
            && SMB1600_AWA_1600.TYPE != '3') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1600_AWA_1600.TYPE_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SMB1600_AWA_1600.USE_IN != 'Y' 
            && SMB1600_AWA_1600.USE_IN != 'N') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = SMB1600_AWA_1600.USE_IN_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        R00_CHECK_ERROR();
        if (pgmRtn) return;
    }
    public void R00_CHECK_ERROR() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        BPRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        BPCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        BPRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        BPCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '2';
        BPRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        BPRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        BPCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        BPCPRMM.EXP_DT = SMB1600_AWA_1600.EXPDATE;
        BPRPRMT.DESC = SMB1600_AWA_1600.DESC;
        BPRPRMT.CDESC = SMB1600_AWA_1600.CDESC;
        IBS.init(SCCGWA, BPRTMT.DATA_TXT);
        BPRTMT.DATA_TXT.BK = SMB1600_AWA_1600.BK;
        BPRTMT.DATA_TXT.BR = SMB1600_AWA_1600.BR;
        BPRTMT.DATA_TXT.DP = SMB1600_AWA_1600.DP;
        BPRTMT.DATA_TXT.TYPE = SMB1600_AWA_1600.TYPE;
        BPRTMT.DATA_TXT.PRNT_ADDR = SMB1600_AWA_1600.PRNT_ADD;
        BPRTMT.DATA_TXT.USE_IN = SMB1600_AWA_1600.USE_IN;
        BPRTMT.DATA_TXT.TL_ID = SMB1600_AWA_1600.TL_ID;
        BPRTMT.DATA_TXT.SIGN_DATE = 0;
        BPRTMT.DATA_TXT.SIGN_TIME = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTMT.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        BPRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        BPCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '0';
        BPRPRMT.KEY.TYP = SMB1600_AWA_1600.PTYP;
        BPRPRMT.KEY.CD = SMB1600_AWA_1600.CODE;
        BPCPRMM.EFF_DT = SMB1600_AWA_1600.EFFDATE;
        BPCPRMM.EXP_DT = SMB1600_AWA_1600.EXPDATE;
        BPRPRMT.DESC = SMB1600_AWA_1600.DESC;
        BPRPRMT.CDESC = SMB1600_AWA_1600.CDESC;
        IBS.init(SCCGWA, BPRTMT.DATA_TXT);
        BPRTMT.DATA_TXT.BK = SMB1600_AWA_1600.BK;
        BPRTMT.DATA_TXT.BR = SMB1600_AWA_1600.BR;
        BPRTMT.DATA_TXT.DP = SMB1600_AWA_1600.DP;
        BPRTMT.DATA_TXT.TYPE = SMB1600_AWA_1600.TYPE;
        BPRTMT.DATA_TXT.PRNT_ADDR = SMB1600_AWA_1600.PRNT_ADD;
        BPRTMT.DATA_TXT.USE_IN = SMB1600_AWA_1600.USE_IN;
        BPRTMT.DATA_TXT.TL_ID = SMB1600_AWA_1600.TL_ID;
        BPRTMT.DATA_TXT.SIGN_DATE = 0;
        BPRTMT.DATA_TXT.SIGN_TIME = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRTMT.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP16);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP16.FUNC = SMB1600_AWA_1600.FUNC;
        BPCXP16.TYPE = BPRPRMT.KEY.TYP;
        BPCXP16.CODE = BPRPRMT.KEY.CD;
        BPCXP16.DESC = BPRPRMT.DESC;
        BPCXP16.CDESC = BPRPRMT.CDESC;
        BPCXP16.FLAG = 0X02;
        BPCXP16.EFF_DATE = BPCPRMM.EFF_DT;
        BPCXP16.EXP_DATE = BPCPRMM.EXP_DT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRTMT.DATA_TXT);
        BPCXP16.TMT_BK = BPRTMT.DATA_TXT.BK;
        BPCXP16.TMT_BR = BPRTMT.DATA_TXT.BR;
        BPCXP16.TMT_DP = BPRTMT.DATA_TXT.DP;
        BPCXP16.TMT_TYPE = BPRTMT.DATA_TXT.TYPE;
        BPCXP16.TMT_PRNT_ADDR = BPRTMT.DATA_TXT.PRNT_ADDR;
        BPCXP16.TMT_USE_IN = BPRTMT.DATA_TXT.USE_IN;
        BPCXP16.TMT_TL_ID = BPRTMT.DATA_TXT.TL_ID;
        BPCXP16.TMT_SIGN_DATE = BPRTMT.DATA_TXT.SIGN_DATE;
        BPCXP16.TMT_SIGN_TIME = BPRTMT.DATA_TXT.SIGN_TIME;
        SCCFMT.FMTID = "BPP16";
        SCCFMT.DATA_PTR = BPCXP16;
        SCCFMT.DATA_LEN = 195;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_AP = BPCPRMM.RC.RC_APP;
            WS_TEMP_VARIABLE.WS_MSGID.WS_MSG_CODE = BPCPRMM.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
