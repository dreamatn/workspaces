package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class IBZCASH {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm IBTSCASH_BR = new brParm();
    DBParm IBTSCASH_RD;
    DBParm IBTSCDTL_RD;
    DBParm IBTMST_RD;
    DBParm IBTMANIP_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMPAG_WS_VARIABLES WS_VARIABLES = new SCCMPAG_WS_VARIABLES();
    SCCMPAG_WS_VAR1 WS_VAR1 = new SCCMPAG_WS_VAR1();
    SCCMPAG_WS_VAR2 WS_VAR2 = new SCCMPAG_WS_VAR2();
    SCCMPAG_WS_CONDITION_FLAG WS_CONDITION_FLAG = new SCCMPAG_WS_CONDITION_FLAG();
    IBCMSG_ERROR_MSG ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCOCASH IBCOCASH = new IBCOCASH();
    IBCQIDNM IBCQIDNM = new IBCQIDNM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    DDCIMMST DDCIMMST = new DDCIMMST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    IBRSCASH IBRSCASH = new IBRSCASH();
    IBRMST IBRMST = new IBRMST();
    IBRMANIP IBRMANIP = new IBRMANIP();
    IBRSCDTL IBRSCDTL = new IBRSCDTL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    IBCCASH IBCCASH;
    public void MP(SCCGWA SCCGWA, IBCCASH IBCCASH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCCASH = IBCCASH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZCASH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_CONDITION_FLAG);
        IBS.init(SCCGWA, WS_VAR1);
        IBS.init(SCCGWA, WS_VAR2);
        IBCCASH.RC.RC_MMO = " ";
        IBCCASH.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCASH.FUNC);
        if (IBCCASH.FUNC == INQUIRE) {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_INQ_CORP_CASH();
            if (pgmRtn) return;
        } else if (IBCCASH.FUNC == QUERY) {
            B011_CHECK_INPUT();
            if (pgmRtn) return;
            B030_INQ_CORP_CASH();
            if (pgmRtn) return;
        } else if (IBCCASH.FUNC == CLOS_CHK) {
            B012_CHECK_INPUT();
            if (pgmRtn) return;
            B040_INQ_CLOSE_CHECK();
            if (pgmRtn) return;
        } else if (IBCCASH.FUNC == MODIFY) {
            B013_CHECK_INPUT();
            if (pgmRtn) return;
            B050_CASH_UPDATE();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCCASH.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        WS_VARIABLES.POST_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        B010_02_GET_CORP_ID();
        if (pgmRtn) return;
    }
    public void B010_02_GET_CORP_ID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_VARIABLES.POST_CTR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        if (BPCPQORG.TYP == null) BPCPQORG.TYP = "";
        JIBS_tmp_int = BPCPQORG.TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCPQORG.TYP += " ";
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            || !BPCPQORG.VIL_TYP.equalsIgnoreCase("043") 
            || BPCPQORG.TYP.substring(0, 1).equalsIgnoreCase("0")) {
            WS_VARIABLES.CORP_ID = BPCPQORG.VIL_TYP;
            WS_VARIABLES.CORP_TRI_ID = BPCPQORG.TRA_TYP;
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.NON_VIL_TRA, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B011_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCASH.VOSTRO_AC);
        if (IBCCASH.VOSTRO_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.VOSTRO_AC_M, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B012_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCASH.POST_CTR);
        CEP.TRC(SCCGWA, IBCCASH.CORP_ID);
        if (IBCCASH.POST_CTR == 0 
            && IBCCASH.CORP_ID.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BR_CORP_INPUT_ONE, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B013_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCCASH.POST_CTR);
        CEP.TRC(SCCGWA, IBCCASH.CORP_ID);
        if (IBCCASH.POST_CTR == 0 
            && IBCCASH.CORP_ID.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BR_CORP_INPUT_ONE, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCCASH.CCY);
        if (IBCCASH.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.CCY, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCCASH.SIGN);
        if (IBCCASH.SIGN != 'C' 
            && IBCCASH.SIGN != 'D') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.DRCR_SIGN_M, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCCASH.AMT);
        if (IBCCASH.AMT == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.AMT, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_CORP_CASH() throws IOException,SQLException,Exception {
        S000_INIT_MPAG();
        if (pgmRtn) return;
        if (BPCPQORG.TYP == null) BPCPQORG.TYP = "";
        JIBS_tmp_int = BPCPQORG.TYP.length();
        for (int i=0;i<2-JIBS_tmp_int;i++) BPCPQORG.TYP += " ";
        if (!BPCPQORG.TYP.substring(0, 1).equalsIgnoreCase("0")) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = IBCCASH.POST_CTR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
            CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
            IBS.init(SCCGWA, IBRSCASH);
            IBRSCASH.KEY.CORP_ID = BPCPQORG.VIL_TYP;
            IBRSCASH.KEY.CORP_TRI_ID = BPCPQORG.TRA_TYP;
            T000_STARTBR_SCASH1();
            if (pgmRtn) return;
            T000_READNEXT_IBTSCASH();
            if (pgmRtn) return;
            if (WS_CONDITION_FLAG.TABLE_REC == 'N') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.NOTFND_SCASH, IBCCASH.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            while (WS_CONDITION_FLAG.TABLE_REC != 'N' 
                && SCCMPAG.FUNC != 'E') {
                B020_01_INQ_CORP_CASH();
                if (pgmRtn) return;
                T000_READNEXT_IBTSCASH();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, IBRSCASH);
            T000_STARTBR_SCASH2();
            if (pgmRtn) return;
            T000_READNEXT_IBTSCASH();
            if (pgmRtn) return;
            if (WS_CONDITION_FLAG.TABLE_REC == 'N') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.NOTFND_SCASH, IBCCASH.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            while (WS_CONDITION_FLAG.TABLE_REC != 'N' 
                && SCCMPAG.FUNC != 'E') {
                B020_01_INQ_CORP_CASH();
                if (pgmRtn) return;
                T000_READNEXT_IBTSCASH();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_01_INQ_CORP_CASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = IBRSCASH.VOSTRO_AC;
        DDCIQBAL.DATA.CCY = IBRSCASH.KEY.CCY;
        if (IBRSCASH.KEY.CCY.equalsIgnoreCase("156")) {
            DDCIQBAL.DATA.CCY_TYPE = '1';
        } else {
            DDCIQBAL.DATA.CCY_TYPE = '2';
        }
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBRSCASH.AC_NO;
        T000_READ_IBTMST();
        if (pgmRtn) return;
        if (WS_CONDITION_FLAG.TABLE_REC == 'Y') {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = IBRMST.POST_CTR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
        }
        IBS.init(SCCGWA, IBCOCASH);
        IBCOCASH.CORP_ID = IBRSCASH.KEY.CORP_ID;
        IBCOCASH.CORP_TRI_ID = IBRSCASH.KEY.CORP_TRI_ID;
        IBCOCASH.CORP_ATTR = IBRSCASH.CORP_ATTR;
        IBCOCASH.BANK_NAME = BPCPQORG.CHN_NM;
        IBCOCASH.CCY = IBRSCASH.KEY.CCY;
        IBCOCASH.VOSTRO_AC = IBRSCASH.VOSTRO_AC;
        IBCOCASH.AC_NO = IBRSCASH.AC_NO;
        IBCOCASH.L_TXN_AMT = IBRSCASH.L_TXN_AMT;
        IBCOCASH.TXN_AMT = IBRSCASH.TXN_AMT;
        IBCOCASH.CURR_AMT = DDCIQBAL.DATA.AVL_BAL + IBRSCASH.TXN_AMT;
        CEP.TRC(SCCGWA, IBRSCASH.TXN_AMT);
        CEP.TRC(SCCGWA, IBCOCASH.CURR_AMT);
        S000_WRITE_QUEUE();
        if (pgmRtn) return;
    }
    public void B030_INQ_CORP_CASH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRSCASH);
        IBRSCASH.VOSTRO_AC = IBCCASH.VOSTRO_AC;
        T000_STARTBR_SCASH3();
        if (pgmRtn) return;
        T000_READNEXT_IBTSCASH();
        if (pgmRtn) return;
        if (WS_CONDITION_FLAG.TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.NOTFND_SCASH, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBCCASH.CORP_ID = IBRSCASH.KEY.CORP_ID;
        IBCCASH.CCY = IBRSCASH.KEY.CCY;
        IBCCASH.CORP_ATTR = IBRSCASH.CORP_ATTR;
        IBCCASH.AC_NO = IBRSCASH.AC_NO;
        IBCCASH.L_TOT_AMT = IBRSCASH.L_TXN_AMT;
        IBCCASH.TOT_AMT = IBRSCASH.TXN_AMT;
        IBCCASH.UPD_DATE = IBRSCASH.UPD_DATE;
    }
    public void B040_INQ_CLOSE_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMANIP);
        IBRMANIP.IPT_BR = IBCCASH.POST_CTR;
        T000_READ_IBTMANIP();
        if (pgmRtn) return;
        if (WS_CONDITION_FLAG.TABLE_REC == 'Y') {
            IBCCASH.CLOSE_FLAG = 'N';
        } else {
            IBCCASH.CLOSE_FLAG = 'Y';
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBCCASH.POST_CTR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            IBS.init(SCCGWA, IBRSCASH);
            if (IBCCASH.CORP_ID.trim().length() > 0) {
                IBRSCASH.KEY.CORP_ID = IBCCASH.CORP_ID;
            } else {
                WS_VARIABLES.POST_CTR = IBCCASH.POST_CTR;
                B010_02_GET_CORP_ID();
                if (pgmRtn) return;
                IBRSCASH.KEY.CORP_ID = WS_VARIABLES.CORP_ID;
            }
            T000_STARTBR_SCASH1();
            if (pgmRtn) return;
            T000_READNEXT_IBTSCASH();
            if (pgmRtn) return;
            while (WS_CONDITION_FLAG.TABLE_REC != 'N') {
                IBS.init(SCCGWA, IBRMST);
                IBRMST.KEY.AC_NO = IBRSCASH.AC_NO;
                T000_READ_IBTMST();
                if (pgmRtn) return;
                if (IBRMST.POST_CTR == IBCCASH.POST_CTR) {
                    IBS.init(SCCGWA, DDCIQBAL);
                    DDCIQBAL.DATA.AC = IBRSCASH.VOSTRO_AC;
                    DDCIQBAL.DATA.CCY = IBRSCASH.KEY.CCY;
                    if (IBRSCASH.KEY.CCY.equalsIgnoreCase("156")) {
                        DDCIQBAL.DATA.CCY_TYPE = '1';
                    } else {
                        DDCIQBAL.DATA.CCY_TYPE = '2';
                    }
                    S000_CALL_DDZIQBAL();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, IBRMST.VALUE_BAL);
                    CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
                    if (IBRMST.VALUE_BAL != DDCIQBAL.DATA.CURR_BAL) {
                        IBCCASH.CHECK_FLAG = 'A';
                    } else {
                        IBCCASH.CHECK_FLAG = 'B';
                    }
                } else {
                    IBCCASH.CHECK_FLAG = 'B';
                }
                T000_READNEXT_IBTSCASH();
                if (pgmRtn) return;
            }
        } else {
            IBCCASH.CHECK_FLAG = 'B';
        }
        CEP.TRC(SCCGWA, IBCCASH.CHECK_FLAG);
        CEP.TRC(SCCGWA, IBCCASH.CLOSE_FLAG);
    }
    public void B050_CASH_UPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRSCASH);
        WS_VARIABLES.POST_CTR = IBCCASH.POST_CTR;
        B050_01_GET_CORP_ID();
        if (pgmRtn) return;
        IBRSCASH.KEY.CORP_ID = "" + IBCCASH.POST_CTR;
        JIBS_tmp_int = IBRSCASH.KEY.CORP_ID.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) IBRSCASH.KEY.CORP_ID = "0" + IBRSCASH.KEY.CORP_ID;
        IBRSCASH.KEY.CORP_TRI_ID = WS_VARIABLES.CORP_TRI_ID;
        IBRSCASH.KEY.CCY = IBCCASH.CCY;
        CEP.TRC(SCCGWA, IBRSCASH.KEY.CORP_ID);
        CEP.TRC(SCCGWA, IBRSCASH.KEY.CORP_TRI_ID);
        CEP.TRC(SCCGWA, IBRSCASH.KEY.CCY);
        T000_READUPD_IBTSCASH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = IBRSCASH.VOSTRO_AC;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
        if (DDCIMMST.DATA.AC_STS == 'C') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.L_CLOSED, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.AC_DATE >= IBRSCASH.UPD_DATE) {
            WS_VAR1.OLD_TXN_AMT = IBRSCASH.TXN_AMT;
            if (IBRSCASH.TXN_AMT > 0) {
                WS_VAR1.DRCR_FLG1 = 'D';
            } else {
                WS_VAR1.DRCR_FLG1 = 'C';
            }
        } else {
            WS_VAR1.OLD_TXN_AMT = IBRSCASH.L_TXN_AMT;
            if (IBRSCASH.L_TXN_AMT > 0) {
                WS_VAR1.DRCR_FLG1 = 'D';
            } else {
                WS_VAR1.DRCR_FLG1 = 'C';
            }
        }
        CEP.TRC(SCCGWA, IBRSCASH.TXN_AMT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, IBRSCASH.UPD_DATE);
        CEP.TRC(SCCGWA, IBCCASH.SIGN);
        if (SCCGWA.COMM_AREA.AC_DATE > IBRSCASH.UPD_DATE) {
            CEP.TRC(SCCGWA, IBRSCASH.TXN_AMT);
            IBRSCASH.L_TXN_AMT = IBRSCASH.TXN_AMT;
            CEP.TRC(SCCGWA, IBRSCASH.L_TXN_AMT);
            IBRSCASH.TXN_AMT = 0;
            CEP.TRC(SCCGWA, IBRSCASH.TXN_AMT);
            IBRSCASH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRSCASH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            IBRSCASH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        CEP.TRC(SCCGWA, IBRSCASH.UPD_DATE);
        if (SCCGWA.COMM_AREA.AC_DATE == IBRSCASH.UPD_DATE) {
            IBRSCASH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            IBRSCASH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        CEP.TRC(SCCGWA, "111");
        if (SCCGWA.COMM_AREA.AC_DATE < IBRSCASH.UPD_DATE) {
            if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
                if (IBCCASH.SIGN == 'D') {
                    IBRSCASH.L_TXN_AMT = IBRSCASH.L_TXN_AMT - IBCCASH.AMT;
                } else {
                    IBRSCASH.L_TXN_AMT = IBRSCASH.L_TXN_AMT + IBCCASH.AMT;
                }
                CEP.TRC(SCCGWA, "NORMAL1");
                CEP.TRC(SCCGWA, IBRSCASH.L_TXN_AMT);
            } else {
                if (IBCCASH.SIGN == 'D') {
                    IBRSCASH.L_TXN_AMT = IBRSCASH.L_TXN_AMT + IBCCASH.AMT;
                } else {
                    IBRSCASH.L_TXN_AMT = IBRSCASH.L_TXN_AMT - IBCCASH.AMT;
                }
                CEP.TRC(SCCGWA, "REVERSAL1");
                CEP.TRC(SCCGWA, IBRSCASH.L_TXN_AMT);
            }
            CEP.TRC(SCCGWA, "222");
        } else {
            if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
                if (IBCCASH.SIGN == 'D') {
                    IBRSCASH.TXN_AMT = IBRSCASH.TXN_AMT - IBCCASH.AMT;
                } else {
                    IBRSCASH.TXN_AMT = IBRSCASH.TXN_AMT + IBCCASH.AMT;
                }
                CEP.TRC(SCCGWA, "NORMAL2");
                CEP.TRC(SCCGWA, IBRSCASH.TXN_AMT);
            } else {
                if (IBCCASH.SIGN == 'D') {
                    IBRSCASH.TXN_AMT = IBRSCASH.TXN_AMT + IBCCASH.AMT;
                } else {
                    IBRSCASH.TXN_AMT = IBRSCASH.TXN_AMT - IBCCASH.AMT;
                }
                CEP.TRC(SCCGWA, "REVERSAL2");
                CEP.TRC(SCCGWA, IBRSCASH.TXN_AMT);
            }
        }
        if (SCCGWA.COMM_AREA.AC_DATE >= IBRSCASH.UPD_DATE) {
            WS_VAR2.NEW_TXN_AMT = IBRSCASH.TXN_AMT;
            if (IBRSCASH.TXN_AMT > 0) {
                WS_VAR2.DRCR_FLG2 = 'D';
            } else {
                WS_VAR2.DRCR_FLG2 = 'C';
            }
        } else {
            WS_VAR2.NEW_TXN_AMT = IBRSCASH.L_TXN_AMT;
            if (IBRSCASH.L_TXN_AMT > 0) {
                WS_VAR2.DRCR_FLG2 = 'D';
            } else {
                WS_VAR2.DRCR_FLG2 = 'C';
            }
        }
        CEP.TRC(SCCGWA, "333");
        T000_REWRITE_IBTSCASH();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBRSCDTL);
        IBRSCDTL.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRSCDTL.KEY.VCH_NO = IBCCASH.VCH_NO;
        IBRSCDTL.KEY.VCH_SEQ = IBCCASH.VCH_SEQ;
        IBRSCDTL.POST_CTR = IBCCASH.POST_CTR;
        IBRSCDTL.CORP_ID = IBCCASH.CORP_ID;
        IBRSCDTL.CORP_ATTR = IBCCASH.CORP_ATTR;
        IBRSCDTL.CCY = IBCCASH.CCY;
        IBRSCDTL.BK_NAME = IBCCASH.BK_NAME;
        IBRSCDTL.SIGN = IBCCASH.SIGN;
        IBRSCDTL.AMT = IBCCASH.AMT;
        IBRSCDTL.AC_NO = IBCCASH.AC_NO;
        IBRSCDTL.VOSTRO_AC = IBCCASH.VOSTRO_AC;
        IBRSCDTL.L_TOT_AMT = IBCCASH.L_TOT_AMT;
        IBRSCDTL.TOT_AMT = IBCCASH.TOT_AMT;
        IBRSCDTL.UPD_DATE = IBCCASH.UPD_DATE;
        IBRSCDTL.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRSCDTL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_IBTSCDTL();
        if (pgmRtn) return;
        R000_WRITE_BPTNHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "233");
    }
    public void B050_01_GET_CORP_ID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_VARIABLES.POST_CTR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
            || !BPCPQORG.VIL_TYP.equalsIgnoreCase("043")) {
            WS_VARIABLES.CORP_ID = BPCPQORG.VIL_TYP;
            WS_VARIABLES.CORP_TRI_ID = BPCPQORG.TRA_TYP;
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQORG.VIL_TYP);
            CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
            if (!BPCPQORG.TRA_TYP.equalsIgnoreCase("00") 
                || !BPCPQORG.VIL_TYP.equalsIgnoreCase("043")) {
                WS_VARIABLES.CORP_ID = BPCPQORG.VIL_TYP;
                WS_VARIABLES.CORP_TRI_ID = BPCPQORG.TRA_TYP;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.NON_VIL_TRA, IBCCASH.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_WRITE_BPTNHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = IBRSCASH.AC_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (IBRSCASH.KEY.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.TX_TYP_CD = "A307";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "WS-VAR2";
        BPCPNHIS.INFO.FMT_ID_LEN = 17;
        BPCPNHIS.INFO.TX_TYP = 'A';
        CEP.TRC(SCCGWA, WS_VAR1);
        CEP.TRC(SCCGWA, WS_VAR2);
        BPCPNHIS.INFO.OLD_DAT_PT = WS_VAR1;
        BPCPNHIS.INFO.NEW_DAT_PT = WS_VAR2;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_INIT_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 266;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_WRITE_QUEUE() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.TS_CNT > Q_MAX_CNT) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.OUTOF_TS, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, IBCOCASH);
        SCCMPAG.DATA_LEN = 266;
        B_MPAG();
        if (pgmRtn) return;
        WS_VARIABLES.TS_CNT = (short) (WS_VARIABLES.TS_CNT + 1);
        CEP.TRC(SCCGWA, WS_VARIABLES.TS_CNT);
    }
    public void S000_CALL_IBZQIDNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CORP-ID-INQ", IBCQIDNM);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIMMST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_SCASH1() throws IOException,SQLException,Exception {
        IBTSCASH_BR.rp = new DBParm();
        IBTSCASH_BR.rp.TableName = "IBTSCASH";
        IBTSCASH_BR.rp.where = "CORP_ID = :IBRSCASH.KEY.CORP_ID "
            + "AND CORP_TRI_ID = :IBRSCASH.KEY.CORP_TRI_ID";
        IBTSCASH_BR.rp.order = "CCY";
        IBS.STARTBR(SCCGWA, IBRSCASH, this, IBTSCASH_BR);
    }
    public void T000_STARTBR_SCASH2() throws IOException,SQLException,Exception {
        IBTSCASH_BR.rp = new DBParm();
        IBTSCASH_BR.rp.TableName = "IBTSCASH";
        IBTSCASH_BR.rp.where = "CORP_ID > :IBRSCASH.KEY.CORP_ID";
        IBTSCASH_BR.rp.order = "CCY";
        IBS.STARTBR(SCCGWA, IBRSCASH, this, IBTSCASH_BR);
    }
    public void T000_STARTBR_SCASH3() throws IOException,SQLException,Exception {
        IBTSCASH_BR.rp = new DBParm();
        IBTSCASH_BR.rp.TableName = "IBTSCASH";
        IBTSCASH_BR.rp.where = "VOSTRO_AC = :IBRSCASH.VOSTRO_AC";
        IBTSCASH_BR.rp.order = "CORP_ID";
        IBS.STARTBR(SCCGWA, IBRSCASH, this, IBTSCASH_BR);
    }
    public void T000_READNEXT_IBTSCASH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRSCASH, this, IBTSCASH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = IBTSCASH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTSCASH() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBTSCASH_RD.where = "CORP_ID = :IBRSCASH.KEY.CORP_ID";
        IBS.READ(SCCGWA, IBRSCASH, this, IBTSCASH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.NOTFND_SCASH, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "333");
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = IBTSCASH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_IBTSCASH() throws IOException,SQLException,Exception {
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBTSCASH_RD.upd = true;
        IBS.READ(SCCGWA, IBRSCASH, IBTSCASH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.NOTFND_SCASH, IBCCASH.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = IBTSCASH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTSCASH() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBRSCASH.TXN_AMT);
        IBTSCASH_RD = new DBParm();
        IBTSCASH_RD.TableName = "IBTSCASH";
        IBS.REWRITE(SCCGWA, IBRSCASH, IBTSCASH_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
        } else {
            CEP.TRC(SCCGWA, "222");
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = IBTSCASH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTSCDTL() throws IOException,SQLException,Exception {
        IBTSCDTL_RD = new DBParm();
        IBTSCDTL_RD.TableName = "IBTSCDTL";
        IBS.WRITE(SCCGWA, IBRSCDTL, IBTSCDTL_RD);
    }
    public void T000_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMANIP() throws IOException,SQLException,Exception {
        IBTMANIP_RD = new DBParm();
        IBTMANIP_RD.TableName = "IBTMANIP";
        IBTMANIP_RD.where = "IPT_BR = :IBCCASH.POST_CTR "
            + "AND STS = 'P'";
        IBTMANIP_RD.fst = true;
        IBS.READ(SCCGWA, IBRMANIP, this, IBTMANIP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONDITION_FLAG.TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONDITION_FLAG.TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTMANIP";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
