package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFCGRL {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_PARM_BRW = "BP-PARM-BROWSE      ";
    String CPN_R_TXIF = "BP-R-MGM-TXIF       ";
    String CPN_F_INQ_TLR = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_CHK_REL_ORG = "BP-P-CHK-REL-ORG    ";
    String CPN_P_INQ_SAME_SUPR = "BP-P-INQ-SAME-SUPR  ";
    String WS_TAB_CD = " ";
    BPZFCGRL_WS_CORG_KEY WS_CORG_KEY = new BPZFCGRL_WS_CORG_KEY();
    BPZFCGRL_WS_CORG WS_CORG = new BPZFCGRL_WS_CORG();
    short WS_TLR_LVL = 0;
    char WS_DATA_BR_LVL = ' ';
    char WS_TR_BR_LVL = ' ';
    String WS_AUTH = " ";
    String WS_TMP_AUTH = " ";
    String WS_ERR_MSG = " ";
    char WS_MSG_TYPE = ' ';
    char WS_LVL1 = ' ';
    char WS_LVL2 = ' ';
    char WS_CHECK_FLG = 'B';
    char WS_TLR_ALLOW_FLG = 'N';
    char WS_CORGM_RECORD_FLG = 'N';
    char WS_CORGM_LVL_FLG = 'N';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRTXIF BPRTXIF = new BPRTXIF();
    BPRCORGM BPRCORGM = new BPRCORGM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCRTXIF BPCRTXIF = new BPCRTXIF();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPCKRG BPCPCKRG = new BPCPCKRG();
    BPCPORDN BPCPORDN = new BPCPORDN();
    SCCGWA SCCGWA;
    BPCFCGRL BPCFCGRL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFCGRL BPCFCGRL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFCGRL = BPCFCGRL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCGRL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCFCGRL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_AUTH_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCFCGRL.DATA_BR == 0 
            || BPCFCGRL.OPT_TYP != 'M' 
            && BPCFCGRL.OPT_TYP != 'I') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFCGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = BPCFCGRL.DATA_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_DATA_BR_LVL = BPCPQORG.LVL;
        CEP.TRC(SCCGWA, WS_DATA_BR_LVL);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        WS_TR_BR_LVL = BPCPQORG.LVL;
        CEP.TRC(SCCGWA, WS_TR_BR_LVL);
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_LVL == ' ') WS_TLR_LVL = 0;
        else WS_TLR_LVL = Short.parseShort(""+BPCFTLRQ.INFO.TLR_LVL);
        CEP.TRC(SCCGWA, WS_TLR_LVL);
    }
    public void B020_AUTH_PROCESS() throws IOException,SQLException,Exception {
    }
    public void B021_GET_CONTROL_NO() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.SERV_CODE.trim().length() > 0) {
            IBS.init(SCCGWA, BPRTXIF);
            IBS.init(SCCGWA, BPCRTXIF);
            BPRTXIF.KEY.IN_FLG = 'O';
            BPRTXIF.KEY.SYS_MMO = SCCGWA.COMM_AREA.CHNL;
            BPRTXIF.KEY.TX_CD = SCCGWA.COMM_AREA.SERV_CODE;
            BPCRTXIF.INFO.FUNC = 'Q';
            S000_CALL_BPZRTXIF();
            if (pgmRtn) return;
            if (BPRTXIF.TAB_CD.trim().length() > 0) {
                WS_TAB_CD = BPRTXIF.TAB_CD;
            }
        }
        CEP.TRC(SCCGWA, WS_TAB_CD);
        if (WS_TAB_CD.trim().length() == 0 
            && SCCGWA.COMM_AREA.TR_ID.TR_AP != 0 
            && SCCGWA.COMM_AREA.TR_ID.TR_CODE != ' ') {
            IBS.init(SCCGWA, BPRTXIF);
            IBS.init(SCCGWA, BPCRTXIF);
            BPRTXIF.KEY.IN_FLG = 'I';
            BPRTXIF.KEY.SYS_MMO = "IBS";
            BPRTXIF.KEY.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            BPCRTXIF.INFO.FUNC = 'Q';
            S000_CALL_BPZRTXIF();
            if (pgmRtn) return;
            if (BPRTXIF.TAB_CD.trim().length() > 0) {
                WS_TAB_CD = BPRTXIF.TAB_CD;
            }
        }
        CEP.TRC(SCCGWA, WS_TAB_CD);
    }
    public void B022_AUTH_CTRL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCORGM);
        IBS.init(SCCGWA, BPCPRMB);
        BPRCORGM.KEY.TYP = "SORGA";
        IBS.init(SCCGWA, WS_CORG_KEY);
        WS_CORG_KEY.WS_CORG_KEY_CON.WS_CORG_BNK = SCCGWA.COMM_AREA.TR_BANK;
        WS_CORG_KEY.WS_CORG_KEY_CON.WS_CORG_TAB_CD = WS_TAB_CD;
        WS_CORG_KEY.WS_CORG_KEY_CON.WS_CORG_BR_TYP = BPCPQORG.TYP;
        WS_CORG_KEY.WS_CORG_KEY_CON.WS_CORG_OPT_TYP = BPCFCGRL.OPT_TYP;
        BPRCORGM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CORG_KEY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CORG_KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_CORG);
        CEP.TRC(SCCGWA, WS_CORG);
        BPCPRMB.FUNC = '0';
        BPCPRMB.DAT_PTR = BPRCORGM;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        if (BPCPRMB.RC.RC_RTNCODE == 0) {
            JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, WS_CORG_KEY.WS_CORG_KEY_CON);
            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_CORG.WS_CORG_BRW_CON);
            do {
                IBS.init(SCCGWA, BPCPRMB.RC);
                BPCPRMB.FUNC = '1';
                S000_CALL_BPZPRMB();
                if (pgmRtn) return;
                if (BPCPRMB.RC.RC_RTNCODE == 0) {
                    IBS.CPY2CLS(SCCGWA, BPRCORGM.KEY.CD, WS_CORG_KEY);
                    CEP.TRC(SCCGWA, WS_CORG_KEY);
                    JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_CORG_KEY.WS_CORG_KEY_CON);
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CORG.WS_CORG_BRW_CON);
                    if (JIBS_tmp_str[1].equalsIgnoreCase(JIBS_tmp_str[0])) {
                        WS_CORGM_RECORD_FLG = 'Y';
                        WS_CHECK_FLG = 'B';
                        B023_CHECK_AUTH();
                        if (pgmRtn) return;
                    }
                }
            } while (BPCPRMB.RC.RC_RTNCODE == 0 
                && WS_CHECK_FLG != 'O' 
                && JIBS_tmp_str[2].compareTo(JIBS_tmp_str[1]) <= 0);
        }
        IBS.init(SCCGWA, BPCPRMB.RC);
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        if (WS_CORGM_RECORD_FLG == 'Y') {
            if (WS_TLR_ALLOW_FLG == 'N' 
                || WS_CORGM_LVL_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BRANCE_NOT_IN_AUTH, BPCFCGRL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (WS_AUTH.trim().length() > 0 
            && !WS_AUTH.equalsIgnoreCase("00")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEED_AUTHORIZE;
            WS_MSG_TYPE = 'A';
            if (WS_AUTH == null) WS_AUTH = "";
            JIBS_tmp_int = WS_AUTH.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) WS_AUTH += " ";
            WS_LVL1 = WS_AUTH.substring(0, 1).charAt(0);
            if (WS_AUTH == null) WS_AUTH = "";
            JIBS_tmp_int = WS_AUTH.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) WS_AUTH += " ";
            WS_LVL2 = WS_AUTH.substring(2 - 1, 2 + 1 - 1).charAt(0);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B023_CHECK_AUTH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCORGM.DATA_TXT.TLR_LVL);
        WS_TLR_LVL = (short) (10 - WS_TLR_LVL);
        if (BPRCORGM.DATA_TXT.TLR_LVL == null) BPRCORGM.DATA_TXT.TLR_LVL = "";
        JIBS_tmp_int = BPRCORGM.DATA_TXT.TLR_LVL.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPRCORGM.DATA_TXT.TLR_LVL += " ";
        if (!BPRCORGM.DATA_TXT.TLR_LVL.substring(WS_TLR_LVL - 1, WS_TLR_LVL + 1 - 1).equalsIgnoreCase("N")) {
            WS_TLR_ALLOW_FLG = 'Y';
        } else {
            WS_CHECK_FLG = 'N';
        }
        if (WS_CHECK_FLG == 'B') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == BPCFCGRL.DATA_BR) {
                WS_CORGM_LVL_FLG = 'Y';
                CEP.TRC(SCCGWA, BPRCORGM.DATA_TXT.ISLF_AUTH);
                if (BPRCORGM.DATA_TXT.ISLF_FLG == 'N') {
                    WS_TMP_AUTH = BPRCORGM.DATA_TXT.ISLF_AUTH;
                    B024_SAVE_MAX_AUTH();
                    if (pgmRtn) return;
                    WS_CHECK_FLG = 'N';
                } else {
                    WS_CHECK_FLG = 'O';
                }
                CEP.TRC(SCCGWA, WS_AUTH);
            } else {
                IBS.init(SCCGWA, BPCPORDN);
                BPCPORDN.BNK = SCCGWA.COMM_AREA.TR_BANK;
                BPCPORDN.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCPORDN.BR2 = BPCFCGRL.DATA_BR;
                CEP.TRC(SCCGWA, BPCPORDN.BR1);
                CEP.TRC(SCCGWA, BPCPORDN.BR2);
                S000_CALL_BPZPORDN();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCPORDN.FLAG);
                CEP.TRC(SCCGWA, BPCPORDN.LVL);
                if (BPCPORDN.FLAG == '2') {
                    CEP.TRC(SCCGWA, BPRCORGM.DATA_TXT.BLG_LVL);
                    CEP.TRC(SCCGWA, BPRCORGM.DATA_TXT.BLG_AUTH);
                    if (BPRCORGM.DATA_TXT.BLG_LVL == '9') {
                        WS_CORGM_LVL_FLG = 'Y';
                        WS_CHECK_FLG = 'O';
                    } else {
                        WS_CORGM_LVL_FLG = 'Y';
                        if (BPCPORDN.LVL > BPRCORGM.DATA_TXT.BLG_LVL) {
                            WS_TMP_AUTH = BPRCORGM.DATA_TXT.BLG_AUTH;
                            B024_SAVE_MAX_AUTH();
                            if (pgmRtn) return;
                            WS_CHECK_FLG = 'N';
                        } else {
                            WS_CHECK_FLG = 'O';
                        }
                    }
                    CEP.TRC(SCCGWA, WS_AUTH);
                } else {
                    if (WS_TR_BR_LVL < WS_DATA_BR_LVL) {
                        CEP.TRC(SCCGWA, BPRCORGM.DATA_TXT.UP_LVL);
                        CEP.TRC(SCCGWA, BPRCORGM.DATA_TXT.UP_AUTH);
                        if (BPRCORGM.DATA_TXT.UP_LVL == '9') {
                            WS_CORGM_LVL_FLG = 'Y';
                            WS_CHECK_FLG = 'O';
                        } else {
                            WS_CORGM_LVL_FLG = 'Y';
                            if (BPCPORDN.LVL > BPRCORGM.DATA_TXT.UP_LVL) {
                                WS_TMP_AUTH = BPRCORGM.DATA_TXT.UP_AUTH;
                                B024_SAVE_MAX_AUTH();
                                if (pgmRtn) return;
                                WS_CHECK_FLG = 'N';
                            } else {
                                WS_CHECK_FLG = 'O';
                            }
                        }
                    } else {
                        CEP.TRC(SCCGWA, BPRCORGM.DATA_TXT.DWN_LVL);
                        CEP.TRC(SCCGWA, BPRCORGM.DATA_TXT.DWN_AUTH);
                        if (BPRCORGM.DATA_TXT.DWN_LVL == '9') {
                            WS_CORGM_LVL_FLG = 'Y';
                            WS_CHECK_FLG = 'O';
                        } else {
                            WS_CORGM_LVL_FLG = 'Y';
                            if (BPCPORDN.LVL > BPRCORGM.DATA_TXT.DWN_LVL) {
                                WS_TMP_AUTH = BPRCORGM.DATA_TXT.DWN_AUTH;
                                B024_SAVE_MAX_AUTH();
                                if (pgmRtn) return;
                                WS_CHECK_FLG = 'N';
                            } else {
                                WS_CHECK_FLG = 'O';
                            }
                        }
                    }
                    CEP.TRC(SCCGWA, WS_AUTH);
                }
            }
        }
    }
    public void B024_SAVE_MAX_AUTH() throws IOException,SQLException,Exception {
        if (WS_TMP_AUTH == null) WS_TMP_AUTH = "";
        JIBS_tmp_int = WS_TMP_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) WS_TMP_AUTH += " ";
        if (WS_AUTH == null) WS_AUTH = "";
        JIBS_tmp_int = WS_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) WS_AUTH += " ";
        if (WS_TMP_AUTH.substring(0, 1).compareTo(WS_AUTH.substring(0, 1)) > 0) {
            if (WS_TMP_AUTH == null) WS_TMP_AUTH = "";
            JIBS_tmp_int = WS_TMP_AUTH.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) WS_TMP_AUTH += " ";
            if (WS_AUTH == null) WS_AUTH = "";
            JIBS_tmp_int = WS_AUTH.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) WS_AUTH += " ";
            WS_AUTH = WS_TMP_AUTH.substring(0, 1) + WS_AUTH.substring(1);
        }
        if (WS_TMP_AUTH == null) WS_TMP_AUTH = "";
        JIBS_tmp_int = WS_TMP_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) WS_TMP_AUTH += " ";
        if (WS_AUTH == null) WS_AUTH = "";
        JIBS_tmp_int = WS_AUTH.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) WS_AUTH += " ";
        if (WS_TMP_AUTH.substring(2 - 1, 2 + 1 - 1).compareTo(WS_AUTH.substring(2 - 1, 2 + 1 - 1)) > 0) {
            if (WS_TMP_AUTH == null) WS_TMP_AUTH = "";
            JIBS_tmp_int = WS_TMP_AUTH.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) WS_TMP_AUTH += " ";
            if (WS_AUTH == null) WS_AUTH = "";
            JIBS_tmp_int = WS_AUTH.length();
            for (int i=0;i<2-JIBS_tmp_int;i++) WS_AUTH += " ";
            WS_AUTH = WS_AUTH.substring(0, 2 - 1) + WS_TMP_AUTH.substring(2 - 1, 2 + 1 - 1) + WS_AUTH.substring(2 + 1 - 1);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTXIF() throws IOException,SQLException,Exception {
        BPCRTXIF.INFO.POINTER = BPRTXIF;
        BPCRTXIF.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, CPN_R_TXIF, BPCRTXIF);
        CEP.TRC(SCCGWA, BPCRTXIF.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
        if (BPCRTXIF.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_TXIF_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_BRW, BPCPRMB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (BPCPRMB.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_INQ_TLR, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCKRG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CHK_REL_ORG, BPCPCKRG);
        CEP.TRC(SCCGWA, BPCPCKRG.RC);
        if (BPCPCKRG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPCKRG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCPCKRG.FLAG);
        if (BPCPCKRG.FLAG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ORGREL_NOTFND, BPCFCGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPORDN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_SAME_SUPR, BPCPORDN);
        if (BPCPORDN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPORDN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCGRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = WS_ERR_MSG;
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.LVL.LVL1 = WS_LVL1;
        SCCMSG.LVL.LVL2 = WS_LVL2;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFCGRL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFCGRL = ");
            CEP.TRC(SCCGWA, BPCFCGRL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
