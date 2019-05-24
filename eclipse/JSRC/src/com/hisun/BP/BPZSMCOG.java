package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMCOG {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    String K_PARM_CONNO = "CONNO";
    String K_PARM_ORGTP = "ORGTP";
    String K_PARM_ORGRT = "ORGRT";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "SORGA INFO MAINTAIN     ";
    String K_CPY_BPRCORGM = "BPRCORGM";
    String WS_ERR_MSG = " ";
    String WS_TAB_DESC = " ";
    String WS_BR_DESC = " ";
    String WS_REL_DESC = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRCORGM BPRCORGM = new BPRCORGM();
    BPRCORGM BPROCORG = new BPRCORGM();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCO534 BPCO534 = new BPCO534();
    SCCGWA SCCGWA;
    BPCSMCOG BPCSMCOG;
    public void MP(SCCGWA SCCGWA, BPCSMCOG BPCSMCOG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMCOG = BPCSMCOG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSMCOG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMCOG);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (BPCSMCOG.I_FUNC == 'I') {
            B020_QUERY_PROCESS();
        } else if (BPCSMCOG.I_FUNC == 'A') {
            B030_CREATE_PROCESS();
        } else if (BPCSMCOG.I_FUNC == 'U') {
            B040_MODIFY_PROCESS();
        } else if (BPCSMCOG.I_FUNC == 'D') {
            B050_DELETE_PROCESS();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
        B060_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSMCOG.BNK.equalsIgnoreCase("0")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BNK;
            S000_ERR_MSG_PROC();
        }
        if (BPCSMCOG.TAB_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_TAB_CD;
            S000_ERR_MSG_PROC();
        }
        if (BPCSMCOG.BR_TYP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BR_TYPE;
            S000_ERR_MSG_PROC();
        }
        if (BPCSMCOG.OPT_TYP == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_OPT_TYPE;
            S000_ERR_MSG_PROC();
        }
        if (BPCSMCOG.REL_TYP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_REL_TYPE;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCORGM);
        IBS.init(SCCGWA, BPCPRMM);
        BPRCORGM.KEY.TYP = "SORGA";
        BPCPRMM.FUNC = '3';
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.BNK == null) BPCSMCOG.BNK = "";
        JIBS_tmp_int = BPCSMCOG.BNK.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCSMCOG.BNK += " ";
        BPRCORGM.KEY.CD = BPCSMCOG.BNK + BPRCORGM.KEY.CD.substring(3);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.TAB_CD == null) BPCSMCOG.TAB_CD = "";
        JIBS_tmp_int = BPCSMCOG.TAB_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMCOG.TAB_CD += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 4 - 1) + BPCSMCOG.TAB_CD + BPRCORGM.KEY.CD.substring(4 + 4 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.BR_TYP == null) BPCSMCOG.BR_TYP = "";
        JIBS_tmp_int = BPCSMCOG.BR_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.BR_TYP += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 8 - 1) + BPCSMCOG.BR_TYP + BPRCORGM.KEY.CD.substring(8 + 2 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        JIBS_tmp_str[0] = "" + BPCSMCOG.OPT_TYP;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 10 - 1) + JIBS_tmp_str[0] + BPRCORGM.KEY.CD.substring(10 + 1 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.REL_TYP == null) BPCSMCOG.REL_TYP = "";
        JIBS_tmp_int = BPCSMCOG.REL_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.REL_TYP += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 11 - 1) + BPCSMCOG.REL_TYP + BPRCORGM.KEY.CD.substring(11 + 2 - 1);
        BPCPRMM.EFF_DT = BPCSMCOG.EFF_DT;
        BPCPRMM.DAT_PTR = BPRCORGM;
        S000_CALL_BPZPRMM();
        CEP.TRC(SCCGWA, BPRCORGM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CREATE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        IBS.init(SCCGWA, BPRCORGM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        BPRCORGM.KEY.TYP = "SORGA";
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.BNK == null) BPCSMCOG.BNK = "";
        JIBS_tmp_int = BPCSMCOG.BNK.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCSMCOG.BNK += " ";
        BPRCORGM.KEY.CD = BPCSMCOG.BNK + BPRCORGM.KEY.CD.substring(3);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.TAB_CD == null) BPCSMCOG.TAB_CD = "";
        JIBS_tmp_int = BPCSMCOG.TAB_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMCOG.TAB_CD += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 4 - 1) + BPCSMCOG.TAB_CD + BPRCORGM.KEY.CD.substring(4 + 4 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.BR_TYP == null) BPCSMCOG.BR_TYP = "";
        JIBS_tmp_int = BPCSMCOG.BR_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.BR_TYP += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 8 - 1) + BPCSMCOG.BR_TYP + BPRCORGM.KEY.CD.substring(8 + 2 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        JIBS_tmp_str[0] = "" + BPCSMCOG.OPT_TYP;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 10 - 1) + JIBS_tmp_str[0] + BPRCORGM.KEY.CD.substring(10 + 1 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.REL_TYP == null) BPCSMCOG.REL_TYP = "";
        JIBS_tmp_int = BPCSMCOG.REL_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.REL_TYP += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 11 - 1) + BPCSMCOG.REL_TYP + BPRCORGM.KEY.CD.substring(11 + 2 - 1);
        BPCPRMM.EFF_DT = BPCSMCOG.EFF_DT;
        BPCPRMM.EXP_DT = BPCSMCOG.EXP_DT;
        R000_TRANS_DATA_PARAMETER();
        BPRCORGM.DATA_TXT.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCORGM.DATA_TXT.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCORGM.DATA_TXT.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRCORGM.DATA_LEN = 126;
        BPCPRMM.DAT_PTR = BPRCORGM;
        S000_CALL_BPZPRMM();
        CEP.TRC(SCCGWA, BPRCORGM);
        B030_01_HISTORY_RECORD();
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = BPCSMCOG.BNK;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCORGM;
        S000_CALL_BPZPNHIS();
    }
    public void B040_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCORGM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        BPRCORGM.KEY.TYP = "SORGA";
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.BNK == null) BPCSMCOG.BNK = "";
        JIBS_tmp_int = BPCSMCOG.BNK.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCSMCOG.BNK += " ";
        BPRCORGM.KEY.CD = BPCSMCOG.BNK + BPRCORGM.KEY.CD.substring(3);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.TAB_CD == null) BPCSMCOG.TAB_CD = "";
        JIBS_tmp_int = BPCSMCOG.TAB_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMCOG.TAB_CD += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 4 - 1) + BPCSMCOG.TAB_CD + BPRCORGM.KEY.CD.substring(4 + 4 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.BR_TYP == null) BPCSMCOG.BR_TYP = "";
        JIBS_tmp_int = BPCSMCOG.BR_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.BR_TYP += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 8 - 1) + BPCSMCOG.BR_TYP + BPRCORGM.KEY.CD.substring(8 + 2 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        JIBS_tmp_str[0] = "" + BPCSMCOG.OPT_TYP;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 10 - 1) + JIBS_tmp_str[0] + BPRCORGM.KEY.CD.substring(10 + 1 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.REL_TYP == null) BPCSMCOG.REL_TYP = "";
        JIBS_tmp_int = BPCSMCOG.REL_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.REL_TYP += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 11 - 1) + BPCSMCOG.REL_TYP + BPRCORGM.KEY.CD.substring(11 + 2 - 1);
        BPCPRMM.EFF_DT = BPCSMCOG.EFF_DT;
        BPCPRMM.DAT_PTR = BPRCORGM;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        S000_CALL_BPZPRMM();
        CEP.TRC(SCCGWA, BPRCORGM);
        IBS.CLONE(SCCGWA, BPRCORGM, BPROCORG);
        R000_TRANS_DATA_PARAMETER();
        IBS.init(SCCGWA, BPCPRMM);
        BPRCORGM.DATA_TXT.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.FUNC = '2';
        BPRCORGM.KEY.TYP = "SORGA";
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.BNK == null) BPCSMCOG.BNK = "";
        JIBS_tmp_int = BPCSMCOG.BNK.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCSMCOG.BNK += " ";
        BPRCORGM.KEY.CD = BPCSMCOG.BNK + BPRCORGM.KEY.CD.substring(3);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.TAB_CD == null) BPCSMCOG.TAB_CD = "";
        JIBS_tmp_int = BPCSMCOG.TAB_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMCOG.TAB_CD += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 4 - 1) + BPCSMCOG.TAB_CD + BPRCORGM.KEY.CD.substring(4 + 4 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.BR_TYP == null) BPCSMCOG.BR_TYP = "";
        JIBS_tmp_int = BPCSMCOG.BR_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.BR_TYP += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 8 - 1) + BPCSMCOG.BR_TYP + BPRCORGM.KEY.CD.substring(8 + 2 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        JIBS_tmp_str[0] = "" + BPCSMCOG.OPT_TYP;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 10 - 1) + JIBS_tmp_str[0] + BPRCORGM.KEY.CD.substring(10 + 1 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.REL_TYP == null) BPCSMCOG.REL_TYP = "";
        JIBS_tmp_int = BPCSMCOG.REL_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.REL_TYP += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 11 - 1) + BPCSMCOG.REL_TYP + BPRCORGM.KEY.CD.substring(11 + 2 - 1);
        BPCPRMM.EFF_DT = BPCSMCOG.EFF_DT;
        BPCPRMM.EXP_DT = BPCSMCOG.EXP_DT;
        BPRCORGM.DATA_TXT.LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRCORGM.DATA_TXT.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRCORGM.DATA_LEN = 126;
        BPCPRMM.DAT_PTR = BPRCORGM;
        S000_CALL_BPZPRMM();
        CEP.TRC(SCCGWA, BPRCORGM);
        B040_01_HISTORY_RECORD();
    }
    public void B040_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = BPCSMCOG.BNK;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCORGM;
        CEP.TRC(SCCGWA, "MODIFY HIS");
        CEP.TRC(SCCGWA, BPROCORG.DATA_TXT);
        CEP.TRC(SCCGWA, BPRCORGM.DATA_TXT);
        BPCPNHIS.INFO.OLD_DAT_PT = BPROCORG;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRCORGM;
        S000_CALL_BPZPNHIS();
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCORGM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        BPRCORGM.KEY.TYP = "SORGA";
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.BNK == null) BPCSMCOG.BNK = "";
        JIBS_tmp_int = BPCSMCOG.BNK.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) BPCSMCOG.BNK += " ";
        BPRCORGM.KEY.CD = BPCSMCOG.BNK + BPRCORGM.KEY.CD.substring(3);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.TAB_CD == null) BPCSMCOG.TAB_CD = "";
        JIBS_tmp_int = BPCSMCOG.TAB_CD.length();
        for (int i=0;i<4-JIBS_tmp_int;i++) BPCSMCOG.TAB_CD += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 4 - 1) + BPCSMCOG.TAB_CD + BPRCORGM.KEY.CD.substring(4 + 4 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.BR_TYP == null) BPCSMCOG.BR_TYP = "";
        JIBS_tmp_int = BPCSMCOG.BR_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.BR_TYP += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 8 - 1) + BPCSMCOG.BR_TYP + BPRCORGM.KEY.CD.substring(8 + 2 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        JIBS_tmp_str[0] = "" + BPCSMCOG.OPT_TYP;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 10 - 1) + JIBS_tmp_str[0] + BPRCORGM.KEY.CD.substring(10 + 1 - 1);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        if (BPCSMCOG.REL_TYP == null) BPCSMCOG.REL_TYP = "";
        JIBS_tmp_int = BPCSMCOG.REL_TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.REL_TYP += " ";
        BPRCORGM.KEY.CD = BPRCORGM.KEY.CD.substring(0, 11 - 1) + BPCSMCOG.REL_TYP + BPRCORGM.KEY.CD.substring(11 + 2 - 1);
        BPCPRMM.EFF_DT = BPCSMCOG.EFF_DT;
        BPRCORGM.DATA_LEN = 126;
        BPCPRMM.DAT_PTR = BPRCORGM;
        S000_CALL_BPZPRMM();
        CEP.TRC(SCCGWA, BPRCORGM);
        B050_01_HISTORY_RECORD();
        BPCPRMM.FUNC = '1';
        BPRCORGM.KEY.TYP = "SORGA";
        BPCPRMM.EFF_DT = BPCSMCOG.EFF_DT;
        BPRCORGM.DATA_LEN = 126;
        BPCPRMM.DAT_PTR = BPRCORGM;
        S000_CALL_BPZPRMM();
    }
    public void B050_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = BPCSMCOG.BNK;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRCORGM;
        S000_CALL_BPZPNHIS();
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R010_INQUERY_CODE_DESC();
        R020_TRANS_DATA_OUPUT();
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSMCOG.I_FUNC == 'I') {
            SCCFMT.FMTID = "BPX01";
        } else {
            SCCFMT.FMTID = "BP534";
        }
        SCCFMT.DATA_PTR = BPCO534;
        SCCFMT.DATA_LEN = 339;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRCORGM.DATA_TXT.TLR_LVL = BPCSMCOG.TLR_LVL;
        BPRCORGM.DATA_TXT.ISLF_FLG = BPCSMCOG.ISLF_FLG;
        BPRCORGM.DATA_TXT.ISLF_AUTH = BPCSMCOG.ISLR_AUTH;
        BPRCORGM.DATA_TXT.UP_LVL = BPCSMCOG.UP_LVL;
        BPRCORGM.DATA_TXT.UP_AUTH = BPCSMCOG.UP_AUTH;
        BPRCORGM.DATA_TXT.DWN_LVL = BPCSMCOG.DWN_LVL;
        BPRCORGM.DATA_TXT.DWN_AUTH = BPCSMCOG.DWN_AUTH;
        BPRCORGM.DATA_TXT.BLG_LVL = BPCSMCOG.BLG_LVL;
        BPRCORGM.DATA_TXT.BLG_AUTH = BPCSMCOG.BLG_AUTH;
        BPRCORGM.DESC = BPCSMCOG.DESC;
        BPRCORGM.CDESC = BPCSMCOG.CDESC;
        BPRCORGM.DATA_TXT.DESC_HIS = BPCSMCOG.DESC;
        BPRCORGM.DATA_TXT.CDESC_HIS = BPCSMCOG.CDESC;
    }
    public void R010_INQUERY_CODE_DESC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_PARM_CONNO;
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        BPCOQPCD.INPUT_DATA.CODE = BPRCORGM.KEY.CD.substring(4 - 1, 4 + 4 - 1);
        S000_CALL_BPZPQPCD();
        WS_TAB_DESC = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_PARM_ORGTP;
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        BPCOQPCD.INPUT_DATA.CODE = BPRCORGM.KEY.CD.substring(8 - 1, 8 + 2 - 1);
        S000_CALL_BPZPQPCD();
        WS_BR_DESC = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
        IBS.init(SCCGWA, BPCOQPCD);
        BPCOQPCD.INPUT_DATA.TYPE = K_PARM_ORGRT;
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        BPCOQPCD.INPUT_DATA.CODE = BPRCORGM.KEY.CD.substring(11 - 1, 11 + 2 - 1);
        S000_CALL_BPZPQPCD();
        WS_REL_DESC = BPCOQPCD.OUTPUT_DATA.CODE_INFO.NAME;
    }
    public void R020_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO534);
        BPCO534.FUNC = BPCSMCOG.I_FUNC;
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        BPCO534.BNK = BPRCORGM.KEY.CD.substring(0, 3);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        BPCO534.TAB_CD = BPRCORGM.KEY.CD.substring(4 - 1, 4 + 4 - 1);
        BPCO534.TAB_DESC = WS_TAB_DESC;
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        BPCO534.BR_TYP = BPRCORGM.KEY.CD.substring(8 - 1, 8 + 2 - 1);
        BPCO534.BR_DESC = WS_BR_DESC;
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        BPCO534.OPT_TYP = BPRCORGM.KEY.CD.substring(10 - 1, 10 + 1 - 1).charAt(0);
        if (BPRCORGM.KEY.CD == null) BPRCORGM.KEY.CD = "";
        JIBS_tmp_int = BPRCORGM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCORGM.KEY.CD += " ";
        BPCO534.REL_TYP = BPRCORGM.KEY.CD.substring(11 - 1, 11 + 2 - 1);
        BPCO534.REL_DESC = WS_REL_DESC;
        BPCO534.TLR_LVL = BPRCORGM.DATA_TXT.TLR_LVL;
        BPCO534.ISLF_FLG = BPRCORGM.DATA_TXT.ISLF_FLG;
        BPCO534.ISLF_AUTH = BPRCORGM.DATA_TXT.ISLF_AUTH;
        BPCO534.UP_LVL = BPRCORGM.DATA_TXT.UP_LVL;
        BPCO534.UP_AUTH = BPRCORGM.DATA_TXT.UP_AUTH;
        BPCO534.DWN_LVL = BPRCORGM.DATA_TXT.DWN_LVL;
        BPCO534.DWN_AUTH = BPRCORGM.DATA_TXT.DWN_AUTH;
        BPCO534.BLG_LVL = BPRCORGM.DATA_TXT.BLG_LVL;
        BPCO534.BLG_AUTH = BPRCORGM.DATA_TXT.BLG_AUTH;
        BPCO534.EFF_DT = BPCPRMM.EFF_DT;
        BPCO534.EXP_DT = BPCPRMM.EXP_DT;
        BPCO534.DESC = BPRCORGM.DESC;
        BPCO534.CDESC = BPRCORGM.CDESC;
        BPCO534.OPEN_DATE = BPRCORGM.DATA_TXT.OPEN_DATE;
        BPCO534.LAST_DATE = BPRCORGM.DATA_TXT.LAST_DATE;
        BPCO534.LAST_TLR = BPRCORGM.DATA_TXT.LAST_TLR;
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PC, BPCOQPCD);
        if (BPCOQPCD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOQPCD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCORGM.KEY.CD);
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
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
