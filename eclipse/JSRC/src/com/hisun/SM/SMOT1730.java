package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SMOT1730 {
    BPRCUT_FILE FILE;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_ERROR = 'E';
    SMOT1730_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMOT1730_WS_TEMP_VARIABLE();
    String WS_TSQ_REC = " ";
    BPCXP22 BPCXP22 = new BPCXP22();
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
    BPRCUT BPRCUT = new BPRCUT();
    SCCGWA SCCGWA;
    SMB1730_AWA_1730 SMB1730_AWA_1730;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMOT1730 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SMB1730_AWA_1730>");
        SMB1730_AWA_1730 = (SMB1730_AWA_1730) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        if (SMB1730_AWA_1730.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1730_AWA_1730.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1730_AWA_1730.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
        } else if (SMB1730_AWA_1730.FUNC == 'D') {
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
        for (WS_TEMP_VARIABLE.WS_COUNT = 1; WS_TEMP_VARIABLE.WS_COUNT <= 30; WS_TEMP_VARIABLE.WS_COUNT += 1) {
            if ((SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILESTUS != 'Y' 
                && SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILESTUS != ' ' 
                && SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILESTUS != '1' 
                && SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILESTUS != '2' 
                && SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILESTUS != 'A' 
                && SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILESTUS != 'B')) {
                IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, WS_TEMP_VARIABLE.WS_MSGID);
                WS_TEMP_VARIABLE.WS_FLD_NO = SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILESTUS_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
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
        BPRPRMT.KEY.TYP = SMB1730_AWA_1730.PTYP;
        BPRPRMT.KEY.CD = SMB1730_AWA_1730.CODE;
        BPCPRMM.EFF_DT = SMB1730_AWA_1730.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = SMB1730_AWA_1730.PTYP;
        BPRPRMT.KEY.CD = SMB1730_AWA_1730.CODE;
        BPCPRMM.EFF_DT = SMB1730_AWA_1730.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '2';
        BPRPRMT.KEY.TYP = SMB1730_AWA_1730.PTYP;
        BPRPRMT.KEY.CD = SMB1730_AWA_1730.CODE;
        BPCPRMM.EFF_DT = SMB1730_AWA_1730.EFFDATE;
        BPCPRMM.EXP_DT = SMB1730_AWA_1730.EXPDATE;
        BPRPRMT.DESC = SMB1730_AWA_1730.DESC;
        BPRPRMT.CDESC = SMB1730_AWA_1730.CDESC;
        SMB1730_AWA_1730.FILE_CNT = 0;
        for (WS_TEMP_VARIABLE.WS_COUNT = 1; WS_TEMP_VARIABLE.WS_COUNT <= 30; WS_TEMP_VARIABLE.WS_COUNT += 1) {
            FILE = BPRCUT.DATA_TXT.FILE.get(WS_TEMP_VARIABLE.WS_COUNT-1);
            FILE.FILE_NAME = SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILENAME;
            BPRCUT.DATA_TXT.FILE.set(WS_TEMP_VARIABLE.WS_COUNT-1, FILE);
            FILE = BPRCUT.DATA_TXT.FILE.get(WS_TEMP_VARIABLE.WS_COUNT-1);
            FILE.FILE_STUS = SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILESTUS;
            BPRCUT.DATA_TXT.FILE.set(WS_TEMP_VARIABLE.WS_COUNT-1, FILE);
            if ((SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILENAME.trim().length() > 0)) {
                SMB1730_AWA_1730.FILE_CNT += 1;
            }
        }
        BPRCUT.DATA_TXT.FILE_CNT = SMB1730_AWA_1730.FILE_CNT;
        FILE = new BPRCUT_FILE();
        BPRCUT.DATA_TXT.FILE.add(FILE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCUT.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = SMB1730_AWA_1730.PTYP;
        BPRPRMT.KEY.CD = SMB1730_AWA_1730.CODE;
        BPCPRMM.EFF_DT = SMB1730_AWA_1730.EFFDATE;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '0';
        BPRPRMT.KEY.TYP = SMB1730_AWA_1730.PTYP;
        BPRPRMT.KEY.CD = SMB1730_AWA_1730.CODE;
        BPCPRMM.EFF_DT = SMB1730_AWA_1730.EFFDATE;
        BPCPRMM.EXP_DT = SMB1730_AWA_1730.EXPDATE;
        BPRPRMT.DESC = SMB1730_AWA_1730.DESC;
        BPRPRMT.CDESC = SMB1730_AWA_1730.CDESC;
        for (WS_TEMP_VARIABLE.WS_COUNT = 1; WS_TEMP_VARIABLE.WS_COUNT <= 30; WS_TEMP_VARIABLE.WS_COUNT += 1) {
            FILE.FILE_NAME = SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILENAME;
            FILE.FILE_STUS = SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILESTUS;
            if ((SMB1730_AWA_1730.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILENAME.trim().length() > 0)) {
                SMB1730_AWA_1730.FILE_CNT += 1;
            }
        }
        BPRCUT.DATA_TXT.FILE_CNT = SMB1730_AWA_1730.FILE_CNT;
        FILE = new BPRCUT_FILE();
        BPRCUT.DATA_TXT.FILE.add(FILE);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRCUT.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCXP22);
        IBS.init(SCCGWA, SCCFMT);
        BPCXP22.FUNC = SMB1730_AWA_1730.FUNC;
        BPCXP22.TYPE = BPRPRMT.KEY.TYP;
        BPCXP22.CODE = BPRPRMT.KEY.CD;
        BPCXP22.EFF_DATE = BPCPRMM.EFF_DT;
        BPCXP22.EXP_DATE = BPCPRMM.EXP_DT;
        BPCXP22.DESC = BPRPRMT.DESC;
        BPCXP22.CDESC = BPRPRMT.CDESC;
        BPCXP22.FLAG = 0X02;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCUT.DATA_TXT);
        for (WS_TEMP_VARIABLE.WS_COUNT = 1; WS_TEMP_VARIABLE.WS_COUNT <= 30; WS_TEMP_VARIABLE.WS_COUNT += 1) {
            BPCXP22.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILE_NAME = BPRCUT.DATA_TXT.FILE.get(WS_TEMP_VARIABLE.WS_COUNT-1).FILE_NAME;
            BPCXP22.FILE[WS_TEMP_VARIABLE.WS_COUNT-1].FILE_STUS = BPRCUT.DATA_TXT.FILE.get(WS_TEMP_VARIABLE.WS_COUNT-1).FILE_STUS;
        }
        BPCXP22.FILE_CNT = BPRCUT.DATA_TXT.FILE_CNT;
        SCCFMT.FMTID = "BPP22";
        SCCFMT.DATA_PTR = BPCXP22;
        SCCFMT.DATA_LEN = 422;
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
