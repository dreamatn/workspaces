package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCASH {
    String JIBS_tmp_str[] = new String[10];
    DBParm BPTTHIS_RD;
    String CPN_R_HISF = "BP-R-ADW-THIS    ";
    String CPN_R_MAINT_CLBI = "BP-R-ADW-CLBI";
    String CPN_R_MAINT_CLIB = "BP-R-ADW-CLIB";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    int K_MAX_PAR_CNT = 12;
    String K_CPY_BPZSCASH = "BPZSCASH";
    String K_HIS_REMARKS = "CASH EXCHANGE";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_BR = 0;
    int WS_PAR_CNT = 0;
    int WS_SEQ = 0;
    char WS_FND_PAR_FLG = ' ';
    char WS_DATA_OVERFLOW_FLAG = ' ';
    char WS_CASH_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRCLIB BPRCLIB = new BPRCLIB();
    BPRCLBI BPRCLBI = new BPRCLBI();
    BPCOCASH BPCOCASH = new BPCOCASH();
    BPRTHIS BPRTHIS = new BPRTHIS();
    BPCTHISF BPCTHISF = new BPCTHISF();
    BPCTLIBF BPCTLIBF = new BPCTLIBF();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCTLBIF BPCTLBIF = new BPCTLBIF();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCSCASH BPCSCASH;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA, BPCSCASH BPCSCASH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCASH = BPCSCASH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSCASH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B020_UPDATE_CASH_IN_INF();
        B070_UPDATE_CASH_PAR_IN();
        B050_WRITE_CASH_IN_THIS();
        B030_UPDATE_CASH_OUT_INF();
        B080_UPDATE_CASH_PAR_OUT();
        B060_WRITE_CASH_OUT_THIS();
        B040_TRANS_DATA_OUTPUT();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_UPDATE_CASH_IN_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPRCLIB);
        IBS.init(SCCGWA, BPCTLVBF);
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.PLBOX_TP = BPCSCASH.PLBOX_TYPE;
        BPRTLVB.CRNT_TLR = BPCSCASH.TLR;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLVBF.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_TLVB_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPRCLIB.KEY.CCY = BPCSCASH.CCY;
        BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
        BPRCLIB.KEY.CASH_TYP = BPCSCASH.IN_CASH_TYPE;
        BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPCTLIBF.INFO.FUNC = 'R';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPRCLIB.GD_AMT);
        if (BPRCLIB.GD_AMT < 0 
            || BPRCLIB.BD_AMT < 0 
            || BPRCLIB.HBD_AMT < 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            if (BPCSCASH.IN_CS_KIND == '0' 
                && BPRCLIB.GD_AMT < BPCSCASH.EXCH_AMT 
                || BPCSCASH.IN_CS_KIND == '2' 
                && BPRCLIB.BD_AMT < BPCSCASH.EXCH_AMT 
                || BPCSCASH.IN_CS_KIND == '3' 
                && BPRCLIB.HBD_AMT < BPCSCASH.EXCH_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
            }
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE > BPRCLIB.NEW_DT) {
            BPRCLIB.L_TLT_AMT = BPRCLIB.BAL;
            BPRCLIB.L_GD_AMT = BPRCLIB.GD_AMT;
            BPRCLIB.L_BD_AMT = BPRCLIB.BD_AMT;
            BPRCLIB.L_HBD_AMT = BPRCLIB.HBD_AMT;
            CEP.TRC(SCCGWA, SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE);
            BPRCLIB.LAST_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            BPRCLIB.BAL = BPRCLIB.BAL + BPCSCASH.EXCH_AMT;
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT + BPCSCASH.EXCH_AMT;
            }
            if (BPCSCASH.IN_CS_KIND == '0') {
                BPRCLIB.GD_AMT = BPRCLIB.GD_AMT + BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT + BPCSCASH.EXCH_AMT;
                }
            } else if (BPCSCASH.IN_CS_KIND == '2') {
                BPRCLIB.BD_AMT = BPRCLIB.BD_AMT + BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_BD_AMT = BPRCLIB.L_BD_AMT + BPCSCASH.EXCH_AMT;
                }
            } else if (BPCSCASH.IN_CS_KIND == '3') {
                BPRCLIB.HBD_AMT = BPRCLIB.HBD_AMT + BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_HBD_AMT = BPRCLIB.L_HBD_AMT + BPCSCASH.EXCH_AMT;
                }
            } else {
            }
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
                S000_ERR_MSG_PROC();
            }
        } else {
            BPRCLIB.BAL = BPRCLIB.BAL - BPCSCASH.EXCH_AMT;
            if (BPRCLIB.BAL < 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
            }
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT - BPCSCASH.EXCH_AMT;
            }
            if (BPCSCASH.IN_CS_KIND == '0') {
                BPRCLIB.GD_AMT = BPRCLIB.GD_AMT - BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT - BPCSCASH.EXCH_AMT;
                }
            } else if (BPCSCASH.IN_CS_KIND == '2') {
                BPRCLIB.BD_AMT = BPRCLIB.BD_AMT - BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_BD_AMT = BPRCLIB.L_BD_AMT - BPCSCASH.EXCH_AMT;
                }
            } else if (BPCSCASH.IN_CS_KIND == '3') {
                BPRCLIB.HBD_AMT = BPRCLIB.HBD_AMT - BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_HBD_AMT = BPRCLIB.L_HBD_AMT - BPCSCASH.EXCH_AMT;
                }
            } else {
            }
        }
        IBS.init(SCCGWA, BPCTLIBF.RC);
        BPRCLIB.BAL_FLG = 'N';
        BPRCLIB.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.NEW_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.UPD_TLR = BPCSCASH.TLR;
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_UPDATE_CASH_OUT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLIBF);
        IBS.init(SCCGWA, BPRCLIB);
        BPRCLIB.KEY.CCY = BPCSCASH.CCY;
        BPRCLIB.KEY.BR = BPRTLVB.KEY.BR;
        BPRCLIB.KEY.CASH_TYP = BPCSCASH.IN_CASH_TYPE;
        BPRCLIB.KEY.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPCTLIBF.INFO.FUNC = 'R';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
        }
        if (BPRCLIB.GD_AMT < 0 
            || BPRCLIB.BD_AMT < 0 
            || BPRCLIB.HBD_AMT < 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            if (BPCSCASH.OUT_CS_KIND == '0' 
                && BPRCLIB.GD_AMT < BPCSCASH.EXCH_AMT 
                || BPCSCASH.OUT_CS_KIND == '2' 
                && BPRCLIB.BD_AMT < BPCSCASH.EXCH_AMT 
                || BPCSCASH.OUT_CS_KIND == '3' 
                && BPRCLIB.HBD_AMT < BPCSCASH.EXCH_AMT) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
            }
        }
        if (SCCGWA.COMM_AREA.AC_DATE > BPRCLIB.NEW_DT) {
            BPRCLIB.L_TLT_AMT = BPRCLIB.BAL;
            BPRCLIB.L_GD_AMT = BPRCLIB.GD_AMT;
            BPRCLIB.L_BD_AMT = BPRCLIB.BD_AMT;
            BPRCLIB.L_HBD_AMT = BPRCLIB.HBD_AMT;
            BPRCLIB.LAST_DT = SCRCWA.AC_DATE_AREA[1-1].LAST_AC_DATE;
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPRCLIB.BAL = BPRCLIB.BAL + BPCSCASH.EXCH_AMT;
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT + BPCSCASH.EXCH_AMT;
            }
            if (BPCSCASH.OUT_CS_KIND == '0') {
                BPRCLIB.GD_AMT = BPRCLIB.GD_AMT + BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT + BPCSCASH.EXCH_AMT;
                }
            } else if (BPCSCASH.OUT_CS_KIND == '2') {
                BPRCLIB.BD_AMT = BPRCLIB.BD_AMT + BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_BD_AMT = BPRCLIB.L_BD_AMT + BPCSCASH.EXCH_AMT;
                }
            } else if (BPCSCASH.OUT_CS_KIND == '3') {
                BPRCLIB.HBD_AMT = BPRCLIB.HBD_AMT + BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_HBD_AMT = BPRCLIB.L_HBD_AMT + BPCSCASH.EXCH_AMT;
                }
            } else {
            }
            if (WS_DATA_OVERFLOW_FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
                S000_ERR_MSG_PROC();
            }
        } else {
            BPRCLIB.BAL = BPRCLIB.BAL - BPCSCASH.EXCH_AMT;
            if (BPRCLIB.BAL < 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CASH_NOT_ENOUGH);
            }
            if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                BPRCLIB.L_TLT_AMT = BPRCLIB.L_TLT_AMT - BPCSCASH.EXCH_AMT;
            }
            if (BPCSCASH.OUT_CS_KIND == '0') {
                BPRCLIB.GD_AMT = BPRCLIB.GD_AMT - BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_GD_AMT = BPRCLIB.L_GD_AMT - BPCSCASH.EXCH_AMT;
                }
            } else if (BPCSCASH.OUT_CS_KIND == '2') {
                BPRCLIB.BD_AMT = BPRCLIB.BD_AMT - BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_BD_AMT = BPRCLIB.L_BD_AMT - BPCSCASH.EXCH_AMT;
                }
            } else if (BPCSCASH.OUT_CS_KIND == '3') {
                BPRCLIB.HBD_AMT = BPRCLIB.HBD_AMT - BPCSCASH.EXCH_AMT;
                if (SCCGWA.COMM_AREA.AC_DATE < BPRCLIB.NEW_DT) {
                    BPRCLIB.L_HBD_AMT = BPRCLIB.L_HBD_AMT - BPCSCASH.EXCH_AMT;
                }
            } else {
            }
        }
        IBS.init(SCCGWA, BPCTLIBF.RC);
        BPRCLIB.BAL_FLG = 'N';
        BPRCLIB.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.NEW_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRCLIB.UPD_TLR = BPCSCASH.TLR;
        BPCTLIBF.INFO.FUNC = 'U';
        BPCTLIBF.POINTER = BPRCLIB;
        BPCTLIBF.LEN = 352;
        S000_CALL_BPZTLIBF();
        if (BPCTLIBF.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PLBOX_NO_THIS_CCY;
            S000_ERR_MSG_PROC();
        }
    }
    public void B070_UPDATE_CASH_PAR_IN() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
            if (BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_PVAL > 0 
                && BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_NUM > 0) {
                IBS.init(SCCGWA, BPCTLBIF);
                IBS.init(SCCGWA, BPRCLBI);
                CEP.TRC(SCCGWA, BPCSCASH.BR);
                CEP.TRC(SCCGWA, BPRCLIB.KEY.PLBOX_NO);
                CEP.TRC(SCCGWA, BPRCLIB.KEY.CASH_TYP);
                CEP.TRC(SCCGWA, BPCSCASH.CCY);
                BPRCLBI.KEY.VB_BR = BPCSCASH.BR;
                BPRCLBI.KEY.PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPRCLIB.KEY.CASH_TYP;
                BPRCLBI.KEY.CCY = BPCSCASH.CCY;
                BPRCLBI.KEY.PAR_VAL = BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_PVAL;
                BPRCLBI.KEY.M_FLG = BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_M_FLG;
                BPCTLBIF.INFO.FUNC = 'R';
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                S000_CALL_BPZTLBIF();
                if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
                    if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_THIS_PVAL;
                        S000_ERR_MSG_PROC();
                    } else {
                        if (BPCSCASH.IN_CS_KIND == '0' 
                            && BPRCLBI.GD_NUM < BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_PVAL 
                            || BPCSCASH.IN_CS_KIND == '2' 
                            && BPRCLBI.BD_NUM < BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_PVAL 
                            || BPCSCASH.IN_CS_KIND == '3' 
                            && BPRCLBI.HBD_NUM < BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_PVAL) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                            S000_ERR_MSG_PROC();
                        }
                    }
                }
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
                    IBS.init(SCCGWA, BPCTLBIF);
                    IBS.init(SCCGWA, BPRCLBI);
                    BPRCLBI.KEY.VB_BR = BPCSCASH.BR;
                    BPRCLBI.KEY.PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
                    BPRCLBI.KEY.CASH_TYP = BPRCLIB.KEY.CASH_TYP;
                    BPRCLBI.KEY.CCY = BPCSCASH.CCY;
                    BPRCLBI.KEY.PAR_VAL = BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_PVAL;
                    BPRCLBI.KEY.M_FLG = BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_M_FLG;
                    if (BPCSCASH.IN_CS_KIND == '0') {
                        BPRCLBI.GD_NUM = BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                    } else if (BPCSCASH.IN_CS_KIND == '2') {
                        BPRCLBI.BD_NUM = BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                    } else if (BPCSCASH.IN_CS_KIND == '3') {
                        BPRCLBI.HBD_NUM = BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                    } else {
                    }
                    BPCTLBIF.INFO.FUNC = 'A';
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    S000_CALL_BPZTLBIF();
                } else {
                    if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                        if (BPCSCASH.IN_CS_KIND == '0') {
                            BPRCLBI.GD_NUM = BPRCLBI.GD_NUM + BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                        } else if (BPCSCASH.IN_CS_KIND == '2') {
                            BPRCLBI.BD_NUM = BPRCLBI.BD_NUM + BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                        } else if (BPCSCASH.IN_CS_KIND == '3') {
                            BPRCLBI.HBD_NUM = BPRCLBI.HBD_NUM + BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                        } else {
                        }
                    } else {
                        if (BPCSCASH.IN_CS_KIND == '0') {
                            BPRCLBI.GD_NUM = BPRCLBI.GD_NUM - BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                        } else if (BPCSCASH.IN_CS_KIND == '2') {
                            BPRCLBI.BD_NUM = BPRCLBI.BD_NUM - BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                        } else if (BPCSCASH.IN_CS_KIND == '3') {
                            BPRCLBI.HBD_NUM = BPRCLBI.HBD_NUM - BPCSCASH.IN_PVAL_INFO[WS_CNT-1].IN_NUM;
                        } else {
                        }
                    }
                    if (BPRCLBI.GD_NUM == 0 
                        && BPRCLBI.BD_NUM == 0 
                        && BPRCLBI.HBD_NUM == 0) {
                        BPCTLBIF.INFO.FUNC = 'D';
                    } else {
                        BPCTLBIF.INFO.FUNC = 'U';
                    }
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    IBS.init(SCCGWA, BPCTLIBF.RC);
                    S000_CALL_BPZTLBIF();
                }
            }
        }
    }
    public void B080_UPDATE_CASH_PAR_OUT() throws IOException,SQLException,Exception {
        for (WS_CNT = 1; WS_CNT <= K_MAX_PAR_CNT; WS_CNT += 1) {
            if (BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_PVAL > 0 
                && BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM > 0) {
                IBS.init(SCCGWA, BPCTLBIF);
                IBS.init(SCCGWA, BPRCLBI);
                BPRCLBI.KEY.VB_BR = BPCSCASH.BR;
                BPRCLBI.KEY.PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
                BPRCLBI.KEY.CASH_TYP = BPRCLIB.KEY.CASH_TYP;
                BPRCLBI.KEY.CCY = BPCSCASH.CCY;
                BPRCLBI.KEY.PAR_VAL = BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_PVAL;
                BPRCLBI.KEY.M_FLG = BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_M_FLG;
                BPCTLBIF.INFO.FUNC = 'R';
                BPCTLBIF.POINTER = BPRCLBI;
                BPCTLBIF.LEN = 222;
                S000_CALL_BPZTLBIF();
                if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
                    if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NO_THIS_PVAL;
                        S000_ERR_MSG_PROC();
                    } else {
                        if (BPCSCASH.OUT_CS_KIND == '0' 
                            && BPRCLBI.GD_NUM < BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM 
                            || BPCSCASH.OUT_CS_KIND == '2' 
                            && BPRCLBI.BD_NUM < BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM 
                            || BPCSCASH.OUT_CS_KIND == '3' 
                            && BPRCLBI.HBD_NUM < BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PNUM_NOT_ENOUGH;
                            S000_ERR_MSG_PROC();
                        }
                    }
                }
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
                    IBS.init(SCCGWA, BPCTLBIF);
                    IBS.init(SCCGWA, BPRCLBI);
                    BPRCLBI.KEY.VB_BR = BPCSCASH.BR;
                    BPRCLBI.KEY.PLBOX_NO = BPRCLIB.KEY.PLBOX_NO;
                    BPRCLBI.KEY.CASH_TYP = BPRCLIB.KEY.CASH_TYP;
                    BPRCLBI.KEY.CCY = BPCSCASH.CCY;
                    BPRCLBI.KEY.PAR_VAL = BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_PVAL;
                    BPRCLBI.KEY.M_FLG = BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_M_FLG;
                    if (BPCSCASH.OUT_CS_KIND == '0') {
                        BPRCLBI.GD_NUM = BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM;
                    } else if (BPCSCASH.OUT_CS_KIND == '2') {
                        BPRCLBI.BD_NUM = BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM;
                    } else if (BPCSCASH.OUT_CS_KIND == '3') {
                        BPRCLBI.HBD_NUM = BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM;
                    } else {
                    }
                    BPCTLBIF.INFO.FUNC = 'A';
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    S000_CALL_BPZTLBIF();
                } else {
                    if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                        if (BPCSCASH.OUT_CS_KIND == '0') {
                            BPRCLBI.GD_NUM = BPRCLBI.GD_NUM + BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM;
                        } else if (BPCSCASH.OUT_CS_KIND == '2') {
                            BPRCLBI.BD_NUM = BPRCLBI.BD_NUM + BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM;
                        } else if (BPCSCASH.OUT_CS_KIND == '3') {
                            BPRCLBI.HBD_NUM = BPRCLBI.HBD_NUM + BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM;
                        } else {
                        }
                    } else {
                        if (BPCSCASH.OUT_CS_KIND == '0') {
                            BPRCLBI.GD_NUM = BPRCLBI.GD_NUM - BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM;
                        } else if (BPCSCASH.OUT_CS_KIND == '2') {
                            BPRCLBI.BD_NUM = BPRCLBI.BD_NUM - BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM;
                        } else if (BPCSCASH.OUT_CS_KIND == '3') {
                            BPRCLBI.HBD_NUM = BPRCLBI.HBD_NUM - BPCSCASH.OUT_PVAL_INFO[WS_CNT-1].OUT_NUM;
                        } else {
                        }
                    }
                    if (BPRCLBI.GD_NUM == 0 
                        && BPRCLBI.BD_NUM == 0 
                        && BPRCLBI.HBD_NUM == 0) {
                        BPCTLBIF.INFO.FUNC = 'D';
                    } else {
                        BPCTLBIF.INFO.FUNC = 'U';
                    }
                    BPCTLBIF.POINTER = BPRCLBI;
                    BPCTLBIF.LEN = 222;
                    IBS.init(SCCGWA, BPCTLIBF.RC);
                    S000_CALL_BPZTLBIF();
                }
            }
        }
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSCASH.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOCASH;
        SCCFMT.DATA_LEN = 650;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_WRITE_CASH_IN_THIS() throws IOException,SQLException,Exception {
        WS_CASH_FLG = 'C';
        B070_WRITE_THIS();
    }
    public void B060_WRITE_CASH_OUT_THIS() throws IOException,SQLException,Exception {
        WS_CASH_FLG = 'D';
        B070_WRITE_THIS();
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCASH);
        BPCOCASH.PLBOX_TYPE = BPCSCASH.PLBOX_TYPE;
        BPCOCASH.CCY = BPCSCASH.CCY;
        BPCOCASH.EXCH_AMT = BPCSCASH.EXCH_AMT;
        BPCOCASH.IN_CASH_TYPE = BPCSCASH.IN_CASH_TYPE;
        BPCOCASH.IN_CS_KIND = BPCSCASH.IN_CS_KIND;
        BPCOCASH.OUT_CASH_TYPE = BPCSCASH.OUT_CASH_TYPE;
        BPCOCASH.OUT_CS_KIND = BPCSCASH.OUT_CS_KIND;
        for (WS_PAR_CNT = 1; WS_PAR_CNT <= K_MAX_PAR_CNT; WS_PAR_CNT += 1) {
            BPCOCASH.IN_PAR_INFO[WS_PAR_CNT-1].IN_PVAL = BPCSCASH.IN_PVAL_INFO[WS_PAR_CNT-1].IN_PVAL;
            BPCOCASH.IN_PAR_INFO[WS_PAR_CNT-1].IN_NUM = BPCSCASH.IN_PVAL_INFO[WS_PAR_CNT-1].IN_NUM;
            BPCOCASH.IN_PAR_INFO[WS_PAR_CNT-1].IN_M_FLG = BPCSCASH.IN_PVAL_INFO[WS_PAR_CNT-1].IN_M_FLG;
            BPCOCASH.OUT_PAR_INFO[WS_PAR_CNT-1].OUT_PVAL = BPCSCASH.OUT_PVAL_INFO[WS_PAR_CNT-1].OUT_PVAL;
            BPCOCASH.OUT_PAR_INFO[WS_PAR_CNT-1].OUT_NUM = BPCSCASH.OUT_PVAL_INFO[WS_PAR_CNT-1].OUT_NUM;
            BPCOCASH.OUT_PAR_INFO[WS_PAR_CNT-1].OUT_M_FLG = BPCSCASH.OUT_PVAL_INFO[WS_PAR_CNT-1].OUT_M_FLG;
        }
    }
    public void B070_WRITE_THIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHIS);
        IBS.init(SCCGWA, BPCTHISF);
        BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRTHIS.KEY.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
        S000_GET_SEQ();
        IBS.init(SCCGWA, BPRTHIS);
        BPRTHIS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRTHIS.KEY.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
        WS_SEQ += 1;
        BPRTHIS.KEY.SEQ = WS_SEQ;
        BPRTHIS.AP_CODE = SCCGWA.COMM_AREA.TR_MMO;
        BPRTHIS.TR_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        if (WS_CASH_FLG == 'D') {
            BPRTHIS.DC_FLG = 'D';
            BPRTHIS.PAY_PBNO = BPRTLVB.KEY.PLBOX_NO;
        } else {
            BPRTHIS.DC_FLG = 'C';
            BPRTHIS.RCE_PBNO = BPRTLVB.KEY.PLBOX_NO;
        }
        BPRTHIS.CCY = BPCSCASH.CCY;
        BPRTHIS.AMT = BPCSCASH.EXCH_AMT;
        BPRTHIS.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRTHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRTHIS.STS = '0';
        BPCTHISF.INFO.FUNC = 'A';
        BPCTHISF.POINTER = BPRTHIS;
        BPCTHISF.LEN = 959;
        S000_CALL_BPZTHISF();
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPZSCASH;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 684;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCSCASH;
        S000_CALL_BPZPNHIS();
    }
    public void S000_GET_SEQ() throws IOException,SQLException,Exception {
        BPTTHIS_RD = new DBParm();
        BPTTHIS_RD.TableName = "BPTTHIS";
        BPTTHIS_RD.set = "WS-SEQ=COUNT(*)";
        BPTTHIS_RD.where = "'DATE' = :BPRTHIS.KEY.DATE "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO";
        IBS.GROUP(SCCGWA, BPRTHIS, this, BPTTHIS_RD);
    }
    public void S000_CALL_BPZTHISF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_HISF, BPCTHISF);
        if (BPCTHISF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTHISF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZTLBIF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLBI, BPCTLBIF);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
        if (BPCTLBIF.RC.RC_CODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CLBI_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTLBIF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLIBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_MAINT_CLIB, BPCTLIBF);
        CEP.TRC(SCCGWA, BPCTLIBF.RC.RC_CODE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
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
