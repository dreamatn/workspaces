package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;
import com.hisun.SC.*;

public class BPZFCSTS {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String CPN_F_STS_TBL_AUTH = "BP-F-STS-TBL-AUTH   ";
    String CPN_R_STS_TBL_AUTH = "BP-R-TSTS-MAINTAIN  ";
    short WS_I = 0;
    short WS_J = 0;
    BPZFCSTS_WS_ERR_MSG WS_ERR_MSG = new BPZFCSTS_WS_ERR_MSG();
    char WS_MSG_TYPE = ' ';
    String WS_INFO = " ";
    BPZFCSTS_WS_LVL WS_LVL = new BPZFCSTS_WS_LVL();
    BPZFCSTS_WS_TSTS_FLD WS_TSTS_FLD = new BPZFCSTS_WS_TSTS_FLD();
    BPZFCSTS_WS_CTL WS_CTL = new BPZFCSTS_WS_CTL();
    char WS_TBL_CSTS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRTSTS BPCRTSTS = new BPCRTSTS();
    BPRPTSTS BPRPTSTS = new BPRPTSTS();
    SCCPRMR SCCPRMR = new SCCPRMR();
    SCCGWA SCCGWA;
    BPCFCSTS BPCFCSTS;
    BPRTLT BPRTLR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFCSTS BPCFCSTS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFCSTS = BPCFCSTS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_CHECK_STUS_WORD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFCSTS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPRTLR = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
    }
    public void B000_CHECK_STUS_WORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFCSTS);
        IBS.init(SCCGWA, BPRPTSTS);
        IBS.init(SCCGWA, SCCPRMR);
        BPRPTSTS.KEY1.TYP = "TSTS ";
        BPRPTSTS.KEY1.KEY.TSTS_APP = BPCFCSTS.AP_MMO;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        BPRPTSTS.KEY1.KEY.TSTS_NO = BPCFCSTS.TBL_NO;
        BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            BPRPTSTS.KEY1.KEY.CHNL = SCCGWA.COMM_AREA.CHNL;
            BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
            S000_CALL_SCZPRMR_CN();
            if (pgmRtn) return;
            if (SCCPRMR.RC.RC_RTNCODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCPRMR.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                    IBS.init(SCCGWA, BPRPTSTS);
                    IBS.init(SCCGWA, SCCPRMR);
                    BPRPTSTS.KEY1.TYP = "TSTS ";
                    BPRPTSTS.KEY1.KEY.TSTS_APP = BPCFCSTS.AP_MMO;
                    BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
                    BPRPTSTS.KEY1.KEY.TSTS_NO = BPCFCSTS.TBL_NO;
                    BPRPTSTS.KEY1.CD = IBS.CLS2CPY(SCCGWA, BPRPTSTS.KEY1.KEY);
                    S000_CALL_SCZPRMR();
                    if (pgmRtn) return;
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCPRMR.RC);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCSTS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        } else {
            S000_CALL_SCZPRMR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "F1");
        if (BPRPTSTS.DATA_TXT.FLD_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STATUS_RECORD_NOFND, BPCFCSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "F2");
        CEP.TRC(SCCGWA, BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[1-1]);
        CEP.TRC(SCCGWA, BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[2-1]);
        CEP.TRC(SCCGWA, BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[3-1]);
        CEP.TRC(SCCGWA, WS_TSTS_FLD.WS_TX_CLASS);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        CEP.TRC(SCCGWA, "== START CIRCLE ==");
        for (WS_I = 1; WS_I <= BPRPTSTS.DATA_TXT.FLD_CNT; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPRTLR.TLR_TYP);
            CEP.TRC(SCCGWA, BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL5);
            CEP.TRC(SCCGWA, BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL6);
            CEP.TRC(SCCGWA, BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL7);
            CEP.TRC(SCCGWA, BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL8);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_NAME);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TSTS_FLD);
            if (WS_TSTS_FLD.WS_TX_CLASS == ' ' 
                || WS_TSTS_FLD.WS_TX_CLASS == BPCFCSTS.TX_CLASS) {
                if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                    || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                    && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                    if (BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL1 == ' ') WS_CTL.WS_CTL1 = 0;
                    else WS_CTL.WS_CTL1 = Short.parseShort(""+BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL1);
                    if (BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL2 == ' ') WS_CTL.WS_CTL23.WS_CTL2 = 0;
                    else WS_CTL.WS_CTL23.WS_CTL2 = Short.parseShort(""+BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL2);
                    if (BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL3 == ' ') WS_CTL.WS_CTL23.WS_CTL3 = 0;
                    else WS_CTL.WS_CTL23.WS_CTL3 = Short.parseShort(""+BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL3);
                    WS_CTL.WS_CTL4 = BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL4;
                } else {
                    if (BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL5 == ' ') WS_CTL.WS_CTL1 = 0;
                    else WS_CTL.WS_CTL1 = Short.parseShort(""+BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL5);
                    if (BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL6 == ' ') WS_CTL.WS_CTL23.WS_CTL2 = 0;
                    else WS_CTL.WS_CTL23.WS_CTL2 = Short.parseShort(""+BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL6);
                    if (BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL7 == ' ') WS_CTL.WS_CTL23.WS_CTL3 = 0;
                    else WS_CTL.WS_CTL23.WS_CTL3 = Short.parseShort(""+BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL7);
                    WS_CTL.WS_CTL4 = BPRPTSTS.DATA_TXT.ITEM_LST.ITEM_LST_DATA[WS_I-1].FLD_CTL8;
                }
                CEP.TRC(SCCGWA, WS_CTL);
                CEP.TRC(SCCGWA, WS_TSTS_FLD.WS_REJ_CODE_COM);
                CEP.TRC(SCCGWA, WS_TSTS_FLD.WS_REJ_CODE_SYS);
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD.substring(WS_TSTS_FLD.WS_FLD_NO - 1, WS_TSTS_FLD.WS_FLD_NO + 1 - 1));
                if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                if (!(BPCFCSTS.STATUS_WORD.substring(WS_TSTS_FLD.WS_FLD_NO - 1, WS_TSTS_FLD.WS_FLD_NO + 1 - 1).trim().length() == 0 
                    && WS_CTL.WS_CTL1 == 0)) {
                    CEP.TRC(SCCGWA, WS_TSTS_FLD.WS_FLD_NO);
                    CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
                    CEP.TRC(SCCGWA, WS_CTL.WS_CTL1);
                    if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                    JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                    for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                    if (!BPCFCSTS.STATUS_WORD.substring(WS_TSTS_FLD.WS_FLD_NO - 1, WS_TSTS_FLD.WS_FLD_NO + 1 - 1).equalsIgnoreCase(WS_CTL.WS_CTL1+"")) {
                        CEP.TRC(SCCGWA, "F11");
                        if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102")) 
                            && !SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
                            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_CTL.WS_CTL23);
                            if (WS_TSTS_FLD.WS_REJ_CODE_COM.WS_REJ_AP_CODE_COM == 0 
                                && !JIBS_tmp_str[1].equalsIgnoreCase("00") 
                                || !IBS.isNumeric(WS_TSTS_FLD.WS_REJ_CODE_COM.WS_REJ_AP_CODE_COM+"")) {
                            } else {
                                if (WS_TSTS_FLD.WS_REJ_CODE_COM.WS_REJ_AP_MMO_COM.trim().length() == 0) {
                                    WS_ERR_MSG.WS_MSG_AP_MMO = BPCFCSTS.AP_MMO;
                                    WS_ERR_MSG.WS_MSG_CODE = WS_TSTS_FLD.WS_REJ_CODE_COM.WS_REJ_AP_CODE_COM;
                                    B010_ERROR_MSG();
                                    if (pgmRtn) return;
                                } else {
                                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TSTS_FLD.WS_REJ_CODE_COM);
                                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
                                    B010_ERROR_MSG();
                                    if (pgmRtn) return;
                                }
                            }
                        } else {
                            JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, WS_CTL.WS_CTL23);
                            if (WS_TSTS_FLD.WS_REJ_CODE_SYS.WS_REJ_AP_CODE_SYS == 0 
                                && !JIBS_tmp_str[1].equalsIgnoreCase("00") 
                                || !IBS.isNumeric(WS_TSTS_FLD.WS_REJ_CODE_SYS.WS_REJ_AP_CODE_SYS+"")) {
                            } else {
                                if (WS_TSTS_FLD.WS_REJ_CODE_SYS.WS_REJ_AP_MMO_SYS.trim().length() == 0) {
                                    WS_ERR_MSG.WS_MSG_AP_MMO = BPCFCSTS.AP_MMO;
                                    WS_ERR_MSG.WS_MSG_CODE = WS_TSTS_FLD.WS_REJ_CODE_SYS.WS_REJ_AP_CODE_SYS;
                                    B010_ERROR_MSG();
                                    if (pgmRtn) return;
                                } else {
                                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TSTS_FLD.WS_REJ_CODE_SYS);
                                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_ERR_MSG);
                                    B010_ERROR_MSG();
                                    if (pgmRtn) return;
                                }
                            }
                        }
                        CEP.TRC(SCCGWA, "F12");
                    }
                }
                CEP.TRC(SCCGWA, WS_CTL.WS_CTL4);
                if (WS_CTL.WS_CTL4 != ' ') {
                    if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
                    JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
                    for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
                    JIBS_tmp_str[0] = "" + WS_CTL.WS_CTL4;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, WS_TSTS_FLD.WS_FLD_NO - 1) + JIBS_tmp_str[0] + BPCFCSTS.STATUS_WORD.substring(WS_TSTS_FLD.WS_FLD_NO + 1 - 1);
                }
                CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
            }
        }
    }
    public void B010_ERROR_MSG() throws IOException,SQLException,Exception {
        SCCMSG.INFO = BPCFCSTS.MSG_INFO;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CTL.WS_CTL4);
        if (JIBS_tmp_str[0].equalsIgnoreCase("00")) {
            WS_MSG_TYPE = 'W';
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CTL.WS_CTL4);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase("99")) {
            CEP.TRC(SCCGWA, "F121");
            WS_MSG_TYPE = 'E';
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "F122");
            if (WS_ERR_MSG.WS_MSG_CODE == 0) {
            } else {
                CEP.TRC(SCCGWA, "F21");
                CEP.TRC(SCCGWA, BPCFCSTS.AP_MMO);
                CEP.TRC(SCCGWA, WS_CTL.WS_CTL23);
                WS_MSG_TYPE = 'A';
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_CTL.WS_CTL23);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_LVL);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SCZPRMR() throws IOException,SQLException,Exception {
        //BPRPTSTS.DATA_LEN = 4634;
        SCCPRMR.DAT_PTR = BPRPTSTS;
        IBS.CALLCPN(SCCGWA, "SC-PARM-READ        ", SCCPRMR);
        if (SCCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCPRMR.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFCSTS.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_STATUS_RECORD_NOFND, BPCFCSTS.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_SCZPRMR_CN() throws IOException,SQLException,Exception {
        //BPRPTSTS.DATA_LEN = 4634;
        SCCPRMR.DAT_PTR = BPRPTSTS;
        IBS.CALLCPN(SCCGWA, "SC-PARM-READ        ", SCCPRMR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
        SCCMSG SCCMSG = new SCCMSG();
        IBS.init(SCCGWA, SCCMSG);
        SCCMSG.MSGID = JIBS_tmp_str[0];
        SCCMSG.TYPE = WS_MSG_TYPE;
        SCCMSG.LVL.LVL1 = (char) WS_LVL.WS_LVL1;
        SCCMSG.LVL.LVL2 = (char) WS_LVL.WS_LVL2;
        SCCMSG.INFO = BPCFCSTS.MSG_INFO;
        CEP.ERR(SCCGWA, SCCMSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFCSTS.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFCSTS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCFCSTS = ");
            CEP.TRC(SCCGWA, BPCFCSTS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
