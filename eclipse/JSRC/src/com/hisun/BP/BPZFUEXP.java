package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFUEXP {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String CPN_F_T_FEE_INFO = "BP-F-T-FEE-INFO     ";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_CNT1 = 0;
    short WS_FEE_NO = 0;
    short WS_IDX = 0;
    short WS_NO = 0;
    char WS_DATA_CHG_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPVFEXP BPVFEXP = new BPVFEXP();
    BPVFEXP BPVHEXP = new BPVFEXP();
    BPVFMSK BPVFMSK = new BPVFMSK();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    SCCGWA SCCGWA;
    BPCOFEXP BPCOUEXP;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFEXP BPCOUEXP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOUEXP = BPCOUEXP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFUEXP return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPVFEXP);
        IBS.init(SCCGWA, BPVHEXP);
        IBS.init(SCCGWA, BPCFPARM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCOUEXP.FUNC == 'I') {
            B010_QUERY_PROCESS();
        } else if (BPCOUEXP.FUNC == 'A') {
            B020_CREATE_PROCESS();
        } else if (BPCOUEXP.FUNC == 'U') {
            B030_MODIFY_PROCESS();
        } else if (BPCOUEXP.FUNC == 'D') {
            B040_DELETE_PROCESS();
        } else if (BPCOUEXP.FUNC == 'S') {
            B050_STARTBR_PROCESS();
        } else if (BPCOUEXP.FUNC == 'N') {
            B050_READNEXT_PROCESS();
        } else if (BPCOUEXP.FUNC == 'E') {
            B050_ENDBR_PROCESS();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFEXP);
        BPVFEXP.KEY.DER_CODE = BPCOUEXP.KEY.DER_CODE;
        BPVFEXP.VAL.EFF_DATE = BPCOUEXP.VAL.EFF_DATE;
        CEP.TRC(SCCGWA, BPVFEXP.KEY.DER_CODE);
        CEP.TRC(SCCGWA, BPVFEXP.VAL.EFF_DATE);
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFEXP;
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FEXP ";
        S000_CALL_BPZFPARM();
        S000_CHECK_RETURN();
        R000_TRANS_DATE_OUTPUT();
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        R000_TRANS_DATE_UEXP_FEXP();
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFEXP;
        BPCFPARM.INFO.FUNC = '0';
        BPCFPARM.INFO.TYPE = "FEXP ";
        S000_CALL_BPZFPARM();
        S000_CHECK_RETURN();
        R000_TRANS_DATE_OUTPUT();
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        BPVFEXP.KEY.DER_CODE = BPCOUEXP.KEY.DER_CODE;
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFEXP;
        BPCFPARM.INFO.FUNC = '4';
        BPCFPARM.INFO.TYPE = "FEXP ";
        S000_CALL_BPZFPARM();
        IBS.CLONE(SCCGWA, BPVFEXP, BPVHEXP);
        R000_TRANS_DATE_UEXP_FEXP();
        if ("1".trim().length() == 0) WS_CNT1 = 0;
        else WS_CNT1 = Short.parseShort("1");
        for (WS_CNT1 = 1; WS_CNT1 <= 50; WS_CNT1 += 1) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPVFEXP.VAL.EXP_DATA[WS_CNT1-1]);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPVHEXP.VAL.EXP_DATA[WS_CNT1-1])) {
                WS_DATA_CHG_FLG = 'Y';
            }
            if (WS_DATA_CHG_FLG == 'Y') {
            }
        }
        if (BPVFEXP.VAL.EXP_DATE != BPVHEXP.VAL.EXP_DATE) {
            WS_DATA_CHG_FLG = 'Y';
        }
        if (WS_DATA_CHG_FLG != 'Y') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_UPD_DATA_NOT_CHG;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFEXP;
        BPCFPARM.INFO.FUNC = '1';
        BPCFPARM.INFO.TYPE = "FEXP ";
        B020_01_HISTORY_RECORD();
        S000_CALL_BPZFPARM();
        S000_CHECK_RETURN();
        R000_TRANS_DATE_OUTPUT();
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFEXP);
        BPVFEXP.KEY.DER_CODE = BPCOUEXP.KEY.DER_CODE;
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFEXP;
        BPCFPARM.INFO.FUNC = '4';
        BPCFPARM.INFO.TYPE = "FEXP ";
        S000_CALL_BPZFPARM();
        R000_TRANS_DATE_OUTPUT();
        IBS.CLONE(SCCGWA, BPVFEXP, BPVHEXP);
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFEXP;
        BPCFPARM.INFO.FUNC = '2';
        BPCFPARM.INFO.TYPE = "FEXP ";
        S000_CALL_BPZFPARM();
        S000_CHECK_RETURN();
    }
    public void B050_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFEXP);
        BPVFEXP.KEY.DER_CODE = BPCOUEXP.KEY.DER_CODE;
        CEP.TRC(SCCGWA, BPVFEXP.KEY.DER_CODE);
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFEXP;
        BPCFPARM.INFO.FUNC = '5';
        BPCFPARM.INFO.OPT = '0';
        BPCFPARM.INFO.TYPE = "FEXP ";
        S000_CALL_BPZFPARM();
        S000_CHECK_RETURN();
    }
    public void B050_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCFPARM.INFO.POINTER = BPVFEXP;
        BPCFPARM.INFO.FUNC = '5';
        BPCFPARM.INFO.OPT = '1';
        BPCFPARM.INFO.TYPE = "FEXP ";
        S000_CALL_BPZFPARM();
        S000_CHECK_RETURN();
        R000_TRANS_DATE_OUTPUT();
        CEP.TRC(SCCGWA, "READ NEXT OUT  ");
    }
    public void B050_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFPARM);
        BPCFPARM.INFO.POINTER = BPVFEXP;
        BPCFPARM.INFO.FUNC = '5';
        BPCFPARM.INFO.OPT = '2';
        BPCFPARM.INFO.TYPE = "FEXP ";
        S000_CALL_BPZFPARM();
        S000_CHECK_RETURN();
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        BPCFPARM.INFO.POINTER_OLD = BPVHEXP;
    }
    public void S000_GET_FEE_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFBAS);
        BPRFBAS.KEY.FEE_CODE = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE;
        CEP.TRC(SCCGWA, BPRFBAS.KEY.FEE_CODE);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPCTFBAS.INFO.FUNC = 'Q';
        S000_CALL_BPZTFBAS();
        CEP.TRC(SCCGWA, BPCTFBAS.RETURN_INFO);
    }
    public void S000_GET_FEE_CODE() throws IOException,SQLException,Exception {
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPCTFBAS.INFO.FUNC = 'Q';
        S000_CALL_BPZTFBAS();
        if (BPCTFBAS.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_MAINTAIN_PARM, BPCFPARM);
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_T_FEE_INFO, BPCTFBAS);
        if (BPCTFBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTFBAS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_TRANS_DATE_UEXP_FEXP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPVFEXP);
        BPVFEXP.KEY.DER_CODE = BPCOUEXP.KEY.DER_CODE;
        BPVFEXP.VAL.DER_DESC = BPCOUEXP.VAL.DER_DESC;
        BPVFEXP.VAL.EFF_DATE = BPCOUEXP.VAL.EFF_DATE;
        BPVFEXP.VAL.EXP_DATE = BPCOUEXP.VAL.EXP_DATE;
        for (WS_CNT1 = 1; WS_CNT1 <= 50 
            && BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE.trim().length() != 0; WS_CNT1 += 1) {
            if (BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE.trim().length() > 0 
                && !BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE.equalsIgnoreCase("0") 
                && BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE.charAt(0) != 0X00) {
                S000_GET_FEE_NO();
                if (BPCTFBAS.RETURN_INFO != 'F') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
                    S000_ERR_MSG_PROC();
                }
                WS_IDX = (short) (BPRFBAS.FEE_NO / 254);
                WS_NO = (short) (BPRFBAS.FEE_NO - WS_IDX * 254);
                if (WS_IDX == 0) {
                    if (BPVFEXP.VAL.FEE_MASK1 == null) BPVFEXP.VAL.FEE_MASK1 = "";
                    JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK1.length();
                    for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK1 += " ";
                    if ((!BPVFEXP.VAL.FEE_MASK1.substring(WS_NO - 1, WS_NO + 1 - 1).equalsIgnoreCase("1"))) {
                        if (BPVFEXP.VAL.FEE_MASK1 == null) BPVFEXP.VAL.FEE_MASK1 = "";
                        JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK1.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK1 += " ";
                        BPVFEXP.VAL.FEE_MASK1 = BPVFEXP.VAL.FEE_MASK1.substring(0, WS_NO - 1) + "1" + BPVFEXP.VAL.FEE_MASK1.substring(WS_NO + 1 - 1);
                    } else {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_DUB;
                        WS_ERR_INFO = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE;
                        S000_ERR_MSG_PROC();
                    }
                } else if (WS_IDX == 1) {
                    if (BPVFEXP.VAL.FEE_MASK2 == null) BPVFEXP.VAL.FEE_MASK2 = "";
                    JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK2.length();
                    for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK2 += " ";
                    if ((!BPVFEXP.VAL.FEE_MASK2.substring(WS_NO - 1, WS_NO + 1 - 1).equalsIgnoreCase("1"))) {
                        if (BPVFEXP.VAL.FEE_MASK2 == null) BPVFEXP.VAL.FEE_MASK2 = "";
                        JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK2.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK2 += " ";
                        BPVFEXP.VAL.FEE_MASK2 = BPVFEXP.VAL.FEE_MASK2.substring(0, WS_NO - 1) + "1" + BPVFEXP.VAL.FEE_MASK2.substring(WS_NO + 1 - 1);
                    } else {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_DUB;
                        WS_ERR_INFO = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE;
                        S000_ERR_MSG_PROC();
                    }
                } else if (WS_IDX == 2) {
                    if (BPVFEXP.VAL.FEE_MASK3 == null) BPVFEXP.VAL.FEE_MASK3 = "";
                    JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK3.length();
                    for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK3 += " ";
                    if ((!BPVFEXP.VAL.FEE_MASK3.substring(WS_NO - 1, WS_NO + 1 - 1).equalsIgnoreCase("1"))) {
                        if (BPVFEXP.VAL.FEE_MASK3 == null) BPVFEXP.VAL.FEE_MASK3 = "";
                        JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK3.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK3 += " ";
                        BPVFEXP.VAL.FEE_MASK3 = BPVFEXP.VAL.FEE_MASK3.substring(0, WS_NO - 1) + "1" + BPVFEXP.VAL.FEE_MASK3.substring(WS_NO + 1 - 1);
                    } else {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_DUB;
                        WS_ERR_INFO = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE;
                        S000_ERR_MSG_PROC();
                    }
                } else if (WS_IDX == 3) {
                    if (BPVFEXP.VAL.FEE_MASK4 == null) BPVFEXP.VAL.FEE_MASK4 = "";
                    JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK4.length();
                    for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK4 += " ";
                    if ((!BPVFEXP.VAL.FEE_MASK4.substring(WS_NO - 1, WS_NO + 1 - 1).equalsIgnoreCase("1"))) {
                        if (BPVFEXP.VAL.FEE_MASK4 == null) BPVFEXP.VAL.FEE_MASK4 = "";
                        JIBS_tmp_int = BPVFEXP.VAL.FEE_MASK4.length();
                        for (int i=0;i<254-JIBS_tmp_int;i++) BPVFEXP.VAL.FEE_MASK4 += " ";
                        BPVFEXP.VAL.FEE_MASK4 = BPVFEXP.VAL.FEE_MASK4.substring(0, WS_NO - 1) + "1" + BPVFEXP.VAL.FEE_MASK4.substring(WS_NO + 1 - 1);
                    } else {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_DUB;
                        WS_ERR_INFO = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE;
                        S000_ERR_MSG_PROC();
                    }
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AWA_FEE_NOTFOUND;
                    S000_ERR_MSG_PROC();
                }
            }
            BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE;
            BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].CCY = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].CCY;
            CEP.TRC(SCCGWA, BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].CCY);
            BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].DER_FLG = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].DER_FLG;
            BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].MAX_PERCENT = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].MAX_PERCENT;
            BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].MAX_AMT = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].MAX_AMT;
            BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].AUTH_LVL = BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].AUTH_LVL;
        }
        BPVFEXP.VAL.LAST_DATE = BPCOUEXP.VAL.LAST_DATE;
        BPVFEXP.VAL.LAST_TELL = BPCOUEXP.VAL.LAST_TELL;
        BPVFEXP.VAL.SUP_TEL1 = BPCOUEXP.VAL.SUP_TEL1;
        BPVFEXP.VAL.SUP_TEL2 = BPCOUEXP.VAL.SUP_TEL2;
    }
    public void R000_TRANS_DATE_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOUEXP.VAL);
        BPCOUEXP.KEY.DER_CODE = BPVFEXP.KEY.DER_CODE;
        BPCOUEXP.VAL.DER_DESC = BPVFEXP.VAL.DER_DESC;
        BPCOUEXP.VAL.EFF_DATE = BPVFEXP.VAL.EFF_DATE;
        BPCOUEXP.VAL.EXP_DATE = BPVFEXP.VAL.EXP_DATE;
        if ("1".trim().length() == 0) WS_CNT1 = 0;
        else WS_CNT1 = Short.parseShort("1");
        for (WS_CNT1 = 1; WS_CNT1 <= 50 
            && BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE.trim().length() != 0; WS_CNT1 += 1) {
            CEP.TRC(SCCGWA, WS_CNT1);
            BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE = BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_CODE;
            if (BPCFPARM.INFO.FUNC != '5') {
                if (BPCOUEXP.FUNC == 'D' 
                    || BPCOUEXP.FUNC == 'I') {
                    S000_GET_FEE_NO();
                }
                BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].FEE_DESC = BPRFBAS.FEE_DESC;
                CEP.TRC(SCCGWA, "UEXP-FEE-CODE  ");
                BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].CCY = BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].CCY;
                CEP.TRC(SCCGWA, "UEXP-CCY       ");
                BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].DER_FLG = BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].DER_FLG;
                BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].MAX_PERCENT = BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].MAX_PERCENT;
                BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].MAX_AMT = BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].MAX_AMT;
                BPCOUEXP.VAL.EXP_DATA[WS_CNT1-1].AUTH_LVL = BPVFEXP.VAL.EXP_DATA[WS_CNT1-1].AUTH_LVL;
                CEP.TRC(SCCGWA, "UEXP-AUTH-LVL  ");
            }
        }
        CEP.TRC(SCCGWA, "TRANS OUT      ");
    }
    public void S000_CHECK_RETURN() throws IOException,SQLException,Exception {
        if (BPCFPARM.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_RECORD_EXIST;
            S000_ERR_MSG_PROC();
        }
        if (BPCFPARM.RETURN_INFO == 'N' 
            && BPCFPARM.INFO.FUNC == '5') {
            BPCOUEXP.FOUND_FLG = 'N';
        } else {
            if (BPCFPARM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_NO_NOTFOUND;
                S000_ERR_MSG_PROC();
            }
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
