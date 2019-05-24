package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4963 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String CPN_P_INQ_PC = "BP-P-INQ-PC         ";
    String CPN_P_INQ_BNK = "BP-P-QUERY-BANK     ";
    String CPN_MAINT_CORG = "BP-S-MAINT-CORG     ";
    String K_PARM_CONNO = "CONNO";
    String K_PARM_ORGTP = "ORGTP";
    String K_PARM_ORGRT = "ORGRT";
    char K_ERROR = 'E';
    BPOT4963_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT4963_WS_TEMP_VARIABLE();
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
        CEP.TRC(SCCGWA, "BPOT4963 return!");
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
        B200_MODIFY_PROCESS();
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
        if (BPB4960_AWA_4960.LVL9_AUT != ' ' 
            && BPB4960_AWA_4960.LVL9_AUT != 'Y' 
            && BPB4960_AWA_4960.LVL9_AUT != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.LVL9_AUT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.LVL8_AUT != ' ' 
            && BPB4960_AWA_4960.LVL8_AUT != 'Y' 
            && BPB4960_AWA_4960.LVL8_AUT != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.LVL8_AUT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.LVL7_AUT != ' ' 
            && BPB4960_AWA_4960.LVL7_AUT != 'Y' 
            && BPB4960_AWA_4960.LVL7_AUT != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.LVL7_AUT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.LVL6_AUT != ' ' 
            && BPB4960_AWA_4960.LVL6_AUT != 'Y' 
            && BPB4960_AWA_4960.LVL6_AUT != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.LVL6_AUT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.LVL5_AUT != ' ' 
            && BPB4960_AWA_4960.LVL5_AUT != 'Y' 
            && BPB4960_AWA_4960.LVL5_AUT != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.LVL5_AUT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.LVL4_AUT != ' ' 
            && BPB4960_AWA_4960.LVL4_AUT != 'Y' 
            && BPB4960_AWA_4960.LVL4_AUT != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.LVL4_AUT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.LVL3_AUT != ' ' 
            && BPB4960_AWA_4960.LVL3_AUT != 'Y' 
            && BPB4960_AWA_4960.LVL3_AUT != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.LVL3_AUT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.LVL2_AUT != ' ' 
            && BPB4960_AWA_4960.LVL2_AUT != 'Y' 
            && BPB4960_AWA_4960.LVL2_AUT != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.LVL2_AUT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.LVL1_AUT != ' ' 
            && BPB4960_AWA_4960.LVL1_AUT != 'Y' 
            && BPB4960_AWA_4960.LVL1_AUT != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.LVL1_AUT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.LVL0_AUT != ' ' 
            && BPB4960_AWA_4960.LVL0_AUT != 'Y' 
            && BPB4960_AWA_4960.LVL0_AUT != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.LVL0_AUT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.SELF_FLG != ' ' 
            && BPB4960_AWA_4960.SELF_FLG != 'Y' 
            && BPB4960_AWA_4960.SELF_FLG != 'N') {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_INPUT_ERR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.SELF_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((BPB4960_AWA_4960.SELF_AU2 != ' ' 
            && BPB4960_AWA_4960.SELF_AU2 != '0') 
            && BPB4960_AWA_4960.SELF_AU2 <= BPB4960_AWA_4960.SELF_AU1) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_LOW_AUTH_LVL;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.SELF_AU2_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((BPB4960_AWA_4960.UP_AUTH2 != ' ' 
            && BPB4960_AWA_4960.UP_AUTH2 != '0') 
            && BPB4960_AWA_4960.UP_AUTH2 <= BPB4960_AWA_4960.UP_AUTH1) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_LOW_AUTH_LVL;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.UP_AUTH2_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((BPB4960_AWA_4960.DOWN_AU2 != ' ' 
            && BPB4960_AWA_4960.DOWN_AU2 != '0') 
            && BPB4960_AWA_4960.DOWN_AU2 <= BPB4960_AWA_4960.DOWN_AU1) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_LOW_AUTH_LVL;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.DOWN_AU2_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if ((BPB4960_AWA_4960.BLG_AUT2 != ' ' 
            && BPB4960_AWA_4960.BLG_AUT2 != '0') 
            && BPB4960_AWA_4960.BLG_AUT2 <= BPB4960_AWA_4960.BLG_AUT1) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_LOW_AUTH_LVL;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.BLG_AUT2_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB4960_AWA_4960.EXP_DATE <= BPB4960_AWA_4960.EFF_DATE) {
            WS_TEMP_VARIABLE.WS_MSGID = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERROR;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB4960_AWA_4960.EXP_DATE_NO;
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
    public void B200_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMCOG);
        BPCSMCOG.I_FUNC = 'U';
        BPCSMCOG.BNK = BPB4960_AWA_4960.BANK;
        BPCSMCOG.TAB_CD = BPB4960_AWA_4960.CON_NO;
        BPCSMCOG.BR_TYP = BPB4960_AWA_4960.BRANCH_T;
        BPCSMCOG.OPT_TYP = BPB4960_AWA_4960.OPER_TYP;
        BPCSMCOG.REL_TYP = BPB4960_AWA_4960.RELA_TYP;
        if (BPCSMCOG.TLR_LVL == null) BPCSMCOG.TLR_LVL = "";
        JIBS_tmp_int = BPCSMCOG.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMCOG.TLR_LVL += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.LVL9_AUT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.TLR_LVL = JIBS_tmp_str[0] + BPCSMCOG.TLR_LVL.substring(1);
        if (BPCSMCOG.TLR_LVL == null) BPCSMCOG.TLR_LVL = "";
        JIBS_tmp_int = BPCSMCOG.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMCOG.TLR_LVL += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.LVL8_AUT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.TLR_LVL = BPCSMCOG.TLR_LVL.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPCSMCOG.TLR_LVL.substring(2 + 1 - 1);
        if (BPCSMCOG.TLR_LVL == null) BPCSMCOG.TLR_LVL = "";
        JIBS_tmp_int = BPCSMCOG.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMCOG.TLR_LVL += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.LVL7_AUT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.TLR_LVL = BPCSMCOG.TLR_LVL.substring(0, 3 - 1) + JIBS_tmp_str[0] + BPCSMCOG.TLR_LVL.substring(3 + 1 - 1);
        if (BPCSMCOG.TLR_LVL == null) BPCSMCOG.TLR_LVL = "";
        JIBS_tmp_int = BPCSMCOG.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMCOG.TLR_LVL += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.LVL6_AUT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.TLR_LVL = BPCSMCOG.TLR_LVL.substring(0, 4 - 1) + JIBS_tmp_str[0] + BPCSMCOG.TLR_LVL.substring(4 + 1 - 1);
        if (BPCSMCOG.TLR_LVL == null) BPCSMCOG.TLR_LVL = "";
        JIBS_tmp_int = BPCSMCOG.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMCOG.TLR_LVL += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.LVL5_AUT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.TLR_LVL = BPCSMCOG.TLR_LVL.substring(0, 5 - 1) + JIBS_tmp_str[0] + BPCSMCOG.TLR_LVL.substring(5 + 1 - 1);
        if (BPCSMCOG.TLR_LVL == null) BPCSMCOG.TLR_LVL = "";
        JIBS_tmp_int = BPCSMCOG.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMCOG.TLR_LVL += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.LVL4_AUT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.TLR_LVL = BPCSMCOG.TLR_LVL.substring(0, 6 - 1) + JIBS_tmp_str[0] + BPCSMCOG.TLR_LVL.substring(6 + 1 - 1);
        if (BPCSMCOG.TLR_LVL == null) BPCSMCOG.TLR_LVL = "";
        JIBS_tmp_int = BPCSMCOG.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMCOG.TLR_LVL += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.LVL3_AUT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.TLR_LVL = BPCSMCOG.TLR_LVL.substring(0, 7 - 1) + JIBS_tmp_str[0] + BPCSMCOG.TLR_LVL.substring(7 + 1 - 1);
        if (BPCSMCOG.TLR_LVL == null) BPCSMCOG.TLR_LVL = "";
        JIBS_tmp_int = BPCSMCOG.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMCOG.TLR_LVL += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.LVL2_AUT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.TLR_LVL = BPCSMCOG.TLR_LVL.substring(0, 8 - 1) + JIBS_tmp_str[0] + BPCSMCOG.TLR_LVL.substring(8 + 1 - 1);
        if (BPCSMCOG.TLR_LVL == null) BPCSMCOG.TLR_LVL = "";
        JIBS_tmp_int = BPCSMCOG.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMCOG.TLR_LVL += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.LVL1_AUT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.TLR_LVL = BPCSMCOG.TLR_LVL.substring(0, 9 - 1) + JIBS_tmp_str[0] + BPCSMCOG.TLR_LVL.substring(9 + 1 - 1);
        if (BPCSMCOG.TLR_LVL == null) BPCSMCOG.TLR_LVL = "";
        JIBS_tmp_int = BPCSMCOG.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCSMCOG.TLR_LVL += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.LVL0_AUT;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.TLR_LVL = BPCSMCOG.TLR_LVL.substring(0, 10 - 1) + JIBS_tmp_str[0] + BPCSMCOG.TLR_LVL.substring(10 + 1 - 1);
        BPCSMCOG.ISLF_FLG = BPB4960_AWA_4960.SELF_FLG;
        if (BPCSMCOG.ISLR_AUTH == null) BPCSMCOG.ISLR_AUTH = "";
        JIBS_tmp_int = BPCSMCOG.ISLR_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.ISLR_AUTH += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.SELF_AU1;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.ISLR_AUTH = JIBS_tmp_str[0] + BPCSMCOG.ISLR_AUTH.substring(1);
        if (BPCSMCOG.ISLR_AUTH == null) BPCSMCOG.ISLR_AUTH = "";
        JIBS_tmp_int = BPCSMCOG.ISLR_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.ISLR_AUTH += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.SELF_AU2;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.ISLR_AUTH = BPCSMCOG.ISLR_AUTH.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPCSMCOG.ISLR_AUTH.substring(2 + 1 - 1);
        BPCSMCOG.UP_LVL = BPB4960_AWA_4960.UP_LEVEL;
        if (BPCSMCOG.UP_AUTH == null) BPCSMCOG.UP_AUTH = "";
        JIBS_tmp_int = BPCSMCOG.UP_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.UP_AUTH += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.UP_AUTH1;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.UP_AUTH = JIBS_tmp_str[0] + BPCSMCOG.UP_AUTH.substring(1);
        if (BPCSMCOG.UP_AUTH == null) BPCSMCOG.UP_AUTH = "";
        JIBS_tmp_int = BPCSMCOG.UP_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.UP_AUTH += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.UP_AUTH2;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.UP_AUTH = BPCSMCOG.UP_AUTH.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPCSMCOG.UP_AUTH.substring(2 + 1 - 1);
        BPCSMCOG.DWN_LVL = BPB4960_AWA_4960.DOWN_LEV;
        if (BPCSMCOG.DWN_AUTH == null) BPCSMCOG.DWN_AUTH = "";
        JIBS_tmp_int = BPCSMCOG.DWN_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.DWN_AUTH += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.DOWN_AU1;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.DWN_AUTH = JIBS_tmp_str[0] + BPCSMCOG.DWN_AUTH.substring(1);
        if (BPCSMCOG.DWN_AUTH == null) BPCSMCOG.DWN_AUTH = "";
        JIBS_tmp_int = BPCSMCOG.DWN_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.DWN_AUTH += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.DOWN_AU2;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.DWN_AUTH = BPCSMCOG.DWN_AUTH.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPCSMCOG.DWN_AUTH.substring(2 + 1 - 1);
        BPCSMCOG.BLG_LVL = BPB4960_AWA_4960.BLG_LEVE;
        if (BPCSMCOG.BLG_AUTH == null) BPCSMCOG.BLG_AUTH = "";
        JIBS_tmp_int = BPCSMCOG.BLG_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.BLG_AUTH += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.BLG_AUT1;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.BLG_AUTH = JIBS_tmp_str[0] + BPCSMCOG.BLG_AUTH.substring(1);
        if (BPCSMCOG.BLG_AUTH == null) BPCSMCOG.BLG_AUTH = "";
        JIBS_tmp_int = BPCSMCOG.BLG_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCSMCOG.BLG_AUTH += " ";
        JIBS_tmp_str[0] = "" + BPB4960_AWA_4960.BLG_AUT2;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPCSMCOG.BLG_AUTH = BPCSMCOG.BLG_AUTH.substring(0, 2 - 1) + JIBS_tmp_str[0] + BPCSMCOG.BLG_AUTH.substring(2 + 1 - 1);
        BPCSMCOG.EFF_DT = BPB4960_AWA_4960.EFF_DATE;
        BPCSMCOG.EXP_DT = BPB4960_AWA_4960.EXP_DATE;
        BPCSMCOG.DESC = BPB4960_AWA_4960.DESC;
        BPCSMCOG.CDESC = BPB4960_AWA_4960.CDESC;
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
