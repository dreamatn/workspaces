package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1770 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMOT1770_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1770_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP26 BPCXP26 = new BPCXP26();
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
    BPRINTCF BPRINTCF = new BPRINTCF();
    SCCGWA SCCGWA;
    SMB1770_AWA_1770 SMB1770_AWA_1770;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1770 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1770_AWA_1770>");
        SMB1770_AWA_1770 = (SMB1770_AWA_1770) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        if (SMB1770_AWA_1770.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1770_AWA_1770.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1770_AWA_1770.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1770_AWA_1770.FUNC == 'D') {
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
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = SMB1770_AWA_1770.PTYP;
        BPRPRMT.KEY.CD = SMB1770_AWA_1770.CODE;
        BPCPRMM.EFF_DT = SMB1770_AWA_1770.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = SMB1770_AWA_1770.PTYP;
        BPRPRMT.KEY.CD = SMB1770_AWA_1770.CODE;
        BPCPRMM.EFF_DT = SMB1770_AWA_1770.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '2';
        BPRPRMT.KEY.TYP = SMB1770_AWA_1770.PTYP;
        BPRPRMT.KEY.CD = SMB1770_AWA_1770.CODE;
        BPCPRMM.EFF_DT = SMB1770_AWA_1770.EFFDATE;
        BPCPRMM.EXP_DT = SMB1770_AWA_1770.EXPDATE;
        BPRPRMT.DESC = SMB1770_AWA_1770.DESC;
        BPRPRMT.CDESC = SMB1770_AWA_1770.CDESC;
        IBS.init(SCCGWA, BPRINTCF.DATA_TXT);
        BPRINTCF.DATA_TXT.DES = SMB1770_AWA_1770.DES;
        BPRINTCF.DATA_TXT.PARA1 = SMB1770_AWA_1770.PARA1;
        BPRINTCF.DATA_TXT.PARA2 = SMB1770_AWA_1770.PARA2;
        BPRINTCF.DATA_TXT.PARA3 = SMB1770_AWA_1770.PARA3;
        BPRINTCF.DATA_TXT.PARA4 = SMB1770_AWA_1770.PARA4;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRINTCF.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = SMB1770_AWA_1770.PTYP;
        BPRPRMT.KEY.CD = SMB1770_AWA_1770.CODE;
        BPCPRMM.EFF_DT = SMB1770_AWA_1770.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '0';
        BPRPRMT.KEY.TYP = SMB1770_AWA_1770.PTYP;
        BPRPRMT.KEY.CD = SMB1770_AWA_1770.CODE;
        BPCPRMM.EFF_DT = SMB1770_AWA_1770.EFFDATE;
        BPCPRMM.EXP_DT = SMB1770_AWA_1770.EXPDATE;
        BPRPRMT.DESC = SMB1770_AWA_1770.DESC;
        BPRPRMT.CDESC = SMB1770_AWA_1770.CDESC;
        IBS.init(SCCGWA, BPRINTCF.DATA_TXT);
        BPRINTCF.DATA_TXT.DES = SMB1770_AWA_1770.DES;
        BPRINTCF.DATA_TXT.PARA1 = SMB1770_AWA_1770.PARA1;
        BPRINTCF.DATA_TXT.PARA2 = SMB1770_AWA_1770.PARA2;
        BPRINTCF.DATA_TXT.PARA3 = SMB1770_AWA_1770.PARA3;
        BPRINTCF.DATA_TXT.PARA4 = SMB1770_AWA_1770.PARA4;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRINTCF.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP26);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP26.FUNC = SMB1770_AWA_1770.FUNC;
        BPCXP26.TYPE = BPRPRMT.KEY.TYP;
        BPCXP26.CODE = BPRPRMT.KEY.CD;
        BPCXP26.EFF_DATE = BPCPRMM.EFF_DT;
        BPCXP26.EXP_DATE = BPCPRMM.EXP_DT;
        BPCXP26.DESC = BPRPRMT.DESC;
        BPCXP26.CDESC = BPRPRMT.CDESC;
        BPCXP26.FLAG = 0X02;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRINTCF.DATA_TXT);
        BPCXP26.DES = BPRINTCF.DATA_TXT.DES;
        BPCXP26.PARA1 = BPRINTCF.DATA_TXT.PARA1;
        BPCXP26.PARA2 = BPRINTCF.DATA_TXT.PARA2;
        BPCXP26.PARA3 = BPRINTCF.DATA_TXT.PARA3;
        BPCXP26.PARA4 = BPRINTCF.DATA_TXT.PARA4;
        SCCFMT.FMTID = "BPP26";
        SCCFMT.DATA_PTR = BPCXP26;
        SCCFMT.DATA_LEN = 595;
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
